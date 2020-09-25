/*
 * 文件名：OperationManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.operationmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.operationmanage.dto.OperationManageDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 运营管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月18日
 * @see OperationManageService
 * @since 1.0
 */

@Service
public class OperationManageService extends CommonExportService<OperationManageDto> implements BaseProcessService<OperationManageDto> {
	
	
	/**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId(Map<String, Object> param){
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME + "/OperationManage/getPortfolioId",param,
        		SuggestDto.class);
    }
    
    /**
     * 
     * 组合代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId1(Map<String, Object> param){
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME + "/OperationManage/getPortfolioId1",param,
        		SuggestDto.class);
    }
	
	/**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<OperationManageDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/OperationManage/selectForPage",
            pageParam, OperationManageDto.class);
    }
	
	@Override
	public void doComplete(OperationManageDto arg0, OperationType arg1) {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
    	if(exportParam.getSelectParam().get("index").equals("0")){
    		exportParam.config(
    	            "/com/joyintech/tams/invest/basicdata/OperationManage/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    	}else if(exportParam.getSelectParam().get("index").equals("1")){
    		exportParam.config(
    	            "/com/joyintech/tams/invest/basicdata/OperationManage/conf/export_conf1.xml").dataSource(DataSourceType.MASERT);
    	}else{
    		exportParam.config(
    	            "/com/joyintech/tams/invest/basicdata/OperationManage/conf/export_conf2.xml").dataSource(DataSourceType.MASERT);
    	}
    }
    
    @Override
    public Page<OperationManageDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
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
