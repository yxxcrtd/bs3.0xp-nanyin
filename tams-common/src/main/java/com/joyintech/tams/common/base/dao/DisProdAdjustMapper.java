package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdAdjust;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdAdjustMapper {
    int deleteByPrimaryKey(String prodAdjustId);

    int insert(DisProdAdjust record);

    int insertSelective(DisProdAdjust record);

    int insertBasicByBasicInfo(DisProdAdjust record);

    DisProdAdjust selectByPrimaryKey(String prodAdjustId);

    int updateByPrimaryKeySelective(DisProdAdjust record);

    int updateByPrimaryKey(DisProdAdjust record);
}