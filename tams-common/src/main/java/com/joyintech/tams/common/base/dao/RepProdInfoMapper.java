package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import org.springframework.stereotype.Repository;

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