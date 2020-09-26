/*
 * 文件名：EquityBaseInfo001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月13日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.maintenance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.maintenance.dto.EquiteBaseInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 权益基期信息
 * 
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see EquityBaseInfoMService
 * @since 1.0
 */
@Service
public class EquityBaseInfoMService extends CommonExportService<EquiteBaseInfo001Dto> {


    /**
     * 产品名称下拉框
     * 
     * @return 产品名称列表
     */
	public List<SuggestDto> queryPrdNameList(String param) {
		return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/getPrdName",param,
        		SuggestDto.class);
    }

    /**
     * 查询列表
     * 
     * @return Page
     */
    public Page<EquiteBaseInfo001Dto> searchForPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/findPage",param,
        		EquiteBaseInfo001Dto.class);
    }


    /**
     * 导出excel查询
     *
     * @return 导出
     */
	public List<EquiteBaseInfo001Dto> queryReportList(Map<String, Object> param){
		return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/queryReportList",param,
        		EquiteBaseInfo001Dto.class);
    }

    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     */
    public void doDelete(String deleteKeys) {
    	restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/doDelete",deleteKeys,
        		Integer.class);
    }

	public List<ExcelValidDto> importData(String fileName) {
		String deptCode=CommonUtil.getDeptCode();
    	String orgCode=CommonUtil.getOrgCode();
    	Map<String, Object> param =new HashMap<String, Object>();
    	param.put("fileName", ExcelUtil.getDataList(fileName));
    	param.put("deptCode", deptCode);
    	param.put("orgCode", orgCode);
    	return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/importData",
        		param,
        		ExcelValidDto.class);
    }
    
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
    	return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBaseInfo/checkConfirmMsgForImportData",
        		ExcelUtil.getDataList(fileName),
        		SvrAjaxResult.class);
    }
}
