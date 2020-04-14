package com.vlasova.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vlasova.exception.connection.ClosePoolException;
import com.vlasova.exception.connection.CreatePoolException;
import com.vlasova.exception.connection.InitiationPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConnectionPool {
    INSTANCE;
    private Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String PROPERTY_PATH = "C:/Users/Bubaleh/Desktop/Faculity/src/main/resources/db.properties";
    private static final int DEFAULT_POOL_SIZE = 32;
    private String dbUrl;
    private BlockingQueue<ProxyConnection> free;
    private Queue<ProxyConnection> given;
    private AtomicBoolean isExist = new AtomicBoolean(false);
    private Properties properties;

    private ConnectionPool() {
        free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        given = new ArrayDeque<>();
        try {
            initDBProperties();
        } catch (InitiationPoolException e) {
            logger.fatal(e);
            throw new CreatePoolException("Fail to initialise property.", e);
        }
        try {
            init();
        } catch (InitiationPoolException e) {
            logger.fatal(e);
            throw new CreatePoolException("Fail to initialize pool.", e);
        }
    }

    private void init() throws InitiationPoolException {
        if (!isExist.get()) {
            try {
                fillPool();
                isExist.set(true);
            } catch (Exception e) {
                logger.warn("Fail to fill pool.", e);
                throw new InitiationPoolException(e);
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            given.offer(connection);
        } catch (InterruptedException e) {
            if (connection!=null){
                //todo add connection obratno
            }
            Thread.currentThread().interrupt();
            logger.warn(e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        if (connection != null) {
            given.remove(connection);
            if (!free.offer(connection)) {
                logger.warn("Fail to return connection in pool.");
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
                logger.warn(e);
                Thread.currentThread().interrupt();
                throw new ClosePoolException("Failed to close pool");
            }
        }
        deregisterDrivers();
        isExist.set(false);
    }

    private void fillPool() throws SQLException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            free.add(new ProxyConnection(DriverManager.getConnection(dbUrl, properties)));
        }
    }

    private void initDBProperties() throws InitiationPoolException {
        try (FileInputStream inputStream = new FileInputStream(PROPERTY_PATH)) {
            properties = new Properties();
            properties.load(Objects.requireNonNull(inputStream));
            Class.forName(properties.getProperty("driver"));
            dbUrl = properties.getProperty("url");
        } catch (ClassNotFoundException e) {
            logger.warn(e);
            throw new InitiationPoolException("Failed to initialize properties.", e);
        } catch (IOException e) {
            logger.warn(e);
            throw new InitiationPoolException("Failed to load properties.", e);
        }
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }
}