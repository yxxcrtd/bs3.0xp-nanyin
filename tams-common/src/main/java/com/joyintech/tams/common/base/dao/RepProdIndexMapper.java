package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdIndex;
import com.joyintech.tams.common.base.entity.RepProdIndexKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIndexMapper {
    int deleteByPrimaryKey(RepProdIndexKey key);

    int insert(RepProdIndex record);

    int insertSelective(RepProdIndex record);

    int insertBasicByBasicInfo(RepProdIndex record);

    RepProdIndex selectByPrimaryKey(RepProdIndexKey key);

    int updateByPrimaryKeySelective(RepProdIndex record);

    int updateByPrimaryKey(RepProdIndex record);
}