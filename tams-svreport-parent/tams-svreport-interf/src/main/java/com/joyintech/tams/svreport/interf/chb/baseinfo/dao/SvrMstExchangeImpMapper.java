package com.joyintech.tams.svreport.interf.chb.baseinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.base.entity.MstExchargeQuoteKey;

public interface SvrMstExchangeImpMapper {
    int deleteByPrimaryKey(MstExchargeQuoteKey key);

    int insert(MstExchargeQuote record);

    int insertSelective(MstExchargeQuote record);

    MstExchargeQuote selectByPrimaryKey(MstExchargeQuoteKey key);

    int updateByPrimaryKeySelective(MstExchargeQuote record);

    int updateByPrimaryKey(MstExchargeQuote record);
    
    int deleteExchangeBatch(@Param("exchangeInfoList")List<MstExchargeQuote> list);
    
    int mergeExchangeData(@Param("list")List<MstExchargeQuote> list);

	void deleteAllData();
	
	int deleteTodayData();
}