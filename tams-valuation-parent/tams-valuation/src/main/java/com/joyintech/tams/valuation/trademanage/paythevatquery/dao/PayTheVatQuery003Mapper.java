/*
 * 文件名：PayTheVatQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chufengjie
 * 修改时间：2019年02月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery001Dto;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
public interface PayTheVatQuery003Mapper {

    /**
     * 根据条件查询产品 ：TA交易树查询
     * 
     * @param params
     * @return
     */
    List<PayTheVatQuery001Dto> selectList(Map<String, Object> params);

    /**
     * 根据条件查询产品 ：TA交易树查询
     * 
     * @param params
     * @return
     */
    List<PayTheVatQuery001Dto> findFinproduct(@Param("booksetId") String booksetId,
                                              @Param("seitleDate") String seitleDate);

    /**
     * 根据条件查询产品 ：TA交易树查询
     * 
     * @param params
     * @return
     */
    List<PayTheVatQuery001Dto> findPtradeid(@Param("tradeId") String tradeId);

    List<PayTheVatQuery001Dto> queryBooksetExt(String tradeId);
    /**
     * 现金流代码
     * @param booksetId 账套代码
     * @param acctsubjectType 核算主体类型
     * @param cashType2 现金流类型2
     * @return 现金流代码
     * @see
     * @since 1.0
     */
    List<PayTheVatQuery001Dto> findCashid(@Param("booksetId") String booksetId,
                                          @Param("acctsubjectType") String acctsubjectType,
                                          @Param("cashType2") String cashType2);

    String findbokDetailid(@Param("booksetId") String booksetId, @Param("cashId") String cashId);

    /**
     * 本方资金账户
     * 
     * @param booksetId 账套代码
     * @param acctType 资金账户类型
     * @return
     */
    List<PayTheVatQuery001Dto> findacctNo(@Param("booksetId") String booksetId,
                                          @Param("acctType") String acctType);

    /**
     * 入金融产品交易扩展表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertProdealext(PayTheVatQuery001Dto dto);

    /**
     * 入金融产品交易表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertProdeal(PayTheVatQuery001Dto dto);

    /**
     * 入交易费用明细表表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertTrdfeedetail(PayTheVatQuery001Dto dto);

    /**
     * 入入账信息表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertDealaccount(PayTheVatQuery001Dto dto);

    /**
     * 入账务明细表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertDealaccountdetail(PayTheVatQuery001Dto dto);

    /**
     * 入结算指令扩展表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertStlordersext(PayTheVatQuery001Dto dto);

    /**
     * 入结算指令表 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     */
    int insertStlorders(PayTheVatQuery001Dto dto);
}
