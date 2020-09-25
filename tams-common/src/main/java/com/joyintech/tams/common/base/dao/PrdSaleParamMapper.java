package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdSaleParam;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdSaleParamMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PrdSaleParam record);

    int insertSelective(PrdSaleParam record);

    int insertBasicByBasicInfo(PrdSaleParam record);

    PrdSaleParam selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PrdSaleParam record);

    int updateByPrimaryKey(PrdSaleParam record);
}