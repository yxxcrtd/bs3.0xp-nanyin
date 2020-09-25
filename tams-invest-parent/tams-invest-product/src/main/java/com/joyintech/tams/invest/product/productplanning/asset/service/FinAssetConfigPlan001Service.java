/*
 * 文件名：FinAssetConfigPlan001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.asset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.AssetConfigDetailMapper;
import com.joyintech.tams.common.base.dao.AssetConfigMonDetailMapper;
import com.joyintech.tams.common.base.dao.AssetConfigPlanMapper;
import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigDetailKey;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetailKey;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetConfigPlan001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlan001Service
 * @since 1.0
 */
@Service
public class FinAssetConfigPlan001Service extends CommonExportService<FinAssetConfigPlan001Dto> implements BaseProcessService<FinAssetConfigPlan001Dto>{

    @Autowired
    private AssetConfigPlanMapper assetConfigPlanMapper;
    @Autowired
    private AssetConfigDetailMapper assetConfigDetailMapper;
    @Autowired
    private AssetConfigMonDetailMapper assetConfigMonDetailMapper;
    
    /**
     * 查询资产配置计划
     *
     * @param Map<String, Object> map
     * @return Page结果
     * @see
     * @since 1.0
     */
    public Page<FinAssetConfigPlan001Dto> selectForPage(Map<String, Object> map) {
        if (map.get("assetType") != null){
            map.put("assetType", StringUtil.splitToList(null, map.get("assetType").toString()));
        }        
        Page<FinAssetConfigPlan001Dto> page = restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/selectForPage", map,
        		FinAssetConfigPlan001Dto.class);
        return page;
    }

    /**
     * 查询资产配置计划
     *
     * @param Map<String, Object> map
     * @return List<FinAssetConfigPlan001Dto>结果
     * @see
     * @since 1.0
     */
    public List<FinAssetConfigPlan001Dto> selectForList(Map<String, Object> map) {
        if (map.get("assetType") != null){
            map.put("assetType", StringUtil.splitToList(null, map.get("assetType").toString()));
        }        
        List<FinAssetConfigPlan001Dto> list = restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryAssetConfigPlanList", map,
        		FinAssetConfigPlan001Dto.class);
        return list;
    }

     /**
     * 导出资产配置计划
     *
     * @param ExportParam exportParam
     * @return
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/productplanning/asset/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
    * 查询资产配置计划
    *
    * @param Map<String, Object> selectParam
    * @return Page<FinAssetConfigPlan001Dto>
    * @see
    * @since 1.0
    */
    @Override
    public Page<FinAssetConfigPlan001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 执行删除逻辑
     *
     * @param FinAssetConfigPlan001Dto entity
     * @param OperationType type
     * @return
     * @see
     * @since 1.0
     */
	@Override
	public void doComplete(FinAssetConfigPlan001Dto entity, OperationType type) {
		if (OperationType.DELETE.equals(type)) {
			String assetConfigCode = entity.getAssetConfigCode();
			AssetConfigPlan plan = assetConfigPlanMapper.selectByPrimaryKey(assetConfigCode);
			
			Map<String, Object> param = new HashMap<String, Object>();
	    	param.put("assetConfigCode", assetConfigCode);
	    	List<AssetConfigDetail> details = restTemplate.postForList(
	                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinAssetConfigPlan/queryAssetConfigDetails",param, AssetConfigDetail.class);
			for (AssetConfigDetail dto : details) {
    			AssetConfigDetailKey key1 = new AssetConfigDetailKey();
    			key1.setAssetConfigCode(assetConfigCode);
    			key1.setAssetType(dto.getAssetType());
    			assetConfigDetailMapper.deleteByPrimaryKey(key1);   			
    			int month = DateUtil.getMonth(DateUtil.format(plan.getBenchDate()));
    	    	while (month <= 12 ) {
	    			AssetConfigMonDetailKey key2 = new AssetConfigMonDetailKey();
	    			key2.setAssetConfigCode(assetConfigCode);
	    			key2.setAssetType(dto.getAssetType());
	    			key2.setAssetMonth(String.valueOf(month));
	    			assetConfigMonDetailMapper.deleteByPrimaryKey(key2);
	    			month++;
    			}
    		}
			assetConfigPlanMapper.deleteByPrimaryKey(assetConfigCode);
		}
		
	}

}
