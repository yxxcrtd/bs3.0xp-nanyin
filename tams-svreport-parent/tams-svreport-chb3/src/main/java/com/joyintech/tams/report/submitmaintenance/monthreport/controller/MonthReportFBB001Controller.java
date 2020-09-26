package com.joyintech.tams.report.submitmaintenance.monthreport.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.monthreport.dto.MonthReport001Dto;
import com.joyintech.tams.report.submitmaintenance.monthreport.service.MonthReport001Service;

@Controller
@PageId("MonthReportFBB001")
@RequestMapping("/svreport/submitmaintenance/monthreport/MonthReportFBB001")
public class MonthReportFBB001Controller extends BaseController{
	
	@Autowired
    private MonthReport001Service monthReport001Service;
	 
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }
	
//	 /**
//    *
//    * 查询功能
//    * <功能详细描述>
//    * @return 存储了查询结果的list
//    * @throws Exception 异常
//    * @see
//    * @since 1.0
//    */
//   @ResponseBody
//   @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
//   public Page<MonthReport001Dto> search()
//       throws Exception{
//       Map<String, Object> param = RequestUtil.getPageParam();
//       return monthReport001Service.searchForPage(param);
//   }
    
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
       return monthReport001Service.exportMonthReport(this.getPageId(),pageParam,"非保本银行月度统计表","MonthReportFBB001");
   }
   
   @ResponseBody
   @RequestMapping(value = "recal")
   public AjaxResult recal() {
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("export_method_flg", "recal");
       monthReport001Service.recalMonthReport(this.getPageId(),pageParam,"非保本银行月度统计表","MonthReportFBB001");
       return new AjaxResult("重新计算成功！");
   }
 
   
   @RequestMapping(value = "getSubmitstatus")
   @ResponseBody
   public Map<String,Object> getSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "FBB");
	   map = monthReport001Service.getSubmitstatus(pageParam);
       return map;
   }
   

   @RequestMapping(value = "manualSubmitstatus")
   @ResponseBody
   public Map<String,Object> manualSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "FBB");
       pageParam.put("excelenname", "MonthReportFBB001");
	   map = monthReport001Service.manualSubmitstatus(pageParam);
       return map;
   }
   
   @RequestMapping(value = "cancelSubmitstatus")
   @ResponseBody
   public Map<String,Object> cancelSubmitstatus() {
	   Map<String,Object> map = new HashMap<String,Object>();
       Map<String, Object> pageParam = RequestUtil.getPageParam();
       pageParam.put("reporttype", "FBB");
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
