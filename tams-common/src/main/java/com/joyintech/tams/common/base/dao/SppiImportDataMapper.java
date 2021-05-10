package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SppiImportData;
import org.springframework.stereotype.Repository;

@Repository
public interface SppiImportDataMapper {
    int deleteByPrimaryKey(String bondId);

    int insert(SppiImportData record);

    int insertSelective(SppiImportData record);

    int insertBasicByBasicInfo(SppiImportData record);

    SppiImportData selectByPrimaryKey(String bondId);

    int updateByPrimaryKeySelective(SppiImportData record);

    int updateByPrimaryKey(SppiImportData record);
}