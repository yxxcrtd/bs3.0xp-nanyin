/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.busicommon.common.service.DisValAssetBook001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service.DisValProfitQuery002Service;

/**
 * 利润表-明细列表
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("DisValProfitQuery002")
@RequestMapping("/disclosure/accounadjustmanage/valprofitquery/DisValProfitQuery002")
public class DisValProfitQuery002Controller extends BaseController {

    @Autowired
    private DisValProfitQuery002Service valProfitQuery002Service;
    
    @Autowired
    private DisValAssetBook001Service valAssetBook001Service;

    @Override
    public String init(Model model) {
    	DisValProfitQuery001Dto entity = new DisValProfitQuery001Dto();
    	entity.setLastDimen(this.getParam("lastDimen"));
    	
    	String reportDate = "";
    	Map<String,Object> pageParam = new HashMap<String,Object>();
    	pageParam.put("reportDate", this.getParam("reportDate"));
    	pageParam.put("reportTerm", this.getParam("reportTerm"));
    	pageParam.put("reportTermType", this.getParam("reportTermType"));
    	try {
    		reportDate = DateUtil.format(valProfitQuery002Service.analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
		} catch (ParseException e) {
		}
    	entity.setReportDate(reportDate);
    	entity.setReportTerm(this.getParam("reportTerm"));
    	entity.setReportTermType(this.getParam("reportTermType"));
    	entity.setProject(this.getParam("project"));
    	entity.setThisTermAmt(new BigDecimal(String.valueOf(this.getParam("thisTermAmt"))));
    	entity.setLastTermAmt(new BigDecimal(String.valueOf(this.getParam("lastTermAmt"))));
    	model.addAttribute("entity", entity);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public List<DisValProfitQuery001Dto> selectBalanceStatement()
        throws Exception {
    	Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
    	// 全账套
    	List<SuggestDto> accountList = valAssetBook001Service.getBooksetId2(super.getRequest().getParameter("param"));
    	paraMap.put("accounts", accountList);
        return valProfitQuery002Service.findPageAll(paraMap);
    }
}
