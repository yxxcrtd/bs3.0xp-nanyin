package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFxTargetInfoMapper {
    int insert(ValFxTargetInfo record);

    int insertSelective(ValFxTargetInfo record);

    int insertBasicByBasicInfo(ValFxTargetInfo record);
}