
package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGradeDetail001Dto;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.service.InsideOutsideGrade001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 模板示例<br>
 * 内外部评级映射管理-新建、编辑控制层
 * 
 * @author 刘华明
 * @version 1.0 2021年7月2日
 * @see InsideOutsideGrade002Controller
 * @since 1.0
 */
@Controller
@PageId("InsideOutsideGrade002")
@RequestMapping("/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002")
public class InsideOutsideGrade002Controller extends BaseController {

    /**
     * InsideOutsideGrade001Service
     */
    @Autowired
    private InsideOutsideGrade001Service insideOutsideGrade001Service;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String gradeInst = this.getParam("gradeInst");
        String oType = this.getParam("oType");
        InsideOutsideGrade001Dto dto = new InsideOutsideGrade001Dto();
        List<InsideOutsideGrade001Dto> dtoList = new ArrayList<InsideOutsideGrade001Dto>();
        List<InsideOutsideGradeDetail001Dto> detailList = new ArrayList<InsideOutsideGradeDetail001Dto>();

        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(gradeInst) && StringUtil.isNotEmpty(oType)) {
            Map<String,Object> map = new HashMap<>();
            map.put("gradeInst",gradeInst);
            map.put("oType",oType);
            dtoList = insideOutsideGrade001Service.getInsideOutSideDataBySeqNo(map);
            dto = dtoList.get(0);

            for (InsideOutsideGrade001Dto item:dtoList
            ) {
                InsideOutsideGradeDetail001Dto detailDto = new InsideOutsideGradeDetail001Dto();
                detailDto.setGradeResult(item.getGradeResult());
                detailDto.setGradeStandardResult(item.getGradeStandardResult());
                detailList.add(detailDto);
            }

        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = insideOutsideGrade001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("pageModel",dto.getPageModel());

            Map<String,Object> map = new HashMap<>();
            map.put("gradeInst",dto.getGradeInst());
            map.put("oType",dto.getoType());

            if(dto.getSelectInsideOutsideDetail() == null){
                dtoList = insideOutsideGrade001Service.getInsideOutSideDataBySeqNo(map);
                for (InsideOutsideGrade001Dto item:dtoList
                ) {
                    InsideOutsideGradeDetail001Dto detailDto = new InsideOutsideGradeDetail001Dto();
                    detailDto.setGradeResult(item.getGradeResult());
                    detailDto.setGradeStandardResult(item.getGradeStandardResult());
                    detailList.add(detailDto);
                }
            }else{
                detailList.addAll(dto.getSelectInsideOutsideDetail());
            }
        }
        this.setSelectOptionToModel(model, DictCode.评级机构,DictCode.评级类型,DictCode.评级结果,DictCode.内部评级);

        model.addAttribute("detailList", detailList);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 修改现金流折现模型曲线配置
     *
     * @param dto 参数
     * @param dto 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody InsideOutsideGrade001Dto dto, BindingResult br){
        ProcessParameter<InsideOutsideGrade001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<InsideOutsideGrade001Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getGradeInst()+"_"+dto.getoType());
        // 业务类型
        params.setBusinessType(DictCodeListExt3.共通业务类型_内外部评级映射管理.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("内外部评级映射管理修改：", SysDataUtil.getNameFromDataDict(DictCode.评级机构, entity.getGradeInst())+"_"+SysDataUtil.getNameFromDataDict(DictCode.评级类型, entity.getoType())));

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = insideOutsideGrade001Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }


    /**
     * 新建时校验
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) InsideOutsideGrade001Dto dto, BindingResult result) {

        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("InsideOutsideGrade002");
        wflValidInputParameter.setBusinessDataPk1(dto.getGradeInst());
        wflValidInputParameter.setBusinessDataPk2(dto.getoType());

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
            if (StringUtil.isNotEmpty(dto.getGradeInst()) && StringUtil.isNotEmpty(dto.getoType())) {
                boolean flag = insideOutsideGrade001Service.haveDataCheck(dto);
                if (flag) {
                    errorList.add(new ValidMessage("该评级机构和对象类型已配置映射，不可重复配置！"));
                }
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
}
