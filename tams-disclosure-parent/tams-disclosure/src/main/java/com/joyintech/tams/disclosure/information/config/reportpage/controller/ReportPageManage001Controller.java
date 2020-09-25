/*
 * 文件名：ReportPageManage001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.tams.disclosure.information.config.reportpage.service.ReportPageManage001Service;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.system.constant.SystemGlobalConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage001Controller
 * @since 1.0
 */
@PageId("ReportPageManage001")
@Controller
@RequestMapping("/disclosure/information/reportpage/ReportPageManage001")
public class ReportPageManage001Controller extends BaseController {
    
    @Autowired
    private ReportPageManage001Service reportPageManage001Service;
    
    @Autowired
    private FtpPathService ftpPathService;
    
    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, SystemDictCode.机构类型);//读的是字典的内容
        model.addAttribute("REPORT_NAME",JsonUtils.objectArray2Json(this.getReportNameList().toArray()));
        model.addAttribute("ADMINISTRATIVE_TYPE1",JsonUtils.objectArray2Json(01,02));
        //model.addAttribute("pageList", JsonUtils.objectArray2Json(reportPageManage001Service.getPageName(null).toArray()));
        model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
        return super.init(model);
    }
    //查询报表名称
    private List<SelectOptionDto> getReportNameList() {
		
    	return reportPageManage001Service.getReportInfo();
	}
	@ResponseBody
    @RequestMapping("getPageName")
    public List<SuggestDto> getPageName(String param){
        return reportPageManage001Service.getPageName(param);
        
    }
    @ResponseBody
    @RequestMapping("findPage")
    public List<ReportPageManage001Dto> findPage(Map<String, Object> param){
        return reportPageManage001Service.findPage(RequestUtil.getPageParam());
        
    }
   
    @ResponseBody
    @RequestMapping("effitive")
    public AjaxResult effitive(@RequestBody Map<String,Object> param){
        reportPageManage001Service.updateReportStatus(param);
        
        return new AjaxResult("保存成功");
        
    }
    @ResponseBody
    @RequestMapping("deleteReport")
    public AjaxResult deleteReport(@RequestBody Map<String,Object> param){
        reportPageManage001Service.deleteReport(param);
        return new AjaxResult("删除成功");
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
        String exportFileName = this.getPageId();
		com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if(null != page) {
            exportFileName = page.getName()
                             + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                             + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                             + FileNameUtil.getFileExtName(outFile);
        }
		return ExportUtil.export(exportFileName, outFile, true);
    }
}
