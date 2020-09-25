package com.joyintech.tams.invest.product.publish.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.invest.product.publish.dto.ProductPublishCounting001Dto;
import com.joyintech.tams.invest.product.publish.service.ProductPublishCounting002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品发行规划试算-新建
 * @version 1.0 2018年12月18日
 * @see ProductPublishCounting002Controller
 * @since 1.0
 */
@Controller
@PageId("ProductPublishCounting002")
@RequestMapping("/invest/product/publish/ProductPublishCounting002")
public class ProductPublishCounting002Controller extends BaseController {
	
	 @Autowired
	 private PlanningCommonService planningCommonService;
	 
	 @Autowired
	 private ProductPublishCounting002Service productPublishCounting002Service;
	 
	 @Autowired(required=false)
	 private IPlatformDate iPlatformDate;

    /**
     * 主键生成类
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
	 
	/**
     * 页面初始化
     * @see
     * @since 1.0
     */
	@Override
    public String init(Model model) {
    	ProductPublishCounting001Dto productPublishCounting001Dto = new ProductPublishCounting001Dto();
    	String workflowId = getParam("workflowId");
    	String prdIssueplanCode = getParam("prdIssueplanCode");
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(prdIssueplanCode)) {
        	productPublishCounting001Dto = productPublishCounting002Service.getPrdIssuePlanInfoByCode(prdIssueplanCode);
        	model.addAttribute("entity", productPublishCounting001Dto);
        	Map<String, Object> map = new HashMap<String, Object>();
        	List<SelectOptionDto> list = planningCommonService.getPtlInvStrategyList(map);
        	model.addAttribute("ISSUE_PLAN_STRATEGY", JsonUtils.objectArray2Json(list.toArray()));
        }
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
        	productPublishCounting001Dto = productPublishCounting002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", productPublishCounting001Dto);
            model.addAttribute("workflowId", workflowId);
            Map<String, Object> map = new HashMap<String, Object>();
        	List<SelectOptionDto> list = planningCommonService.getPtlInvStrategyList(map);
        	model.addAttribute("ISSUE_PLAN_STRATEGY", JsonUtils.objectArray2Json(list.toArray()));
        }
        else {
        	Date date = iPlatformDate.getSysDate();
        	productPublishCounting001Dto.setVdate(date);
        	Calendar cal = Calendar.getInstance();     
            cal.setTime(date); 	
            cal.set(Calendar.MONTH, 11);
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
            productPublishCounting001Dto.setEdate(cal.getTime());
        	model.addAttribute("entity", productPublishCounting001Dto);
        	Map<String, Object> map = new HashMap<String, Object>();
        	List<SelectOptionDto> list = planningCommonService.getPtlInvStrategyList(map);
        	model.addAttribute("ISSUE_PLAN_STRATEGY", JsonUtils.objectArray2Json(list.toArray()));
        }
        
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ProductPublishCounting001Dto dto
    		, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ProductPublishCounting001Dto dto
    		, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        //产品发行规划编号
        String prdIssueplanCode = "";
        if(StringUtil.isNotEmpty(dto.getPrdIssueplanCode())) {
        	prdIssueplanCode = dto.getPrdIssueplanCode();
        }
        else {
        	/*prdIssueplanCode = IDGenerator.generate();
            dto.setPrdIssueplanCode(prdIssueplanCode);*/
            //BSTOT-202 guyunfei 20200106 start
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessExtType(
            		DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_发行规划试算));
            if (baseGetBusinessId != null) {
                prdIssueplanCode = baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null
                        ? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter)
                        : IDGenerator.generate();
                dto.setPrdIssueplanCode(prdIssueplanCode);
            }
            //BSTOT-202 guyunfei 20200106 end
        }
        ProcessParameter<ProductPublishCounting001Dto> processParameter = new ProcessParameter<ProductPublishCounting001Dto>();
        WorkFlowParameter<ProductPublishCounting001Dto> params = new WorkFlowParameter<ProductPublishCounting001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(prdIssueplanCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_发行规划试算.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> "规划名称"+ "(" + entity.getPrdIssueplanName()+")" +  "-" 
            		+ "起始日"+ "(" + DateUtil.format(entity.getVdate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")"
                    + "-" + "结束日"+ "(" + DateUtil.format(entity.getEdate(),DateUtil.DEFAULT_DATE_FORMAT_EN) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = productPublishCounting002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 存续产品系列列表查询
     */
    @ResponseBody
    @RequestMapping("getPrdPlanInfoList")
    public Page<PrdPlanDetail> getPrdPlanInfoList() {
    	Page<PrdPlanDetail> page = new Page<>();
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(map.get(GlobalConstant.WORKFLOW_ID))) {
    		ProductPublishCounting001Dto productPublishCounting001Dto = 
    				productPublishCounting002Service.getBussinessEntityByWorkflowId((String)map.get(GlobalConstant.WORKFLOW_ID));
    		page.setRoot(productPublishCounting001Dto.getPrdPlanInfoList());
    		return page;
    	}else if(StringUtil.isNotEmpty(this.getParam("prdIssueplanCode"))){
    		List<PrdPlanDetail> list = planningCommonService.getPrdPlanInfoList(map);
        	page.setRoot(list);
        	return page;
        }else {
        	List<PrdPlanDetail> list = planningCommonService.getPrdPlanInfoList(map);
        	page.setRoot(list);
        	return page;
        }
    }
    /**
     * 策略列表
     */
    @ResponseBody
    @RequestMapping("getPrdPlanStrategyList")
    public Page<ProductPublishCounting001Dto> getPrdPlanStrategyList() {
    	Page<ProductPublishCounting001Dto> page = new Page<>();
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(map.get(GlobalConstant.WORKFLOW_ID))) {
    		ProductPublishCounting001Dto productPublishCounting001Dto = 
    				productPublishCounting002Service.getBussinessEntityByWorkflowId((String)map.get(GlobalConstant.WORKFLOW_ID));
    		page.setRoot(productPublishCounting001Dto.getPrdIssuePlanStrategyList());
    		return page;
    	}else if(StringUtil.isNotEmpty(map.get("prdIssueplanCode"))){
    		List<ProductPublishCounting001Dto> list = 
    				productPublishCounting002Service.getPrdPlanStrategyListByCode((String)map.get("prdIssueplanCode"));
        	page.setRoot(list);
        	return page;
        }else {
        	return page;
        }
       
    }
    /**
     * 资产大类列表查询
     */
    @ResponseBody
    @RequestMapping("getAssetInfoList")
    public Page<ProductPublishCounting001Dto> getAssetInfoList() {
    	Page<ProductPublishCounting001Dto> page = new Page<>();
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
    		ProductPublishCounting001Dto productPublishCounting001Dto = 
    				productPublishCounting002Service.getBussinessEntityByWorkflowId((String)map.get(GlobalConstant.WORKFLOW_ID));
    		page.setRoot(productPublishCounting001Dto.getPrdIssuePlanAssetList());
    		return page;
    	}else if(StringUtil.isNotEmpty(map.get("prdIssueplanCode"))){
    		List<ProductPublishCounting001Dto> list = productPublishCounting002Service.getAssetPlanList(map);
        	page.setRoot(list);
        	return page;
        }else {
        	List<ProductPublishCounting001Dto> list = productPublishCounting002Service.getAssetPlanList(map);
        	page.setRoot(list);
        	return page;
        }
    }
    /**
     * 配置列表策略查询
     */
    @ResponseBody
    @RequestMapping("getPtlInvStrategyList")
    public List<SelectOptionDto> getgetPtlInvStrategyList() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<SelectOptionDto> list = planningCommonService.getPtlInvStrategyList(map);
        return list;
    }
    /**
     * 试算接口 （返回试算数据）
     */
    @ResponseBody
    @RequestMapping("getPublishCountingList")
    public List<ProductPublishCounting001Dto> getPublishCountingList(@RequestBody ProductPublishCounting001Dto dto) {
        return productPublishCounting002Service.getPublishCountingList(dto);
    }
}
