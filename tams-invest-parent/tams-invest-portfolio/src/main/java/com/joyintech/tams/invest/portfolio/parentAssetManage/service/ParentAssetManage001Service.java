/*
 * 文件名：FinPlanProfitAsset003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.parentAssetManage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.parentAssetManage.dto.ParentAssetManage001Dto;

/**
 * 母资管计划服务
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年11月21日
 * @see FinPlanProfitAsset003Service
 * @since 1.0
 */
@Service
public class ParentAssetManage001Service extends CommonExportService<ParentAssetManage001Dto> {
	
    /**
     * 母资管计划查询
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<ParentAssetManage001Dto> getParentPlanInfos(Map<String, Object> map) {
    	List<ParentAssetManage001Dto> postForList = restTemplate.postForList(
    			ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ParentAssetManage/selectForList", 
    			map, ParentAssetManage001Dto.class);
    	Page<ParentAssetManage001Dto> page = new Page<ParentAssetManage001Dto>();
    	page.setRoot(postForList);
        return page;
	}
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/parentAssetManage/conf/export_parent_plan.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    public Page<SelectOptionDto> getAssetIdOption() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue());
        return restTemplate.postForPage(ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/getAssetIdOption", params,
            SelectOptionDto.class);
    } 
    
    
    
    /**
     * 导出数据调用查询方法
     */
    @Override
    public Page<ParentAssetManage001Dto> selectPageData(Map<String, Object> selectParam) {
    	// 添加导出判断标识
    	selectParam.put("export", true);
        return getParentPlanInfos(selectParam);
    }
}
