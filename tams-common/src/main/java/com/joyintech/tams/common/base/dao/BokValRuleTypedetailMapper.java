package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValRuleTypedetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValRuleTypedetailMapper {
    int deleteByPrimaryKey(String ruleDetailId);

    int insert(BokValRuleTypedetail record);

    int insertSelective(BokValRuleTypedetail record);

    int insertBasicByBasicInfo(BokValRuleTypedetail record);

    BokValRuleTypedetail selectByPrimaryKey(String ruleDetailId);

    int updateByPrimaryKeySelective(BokValRuleTypedetail record);

    int updateByPrimaryKey(BokValRuleTypedetail record);
}