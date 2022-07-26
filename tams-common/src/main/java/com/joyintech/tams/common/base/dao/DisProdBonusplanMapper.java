package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdBonusplanMapper {
    int deleteByPrimaryKey(String bonusPlanId);

    int insert(DisProdBonusplan record);

    int insertSelective(DisProdBonusplan record);

    int insertBasicByBasicInfo(DisProdBonusplan record);

    DisProdBonusplan selectByPrimaryKey(String bonusPlanId);

    int updateByPrimaryKeySelective(DisProdBonusplan record);

    int updateByPrimaryKey(DisProdBonusplan record);
}