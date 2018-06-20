package com.databankgroup.bigdata.repository;

import com.databankgroup.bigdata.model.Table;

import java.util.ArrayList;
import java.util.List;

public interface TableRepository {

    default List<Table> searchByClientName(String searchString) {
        return null;
    }

    List<Table> searchForClient(String searchString);

    default ArrayList<Table> searchByIntegraAccNo(String searchString) {
        return null;
    }

    default public ArrayList<Table> searchByBackconnectAccNo(String searchString) {
        return null;
    }
}
