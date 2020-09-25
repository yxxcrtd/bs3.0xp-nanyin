/*
 * 文件名：PtlSingleAssetPositionAnalysis001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;

/**
 * 单一资产持仓分析Service
 * @author wanghb
 * @version 1.0 2019年1月23日
 * @see PtlSingleAssetPositionAnalysis001Service
 * @since 1.0
 */
@Service
public class PtlSingleAssetPositionAnalysis001Service extends CommonExportService<PtlSingleAssetPositionAnalysis001Dto>{
    /**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectForPage(Map<String, Object> map){
        Page<PtlSingleAssetPositionAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleAssetPositionAnalysis/findPage",
                map, PtlSingleAssetPositionAnalysis001Dto.class);
        return page;
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
    public List<PtlSingleAssetPositionAnalysis001Dto> selectForList(Map<String, Object> map){
        List<PtlSingleAssetPositionAnalysis001Dto> list=restTemplate.postForList(
             ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleAssetPositionAnalysis/findList",
             map,PtlSingleAssetPositionAnalysis001Dto.class);
        return list;
    }
    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodMarketId(Map<String,Object> map) {
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/PtlSingleAssetPositionAnalysis/getFinprodMarketId" , map,
                SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            exportParam.config(
                    "/com/joyintech/tams/invest/portfolio/ptlsingleassetpositionanalysis/conf/export_conf_ls.xml").dataSource(
                    DataSourceType.MASERT);
        }else{
            exportParam.config(
                    "/com/joyintech/tams/invest/portfolio/ptlsingleassetpositionanalysis/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
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
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectForExport(Map<String, Object> map){
        Page<PtlSingleAssetPositionAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSingleAssetPositionAnalysis/findPageForExport",
                map, PtlSingleAssetPositionAnalysis001Dto.class);
        return page;
    }
}
