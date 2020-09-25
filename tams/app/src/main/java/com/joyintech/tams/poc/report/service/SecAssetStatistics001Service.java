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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.report.dto.SecAssetStatistics001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil; 
import net.sf.jxls.transformer.XLSTransformer;

/**
 * 债券结构统计表Service
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年10月30日
 * @see SecAssetStatistics001Service
 * @since 1.0
 */
@Service
public class SecAssetStatistics001Service extends CommonExportService<SecAssetStatistics001Dto>{
    
  private Logger logger = LoggerFactory.getLogger(getClass());
    
 
 
 public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
     
	 String date = (String)selectParam.get("searchDate");
	 String lastMonth = searchLastMonth(date);//上月最后一天
	 String lastYear = searchLastYear(date);//去年年末日期
	 String lastYearQuarter = searchLastYearSameDay(date);//去年同期
	 
	 selectParam.put("lastMonth", lastMonth);
	 selectParam.put("lastYear", lastYear);
	 selectParam.put("lastYearQuarter", lastYearQuarter);
	 
	 Map<String,Object> data = new HashMap<String,Object>();
	 
    /* @SuppressWarnings("unchecked")
     Map<String,Object> data = restTemplate.postForObject(
         ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage",selectParam,
                     Map.class);*/
	 
     String exceltemplate = "SecAssetStatistics";
   
