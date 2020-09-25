package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.BokStatBokPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokStatBokPositionMapper {
    int deleteByPrimaryKey(BokStatBokPositionKey key);

    int insert(BokStatBokPosition record);

    int insertSelective(BokStatBokPosition record);

    int insertBasicByBasicInfo(BokStatBokPosition record);

    BokStatBokPosition selectByPrimaryKey(BokStatBokPositionKey key);

    int updateByPrimaryKeySelective(BokStatBokPosition record);

    int updateByPrimaryKey(BokStatBokPosition record);
}