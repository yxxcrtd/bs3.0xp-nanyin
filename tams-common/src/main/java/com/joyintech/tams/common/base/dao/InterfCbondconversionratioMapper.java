package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbondconversionratio;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbondconversionratioMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbondconversionratio record);

    int insertSelective(InterfCbondconversionratio record);

    int insertBasicByBasicInfo(InterfCbondconversionratio record);

    InterfCbondconversionratio selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbondconversionratio record);

    int updateByPrimaryKey(InterfCbondconversionratio record);
}