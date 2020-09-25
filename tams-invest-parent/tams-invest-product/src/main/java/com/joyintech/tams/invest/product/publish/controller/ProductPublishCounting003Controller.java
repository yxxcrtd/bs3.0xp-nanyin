package com.joyintech.tams.invest.product.publish.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.invest.product.publish.dto.ProductPublishCounting001Dto;
import com.joyintech.tams.invest.product.publish.service.ProductPublishCounting002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 产品发行规划试算-新建
 * @version 1.0 2018年12月18日
 * @see ProductPublishCounting003Controller
 * @since 1.0
 */
@Controller
@PageId("ProductPublishCounting003")
@RequestMapping("/invest/product/publish/ProductPublishCounting003")
public class ProductPublishCounting003Controller extends BaseController {
	
	 @Autowired
	 private PlanningCommonService planningCommonService;
	 
	 @Autowired
	 private ProductPublishCounting002Service productPublishCounting002Service;
	 
	 @Autowired(required=false)
	 private IPlatformDate iPlatformDate;
	 
	/**
     * 页面初始化
     * @see
     * @since 1.0
     */
	@Override
	@RequestMapping(PAGE_INIT)
    public String init(Model model) {
    	String listObj = getParam("strategyList");
        model.addAttribute("ISSUE_PLAN_STRATEGY", listObj);
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ProductPublishCounting001Dto dto
    		, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
	/**
	 * 试算接口 （返回试算数据）
	 */
	@ResponseBody
	@RequestMapping("getPublishCountingList")
	public List<ProductPublishCounting001Dto> getPublishCountingList() {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		List<Object> list = JSONObject.parseObject(pageParam.get("prdIssuePlanStrategyList").toString(), List.class);
		List<ProductPublishCounting001Dto> dtoList = new ArrayList<ProductPublishCounting001Dto>();
		for (Object obj : list) {
			ProductPublishCounting001Dto dto = JSONObject.parseObject(obj.toString(), ProductPublishCounting001Dto.class);
			dtoList.add(dto);
		}
		ProductPublishCounting001Dto dto = new ProductPublishCounting001Dto();
		dto.setPrdIssuePlanStrategyList(dtoList);
		return productPublishCounting002Service.getPublishCountingList(dto);
	}
}
