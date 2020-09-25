package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstYieldCurveInfoMapper {
    int deleteByPrimaryKey(MstYieldCurveInfoKey key);

    int insert(MstYieldCurveInfo record);

    int insertSelective(MstYieldCurveInfo record);

    int insertBasicByBasicInfo(MstYieldCurveInfo record);

    MstYieldCurveInfo selectByPrimaryKey(MstYieldCurveInfoKey key);

    int updateByPrimaryKeySelective(MstYieldCurveInfo record);

    int updateByPrimaryKey(MstYieldCurveInfo record);
}