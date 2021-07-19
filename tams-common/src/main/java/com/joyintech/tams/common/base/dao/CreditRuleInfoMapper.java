package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditRuleInfo;

@Repository
public interface CreditRuleInfoMapper {
    int deleteByPrimaryKey(String creditRuleId);

    int insert(CreditRuleInfo record);

    int insertSelective(CreditRuleInfo record);

    int insertBasicByBasicInfo(CreditRuleInfo record);

    CreditRuleInfo selectByPrimaryKey(String creditRuleId);

    int updateByPrimaryKeySelective(CreditRuleInfo record);

    int updateByPrimaryKey(CreditRuleInfo record);
}