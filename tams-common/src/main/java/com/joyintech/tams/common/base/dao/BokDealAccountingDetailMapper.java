package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokDealAccountingDetailMapper {
    int deleteByPrimaryKey(BokDealAccountingDetailKey key);

    int insert(BokDealAccountingDetail record);

    int insertSelective(BokDealAccountingDetail record);

    int insertBasicByBasicInfo(BokDealAccountingDetail record);

    BokDealAccountingDetail selectByPrimaryKey(BokDealAccountingDetailKey key);

    int updateByPrimaryKeySelective(BokDealAccountingDetail record);

    int updateByPrimaryKey(BokDealAccountingDetail record);
}