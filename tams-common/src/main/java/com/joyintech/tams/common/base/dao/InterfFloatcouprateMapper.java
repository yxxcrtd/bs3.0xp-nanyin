package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfFloatcouprate;
import com.joyintech.tams.common.base.entity.InterfFloatcouprateKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfFloatcouprateMapper {
    int deleteByPrimaryKey(InterfFloatcouprateKey key);

    int insert(InterfFloatcouprate record);

    int insertSelective(InterfFloatcouprate record);

    int insertBasicByBasicInfo(InterfFloatcouprate record);

    InterfFloatcouprate selectByPrimaryKey(InterfFloatcouprateKey key);

    int updateByPrimaryKeySelective(InterfFloatcouprate record);

    int updateByPrimaryKey(InterfFloatcouprate record);
}