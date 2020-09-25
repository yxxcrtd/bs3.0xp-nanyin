package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIndicatorParaCalcdetailMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskIndicatorParaCalcdetail record);

    int insertSelective(RskIndicatorParaCalcdetail record);

    int insertBasicByBasicInfo(RskIndicatorParaCalcdetail record);

    RskIndicatorParaCalcdetail selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskIndicatorParaCalcdetail record);

    int updateByPrimaryKey(RskIndicatorParaCalcdetail record);
}