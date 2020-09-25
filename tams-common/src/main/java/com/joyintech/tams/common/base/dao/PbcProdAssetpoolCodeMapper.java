package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcProdAssetpoolCode;
import com.joyintech.tams.common.base.entity.PbcProdAssetpoolCodeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcProdAssetpoolCodeMapper {
    int deleteByPrimaryKey(PbcProdAssetpoolCodeKey key);

    int insert(PbcProdAssetpoolCode record);

    int insertSelective(PbcProdAssetpoolCode record);

    int insertBasicByBasicInfo(PbcProdAssetpoolCode record);

    PbcProdAssetpoolCode selectByPrimaryKey(PbcProdAssetpoolCodeKey key);

    int updateByPrimaryKeySelective(PbcProdAssetpoolCode record);

    int updateByPrimaryKey(PbcProdAssetpoolCode record);
}