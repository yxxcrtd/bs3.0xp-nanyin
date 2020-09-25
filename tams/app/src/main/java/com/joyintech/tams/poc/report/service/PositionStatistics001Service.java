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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.report.dto.PositionStatistics001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil; 
import net.sf.jxls.transformer.XLSTransformer;

/**
 * 头寸统计表Service
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatistics001Service
 * @since 1.0
 */
@Service
public class PositionStatistics001Service extends CommonExportService<PositionStatistics001Dto>{
    
  private Logger logger = LoggerFactory.getLogger(getClass());
    
  /**
   * 根据条件获取表格信息
   * 
   * @param params 筛选条件
   * @return 表格信息
   * @throws Exception 
   */
 public Page<PositionStatistics001Dto> findPage(Map<String, Object> params) throws Exception {  
      if (StringUtil.isNotEmpty(params.get("manager"))) {
         params.put("manager", StringUtil.splitToList(null, params.get("manager").toString()));
      }
      List<PositionStatistics001Dto> list =  restTemplate.postForList(
          ServiceApplication.REPORT_SERVICE_NAME + "/PositionStatistics/selectForPage", params,
          PositionStatistics001Dto.class);
      Page<PositionStatistics001Dto> page = new Page<>(list); 
      return page; 
 }
 
 public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
	 Map<String, Object> data = new HashMap<String, Object>();
	 String date = (String)selectParam.get("searchDate");
	 //理财经理
     if (StringUtil.isNotEmpty(selectParam.get("manager"))) {
         selectParam.put("manager", StringUtil.splitToList(null, selectParam.get("manager").toString()));
     }
     @SuppressWarnings("unchecked")
     String exceltemplate = "PositionStatistics";
     
     String fileName = "头寸统计表"+"("+date+")"+".xls";
     List<PositionStatistics001Dto> list =  restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/PositionStatistics/selectForPage", selectParam,
             PositionStatistics001Dto.class);
     data.put("list", list);
     try {
         return exportReportExcel(data,exceltemplate,fileName);
     }
     catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
     return null;
 }
 
 public ResponseEntity<byte[]>  exportReportExcel(Map<String,Object> data,String exceltemplate,String fileName) throws IOException {
     File depositfile = TisUtil.createFile(data,exceltemplate,exceltemplate+".xls");
     return ExportUtil.export(fileName, depositfile, true);
 }
 
 //创建导出文件
 public File createFile(Map<Object,Object> data,String exceltemplate){
     String flieName = exceltemplate+".xlsx";
     File depositfile = null;
     FileOutputStream fos=null;
     try{          
     // 关联模板
     XLSTransformer transformer = new XLSTransformer();
     String template = FileNameUtil.getTplFile(exceltemplate + ".xlsx");
     //传放地址
     String depositfilePath = SvrDataUtil.getSysParam(
             GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
     
     depositfile = new File(depositfilePath + File.separator + flieName);
     if (!depositfile.getParentFile().exists()) {
         depositfile.getParentFile().mkdirs();
     }
     // 缓存区，一次性读取1024字节
     byte[] buffer = new byte[1024];
     String temp = depositfilePath + "ptl.xlsx";
     // 原文件
     File srcFile = new File(template);
     // 目标文件
     File destFile = new File(temp);
     InputStream fis = null;
     if (srcFile.exists()) {
         fis = new FileInputStream(srcFile);
     } else {
         fis = SecAssetStatistics001Service.class.getResourceAsStream("/tpl/" + exceltemplate + ".xlsx");
     }
      fos = new FileOutputStream(destFile);
     while (true) {
         // 实际读取的长度是 actuallyReaded,有可能小于1024
         int actuallyReaded = fis.read(buffer);
         // -1表示没有可读的内容了
         if (-1 == actuallyReaded)
             break;
         fos.write(buffer, 0, actuallyReaded);
         fos.flush();
     }
     transformer.transformXLS(temp, data, depositfilePath + File.separator +flieName);
     }catch(Exception e){
         logger.info(exceltemplate+"生成失败！");
         throw new RuntimeException(e);
     }finally{
         if(fos !=null){
             try {
                 fos.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
      return depositfile;
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
         ServiceApplication.REPORT_SERVICE_NAME + "/PositionStatistics/queryManagerInfo", param,
         SelectOptionDto.class);
 }
}
