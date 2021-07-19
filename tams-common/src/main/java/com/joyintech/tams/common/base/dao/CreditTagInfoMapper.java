package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditTagInfo;

@Repository
public interface CreditTagInfoMapper {
    int deleteByPrimaryKey(String creditTagId);

    int insert(CreditTagInfo record);

    int insertSelective(CreditTagInfo record);

    int insertBasicByBasicInfo(CreditTagInfo record);

    CreditTagInfo selectByPrimaryKey(String creditTagId);

    int updateByPrimaryKeySelective(CreditTagInfo record);

    int updateByPrimaryKey(CreditTagInfo record);
}