package com.databankgroup.bigdata.repository;

import com.databankgroup.bigdata.model.Table;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRowMapper implements RowMapper<Table> {
    @Override
    public Table mapRow(ResultSet rs, int i) throws SQLException {

        Table t = new Table();
        t.setBackconnectAccountNumber(rs.getString("bcclientcode"));
        t.setMilesAccountNumber(rs.getString("milesclientcode"));
        t.setMobile(rs.getString(rs.getString("mobile")));
        t.setNameInBackconnect(rs.getString("client_name"));
        t.setNameInMiles(rs.getString("fullname"));
        t.setPlanID(rs.getString("planid"));
        t.setAddress(rs.getString("address"));
        return t;

    }
}
