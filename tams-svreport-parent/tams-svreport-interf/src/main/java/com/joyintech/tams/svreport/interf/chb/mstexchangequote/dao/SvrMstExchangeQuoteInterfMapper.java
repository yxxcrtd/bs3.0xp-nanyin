package com.joyintech.tams.svreport.interf.chb.mstexchangequote.dao;

import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SvrMstExchangeQuoteInterfMapper {

    /**
     * 查询截止上次接入时间的汇率信息数据
     * @param batchTime
     * @return
     */
    List<MstExchargeQuote> select(@Param("updatetime") Date updatetime);

}