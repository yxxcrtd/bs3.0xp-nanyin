package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetTypeMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetTypeMappingMapper {
    int deleteByPrimaryKey(String typeuuid);

    int insert(ChbAssetTypeMapping record);

    int insertSelective(ChbAssetTypeMapping record);

    int insertBasicByBasicInfo(ChbAssetTypeMapping record);

    ChbAssetTypeMapping selectByPrimaryKey(String typeuuid);

    int updateByPrimaryKeySelective(ChbAssetTypeMapping record);

    int updateByPrimaryKey(ChbAssetTypeMapping record);
}