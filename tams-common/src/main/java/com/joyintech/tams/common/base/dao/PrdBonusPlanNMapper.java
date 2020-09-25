package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdBonusPlanN;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdBonusPlanNMapper {
    int deleteByPrimaryKey(String bonusPlanId);

    int insert(PrdBonusPlanN record);

    int insertSelective(PrdBonusPlanN record);

    int insertBasicByBasicInfo(PrdBonusPlanN record);

    PrdBonusPlanN selectByPrimaryKey(String bonusPlanId);

    int updateByPrimaryKeySelective(PrdBonusPlanN record);

    int updateByPrimaryKey(PrdBonusPlanN record);
}