/*
 * 文件名：FinAssetConfigPlan002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.asset.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;

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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productplanning.asset.dto.FinAssetConfigPlan001Dto;
import com.joyintech.tams.invest.product.productplanning.asset.service.FinAssetConfigPlan002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2018年11月1日
 * @see FinAssetConfigPlan002Controller
 * @since 1.0
 */
@Controller
@PageId("FinAssetConfigPlan002")
@RequestMapping("/invest/product/productplanning/asset/FinAssetConfigPlan002")
public class FinAssetConfigPlan002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinAssetConfigPlan002Service finAssetConfigPlan002Service;

    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 初始化页面
     *
     * @param Model model
     * @return String
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DictCode.资产大类, DictCode.调整类型);
    	FinAssetConfigPlan001Dto entity = new FinAssetConfigPlan001Dto();
    	String workflowId = getParam("workflowId");
        entity.setGenDate(platformDate.getSysDate());
        entity.setBenchDate(DateUtil.addDay(platformDate.getSysDate(), -1));
        model.addAttribute("platDate", platformDate.getSysDateStr());
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ProcessMark pro = finAssetConfigPlan002Service.getBusinessInfo(workflowId);
            if (pro != null) {
            	entity = (FinAssetConfigPlan001Dto)JsonUtils.json2Object(pro.getBusinessData(), FinAssetConfigPlan001Dto.class);
            }
            model.addAttribute( GlobalConstant.WORKFLOW_ID,workflowId);
        }
        else if (StringUtil.isNotEmpty(this.getParam("assetConfigCode"))) {
            String assetConfigCode = this.getParam("assetConfigCode");
            entity = finAssetConfigPlan002Service.getAssetConfigPlan(assetConfigCode);            
        }
        model.addAttribute(GlobalConstant.ENTITY, entity);

        return super.init(model);
    }

    /**
     * 得到资产配置计划列表
     *
     * @return Page<FinAssetConfigPlan001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinAssetConfigPlan001Dto> getFinAssetConfigPlanList() throws Exception{
    	/*if (StringUtil.isNotEmpty(this.getParam("workflowId"))) {
    		ProcessMark pro = finAssetConfigPlan002Service.getBusinessInfo(this.getParam("workflowId"));
            if (pro != null) {
            	FinAssetConfigPlan001Dto entity = (FinAssetConfigPlan001Dto)JsonUtils.json2Object(pro.getBusinessData(), FinAssetConfigPlan001Dto.class);
            	List<FinAssetConfigPlan001Dto> list=new ArrayList<FinAssetConfigPlan001Dto>();
            	if(entity!=null)
            		list = entity.getSelectedItem();
            	Page<FinAssetConfigPlan001Dto> page = new Page<>();
            	page.setRoot(list);
                page.setRecords(list.size());
            	return page;
            }
    	}*/
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finAssetConfigPlan002Service.getFinAssetConfigPlanList(param);
    }
    
    /**
     * 检查记录有效
     *
     * @param FinAssetConfigPlan001Dto dto
     * @param BindingResult result
     * @return AjaxResult结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinAssetConfigPlan001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存记录
     *
     * @param FinAssetConfigPlan001Dto dto
     * @param BindingResult br
     * @return AjaxResult结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated FinAssetConfigPlan001Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinAssetConfigPlan001Dto> processParameter = new ProcessParameter<FinAssetConfigPlan001Dto>();
        WorkFlowParameter<FinAssetConfigPlan001Dto> params = new WorkFlowParameter<FinAssetConfigPlan001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        //判断assetConfigCode为空
        if (StringUtil.isBlank(dto.getAssetConfigCode())) {
	        if (baseGetBusinessId != null) {
	            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
	            dto.setAssetConfigCode(baseGetBusinessId.buildBussinessCode(codeParameter));
	        }else {
	            //判断assetConfigCode不为空
                dto.setAssetConfigCode(IDGenerator.generate());
            }
        }
        Date configMonth = new SimpleDateFormat("yyyy-MM").parse(dto.getConfigMonth2());
        dto.setConfigMonth(configMonth);
        params.setBusinessCode(dto.getAssetConfigCode());
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产配置计划.getValue());
        params.setComment(entity -> this.getMessage("FinAssetConfigPlan001.assetConfigCode") + "("
            + entity.getAssetConfigCode() +")");
        processParameter.setWorkFlowParameter(params);
        String msg = finAssetConfigPlan002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
