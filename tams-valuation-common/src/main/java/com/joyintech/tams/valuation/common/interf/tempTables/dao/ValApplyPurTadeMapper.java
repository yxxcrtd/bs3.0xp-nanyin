package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValApplyPurTade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValApplyPurTadeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValApplyPurTade record);

    int insertSelective(ValApplyPurTade record);

    int insertBasicByBasicInfo(ValApplyPurTade record);

    ValApplyPurTade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValApplyPurTade record);

    int updateByPrimaryKey(ValApplyPurTade record);
}