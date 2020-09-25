/*
 * 文件名：RepFinProductBalanceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.balance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.balance.dto.RepFinProductBalanceDto;

/**
 *  产品余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月1日
 * @see RepFinProductBalanceService
 * @since 1.0
 */
@Service
public class RepFinProductBalanceService extends CommonExportService<RepFinProductBalanceDto>{
    
    @Autowired
    private IPlatformDate iPlatformDate; 
    
    public Page<RepFinProductBalanceDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("sysDate", DateUtil.getSysDate());
        pageParam.put("queryDate", pageParam.get("queryDate"));
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
        }else{
            pageParam.remove("portfolioId");
        }
        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.remove("finprodId");
        }
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductBalance/findPage",
            pageParam, RepFinProductBalanceDto.class);
    }

    public List<SelectOptionDto> getFinprodIdByPortfolioId(Map<String,Object> map){
        if (StringUtil.isNotEmpty(map.get("portfolioId"))) {
            map.put("portfolioId", StringUtil.splitToList(null, map.get("portfolioId").toString()));
        }else{
            map.remove("portfolioId");
        }
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductBalance/getFinprodIdByPortfolioId",
            map, SelectOptionDto.class);
    }
    
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/balance/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepFinProductBalanceDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
   
}
