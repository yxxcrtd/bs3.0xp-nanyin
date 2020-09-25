package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.BokTradeOrderKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokTradeOrderMapper {
    int deleteByPrimaryKey(BokTradeOrderKey key);

    int insert(BokTradeOrder record);

    int insertSelective(BokTradeOrder record);

    int insertBasicByBasicInfo(BokTradeOrder record);

    BokTradeOrder selectByPrimaryKey(BokTradeOrderKey key);

    int updateByPrimaryKeySelective(BokTradeOrder record);

    int updateByPrimaryKey(BokTradeOrder record);
}