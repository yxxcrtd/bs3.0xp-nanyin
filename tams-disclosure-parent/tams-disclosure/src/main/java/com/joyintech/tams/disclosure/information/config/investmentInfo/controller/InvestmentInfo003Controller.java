package com.joyintech.tams.disclosure.information.config.investmentInfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.information.config.investmentInfo.service.InvestmentInfo003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@PageId("InvestmentInfo003")
@Controller
@RequestMapping("/disclosure/information/config/investmentInfo/InvestmentInfo003")
public class InvestmentInfo003Controller extends BaseController {

    @Autowired
    private InvestmentInfo003Service investmentInfo003Service;

    /**
     * 内容初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        //自定义指标id
        String repIndexId = this.getParam("repIndexId");
        model.addAttribute("repIndexId",repIndexId);

        String prodId = this.getParam("prodId");
        model.addAttribute("prodId",prodId);
        return super.init(model);
    }

    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("/getAllProdIds")
    public List<TreeNode> getAllProdIds() {
        String repIndexId=super.getRequest().getParameter("repIndexId");
        String prodId=super.getRequest().getParameter("prodId");
        List<String>prodIdList= StringUtil.splitToList(",",prodId);
        if(StringUtil.isNotEmpty(repIndexId)){
            return null;
        } else{//新建
            return investmentInfo003Service.getProductTree(prodIdList);
        }
    }
}
