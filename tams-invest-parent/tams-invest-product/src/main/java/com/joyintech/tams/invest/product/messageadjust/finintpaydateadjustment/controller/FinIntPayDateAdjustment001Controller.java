/*
 * 文件名：FinIntPayDateAdjustment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.service.FinIntPayDateAdjustmentService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 信息调整管理_付息日调整                                                      
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustment001Controller
 * @since 1.0
 */
@Controller
@PageId("FinIntPayDateAdjustment001")
@RequestMapping("/invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001")
public class FinIntPayDateAdjustment001Controller extends BaseController{
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    /**
     * 付息日调整service
     */
    @Autowired
    private FinIntPayDateAdjustmentService finIntPayDateAdjustmentService;
    
    @Override
    public String init(Model model) {
        
        String workflowId = getParam("workflowId");
        FinIntPayDateAdjustmentDto finIntPayDateAdjustmentDto =new FinIntPayDateAdjustmentDto();
        if(StringUtil.isNotEmpty(workflowId)){
            finIntPayDateAdjustmentDto = finIntPayDateAdjustmentService.getBussinessEntityByWorkflowId(workflowId);
            //add by yejiejie 2019-4-2 start
            //if(StringUtil.equals(finIntPayDateAdjustmentDto.getOperateType(), "save")){
	            Map<String,Object> param = new HashMap<String,Object>();
	            param.put("cashId", finIntPayDateAdjustmentDto.getCashId());
	            List<FinIntPayDateAdjustmentDto> list = finIntPayDateAdjustmentService.selectForPage(param);
	            if(finIntPayDateAdjustmentDto != null && !StringUtil.isEmptyList(list)
	            		&& !StringUtil.isEmptyList(finIntPayDateAdjustmentDto.getSelectedItem1())){
		            for(int i=0;i<finIntPayDateAdjustmentDto.getSelectedItem1().size();i++){
		            	if(i<list.size()){
		            		if(DateUtil.compareDate(finIntPayDateAdjustmentDto.getSelectedItem1().get(i).getVdate(), list.get(i).getVdate()) == 0
		            				&& DateUtil.compareDate(finIntPayDateAdjustmentDto.getSelectedItem1().get(i).getMdate(), list.get(i).getMdate()) == 0){
		            			//起息日，到期日都相等，则没有改
		            			//不做改变
		            		}else{
		            			finIntPayDateAdjustmentDto.getSelectedItem1().get(i).setPayStatus("");
		            		}
		            	}
		            }
	            }
            //}
            //add by yejiejie 2019-4-2 end
            model.addAttribute("entity", finIntPayDateAdjustmentDto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("selectedItem1", finIntPayDateAdjustmentDto.getSelectedItem1());
        }
        return super.init(model);
    }
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see 
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCashId")
    public List<SelectOptionDto> getCashId(){
        return finIntPayDateAdjustmentService.getCashId(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @return Page<FinIntPayDateAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<FinIntPayDateAdjustmentDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finIntPayDateAdjustmentService.selectForPage(param);
    }
    
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finIntPayDateAdjustmentService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinIntPayDateAdjustmentDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * save、submit
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinIntPayDateAdjustmentDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        String code = null;
        if(bussGetBussinessIdService != null){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_付息日调整);
           //code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            code = bussGetBussinessIdService.buildBussinessCode(param);
        }else {
        	code = IDGenerator.generate();
        }
        ProcessParameter<FinIntPayDateAdjustmentDto> processParameter = new ProcessParameter<FinIntPayDateAdjustmentDto>();
        WorkFlowParameter<FinIntPayDateAdjustmentDto> params = new WorkFlowParameter<FinIntPayDateAdjustmentDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        //业务编号
        params.setBusinessCode(code);
        params.setBusinessType(DictCodeList.共通业务类型_付息日调整.getValue());
        //业务说明：产品简称 + "(" + 产品代码 + ")"
        params.setComment(entity -> this.getMessage("产品简称") + "("
            + entity.getFinprodAbbr() +")" +this.getMessage("FinIntPayDateAdjustment001.cashId") + "("
            + entity.getFinprodMarketId() +")");
        processParameter.setWorkFlowParameter(params);
        String msg = finIntPayDateAdjustmentService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 获取付息日计划未付息最小计息区间开始日
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getVDate")
    public String getVDate(){
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        Date vdate = finIntPayDateAdjustmentService.selectForList(param);
        return DateUtil.format(vdate); 
    }

}
