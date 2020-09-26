/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitexamination.delayverification.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.service.SvrBaseCommonService;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification001Dto;
import com.joyintech.tams.report.submitexamination.delayverification.dto.DelayVerification002Dto;
import com.joyintech.tams.report.submitexamination.delayverification.service.DelayVerification001Service;
import com.joyintech.tams.report.submitexamination.delayverification.service.DelayVerificationMService;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 延时报送检查Controller
 * @author dingzhipeng
 * @version 1.0 2017年9月25日
 * @see DelayVerification001Controller
 * @since 1.0
 */
@Controller
@PageId("DelayVerification001")
@RequestMapping("/svreport/submitexamination/delayverification/DelayVerification001")
public class DelayVerification001Controller extends BaseController{
    /**
     * 延时报送检查service
     */
    @Autowired
    private DelayVerificationMService delayVerificationMService;
    /**
     * 延时报送检查service
     */
    @Autowired
    private DelayVerification001Service delayVerification001Service;
    /**
     * 共通Service
     */
    @Autowired
    private SvrBaseCommonService svrBaseCommonService;
    /**
     * 报送前检查service
     */
//    @Autowired
//    private CheckReport001Service checkReport001Service;
    /**
     * 画面的初始化的函数
     * @param model model
     * @return String
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Date dateStart=DateUtil.getFirstDay(svrBaseCommonService.getSysDate());
        Date dateEnd=DateUtil.getLastDay(svrBaseCommonService.getSysDate());
        model.addAttribute("DATE_START",DateUtil.format(dateStart));
        model.addAttribute("DATE_END",DateUtil.format(dateEnd));
        String needTab = SvrDataUtil.getSysParam("SVR_DELAY_SUBMIT_TAB");
        model.addAttribute("tabflag",needTab);
        String myOrgCode=CommonUtil.getOrgCode();
        if(StringUtil.isNotEmpty(myOrgCode)){
            model.addAttribute("MY_AGENCY_CODE",myOrgCode);
        }
        return super.init(model);
    }
    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchDate")
    public Map<String,DelayVerification001Dto> search(){
        return delayVerificationMService.searchDate(RequestUtil.getPageParam(),"CHB");
    }
    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchDatePbc")
    public Map<String,DelayVerification001Dto> searchPbc(){
        return delayVerificationMService.searchDate(RequestUtil.getPageParam(),"PBC");
    }
    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTable")
    public Page<DelayVerification002Dto> searchTable(){
        return delayVerificationMService.searchTable(RequestUtil.getPageParam());
    }
    /**
     * 查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/readConfig")
    public Map<String,String[]>  readConfig(String indexTotal) {
        Map<String,String[]> resultMap=new HashMap<String,String[]>();
//        RequestUtil.getCurrentRequest().getServletContext().getRealPath("/WEB-INF/");
//        File f = new File("checkReportColor.xml");   
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(f);
//        NodeList nl = doc.getElementsByTagName("VALUE");
//        for (int i = 0; i < nl.getLength(); i++ ) {
//            String code=doc.getElementsByTagName("CODE").item(i).getFirstChild().getNodeValue();
//            String value=doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue();
//            resultMap.put(code, value);
//        }
        resultMap=delayVerification001Service.readConfig(indexTotal);
        return resultMap;
    }
    
    /**
     * 查询列表CHB
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchRegistrationsCHB")
    public Page<DelayVerification002Dto>  searchCHBRegistrations() {
        return delayVerification001Service.selectCHBAndPBC("CHB",RequestUtil.getPageParam());
    }   
    /**
     * 查询列表PBC
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchRegistrationsPBC")
    public Page<DelayVerification002Dto>  searchPBCRegistrations() {
    	return delayVerification001Service.selectCHBAndPBC("PBC",RequestUtil.getPageParam());
    }
}
