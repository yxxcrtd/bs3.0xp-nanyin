package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetConfigMonDetailMapper {
    int deleteByPrimaryKey(AssetConfigMonDetailKey key);

    int insert(AssetConfigMonDetail record);

    int insertSelective(AssetConfigMonDetail record);

    int insertBasicByBasicInfo(AssetConfigMonDetail record);

    AssetConfigMonDetail selectByPrimaryKey(AssetConfigMonDetailKey key);

    int updateByPrimaryKeySelective(AssetConfigMonDetail record);

    int updateByPrimaryKey(AssetConfigMonDetail record);
}