/*
 * 文件名：RepFinProductOrgIssRegistService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.debtreport.repfinproductorgissregist.dto.RepFinProductOrgIssRegistDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月21日
 * @see RepFinProductOrgIssRegistService
 * @since 1.0
 */
@Service
public class RepFinProductOrgIssRegistService extends CommonExportService<RepFinProductOrgIssRegistDto> {
    /**
     * 
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodName(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME +
            "/RepFinProductOrgIssRegist/getRepFinprodName",pageParam,
            SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<RepFinProductOrgIssRegistDto> selectForPage(Map<String, Object> pageParam) {
        if(pageParam.get("dateSDate") == null || "".equals(pageParam.get("dateSDate"))){
            //获取平台日
              pageParam.put("SESSION_SYS_DATE", platformDate != null ? platformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
          }
          pageParam.put("ISSUE_PARTY_ID", CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
          return restTemplate.postForPage(
              ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductOrgIssRegist/findPage",
              pageParam, RepFinProductOrgIssRegistDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repfinproductorgissregist/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepFinProductOrgIssRegistDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
