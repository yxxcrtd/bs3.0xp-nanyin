/*
 * 文件名：RiskFactor001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor001Service
 * @since 1.0
 */
@Service
public class RiskFactor001Service  extends CommonExportService<RiskFactor001Dto>
     {
   
   /**
    *
    * 分页查询
    * @author
    * @version
    * @see
    * @since 1.0
    */
    public Page<RiskFactor001Dto> selectAll( @RequestBody Map<String, Object> param){
        

        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/RiskFactor001/findPage",param,
            RiskFactor001Dto.class);
    }

 

/**
 *
 * 查询页面因子名称下拉框
 * * @author
 * @version
 * @see
 * @since 1.0
 */
    public List<SelectOptionDto> selectRiskFactorMap(@RequestBody Map<String, Object> param){
      
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/RiskFactor001/selectRiskFactorMap",param,
            SelectOptionDto.class);
    }
         /**
          *
          * 查询页面因子名称下拉框历史数据
          * * @author
          * @version
          * @see
          * @since 1.0
          */
         public List<SelectOptionDto> selectRiskFactorMap2(@RequestBody Map<String, Object> param){

             return restTemplate.postForList(
                     ServiceApplication.RISK_SERVICE_NAME +
                             "/RiskFactor001/selectRiskFactorMap2",param,
                     SelectOptionDto.class);
         }
/**
 *
 * 查询页面风险分类下拉框
 * @author
 * @version
 * @see
 * @since 1.0
 */
    public List<SelectOptionDto> selectRiskFactorMapType1(@RequestBody Map<String, Object> param){
        
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/RiskFactor001/selectRiskFactorMapType1",param,
            SelectOptionDto.class);
    }
         /**
          *
          * 查询页面因子分类下拉框
          * @author
          * @version
          * @see
          * @since 1.0
          */
    public List<SelectOptionDto> selectRiskFactorMapType2(@RequestBody Map<String, Object> param){
        
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/RiskFactor001/selectRiskFactorMapType2",param,
            SelectOptionDto.class);
    }
    /**
     *
     * 停用检出因子是否被情景使用
     * @author
     * @version
     * @see
     * @since 1.0
     */
 public List<FlowRiskFactroRManageDto> check(@RequestBody Map<String, Object> param){
        
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/FlowRiskFactroRManage/querycheck",param,
            FlowRiskFactroRManageDto.class);
    }
    /**
     *
     * 导出重写方法
     * @author
     * @version
     * @see
     * @since 1.0
     */
 @Override
 protected void buildExportParam(ExportParam exportParam) {
    
     exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/riskfactor/conf/RiskFactor001_config.xml");
 }

/**
 *
 * 导出重写方法
 * @author
 * @version
 * @see
 * @since 1.0
 */
 @Override
 public Page<RiskFactor001Dto> selectPageData(Map<String, Object> selectParam) {
     // TODO Auto-generated method stub
       return selectAll(selectParam);
 }
 
}
