package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaFinProductAssetAttach;
import org.springframework.stereotype.Repository;

@Repository
public interface PaFinProductAssetAttachMapper {
    int deleteByPrimaryKey(String assetAttachNo);

    int insert(PaFinProductAssetAttach record);

    int insertSelective(PaFinProductAssetAttach record);

    int insertBasicByBasicInfo(PaFinProductAssetAttach record);

    PaFinProductAssetAttach selectByPrimaryKey(String assetAttachNo);

    int updateByPrimaryKeySelective(PaFinProductAssetAttach record);

    int updateByPrimaryKey(PaFinProductAssetAttach record);
}