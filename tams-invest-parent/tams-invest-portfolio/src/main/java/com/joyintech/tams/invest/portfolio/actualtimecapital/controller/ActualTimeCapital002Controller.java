package com.joyintech.tams.invest.portfolio.actualtimecapital.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.portfolio.actualtimecapital.dto.ActualTimeCapital002Dto;
import com.joyintech.tams.invest.portfolio.actualtimecapital.service.ActualTimeCapital002Service;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAsset001Controller
 * @author: yanya
 * @create: 2019-10-15
 **/
@Controller
@PageId("ActualTimeCapital002")
@RequestMapping("/invest/portfolio/actualtimecapital/ActualTimeCapital002")
public class ActualTimeCapital002Controller extends BaseController {
    /**
     * 现金余额调整service
     */
    @Autowired
    private ActualTimeCapital002Service actualTimeCapital002Service;

    /**
     * 现金余额调整界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String portfolioId = getParam("portfolioId");
        String acctId = getParam("acctId");
        String adjDateStr = getParam("adjDate");
        String ccy = getParam("ccy");
        //String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        ActualTimeCapital002Dto paramDto = new ActualTimeCapital002Dto();
        paramDto.setPortfolioId(portfolioId);
        paramDto.setAcctId(acctId);
        paramDto.setAdjDate(DateUtil.parse(adjDateStr));
        paramDto.setCcy(ccy);
//        if (StringUtil.isNotEmpty(workflowId) && !WorkFlowUtil.isNotFromToDoList()) {
//            ActualTimeCapital002Dto dto = actualTimeCapital002Service.getBussinessEntityByWorkflowId(workflowId);
//            model.addAttribute("entity", dto);
//        }
//        else 
      
        ActualTimeCapital002Dto dto = actualTimeCapital002Service.getInitInfo(paramDto);
        if(dto == null) {
            model.addAttribute("entity", paramDto);
        }
        else {
            model.addAttribute("entity", dto);
        }
       
        return super.init(model);
    }

    
    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ActualTimeCapital002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
   
    
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Valid ActualTimeCapital002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //dto.setAcctId("TEST");
        String msg = actualTimeCapital002Service.adjustmentBalance(dto);
        return new AjaxResult(msg);
    }
}
