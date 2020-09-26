package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsStatement001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsStatement002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.service.CashFlowsStatement001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PageId("CashFlowsStatement002")
@RequestMapping("/valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement002")
public class CashFlowsStatement002Controller extends BaseController {

	@Autowired
	private ValAssetBook001Service valAssetBook001Service;

	@Autowired
	private CashFlowsStatement001Service cashFlowsStatement001Service;

	@Autowired
	private ValProfitQuery002Service valProfitQuery002Service;

	@Override
	public String init(Model model) {
		CashFlowsStatement002Dto entity = new CashFlowsStatement002Dto();
		String reportDate = "";
		Map<String, Object> pageParam = new HashMap<String, Object>();
		pageParam.put("reportDate", this.getParam("reportDate"));
		pageParam.put("reportTerm", this.getParam("reportTerm"));
		pageParam.put("reportTermType", this.getParam("reportTermType"));
		try {
			reportDate = DateUtil.format(valProfitQuery002Service.analyseDate(pageParam),
					DateUtil.DEFAULT_DATE_FORMAT_EN);
		} catch (ParseException e) {
		}
		entity.setReportDate(reportDate);
		entity.setProject(this.getParam("project"));
		entity.setThisTermAmt(new BigDecimal(String.valueOf(this.getParam("thisTermAmt"))));
		entity.setLastTermAmt(new BigDecimal(String.valueOf(this.getParam("lastTermAmt"))));
		entity.setTableId(this.getParam("tableId"));
		entity.setReportDimen(this.getParam("reportDimen"));
		entity.setReportTerm(this.getParam("reportTerm"));
		entity.setReportTermType(this.getParam("reportTermType"));
		entity.setBooksetGroup(this.getParam("booksetGroup"));
		entity.setLastDimen(this.getParam("lastDimen"));
		model.addAttribute("entity", entity);
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("findPage")
	public List<CashFlowsStatement001Dto> selectCashFlowsStatement(CashFlowsStatement001Dto dto) throws Exception {
		Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
		// 报表维度为账套组
		List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
		dto.setBooksetId(
				list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(",")));
		return cashFlowsStatement001Service.selectListp(RequestUtil.getParameters(getRequest()), dto);
	}

}
