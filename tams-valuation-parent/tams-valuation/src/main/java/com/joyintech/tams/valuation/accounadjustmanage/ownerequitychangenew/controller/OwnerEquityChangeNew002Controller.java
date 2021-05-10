package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.service.OwnerEquityChangeNew001Service;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service.ValProfitQuery002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Controller
@PageId("OwnerEquityChangeNew002")
@RequestMapping("/valuation/accounadjustmanage/ownerequitychangenew/OwnerEquityChangeNew002")
public class OwnerEquityChangeNew002Controller extends BaseController {
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private OwnerEquityChangeNew001Service ownerEquityChangeNew001Service;

    @Autowired
    private ValProfitQuery002Service valProfitQuery002Service;

    @Override
    public String init(Model model) {
        OwnerEquityChangeNew001Dto entity = new OwnerEquityChangeNew001Dto();
        String reportDate = "";
        String reportDatep = "";
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("reportDate", this.getParam("valDate"));
        pageParam.put("reportTerm", this.getParam("reportTerm"));
        pageParam.put("reportTermType", this.getParam("reportTermType"));
        try {
            reportDate = DateUtil.format(valProfitQuery002Service.analyseDate(pageParam),
                    DateUtil.DEFAULT_DATE_FORMAT_EN);
            reportDatep = DateUtil.format(valProfitQuery002Service.analyseDate(pageParam),
                    DateUtil.DEFAULT_DATE_FORMAT_EN);
//            reportDatep = DateUtil.format(valProfitQuery002Service.analyseDatep(pageParam),
//                    DateUtil.DEFAULT_DATE_FORMAT_EN);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setValDate(reportDatep + "-" + reportDate);
        entity.setProject(this.getParam("project").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",""));
        entity.settIncomeFund(new BigDecimal(String.valueOf(this.getParam("tIncomeFund"))));
        entity.settUnQuarterProfit(new BigDecimal(String.valueOf(this.getParam("tUnQuarterProfit"))));
        entity.settOtherCompIncome(new BigDecimal(String.valueOf(this.getParam("tOtherCompIncome"))));
        entity.settOwnerEquityTot(new BigDecimal(String.valueOf(this.getParam("tOwnerEquityTot"))));

        entity.setyIncomeFund(new BigDecimal(String.valueOf(this.getParam("yIncomeFund"))));
        entity.setyUnQuarterProfit(new BigDecimal(String.valueOf(this.getParam("yUnQuarterProfit"))));
        entity.setyOtherCompIncome(new BigDecimal(String.valueOf(this.getParam("yOtherCompIncome"))));
        entity.setyOwnerEquityTot(new BigDecimal(String.valueOf(this.getParam("yOwnerEquityTot"))));

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
    public List<OwnerEquityChangeNew001Dto> selectCashFlowsStatement() throws Exception {
        Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
        // 报表维度为账套组
        List<SysBatchGrpPrdlink> list = valAssetBook001Service.findAllProduct(paraMap);
        String booksetId = list.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(","));
        List<String> booksetIds = Arrays.asList(booksetId.split(","));
        paraMap.put("booksetIds", booksetIds);
        return ownerEquityChangeNew001Service.selectList(paraMap);
    }
}
