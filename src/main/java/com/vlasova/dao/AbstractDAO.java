package com.vlasova.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);
    protected ResultSet resultSet;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
    }
}
