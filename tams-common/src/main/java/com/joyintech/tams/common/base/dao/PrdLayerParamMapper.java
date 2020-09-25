package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdLayerParam;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdLayerParamMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PrdLayerParam record);

    int insertSelective(PrdLayerParam record);

    int insertBasicByBasicInfo(PrdLayerParam record);

    PrdLayerParam selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PrdLayerParam record);

    int updateByPrimaryKey(PrdLayerParam record);
}