package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetAstBill;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetAstBillMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetAstBill record);

    int insertSelective(RepAssetAstBill record);

    int insertBasicByBasicInfo(RepAssetAstBill record);

    RepAssetAstBill selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetAstBill record);

    int updateByPrimaryKey(RepAssetAstBill record);
}