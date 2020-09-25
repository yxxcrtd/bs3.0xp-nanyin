package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 指标参数新建controller
 *
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParam002Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionIndicatorParam002")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002")
public class RskSupervisionIndicatorParam002Controller extends BaseController {

    /**
     * 指标参数管理service
     */
    @Autowired
    private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;

    /**
     * 指标参数新建的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, BussinessDictCodeRds.指标参数实现类型, BussinessDictCodeRds.是否,BussinessDictCodeRds.指标参数分类维度, DictCode.报表数据格式类型);
        String paraCode = this.getParam("paraCode");
        if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
       	 String workflowId = getParam("workflowId");
            model.addAttribute("workflowId", workflowId);
            RskSupervisionIndicatorParamDto entity = rskSupervisionIndicatorParamService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        else if(StringUtil.isNotEmpty(getParam("paraCode"))){
            List<RskSupervisionIndicatorParamDto> rskSupervisionIndicatorParamInfo = rskSupervisionIndicatorParamService.selectInfo(paraCode);
            RskSupervisionIndicatorParamDto dto = new RskSupervisionIndicatorParamDto();
            if (!CollectionUtils.isEmpty(rskSupervisionIndicatorParamInfo) && rskSupervisionIndicatorParamInfo.size()>0) { 
                BeanUtil.copyPropertiesIgnoreNull(dto,rskSupervisionIndicatorParamInfo.get(0));
                for(RskSupervisionIndicatorParamDto entity:rskSupervisionIndicatorParamInfo){
                	 if(StringUtil.equals(entity.getParaGranularity(), BussinessDictCodeListRds.参数粒度_买入.getValue())){
    	                    dto.setMrContent(entity.getContent());
    	         	 }
    	         	 else if(StringUtil.equals(entity.getParaGranularity(),BussinessDictCodeListRds.参数粒度_卖出.getValue())){
    	         		dto.setMcContent(entity.getContent());
    	         	 }else if(StringUtil.equals(entity.getParaGranularity(),BussinessDictCodeListRds.参数粒度_实时.getValue())){
    	         		dto.setContent(entity.getContent());
    	         	 }else if(StringUtil.equals(entity.getParaGranularity(),BussinessDictCodeListRds.参数粒度_事后_日终.getValue())){
    	         		dto.setRzContent(entity.getContent());
    	         	 }else if(StringUtil.equals(entity.getParaGranularity(),BussinessDictCodeListRds.参数粒度_事后_日间.getValue())){
    	         		dto.setRjContent(entity.getContent());
    	         	 }
                }
            }
            model.addAttribute("entity",dto);
        }else{
        	RskSupervisionIndicatorParamDto rskSupervisionIndicatorParamInfo = new RskSupervisionIndicatorParamDto();
        	rskSupervisionIndicatorParamInfo.setParaDisplayType(BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
        	model.addAttribute("entity",rskSupervisionIndicatorParamInfo);
        }
        model.addAttribute("PLUGIN", BussinessDictCodeListRds.指标参数实现类型_类名.getValue());
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
        ValidateGroupEdit.class}) RskSupervisionIndicatorParamDto dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody RskSupervisionIndicatorParamDto dto, BindingResult br)
        throws Exception{
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndicatorParamDto> processParameter = new ProcessParameter<RskSupervisionIndicatorParamDto>();
        WorkFlowParameter<RskSupervisionIndicatorParamDto> params = new WorkFlowParameter<RskSupervisionIndicatorParamDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        
        // 参数代码作为业务编号
        String businessCode = dto.getParaCode();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_语言参数管理.getValue());
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam002.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam002.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(),GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParamService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
