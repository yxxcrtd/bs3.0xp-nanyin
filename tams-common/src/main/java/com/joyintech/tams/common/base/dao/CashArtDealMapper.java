package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CashArtDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface CashArtDealMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CashArtDeal record);

    int insertSelective(CashArtDeal record);

    int insertBasicByBasicInfo(CashArtDeal record);

    CashArtDeal selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CashArtDeal record);

    int updateByPrimaryKey(CashArtDeal record);
}