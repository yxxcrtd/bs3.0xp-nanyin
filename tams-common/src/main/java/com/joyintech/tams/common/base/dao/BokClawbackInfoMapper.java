package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokClawbackInfo;
import com.joyintech.tams.common.base.entity.BokClawbackInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokClawbackInfoMapper {
    int deleteByPrimaryKey(BokClawbackInfoKey key);

    int insert(BokClawbackInfo record);

    int insertSelective(BokClawbackInfo record);

    int insertBasicByBasicInfo(BokClawbackInfo record);

    BokClawbackInfo selectByPrimaryKey(BokClawbackInfoKey key);

    int updateByPrimaryKeySelective(BokClawbackInfo record);

    int updateByPrimaryKey(BokClawbackInfo record);
}