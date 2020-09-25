package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTgtSecinfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOpicsTgtSecinfoMapper {
    int deleteByPrimaryKey(String secid);

    int insert(InterfOpicsTgtSecinfo record);

    int insertSelective(InterfOpicsTgtSecinfo record);

    int insertBasicByBasicInfo(InterfOpicsTgtSecinfo record);

    InterfOpicsTgtSecinfo selectByPrimaryKey(String secid);

    int updateByPrimaryKeySelective(InterfOpicsTgtSecinfo record);

    int updateByPrimaryKey(InterfOpicsTgtSecinfo record);
}