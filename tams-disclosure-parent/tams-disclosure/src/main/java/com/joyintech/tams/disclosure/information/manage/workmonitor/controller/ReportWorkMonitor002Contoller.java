/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmonitor.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor002Dto;
import com.joyintech.tams.disclosure.information.manage.workmonitor.service.ReportWorkMonitor001Service;
import com.joyintech.tams.disclosure.information.manage.workmonitor.service.ReportWorkMonitor002Service;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author guozhongbao
 * @version 1.0 2018年12月5日
 * @see ReportWorkMonitor002Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportWorkMonitor002")
@RequestMapping("/disclosure/information/manage/workmonitor/ReportWorkMonitor002")
public class ReportWorkMonitor002Contoller extends BaseController {
    
	@Autowired
	private ReportWorkMonitor002Service reportWorkMonitor002Service;
	
	@Autowired
    private ReportWorkMonitor001Service reportWorkMonitor001Service;
	
	@Autowired
    private FtpPathService ftpPathService;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	model.addAttribute("jobId", this.getParam("jobId"));
    	model.addAttribute("JOBID_LIST",JsonUtils.objectArray2Json(this.getJobidList().toArray()));
    	
        model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
        this.setSelectOptionToModel(model,DictCode.执行结果);
        String execDate = this.getParam("execDate");
        String startExecDate = this.getParam("startExecDate");
        String resultDate = null;
        if(StringUtil.isNotEmpty(startExecDate) && !StringUtil.equals("", startExecDate)) {
        	resultDate = DateUtil.format(DateUtil.parse(startExecDate));
        }else {
        	resultDate = execDate;
        }
        model.addAttribute("execDate", resultDate);
        
        // 首页跳转使用
        String confromPage = this.getParam("confromPage");
        if(StringUtil.equals("01", confromPage)){
        	model.addAttribute("confromPage", confromPage);
//        	model.addAttribute("searchDate", this.getParam("searchDate"));
        	model.addAttribute("searchDateStart", this.getParam("searchDateStart"));
        	model.addAttribute("searchDateEnd", this.getParam("searchDateEnd"));
        	model.addAttribute("execDate", this.getParam("execDate"));//执行日期
        	model.addAttribute("execResult", DictCodeListExt2.执行结果_失败.getValue());
        	if(!StringUtil.equals("firstCol", this.getParam("repId"))) {
        		model.addAttribute("repType", this.getParam("repId"));
        	}
        }
        
        return super.init(model);
    }
    
    /**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect()
        throws Exception {
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
        return prdInfoSelect;
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ReportWorkMonitor002Dto> findPage() throws Exception{
        Page<ReportWorkMonitor002Dto> pageList = reportWorkMonitor002Service.findPage(RequestUtil.getPageParam());
        pageList.getRoot().forEach(dto -> {
        	dto.setExecStatusName(SysDataUtil.getNameFromDataDict(DictCode.执行结果, dto.getExecStatus()));
        	dto.setExecResult(SysDataUtil.getNameFromDataDict(DictCode.执行结果, dto.getExecResult()));
        });
        return pageList;
    }
    
    @RequestMapping("/getJobidList")
    @ResponseBody
    public List<SelectOptionDto> getJobidList() {
         Map<String,Object> map=new HashMap<String,Object>();
         return reportWorkMonitor001Service.getJobidList(map);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
    	 String datas = this.getParam("datas");
         List<ReportWorkMonitor002Dto> dtoList;
         if (datas == null) {
        	 dtoList = this.findPage().getRoot();
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ReportWorkMonitor002Dto.class);
         }
        File outFile = reportWorkMonitor002Service.makeFile("/com/joyintech/tams/disclosure/information/manage/workmonitor/conf/export_reportWorkMonitor002.xml",dtoList);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
