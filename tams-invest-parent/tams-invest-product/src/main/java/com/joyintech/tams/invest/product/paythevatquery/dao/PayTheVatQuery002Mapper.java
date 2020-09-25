/*
 * 文件名：PayTheVatQuery002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.product.paythevatquery.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002Dto;


/**
 * 纳税增值税新建
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Mapper
 * @since 1.0
 */
@Repository("finproductPayTheVatQuery002Mapper")
public interface PayTheVatQuery002Mapper {
    
    /**
     * 查询产品增值税
     * @param params
     * @return
     */
    List<PayTheVatQuery002Dto> searchProductVat(Map<String, Object> params);
    
    /**
     * 查询通道增值税
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<PayTheVatQuery002Dto> searchChannelVat(Map<String, Object> params);
    
    /**
     * 校验缴税/退税日期
     */
    List<PayTheVatQuery002Dto> checkdate(Map<String, Object> params);
    
    /**
     * 
     * 删除行数据
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    int  deleteByBooksetId(String booksetId);
    
    /**
     * 现金流代码
     * @param booksetId
     * @param acctsubjectType
     * @return
     * @see
     * @since 1.0
     */
    List<PayTheVatQuery002Dto> findCashid(@Param("booksetId") String booksetId,
                                          @Param("acctsubjectType") String acctsubjectType);
    
    /**
     * 本方资金账户
     * 
     * @param dto
     * @return
     */
    List<PayTheVatQuery002Dto> findacctNo(@Param("booksetId") String booksetId);
    /**
     * 账务明细代码
     * @param booksetId
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    String findbokDetailid(@Param("booksetId") String booksetId, @Param("finprodId") String finprodId);
    
    /**
     * 查询最近一次交税记录
     * @param params
     * @return
     */
    List<PayTheVatQuery002Dto> getLastPayTheVatInfoList(Map<String, Object> params);
    
    /**
     * 获取产品的已跑批日期自然日
     * @param params
     * @return
     */
    List<PayTheVatQuery002Dto> getLastcdate(Map<String, Object> params);
    
    /**
     * 
     * 获取通道下的组合
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectPortfolioIdByChannel(Map<String, Object> param);
    
    /**
     * 
     * 获取通道最新缴纳日期查询
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    Date getLastChannelSettleDate(Map<String, Object> params);
    
    
    /**
     * 查询币种
     */
    List<String> searchCurrency(Map<String, Object> pageParam);
}
