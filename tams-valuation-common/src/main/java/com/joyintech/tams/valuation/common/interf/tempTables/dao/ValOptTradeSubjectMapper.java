package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValOptTradeSubject;
import org.springframework.stereotype.Repository;

@Repository
public interface ValOptTradeSubjectMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(ValOptTradeSubject record);

    int insertSelective(ValOptTradeSubject record);

    int insertBasicByBasicInfo(ValOptTradeSubject record);

    ValOptTradeSubject selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(ValOptTradeSubject record);

    int updateByPrimaryKey(ValOptTradeSubject record);
}