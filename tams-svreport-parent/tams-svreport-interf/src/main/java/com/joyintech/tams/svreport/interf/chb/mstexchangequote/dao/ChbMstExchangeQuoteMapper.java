package com.joyintech.tams.svreport.interf.chb.mstexchangequote.dao;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbMstExchangeQuoteMapper {

    /**
     * 汇率信息 （合并数据）入库
     * @param subList
     * @return
     */
    int mergeInfoList(List<MstExchargeQuote> subList);
}
