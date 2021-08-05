/*
 * 文件名：InvestorsQualityCheck002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年05月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.submitexamination.investorsqualitycheck.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 投资者质量核查-持有信息汇总弹出框
 * 〈功能详细描述〉
 * @author wanghai
 * @version 1.0 2021年5月10日
 * @see InvestorsQualityCheck002Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/submitexamination/investorsqualitycheck/InvestorsQualityCheck002")
@PageId("InvestorsQualityCheck002")
public class InvestorsQualityCheck002Controller extends BaseController{

    @Autowired
    private InvestorsQualityCheckMService investorsQualityCheckMService;

    @Override
    public String init(Model model) {
        String lastSummaryTime = investorsQualityCheckMService.getLastSummaryTime();
        model.addAttribute("lastSummaryTime", StringUtil.isNotEmpty(lastSummaryTime)?lastSummaryTime:"");
    	return super.init(model);
    }
    /**
    * 取得查询数据
    * @return 存在于查询条件的数据
    */
    @RequestMapping("investorsSummary")
    @ResponseBody
    public AjaxResult investorsSummary() {
        Map<String, Object> param = RequestUtil.getPageParam();
        String startDate = (String) param.get("startDate");
        String endDate = (String) param.get("endDate");
        AjaxResult ar = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if(StringUtil.isEmpty(startDate)){
            errorList.add(new ValidMessage("汇总开始日不能为空！"));
        }
        if(StringUtil.isEmpty(endDate)){
            errorList.add(new ValidMessage("汇总结束日不能为空！"));
        }
        if(StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(endDate) && startDate.compareTo(endDate) > 0){
            errorList.add(new ValidMessage("汇总开始日不能大于汇总结束日！"));
        }
        if(errorList.size() > 0){
            ar.setErrorList(errorList);
            return ar;
        }

        String msg = investorsQualityCheckMService.executeInvestorsSummary(param);
        if(StringUtil.isNotEmpty(msg)){
            return new AjaxResult(msg);
        }
        return new AjaxResult("执行完成！");
    }

}
