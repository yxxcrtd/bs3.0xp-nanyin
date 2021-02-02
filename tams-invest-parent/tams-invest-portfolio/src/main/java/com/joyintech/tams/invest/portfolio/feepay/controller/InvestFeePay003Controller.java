/*
 * 文件名：InvestFeePay003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.feepay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay003Dto;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay002Service;


/**
 * 费用明细
 * 
 * @author malinlin
 * @version 1.0 2019年12月9日
 * @see InvestFeePay003Controller
 * @since 1.0
 */
@Controller
@PageId("InvestFeePay003")
@RequestMapping("/invest/portfolio/feepay/InvestFeePay003")
public class InvestFeePay003Controller extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePay002Service investFeePay002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("secAcctId", this.getParam("secAcctId"));
        model.addAttribute("ptlId", this.getParam("ptlId"));
        model.addAttribute("calcStartDate", this.getParam("calcStartDate"));
        model.addAttribute("calcEndDate", this.getParam("calcEndDate"));
        model.addAttribute("feeType", this.getParam("feeType"));
        model.addAttribute("confirmFlag", this.getParam("confirmFlag"));
        model.addAttribute("tradeId", this.getParam("tradeId"));
        model.addAttribute("org", this.getParam("org"));
        return super.init(model);
    }
    
    /**
     * 查询费用明细列表
     * 
     * @param finprodId 金融产品代码
     * @param calcStartDate 区间开始日
     * @param calcEndDate 区间结束日
     * @param feeId 费用代码
     * @return 费用明细列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeePositionDetail")
    public List<InvestFeePay003Dto> queryFeePositionDetail(String secAcctId, String ptlId, String calcStartDate, String calcEndDate, String feeType,String confirmFlag,String tradeId,String org){
    	List<InvestFeePay003Dto> payList = investFeePay002Service.queryFeePositionDetail(secAcctId, ptlId, calcStartDate, calcEndDate, feeType,confirmFlag,tradeId,org);
    	return payList;
    }
}
