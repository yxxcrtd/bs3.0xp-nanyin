package com.joyintech.tams.invest.product.messageadjust.supplement.controller;

import com.google.json.JsonSanitizer;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.messageadjust.supplement.dto.FinSupplementPlanChg001Dto;
import com.joyintech.tams.invest.product.messageadjust.supplement.service.FinSupplementPlanChg001Service;
import com.joyintech.tams.invest.product.productelement.periodicmanagement.dto.PeriodicManagement001Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("FinSupplementPlanChg002")
@RequestMapping("/invest/product/messageadjust/supplement/FinSupplementPlanChg002")
public class FinSupplementPlanChg002Controller extends BaseController {

    @Autowired
    private FinSupplementPlanChg001Service finSupplementPlanChg001Service;

    @Autowired
    private IPlatformDate platformDate;

    /**
     * 业务标号生成共通
     */

    @Override
    public String init(Model model) {
        FinSupplementPlanChg001Dto dto = new FinSupplementPlanChg001Dto();
        
        dto.setFinprodMarketId(getParam("finprodMarketId"));
        dto.setFinprodId(getParam("finprodId"));
        dto.setFinprodAbbr(getParam("finprodAbbr"));
        dto.setIsLay(getParam("isLay"));
        if(StringUtil.equals(dto.getIsLay(),DictCodeList.是否_是.getValue())) {
            dto.setProdStyle(getParam("prodStyle"));
            dto.setLayFinprodId(getParam("layFinprodId"));
        }
        dto.setRn(new BigDecimal(!StringUtil.isBlank(getParam("rn"))?getParam("rn"):"0"));
        dto.setCashId(getParam("cashId"));
        dto.setSupplementStatus(getParam("supplementStatus"));
        dto.setAccrDate(new Date(Long.parseLong(getParam("accrDate"))));
        dto.setAccrStrDate(new Date(Long.parseLong(getParam("accrStrDate"))));
        dto.setAccrEndDate(new Date(Long.parseLong(getParam("accrEndDate"))));
         
        model.addAttribute("sysDate", platformDate.getSysDateStr());
        
        model.addAttribute("entity", dto);

        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("/getAccrDate")
    public String getAccrDate(@RequestParam Map<String, Object> params) {
        String dateStr = finSupplementPlanChg001Service.getAccrDate(params);
        return JsonSanitizer.sanitize(dateStr);
    }
    
}
