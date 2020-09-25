/*
 * 文件名：ReportPageManage002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.RepJobRangeDto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.tams.disclosure.information.config.reportpage.service.ReportPageManage001Service;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage004Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage002Controller
 * @since 1.0
 */
@PageId("ReportPageManage002")
@Controller
@RequestMapping("/disclosure/information/reportpage/ReportPageManage002")
public class ReportPageManage002Controller extends BaseController {
    
    @Autowired
    private ReportPageManage001Service reportPageManage001Service;
    
    @Autowired
	ReportConfirmManage004Service reportConfirmManage004Service;
    
    @Autowired
    private FtpPathService ftpPathService;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportPageManage002Controller.class);
    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.作业规则1);
        this.setSelectOptionToModel(model, DictCode.作业规则2);
        this.setSelectOptionToModel(model, DictCode.作业规则3);
        this.setSelectOptionToModel(model, DictCode.收益类型);
        this.setSelectOptionToModel(model, DictCode.募集方式);
        this.setSelectOptionToModel(model, DictCode.发行方式);
        this.setSelectOptionToModel(model, DictCode.发行状态);
        this.setSelectOptionToModel(model, DictCode.开放模式);
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DisclosureDictCode.产品类型);
        this.setSelectOptionToModel(model, DisclosureDictCode.理财类型);
        this.setSelectOptionToModel(model, DisclosureDictCode.产品期限类型);
        model.addAttribute("templatePath",getParam("templatePath"));
        model.addAttribute("templateId",getParam("templateId"));
        model.addAttribute("templateName",getParam("templateName"));
        model.addAttribute("reportType",getParam("reportType"));
        String reportName = getParam("reportName");
        model.addAttribute("reportName", reportName);
        String repId = getParam("repId");
        model.addAttribute("repId", repId);
        if(StringUtil.isNotEmpty(repId)){
        List<RepScreen> RepScreenList = reportPageManage001Service.getRepScreenList(repId);
        model.addAttribute("RepScreenList",RepScreenList);
        }
        List<SelectOptionDto> effective = new ArrayList<>();
        SelectOptionDto effective1 = new SelectOptionDto();
        effective1.setId("Y");
        effective1.setText("启用");
        SelectOptionDto effective2 = new SelectOptionDto();
        effective2.setId("N");
        effective2.setText("禁用");
        effective.add(effective1);
        effective.add(effective2);        
        model.addAttribute("effective",JsonUtils.objectArray2Json(effective.toArray()));
        model.addAttribute("ADMINISTRATIVE_TYPE1",JsonUtils.objectArray2Json("01","02"));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
        model.addAttribute(ReportGlobalConstant.REPORT_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
        model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));
        List<String> dictType = new ArrayList<>();
        for(SystemDictCode type:SystemDictCode.values()){
            dictType.add(type.toString());
        }
        model.addAttribute("dictCodeParam", JsonUtils.objectArray2Json(dictType.toArray()));
        // 获取报表类别        
        model.addAttribute("reportStyle",getParam("reportStyle"));
        model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
        return "disclosure/information/reportpage/ReportPageManage002";
    }
    @ResponseBody
    @RequestMapping("saveCheck")
    public AjaxResult saveCheck(@RequestBody Map<String,Object> checkMap){
        AjaxResult message = new AjaxResult();
        
        int name = reportPageManage001Service.checkSave(checkMap);
        if(name > 0){
        	message=new AjaxResult("报表名称重复,请调整报表名称", MessageType.错误消息);
        }
        return message;
        
    }
    @ResponseBody   
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult doAdd(@RequestBody ReportPageManage001Dto param) throws Exception {
    	reportPageManage001Service.insertReportInfo(param);
        return new AjaxResult("保存成功");
    }
    
	@SuppressWarnings("finally")
	@RequestMapping("/getReportInputs")
    @ResponseBody
    public AjaxResult getReportInputs() throws Exception{
        AjaxResult value = new AjaxResult();
        try {
//        	List<Map<String,String>> result = reportPageManage001Service.getReportInputs(RequestUtil.getPageParam());
        	List<Map<String,String>> result = reportPageManage001Service.getReportInputsNew(RequestUtil.getPageParam());
        	value.setData(result);
        }catch (Exception e) {
            value.setMessage("查询报表模板:"+e.getMessage(), MessageType.错误消息);
//            e.printStackTrace();
            logger.error("error:",e);
        }finally{
            //return value;
        }
        return value;
    }
	/**
     * 根据作业编号查询不适用产品
     * @return
     */
    @RequestMapping("/findRulePage")
    @ResponseBody
    public List<RepJobRangeDto> findRulePage() {
        if (StringUtil.isNotEmpty(this.getParam("repId"))) {
            return reportPageManage001Service.findRulePage(this.getParam("repId"));
        }
        return new ArrayList<RepJobRangeDto>();
    }
}
