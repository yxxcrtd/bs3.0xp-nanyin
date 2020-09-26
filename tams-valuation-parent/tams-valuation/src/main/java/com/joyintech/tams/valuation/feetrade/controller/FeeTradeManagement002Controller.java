/*
 * 文件名：FinProductDurDeal001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.feetrade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeDetailDto;
import com.joyintech.tams.valuation.feetrade.dto.FeeTradeManagementDto;
import com.joyintech.tams.valuation.feetrade.service.FeeTradeManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 费用交易管理
 * @version 1.0 2018年01月18日
 * @see FeeTradeManagement002Controller
 * @since 1.0
 */
@Controller
@PageId("FeeTradeManagement002")
@RequestMapping("/valuation/feetrade/FeeTradeManagement002")
public class FeeTradeManagement002Controller extends BaseController {
	
	@Autowired
	private FeeTradeManagement002Service feeTradeManagement002Service;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.登记托管机构, DictCode.是否);
		FeeTradeManagementDto dto = new FeeTradeManagementDto();
		Map<String, Object> map = RequestUtil.getPageParam();
    	String workflowId = getParam("workflowId");
    	String tradeId = getParam("tradeId");
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(tradeId)) {
        	dto.setFeeType(getParam("feeType"));
        	dto.setTradeId(tradeId);
        	Date date = new Date(Long.parseLong(getParam("payDate")));
        	dto.setPayDate(date);
        	model.addAttribute("entity", dto);
        	//DictCode.现金流二级类型.
        	List<DataDictItem> cashList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.现金流二级类型.getValue());
        	//DictCode.一次性费用类型
        	List<DataDictItem> feeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.一次性费用类型.getValue());
        	List<SelectOptionDto> feeTypeList = new ArrayList<SelectOptionDto>();
        	for(DataDictItem item : cashList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	for(DataDictItem item : feeList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	model.addAttribute("feeTypeSelect", JsonUtils.objectArray2Json(feeTypeList.toArray()));
        	RequestUtil.getSession().setAttribute("feeTradeObj", map);
        }
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
        	dto = feeTradeManagement002Service.getBussinessEntityByWorkflowId(workflowId);
        	model.addAttribute("entity", dto);
            model.addAttribute("workflowId", workflowId);
            //DictCode.现金流二级类型.
        	List<DataDictItem> cashList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.现金流二级类型.getValue());
        	//DictCode.一次性费用类型
        	List<DataDictItem> feeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.一次性费用类型.getValue());
        	List<SelectOptionDto> feeTypeList = new ArrayList<SelectOptionDto>();
        	for(DataDictItem item : cashList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	for(DataDictItem item : feeList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	model.addAttribute("feeTypeSelect", JsonUtils.objectArray2Json(feeTypeList.toArray()));
        }
        else {
        	//DictCode.现金流二级类型.
        	List<DataDictItem> cashList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.现金流二级类型.getValue());
        	//DictCode.一次性费用类型
        	List<DataDictItem> feeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCode.一次性费用类型.getValue());
        	List<SelectOptionDto> feeTypeList = new ArrayList<SelectOptionDto>();
        	for(DataDictItem item : cashList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	for(DataDictItem item : feeList) {
        		SelectOptionDto selectOpt = new SelectOptionDto();
        		selectOpt.setId(item.getItemCode());
        		selectOpt.setText(item.getItemValue());
        		feeTypeList.add(selectOpt);
        	}
        	//dto.setFeeType(feeType);
        	model.addAttribute("entity", dto);
        	model.addAttribute("feeTypeSelect", JsonUtils.objectArray2Json(feeTypeList.toArray()));
        }
		return super.init(model);
	}
	
	@ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FeeTradeManagementDto dto
    		, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
	
	@ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FeeTradeManagementDto dto
    		, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        //交易编号
        String tradeId = "";
        if(StringUtil.isNotEmpty(dto.getTradeId())) {
        	tradeId = dto.getTradeId();
        }
        else {
        	tradeId = IDGenerator.generate();
            dto.setTradeId(tradeId);
        }
        ProcessParameter<FeeTradeManagementDto> processParameter = new ProcessParameter<FeeTradeManagementDto>();
        WorkFlowParameter<FeeTradeManagementDto> params = new WorkFlowParameter<FeeTradeManagementDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(tradeId);
        params.setBusinessType(DictCodeListExt3.共通业务类型_费用交易管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "费用交易管理"+ "(" + entity.getFeeType()+")" +  "-" 
            		+ "支付日期"+ "(" + DateUtil.format(entity.getPayDate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = feeTradeManagement002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
	
	/**
     * 费用交易明细列表查询
     */
    @ResponseBody
    @RequestMapping("getFeeTradeDetailList")
    public Page<FeeTradeDetailDto> getFeeTradeDetailList() {
    	Page<FeeTradeDetailDto> page = new Page<>();
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(map.get(GlobalConstant.WORKFLOW_ID))) {
    		FeeTradeManagementDto feeTradeManagementDto = 
    				feeTradeManagement002Service.getBussinessEntityByWorkflowId((String)map.get(GlobalConstant.WORKFLOW_ID));
    		if(StringUtil.isNotEmpty(feeTradeManagementDto.getFeeTradeList())) {
    			page.setRoot(feeTradeManagementDto.getFeeTradeList());
    		}
    		else {
    			List<FeeTradeDetailDto> list = new ArrayList<>();
    			FeeTradeDetailDto dto = new FeeTradeDetailDto();
    			dto.setBookSetId(feeTradeManagementDto.getBookSetId());
    			dto.setFeeEndDate(feeTradeManagementDto.getFeeEndDate());
    			if(StringUtil.isNotEmpty(feeTradeManagementDto.getShouldPayAmt())) {
    				dto.setShouldPayAmt(feeTradeManagementDto.getShouldPayAmt());
    			}
    			else {
    				dto.setShouldPayAmt(BigDecimal.ZERO);
    			}
    			dto.setPayAmt(feeTradeManagementDto.getPayAmt());
    			dto.setIsCountOver(feeTradeManagementDto.getIsCountOver());
    			dto.setIsNeedTan(feeTradeManagementDto.getIsNeedTan());
    			list.add(dto);
    			page.setRoot(list);
    		}
    		return page;
    	}else if(StringUtil.isNotEmpty(map.get("tradeId"))){
    		Map<String, Object> feeTrade = (Map<String, Object>) RequestUtil.getSession().getAttribute("feeTradeObj");
    		FeeTradeDetailDto dto = new FeeTradeDetailDto();
    		dto.setBookSetId(feeTrade.get("bookSetId").toString());
    		dto.setSubBookSetId(feeTrade.get("subBookSetId").toString());
    		dto.setFeeEndDate(new Date(Long.parseLong(feeTrade.get("feeEndDate").toString())));
    		if(StringUtil.isNotEmpty(feeTrade.get("shouldPayAmt"))) {
    			dto.setShouldPayAmt(new BigDecimal(feeTrade.get("shouldPayAmt").toString()));
			}
    		else {
    			dto.setShouldPayAmt(BigDecimal.ZERO);
    		}
    		dto.setPayAmt(new BigDecimal(feeTrade.get("payAmt").toString()));
    		dto.setIsCountOver(feeTrade.get("isCountOver").toString());
    		if(StringUtil.isNotEmpty(feeTrade.get("isNeedTan"))) {
    			dto.setIsNeedTan(feeTrade.get("isNeedTan").toString());
    		}
    		else {
    			dto.setIsNeedTan(DictCodeList.是否_否.getValue());
    		}
    		List<FeeTradeDetailDto> list = new ArrayList<>();
    		list.add(dto);
    		page.setRoot(list);
    		page.setTotal(1);
        	return page;
        }else {
        	return page;
        }
    }
    
    /**
     * 获取账套信息
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getBookSetList")
    @ResponseBody
    public List<SelectOptionDto> getBookSetList(@RequestParam Map<String, Object> params) {
    	params.put("noBooksetType", DictCodeList.账套类别_账套模板.getValue());
    	List<SelectOptionDto> list = feeTradeManagement002Service.getBookSetList(params);
    	return list;
    }
    
    /**
     * 获取应付金额
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getShouldPayAmt")
    @ResponseBody
    public BigDecimal getShouldPayAmt(@RequestBody Map<String, Object> params) {
    	return feeTradeManagement002Service.getShouldPayAmt(params);
    }
    
    /**
     * 获取子账套信息
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("getSubBookSetList")
    @ResponseBody
    public List<SelectOptionDto> getSubBookSetList(@RequestParam String bookSetId) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("bookSetId", bookSetId);
    	List<SelectOptionDto> list = feeTradeManagement002Service.getSubBookSetList(map);
    	return list;
    }
}
