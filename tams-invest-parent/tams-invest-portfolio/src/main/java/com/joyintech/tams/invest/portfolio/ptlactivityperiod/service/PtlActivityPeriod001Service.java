/*
 * 文件名：PtlActivityPeriod001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 活期结息
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Service
 * @since 1.0
 */
@Service
public class PtlActivityPeriod001Service extends CommonExportService<PtlActivityPeriod001Dto> 
        implements BaseProcessService<PtlActivityPeriod001Dto> {
    /**
   * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
   * 
   * @param pageParam pageParam
   * @return Page类 Page
   * @throws Exception 异常信息
   */
  public Page<PtlActivityPeriod001Dto> selectForPage(Map<String, Object> pageParam){
      //活期结息有效数据查询不到 yjm  start 20190812 
     /* if(pageParam.get("tradeStatus") != null && DictCodeListExt1.交易流程状态_有效.getValue().equals(pageParam.get("tradeStatus").toString())){
          pageParam.replace("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
      }*/
      //活期结息有效数据查询不到 yjm  end 20190812
      pageParam.put("userId",SecurityUtil.getLoginUser().getUserName());
      if (StringUtil.isNotEmpty(pageParam.get("acctNo"))) {
    	  pageParam.put("acctNoList", StringUtil.splitToList(null, pageParam.get("acctNo").toString()));
      }
      Page<PtlActivityPeriod001Dto> page = restTemplate.postForPage(
          ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/findPage",
          pageParam, PtlActivityPeriod001Dto.class);
      return page;
  }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlActivityPeriod001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/doCompelete" , entity,
            Integer.class);
    }
    /**
     * 模糊查询组合代码
     * @param param 模糊查询参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getPortfolioId",map, SelectOptionDto.class);
    }
    /**
     * 组合联想文本框
     * @param map 请求参数 
     * @return 请求参数
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioIdSug(Map<String,Object> map){
        if(map.get("initValue") == null && map.get("value") == null){
            map.put("userId", SecurityUtil.getLoginUser().getUserName());
        }
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getPortfolioIdSug",map, SuggestDto.class);
    }
    
    
    /**
     * 获取账号信息
     * @param map 查询参数
     * @return 账号集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiAcctNo(Map<String,Object> map){
        Page<SelectOptionDto> Page = restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getBusiAcctNo",map, SelectOptionDto.class);
        return Page;
    }
    
    /**
     * 获取业务编码
     * @param param 模糊查询参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBusiNo(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getBusiNo",map, SuggestDto.class);
        
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlactivityperiod/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlActivityPeriod001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 设置单元格值
     * <p>
     * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
     * </p>
     * 
     * @param beanName sheet对应的JavaBean
     * @param cell 单元格
     * @param propName 属性名
     * @param value 单元格的值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if("vdate".equals(propName) || "mdate".equals(propName)){
            cell.setCellValue(DateUtil.format((Date)value, "yyyy-MM-dd"));
        }
    }
}
