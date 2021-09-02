package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
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
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.PdFactorKpmgConfigDto;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.service.PdFactorKpmgConfig001Service;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  前瞻因子维护
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年6月23日
 * @see PdFactorKpmgConfig002Controller
 * @since 1.0
 */
@Controller
@PageId("PdFactorKpmgConfig002")
@RequestMapping("/model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002")
public class PdFactorKpmgConfig002Controller extends BaseController{
	
	@Autowired
    private PdFactorKpmgConfig001Service pdFactorKpmgConfig001Service;
	
	
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
        PdFactorKpmgConfigDto dto = new PdFactorKpmgConfigDto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(seqno)) { // 查看 、 修改
            dto = pdFactorKpmgConfig001Service.findByPrimaryKey(RequestUtil.getPageParam());
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = pdFactorKpmgConfig001Service.getBussinessEntityByWorkflowId(workflowId);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
        this.setSelectOptionToModel(model, DictCode.季度);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PdFactorKpmgConfigDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("PdFactorConfig002");
        wflValidInputParameter.setBusinessDataPk1(dto.getVouchYear());
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

        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (StringUtil.isNotEmpty(dto.getVouchYear()) ) {
                Map<String, Object> map = RequestUtil.getPageParam();
                map.put("vouchYear", dto.getVouchYear());
                //新建时，年份+季度数据已存在的情况下，报错
                List<PdFactorKpmgConfigDto> list = pdFactorKpmgConfig001Service.selectByYear(map);
                if (!StringUtil.isEmptyList(list)) {
                    errorList.add(new ValidMessage("当前日期已存在数据，请修改。"));
                }
            }
        }else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT) ){
            Map<String, Object> map = RequestUtil.getPageParam();
            map.put("seqno",dto.getSeqno());
            int exists = pdFactorKpmgConfig001Service.checkBySeqNo(map);
            //配置因子只要被PD生成批处理调用过
            if(exists>0) {
                ajaxResult.setData(this.getMessage("本次修改是否影响历史数据？", this.getMessage("SysCommon.save")));
                return ajaxResult;
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
        ValidateGroupEdit.class}) PdFactorKpmgConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getSeqno())){
            dto.setSeqno(IDGenerator.generate());
        }
        String businessCode = dto.getVouchYear()+"_"+ DateUtil.format(new Date(),"yyyyMMdd_HH:mm:ss");
        ProcessParameter<PdFactorKpmgConfigDto> processParameter = new ProcessParameter<PdFactorKpmgConfigDto>();
        WorkFlowParameter<PdFactorKpmgConfigDto> params = new WorkFlowParameter<PdFactorKpmgConfigDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_前瞻因子维护.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "年份" + "-" + entity.getVouchYear() );
        processParameter.setWorkFlowParameter(params);
        String msg = pdFactorKpmgConfig001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    
	
	
	
}
