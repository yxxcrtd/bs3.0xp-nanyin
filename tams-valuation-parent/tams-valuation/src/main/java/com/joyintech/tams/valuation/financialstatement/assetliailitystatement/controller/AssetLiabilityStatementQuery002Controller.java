package com.joyintech.tams.valuation.financialstatement.assetliailitystatement.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.select.SuggestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery002Service;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatement.service.AssetLiabilityStatementQuery002Service;

@Controller
@PageId("AssetLiabilityStatementQuery002")
@RequestMapping("/valuation/financialstatement/assetliailitystatement/AssetLiabilityStatementQuery002")
public class AssetLiabilityStatementQuery002Controller extends BaseController {
    
    @Autowired
    private AssetLiabilityStatementQuery002Service assetLiabilityStatementQuery002Service;

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private ValProfitQuery002Service valProfitQuery002Service;

    @Override
    public String init(Model model) {
    	AssetLiabilityStatementQuery001SearchDto entity = new AssetLiabilityStatementQuery001SearchDto();
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
    	entity.setItemName(this.getParam("itemName"));
    	entity.setEndingBalance(new BigDecimal(String.valueOf(this.getParam("endingBalance"))));
    	entity.setInitialBalance(new BigDecimal(String.valueOf(this.getParam("initialBalance"))));
    	entity.setTableId(this.getParam("tableId"));
    	entity.setReportDimen(this.getParam("reportDimen"));
    	entity.setReportTerm(this.getParam("reportTerm"));
    	entity.setReportTermType(this.getParam("reportTermType"));
		entity.setBooksetGroup(this.getParam("booksetGroup"));
    	model.addAttribute("entity", entity);
        return super.init(model);
    }

	@ResponseBody
	@RequestMapping("findPage")
	public List<AssetLiabilityStatementQuery001Dto> findPage(AssetLiabilityStatementQuery001SearchDto dto)
			throws Exception {
		Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
		// 报表维度为全账套
		//dto.setAccountList(valAssetBook001Service.getBooksetId2(super.getRequest().getParameter("param")));
		// 报表维度为账套组
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		//add by yanya 20190929 修复账套组下钻明细出错，accountList为空
		String booksetIds = list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(","));
		dto.setBooksetId(booksetIds);
		//paraMap.put("booksetId", booksetIds);
		//List<SuggestDto> accountList = valAssetBook001Service.getBooksetId3(paraMap);
		//dto.setAccountList(accountList);
		dto.setAccountList(valAssetBook001Service.getBooksetId2(paraMap));
		if ("AssetTable".equals(paraMap.get("tableId"))) {
			return assetLiabilityStatementQuery002Service.findAssetsStatementAll(dto);
		} else {
			return assetLiabilityStatementQuery002Service.findLiabilityInterestStatementAll(dto);
		}
	}
}
