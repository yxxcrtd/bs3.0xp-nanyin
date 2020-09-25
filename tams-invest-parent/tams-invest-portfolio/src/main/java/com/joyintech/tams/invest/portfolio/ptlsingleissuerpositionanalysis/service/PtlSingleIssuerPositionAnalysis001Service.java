/*
 * 文件名：PtlManagementInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.service;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto; 
import com.joyintech.framework.util.StringUtil; 
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate; 
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlcashflowgapanalysis.dto.PtlCashFlowGapAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysis001Dto;

/**
 * 单一发行人持仓分析Service
 * @author pancz
 * @version 1.0 2018年9月6日
 * @see PtlManagementInfo001Service
 * @since 1.0
 */
@Service("PtlSingleIssuerPositionAnalysis001Service")
@Transactional(rollbackFor=Exception.class)
public class PtlSingleIssuerPositionAnalysis001Service extends CommonExportService<PtlSingleIssuerPositionAnalysis001Dto> {
	@Autowired(required = false) 
	private IPlatformDate iPlatformDate;
	/**
	 * 获取发行人信息
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public Page<SelectOptionDto> getPublisher(Map<String,Object> map)
			throws Exception {
		Page<SelectOptionDto> publisherSelect = restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleIssuerPositionAnalysis/getPublisher",map,
				SelectOptionDto.class);
		return publisherSelect;
	}
	
	/**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlSingleIssuerPositionAnalysis001Dto> selectForPage(Map<String, Object> map){
        Page<PtlSingleIssuerPositionAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleIssuerPositionAnalysis/findPage",
                map, PtlSingleIssuerPositionAnalysis001Dto.class);
        return page;
    }
    
    /**
     * 导出查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlSingleIssuerPositionAnalysis001Dto> selectForExport(Map<String, Object> map){
        Page<PtlSingleIssuerPositionAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleIssuerPositionAnalysis/findPageForExport",
                map, PtlSingleIssuerPositionAnalysis001Dto.class);
        return page;
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            exportParam.config(
                    "/com/joyintech/tams/invest/portfolio/ptlsingleissuerpositionanalysis/conf/export_conf_ls.xml").dataSource(
                    DataSourceType.MASERT);
        }else{
            exportParam.config(
                    "/com/joyintech/tams/invest/portfolio/ptlsingleissuerpositionanalysis/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
        }

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlSingleIssuerPositionAnalysis001Dto> selectPageData(Map<String, Object> selectParam) {
        String tab = (String) selectParam.get("tab");
        Page<PtlSingleIssuerPositionAnalysis001Dto>  page = null;
        if(StringUtil.equals("0", tab)){
            page = selectForExport(selectParam);
        }else {
            page = selectForExportTwo(selectParam);
        }
    	return page;
    }
    
    /**
     * 导出查询方法2
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<PtlSingleIssuerPositionAnalysis001Dto> selectForExportTwo(Map<String, Object> map){
        Page<PtlSingleIssuerPositionAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleIssuerPositionAnalysis/findPageForExportTwo",
                map, PtlSingleIssuerPositionAnalysis001Dto.class);
        return page;
    }
}
