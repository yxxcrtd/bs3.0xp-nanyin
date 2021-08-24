package com.joyintech.service.common.config.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DynamicConfigMapper {

    List<Map> selectByDynId(String dynId);
    
    int updateByParam(Map<String, Object> param);

    List<SelectOptionDto> selectPage(Map<String, Object> param);

    List<Map> selectUserDynByDynId(Map<String, Object> param);

    Integer updateUserDynByParam(Map<String, Object> param);

    Integer insertUserDyn(Map<String, Object> param);
}
