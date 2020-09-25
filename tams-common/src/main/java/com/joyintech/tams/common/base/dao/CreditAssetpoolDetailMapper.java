package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditAssetpoolDetail;
import com.joyintech.tams.common.base.entity.CreditAssetpoolDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAssetpoolDetailMapper {
    int deleteByPrimaryKey(CreditAssetpoolDetailKey key);

    int insert(CreditAssetpoolDetail record);

    int insertSelective(CreditAssetpoolDetail record);

    int insertBasicByBasicInfo(CreditAssetpoolDetail record);

    CreditAssetpoolDetail selectByPrimaryKey(CreditAssetpoolDetailKey key);

    int updateByPrimaryKeySelective(CreditAssetpoolDetail record);

    int updateByPrimaryKey(CreditAssetpoolDetail record);
}