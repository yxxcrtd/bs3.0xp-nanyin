package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRepDealSecSubject;

@Repository
public interface ValRepDealSecSubjectMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(ValRepDealSecSubject record);

    int insertSelective(ValRepDealSecSubject record);

    int insertBasicByBasicInfo(ValRepDealSecSubject record);

    ValRepDealSecSubject selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(ValRepDealSecSubject record);

    int updateByPrimaryKey(ValRepDealSecSubject record);
}