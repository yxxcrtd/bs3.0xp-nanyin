package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetClassKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetClassMapper {
    int deleteByPrimaryKey(AssetClassKey key);

    int insert(AssetClass record);

    int insertSelective(AssetClass record);

    int insertBasicByBasicInfo(AssetClass record);

    AssetClass selectByPrimaryKey(AssetClassKey key);

    int updateByPrimaryKeySelective(AssetClass record);

    int updateByPrimaryKey(AssetClass record);
}