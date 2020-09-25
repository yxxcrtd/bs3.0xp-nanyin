package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetOther;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetOtherMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetOther record);

    int insertSelective(RepAssetOther record);

    int insertBasicByBasicInfo(RepAssetOther record);

    RepAssetOther selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetOther record);

    int updateByPrimaryKey(RepAssetOther record);
}