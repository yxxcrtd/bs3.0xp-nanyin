package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepProdInfo;

@Repository
public interface RepProdInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(RepProdInfo record);

    int insertSelective(RepProdInfo record);

    int insertBasicByBasicInfo(RepProdInfo record);

    RepProdInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(RepProdInfo record);

    int updateByPrimaryKey(RepProdInfo record);
}