package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorWeightConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service.PdFactorWeightConfigService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  前瞻因子权重配置
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年6月24日
 * @see PdFactorWeightConfig003Controller
 * @since 1.0
 */
@Controller
@PageId("PdFactorWeightConfig003")
@RequestMapping("/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003")
public class PdFactorWeightConfig003Controller extends BaseController{
	
	@Autowired
    private PdFactorWeightConfigService pdFactorWeightConfigService;
	
	
	 /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	/**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
    	String seqno = getParam("seqno");
        PdFactorWeightConfigDto dto = new PdFactorWeightConfigDto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
        if (StringUtils.isNotEmpty(seqno)) { // 查看 、 修改
        	dto = pdFactorWeightConfigService.findLastWeightInfo(RequestUtil.getPageParam());
        }else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = pdFactorWeightConfigService.getBussinessEntityByWorkflowId(workflowId);
        }else {
        	dto = pdFactorWeightConfigService.findLastWeightInfo(RequestUtil.getPageParam());
        }
        
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }
	
    
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PdFactorWeightConfigDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("PdFactorWeightConfig003");
        wflValidInputParameter.setBusinessDataPk1(dto.getSeqno());
        boolean is = WorkFlowUtil.isNotFromToDoList();
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        //判断乐观权重+基础权重+悲观权重是否等于100
        BigDecimal num = dto.getOptimisticWeight().add(dto.getBasicWeight()).add(dto.getPessimismWeight());
        BigDecimal num2 = BigDecimal.valueOf(100);
        if(num2.compareTo(num)!=0) {
            errorList.add(new ValidMessage("权重合计值不等于100，请调整。"));
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 
     * 保存提交工作流
     * <功能详细描述>
     * @param dto 保存参数
     * @param br 保存参数
     * @return 保存返回信息
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PdFactorWeightConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
		/*
		 * if(StringUtil.isEmpty(dto.getSeqno())){ dto.setSeqno(IDGenerator.generate());
		 * }
		 */
        dto.setSeqno(IDGenerator.generate());
        ProcessParameter<PdFactorWeightConfigDto> processParameter = new ProcessParameter<PdFactorWeightConfigDto>();
        WorkFlowParameter<PdFactorWeightConfigDto> params = new WorkFlowParameter<PdFactorWeightConfigDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(DictCodeListExt3.共通业务类型_前瞻因子权重配置.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "乐观权重" + "-" + entity.getOptimisticWeight()+ "基础权重" + "-"+entity.getBasicWeight() +"悲观权重" + "-"+entity.getPessimismWeight());
        processParameter.setWorkFlowParameter(params);
        String msg = pdFactorWeightConfigService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    
	
	
	
}
