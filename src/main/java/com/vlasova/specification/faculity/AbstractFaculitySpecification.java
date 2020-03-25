package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class AbstractFaculitySpecification {
    private static final String ID = "faculity_id";
    private static final String NAME = "faculity_name";
    private static final String INFO = "faculity_info";
    private static final String FREE_ACCEPT_PLAN = "free_accept_plan";
    private static final String PAID_ACCEPT_PLAN = "paid_accept_plan";
    //add smth to adding subjects?
    protected ResultSet resultSet;
    protected Set<Faculity> faculities;

    public AbstractFaculitySpecification() {
    }

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {

            }
        }
    }

    protected Faculity createFaculity() {
        Faculity faculity = null;

        if (resultSet != null) {
            faculity = new Faculity();
            try {
                faculity.setId(resultSet.getInt(ID));
                faculity.setName(resultSet.getString(NAME));
                faculity.setInfo(resultSet.getString(INFO));
                faculity.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
                faculity.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));
                //add smth to adding subjects?
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return faculity;
    }
}













