package com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.busicommon.common.service.DisValAssetBook001Service;
import com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.dto.DisAssetLiabilityStatementQuery001SearchDto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service.DisValProfitQuery002Service;

@Controller
@PageId("DisAssetLiabilityStatementQuery002")
@RequestMapping("/disclosure/financialstatement/assetliailitystatement/DisAssetLiabilityStatementQuery002")
public class DisAssetLiabilityStatementQuery002Controller extends BaseController {
    
//    @Autowired
//    private AssetLiabilityStatementQuery002Service assetLiabilityStatementQuery002Service;

    @Autowired
    private DisValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private DisValProfitQuery002Service valProfitQuery002Service;

    @Override
    public String init(Model model) {
    	DisAssetLiabilityStatementQuery001SearchDto entity = new DisAssetLiabilityStatementQuery001SearchDto();
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
    	model.addAttribute("entity", entity);
        return super.init(model);
    }
    
//    @ResponseBody
//    @RequestMapping("findPage")
//    public List<AssetLiabilityStatementQuery001Dto> findPage(DisAssetLiabilityStatementQuery001SearchDto dto)
//        throws Exception {
//    	Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
//    	// 报表维度为全账套
//    	dto.setAccountList(valAssetBook001Service.getBooksetId2(super.getRequest().getParameter("param")));
//    	if ("AssetTable".equals(paraMap.get("tableId"))) {
//    		return assetLiabilityStatementQuery002Service.findAssetsStatementAll(dto);
//    	} else {
//    		return assetLiabilityStatementQuery002Service.findLiabilityInterestStatementAll(dto);
//    	}
//    }
}
