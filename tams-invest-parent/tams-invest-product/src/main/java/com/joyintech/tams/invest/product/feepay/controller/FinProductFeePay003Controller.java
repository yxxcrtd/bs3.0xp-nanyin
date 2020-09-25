/*
 * 文件名：FinProductFeePay003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.feepay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay003Dto;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay002Service;


/**
 * 费用计提明细
 * 
 * @author zhangliang
 * @version 1.0 2018年11月23日
 * @see FinProductFeePay003Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductFeePay003")
@RequestMapping("/invest/product/feepay/FinProductFeePay003")
public class FinProductFeePay003Controller extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePay002Service finProductFeePay002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("calcStartDate", this.getParam("calcStartDate"));
        model.addAttribute("calcEndDate", this.getParam("calcEndDate"));
        model.addAttribute("feeId", this.getParam("feeId"));
        model.addAttribute("branch", this.getParam("branch"));
        model.addAttribute("feeType", this.getParam("feeType"));
        model.addAttribute("sFinprodId", this.getParam("sFinprodId"));
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
    public List<FinProductFeePay003Dto> queryFeePositionDetail(String finprodId, String sFinprodId, String calcStartDate, String calcEndDate, String feeId,
                                                               String branch, String feeType){
    	String finprodIdTmp = "";
    	if(StringUtil.isNotEmpty(sFinprodId)){
    		finprodIdTmp = sFinprodId;
    	}
    	else{
    		finprodIdTmp = finprodId;
    	}
    	List<FinProductFeePay003Dto> payList = finProductFeePay002Service.queryFeePositionDetail(finprodIdTmp, calcStartDate, calcEndDate, feeId, branch, feeType);
    	for (FinProductFeePay003Dto finProductFeePay003Dto : payList) {
			if(StringUtil.isNotEmpty(sFinprodId)){
				finProductFeePay003Dto.setsFinprodId(finProductFeePay003Dto.getFinprodId());
				finProductFeePay003Dto.setFinprodId(finprodId);
			}
		}
//        return finProductFeePay002Service.queryFeePositionDetail(finprodId, calcStartDate, calcEndDate, feeId, branch, feeType);
    	return payList;
    }
}
