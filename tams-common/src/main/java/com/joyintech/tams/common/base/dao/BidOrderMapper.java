package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BidOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface BidOrderMapper {
    int deleteByPrimaryKey(String bidId);

    int insert(BidOrder record);

    int insertSelective(BidOrder record);

    int insertBasicByBasicInfo(BidOrder record);

    BidOrder selectByPrimaryKey(String bidId);

    int updateByPrimaryKeySelective(BidOrder record);

    int updateByPrimaryKey(BidOrder record);
}