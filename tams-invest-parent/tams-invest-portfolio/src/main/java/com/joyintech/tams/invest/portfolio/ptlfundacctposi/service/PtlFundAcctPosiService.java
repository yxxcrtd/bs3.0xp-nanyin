/*
 * 文件名：PtlFundAcctPosiService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundacctposi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.joyintech.tams.constant.DictCodeList;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CommonAlgorithmUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.invest.portfolio.ptlfundacctposi.dto.PtlFundAcctPosiDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 
 * 组合资金账户头寸
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月20日
 * @see PtlFundAcctPosiService
 * @since 1.0
 */
@Service
public class PtlFundAcctPosiService extends CommonExportService<PtlFundAcctPosiDto> implements BaseProcessService<PtlFundAcctPosiDto>{
	
	/**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String, Object> param){
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAcctPosi/getPortfolioId",param,
            SelectOptionDto.class);
    }
	
	/**
     * 页面查询
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<PtlFundAcctPosiDto> selectForPage(Map<String, Object> pageParam){
        String portfolioId = (String)pageParam.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)){
            List<String> portfolioIdList = StringUtil.splitToList(",",portfolioId);
            pageParam.put("portfolioIdList",portfolioIdList);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        String prodManager = (String)pageParam.get("prodManager");
        if (StringUtil.isNotEmpty(prodManager)){
            List<String> prodManagerList = StringUtil.splitToList(",",prodManager);
            pageParam.put("prodManager",prodManagerList);
        }else {
            pageParam.put("prodManager",null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end
        pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAcctPosi/selectForPage",
            pageParam, PtlFundAcctPosiDto.class);
    }
    
    /**
     * 页面查询
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<PtlFundAcctPosiDto> selectForPage1(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAcctPosi/selectForPage1",
            pageParam, PtlFundAcctPosiDto.class);
    }
    
    /**
     * 页面查询
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<PtlFundAcctPosiDto> selectForPage2(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAcctPosi/selectForPage2",
            pageParam, PtlFundAcctPosiDto.class);
    }
    
	@Override
	public void doComplete(PtlFundAcctPosiDto arg0, OperationType arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	if(exportParam.getSelectParam().get("index").equals("0")){
    		exportParam.config(
    	            "/com/joyintech/tams/invest/portfolio/ptlfundacctposi/conf/export_conf.xml").dataSource(
    	                DataSourceType.MASERT);
    	}else{
    		exportParam.config(
    	            "/com/joyintech/tams/invest/portfolio/ptlfundacctposi/conf/export_conf1.xml").dataSource(
    	                DataSourceType.MASERT);
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlFundAcctPosiDto> selectPageData(Map<String, Object> selectParam) {
        // add by wangyang1 【吉林银行】 导出一二三级明细  2019年9月29日 start
        if(selectParam.get("index").equals("0")){
        	return selectForPage(selectParam);
        }else{
        	return selectForPage1(selectParam);
        }
        //add by wangyang1 【吉林银行】 导出明细  end
    }

    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "settleDate")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    
   
}
