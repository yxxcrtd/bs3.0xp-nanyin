/*
 * 文件名：ValProfitQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
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
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.service.ValProfitNewQuery002Service;

/**
 * 利润表-明细列表
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitNewQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("ValProfitNewQuery002")
@RequestMapping("/valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery002")
public class ValProfitNewQuery002Controller extends BaseController {

    @Autowired
    private ValProfitNewQuery002Service valProfitNewQuery002Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Override
    public String init(Model model) {
		ValProfitNewQuery001Dto entity = new ValProfitNewQuery001Dto();
		entity.setLastDimen(this.getParam("lastDimen"));

		String reportDate = "";
		Map<String,Object> pageParam = new HashMap<String,Object>();
		pageParam.put("reportDate", this.getParam("reportDate"));
		pageParam.put("reportTerm", this.getParam("reportTerm"));
		pageParam.put("reportTermType", this.getParam("reportTermType"));
		//add by yanya 20190929 利润表年报变更为选择具体天数时，增加参数类型区分，后端区别处理日期类型
		pageParam.put("yearType", this.getParam("yearType"));
		try {
			reportDate = DateUtil.format(valProfitNewQuery002Service.analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
		} catch (ParseException e) {
			getLogger().error(e.getMessage(), e);
		}
		entity.setReportDate(reportDate);
		entity.setReportTerm(this.getParam("reportTerm"));
		entity.setReportTermType(this.getParam("reportTermType"));
		entity.setProject(this.getParam("project"));
		entity.setBooksetGroup(this.getParam("booksetGroup"));
		entity.setThisTermAmt(new BigDecimal(String.valueOf(this.getParam("thisTermAmt"))));
		entity.setLastTermAmt(new BigDecimal(String.valueOf(this.getParam("lastTermAmt"))));
		model.addAttribute("entity", entity);
		return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public List<ValProfitNewQuery001Dto> selectBalanceStatement()
        throws Exception {
    	/*Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
    	// 全账套
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		paraMap.put("booksetId", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
		List<SuggestDto> accountList = valAssetBook001Service.getBooksetId3(paraMap);
		paraMap.put("accounts", accountList);*/
		Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
		// 报表维度为账套组
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		paraMap.put("booksetId", list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
		return valProfitNewQuery002Service.findPageAll(paraMap);
    }
}
