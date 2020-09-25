/*
 * 文件名：FinProductFeeInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.common.service.FinProductFeeInfoService;

/**
 * 费用信息业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月9日
 * @see FinProductFeeInfoController
 * @since 1.0
 */
@RestController("backFinProductFeeInfoController")
@RequestMapping("/FinProductFeeInfo")
public class FinProductFeeInfoController {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinProductFeeInfoService finProductFeeInfoService;

    /**
     * 查询费用信息
     * 
     * @param map 参数
     * @return 费用信息下拉框
     */
    @PostMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList(@RequestBody Map<String, Object> map) {
        return finProductFeeInfoService.queryFeeInfoList(map);
    }

    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @return 费用基本信息
     */
    @GetMapping("queryMstFeeinfoByFeeId")
    public MstFeeinfo queryMstFeeinfoByFeeId(String feeId) {
        return finProductFeeInfoService.queryMstFeeinfoByFeeId(feeId);
    }

    /**
     * 根据金融产品代码查询费用列表信息
     * 
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     */
    @GetMapping("queryFeeInfoByFinprodId")
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(String finprodId,String finprodType2) {
        return finProductFeeInfoService.queryFeeInfoByFinprodId(finprodId,finprodType2);
    }
    
    /**
     * 根据金融产品代码查询费用列表信息包含浮动管理费
     * 
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     */
    @GetMapping("queryFeeInfoByFinprodIdEx")
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodIdEx(String finprodId) {
        return finProductFeeInfoService.queryFeeInfoByFinprodIdEx(finprodId);
    }

    /**
     * 根据参数查询付费计划
     * 
     * @param map 金融产品代码
     * @return 付费计划信息
     */
    @PostMapping("queryFeePlanListByFinprodId")
    public List<FinCashPlan> queryFeePlanListByFinprodId(@RequestBody Map<String, Object> map) {
        return finProductFeeInfoService.queryFeePlanListByFinprodId(map);
    }
    
    /**
     * 根据组合代码查询费用列表信息
     * 
     * @param map 组合代码
     * @return 费用列表信息
     */
    @PostMapping("queryFeeInfoByPtlId")
    public List<BussFinProductFeeInfoDto> queryFeeInfoByPtlId(@RequestBody Map<String, Object> map) {
        return finProductFeeInfoService.queryFeeInfoByPtlId(map);
    }
    
    /**
     * 查询资金账户费用信息
     * 
     * @param map 参数
     * @return 费用信息下拉框
     */
    @PostMapping("queryFundFeeInfoList")
    public List<SelectOptionDto> queryFundFeeInfoList(@RequestBody Map<String, Object> map) {
        return finProductFeeInfoService.queryFundFeeInfoList(map);
    }
    
    /**
     * 获取资金账号信息
     * @param acctNo 账号
     * @return 资金账号信息
     * @see
     * @since 1.0
     */
    @GetMapping("getCctInfo")
    public StlCapacctInfo getCctInfo(String acctNo){
        return finProductFeeInfoService.getCctInfo(acctNo);
    }
    
    @PostMapping("queryMstFeeinfoForRateAdjByFeeId")
    public BussFinProductFeeInfoDto queryMstFeeinfoForRateAdjByFeeId(@RequestBody Map<String, Object> map){
        return finProductFeeInfoService.queryMstFeeinfoForRateAdjByFeeId(map);
    }
    
}
