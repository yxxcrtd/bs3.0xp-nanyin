/*
 * 文件名：FinNCProductBuild002Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.finncproductbuild.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.finncproductbuild.service.FinNCProductBuild001Service;
import com.joyintech.tams.valuation.finncproductbuild.service.FinNCProductBuild002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 新建产品信息
 * 
 * @author tanxuan
 * @version 2.0 2020年2月13日
 * @see FinNCProductBuild002Contoller
 * @since 2.0
 */
@Controller
@PageId("FinNCProductBuild002")
@RequestMapping("/valuation/finncproductbuild/FinNCProductBuild002")
public class FinNCProductBuild002Controller extends BaseController {
	
    @Autowired
    private FinNCProductBuild001Service finNCProductBuild001Service;
    
    @Autowired
    private FinNCProductBuild002Service finNCProductBuild002Service;

    @Override
    public String init(Model model) {
    	String finprodId = this.getParam("finprodId");
    	String operateType = getParam("operateType");
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		FinNCProductBuild001Dto dto = new FinNCProductBuild001Dto();
		
		if (StringUtils.isNotEmpty(finprodId)) {// 非新建
			Map<String, Object> params = Maps.newHashMap();
			params.put("finprodId", finprodId);
			dto = finNCProductBuild001Service.findByFinprodId(params);
			
//			params.put("paramCode", ValDictCodeList.账套配置表参数项_份额小数位尾数处理.getValue());
//	        List<BokBooksetExt> bokBookSetExtList1 = finNCProductBuild001Service.queryBooksetExtByKey(params);
//	        if(StringUtil.isEmptyList(bokBookSetExtList1)){
//	        	model.addAttribute("valuationRoundRule", DictCodeList.估值舍入规则_舍位.getValue());
//	        }else{
//	        	model.addAttribute("valuationRoundRule", bokBookSetExtList1.get(0).getParamValue());
//	        }
			params.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
	        List<ValProductInfoFeeDto> list = finNCProductBuild001Service.queryfloatFeeList(params);
	        if (list.size() > 0) {
	            list.get(0).setFeeName(DictCodeListExt1.现金流二级类型_浮动管理费.getValue());//modify by dongjun on 20181226
	            dto.setValProductInfoFeeDto(list.get(0));
	        }
	        
	        // 查询账套配置表，获取 份额小数位
	        params.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
	        List<BokBooksetExt> listBokBooksetExt = finNCProductBuild001Service.queryBooksetExt(params);
	        for (BokBooksetExt item : listBokBooksetExt) {
	            if (ValDictCodeList.账套配置表参数项_份额小数位位数.getValue().equals(item.getParamCode())) {
	                dto.setShareDecDigits(item.getParamValue());
	            }
	            if (ValDictCodeList.账套配置表参数项_份额小数位尾数处理.getValue().equals(item.getParamCode())) {
	                dto.setShareDecType(item.getParamValue());
	            }
	        }
			
		}else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
		    dto = finNCProductBuild001Service.getBussinessEntityByWorkflowId(workflowId);
		    ValProductInfoFeeDto feeDto = new ValProductInfoFeeDto();
		    feeDto.setActRule(dto.getActRule());
		    feeDto.setBaseType(dto.getBaseType());
		    feeDto.setActVdateType(dto.getActVdateType());
		    feeDto.setFirCalculaDate(dto.getFirCalculaDate());
		    feeDto.setEndCalculaDate(dto.getEndCalculaDate());
		    feeDto.setAccrCycle(dto.getAccrCycle());
		    dto.setValProductInfoFeeDto(feeDto);
		}

