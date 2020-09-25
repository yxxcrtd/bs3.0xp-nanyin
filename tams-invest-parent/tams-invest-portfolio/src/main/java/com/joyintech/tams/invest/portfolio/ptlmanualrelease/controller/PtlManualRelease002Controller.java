/*
 * 文件名：PtlCashFlow001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanualrelease.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.service.PtlManualRelease002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 手动释放头寸
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月09日
 * @see PtlManualRelease002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManualRelease002")
@RequestMapping("/invest/portfolio/ptlmanualrelease/PtlManualRelease002")
public class PtlManualRelease002Controller extends BaseController{
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlManualRelease002Service ptlManualRelease002Service;
  
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		String tradeId = getParam("tradeId");

    	// 是否存在与工作流中
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			PtlManualRelease001Dto dto = ptlManualRelease002Service.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("businessId", workflowId);
			model.addAttribute(GlobalConstant.ENTITY, dto);
	     	model.addAttribute("tradeId",dto.getTradeId());
	        Map<String, Object> pageParam =new HashMap<String, Object>();
	        pageParam.put("tradeId", dto.getTradeId());
	        List<PtlManualRelease001Dto> replist= ptlManualRelease002Service.findPageRepInfoList(pageParam);
	    	//_dto.setTradeId(getParam("tradeId"));
	    	model.addAttribute("entity", dto);

            model.addAttribute("entity_replist",replist);
		}else if(StringUtil.isNotEmpty(tradeId)){
	    	//model.addAttribute("entity", getParam("data"));
	    	PtlManualRelease001Dto _dto=new PtlManualRelease001Dto();
	    	_dto=ptlManualRelease002Service.selectTradeInfo(tradeId);
	        ptlManualRelease002Service.findPageRepInfo(RequestUtil.getParameters(getRequest()));
	        Map<String, Object> pageParam =new HashMap<String, Object>();
	        pageParam.put("tradeId", tradeId);
	        List<PtlManualRelease001Dto> replist= ptlManualRelease002Service.findPageRepInfoList(pageParam);
	    	//_dto.setTradeId(getParam("tradeId"));
            model.addAttribute("entity_replist",replist);
	     	model.addAttribute("tradeId",tradeId);
	    	model.addAttribute("entity", _dto);
		}
        return super.init(model);
    }
    
    /**
	 * 修改流程 <功能详细描述>
	 *
	 * @param dto
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("save")
	@ResponseBody
	public AjaxResult save(@RequestBody PtlManualRelease001Dto dto, BindingResult br) throws Exception {
		ProcessParameter<PtlManualRelease001Dto> processParameter = new ProcessParameter<PtlManualRelease001Dto>();
		WorkFlowParameter<PtlManualRelease001Dto> params = new WorkFlowParameter<PtlManualRelease001Dto>();
		params.setElementId(dto.getElementId());
		params.setPageId(this.getPageId());
		params.setBusinessCode(dto.getTradeId());
		params.setServiceName("PtlManualRelease002Service");
		params.setBusinessType(DictCodeList.共通业务类型_手动释放头寸.getValue());
		// 设置工作流注解
		params.setComment(entity -> this.getMessage("手工释放:")
                + this.getMessage("交易编号") + "("
                 + entity.getTradeId() + ")" 
                + this.getMessage("质押式正回购") + "-到期结算金额("
                +  entity.getMtradeAmt() + ")" );
		processParameter.setWorkFlowParameter(params);

		// 调用工作流事件
		String msg = ptlManualRelease002Service.start(processParameter, dto);
		// 返回工作流错误信息
		return new AjaxResult(msg);
	}
	
	 @ResponseBody
	 @RequestMapping("/findPageRepInfo")
	 public Page<PtlManualRelease001Dto> findPageRepInfo()
	        throws Exception {
	        return ptlManualRelease002Service.findPageRepInfo(RequestUtil.getParameters(getRequest()));
	 }
}
