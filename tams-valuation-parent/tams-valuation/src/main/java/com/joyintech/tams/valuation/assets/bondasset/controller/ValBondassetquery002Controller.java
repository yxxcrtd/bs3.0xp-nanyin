/*
 * 文件名：ValBondassetquery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.bondasset.controller;

import java.io.IOException;
import java.util.*;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.assets.bondasset.dto.*;
import com.joyintech.tams.valuation.assets.bondasset.service.ValBondassetquery002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 债券类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValBondassetquery002Controller
 * @since 2.0
 */
@Controller
@PageId("ValBondassetquery002")
@RequestMapping("/valuation/assets/bondasset/ValBondassetquery002")
public class ValBondassetquery002Controller extends BaseController {
    
    /**
     * ValBondassetquery002Service
     */
    @Autowired
    private ValBondassetquery002Service valBondassetquery002Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        ValBondassetquery002Dto dto;
        String couponType=this.getParam("couponType");
        model.addAttribute("couponType", couponType);
        
        String finprodId=this.getParam("finprodId");

        this.setSelectOptionToModel(model, DictCode.境内外);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
        this.setSelectOptionToModel(model, DictCode.计息基础);
        this.setSelectOptionToModel(model, DictCode.息票品种);
        this.setSelectOptionToModel(model, DictCode.利率类型);
        this.setSelectOptionToModel(model, DictCode.推算方式);
        this.setSelectOptionToModel(model, DictCode.营业日准则);
        this.setSelectOptionToModel(model, DictCode.债券类型);
        this.setSelectOptionToModel(model, DictCode.频率);
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.行权方式);
        this.setSelectOptionToModel(model, DictCode.含权债摊销日);
        model.addAttribute(DictCode.交易日历.getValue(), getTradeCalendar());
        model.addAttribute(DictCode.交易日历.getValue() + "_default", null);
        
        model.addAttribute("ISSUER", getIssure());
        model.addAttribute("ISSUER" + "_default", null);
    
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if(StringUtil.isEmpty(workflowId)) {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("finprodId", finprodId);
            dto = valBondassetquery002Service.queryBondasetByfinprodId(map);
            // 是否有交易数据标识
            if (valBondassetquery002Service.selectDealCount(finprodId) > 0) {
                model.addAttribute("hasDeals","1");
            }
        } else {
            dto = valBondassetquery002Service.getBussinessEntityByWorkflowId(workflowId);
            dto.setBondtypeCode(dto.getBondtype());
            dto.setCouponTypeCode(dto.getCouponType());
            model.addAttribute(GlobalConstant.WORKFLOW_ID,workflowId);
        }
      
      //  债券行权交易后，行权摊销日设置字段不可修改
      Map<String, Object> map = Maps.newHashMap();
      map.put("finprodid", dto.getFinprodId());
      map.put("ps", StringUtils.join(new String[]{
//    		  DictCodeList.交易方向_行权.getValue(), 
          DictCodeList.交易方向_行权_回售.getValue(), DictCodeList.交易方向_行权_赎回.getValue()}, ","));
      if (valBondassetquery002Service.selectDealCount(map) > 0) {
            model.addAttribute("isEditExeDate", true);
      } 
      
      model.addAttribute("entity", dto);
      return super.init(model);
  }
    
    /**
     * 获取发行人信息
     * 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private String getIssure() {
        return valBondassetquery002Service.getIssure();
    }

    /**
     * 查询交易日历下拉框
     * 
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private String getTradeCalendar() {
        return valBondassetquery002Service.getTradeCalendar();
    }

    /**
     * 详情页表单数据的获取-行权信息
     * 
     */
    @RequestMapping("bondxqinfo")
    @ResponseBody
    public List<ValBondassetquery002Dto> queryXqinfo() {
        
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if(StringUtil.isEmpty(workflowId)) {
            String finprodId = this.getParam("finprodId");
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId", finprodId);
            return valBondassetquery002Service.queryXqinfo(map);
        } else {
            List<ValBondassetXQDto> xqList = valBondassetquery002Service.getBussinessEntityByWorkflowId(workflowId)
                                                .getComponentDto().getXqList();
            List<ValBondassetquery002Dto> retList = new ArrayList<ValBondassetquery002Dto>();
            for (ValBondassetXQDto xq : xqList) {
                ValBondassetquery002Dto dto = new ValBondassetquery002Dto();
                dto.setApplydatestr(DateUtil.format(xq.getApplydatestr()));
                dto.setApplydateend(DateUtil.format(xq.getApplydateend()));
                dto.setXqmode(xq.getXqmode());
                dto.setExerciseDate(DateUtil.format(xq.getExerciseDate()));
                dto.setXqnetprice(xq.getXqnetprice());
                dto.setXqclause(xq.getXqclause());
                retList.add(dto);
            }
            return retList;
        }
        
    }
    
    /**
     * 详情页表单数据的获取-债券利率报价信息
     * 
     */
    @RequestMapping("bondintrate")
    @ResponseBody
    public List<ValBondassetquery002Dto> queryintrate() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if(StringUtil.isEmpty(workflowId)) {
            String finprodId = this.getParam("finprodId");
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId", finprodId);
            return valBondassetquery002Service.queryintrate(map);
        } else {
            List<ValBondassetBJDto> bjList = valBondassetquery002Service.getBussinessEntityByWorkflowId(workflowId)
                                                .getComponentDto().getBjList();
            List<ValBondassetquery002Dto> retList = new ArrayList<ValBondassetquery002Dto>();
            for (ValBondassetBJDto bj : bjList) {
                ValBondassetquery002Dto dto = new ValBondassetquery002Dto();
                dto.setEfdate(DateUtil.format(bj.getEfdate()));
                dto.setRate2(bj.getRate2());
                retList.add(dto);
            }
            return retList;
        }
    }
    
    /**
     * 详情页表单数据的获取-付息计划列表
     * 
     */
    @RequestMapping("bondintpay")
    @ResponseBody
    public List<ValBondassetquery002Dto> queryintpay() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if(StringUtil.isEmpty(workflowId)) {
            String finprodId = this.getParam("finprodId");
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId", finprodId);
            return valBondassetquery002Service.queryintpay(map);
        } else {
            List<ValBondassetFXDto> fxList = valBondassetquery002Service.getBussinessEntityByWorkflowId(workflowId)
                                                .getComponentDto().getFxList();
            List<ValBondassetquery002Dto> retList = new ArrayList<ValBondassetquery002Dto>();
            for (ValBondassetFXDto fx : fxList) {
                ValBondassetquery002Dto dto = new ValBondassetquery002Dto();
                dto.setTheorydatestr(DateUtil.format(fx.getTheorydatestr()));
                dto.setTheorydateend(DateUtil.format(fx.getTheorydateend()));
                dto.setActdatestr(DateUtil.format(fx.getActdatestr()));
                dto.setActdateend(DateUtil.format(fx.getActdateend()));
                dto.setPlandate(DateUtil.format(fx.getPlandate()));
                dto.setPlanintamt(fx.getPlanintamt());
                retList.add(dto);
            }
            return retList;
        }
    }
    
    /**
     * 详情页表单数据的获取-还本计划列表
     * 
     */
    @RequestMapping("bondrepay")
    @ResponseBody
    public List<ValBondassetquery002Dto> queryrepay() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if(StringUtil.isEmpty(workflowId)) {
            String finprodId = this.getParam("finprodId");
            Map<String, Object> map = new HashMap<>();
            map.put("finprodId", finprodId);
            return valBondassetquery002Service.queryrepay(map);
        } else {
            List<ValBondassetHBDto> hbList = valBondassetquery002Service.getBussinessEntityByWorkflowId(workflowId)
                                                .getComponentDto().getHbList();
            List<ValBondassetquery002Dto> retList = new ArrayList<ValBondassetquery002Dto>();
            for (ValBondassetHBDto hb : hbList) {
                ValBondassetquery002Dto dto = new ValBondassetquery002Dto();
                dto.setJsdatestr(DateUtil.format(hb.getJsdatestr()));
                dto.setJsdateend(DateUtil.format(hb.getJsdateend()));
                dto.setPaydate(DateUtil.format(hb.getPaydate()));
                dto.setRemoney(hb.getRemoney());
                retList.add(dto);
            }
            return retList;
        }
    }
    
    /**
     * 详情页表单数据的获取-现金流列表
     * 
     */
    @RequestMapping("bondcashflow")
    @ResponseBody
    public List<ValBondassetquery002Dto> querycashflow(){
        String finprodId = this.getParam("finprodId");
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        return valBondassetquery002Service.querycashflow(map);
    }
    
    @ResponseBody
    @RequestMapping("doUpdateCheck")
    public AjaxResult doUpdateCheck(ValBondassetquery002Dto dto, BindingResult result,String operateType) throws Exception {
    	Map<String, Object> paramMap = RequestUtil.getParameters(getRequest());
        int res = valBondassetquery002Service.workflowCheck(dto);
        if (res > 0) {
            return new AjaxResult("该资产存在流程中数据，不能修改！", MessageType.错误消息);
        }
        List<ValBondassetFXDto> fxList = JsonUtils.json2List(String.valueOf(paramMap.get("fxList")), ValBondassetFXDto.class);
        List<ValBondassetHBDto> hbList = JsonUtils.json2List(String.valueOf(paramMap.get("hbList")), ValBondassetHBDto.class);
        AjaxResult checkResult = null;
        checkResult = checkFxList(fxList, dto);
        if (checkResult != null) {
        	return checkResult;
        }
        checkResult = checkHbList(hbList, dto);
        if (checkResult != null) {
        	return checkResult;
        }
        return valBondassetquery002Service.doUpdateCheck(result, dto);
    }
    
    /**
     * 获取行权方式下拉框
     * 
     * @return 分支序号下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getExerciseType")
    public List<SelectOptionDto> getExerciseType()
        throws Exception {
        return getSelectDataFromDataDic(DictCode.行权方式, null).getOptions();
    }

    /**
     * 修改凭证摘要
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @throws IOException 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate")
    public AjaxResult doUpdate(ValBondassetquery002Dto dto) throws IOException {
        Map<String, Object> paramMap = RequestUtil.getParameters(getRequest());
        ProcessParameter<ValBondassetquery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValBondassetquery002Dto> params = new WorkFlowParameter<>();
        List<ValBondassetXQDto> xqList = JsonUtils.json2List(String.valueOf(paramMap.get("xqList")), ValBondassetXQDto.class);
        List<ValBondassetBJDto> bjList = JsonUtils.json2List(String.valueOf(paramMap.get("bjList")), ValBondassetBJDto.class);
        List<ValBondassetFXDto> fxList = JsonUtils.json2List(String.valueOf(paramMap.get("fxList")), ValBondassetFXDto.class);
        List<ValBondassetHBDto> hbList = JsonUtils.json2List(String.valueOf(paramMap.get("hbList")), ValBondassetHBDto.class);
        // 业务代码
        params.setBusinessCode(dto.getBondid());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产信息.getValue());
        // 页面id
        params.setPageId(this.getPageId());
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 流程业务说明
        params.setComment(entity -> WorkFlowUtil.concatComment("债券类资产信息修改：债券代码",
            entity.getBondid()//资产代码
        ));
        proParam.setWorkFlowParameter(params);
        
        String msg = getMessage("COMMON_I_0001");
        try {
            dto.getComponentDto().setXqList(xqList);
            dto.getComponentDto().setBjList(bjList);
            dto.getComponentDto().setFxList(fxList);
            dto.getComponentDto().setHbList(hbList);
            msg = valBondassetquery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }
    
	@ResponseBody
    @RequestMapping("doAdd")
    public AjaxResult doAdd(@Valid @RequestBody ValBondassetquery002Dto dto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(new ValBondassetquery002Dto());
        return ajaxResult;
    }
	
	/**
     * 还本计划支付日校验
     * @param hbList
     * @param dto
     * @return
     */
    private AjaxResult checkHbList(List<ValBondassetHBDto> hbList, ValBondassetquery002Dto dto) {
		for (int i = 0; i < hbList.size(); i ++) {
			ValBondassetHBDto hb = hbList.get(i);
			Date calcDate = valBondassetquery002Service.getNextWorkDay(hb.getJsdateend(), dto.getTradingtime());
			// 计息截止日为非节假日 或者 营业日准则选择不调整时
        	// if (calcDate.compareTo(hb.getJsdateend()) == 0 || "04".equals(dto.getYyrzz())) {
			if (calcDate.compareTo(hb.getJsdateend()) == 0 || "04".equals(dto.getEndTdayRule())) {
        		// 支付日不等于计息截止日
        		if (hb.getPaydate().compareTo(hb.getJsdateend()) != 0) {
        			return new AjaxResult("第" + (i + 1) + "条还本计划的支付日不符合规则！",MessageType.错误消息);
        		}
        	}
        	// 当营业日准则选择向后调整且计息截止日为节假日时
        	// if ("02".equals(dto.getYyrzz()) && calcDate.compareTo(hb.getJsdateend()) != 0) {
			if ("02".equals(dto.getEndTdayRule()) && calcDate.compareTo(hb.getJsdateend()) != 0) {
        		// 支付日不等于计息截止日后的第一个工作日
        		if (hb.getPaydate().compareTo(calcDate) != 0) {
        			return new AjaxResult("第" + (i + 1) + "条还本计划的支付日不符合规则！",MessageType.错误消息);
        		}
        	}
		}
		return null;
	}

    /**
     * 付息计划支付日校验
     * @param fxList
     * @param dto
     * @return
     */
	private AjaxResult checkFxList(List<ValBondassetFXDto> fxList, ValBondassetquery002Dto dto) {
    	for (int i = 0; i < fxList.size(); i ++) {
        	ValBondassetFXDto fx = fxList.get(i);
        	Date calcDate = valBondassetquery002Service.getNextWorkDay(fx.getActdateend(), dto.getTradingtime());
        	// 计息截止日为非节假日 或者 营业日准则选择不调整时
        	// if (calcDate.compareTo(fx.getActdateend()) == 0 || "04".equals(dto.getYyrzz())) {
        	if (calcDate.compareTo(fx.getActdateend()) == 0 || "04".equals(dto.getEndTdayRule())) {
        		// 支付日不等于计息截止日
        		if (fx.getPlandate().compareTo(fx.getActdateend()) != 0) {
        			return new AjaxResult("第" + (i + 1) + "条付息计划的支付日不符合规则！",MessageType.错误消息);
        		}
        	}
        	// 当营业日准则选择向后调整且计息截止日为节假日时
        	// if ("02".equals(dto.getYyrzz()) && calcDate.compareTo(fx.getActdateend()) != 0) {
        	if ("02".equals(dto.getEndTdayRule()) && calcDate.compareTo(fx.getActdateend()) != 0) {
        		// 支付日不等于计息截止日后的第一个工作日
        		if (fx.getPlandate().compareTo(calcDate) != 0) {
        			return new AjaxResult("第" + (i + 1) + "条付息计划的支付日不符合规则！",MessageType.错误消息);
        		}
        	}
        }
		return null;
	}
}
