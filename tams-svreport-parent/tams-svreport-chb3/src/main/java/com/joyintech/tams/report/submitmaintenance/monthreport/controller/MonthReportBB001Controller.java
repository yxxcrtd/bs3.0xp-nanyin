package com.joyintech.tams.report.submitmaintenance.monthreport.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.submitmaintenance.monthreport.service.MonthReport001Service;

@Controller
@PageId("MonthReportBB001")
@RequestMapping("/svreport/submitmaintenance/monthreport/MonthReportBB001")
public class MonthReportBB001Controller extends BaseController {
	
	@Autowired
    private MonthReport001Service monthReport001Service;
	
	
	/**
    *
    * 导出功能
    * <功能详细描述>
    * @return 结果
    * @see
    * @since 1.0
    */
   @RequestMapping(value = ACTION_EXPORT)
   public ResponseEntity<byte[]> export() {
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("export_method_flg", "export");
       return monthReport001Service.exportMonthReport(this.getPageId(),pageParam,"保本银行月度统计表","MonthReportBB001");
   }
   
   @ResponseBody
   @RequestMapping(value = "recal")
   public AjaxResult recal() {
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("export_method_flg", "recal");
       monthReport001Service.recalMonthReport(this.getPageId(),pageParam,"保本银行月度统计表","MonthReportBB001");
       return new AjaxResult("重新计算成功！");
   }

   @RequestMapping(value = "getSubmitstatus")
   @ResponseBody
   public Map<String,Object> getSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "BB");
	   map = monthReport001Service.getSubmitstatus(pageParam);
       return map;
   }
   
   @RequestMapping(value = "manualSubmitstatus")
   @ResponseBody
   public Map<String,Object> manualSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "BB");
       pageParam.put("excelenname", "MonthReportBB001");
	   map = monthReport001Service.manualSubmitstatus(pageParam);
       return map;
   }
   
   @RequestMapping(value = "cancelSubmitstatus")
   @ResponseBody
   public Map<String,Object> cancelSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "BB");
	   map = monthReport001Service.cancelSubmitstatus(pageParam);
       return map;
   }
   
   
   @ResponseBody
   @RequestMapping(value = "showInfo")
   public AjaxResult showInfo() {
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       String info = pageParam.get("info")==null?"":pageParam.get("info").toString();
       return new AjaxResult(info);
   }
}
