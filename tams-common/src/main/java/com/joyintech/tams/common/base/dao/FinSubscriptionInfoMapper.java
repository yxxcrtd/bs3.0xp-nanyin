package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.tams.common.base.entity.FinSubscriptionInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinSubscriptionInfoMapper {
    int deleteByPrimaryKey(FinSubscriptionInfoKey key);

    int insert(FinSubscriptionInfo record);

    int insertSelective(FinSubscriptionInfo record);

    int insertBasicByBasicInfo(FinSubscriptionInfo record);

    FinSubscriptionInfo selectByPrimaryKey(FinSubscriptionInfoKey key);

    int updateByPrimaryKeySelective(FinSubscriptionInfo record);

    int updateByPrimaryKey(FinSubscriptionInfo record);
}