package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TargetTypeInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetTypeInfoMapper {
    int deleteByPrimaryKey(String taregetTypeCode);

    int insert(TargetTypeInfo record);

    int insertSelective(TargetTypeInfo record);

    int insertBasicByBasicInfo(TargetTypeInfo record);

    TargetTypeInfo selectByPrimaryKey(String taregetTypeCode);

    int updateByPrimaryKeySelective(TargetTypeInfo record);

    int updateByPrimaryKey(TargetTypeInfo record);
}