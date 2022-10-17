package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisBonusList;
import org.springframework.stereotype.Repository;

@Repository
public interface DisBonusListMapper {
    int deleteByPrimaryKey(String bonusListId);

    int insert(DisBonusList record);

    int insertSelective(DisBonusList record);

    int insertBasicByBasicInfo(DisBonusList record);

    DisBonusList selectByPrimaryKey(String bonusListId);

    int updateByPrimaryKeySelective(DisBonusList record);

    int updateByPrimaryKey(DisBonusList record);
}