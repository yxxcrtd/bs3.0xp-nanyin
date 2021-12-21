package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSalerStlDealInfo;
import com.joyintech.tams.common.base.entity.ChbSalerStlDealInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSalerStlDealInfoMapper {
    int deleteByPrimaryKey(ChbSalerStlDealInfoKey key);

    int insert(ChbSalerStlDealInfo record);

    int insertSelective(ChbSalerStlDealInfo record);

    int insertBasicByBasicInfo(ChbSalerStlDealInfo record);

    ChbSalerStlDealInfo selectByPrimaryKey(ChbSalerStlDealInfoKey key);

    int updateByPrimaryKeySelective(ChbSalerStlDealInfo record);

    int updateByPrimaryKey(ChbSalerStlDealInfo record);
}