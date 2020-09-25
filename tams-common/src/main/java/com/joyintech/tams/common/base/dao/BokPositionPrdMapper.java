package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.BokPositionPrdKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokPositionPrdMapper {
    int deleteByPrimaryKey(BokPositionPrdKey key);

    int insert(BokPositionPrd record);

    int insertSelective(BokPositionPrd record);

    int insertBasicByBasicInfo(BokPositionPrd record);

    BokPositionPrd selectByPrimaryKey(BokPositionPrdKey key);

    int updateByPrimaryKeySelective(BokPositionPrd record);

    int updateByPrimaryKey(BokPositionPrd record);
}