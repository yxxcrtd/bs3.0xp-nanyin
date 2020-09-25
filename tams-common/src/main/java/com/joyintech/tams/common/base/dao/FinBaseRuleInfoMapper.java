package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinBaseRuleInfoMapper {
    int deleteByPrimaryKey(FinBaseRuleInfoKey key);

    int insert(FinBaseRuleInfo record);

    int insertSelective(FinBaseRuleInfo record);

    int insertBasicByBasicInfo(FinBaseRuleInfo record);

    FinBaseRuleInfo selectByPrimaryKey(FinBaseRuleInfoKey key);

    int updateByPrimaryKeySelective(FinBaseRuleInfo record);

    int updateByPrimaryKey(FinBaseRuleInfo record);
}