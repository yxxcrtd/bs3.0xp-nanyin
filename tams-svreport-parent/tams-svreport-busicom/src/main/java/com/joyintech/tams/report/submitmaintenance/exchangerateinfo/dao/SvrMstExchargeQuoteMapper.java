package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.base.entity.MstExchargeQuoteKey;

@Repository
public interface SvrMstExchargeQuoteMapper {
    int deleteByPrimaryKey(MstExchargeQuoteKey key);

    int insert(MstExchargeQuote record);

    int insertSelective(MstExchargeQuote record);

    int insertBasicByBasicInfo(MstExchargeQuote record);

    MstExchargeQuote selectByPrimaryKey(MstExchargeQuoteKey key);

    int updateByPrimaryKeySelective(MstExchargeQuote record);

    int updateByPrimaryKey(MstExchargeQuote record);
}