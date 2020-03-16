package com.vlasova.pool;

import com.vlasova.exception.*;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConnectionPool {
    INSTANCE;
    private Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 32;
    private static final String DB_PROPERTIES_PATH = "src/main/resources/db.properties";
    private BlockingQueue<ProxyConnection> free;
    private Queue<ProxyConnection> given;
    private AtomicBoolean isExist;
    private Properties properties;
    private static final String URL = "";

    ConnectionPool() {
        free = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        given = new ArrayDeque<>();
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
                initDBProperties();
                fillPool();
                isExist.set(true);
            } catch (Exception e) {
                logger.warn("Fail to init pool", e);
                throw new InitiationPoolException(e);
            }
        }
    }

    // Get connection from connection pool
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            given.offer(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn(e);
        }
        return connection;
    }

    //Back connection to pool
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
        isExist.set(false);
    }

    private void fillPool() throws SQLException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            free.add(new ProxyConnection(DriverManager.getConnection(URL, properties)));
        }
    }

    private void initDBProperties() throws InitiationPoolException {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_PATH));
        } catch (IOException e) {
            logger.warn(e);
            throw new InitiationPoolException("Failed to load DB properties", e);
        }
    }
}