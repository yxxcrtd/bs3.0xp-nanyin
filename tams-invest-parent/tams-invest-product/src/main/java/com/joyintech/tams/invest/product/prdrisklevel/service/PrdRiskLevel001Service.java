/*
 * 文件名：FinProductPayInt001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.prdrisklevel.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.prdrisklevel.dto.PrdRiskLevel001Dto;

/**
 * 新发产品风险等级评定查询service
 * yangxiaofeng
 */
@Service
public class PrdRiskLevel001Service extends CommonExportService<PrdRiskLevel001Dto> {

    /**
     * 产品设计编码下拉框
     */
    public Page<SelectOptionDto> productDesignCode(Map<String, Object> param)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/productDesignCode", param,
            SelectOptionDto.class);
    }

    /**
     * 检索产品存续期交易信息
     *
     * @param pageParam 查询参数
     * @return 产品存续期交易信息
     * @see
     * @since 1.0
     */
    public Page<PrdRiskLevel001Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/findPage", pageParam,
            PrdRiskLevel001Dto.class);
    }
    
    /**
     * 产品设计系列名称模糊查询
     */
    public List<SelectOptionDto> selectFinProductName(Map<String, Object> pageParam){
    	return restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/PrdRiskLevel/selectFinProductName", pageParam,
                SelectOptionDto.class);
    }
    
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/prdrisklevel/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}
	
	public Page<PrdRiskLevel001Dto> selectPageData(Map<String, Object> selectParam) {
		return findPage(selectParam);
	}
}
