package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.OpicsParam;
import com.joyintech.tams.common.base.entity.OpicsParamKey;
import org.springframework.stereotype.Repository;

@Repository
public interface OpicsParamMapper {
    int deleteByPrimaryKey(OpicsParamKey key);

    int insert(OpicsParam record);

    int insertSelective(OpicsParam record);

    int insertBasicByBasicInfo(OpicsParam record);

    OpicsParam selectByPrimaryKey(OpicsParamKey key);

    int updateByPrimaryKeySelective(OpicsParam record);

    int updateByPrimaryKey(OpicsParam record);
}