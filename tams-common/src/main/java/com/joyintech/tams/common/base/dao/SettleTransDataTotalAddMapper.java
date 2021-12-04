package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataTotalAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataTotalAddMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(SettleTransDataTotalAdd record);

    int insertSelective(SettleTransDataTotalAdd record);

    int insertBasicByBasicInfo(SettleTransDataTotalAdd record);

    SettleTransDataTotalAdd selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(SettleTransDataTotalAdd record);

    int updateByPrimaryKey(SettleTransDataTotalAdd record);
}