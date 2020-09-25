/*
 * 文件名：MaintainProductService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.rddesign.productelementdes.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes002Dto;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 *
 * 产品要素设计查询
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see ProductelementdesService
 * @since 1.0
 */
@Service
public class ProductelementdesService extends CommonExportService<Productelementdes002Dto> {
    /**
     * 查询table2
     * @param findepage
     * @return
     */
    public Page<Productelementdes002Dto> findPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
         return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/findPage", pageParam,
                Productelementdes002Dto.class);
    }
    public Page<Productelementdes001Dto> findDetailPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/findDetailPage", pageParam,
                Productelementdes001Dto.class);
    }
    public Productelementdes001Dto findSinglePage(String pageParam) {

        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/findSinglePage?pageParam="+pageParam,
                Productelementdes001Dto.class);
    }
    /**
     * 查找设计要素信息
     */
    public Productelementdes002Dto findprdDesignId(String pageParam) {

        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/findprdDesignId?pageParam=" + pageParam,
                Productelementdes002Dto.class);
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();
        String tableId = (String)param.get("tableId");
        if (StringUtil.equals(tableId, "total")) {
            exportParam.config("/com/joyintech/tams/invest/product/rddesign/productelementdes/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/product/rddesign/productelementdes/conf/export_conf_detail.xml").dataSource(
                    DataSourceType.MASERT);
        }

    }
    @Override
    public Page selectPageData(Map<String, Object> selectParam) {
        String tableId = (String)selectParam.get("tableId");
        if (StringUtil.equals(tableId, "total")) {
            Page<Productelementdes002Dto> result = findPage(selectParam);
            return result;
        }
        else {
            Page<Productelementdes001Dto> result=findDetailPage(selectParam);
            return result;
        }

    }

}
