/*
 * 文件名：OwnerEquityChangeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.poc.creditrisk.riskmanagement.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.poc.creditrisk.riskmanagement.dto.CreditRiskManagement001Dto;

/**
 * 信用风险管理
 * @author QuJianZhong
 *
 */
@Controller
@PageId("CreditRiskManagement001")
@RequestMapping("/poc/creditrisk.riskmanagement/CreditRiskManagement001")
public class CreditRiskManagement001Controller extends BaseController {

    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("findPage")
    public List<CreditRiskManagement001Dto> findPage() {
    	List<CreditRiskManagement001Dto> dataList = new ArrayList<CreditRiskManagement001Dto>();
    	CreditRiskManagement001Dto 货币基金 = new CreditRiskManagement001Dto();
    	货币基金.setProjName("货币基金");
    	货币基金.setSumAmt(new BigDecimal("3972669168"));
    	货币基金.setNotOverdueAmt(new BigDecimal("3972669168"));
    	货币基金.setOverdueAmt12(new BigDecimal("0"));
    	货币基金.setOverdueAmt23(new BigDecimal("0"));
    	货币基金.setOverdueAmt3x(new BigDecimal("0"));
    	dataList.add(货币基金);
    	CreditRiskManagement001Dto 应收票据 = new CreditRiskManagement001Dto();
    	应收票据.setProjName("应收票据");
    	应收票据.setSumAmt(new BigDecimal("4932600"));
    	应收票据.setNotOverdueAmt(new BigDecimal("4932600"));
    	应收票据.setOverdueAmt12(new BigDecimal("0"));
    	应收票据.setOverdueAmt23(new BigDecimal("0"));
    	应收票据.setOverdueAmt3x(new BigDecimal("0"));
    	dataList.add(应收票据);
    	CreditRiskManagement001Dto 应收账款 = new CreditRiskManagement001Dto();
    	应收账款.setProjName("应收账款");
    	应收账款.setSumAmt(new BigDecimal("613516789"));
    	应收账款.setNotOverdueAmt(new BigDecimal("559001137"));
    	应收账款.setOverdueAmt12(new BigDecimal("11424603"));
    	应收账款.setOverdueAmt23(new BigDecimal("2419593"));
    	应收账款.setOverdueAmt3x(new BigDecimal("40671456"));
    	dataList.add(应收账款);
    	CreditRiskManagement001Dto 其他应收款 = new CreditRiskManagement001Dto();
    	其他应收款.setProjName("其他应收款");
    	其他应收款.setSumAmt(new BigDecimal("614396413"));
    	其他应收款.setNotOverdueAmt(new BigDecimal("420655528"));
    	其他应收款.setOverdueAmt12(new BigDecimal("49926047"));
    	其他应收款.setOverdueAmt23(new BigDecimal("28911894"));
    	其他应收款.setOverdueAmt3x(new BigDecimal("114902944"));
    	dataList.add(其他应收款);
    	CreditRiskManagement001Dto 一年内到期的非流动资产 = new CreditRiskManagement001Dto();
    	一年内到期的非流动资产.setProjName("一年内到期的非流动资产");
    	一年内到期的非流动资产.setSumAmt(new BigDecimal("54553307"));
    	一年内到期的非流动资产.setNotOverdueAmt(new BigDecimal("0"));
    	一年内到期的非流动资产.setOverdueAmt12(new BigDecimal("0"));
    	一年内到期的非流动资产.setOverdueAmt23(new BigDecimal("0"));
    	一年内到期的非流动资产.setOverdueAmt3x(new BigDecimal("54553307"));
    	dataList.add(一年内到期的非流动资产);
    	CreditRiskManagement001Dto 长期应收款 = new CreditRiskManagement001Dto();
    	长期应收款.setProjName("长期应收款");
    	长期应收款.setSumAmt(new BigDecimal("21697532"));
    	长期应收款.setNotOverdueAmt(new BigDecimal("21697532"));
    	长期应收款.setOverdueAmt12(new BigDecimal("0"));
    	长期应收款.setOverdueAmt23(new BigDecimal("0"));
    	长期应收款.setOverdueAmt3x(new BigDecimal("0"));
    	dataList.add(长期应收款);
    	CreditRiskManagement001Dto 发放贷款及垫款 = new CreditRiskManagement001Dto();
    	发放贷款及垫款.setProjName("发放贷款及垫款");
    	发放贷款及垫款.setSumAmt(new BigDecimal("2163054930"));
    	发放贷款及垫款.setNotOverdueAmt(new BigDecimal("2163054930"));
    	发放贷款及垫款.setOverdueAmt12(new BigDecimal("0"));
    	发放贷款及垫款.setOverdueAmt23(new BigDecimal("0"));
    	发放贷款及垫款.setOverdueAmt3x(new BigDecimal("0"));
    	dataList.add(发放贷款及垫款);
    	CreditRiskManagement001Dto 合计行 = new CreditRiskManagement001Dto();
    	合计行.setSumAmt(new BigDecimal("7444820739"));
    	合计行.setNotOverdueAmt(new BigDecimal("7142010895"));
    	合计行.setOverdueAmt12(new BigDecimal("61350650"));
    	合计行.setOverdueAmt23(new BigDecimal("31331487"));
    	合计行.setOverdueAmt3x(new BigDecimal("210127707"));
    	dataList.add(合计行);
        return dataList;
    }
    
    /**
     * 查询条件检查
     * 
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid CreditRiskManagement001Dto dto, BindingResult result) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return ajaxResult;
    }
    
}
