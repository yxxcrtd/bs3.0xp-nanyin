package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetConfigDetailMapper {
    int deleteByPrimaryKey(AssetConfigDetailKey key);

    int insert(AssetConfigDetail record);

    int insertSelective(AssetConfigDetail record);

    int insertBasicByBasicInfo(AssetConfigDetail record);

    AssetConfigDetail selectByPrimaryKey(AssetConfigDetailKey key);

    int updateByPrimaryKeySelective(AssetConfigDetail record);

    int updateByPrimaryKey(AssetConfigDetail record);
}