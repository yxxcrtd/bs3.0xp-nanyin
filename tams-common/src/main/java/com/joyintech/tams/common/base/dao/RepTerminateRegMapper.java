package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepTerminateReg;
import org.springframework.stereotype.Repository;

@Repository
public interface RepTerminateRegMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepTerminateReg record);

    int insertSelective(RepTerminateReg record);

    int insertBasicByBasicInfo(RepTerminateReg record);

    RepTerminateReg selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepTerminateReg record);

    int updateByPrimaryKey(RepTerminateReg record);
}