/*
 * 文件名：ReportPageManage001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.disclosure.information.config.reportpage.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.service.disclosure.information.config.reportpage.service.ReportPageManage001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage001Controller
 * @since 1.0
 */
@RestController(value="serviceReportPageManage001Controller")
@RequestMapping("/ReportPageManage001")
public class ReportPageManage001Controller extends BaseController {
    
    @Autowired
    private ReportPageManage001Service reportPageManage001Service;
    
    @Autowired
    private FtpPathService ftpPathService;
    
//    @Override
//    public String init(Model model){
//        this.setSelectOptionToModel(model, SystemDictCode.机构类型);//读的是字典的内容
//        model.addAttribute("REPORT_NAME",JsonUtils.objectArray2Json(this.getReportNameList().toArray()));
//        model.addAttribute("ADMINISTRATIVE_TYPE1",JsonUtils.objectArray2Json(01,02));
//        //model.addAttribute("pageList", JsonUtils.objectArray2Json(reportPageManage001Service.getPageName(null).toArray()));
//        model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
//        return super.init(model);
//    }
    //查询报表名称
//    private List<SelectOptionDto> getReportNameList() {
//		
//    	return reportPageManage001Service.getReportInfo();
//	}
    @PostMapping("/getReportInfo")
  public  List<SelectOptionDto> getReportInfo() {
		
  	return reportPageManage001Service.getReportInfo();
	}
    
    
    
	@GetMapping("getPageName")
    public List<SuggestDto> getPageName(String param){
        return reportPageManage001Service.getPageName(param);
        
    }
	 @PostMapping("/findPage")
    public List<ReportPageManage001Dto> findPage(@RequestBody Map<String, Object> param){
        return reportPageManage001Service.findPage(param);
        
    }
   
    @PostMapping("/effitive")
    public void effitive(@RequestBody Map<String,Object> param){
        reportPageManage001Service.updateReportStatus(param);
        
//        return new AjaxResult("保存成功");
        
    }
    @PostMapping("deleteReport")
    public void deleteReport(@RequestBody Map<String,Object> param){
        reportPageManage001Service.deleteReport(param);
//        return new AjaxResult("删除成功");
    }
    /**
     * 导出excel
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export(Map<String, Object> param) throws Exception{
    	String datas = this.getParam("datas");
    	List<ReportPageManage001Dto> dtoList;
        if (datas == null) {
        	dtoList = reportPageManage001Service.findPage(param);
        } else {
       		dtoList = JsonUtils.json2List(datas, ReportPageManage001Dto.class);
        }
        
        File outFile = reportPageManage001Service.makeFile(
            "/com/joyintech/tams/disclosure/information/config/reportpage/config/reportPage_export.xml", dtoList);
        return ExportUtil.export(
            "报表页面管理" + "("
                                 + DateUtil.format(new Date()) + ")"
                                 + "." + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
