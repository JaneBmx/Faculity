package com.vlasova.pool;

import com.vlasova.exception.ClosePoolException;
import com.vlasova.exception.InitiationPoolException;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;
    private static final int DEFAULT_POOL_SIZE = 32;
    private BlockingQueue<ProxyConnection> free;
    private Queue<ProxyConnection> given;
    private AtomicBoolean isExist;
    private Properties properties;
    private static final String URL = "";

//    ConnectionPool() {
//        free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
//        given = new ArrayDeque<>();
//    }

    public void init() throws InitiationPoolException {
        if (!isExist.get()) {
            free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
            given = new ArrayDeque<>();
            try {
                initDBProperties();
                getConnections();
                isExist.set(true);
            } catch (Exception e) {
                //TODO log
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
            Thread.currentThread().interrupt();
            //TODO log
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        given.remove(connection);
        free.offer(connection);
    }

    public void closePool() throws ClosePoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                free.take().finalClose();
            } catch (SQLException e) {
                throw new ClosePoolException(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ClosePoolException("Failed to close pool");
            }
        }
        //deregisterDrivers();
    }

    private void getConnections() throws SQLException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            free.add(new ProxyConnection(DriverManager.getConnection(URL, properties)));
        }
    }

    private void initDBProperties() throws InitiationPoolException {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            //TODO log
            throw new InitiationPoolException("Failed to load DB properties", e);
        }
    }
//    private void deregisterDrivers() {
//        DriverManager.deregisterDriver();
////        Enumeration<Driver> drivers = DriverManager.getDrivers();
////
////        while (drivers.hasMoreElements()) {
////            try {
////                DriverManager.deregisterDriver(drivers.nextElement());
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
//    }
}