/*
 * 文件名：ValManualVoucher003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.BokDetailIdDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003AddDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003SubjectDto;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.SysBatchTask;

/**
 * 手工凭证新建Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年7月11日
 * @see ValManualVoucher003Mapper
 * @since 1.0
 */
@Repository(value="serviceValManualVoucher003Mapper")
public interface ValManualVoucher003Mapper {

    ValManualVoucher001Dto getBySeqno(@Param("seqno") String seqno);

    /**
     * 获取科目号
     * 
     * @return
     * @see
     * @since 1.0
     */
    ValManualVoucher003SubjectDto getSubjectNo(@Param("booksetId") String booksetId,
                                               @Param("subjectNo") String subjectNo);

    /**
     * 获取科目号4下拉框
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSubjectNoPullDw(@Param("booksetId") String booksetId);

    // /**
    // *
    // * 获取资产代码下拉列表
    // * @return
    // * @see
    // * @since 1.0
    // */
    // List<SuggestDto> getBusiId(@Param("finprodType") String finprodType, @Param("booksetId")
    // String booksetId);

    /**
     * 获取标的类金融产品资产代码下拉列表
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProduct(@Param("finprodType") String finprodType,
                                   @Param("booksetId") String booksetId);

    /**
     * 获取交易类金融产品资产代码下拉列表
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinTradeProduct(@Param("finprodType") String finprodType,
                                        @Param("booksetId") String booksetId);

    /**
     * 获取费用类金融产品资产代码下拉列表
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinCashInfo(@Param("finprodType") String finprodType,
                                    @Param("booksetId") String booksetId);

    /**
     * 获取现金类金融产品资产代码下拉列表
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetCapacctInfo(@Param("finprodType") String finprodType,
                                           @Param("booksetId") String booksetId);

    /**
     * 获取列表信息
     * 
     * @param bookId 凭证编号
     * @return 列表信息
     * @throws Exception 异常
     */
    List<ValManualVoucher003AddDto> searchList(String bookId);

    /**
     * 基础科目性质查询
     *
     * @param groupId 分组代码
     * @return 科目性质列表
     * @see
     * @since 1.0
     */
    List<String> getValSubjectNatureList(@Param("groupId") String groupId);

    /**
     * 取得业务明细类型
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    SuggestDto getFinprodType(@Param("finprodId") String finprodId,
                              @Param("booksetId") String booksetId,
                              @Param("branch") Long branch);

    /**
     * 获取资金账户下拉框
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAcctId(@Param("booksetId") String booksetId,
                                    @Param("param") String param);

    /**
     * 获取分支序号下拉框
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBranch(@Param("finprodType") String finprodType,
                                    @Param("branchType") String branchType,
                                    @Param("finprodId") String finprodId);

    /**
     * 查询批处理任务信息
     *
     * @param beanName
     * @see
     * @since 1.0
     */
    SysBatchTask getTaskInfo(@Param("beanName") String beanName);

    /**
     * 删除手工交易凭证明细
     * 
     * @param tradeId 交易编号
     * @return 影响数量
     * @see
     * @since 1.0
     */
    int deleteTrdVouchDealDetail(@Param("tradeId") String tradeId,
                                 @Param("booksetId") String booksetId);

    /**
     * 删除账务凭证历史
     * 
     * @param tradeId 交易编号
     * @param booksetId 账套代码
     * @return 影响数量
     * @see
     * @since 1.0
     */
    int deleteBokBookdetailAll(@Param("tradeId") String tradeId,
                               @Param("booksetId") String booksetId);

    /**
     * 删除凭证子表
     * 
     * @param tradeId 交易编号
     * @param vouchNum 凭证编号
     * @return 影响数量
     * @see
     * @since 1.0
     */
    int deleteBokVouchDetail(@Param("tradeId") String tradeId, @Param("vouchNum") String vouchNum);

    /**
     * 删除账务明细关联表
     * 
     * @param vouchNum 凭证编号
     * @return 影响数量
     * @see
     * @since 1.0
     */
    int deleteBokVouchRel(@Param("vouchNum") String vouchNum);

    /**
     * 根据账套代码获取对应的核算主体信息
     * 
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcctSubjectInfoByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 根据账套代码获取核算标的信息
     * 
     * @param booksetId 账套代码
     * @param listFinprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcctLayeringInfo(@Param("booksetId") String booksetId,
                                         @Param("listFinprodType") List<String> listFinprodType);   
    
    /**moidfy by dongjun on 20181211 start ***/
    /**
     * 
     * 根据科目号和币种查询资金账户
     * 
     * @param booksetId
     * @param acctTypeA01
     * @param ccy
     * @return
     * @see
     * @since 1.0
     */
    String getAcctNo(@Param("booksetId") String booksetId,
                     @Param("acctType") String acctType,
                     @Param("ccy") String ccy);
    /**moidfy by dongjun on 20181211 end ***/

    /**modify by dongjun on 20181214 start **/
    /**
     * 
     * 科目号查询
     * 
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> querySubjectNo(Map<String, Object> para);
    /**modify by dongjun on 20181214 end **/
    
    /**
     * 买断式回购质押品查询
     * @param finprodType 金融产品类型
     * @param finprodId 金融产品代码
     * @return 买断式回购质押金融产品
     * @see
     * @since 1.0
     */
    List<FinTradeProduct> getfinDealPledge(@Param("finprodType") String finprodType,
                                        @Param("finprodId") String finprodId);
    
    /**
     * 投资目的查询
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    BokDetailIdDto getInvAim(Map<String, Object> params);
}
