package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokBalanceKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBalanceMapper {
    int deleteByPrimaryKey(BokBalanceKey key);

    int insert(BokBalance record);

    int insertSelective(BokBalance record);

    int insertBasicByBasicInfo(BokBalance record);

    BokBalance selectByPrimaryKey(BokBalanceKey key);

    int updateByPrimaryKeySelective(BokBalance record);

    int updateByPrimaryKey(BokBalance record);
}