/*
 * 文件名：RiskFactor003Service01.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenyunlong
 * 修改时间：1.0 2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.RequestUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
/**
 * 历史数据查询service
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月30日
 * @see RiskFactor003Service01
 * @since 1.0
 */
@Service
public class RiskFactor003Service01  extends CommonExportService<RiskFactor003Dto>
     {
   
   /**
    *
    * 历史数据查询方法
    * @author
    * @version
    * @see
    * @since 1.0
    */
    public Page<RiskFactor003Dto> selectAll( @RequestBody Map<String, Object> param){
        

        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/RiskFactor003/findPage",param,
            RiskFactor003Dto.class);
    }

         /**
          *
          * 历史数据导出重写方法
          * @author
          * @version
          * @see
          * @since 1.0
          */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/riskfactor/conf/RiskFactor003_config.xml").dataSource(DataSourceType.MASERT);
    }

         /**
          *
          * 历史数据导出重写方法
          * @author
          * @version
          * @see
          * @since 1.0
          */
    @Override
    public Page<RiskFactor003Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub

//            selectParam.put("investtype1", null);
//            selectParam.put("investtype2", null);
//            selectParam.put("FTRVALUESTRUCT", null);
//            selectParam.put("twoClass", selectParam.get("twoClass"));
//            selectParam.put("FTRSTATUS", null);

          return selectAll(selectParam);
    }

}
