package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRate;
import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRateKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PaFinProductBuyoutRateMapper {
    int deleteByPrimaryKey(PaFinProductBuyoutRateKey key);

    int insert(PaFinProductBuyoutRate record);

    int insertSelective(PaFinProductBuyoutRate record);

    int insertBasicByBasicInfo(PaFinProductBuyoutRate record);

    PaFinProductBuyoutRate selectByPrimaryKey(PaFinProductBuyoutRateKey key);

    int updateByPrimaryKeySelective(PaFinProductBuyoutRate record);

    int updateByPrimaryKey(PaFinProductBuyoutRate record);
}