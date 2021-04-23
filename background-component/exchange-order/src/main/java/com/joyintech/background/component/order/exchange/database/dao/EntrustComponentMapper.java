package com.joyintech.background.component.order.exchange.database.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.order.exchange.database.entity.EntrustComponent;

@Repository
public interface EntrustComponentMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EntrustComponent record);

    int insertSelective(EntrustComponent record);

    int insertBasicByBasicInfo(EntrustComponent record);

    EntrustComponent selectByPrimaryKey(String entrustId);
    
    List<EntrustComponent> selectByPrimaryKeyPtl(Map<String, Object> pageParam);

    int updateByPrimaryKeySelective(EntrustComponent record);

    int updateByPrimaryKey(EntrustComponent record);
}