package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinDealPledgePre;
import com.joyintech.tams.common.base.entity.FinDealPledgePreKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinDealPledgePreMapper {
    int deleteByPrimaryKey(FinDealPledgePreKey key);

    int insert(FinDealPledgePre record);

    int insertSelective(FinDealPledgePre record);

    int insertBasicByBasicInfo(FinDealPledgePre record);

    FinDealPledgePre selectByPrimaryKey(FinDealPledgePreKey key);

    int updateByPrimaryKeySelective(FinDealPledgePre record);

    int updateByPrimaryKey(FinDealPledgePre record);
}