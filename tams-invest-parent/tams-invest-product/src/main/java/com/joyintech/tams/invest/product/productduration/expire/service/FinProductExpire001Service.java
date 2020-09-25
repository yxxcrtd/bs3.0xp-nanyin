/*
 * 文件名：FinProductExpire001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.expire.service;

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
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire001Dto;


/**
 * 产品到期查询
 * 
 * @author zhangliang
 * @version 1.0 2019年1月7日
 * @see FinProductExpire001Service
 * @since 1.0
 */
@Service
public class FinProductExpire001Service extends CommonExportService<FinProductExpire001Dto> {

    
    /**
     * 查询产品成立信息
     * 
     * @param map 参数map
     * @return 产品成立信息
     * @see
     * @since 1.0
     */
    public Page<FinProductExpire001Dto> selectForPage(Map<String, Object> map) {
        if (StringUtil.isNotEmpty(map.get("finprodId"))) {
            map.put("finprodIdList", StringUtil.splitToList(null, map.get("finprodId").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("ptlId"))) {
            map.put("ptlId", StringUtil.splitToList(null, map.get("ptlId").toString()));
        }
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/selectForPage", map,
            FinProductExpire001Dto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/productduration/expire/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductExpire001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
}
