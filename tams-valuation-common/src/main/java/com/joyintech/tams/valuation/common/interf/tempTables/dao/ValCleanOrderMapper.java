package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCleanOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface ValCleanOrderMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValCleanOrder record);

    int insertSelective(ValCleanOrder record);

    int insertBasicByBasicInfo(ValCleanOrder record);

    ValCleanOrder selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValCleanOrder record);

    int updateByPrimaryKey(ValCleanOrder record);
}