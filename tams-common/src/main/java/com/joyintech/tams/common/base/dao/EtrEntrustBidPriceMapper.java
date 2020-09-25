package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;
import com.joyintech.tams.common.base.entity.EtrEntrustBidPriceKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustBidPriceMapper {
    int deleteByPrimaryKey(EtrEntrustBidPriceKey key);

    int insert(EtrEntrustBidPrice record);

    int insertSelective(EtrEntrustBidPrice record);

    int insertBasicByBasicInfo(EtrEntrustBidPrice record);

    EtrEntrustBidPrice selectByPrimaryKey(EtrEntrustBidPriceKey key);

    int updateByPrimaryKeySelective(EtrEntrustBidPrice record);

    int updateByPrimaryKey(EtrEntrustBidPrice record);
}