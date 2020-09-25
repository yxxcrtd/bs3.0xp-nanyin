/*
 * 文件名：FinProductEstablish001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.establish.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangliang
 * @version 1.0 2018年12月19日
 * @see FinProductEstablish001Service
 * @since 1.0
 */
@Service
public class FinProductEstablish001Service extends CommonExportService<FinProductEstablishDto>{

    /**
     * 查询产品成立信息
     * 
     * @param map 参数map
     * @return 产品成立信息
     * @see
     * @since 1.0
     */
    public Page<FinProductEstablishDto> selectForPage(Map<String, Object> map) {
        if (StringUtil.isNotEmpty(map.get("finprodId"))) {
            map.put("finprodId", StringUtil.splitToList(null, map.get("finprodId").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("ptlId"))) {
            map.put("ptlId", StringUtil.splitToList(null, map.get("ptlId").toString()));
        }
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectForPage", map,
            FinProductEstablishDto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/productduration/establish/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductEstablishDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
	 * 查询该产品是否有别的交易
	 */
	public Integer selectDealAdd(String finprodId,String vdate){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("finprodId", finprodId);
		map.put("vdate",vdate);
		return restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectDealAdd", map,
				Integer.class);
	}
}
