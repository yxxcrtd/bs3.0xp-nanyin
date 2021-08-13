package com.joyintech.tams.invest.riskredis.quota.bankasset.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.bankasset.dto.BankAsset001Dto;
import com.joyintech.tams.invest.riskredis.quota.bankasset.service.BankAsset001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2020年12月29日
 * @see BankAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("BankAsset002")
@RequestMapping("/invest/riskredis/quota/bankasset/BankAsset002")
public class BankAsset002Controller extends BaseController{

	@Autowired
	private BankAsset001Service bankAsset001Service;
	
	/**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	/**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	 String customerId = getParam("customerId");
         String workflowId = getParam("workflowId");
         if (StringUtil.isNotEmpty(customerId)) {
             //将输入的估值日期格式化
             String effDate = DateUtil.format(new Date(Long.valueOf(getParam("effDate"))));
             String valStatus = getParam("valStatus");
             BigDecimal seqno  = new BigDecimal(getParam("seqno"));
             Map<String, Object> param = new HashMap<>();
             //将需要的数据字典和参数添加到map中
             param.put("customerId", customerId);
             param.put("effDate", effDate);
             param.put("valStatus", valStatus);
             param.put("seqno", seqno);
             //根据主键获取业务实体
             BankAsset001Dto dto = bankAsset001Service.selectBankAsset(param);
             //设置到model中
             model.addAttribute("entity", dto);
             model.addAttribute("customerId", customerId);
         }
         //来自于代办
         else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
             //根据工作流与留痕处理提供的共通方法获取业务实体
        	 BankAsset001Dto bankAsset001Dto;
        	 bankAsset001Dto = bankAsset001Service.
                     getBussinessEntityByWorkflowId(workflowId);
             //设置到model中
             model.addAttribute("pageModel", bankAsset001Dto.getPageModel());
             model.addAttribute("entity", bankAsset001Dto);
         }else{
        	 BankAsset001Dto bankAsset001Dto=new BankAsset001Dto();
        	 bankAsset001Dto.setEffDate(DateUtil.platDateFromCache());
        	 model.addAttribute("entity", bankAsset001Dto);
         }
        
         
         return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping(value = "getCustomerIdList", method = RequestMethod.GET)
    public List<SuggestDto> getCustomerIdList()
            throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<SuggestDto> temp = bankAsset001Service.getCustomerIdList(param);
        return temp;
        }
    
    /**
     * 主体代码下拉框查询
     * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping("/selectCustomerId")
	public Page<SelectOptionDto> selectCustomerId() throws Exception {
		Map<String, Object> map = RequestUtil.getParameters(getRequest());
		Page<SelectOptionDto> page = bankAsset001Service.selectCustomerId(map);
		return page;
	}
	
	/**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) BankAsset001Dto dto, BindingResult result) {
    	if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
    	 AjaxResult ajaxResult = new AjaxResult();
         List<ValidMessage> errorList = new ArrayList<>();
    	if(!StringUtil.isNotEmpty(dto.getInsitituteCode())){
    		errorList.add(new ValidMessage("insitituteCode", "请输入主体代码。"));
    	}
    	if(!StringUtil.isNotEmpty(dto.getNetValue())){
    		errorList.add(new ValidMessage("netValue", "请输入净资产。"));
    	}
    	if(!StringUtil.isNotEmpty(dto.getEffDate())){
    		errorList.add(new ValidMessage("effDate", "请输入更新时间。"));
    	}
    	if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
    	
    	WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("BankAsset002");
        wflValidInputParameter.setBusinessDataPk1(dto.getInsitituteCode());
        wflValidInputParameter.setBusinessDataPk2(dto.getEffDateStr());
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            errorList.add(new ValidMessage("所选对象存在流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
       
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insitituteCode", dto.getInsitituteCode());
        //map.put("effDate", dto.getEffDateStr());
        map.put("valStatus", BussinessDictCodeListRds.商业银行净资产维护_有效状态_有效.getValue());
        map.put("seqno", dto.getSeqno());
        Date maxEffDate = bankAsset001Service.selectMaxBankAsset(map);
        if(StringUtil.isNotEmpty(maxEffDate)){
        	if(DateUtil.compareDate(dto.getEffDate(), maxEffDate)<0){
        		errorList.add(new ValidMessage("更新时间小于系统中更新时间，请重新选择。"));
        		ajaxResult.setErrorList(errorList);
        	}
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
    	return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存操作
     *
     * @param dto dto
     * @param br  BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) BankAsset001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<BankAsset001Dto> processParameter = new ProcessParameter<BankAsset001Dto>();
        WorkFlowParameter<BankAsset001Dto> params = new WorkFlowParameter<BankAsset001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getInsitituteCode());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_商业银行净资产维护.getValue());
        // 设置流程业务说明:资产代码 + "(" + 资产代码 + ")" + "-"  + "估值日期" + "-"  + "估值日期"
        params.setComment(
                entity -> "主体代码" + "(" + entity.getInsitituteCode() + ")"
                        + "更新时间" + "(" + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        // 流程开始
        String msg = bankAsset001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
	
}
