package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTarget;

@Repository
public interface ValDlTargetMapper {
    int deleteByPrimaryKey(String dlId);

    int insert(ValDlTarget record);

    int insertSelective(ValDlTarget record);

    int insertBasicByBasicInfo(ValDlTarget record);

    ValDlTarget selectByPrimaryKey(String dlId);

    int updateByPrimaryKeySelective(ValDlTarget record);

    int updateByPrimaryKey(ValDlTarget record);
}