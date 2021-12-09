package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlCashRecon;
import com.joyintech.tams.common.base.entity.StlCashReconKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlCashReconMapper {
    int deleteByPrimaryKey(StlCashReconKey key);

    int insert(StlCashRecon record);

    int insertSelective(StlCashRecon record);

    int insertBasicByBasicInfo(StlCashRecon record);

    StlCashRecon selectByPrimaryKey(StlCashReconKey key);

    int updateByPrimaryKeySelective(StlCashRecon record);

    int updateByPrimaryKey(StlCashRecon record);
}