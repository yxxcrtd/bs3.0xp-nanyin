package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdVouchDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdVouchDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(TrdVouchDeal record);

    int insertSelective(TrdVouchDeal record);

    int insertBasicByBasicInfo(TrdVouchDeal record);

    TrdVouchDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(TrdVouchDeal record);

    int updateByPrimaryKey(TrdVouchDeal record);
}