package com.databankgroup.bigdata.repository;

import com.databankgroup.bigdata.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TableDao implements TableRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Table> searchByClientName(String searchString) {

        TableRowMapper mapper = new TableRowMapper();

        String sql="select milesclientcode,bcclientcode,fullname,client_name,planid,mobile,address from " +
                "deNORMALIZED "+searchString;



        return this.jdbcTemplate.query(sql,new TableRowMapper());





    }

    @Override
    public List<Table> searchForClient(String searchString) {
        TableRowMapper mapper = new TableRowMapper();

        String sql="select milesclientcode,bcclientcode," +
                "COALESCE(NULLIF(fullname,''), client_name, fullname) full_name,planid,mobile,address from " +
                "deNORMALIZED "+searchString;

        List<Table> custList = new ArrayList<Table>();
        List<Map<String,Object>> rows=this.jdbcTemplate.queryForList(sql);

        for (Map row : rows){
            Table tb = new Table();
            tb.setAddress((String)row.get("address"));
            tb.setPlanID((String)row.get("planid"));
            tb.setNameInMiles((String)row.get("full_name"));
            tb.setMobile((String)row.get("mobile"));
            tb.setMilesAccountNumber((String)row.get("milesclientcode"));
            tb.setBackconnectAccountNumber((String)row.get("bcclientcode"));
            custList.add(tb);
        }
        return custList;



    }


    @Override
    public List<String> getAllProducts() {
        String sql = "select distinct planid from NORMALIZED";
        List<String>productList = new ArrayList<>();
        List<Map<String,Object>>rows = this.jdbcTemplate.queryForList(sql);
        for (Map row : rows){
            productList.add((String)row.get("planid"));
        }
        return productList;
    }
}
