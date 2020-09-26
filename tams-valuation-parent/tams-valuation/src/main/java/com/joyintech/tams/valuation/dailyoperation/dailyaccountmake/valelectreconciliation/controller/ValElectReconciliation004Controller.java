package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation001Dto;

/**
 * 日志查看Controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation004Controller
 * @since 1.0
 */
@Controller
@PageId("ValElectReconciliation004")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation004")
public class ValElectReconciliation004Controller extends BaseController {

    @Override
    public String init(Model model) {
        String valDate = getParam("valDate");
        String prodId = getParam("prodId");
        String serialNo = getParam("serialNo");
        String returnTxt = getParam("returnTxt");
        ValElectReconciliation001Dto dto = new ValElectReconciliation001Dto();
        dto.setValDate(valDate);
        dto.setProdId(prodId);
        dto.setSerialNo(serialNo);
        dto.setReturnTxt(returnTxt);
        model.addAttribute("entity", dto);
        return super.init(model);
    }

}
