package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValRuleAssetdetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValRuleAssetdetailMapper {
    int deleteByPrimaryKey(String ruleDetailId);

    int insert(BokValRuleAssetdetail record);

    int insertSelective(BokValRuleAssetdetail record);

    int insertBasicByBasicInfo(BokValRuleAssetdetail record);

    BokValRuleAssetdetail selectByPrimaryKey(String ruleDetailId);

    int updateByPrimaryKeySelective(BokValRuleAssetdetail record);

    int updateByPrimaryKey(BokValRuleAssetdetail record);
}