/*
 * 文件名：PtlActivityPeriod001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.common.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;

/**
 * 投资委托基础组件调用
 * 
 * @author 强成西
 * @version 1.0 2018年9月17日
 * @see EntrustCommonBaseController
 * @since 1.0
 */
@RestController("entrustcommonbaseCtr_dtx")
@RequestMapping("/common/entrustcommonbase")
public class EntrustCommonBaseController {

    /**
     * 投资委托 基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoServiceImpl;

    /**
     * 投资委托组件.完成委托
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("finishEntrust")
    public boolean finishEntrust(@RequestBody List<EtrFinishStatusDto> etrFinishStatusList) {
        return baseEtrInvestInfoServiceImpl.completeDelegate(etrFinishStatusList);
    }

    /**
     * 投资委托组件.取消委托
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("cancelEntrust")
    public boolean cancelEntrust(@RequestBody List<EtrFinishStatusDto> etrFinishStatusList) {
        try {
            if (etrFinishStatusList != null) {
                etrFinishStatusList.forEach(status -> {
                    baseEtrInvestInfoServiceImpl.cancelCompleteDelegate(status.getEntrustId(),status.getTradeId());
                });
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据组合代码和金融产品代码，查询债券的投资目的
     *
     * @return 查询结果集
     */
    @GetMapping("findInvAim/{portfolioId}/{finprodId:.+}")
    public String findInvAim(@PathVariable("portfolioId") String portfolioId, @PathVariable("finprodId") String finprodId) {
        return baseEtrInvestInfoServiceImpl.findInvAim(portfolioId, finprodId);
    }
	
	/**
     * 插入指令流程中的数据
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsertEtrFlow")
    public boolean doInsertEtrFlow(@RequestBody
                                       BaseEtrInvestInfoDto entity){
        try {
            baseEtrInvestInfoServiceImpl.newDelegateFlow(entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 插入指令流程中的数据
     * @param param
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDeleteEtrFlow")
    public boolean doDeleteEtrFlow(@RequestBody
                                       Map<String,Object> param){
        try {
            baseEtrInvestInfoServiceImpl.deleteDelegateFlow(param);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
