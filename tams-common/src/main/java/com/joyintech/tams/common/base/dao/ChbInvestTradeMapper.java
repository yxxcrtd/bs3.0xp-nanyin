package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestTradeMapper {
    int deleteByPrimaryKey(String tradeSeqno);

    int insert(ChbInvestTrade record);

    int insertSelective(ChbInvestTrade record);

    int insertBasicByBasicInfo(ChbInvestTrade record);

    ChbInvestTrade selectByPrimaryKey(String tradeSeqno);

    int updateByPrimaryKeySelective(ChbInvestTrade record);

    int updateByPrimaryKeyWithBLOBs(ChbInvestTrade record);

    int updateByPrimaryKey(ChbInvestTrade record);
}