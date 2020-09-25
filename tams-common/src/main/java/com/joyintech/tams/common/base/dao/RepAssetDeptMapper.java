package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetDept;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetDeptMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetDept record);

    int insertSelective(RepAssetDept record);

    int insertBasicByBasicInfo(RepAssetDept record);

    RepAssetDept selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetDept record);

    int updateByPrimaryKey(RepAssetDept record);
}