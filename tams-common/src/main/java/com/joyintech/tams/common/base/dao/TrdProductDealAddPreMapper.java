package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdProductDealAddPre;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdProductDealAddPreMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(TrdProductDealAddPre record);

    int insertSelective(TrdProductDealAddPre record);

    int insertBasicByBasicInfo(TrdProductDealAddPre record);

    TrdProductDealAddPre selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(TrdProductDealAddPre record);

    int updateByPrimaryKey(TrdProductDealAddPre record);
}