package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetTypeMappingEx;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetTypeMappingExMapper {
    int deleteByPrimaryKey(String typeuuid);

    int insert(ChbAssetTypeMappingEx record);

    int insertSelective(ChbAssetTypeMappingEx record);

    int insertBasicByBasicInfo(ChbAssetTypeMappingEx record);

    ChbAssetTypeMappingEx selectByPrimaryKey(String typeuuid);

    int updateByPrimaryKeySelective(ChbAssetTypeMappingEx record);

    int updateByPrimaryKey(ChbAssetTypeMappingEx record);
}