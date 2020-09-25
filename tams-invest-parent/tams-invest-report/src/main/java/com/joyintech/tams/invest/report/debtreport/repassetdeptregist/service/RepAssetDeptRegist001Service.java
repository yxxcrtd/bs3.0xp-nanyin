/*
 * 文件名：repAssetDept001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repassetdeptregist.service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.debtreport.repassetdeptregist.dto.RepAssetDeptRegistDto;

/**
 * 资产要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月29日
 * @see RepAssetDeptRegist001Service
 * @since 1.0
 */
@Service
public class RepAssetDeptRegist001Service extends CommonExportService<RepAssetDeptRegistDto>{
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepAssetDeptRegistDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepAssetDeptRegist/findPage",
            map, RepAssetDeptRegistDto.class);
    }
    
    /**
     * 获取资产代码集合
     * @param map 请求参数
     * @return 资产代码集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepAssetCode(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepAssetDeptRegist/getRepAssetCode",
            map, SelectOptionDto.class);
    }
    
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<RepAssetDeptRegistDto> searchPage(Map<String,Object> map){
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepAssetDeptRegist/findPage",
            map, RepAssetDeptRegistDto.class);
    }
    
    /**
    * 导出
    */
   @Override
   protected void buildExportParam(ExportParam exportParam) {
       String export_conf = "export_conf";
       Map<String,Object> map = exportParam.getSelectParam();
       if(map.get("tableNum") != null){
           export_conf = export_conf + map.get("tableNum").toString();
       }
        
       /*exportParam.config(
           "/com/joyintech/tams/invest/report/debtreport/repassetdeptregist/conf/"+export_conf+".xml").dataSource(
               DataSourceType.MASERT);*/
       exportParam.config(
               "/com/joyintech/tams/invest/report/debtreport/repassetdeptregist/conf/export_conf.xml").dataSource(
                   DataSourceType.MASERT);
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public Page<RepAssetDeptRegistDto> selectPageData(Map<String, Object> map) {
       String primaryKey = (String)map.get("primaryKey");
       String[] primaryKeyArray = null;
       if(StringUtil.isNotEmpty(primaryKey)){
           primaryKeyArray = primaryKey.split(",");
           map.replace("primaryKey", Arrays.asList(primaryKeyArray));
       }else{
           map.replace("primaryKey", null);
       }
       if(map.get("tableNum") != null){
           map.replace("tableNum", "0"+map.get("tableNum").toString());
       }
       return this.findPage(map);
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
       String[] dateArr = {"repStatusDate","repVdate","repMdate","repVdate3","repMdate3",
    		   "repVdate4","repMdate4","repVdate5","repMdate5","repVdate8","repMdate8",
    		   "repVdate9","repMdate9","repVdate11","repMdate11","repVdate15","repMdate15",
    		   "repSetDate","repEndDate","repValueDate",
                           "repExpiringDate","repInvestStartdate","repInvestEnddate"};
       for(int i = 0; i<dateArr.length; i++){
           if(StringUtil.equals(dateArr[i], propName)){
               cell.setCellValue(DateUtil.format((Date)value));
           }
       }
   }
    
}
