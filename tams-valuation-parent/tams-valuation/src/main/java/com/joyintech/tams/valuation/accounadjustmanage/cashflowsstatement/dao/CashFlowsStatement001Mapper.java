/*
 * 文件名：CashFlowsStatement001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsDto;

/**
 * 现金流量表查询Mapper
 * 
 * @author QuJianZhong
 * @version 1.0 2018年10月22日
 * @see CashFlowsStatement001Mapper
 * @since 1.0
 */
@Repository
public interface CashFlowsStatement001Mapper {

    /**
     * 根据条件查询现金流量表相关交易信息
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<CashFlowsDto> selectCashFlowsStatement(Map<String, Object> pageParam);

    /**
     * 根据条件查询现金流量表相关交易信息
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<CashFlowsDto> selectCashFlowsStatementp(Map<String, Object> pageParam);

    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param);

    /**
     * 【加：年初现金及现金等价物余额】及【年末现金及现金等价物余额】统计
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectCashAmt(Map<String, Object> queryMap);

    /**
     * 【加：年初现金及现金等价物余额】及【年末现金及现金等价物余额】统计--账套组
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectCashAmtp(Map<String, Object> queryMap);

    /**
     * 手工凭证处理
     * 
     * @param queryMap
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectSubjectAmount(Map<String, Object> queryMap);
}
