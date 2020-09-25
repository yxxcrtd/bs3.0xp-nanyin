/*
 * 文件名：EquityInfoRegist.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.chb3.submitmanagement.equitybusiinforegist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.ChbBatchParaMapper;
import com.joyintech.tams.common.base.entity.ChbBatchPara;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;



/**
 * 投资者身份信息登记服务
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see EquityInfoRegist
 * @since 1.0
 */
@RestController
@RequestMapping("/EquityBusiInfoRegist")
public class EquityBusiInfoRegistController{
    /**
     * 001Service
     */
    @Autowired
    private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;
    
    @Autowired
    private CheckReport001Service checkReport001Service;
 
    @Autowired
    private ChbBatchParaMapper chbBatchParaMapper;
    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EquityBusiInfoRegist001Dto> search(@RequestBody Map<String, Object> param){
        return equityBusiInfoRegist001Service.selectForPage(param);
    }
    @PostMapping("queryPrdNameList")
    public List<SuggestDto> queryPrdNameList(Map<String,Object> paramMap) {
    	return checkReport001Service.queryPrdNameList(paramMap);
    }
    /**
//     *手工处理功能
//     * @return 提示信息
//     * @param map 条件map
//     * @seea
//     * @since 1.0
//     */
    @PostMapping("insert")
    public void insert(@RequestBody ChbBatchPara record){
    	chbBatchParaMapper.insert(record);
    }
    @GetMapping("selectDeletedAndUnsubmittedData")
    public Map<String, Object>selectDeletedAndUnsubmittedData(){
    	return equityBusiInfoRegist001Service.selectDeletedAndUnsubmittedData();
    }
    @GetMapping("checkIfInBatchByTaskBeanName")
    public boolean checkIfInBatchByTaskBeanName(String beanname) {
    	return equityBusiInfoRegist001Service.checkIfInBatchByTaskBeanName(beanname);
    }
    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String, Object> map) {
    	 equityBusiInfoRegist001Service.updateSumStaus(map);
    }
    
    @PostMapping("updateSumStausAll")
    public void updateSumStausAll(@RequestBody Map<String, Object> map) {
    	 equityBusiInfoRegist001Service.updateSumStausAll(map);
    }
    /**
     *取消手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @see
     * @since 1.0
     */
    @PostMapping("cancelMannulAll")
    public void cancelMannulAll(@RequestBody Map<String,Object> map) {
    		equityBusiInfoRegist001Service.cancelMannulAll(map);
    	
    }
    
    /**
     *取消手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @see
     * @since 1.0
     */
    @PostMapping("cancelMannul")
    public void cancelMannul(@RequestBody Map<String,Object> map) {
    		equityBusiInfoRegist001Service.cancelMannul(map);
    	
    }
    /**
     * checkIsNotSubmit
     * @return 提示信息
     * @param map 条件map
     * @see
     * @since 1.0
     */
    @PostMapping("checkIsNotSubmit")
    public List<ValidMessage> checkIsNotSubmit(@RequestBody  Map<String,Object> map) {
    	return equityBusiInfoRegist001Service.checkIsNotSubmit(map);
    }
//    @GetMapping("executeBatch")
//    public void executeBatch() {
//    	 sysBatchStart001Service.executeBatch();
//    }
    

    /**
     * 报送检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("sendBeforeCheck")
    public List<ExcelValidDto> sendBeforeCheck(@RequestBody Map<String,Object> map) {
        return equityBusiInfoRegist001Service.sendBeforeCheck(map);
    }
    /**
     * 报送检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("submitBeforeCheck")
    public List<ExcelValidDto> submitBeforeCheck(@RequestBody Map<String,Object> map) {
        return equityBusiInfoRegist001Service.submitBeforeCheck(map);
    }
    /**
     * 报送检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("submitChb")
    public void submitChb(@RequestBody Map<String,Object> map) {
         equityBusiInfoRegist001Service.dealData(map);
    }
    
}
