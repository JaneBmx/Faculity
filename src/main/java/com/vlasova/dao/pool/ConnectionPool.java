package com.vlasova.dao.pool;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vlasova.dao.exception.connection.ClosePoolException;
import com.vlasova.dao.exception.connection.CreatePoolException;
import com.vlasova.dao.exception.connection.InitiationPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConnectionPool {
    INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 32;
    private static final String PROPERTY_PATH = "resources/db.properties";
    private final AtomicBoolean isExist = new AtomicBoolean(false);
    private final BlockingQueue<ProxyConnection> free = new LinkedBlockingDeque<>();
    private final Queue<ProxyConnection> given = new ArrayDeque<>();
    private String dbUrl;
    private String user;
    private String password;
    private Properties properties;

    public void init() {
        if (!isExist.get()) {
            try {
                initDBProperties();
            } catch (InitiationPoolException e) {
                LOGGER.fatal(e);
                throw new CreatePoolException("Fail to initialise properties for database.", e);
            }
            try {
                fillPool();
                isExist.set(true);
            } catch (SQLException e) {
                LOGGER.warn("Fail to fill pool.", e);
                isExist.set(false);
                throw new CreatePoolException("Fail to fill pool.", e);
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            given.offer(connection);
        } catch (InterruptedException e) {
            //TODO fix venerability with loosin' connection
            Thread.currentThread().interrupt();
            LOGGER.warn(e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        if (connection != null) {
            given.remove(connection);
            if (!free.offer(connection)) {
                LOGGER.warn("Fail to return connection in pool.");
            }
        }
    }

    public void closePool() throws ClosePoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                free.take().finalClose();
            } catch (SQLException e) {
                throw new ClosePoolException(e);
            } catch (InterruptedException e) {
                LOGGER.warn(e);
                Thread.currentThread().interrupt();
                throw new ClosePoolException("Failed to close pool");
            }
        }
        deregisterDrivers();
        isExist.set(false);
    }

    private void fillPool() throws SQLException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            free.add(new ProxyConnection(DriverManager.getConnection(dbUrl,properties)));
        }
    }

    private void initDBProperties() throws InitiationPoolException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "root");
            properties.put("serverTimezone", "GMT");
            properties.put("zeroDateTimeBehavior", "CONVERT_TO_NULL");
            properties.put("autoReconnect","true");
            properties.put("characterEncoding","UTF-8");
            dbUrl = "jdbc:mysql://localhost:3306/faculty";
        } catch (ClassNotFoundException e) {
            throw new InitiationPoolException("cant load db prop.", e);
        }


//        properties = new Properties();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_PATH);
//        try {
//            properties.load(inputStream);
//
////            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            // Class.forName(properties.getProperty("driver"));
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            dbUrl = properties.getProperty("url");
//        } catch (IOException e) {
//            LOGGER.warn(e);
//            throw new InitiationPoolException("Fail to load properties.", e);
//        }catch (ClassNotFoundException e){
//            LOGGER.warn("Fail to load drover", e);
//            throw new InitiationPoolException(e);
////        } catch (SQLException e) {
////            LOGGER.warn("Fail to load drover", e);
////            throw new InitiationPoolException(e);
//        }
//        try (FileInputStream inputStream = new FileInputStream(PROPERTY_PATH)) {
//            properties = new Properties();
//            properties.load(Objects.requireNonNull(inputStream));
//            Class.forName(properties.getProperty("driver"));
//            dbUrl = properties.getProperty("url");
//        } catch (ClassNotFoundException e) {
//            LOGGER.warn(e);
//            throw new InitiationPoolException("Fail to load driver.", e);
//        } catch (IOException e) {
//            LOGGER.warn(e);
//            throw new InitiationPoolException("Fail to read properties data.", e);
//        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
    }
}