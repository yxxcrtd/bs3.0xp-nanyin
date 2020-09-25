package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditAssetpool;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAssetpoolMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditAssetpool record);

    int insertSelective(CreditAssetpool record);

    int insertBasicByBasicInfo(CreditAssetpool record);

    CreditAssetpool selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditAssetpool record);

    int updateByPrimaryKey(CreditAssetpool record);
}