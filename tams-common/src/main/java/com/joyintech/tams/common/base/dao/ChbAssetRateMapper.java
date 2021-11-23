package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetRate;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetRateMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbAssetRate record);

    int insertSelective(ChbAssetRate record);

    int insertBasicByBasicInfo(ChbAssetRate record);

    ChbAssetRate selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbAssetRate record);

    int updateByPrimaryKey(ChbAssetRate record);
}