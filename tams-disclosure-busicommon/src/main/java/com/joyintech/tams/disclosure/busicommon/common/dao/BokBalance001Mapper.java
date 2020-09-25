package com.joyintech.tams.disclosure.busicommon.common.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalanceKey;

@Repository
public interface BokBalance001Mapper {
    int deleteByPrimaryKey(BokBalanceKey key);
    
    int deleteByBooksetId(BokBalanceKey key);

    int insert(BokBalance record);

    int insertSelective(BokBalance record);

    int insertBasicByBasicInfo(BokBalance record);

    BokBalance selectByPrimaryKey(BokBalanceKey key);

    int updateByPrimaryKeySelective(BokBalance record);

    int updateByPrimaryKey(BokBalance record);
}