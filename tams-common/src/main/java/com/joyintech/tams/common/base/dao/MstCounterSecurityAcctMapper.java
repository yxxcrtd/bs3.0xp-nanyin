package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstCounterSecurityAcct;


@Repository
public interface MstCounterSecurityAcctMapper {
    int insert(MstCounterSecurityAcct record);

    int insertSelective(MstCounterSecurityAcct record);

    int insertBasicByBasicInfo(MstCounterSecurityAcct record);
}