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
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.dto.PtlManualRelease003Dto;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.service.PtlManualRelease003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 手动释放头寸
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月09日
 * @see PtlManualRelease003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManualRelease003")
@RequestMapping("/invest/portfolio/ptlmanualrelease/PtlManualRelease003")
public class PtlManualRelease003Controller extends BaseController{
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlManualRelease003Service ptlManualRelease003Service;
  
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){String tradeId = getParam("tradeId");

	// 是否存在与工作流中
	String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
	if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
		PtlManualRelease003Dto dto = ptlManualRelease003Service.getBussinessEntityByWorkflowId(workflowId);
		model.addAttribute("businessId", workflowId);
		model.addAttribute(GlobalConstant.ENTITY, dto);
     	model.addAttribute("tradeId",dto.getTradeId());
        Map<String, Object> pageParam =new HashMap<String, Object>();
        pageParam.put("tradeId", dto.getTradeId());
        List<PtlManualRelease003Dto> replist= ptlManualRelease003Service.findPageRepInfoList(pageParam);
    	//_dto.setTradeId(getParam("tradeId"));
    	model.addAttribute("entity", dto);

        model.addAttribute("entity_replist",replist);
	}else if(StringUtil.isNotEmpty(tradeId)){
    	//model.addAttribute("entity", getParam("data"));
    	PtlManualRelease003Dto _dto=new PtlManualRelease003Dto();
    	_dto=ptlManualRelease003Service.selectTradeInfo(tradeId);
        ptlManualRelease003Service.findPageRepInfo(RequestUtil.getParameters(getRequest()));
        Map<String, Object> pageParam =new HashMap<String, Object>();
        pageParam.put("tradeId", tradeId);
        List<PtlManualRelease003Dto> replist= ptlManualRelease003Service.findPageRepInfoList(pageParam);
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
	public AjaxResult save(@RequestBody PtlManualRelease003Dto dto, BindingResult br) throws Exception {
		ProcessParameter<PtlManualRelease003Dto> processParameter = new ProcessParameter<PtlManualRelease003Dto>();
		WorkFlowParameter<PtlManualRelease003Dto> params = new WorkFlowParameter<PtlManualRelease003Dto>();
		params.setElementId(dto.getElementId());
		params.setPageId(this.getPageId());
		params.setBusinessCode((entity) -> entity.getTradeId());
		params.setServiceName("PtlManualRelease003Service");
		params.setBusinessType(DictCodeList.共通业务类型_手动释放头寸.getValue());
		// 设置工作流注解
		params.setComment(entity -> this.getMessage("手工释放:")
                + this.getMessage("交易编号") + "("
                 + entity.getTradeId() + ")" 
                + this.getMessage("质押式逆回购") + "-到期结算金额("
                 +  entity.getMtradeAmt() + ")" );
		processParameter.setWorkFlowParameter(params);

		// 调用工作流事件
		String msg = ptlManualRelease003Service.start(processParameter, dto);
		// 返回工作流错误信息
		return new AjaxResult(msg);
	}
	
	  @ResponseBody
	    @RequestMapping("/findPageRepInfo")
	    public Page<PtlManualRelease003Dto> findPageRepInfo()
	        throws Exception {
	        return ptlManualRelease003Service.findPageRepInfo(RequestUtil.getParameters(getRequest()));
	    }
}
