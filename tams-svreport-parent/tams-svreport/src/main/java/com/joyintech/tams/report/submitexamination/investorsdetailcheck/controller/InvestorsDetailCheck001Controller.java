package com.joyintech.tams.report.submitexamination.investorsdetailcheck.controller;


import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto.InvestorsDetailCheck001Dto;
import com.joyintech.tams.report.submitexamination.investorsdetailcheck.service.InvestorsDetailCheckMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 投资者明细核查画面
 */
@Controller
@PageId("InvestorsDetailCheck001")
@RequestMapping("/svreport/submitexamination/investorsdetailcheck/InvestorsDetailCheck001")
public class InvestorsDetailCheck001Controller extends BaseController{
    /**
     * 投资者明细核查service
     */
    @Autowired
    private InvestorsDetailCheckMService investorsDetailCheckMService;
    /**
     * 画面的初始化的函数
     * @param model model
     * @return String
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model,DictCode.产品运作模式,DictCode.检查结果);
        return super.init(model);
    }
    /**
     * 查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/searchTable")
    public Page<InvestorsDetailCheck001Dto> searchTable(){
        return investorsDetailCheckMService.searchTable(RequestUtil.getPageParam());
    }
}
