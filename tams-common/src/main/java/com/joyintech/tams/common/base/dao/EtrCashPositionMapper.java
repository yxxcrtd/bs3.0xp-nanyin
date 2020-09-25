package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrCashPosition;
import com.joyintech.tams.common.base.entity.EtrCashPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrCashPositionMapper {
    int deleteByPrimaryKey(EtrCashPositionKey key);

    int insert(EtrCashPosition record);

    int insertSelective(EtrCashPosition record);

    int insertBasicByBasicInfo(EtrCashPosition record);

    EtrCashPosition selectByPrimaryKey(EtrCashPositionKey key);

    int updateByPrimaryKeySelective(EtrCashPosition record);

    int updateByPrimaryKey(EtrCashPosition record);
}