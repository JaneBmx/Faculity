package com.vlasova.pool;

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

    ConnectionPool() {
        free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        given = new ArrayDeque<>();
    }

    public void init() {
        if (!isExist.get()) {
            try {
                free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
                given = new ArrayDeque<>();

                initDBProperties();
                getConnection();
                isExist.set(true);
            } catch (Exception e) {
                //TODO log
                //own exc
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

    public void closePool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                free.take().finalClose();
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO own exc
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                //TODO own exc
            }
        }
        //deregisterDrivers();
    }

    private void getConnections() throws SQLException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            free.add(new ProxyConnection(DriverManager.getConnection(URL, properties)));
        }
        isExist.set(true);
    }

    private void initDBProperties() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            //TODO log
            //own exc
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