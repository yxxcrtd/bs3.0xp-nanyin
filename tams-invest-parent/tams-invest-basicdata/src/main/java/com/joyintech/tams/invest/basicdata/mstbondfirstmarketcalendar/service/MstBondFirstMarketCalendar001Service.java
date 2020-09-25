/*
 * 文件名：MstBondFirstMarketCalendar001Service.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：赵东东
 * 修改时间：2019年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;

import com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.dto.MstBondFirstMarketCalendar001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 一级新债日历Service
 * 
 * @author 赵东东
 * @version 1.0 2019年11月16日
 * @see MstBondFirstMarketCalendar001Service
 * @since 1.0
 */
@Service
public class MstBondFirstMarketCalendar001Service extends CommonExportService<MstBondFirstMarketCalendar001Dto>  {

	

	/**
     * 查询画面查询方法
     * 
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstBondFirstMarketCalendar001Dto> selectForPage(Map<String, Object> map){
        Page<MstBondFirstMarketCalendar001Dto> page = restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBondFirstMarketCalendar001/findPage",
            map, MstBondFirstMarketCalendar001Dto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstbondfirstmarketcalendar/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
     /**
      * 导出时调用查询
      */
    @Override
    public Page<MstBondFirstMarketCalendar001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 查询finprodId
     * @param finprodId
     * @return
     */
    public String getFinprodId(String finprodId){
        return restTemplate.getForObject(ServiceApplication.BASICDATA_SERVICE_NAME +
                "/MstBondFirstMarketCalendar001/getFinprodId?finprodId="+ finprodId,String.class);
    }
}
