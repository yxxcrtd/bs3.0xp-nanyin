package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisDataNobtm;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetsBasisDataNobtmMapper {
    int deleteByPrimaryKey(String valId);

    int insert(ChbAssetsBasisDataNobtm record);

    int insertSelective(ChbAssetsBasisDataNobtm record);

    int insertBasicByBasicInfo(ChbAssetsBasisDataNobtm record);

    ChbAssetsBasisDataNobtm selectByPrimaryKey(String valId);

    int updateByPrimaryKeySelective(ChbAssetsBasisDataNobtm record);

    int updateByPrimaryKey(ChbAssetsBasisDataNobtm record);
}