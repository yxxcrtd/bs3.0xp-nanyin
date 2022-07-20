package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdAsset;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdAssetMapper {
    int deleteByPrimaryKey(String prodAssetId);

    int insert(DisProdAsset record);

    int insertSelective(DisProdAsset record);

    int insertBasicByBasicInfo(DisProdAsset record);

    DisProdAsset selectByPrimaryKey(String prodAssetId);

    int updateByPrimaryKeySelective(DisProdAsset record);

    int updateByPrimaryKey(DisProdAsset record);
}