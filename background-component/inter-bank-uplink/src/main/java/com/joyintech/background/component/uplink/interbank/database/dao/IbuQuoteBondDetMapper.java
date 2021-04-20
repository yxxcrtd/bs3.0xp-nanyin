package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondDet;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteBondDetMapper {
    int deleteByPrimaryKey(String detId);

    int insert(IbuQuoteBondDet record);

    int insertSelective(IbuQuoteBondDet record);

    int insertBasicByBasicInfo(IbuQuoteBondDet record);

    IbuQuoteBondDet selectByPrimaryKey(String detId);

    int updateByPrimaryKeySelective(IbuQuoteBondDet record);

    int updateByPrimaryKey(IbuQuoteBondDet record);
}