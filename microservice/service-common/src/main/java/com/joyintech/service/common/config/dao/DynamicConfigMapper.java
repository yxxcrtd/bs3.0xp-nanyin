package com.joyintech.service.common.config.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DynamicConfigMapper {

    List<Map> selectByDynId(String dynId);
    
    int updateByParam(Map<String, Object> param);
}
