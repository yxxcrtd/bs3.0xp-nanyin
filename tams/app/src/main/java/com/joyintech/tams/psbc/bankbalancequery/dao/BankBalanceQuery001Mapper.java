/*
 * 文件名：BankBalanceQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：wanggengyun
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.psbc.bankbalancequery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.dao.ValFinprodTradeQuery001Mapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.psbc.bankbalancequery.dto.BankBalanceQuery001Dto;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Mapper
 * @since 1.0
 */
public interface BankBalanceQuery001Mapper {
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<BankBalanceQuery001Dto> queryPageList(Map<String, Object> params);
    
    /**
     * 分页查询合并展示
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<BankBalanceQuery001Dto> queryPageListTotal(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryValBokSubject(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<BokBookset> queryBookSet();
    
    /**
     * 
     * 所属账套下拉框初始化
     * @return List<ValAssetbook001Dto>  
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId();
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);
    
    
    /**
     * 
     * 科目流水查询
     * @param vouchNum 凭证编号
     * @param bookDate 入账日期
     * @return 科目流水List
     * @see
     * @since 1.0
     */
    List<ValSubjectWaterQuery001Dto> getSubjectWater(ValSubjectWaterQuery001SearchDto dto);
}
