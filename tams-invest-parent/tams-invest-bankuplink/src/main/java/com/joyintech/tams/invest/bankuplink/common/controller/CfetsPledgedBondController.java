package com.joyintech.tams.invest.bankuplink.common.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对手方质押券信息
 * @auther zhangnian
 * @date 2021/3/8
 */
@Controller
@PageId("CfetsPledgedBond")
@RequestMapping("/invest/bankuplink/common/CfetsPledgedBond")
public class CfetsPledgedBondController extends BaseController {

    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;
    @Override
    public String init(Model model) {
        List<CfetsRepoDealQuotedPrice003Dto> cfetsRepoDealQuotedPrices = new ArrayList<>();
        String quoteId = this.getParam("quoteId");
        String isShowDsfPledge = "N";
        if(StringUtil.isNotEmpty(quoteId)){
            Map<String,Object> map= new HashMap<>();
            map.put("quoteId",quoteId);
            isShowDsfPledge = cfetsRepoDealQuotedPrice003Service.getIsShowDsfPledge(map);
            if(StringUtil.equals(isShowDsfPledge,"Y")){
                cfetsRepoDealQuotedPrices= cfetsRepoDealQuotedPrice003Service.selectQuoteCrepPledgeForDsf(map);
            }
        }
        model.addAttribute("isShowDsfPledge",isShowDsfPledge);
        model.addAttribute("cfetsRepoDealQuotedPrices",cfetsRepoDealQuotedPrices);
        model.addAttribute("pageFrom",this.getParam("pageFrom"));
        return super.init(model);
    }
}

