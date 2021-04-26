package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleDataRule;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleDataRuleMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SettleDataRule record);

    int insertSelective(SettleDataRule record);

    int insertBasicByBasicInfo(SettleDataRule record);

    SettleDataRule selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SettleDataRule record);

    int updateByPrimaryKey(SettleDataRule record);
}