package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.base.entity.PrdMdateAdjustKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdMdateAdjustMapper {
    int deleteByPrimaryKey(PrdMdateAdjustKey key);

    int insert(PrdMdateAdjust record);

    int insertSelective(PrdMdateAdjust record);

    int insertBasicByBasicInfo(PrdMdateAdjust record);

    PrdMdateAdjust selectByPrimaryKey(PrdMdateAdjustKey key);

    int updateByPrimaryKeySelective(PrdMdateAdjust record);

    int updateByPrimaryKey(PrdMdateAdjust record);
}