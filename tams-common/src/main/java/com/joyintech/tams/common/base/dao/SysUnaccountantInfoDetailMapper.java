package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUnaccountantInfoDetailMapper {
    int deleteByPrimaryKey(SysUnaccountantInfoDetailKey key);

    int insert(SysUnaccountantInfoDetail record);

    int insertSelective(SysUnaccountantInfoDetail record);

    int insertBasicByBasicInfo(SysUnaccountantInfoDetail record);

    SysUnaccountantInfoDetail selectByPrimaryKey(SysUnaccountantInfoDetailKey key);

    int updateByPrimaryKeySelective(SysUnaccountantInfoDetail record);

    int updateByPrimaryKey(SysUnaccountantInfoDetail record);
}