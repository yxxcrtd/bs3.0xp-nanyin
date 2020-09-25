package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrAutoDispenseRuleDetail;
import com.joyintech.tams.common.base.entity.EtrAutoDispenseRuleDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrAutoDispenseRuleDetailMapper {
    int deleteByPrimaryKey(EtrAutoDispenseRuleDetailKey key);

    int insert(EtrAutoDispenseRuleDetail record);

    int insertSelective(EtrAutoDispenseRuleDetail record);

    int insertBasicByBasicInfo(EtrAutoDispenseRuleDetail record);

    EtrAutoDispenseRuleDetail selectByPrimaryKey(EtrAutoDispenseRuleDetailKey key);

    int updateByPrimaryKeySelective(EtrAutoDispenseRuleDetail record);

    int updateByPrimaryKey(EtrAutoDispenseRuleDetail record);
}