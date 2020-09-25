package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtlKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblAssetSubMapDtlMapper {
    int deleteByPrimaryKey(RskValtblAssetSubMapDtlKey key);

    int insert(RskValtblAssetSubMapDtl record);

    int insertSelective(RskValtblAssetSubMapDtl record);

    int insertBasicByBasicInfo(RskValtblAssetSubMapDtl record);

    RskValtblAssetSubMapDtl selectByPrimaryKey(RskValtblAssetSubMapDtlKey key);

    int updateByPrimaryKeySelective(RskValtblAssetSubMapDtl record);

    int updateByPrimaryKey(RskValtblAssetSubMapDtl record);
}