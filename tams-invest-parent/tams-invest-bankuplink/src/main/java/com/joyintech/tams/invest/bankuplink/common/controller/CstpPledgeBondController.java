package com.joyintech.tams.invest.bankuplink.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;

/**
 * 成交回报查询--质押券明细
 * @auther yflv
 * @date 2021/3/23
 */
@Controller
@PageId("CstpPledgeBond")
@RequestMapping("/invest/bankuplink/common/CstpPledgeBond")
public class CstpPledgeBondController extends BaseController {

    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;
    @Override
    public String init(Model model) {
        String execid = this.getParam("execid");
//        List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = null;
        if(StringUtil.isNotEmpty(execid)){
//            Map<String,Object> map= new HashMap<>();
//            map.put("execid",execid);
            // 查询债券相关信息
//            pledgeBondList = cfetsRepoDealQuotedPrice003Service.getPledgeBondList(map).getRoot();
            model.addAttribute("execid",execid);
        }
        return super.init(model);
    }
    
    /**
     *查询质押券明细
     * 
     * @param param 入参
     * @return 质押券明细
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCstpPledgeBondList")
    public List<CfetsRepoDealQuotedPrice003Dto> getCstpPledgeBondList() {
    	Map<String, Object> param = RequestUtil.getPageParam();
        return cfetsRepoDealQuotedPrice003Service.getPledgeBondList(param).getRoot();
    }
}

