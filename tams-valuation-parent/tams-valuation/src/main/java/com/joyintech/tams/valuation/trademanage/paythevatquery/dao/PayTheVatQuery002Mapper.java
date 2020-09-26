/*
 * 文件名：PayTheVatQuery002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.paythevatquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002TableDto;

/**
 * 纳税增值税新建
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Mapper
 * @since 1.0
 */
public interface PayTheVatQuery002Mapper {
    
    /**
     * 查询
     * @param params
     * @return
     */
    List<PayTheVatQuery002TableDto> search(Map<String, Object> params);
    
    /**
     * 校验缴税/退税日期
     */
    List<PayTheVatQuery002Dto> checkdate(Map<String, Object> params);
    
    /**
     * 现金流代码
     * @param booksetId
     * @param acctsubjectType
     * @return
     * @see
     * @since 1.0
     */
    List<PayTheVatQuery002Dto> findCashid(@Param("booksetId") String booksetId,
                                          @Param("acctsubjectType") String acctsubjectType,
                                          @Param("cashType2") String cashType2);
    
    /**
     * 本方资金账户
     * 
     * @param dto
     * @return
     */
    List<PayTheVatQuery002Dto> findacctNo(@Param("booksetId") String booksetId,
                                          @Param("acctType") String acctType);
    /**
     * 账务明细代码
     * @param booksetId
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    String findbokDetailid(@Param("booksetId") String booksetId, @Param("finprodId") String finprodId);

}
