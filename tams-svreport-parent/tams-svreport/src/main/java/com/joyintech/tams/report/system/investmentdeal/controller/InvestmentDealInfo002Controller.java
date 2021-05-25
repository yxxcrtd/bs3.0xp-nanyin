package com.joyintech.tams.report.system.investmentdeal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import com.joyintech.tams.report.system.investmentdeal.service.InvestmentDealInfoService;
import com.joyintech.tams.report.system.investmentdeal.util.ErrdataConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@PageId("InvestmentDealInfo002")
@RequestMapping("/svreport/system/investmentdeal/InvestmentDealInfo002")
public class InvestmentDealInfo002Controller extends BaseController {

    @Autowired
    private InvestmentDealInfoService investmentDealInfoService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.币种_中债, DictCode.资产计量方式,DictCode.资金流动类型,DictCode.交易对手类型);
        InvestmentDealInfoDto dto=new InvestmentDealInfoDto();
        Map<String,Object> map= RequestUtil.getPageParam();
        String uuid= (String) map.get("uuid");
        if(!"add".equals(map.get("operateType"))){
            if(StringUtil.isEmpty(uuid)){
                dto = investmentDealInfoService.selectOneDate(map);
                model.addAttribute("entity", dto);
            }else{
                ChbInvestTradeErrordataWithBLOBs chbMs=investmentDealInfoService.selectInvestmentDealInfoErrordata(uuid);
                ErrdataConvertUtil.errdataChbInvestTradeConvert(dto,chbMs);
                model.addAttribute("entity", dto);
            }
        }else{
            model.addAttribute("entity",dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) InvestmentDealInfoDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) InvestmentDealInfoDto dto, BindingResult result) {
           investmentDealInfoService.save(dto);
        return new AjaxResult("保存成功", MessageType.提示消息);
    }

    @ResponseBody
    @RequestMapping("/getTradeCounter")
    public List<SelectOptionDto> getTradeCounter() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<SelectOptionDto> list = investmentDealInfoService.getTradeCounter(param);
        return list;
    }

}
