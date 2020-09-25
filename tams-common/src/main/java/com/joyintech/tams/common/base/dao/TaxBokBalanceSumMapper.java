package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxBokBalanceSumMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TaxBokBalanceSum record);

    int insertSelective(TaxBokBalanceSum record);

    int insertBasicByBasicInfo(TaxBokBalanceSum record);

    TaxBokBalanceSum selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TaxBokBalanceSum record);

    int updateByPrimaryKey(TaxBokBalanceSum record);
}