package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam006Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam003Service;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam006Service;
import com.joyintech.tams.systemtools.service.TamsBaseCommonService;
import com.joyintech.tams.systemtools.util.CommonUtil;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("RskSupervisionIndicatorParam006")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam006")
public class RskSupervisionIndicatorParam006Controller extends BaseController{
	@Autowired
	private RskSupervisionIndicatorParam003Service rskSupervisionIndicatorParam003Service;
	@Autowired
	private RskSupervisionIndicatorParam006Service rskSupervisionIndicatorParam006Service;
	
	@Autowired
	private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;
	 
    
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
		String paraCode = this.getParam("paraCode");
		if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
	       	 String workflowId = getParam("workflowId");
	            model.addAttribute("workflowId", workflowId);
	            RskSupervisionIndicatorParam006Dto entity = rskSupervisionIndicatorParam006Service.getBussinessEntityByWorkflowId(workflowId);
	            model.addAttribute(GlobalConstant.ENTITY, entity);
	        }else if(StringUtil.isNotEmpty(getParam("paraCode"))){
				List<RskSupervisionIndicatorParam003Dto> list = rskSupervisionIndicatorParam003Service.selectFixRecode(paraCode);
	            if(!StringUtil.isEmptyList(list)){
	            	model.addAttribute("entity",list.get(0));
	            }else {
	                list = rskSupervisionIndicatorParam003Service.selectFixRecode2(paraCode); 
	                model.addAttribute("entity",list.get(0));
	            }
	        }
		if (MultilevelDataAuthorityUtil.isEnabled()) {
		   String InstitutionCode = CommonUtil.getInstitutionTypeByLoginUser_ms();
	       model.addAttribute("msInstitutionCode", InstitutionCode);
	       model.addAttribute("isEnabled", "01");
	       model.addAttribute("InstitutionCode", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        }else {
           model.addAttribute("isEnabled", "02");
        } 
		model.addAttribute("SYS_DATE", DateUtil.format(DateUtil.platDateFromCache()));
		return super.init(model);
	}
	
	/**
     * 固定值参数修改查询数据
     * 
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskSupervisionIndicatorParam006Dto> findPage()
        throws Exception {
    	return rskSupervisionIndicatorParam006Service.findPage(RequestUtil.getPageParam());
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
        ValidateGroupEdit.class}) RskSupervisionIndicatorParam006Dto dto, BindingResult result ) {
    	if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
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
    public AjaxResult save(@RequestBody RskSupervisionIndicatorParam006Dto dto, BindingResult br)
        throws Exception{
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_固定值参数管理));
        ProcessParameter<RskSupervisionIndicatorParam006Dto> processParameter = new ProcessParameter<RskSupervisionIndicatorParam006Dto>();
        WorkFlowParameter<RskSupervisionIndicatorParam006Dto> params = new WorkFlowParameter<RskSupervisionIndicatorParam006Dto>();
        params.setPageId("RskSupervisionIndicatorParam006");
        params.setElementId(dto.getElementId());
        
        // 参数代码作为业务编号
        String businessCode = dto.getParaCode();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_固定值参数管理.getValue());
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam006.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam006.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParam006Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskSupervisionIndicatorParam006Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndicatorParam006Dto> processParameter = new ProcessParameter<RskSupervisionIndicatorParam006Dto>();
        WorkFlowParameter<RskSupervisionIndicatorParam006Dto> params = new WorkFlowParameter<RskSupervisionIndicatorParam006Dto>();
        params.setPageId("RskSupervisionIndicatorParam006");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getParaCode());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_固定值参数管理.getValue());
     // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam002.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getParaCode(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam003.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParam006Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
