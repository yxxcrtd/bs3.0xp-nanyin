/*
 * 文件名：SecAssetStatistics001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.report.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.report.dto.RiskMonitorIndicators001Dto; 
 
/**
 * 风险监测指标Service
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月11日
 * @see RiskMonitorIndicators001Service
 * @since 1.0
 */
@Service
public class RiskMonitorIndicators001Service extends CommonExportService<RiskMonitorIndicators001Dto>{
    

  public Page<RiskMonitorIndicators001Dto> selectForPage(Map<String, Object> pageParam){
      Page<RiskMonitorIndicators001Dto> dto =  restTemplate.postForPage(
          ServiceApplication.REPORT_SERVICE_NAME + "/RiskMonitorIndicators/selectForPage", pageParam,
          RiskMonitorIndicators001Dto.class);
      return dto;
  }
 
 
  /**
   * 导出
   */
  @Override
  protected void buildExportParam(ExportParam exportParam) {
      exportParam.config(
          "/com/joyintech/tams/poc/report/conf/riskmonitorindicators_export_conf.xml").dataSource(
              DataSourceType.MASERT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<RiskMonitorIndicators001Dto> selectPageData(Map<String, Object> selectParam) {
      return selectForPage(selectParam);
  }
 /**
  * 理财经理联想下拉框
  *
  * @param param 参数map
  * @return 理财经理
  * @see
  * @since 1.0
  */
 public List<SelectOptionDto> queryManagerInfo(Map<String, Object> param){
     return restTemplate.postForList(
         ServiceApplication.REPORT_SERVICE_NAME + "/RiskMonitorIndicators/queryManagerInfo", param,
         SelectOptionDto.class);
 }
 /**
  * 产品代码联想下拉框
  *
  * @param param 参数map
  * @return 产品信息
  * @see
  * @since 1.0
  */
 public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> param){
     param.put("userId", SecurityUtil.getLoginUser().getUserName());
     param.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
     return restTemplate.postForList(
         ServiceApplication.REPORT_SERVICE_NAME + "/RiskMonitorIndicators/queryProdIdInfo", param,
         SelectOptionDto.class);
 }
}
