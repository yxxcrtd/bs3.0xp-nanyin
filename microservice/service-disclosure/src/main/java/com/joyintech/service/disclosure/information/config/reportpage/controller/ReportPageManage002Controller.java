/*
 * 文件名：ReportPageManage002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.service.disclosure.information.config.reportpage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.reportpage.dto.RepJobRangeDto;
import com.joyintech.service.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.service.disclosure.information.config.reportpage.service.ReportPageManage001Service;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage004Service;
import com.joyintech.tams.common.base.entity.RepScreen;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage002Controller
 * @since 1.0
 */
@RestController(value="serviceReportPageManage002Controller")
@RequestMapping("/ReportPageManage002")
public class ReportPageManage002Controller extends BaseController {
    
    @Autowired
    private ReportPageManage001Service reportPageManage001Service;
    
    @Autowired
	ReportConfirmManage004Service reportConfirmManage004Service;
    
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportPageManage002Controller.class);
//    @Autowired
//    private FtpPathService ftpPathService;
    
    @GetMapping("getRepScreenList")
    public List<RepScreen> getRepScreenList(String repId){
    	return reportPageManage001Service.getRepScreenList(repId);
    }
    
    @PostMapping("checkSave")
    public int checkSave(@RequestBody Map<String,Object> checkMap){
        
        return reportPageManage001Service.checkSave(checkMap);
        
    }
    @PostMapping("insertReportInfo")
    public void insertReportInfo(@RequestBody ReportPageManage001Dto param) throws Exception {
    	reportPageManage001Service.insertReportInfo(param);
//        return new AjaxResult("保存成功");
    }
    
    
    @PostMapping("getReportInputsNew")
    public ReportPageManage001Dto getReportInputsNew(@RequestBody Map<String, Object> pageParam) throws Exception {
    	return reportPageManage001Service.getReportInputsNew(pageParam);
//        return new AjaxResult("保存成功");
    }
    
    
    
//    
//	@SuppressWarnings("finally")
//	@RequestMapping("/getReportInputs")
//    @ResponseBody
//    public AjaxResult getReportInputs() throws Exception{
//        AjaxResult value = new AjaxResult();
//        try {
////        	List<Map<String,String>> result = reportPageManage001Service.getReportInputs(RequestUtil.getPageParam());
//        	List<Map<String,String>> result = reportPageManage001Service.getReportInputsNew(RequestUtil.getPageParam());
//        	value.setData(result);
//        }catch (Exception e) {
//            value.setMessage("查询报表模板:"+e.getMessage(), MessageType.错误消息);
////            e.printStackTrace();
//            logger.error("error:",e);
//        }finally{
//            return value; 
//        }
//    }
	/**
     * 根据作业编号查询不适用产品
     * @return
     */
    @GetMapping("/findRulePage")
    public List<RepJobRangeDto> findRulePage(String repId) {
        if (StringUtil.isNotEmpty(repId)) {
            return reportPageManage001Service.findRulePage(repId);
        }
        return new ArrayList<RepJobRangeDto>();
    }
}
