package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcCapAssetInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcCapAssetInfoMapper {
    int deleteByPrimaryKey(String assetUuid);

    int insert(PbcCapAssetInfo record);

    int insertSelective(PbcCapAssetInfo record);

    int insertBasicByBasicInfo(PbcCapAssetInfo record);

    PbcCapAssetInfo selectByPrimaryKey(String assetUuid);

    int updateByPrimaryKeySelective(PbcCapAssetInfo record);

    int updateByPrimaryKey(PbcCapAssetInfo record);
}