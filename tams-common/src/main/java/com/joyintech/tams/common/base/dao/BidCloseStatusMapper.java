package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BidCloseStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface BidCloseStatusMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(BidCloseStatus record);

    int insertSelective(BidCloseStatus record);

    int insertBasicByBasicInfo(BidCloseStatus record);

    BidCloseStatus selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(BidCloseStatus record);

    int updateByPrimaryKey(BidCloseStatus record);
}