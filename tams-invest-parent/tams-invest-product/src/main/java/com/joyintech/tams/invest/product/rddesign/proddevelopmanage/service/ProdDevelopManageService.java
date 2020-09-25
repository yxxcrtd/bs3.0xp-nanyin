/*
 * 文件名：ProdDevelopManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.proddevelopmanage.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto.FinProductFeaAna001Dto;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.dto.ProdDevelopManageDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品研发方案管理
 * 
 * @author zhangJin
 * @version 1.0 2019年10月22日
 * @see ProdDevelopManageService
 * @since 1.0
 */
@Service
public class ProdDevelopManageService extends CommonExportService<ProdDevelopManageDto> implements BaseProcessService<ProdDevelopManageDto> {

    /**
     * 获取系列编号下拉框
     * 
     * @param pageParam 模糊查询
     * @return 下拉框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getProdDesignSeries(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/ProdDevelopManage/getProdDesignSeries",
            pageParam, SelectOptionDto.class);
    }

    /**
     * 工作流完成方法
     */
    @Override
    public void doComplete(ProdDevelopManageDto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/ProdDevelopManage/doComplete/" + type,
            entity, Integer.class);
    }

    /**
     * 根据系列编号获取结果信息
     * 
     * @param pageParam 系列编号
     * @return 结果信息
     * @see
     * @since 1.0
     */
    public ProdDevelopManageDto getProdResultInfos(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/ProdDevelopManage/getProdResultInfos",
            pageParam, ProdDevelopManageDto.class);
    }

    /**
     * 分页查询
     * 
     * @param parameters 参数
     * @return 查询结果
     */
    public Page<ProdDevelopManageDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/ProdDevelopManage/selectForPage",
            pageParam, ProdDevelopManageDto.class);
    }

    /**
     * 根据流水号查询研发方案详情
     * 
     * @param seqNo 流水号
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    public ProdDevelopManageDto getEntityBySeqNo(String seqNo) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/ProdDevelopManage/getEntityBySeqNo?seqNo=" + seqNo,
            ProdDevelopManageDto.class);
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/rddesign/proddevelopmanage/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 导出查询数据 {@inheritDoc}
     */
    @Override
    public Page<ProdDevelopManageDto> selectPageData(Map<String, Object> pageParam) {
        return selectForPage(pageParam);
    }
}
