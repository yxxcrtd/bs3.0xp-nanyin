/*
 * 文件名：PtlSubFundsAllocate002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocate002Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service.PtlSubFundsAllocate002Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author adrian.zhou
 * @version 1.0 2019年8月15日
 * @see PtlSubFundsAllocate002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlSubFundsAllocate002")
@RequestMapping("/invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002")
public class PtlSubFundsAllocate002Controller extends BaseController{
    @Autowired
    private PtlSubFundsAllocate002Service ptlSubFundsAllocate002Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    
    /**
     * 新建页面画面联动
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlSubFundsAllocate002Dto> selectOther() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        String totleTradeId = this.getParam("totleTradeId");
        String workflowId = getParam("wfId");
        
        if(StringUtil.isNotBlank(workflowId)) {
            PtlSubFundsAllocate002Dto dto = new PtlSubFundsAllocate002Dto();
        	dto = ptlSubFundsAllocate002Service.getBussinessEntityByWorkflowId(
                    workflowId);
        	Page<PtlSubFundsAllocate002Dto> page = new Page<PtlSubFundsAllocate002Dto>();
        	page.setRoot(dto.getSelectedItem());
        	return page;
        }
        else if(StringUtil.isNotBlank(totleTradeId)) {
        	Page<PtlSubFundsAllocate002Dto> page = new Page<PtlSubFundsAllocate002Dto>();
        	page.setRoot(ptlSubFundsAllocate002Service.getSubFundsAllocate(param).getRoot());
        	return page;
        }
        return ptlSubFundsAllocate002Service.selectOther(param);
    }
    
    @ResponseBody
    @RequestMapping("getSubFundsAllocate")
    public Page<PtlSubFundsAllocate002Dto> getSubFundsAllocate() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return ptlSubFundsAllocate002Service.getSubFundsAllocate(param);
    }
    
    @ResponseBody
    @RequestMapping("getCashFlowActualAmt")
    public BigDecimal getCashFlowActualAmt() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return ptlSubFundsAllocate002Service.getCashFlowActualAmt(param);
    }
    
    @ResponseBody
    @RequestMapping("getSumCashFlowAvaAmt")
    public BigDecimal getSumCashFlowAvaAmt() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return ptlSubFundsAllocate002Service.getSumCashFlowAvaAmt(param);
    }
    
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	PtlSubFundsAllocate002Dto dto = new PtlSubFundsAllocate002Dto();
    	String workflowStatus = this.getParam("workflowStatus");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.equals(workflowStatus, DictCodeListExt1.交易流程状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId))) {
            dto = ptlSubFundsAllocate002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("workflowId",workflowId);
            model.addAttribute("portfolioId",dto.getPortfolioId());
            model.addAttribute("portfolioName",dto.getPortfolioName());
            model.addAttribute("totleTradeId",dto.getTradeId());
            model.addAttribute("wfId",workflowId);
        }
        else {
        	String portfolioId = this.getParam("portfolioId");
        	String parentPortfolioId = this.getParam("parentPortfolioId");
        	dto.setPortfolioId(portfolioId);
        	String portfolioName = this.getParam("portfolioName");
        	dto.setPortfolioName(portfolioName);
        	String totleTradeId = this.getParam("totleTradeId");
        	dto.setTradeId(totleTradeId);
        	model.addAttribute("portfolioId",dto.getPortfolioId());
        	model.addAttribute("portfolioName",dto.getPortfolioName());
        	model.addAttribute("totleTradeId",dto.getTradeId());
        	model.addAttribute("portfolioId2",portfolioId);
        	model.addAttribute("parentPortfolioId",portfolioId);
        }
        model.addAttribute("entity", dto);
        // 调配日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        model.addAttribute("settleDate",dto.getSettleDate());
        // 初始化
        return super.init(model);
    }

	/**
	 * 子组合间资金调配-组合代码联想框
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPortfolioId")
	public List<SelectOptionDto> getPortfolioId() throws Exception {
		return ptlSubFundsAllocate002Service.getPortfolioId(RequestUtil.getPageParam());
	}


    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;
    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlSubFundsAllocate002Dto dto, BindingResult result) {
    	/*WflValidInputParameter param =new WflValidInputParameter();
        param.setPageId("PtlSubFundsAllocate002");
        param.setBusinessDataPk1(dto.getTradeId());
        param.setBusinessDataPk2(DictCodeList.共通业务类型_子组合资金调配.getValue());
		if (mstWorkflowCommonServie.isInWflProcess(param)) {
			AjaxResult res= new AjaxResult();
			res.setMessage(getMessage("BUSS_COM_E_0023"), MessageType.错误消息);
    		return res;
    	}*/
    	/*if (ptlSubFundsAllocate002Service.checkBusinessDataInFlow(dto)) {
    		AjaxResult res= new AjaxResult();
			res.setMessage(getMessage("BUSS_COM_E_0023"), MessageType.错误消息);
    		return res;
    	}*/
    	
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
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlSubFundsAllocate002Dto ptlSubFundsAllocate002Dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }        
        return ptlSubFundsAllocate002Service.handleSave(ptlSubFundsAllocate002Dto);
    }
}
