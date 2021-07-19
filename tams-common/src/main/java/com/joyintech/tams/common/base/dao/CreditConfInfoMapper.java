package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditConfInfo;

@Repository
public interface CreditConfInfoMapper {
    int deleteByPrimaryKey(String creditMstId);

    int insert(CreditConfInfo record);

    int insertSelective(CreditConfInfo record);

    int insertBasicByBasicInfo(CreditConfInfo record);

    CreditConfInfo selectByPrimaryKey(String creditMstId);

    int updateByPrimaryKeySelective(CreditConfInfo record);

    int updateByPrimaryKey(CreditConfInfo record);
}