     //查询债券主体评级相关信息
     List<SecAssetStatistics001Dto> list1 = restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage",
             selectParam, SecAssetStatistics001Dto.class);

     //查询债券久期相关信息
     List<SecAssetStatistics001Dto> list2 = restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage2",
             selectParam, SecAssetStatistics001Dto.class);

     //查询债券分类相关信息
     List<SecAssetStatistics001Dto> list3 = restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage3",
             selectParam, SecAssetStatistics001Dto.class);
     
     //查询债券发行方式情况相关信息
     List<SecAssetStatistics001Dto> list4 = restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage4",
             selectParam, SecAssetStatistics001Dto.class);

     //查询账户浮盈浮亏信息
     List<SecAssetStatistics001Dto> list5 = restTemplate.postForList(
             ServiceApplication.REPORT_SERVICE_NAME + "/SecAssetStatistics/selectForPage5",
             selectParam, SecAssetStatistics001Dto.class);
     
     List<SecAssetStatistics001Dto> listone0 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listone1 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listone2 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listone3 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listone4 = new ArrayList<SecAssetStatistics001Dto>();
     
     listone0.add(list1.get(0));
     listone1.add(list1.get(1));
     listone2.add(list2.get(2));
     listone3.add(list2.get(3));
     listone4.add(list2.get(4));
     
     data.put("listone0", listone0);
     data.put("listone1", listone1);
     data.put("listone2", listone2);
     data.put("listone3", listone3);
     data.put("listone4", listone4);
     
     List<SecAssetStatistics001Dto> listtwo0 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listtwo1 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listtwo2 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listtwo3 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listtwo4 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listtwo5 = new ArrayList<SecAssetStatistics001Dto>();

     listtwo0.add(list2.get(0));
     listtwo1.add(list2.get(1));
     listtwo2.add(list2.get(2));
     listtwo3.add(list2.get(3));
     listtwo4.add(list2.get(4));
     listtwo5.add(list2.get(5));
     
     data.put("listtwo0", listtwo0);
     data.put("listtwo1", listtwo1);
     data.put("listtwo2", listtwo2);
     data.put("listtwo3", listtwo3);
     data.put("listtwo4", listtwo4);
     data.put("listtwo5", listtwo5);
     
     List<SecAssetStatistics001Dto> listthree0 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree1 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree2 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree3 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree4 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree5 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree6 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree7 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree8 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree9 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree10 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree11 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree12 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree13 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree14 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listthree15 = new ArrayList<SecAssetStatistics001Dto>();
     
     listthree0.add(list3.get(0));
     listthree1.add(list3.get(1));
     listthree2.add(list3.get(2));
     listthree3.add(list3.get(3));
     listthree4.add(list3.get(4));
     listthree5.add(list3.get(5));
     listthree6.add(list3.get(6));
     listthree7.add(list3.get(7));
     listthree8.add(list3.get(8));
     listthree9.add(list3.get(9));
     listthree10.add(list3.get(10));
     listthree11.add(list3.get(11));
     listthree12.add(list3.get(12));
     listthree13.add(list3.get(13));
     listthree14.add(list3.get(14));
     listthree15.add(list3.get(15));
     
     data.put("listthree0", listthree0);
     data.put("listthree1", listthree1);
     data.put("listthree2", listthree2);
     data.put("listthree3", listthree3);
     data.put("listthree4", listthree4);
     data.put("listthree5", listthree5);
     data.put("listthree6", listthree6);
     data.put("listthree7", listthree7);
     data.put("listthree8", listthree8);
     data.put("listthree9", listthree9);
     data.put("listthree10", listthree10);
     data.put("listthree11", listthree11);
     data.put("listthree12", listthree12);
     data.put("listthree13", listthree13);
     data.put("listthree14", listthree14);
     data.put("listthree15", listthree15);
     
     List<SecAssetStatistics001Dto> listfour0 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfour1 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfour2 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfour3 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfour4 = new ArrayList<SecAssetStatistics001Dto>();
     
     listfour0.add(list4.get(0));
     listfour1.add(list4.get(1));
     listfour2.add(list4.get(2));
     listfour3.add(list4.get(3));
     listfour4.add(list4.get(4));
     
     data.put("listfour0", listfour0);
     data.put("listfour1", listfour1);
     data.put("listfour2", listfour2);
     data.put("listfour3", listfour3);
     data.put("listfour4", listfour4);
     
     List<SecAssetStatistics001Dto> listfive0 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfive1 = new ArrayList<SecAssetStatistics001Dto>();
     List<SecAssetStatistics001Dto> listfive2 = new ArrayList<SecAssetStatistics001Dto>();
     
     listfive0.add(list5.get(0));
     listfive1.add(list5.get(1));
     listfive2.add(list5.get(2));
     
     data.put("listfive0", listfive0);
     data.put("listfive1", listfive1);
     data.put("listfive2", listfive2);
     
     String fileName = "债券结构统计表"+"("+date+")"+".xls";
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
 
 public  String searchLastMonth(String date){
	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	 Date datesearch = null;
	try {
		datesearch = format.parse(date);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 Calendar c= Calendar.getInstance();
	 c.setTime(datesearch);
	 c.set(Calendar.DAY_OF_MONTH, 0);
	 String gtime = format.format(c.getTime()); //上月最后一天
	 return gtime;
 }
 
 public  String searchLastYear(String date){
	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	 Date datesearch = null;
	try {
		datesearch = format.parse(date);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 Calendar c= Calendar.getInstance();
	 c.setTime(datesearch);
	 c.add(Calendar.YEAR, -1);
	 c.set(Calendar.MONTH,12);
	 c.set(Calendar.DAY_OF_MONTH, 0);
	 String gtime = format.format(c.getTime()); //上一年年末日期
	 return gtime;
	 
 }
 
 public  String searchLastYearQuarter(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateserach = null;
		try {
			dateserach = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dateserach);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		if (currentMonth >= 1 && currentMonth <= 3)
			c.set(Calendar.MONTH, 3);
		else if (currentMonth >= 4 && currentMonth <= 6)
			c.set(Calendar.MONTH, 6);
		else if (currentMonth >= 7 && currentMonth <= 9)
			c.set(Calendar.MONTH, 9);
		else if (currentMonth >= 10 && currentMonth <= 12)
			c.set(Calendar.MONTH, 12);
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		String gtime = format.format(c.getTime()); // 上一年同季度
		return gtime;
 }
 
 public  String searchLastYearSameDay(String date){
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     Date dateserach = null;
     try {
         dateserach = format.parse(date);
     } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
     Calendar c = Calendar.getInstance();
     c.setTime(dateserach);
     c.add(Calendar.YEAR, -1);
     String gtime = format.format(c.getTime()); // 上一年同期
     return gtime;
 }
}
