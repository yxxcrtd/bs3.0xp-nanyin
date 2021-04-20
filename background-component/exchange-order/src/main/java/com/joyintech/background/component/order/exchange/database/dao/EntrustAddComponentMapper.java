package com.joyintech.background.component.order.exchange.database.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.order.exchange.database.entity.EntrustAddComponent;

@Repository
public interface EntrustAddComponentMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EntrustAddComponent record);

    int insertSelective(EntrustAddComponent record);

    int insertBasicByBasicInfo(EntrustAddComponent record);

    EntrustAddComponent selectByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(EntrustAddComponent record);

    int updateByPrimaryKey(EntrustAddComponent record);
}