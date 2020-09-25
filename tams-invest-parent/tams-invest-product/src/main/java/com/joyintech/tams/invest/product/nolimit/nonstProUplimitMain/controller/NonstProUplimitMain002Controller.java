package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service.NonstProUplimitMain002Service;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * @program: NonstProUplimitMain002Controller
 * @description: 非标比例上限维护新建
 * @author: cxq
 * @create: 2019-12-28
 **/
@Controller
@PageId("NonstProUplimitMain002")
@RequestMapping("/invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002")
public class NonstProUplimitMain002Controller extends BaseController {
	@Autowired
	NonstProUplimitMain002Service nonstProUplimitMain002Service;
	@Autowired
	MstWorkflowCommonService mstWorkflowCommonService;

	@Override
	public String init(Model model) {
		String workflowId = this.getParam("workflowId");
		String effDate = this.getParam("effDate");
		List<NonstProUplimitMain001Dto> tableList=new ArrayList<NonstProUplimitMain001Dto>();
		NonstProUplimitMain001Dto entity = new NonstProUplimitMain001Dto();
        if (StringUtil.isNotEmpty(effDate)) {
    		Long l = Long.valueOf(effDate);
			entity.setEffDate(new Date(l));
            entity.setPrdPlanSerisId(this.getParam("prdPlanSerisId"));
            entity.setPrdPlanSerisName(this.getParam("prdPlanSerisName"));
            entity.setNonStandardLimit(new BigDecimal(this.getParam("nonStandardLimit")));
            tableList.add(entity);
            model.addAttribute("entity", entity);
            model.addAttribute("tableList",tableList);
        }
        if (WorkFlowUtil.isNotFromToDoList()) {
			model.addAttribute("entity", entity);
		} else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			entity = nonstProUplimitMain002Service.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", entity);
			tableList=entity.getTableList();
			model.addAttribute("tableList",tableList);
		}
		return super.init(model);
	}
	    @RequestMapping("save")
	    @ResponseBody
	    public AjaxResult save(@RequestBody @Validated NonstProUplimitMain001Dto dto, BindingResult br) throws Exception{
	        if(br.hasErrors()){
	            return new AjaxResult(br);
	        }
	        ProcessParameter<NonstProUplimitMain001Dto> processParameter = new ProcessParameter<NonstProUplimitMain001Dto>();
	        WorkFlowParameter<NonstProUplimitMain001Dto> params = new WorkFlowParameter<NonstProUplimitMain001Dto>();
	        dto.setCreateUser(LoginUserUtil.getUserName());
	        dto.setUpdateUser(LoginUserUtil.getUserName());
	        //判断是否在工作流中
	        List<NonstProUplimitMain001Dto> list = dto.getTableList();
	        String id = null;
	        if (!StringUtil.isEmptyList(list)) {
	        	 for (NonstProUplimitMain001Dto dto1 : list) {
	 				id=dto1.getPrdPlanSerisId();
	 			}
			}
	        WflValidInputParameter parameter = new WflValidInputParameter();
	        parameter.setPageId(this.getPageId());
	        parameter.setBusinessDataPk2(id);
	        if(mstWorkflowCommonService.isInWflProcess(parameter)){
	            AjaxResult result = new AjaxResult();
	            result.setMessage("不能操作流程中的数据！");
	            result.setMessageType(MessageType.错误消息);
	            return result;
	        }
	        params.setPageId(this.getPageId());
	        params.setElementId(this.getParam("elementId"));
	        params.setBusinessCode(id);
	        params.setBusinessType("非标限额管理");
	        params.setComment(entity ->"非标比例上限维护"+ this.getMessage("NonstProUplimitMain002.effdate") + "("
	                + entity.getEffDate() +")" + this.getMessage("TotalMaintain002.createTime") + "("
	                + DateUtil.format(DateUtil.now()) + ")");
	        processParameter.setWorkFlowParameter(params);
	        String msg = nonstProUplimitMain002Service.start(processParameter, dto);
	        return new AjaxResult(msg);
	    }
	    /**
		 * 	根据系列id 查name
		 * @param page
		 * @param length
		 * @param param
		 * @return
		 */	
	    @RequestMapping("queryPrdPlanSerName")
	    @ResponseBody
	    public Page<NonstProUplimitMain001Dto> queryPrdPlanSerName(@RequestBody Map<String, Object> map){
			return nonstProUplimitMain002Service.queryPrdPlanSerName(map);
	    	
	    }
	    //校验
	    @ResponseBody
	    @RequestMapping("check")
	    public AjaxResult check(@RequestBody @Validated({
	            ValidateGroupEdit.class,ValidateGroupDelete.class, ValidateGroupNew.class}) NonstProUplimitMain001Dto dto, BindingResult result) {
	        return commonCheck(result, dto.getOperateType());
	    }
	    /**
	     *  页面删除
	     * @param dto 费用品种
	     * @param br br
	     * @return 删除结果
	     * @throws Exception Exception
	     */
	    @ResponseBody
	    @RequestMapping(ACTION_DELETE)
	    public AjaxResult delete(@RequestBody @Validated({
	        ValidateGroupDelete.class}) NonstProUplimitMain001Dto dto, BindingResult br)
	        throws Exception {
	        if (br.hasErrors()) {
	            return new AjaxResult(br);
	        }
	        if(br.hasErrors()){
	            return new AjaxResult(br);
	        }
	        ProcessParameter<NonstProUplimitMain001Dto> processParameter = new ProcessParameter<NonstProUplimitMain001Dto>();
	        WorkFlowParameter<NonstProUplimitMain001Dto> params = new WorkFlowParameter<NonstProUplimitMain001Dto>();
	        dto.setCreateUser(LoginUserUtil.getUserName());
	        dto.setUpdateUser(LoginUserUtil.getUserName());
	        //判断是否在工作流中
	        String id = dto.getPrdPlanSerisId();
	        WflValidInputParameter parameter = new WflValidInputParameter();
	        parameter.setPageId(this.getPageId());
	        parameter.setBusinessDataPk2(id);
	        if(mstWorkflowCommonService.isInWflProcess(parameter)){
	            AjaxResult result = new AjaxResult();
	            result.setMessage("不能操作流程中的数据！");
	            result.setMessageType(MessageType.错误消息);
	            return result;
	        }
	        params.setPageId(this.getPageId());
	        params.setElementId(this.getParam("elementId"));
	        params.setBusinessCode(id);
	        params.setBusinessType("非标比例上限维护");
	        params.setComment(entity -> this.getMessage("TotalMaintain001.effDate") + "("
	                + entity.getEffDate() +")" + this.getMessage("TotalMaintain002.createTime") + "("
	                + DateUtil.format(DateUtil.now()) + ")");
	        processParameter.setWorkFlowParameter(params);
	        String msg = nonstProUplimitMain002Service.start(processParameter, dto);
	        //msg= SpringContextUtil.getTextValue("COMMON_I_0011");
	        return new AjaxResult(msg);
	    }
}
