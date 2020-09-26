package com.joyintech.tams.svreport.infomanagement.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationalInfo004Mapper {


    String getFilterIdByTable(String tableName);

    List<String> getBaseInfoIds(String tableName);

}
