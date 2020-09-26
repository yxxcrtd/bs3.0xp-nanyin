/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo001Service;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 每日债券行情
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Service
 * @since 1.0
 */
@Service
public class ValBondMarketInfo001Service extends CommonExportService<ValBondMarketInfo001Dto> {
    /**
     * 
     *每日债券行情查询
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValBondMarketInfo001Dto> queryPageList(Map<String, Object> params) throws Exception{
//        return super.queryForPage(()->valBondMarketInfo001Mapper.queryPageList(dto));
//        return valBondMarketInfo001Mapper.queryPageList(dto);
    	Page<ValBondMarketInfo001Dto> page = new Page<ValBondMarketInfo001Dto>();
    	page= restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondMarketInfo001/findPage",
    			params, ValBondMarketInfo001Dto.class);
    	return page;
    }
    
    
    public List<ValBondMarketInfo001Dto> queryAllList(Map<String, Object> params) throws Exception{
        
//        return valBondMarketInfo001Mapper.queryPageList(dto);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondMarketInfo001/queryAllList",
                params, ValBondMarketInfo001Dto.class);
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondMarketInfo001/checkConfirmMsgForImportData",
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondMarketInfo001/importData",
                param,
                ExcelValidDto.class);
    }

}
