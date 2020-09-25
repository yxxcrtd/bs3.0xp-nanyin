package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 资产规划信息维护
 * @author guyunfei
 * 20191226
 */
@Service
public class AssetPlanNewsDefend001Service extends CommonExportService<AssetPlanNewsDefend001Dto> implements BaseProcessService<AssetPlanNewsDefend001Dto> {

    public Page<AssetPlanNewsDefend001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetPlanNewsDefend/selectForPage",
                pageParam,AssetPlanNewsDefend001Dto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/product/productplanning/assetplannewsdefend/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<AssetPlanNewsDefend001Dto> selectPageData(Map<String, Object> pageParam){
        return selectForPage(pageParam);
    }

    @Override
    public void doComplete(AssetPlanNewsDefend001Dto dto, OperationType operationType) {
        String assetPlanCode = dto.getAssetPlanCode();
        String assetType = dto.getAssetType();
        Map<String,Object> params = new HashMap<>();
        params.put("assetPlanCode",assetPlanCode);
        params.put("assetType",assetType);
        restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetPlanNewsDefend/deleteByPrimary",
                params,
                Integer.class
        );
    }
}
