package com.joyintech.tams.common.base.dao;


import com.joyintech.tams.common.base.entity.RepProdIndexKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIndexKeyMapper {
    int deleteByPrimaryKey(RepProdIndexKey record);

    int insert(RepProdIndexKey record);

    int insertSelective(RepProdIndexKey record);

    int insertBasicByBasicInfo(RepProdIndexKey record);

    RepProdIndexKey selectByPrimaryKey(RepProdIndexKey record);

    int updateByPrimaryKeySelective(RepProdIndexKey record);

    int updateByPrimaryKey(RepProdIndexKey record);
}