        super.setSelectOptionToModel(model, DictCode.金融产品类型);
        super.setSelectOptionToModel(model, DictCode.收益类型);
        super.setSelectOptionToModel(model, DictCode.币种);
        super.setSelectOptionToModel(model, DictCode.发行方式);
        super.setSelectOptionToModel(model, DictCode.估值舍入规则);
        super.setSelectOptionToModel(model, DictCode.计息基础);
        super.setSelectOptionToModel(model, DictCode.收益标识);
        super.setSelectOptionToModel(model, DictCode.生效规则);
        super.setSelectOptionToModel(model, DictCode.频率);
        super.setSelectOptionToModel(model, DictCode.净值日规则);
        super.setSelectOptionToModel(model, DictCode.基数类型);
        super.setSelectOptionToModel(model, DictCode.基数日期类型);
        super.setSelectOptionToModel(model, DictCode.开放类型);
        super.setSelectOptionToModel(model, DictCode.现金流二级类型);
        super.setSelectOptionToModel(model, DictCode.资金账户类型);
        super.setSelectOptionToModel(model, DictCode.是否);
        super.setSelectOptionToModel(model, DictCode.行内行外);
        dto.setOperateType(operateType);
        model.addAttribute("entity", dto);
        return super.init(model);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryFeeList")
    @ResponseBody
    public List<ValProductInfoFeeDto> queryFeeList()
        throws Exception {
        return finNCProductBuild001Service.queryFeeList(RequestUtil.getPageParam());
    }
    
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinNCProductBuild001Dto dto, BindingResult bindingResult)
			throws Exception {
		String msg = StringUtil.EMPTY;
		String operateType = dto.getOperateType();
		
		if(StringUtils.isEmpty(dto.getFinprodId())) {
			bindingResult.rejectValue("finprodId", "NotBlank", "产品代码不能为空！");
		} else if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel()) && !bindingResult.hasErrors()) {// 新建唯一性验证：主键
		    	dto.setFinprodId(dto.getFinprodType()+"_"+dto.getFinprodId());
				FinNCProductBuild001Dto entity = finNCProductBuild002Service
						.findByFinprodId(dto.getFinprodId());
				if (entity != null) {
					msg = "该产品信息已存在！";
					return new AjaxResult(msg, MessageType.错误消息);
				}
		}
		
		if(DictCodeList.是否_是.getValue().equals(dto.getIsAccrFloManageFee())) {
			if(StringUtils.isEmpty(dto.getActRule())) {
				bindingResult.rejectValue("actRule", "NotBlank", "基准生效规则不能为空！");
			}
			if(StringUtils.isEmpty(dto.getBaseType())) {
				bindingResult.rejectValue("baseType", "NotBlank", "计费基数不能为空！");
			}
			if(StringUtils.isEmpty(dto.getActVdateType())) {
				bindingResult.rejectValue("actVdateType", "NotBlank", "资产净值日不能为空！");
			} 
			if(StringUtils.isEmpty(dto.getFirCalculaDate())) {
				bindingResult.rejectValue("firCalculaDate", "NotBlank", "首次计提日不能为空！");
			} 
			if(StringUtils.isEmpty(dto.getEndCalculaDate())) {
				bindingResult.rejectValue("endCalculaDate", "NotBlank", "计提结束日不能为空！");
			} 
			if(StringUtils.isEmpty(dto.getAccrCycle())) {
				bindingResult.rejectValue("accrCycle", "NotBlank", "区间规则不能为空！");
			} 
		} 
		
		if(StringUtils.isNotEmpty(dto.getFirCalculaDate()) && StringUtils.isNotEmpty(dto.getEndCalculaDate())) {
			if(dto.getEndCalculaDate().compareTo(dto.getFirCalculaDate()) < 0){
				return new AjaxResult("计提结束日必须大于或等于首次计提日", MessageType.错误消息);
			}
		}
		
		if(!dto.getListValProductInfoFeeDto_FD1().isEmpty()) {
	        List<ValProductInfoFeeDto> list = dto.getListValProductInfoFeeDto_FD1();
			for(ValProductInfoFeeDto item:list){
				if(item.getJtEndDate().compareTo(item.getJtStrDate()) < 0){
					return new AjaxResult("计提周期结束日必须大于计提周期开始日", MessageType.错误消息);
				}
			}
		} 
		
		return commonCheck(bindingResult, operateType);
	}
	
	/**
	 * 新建/修改, 并启动流程
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
	public AjaxResult save(@RequestBody FinNCProductBuild001Dto dto, BindingResult bindingResult) {
        
	    String msg = StringUtil.EMPTY;

		ProcessParameter<FinNCProductBuild001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<FinNCProductBuild001Dto> workFlowParameter = new WorkFlowParameter<>();
		
		try {
		    workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_产品信息管理.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
            		entity.getFinprodId(), "产品信息新建"));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            finNCProductBuild001Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
		} catch (Exception e) {
			this.getLogger().error("启动产品信息管理流程失败：", e);
            msg = e.getMessage();
		}
		
		return new AjaxResult(msg);
	}

    /**
     * 母产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getpFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getpFinprodId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finNCProductBuild002Service.getpFinprodId(map);
    }
    
    /**
     * 获取机构信息
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getCustomerId")
    @ResponseBody
    public Page<SelectOptionDto> getCustomerId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        return finNCProductBuild002Service.getCustomerId(map);
    }
    
}
