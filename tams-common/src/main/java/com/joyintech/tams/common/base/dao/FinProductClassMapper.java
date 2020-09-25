package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductClass;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductClassMapper {
    int deleteByPrimaryKey(String optsn);

    int insert(FinProductClass record);

    int insertSelective(FinProductClass record);

    int insertBasicByBasicInfo(FinProductClass record);

    FinProductClass selectByPrimaryKey(String optsn);

    int updateByPrimaryKeySelective(FinProductClass record);

    int updateByPrimaryKey(FinProductClass record);
}