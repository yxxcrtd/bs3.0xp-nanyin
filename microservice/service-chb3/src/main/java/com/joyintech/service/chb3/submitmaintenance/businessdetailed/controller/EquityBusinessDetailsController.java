/*
 * 文件名：EquityBusinessDetails001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.chb3.submitmaintenance.businessdetailed.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitexamination.checkreport.service.CheckReport001Service;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusiDetails001Service;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusiDetails002Service;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;

/**
 *  权益业务明细信息服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see EquityBusinessDetailsController
 * @since 1.0
 */
@RestController
@RequestMapping("/EquityBusinessDetails")
public class EquityBusinessDetailsController{
    
    /**
     * EquityBaseInfoBusinessDetails001Service
     */
    @Autowired
    private EquityBusiDetails001Service equityBusiDetails001Service;

    /**
     * 报送前检查service//使用机构代码下拉框
     */
    @Autowired
    private CheckReport001Service checkReport001Service;
    @Autowired
	private EquityBusiDetails002Service equityBusiDetails002Service;
    @Autowired
    private ProRegistCodeManage001Service proRegistCodeManage001Service;
    
    /**
     * 产品名称下拉框
     * @return 产品名称列表
     */
    @PostMapping("queryPrdNameList")
    public List<SuggestDto> queryPrdNameList(@RequestBody  Map<String,Object> paramMap) {
        return checkReport001Service.queryPrdNameList(paramMap);
    }
    @PostMapping("queryPrdNames")
    public List<SelectOptionDto> queryPrdNames(@RequestBody Map<String, Object> map) {
        return checkReport001Service.queryPrdNames(map);
    }
    
    
    /**
     * 查询列表
     * 
     * @return Page
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<EquiteBusiDetails001Dto> search(@RequestBody Map<String, Object> param) {
        return equityBusiDetails001Service.searchForPage(param);
    }
    
    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        return this.equityBusiDetails001Service.importData(param);
    }
    @PostMapping("importChbData")
    public List<ExcelValidDto> importChbData(@RequestBody Map<String, Object> param) {
        return this.equityBusiDetails001Service.importChbData(param);
    }
    
    /**
     * 导出excel查询
     *
     * @return 导出结果
     */
    @PostMapping("queryReportList")
    public List<EquiteBusiDetails001Dto> queryReportList(@RequestBody Map<String, Object> param) {
    	return this.equityBusiDetails001Service.queryReportList(param); 
    }
    
    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     * 
     * @return 处理结果
     */
    @PostMapping("doDelete")
    public void doDelete(@RequestBody EquiteBusiDetails001Dto dto) {
        equityBusiDetails001Service.doDelete(dto);
    }
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) {
        return equityBusiDetails001Service.checkConfirmMsgForImportData(param);
        		
    }
    /**
     * 导入代销文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @PostMapping("checkConfirmMsgForChbImportData")
    public AjaxResult checkConfirmMsgForChbImportData(@RequestBody Map<String, Object> param) {
        return equityBusiDetails001Service.checkConfirmMsgForChbImportData(param);		
    }
    @PostMapping("getEntityByKey")
    public EquiteBusiDetails001Dto getEntityByKey(@RequestBody Map<String, Object> map) {
    	 return equityBusiDetails002Service.getEntityByKey(map);
    }
    @GetMapping("queryAllRegistCode")
    public List<SelectOptionDto> queryAllRegistCode() {
    	 return proRegistCodeManage001Service.queryAllRegistCode();
    }
    @PostMapping("doSaveOrUpdate")
    public void doSaveOrUpdate(@RequestBody Map<String, Object> map) {
    	  equityBusiDetails002Service.doSaveOrUpdate(map);
    }
}
