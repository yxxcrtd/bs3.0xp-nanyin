package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinDealTarget;
import com.joyintech.tams.common.base.entity.FinDealTargetKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinDealTargetMapper {
    int deleteByPrimaryKey(FinDealTargetKey key);

    int insert(FinDealTarget record);

    int insertSelective(FinDealTarget record);

    int insertBasicByBasicInfo(FinDealTarget record);

    FinDealTarget selectByPrimaryKey(FinDealTargetKey key);

    int updateByPrimaryKeySelective(FinDealTarget record);

    int updateByPrimaryKey(FinDealTarget record);
}