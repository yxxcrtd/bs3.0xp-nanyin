package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("RskSupervisionIndicatorParam003")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam003")
public class RskSupervisionIndicatorParam003Controller extends BaseController{
	
	/**
     * 指标参数管理service
     */
    @Autowired
    private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;
	/**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
	@Autowired
	private RskSupervisionIndicatorParam003Service rskSupervisionIndicatorParam003Service;

    /**
     * IPlatformDate
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		String paraCode = this.getParam("paraCode");
		if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
	       	 String workflowId = getParam("workflowId");
	            model.addAttribute("workflowId", workflowId);
	            RskSupervisionIndicatorParam003Dto entity = rskSupervisionIndicatorParam003Service.getBussinessEntityByWorkflowId(workflowId);
	            model.addAttribute(GlobalConstant.ENTITY, entity);
	        }else{
	        	RskSupervisionIndicatorParam003Dto rskSupervisionIndicatorParamInfo = new RskSupervisionIndicatorParam003Dto();
	        	rskSupervisionIndicatorParamInfo.setValidDate(DateUtil.platDateFromCache());
	        	model.addAttribute("entity",rskSupervisionIndicatorParamInfo);
	        }
		model.addAttribute("SYS_DATE", DateUtil.format(DateUtil.platDateFromCache()));
		return super.init(model);
	}
	/**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskSupervisionIndicatorParam003Dto dto, BindingResult result ) {
    	if(result.getAllErrors().isEmpty()&&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
    			&&rskSupervisionIndicatorParamService.isConfigure(dto.getParaCode())>0){
    		List<JoyinMessage> joyinMessages = new ArrayList<>();
    		JoyinMessage ms = new JoyinMessage(getMessage("RSKRDS_W_0064"), MessageType.确认消息);
    		joyinMessages.add(ms);
    		AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
    	}
        return commonCheck(result, dto.getOperateType());
    }
 
    /**
     * 新建机构代码联想框 
     * @return List List
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("/getInstitutionId")
    public List<SelectOptionDto> getInstitutionId()
        throws Exception { 
        return rskSupervisionIndicatorParam003Service.getInstitutionId(RequestUtil.getPageParam());
    }
    
    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @see
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskSupervisionIndicatorParam003Dto dto, BindingResult br)
        throws Exception{
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        ProcessParameter<RskSupervisionIndicatorParam003Dto> processParameter = new ProcessParameter<RskSupervisionIndicatorParam003Dto>();
        WorkFlowParameter<RskSupervisionIndicatorParam003Dto> params = new WorkFlowParameter<RskSupervisionIndicatorParam003Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_固定值参数管理));
            //param.setDate(dto.getValidDate());
            //param.set
            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                param) : IDGenerator.generate();
            if(StringUtil.isNotEmpty(code)&&code.length()>=2){
                code=code.substring(0,2)+DateUtil.format(StringUtil.isNotEmpty(dto.getValidDate())?dto.getValidDate():iPlatformDate.getSysDate(),DateUtil.DEFAULT_OTHER_FORMAT)+code.substring(2,code.length());
            }
            dto.setParaCode(code);
        }
        
        // 参数代码作为业务编号
        String businessCode = dto.getParaCode();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_固定值参数管理.getValue());
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam002.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam003.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParam003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
