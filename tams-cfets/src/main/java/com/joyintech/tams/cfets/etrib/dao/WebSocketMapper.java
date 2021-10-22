package com.joyintech.tams.cfets.etrib.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WebSocketMapper {

    int select(Map<String, Object> map);
    
    void insert(Map<String, Object> map);

    void update(Map<String, Object> map);
}
