package com.joyintech.tams.common.base.dao;


import com.joyintech.tams.common.base.entity.RepProdIndexKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIndexKeyMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(RepProdIndexKey record);

    int insertSelective(RepProdIndexKey record);

    int insertBasicByBasicInfo(RepProdIndexKey record);

    RepProdIndexKey selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(RepProdIndexKey record);

    int updateByPrimaryKey(RepProdIndexKey record);
}
