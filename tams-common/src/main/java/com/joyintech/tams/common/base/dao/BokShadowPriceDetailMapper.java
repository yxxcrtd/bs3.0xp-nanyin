package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokShadowPriceDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokShadowPriceDetailMapper {
    int deleteByPrimaryKey(String schemeDetailId);

    int insert(BokShadowPriceDetail record);

    int insertSelective(BokShadowPriceDetail record);

    int insertBasicByBasicInfo(BokShadowPriceDetail record);

    BokShadowPriceDetail selectByPrimaryKey(String schemeDetailId);

    int updateByPrimaryKeySelective(BokShadowPriceDetail record);

    int updateByPrimaryKey(BokShadowPriceDetail record);
}