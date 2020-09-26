/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 外汇估值信息
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Service
 * @since 1.0
 */
@Service
public class ValFxValuztionInfo001Service extends CommonExportService<ValFxValuztionInfo001Dto> {
	
	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo001/getFinprodId2",
                params, SelectOptionDto.class);
	}

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<ValFxValuztionInfo001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo001/findPage",
    			params, ValFxValuztionInfo001Dto.class);
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ValFxValuztionInfo001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo001/queryAllList",
                params, ValFxValuztionInfo001Dto.class);
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo001/checkConfirmMsgForImportData",
                param,
                InvAjaxResult.class);
    }



    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("tempList", ExcelUtil.getTempList1(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFxValuztionInfo001/importData",
                param,
                ExcelValidDto.class);
    }


}
