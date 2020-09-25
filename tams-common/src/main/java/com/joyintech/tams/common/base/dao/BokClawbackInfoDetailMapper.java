package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokClawbackInfoDetail;
import com.joyintech.tams.common.base.entity.BokClawbackInfoDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokClawbackInfoDetailMapper {
    int deleteByPrimaryKey(BokClawbackInfoDetailKey key);

    int insert(BokClawbackInfoDetail record);

    int insertSelective(BokClawbackInfoDetail record);

    int insertBasicByBasicInfo(BokClawbackInfoDetail record);

    BokClawbackInfoDetail selectByPrimaryKey(BokClawbackInfoDetailKey key);

    int updateByPrimaryKeySelective(BokClawbackInfoDetail record);

    int updateByPrimaryKey(BokClawbackInfoDetail record);
}