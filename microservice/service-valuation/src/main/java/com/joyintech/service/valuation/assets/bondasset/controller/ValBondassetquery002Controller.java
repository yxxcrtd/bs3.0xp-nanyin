/*
 * 文件名：ValBondassetquery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.bondasset.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery002Dto;
import com.joyintech.service.valuation.assets.bondasset.service.ValBondassetquery002Service;


/**
 * 模板示例<br>
 * 债券类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValBondassetquery002Controller
 * @since 2.0
 */
//@RestController(value="serviceValBondassetquery002Controller")
@RestController("valBondassetquery002Ctl_dtx")
@RequestMapping("/ValBondassetquery002")
public class ValBondassetquery002Controller extends BaseController {
    
    /**
     * ValBondassetquery002Service
     */
    @Autowired
    private ValBondassetquery002Service valBondassetquery002Service;

    /**
     * 查询是否交易数据
     * 
     * <功能详细描述>
     * @param finprodid
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDealCount")
    public int selectDealCount(@RequestBody String finprodid) {        
    	return valBondassetquery002Service.selectDealCount(finprodid);
    }
    
    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @PostMapping("/queryBondasetByfinprodId")
    public ValBondassetquery002Dto queryBondasetByfinprodId(@RequestBody Map<String, Object> map) {  
        return valBondassetquery002Service.queryBondasetByfinprodId(map);  
    }

    /**
     * 查询是否交易数据：行权交易
     * 
     * <功能详细描述>
     * @param finprodid
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDealCount2")
    public int selectDealCount(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.selectDealCount(map);
    }
    
    /**
     * 获取发行人信息
     * 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getIssure")
    public String getIssure() {
        return valBondassetquery002Service.getIssure();
    }
    
    /**
     * 查询交易日历下拉框
     * 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getTradeCalendar")
    public String getTradeCalendar() {
        return valBondassetquery002Service.getTradeCalendar();
    }

    /**
     * 详情页表单数据的获取-行权信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryXqinfo")
    public List<ValBondassetquery002Dto> queryXqinfo(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.queryXqinfo(map);
    }

    /**
     * 详情页表单数据的获取-债券利率报价信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryintrate")
    public List<ValBondassetquery002Dto> queryintrate(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.queryintrate(map);
    }

    /**
     * 详情页表单数据的获取-付息计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryintpay")
    public List<ValBondassetquery002Dto> queryintpay(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.queryintpay(map);
    }

    /**
     * 详情页表单数据的获取-还本计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryrepay")
    public List<ValBondassetquery002Dto> queryrepay(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.queryrepay(map);
    }
    
    /**
     * 详情页表单数据的获取-现金流列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/querycashflow")
    public List<ValBondassetquery002Dto> querycashflow(@RequestBody Map<String, Object> map) {
        return valBondassetquery002Service.querycashflow(map);
    }
    
    /**
     * 查询流程中数据
     * <功能详细描述>
     * @param processMark
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/workflowCheck")
    public int workflowCheck(@RequestBody ProcessMark processMark) throws Exception {
        return valBondassetquery002Service.workflowCheck(processMark);
    }
    
    /**
     * 查询大于等于actDateEnd的第一个工作日
     * 
     * <功能详细描述>
     * @param actDateEnd
     * @param tradMarketId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getNextWorkDay")
    public Date getNextWorkDay(@RequestBody Map<String,Object> map) {
        map.put("actdateend", DateUtil.parse((String)map.get("actdateend")));
        return valBondassetquery002Service.getNextWorkDay(map);
    }
    
    /**
     * 判断资产代码是否存在
     * 
     */
    @PostMapping("/selectByPrimaryKey")
    public int selectByPrimaryKey(@RequestBody String finprodId) {
        return valBondassetquery002Service.selectByPrimaryKey(finprodId);
    }
    
    /**
     * 判断资产代码是否存在
     * 
     */
    @Transactional
	@DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody ValBondassetquery002Dto entity) {
        valBondassetquery002Service.doComplete(entity);
    }
    
}
