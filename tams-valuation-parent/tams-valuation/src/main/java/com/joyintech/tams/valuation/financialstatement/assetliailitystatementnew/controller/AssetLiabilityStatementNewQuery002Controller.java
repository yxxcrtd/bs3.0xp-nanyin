package com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery002Service;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001Dto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001SearchDto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.service.AssetLiabilityStatementNewQuery002Service;

/**
 * 
 * @author huanghao
 *
 */
@Controller
@PageId("AssetLiabilityStatementNewQuery002")
@RequestMapping("/valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery002")
public class AssetLiabilityStatementNewQuery002Controller extends BaseController {
    
    @Autowired
    private AssetLiabilityStatementNewQuery002Service assetLiabilityStatementNewQuery002Service;

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private ValProfitQuery002Service valProfitQuery002Service;

    @Override
    public String init(Model model) {
    	AssetLiabilityStatementNewQuery001SearchDto entity = new AssetLiabilityStatementNewQuery001SearchDto();
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
    	entity.setItemName(this.getParam("itemName").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",""));
    	entity.setItemCode(this.getParam("itemCode"));
		entity.setBooksetGroup(this.getParam("booksetGroup"));
    	entity.setEndingBalance(new BigDecimal(String.valueOf(this.getParam("endingBalance"))));
    	entity.setInitialBalance(new BigDecimal(String.valueOf(this.getParam("initialBalance"))));
    	entity.setTableId(this.getParam("tableId"));
    	entity.setReportDimen(this.getParam("reportDimen"));
    	entity.setReportTerm(this.getParam("reportTerm"));
    	entity.setReportTermType(this.getParam("reportTermType"));
    	model.addAttribute("entity", entity);
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("findPage")
    public List<AssetLiabilityStatementNewQuery001Dto> findPage(AssetLiabilityStatementNewQuery001SearchDto dto)
        throws Exception {
    	Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
    	// 报表维度为全账套
		Map<String, Object> params = new HashMap<>();
		params.put("param",super.getRequest().getParameter("param"));
    	dto.setAccountList(valAssetBook001Service.getBooksetId2(params));
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		dto.setBooksetIds(list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
    	if ("AssetTable".equals(paraMap.get("tableId"))) {
    		return assetLiabilityStatementNewQuery002Service.findAssetsStatementAll(dto);
    	} else {
    		return assetLiabilityStatementNewQuery002Service.findLiabilityInterestStatementAll(dto);
    	}
    }
}
