package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokDealAccountingMapper {
    int deleteByPrimaryKey(BokDealAccountingKey key);

    int insert(BokDealAccounting record);

    int insertSelective(BokDealAccounting record);

    int insertBasicByBasicInfo(BokDealAccounting record);

    BokDealAccounting selectByPrimaryKey(BokDealAccountingKey key);

    int updateByPrimaryKeySelective(BokDealAccounting record);

    int updateByPrimaryKey(BokDealAccounting record);
}