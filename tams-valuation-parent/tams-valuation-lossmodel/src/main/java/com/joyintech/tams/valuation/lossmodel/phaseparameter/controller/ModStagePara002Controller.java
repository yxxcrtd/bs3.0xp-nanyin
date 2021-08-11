package com.joyintech.tams.valuation.lossmodel.phaseparameter.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.lossmodel.phaseparameter.dto.ModStageParaDto;
import com.joyintech.tams.valuation.lossmodel.phaseparameter.service.ModStageParaService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  减值阶段参数修改
 * @author zhangfei
 * @version 1.0 2021年6月28日
 * @see ModStagePara002Controller
 * @since 1.0
 */
@Controller
@PageId("ModStagePara002")
@RequestMapping("/model/impairment/phaseparameter/ModStagePara002")
public class ModStagePara002Controller extends BaseController{
	
	
	@Autowired
	private ModStageParaService modStageParaService;
	
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
        ModStageParaDto dto = new ModStageParaDto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(seqno)) { // 查看 、 修改
            dto = modStageParaService.findByPrimaryKey(RequestUtil.getPageParam());
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = modStageParaService.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        this.setSelectOptionToModel(model, DictCode.要素绝对性);
        this.setSelectOptionToModel(model, DictCode.减值要素);
        this.setSelectOptionToModel(model, DictCode.五级分类);
        this.setSelectOptionToModel(model, DictCode.评级结果);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ModStageParaDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        // 要素为01,02,03,05时，校验elementsValue 为必填项
        if(DictCodeListExt3.减值要素_本金逾期超过天数.getValue().equals(dto.getElements()) || DictCodeListExt3.减值要素_利息逾期超过天数.getValue().equals(dto.getElements()) ||
   	      		DictCodeListExt3.减值要素_公司评级信息下调级数.getValue().equals(dto.getElements()) || DictCodeListExt3.减值要素_公司评级信息低于.getValue().equals(dto.getElements())) {
        	if(StringUtils.isEmpty(dto.getElementsValue())) {
        		ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(new ValidMessage("参数值不能为空"));
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
        	}
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("ModStagePara002");
        wflValidInputParameter.setBusinessDataPk1(dto.getStageOfAeest());
        wflValidInputParameter.setBusinessDataPk2(dto.getElements());
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
        if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT) ){
            Map<String, Object> map = RequestUtil.getPageParam();
            // 判断二阶段和三阶段本金，利息，评级下调参数值比较
            if(DictCodeListExt3.减值要素_本金逾期超过天数.getValue().equals(dto.getElements()) || DictCodeListExt3.减值要素_利息逾期超过天数.getValue().equals(dto.getElements()) ||
                    DictCodeListExt3.减值要素_公司评级信息低于.getValue().equals(dto.getElements())) {
                if(DictCodeListExt3.阶段_买入后风险显著提升_P2.getValue().equals(dto.getStageOfAeest())) {
                    map.put("stageOfAeest",DictCodeListExt3.阶段_买入后出现减值_P3.getValue());
                }else {
                    map.put("stageOfAeest",DictCodeListExt3.阶段_买入后风险显著提升_P2.getValue());
                }
                map.put("elements",dto.getElements());
                //获取p2,p3参数值
                ModStageParaDto checkDto = modStageParaService.findByStageAndElements(map);
                int checkValue = Integer.valueOf(checkDto.getElementsValue());
                int newValue = Integer.valueOf(dto.getElementsValue());
                // 本金利息逾期天数，P2阶段
                if((DictCodeListExt3.减值要素_本金逾期超过天数.getValue().equals(dto.getElements()) || DictCodeListExt3.减值要素_利息逾期超过天数.getValue().equals(dto.getElements()) ) &&
                        (DictCodeListExt3.阶段_买入后风险显著提升_P2.getValue().equals(dto.getStageOfAeest()))) {
                    if(newValue>checkValue) {
                        errorList.add(new ValidMessage("二阶段逾期天数大于三阶段逾期天数，请调整！"));
                    }
                }else if((DictCodeListExt3.减值要素_本金逾期超过天数.getValue().equals(dto.getElements()) || DictCodeListExt3.减值要素_利息逾期超过天数.getValue().equals(dto.getElements()) ) &&
                        (DictCodeListExt3.阶段_买入后出现减值_P3.getValue().equals(dto.getStageOfAeest()))) {
                    if(newValue<checkValue) {
                        errorList.add(new ValidMessage("三阶段逾期天数小于二阶段逾期天数，请调整！"));
                    }
                }else if(DictCodeListExt3.减值要素_公司评级信息低于.getValue().equals(dto.getElements()) && DictCodeListExt3.阶段_买入后风险显著提升_P2.getValue().equals(dto.getStageOfAeest())) {
                    if(newValue>checkValue) {
                        errorList.add(new ValidMessage("二阶段评级等级低于三阶段评级等级，请调整！"));
                    }
                }else if(DictCodeListExt3.减值要素_公司评级信息低于.getValue().equals(dto.getElements()) && DictCodeListExt3.阶段_买入后出现减值_P3.getValue().equals(dto.getStageOfAeest())) {
                    if(newValue<checkValue) {
                        errorList.add(new ValidMessage("三阶段评级等级高于二阶段评级等级，请调整！"));
                    }
                }
            }
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
        ValidateGroupEdit.class}) ModStageParaDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getSeqno())){
            dto.setSeqno(IDGenerator.generate());
        }
        // 所属阶段+"_"+要素
        String businessCode = dto.getStageOfAeest()+"_"+dto.getElements();
        ProcessParameter<ModStageParaDto> processParameter = new ProcessParameter<ModStageParaDto>();
        WorkFlowParameter<ModStageParaDto> params = new WorkFlowParameter<ModStageParaDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_减值阶段参数管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "所属阶段" + "-" + entity.getStageOfAeest()+ "要素" + "-"+entity.getElements() );
        processParameter.setWorkFlowParameter(params);
        String msg = modStageParaService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
	
	
}
