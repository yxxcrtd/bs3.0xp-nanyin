package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataStatusMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(SettleTransDataStatus record);

    int insertSelective(SettleTransDataStatus record);

    int insertBasicByBasicInfo(SettleTransDataStatus record);

    SettleTransDataStatus selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(SettleTransDataStatus record);

    int updateByPrimaryKey(SettleTransDataStatus record);
}