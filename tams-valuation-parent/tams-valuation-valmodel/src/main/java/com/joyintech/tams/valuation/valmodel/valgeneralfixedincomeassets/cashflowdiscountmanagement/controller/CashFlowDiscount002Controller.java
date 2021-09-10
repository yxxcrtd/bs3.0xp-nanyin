
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.service.CashFlowDiscount001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.tams.constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板示例<br>
 * 现金流折现模型曲线配置
 * 
 * @author 刘华明
 * @version 1.0 2021年7月2日
 * @see CashFlowDiscount002Controller
 * @since 1.0
 */
@Controller
@PageId("CashFlowDiscount002")
@RequestMapping("/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002")
public class CashFlowDiscount002Controller extends BaseController {

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private CashFlowDiscount001Service cashFlowDiscount001Service;

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
        String seqNo = this.getParam("seqNo");
        CashFlowDiscount001Dto dto = new CashFlowDiscount001Dto();
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if (StringUtil.isNotEmpty(seqNo)) {
            Map<String,Object> map = new HashMap<>();
            map.put("seqNo",seqNo);
            dto = cashFlowDiscount001Service.getCashFlowDataBySeqNo(map);
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = cashFlowDiscount001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
        }
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型);
        this.setSelectOptionToModel(model, DictCode.资产类型);
        this.setSelectOptionToModel(model, DictCode.资产分类);
        this.setSelectOptionToModel(model, DictCode.曲线族);
        this.setSelectOptionToModel(model, DictCode.平滑区间);
        /** modify by yanya on 20210821 增加评级结果**/
        this.setSelectOptionToModel(model, DictCode.评级结果);
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("SMOOTHSECTION_02", DictCodeListExt3.平滑区间_3个月.getValue());
        this.setSelectOptionToModel(model, DictCode.证券品种);
        if(!StringUtil.isNotEmpty(dto.getIsSmooth())){
            /** modify by yanya on 20210821 调整是否平滑区间默认值为否**/
            //dto.setIsSmooth(DictCodeList.是否_是.getValue());
            dto.setIsSmooth(DictCodeList.是否_否.getValue());
           // dto.setSmoothSection(DictCodeListExt3.平滑区间_3个月.getValue());
        }
        if(!StringUtil.isNotEmpty(dto.getCurveType())){
            dto.setCurveType(DictCodeListExt3.收益率曲线类型_SP即期.getValue());
        }
        /** modify by yanya on 20210821 增加是否兜底评级默认值**/
        if(!StringUtil.isNotEmpty(dto.getIsBottomGrade())){
            dto.setIsBottomGrade(DictCodeList.是否_否.getValue());
        }

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
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    public AjaxResult doUpdate(CashFlowDiscount001Dto dto) {
        ProcessParameter<CashFlowDiscount001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<CashFlowDiscount001Dto> params = new WorkFlowParameter<>();
        // 业务代码
        //modify by yanya on 20210823 去除资产代码
        params.setBusinessCode(dto.getSecTypeModel()+"_"+dto.getSecDetailTypeModel());
        // 业务类型
        params.setBusinessType(DictCodeListExt3.共通业务类型_现金流折现模型曲线配置.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        //modify by yanya on 20210823 修正提交流程中的描述，去除资产代码
        //params.setComment(entity -> WorkFlowUtil.concatComment("现金流折现模型曲线配置修改：", entity.getSecTypeModel()+"_"+entity.getSecDetailTypeModel()+"_"+entity.getFinprodId()));
        params.setComment(entity -> WorkFlowUtil.concatComment("现金流折现模型曲线配置修改：", entity.getSecTypeModel()+"_"+entity.getSecDetailTypeModel()));
        if(dto.getSeqNo().isEmpty()){
            dto.setSeqNo(IDGenerator.generate());
        }

        // 如果界面上是否平滑曲线选择否，需要将平滑区间置空操作
        if(dto.getIsSmooth().equals(DictCodeList.是否_否.getValue())){
            dto.setSmoothSection(null);
        }
        //modify by yanya on 20210823 如果是否使用兜底评级为空，则兜底评级置空
        if(dto.getIsBottomGrade().equals(DictCodeList.是否_否.getValue())){
            dto.setBottomGrade(null);
        }

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = cashFlowDiscount001Service.start(proParam, dto);
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
            ValidateGroupEdit.class}) CashFlowDiscount001Dto dto, BindingResult result) {

        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("CashFlowDiscount002");
        wflValidInputParameter.setBusinessDataPk1(dto.getSecTypeModel());
        wflValidInputParameter.setBusinessDataPk2(dto.getSecDetailTypeModel());
        if(StringUtil.isNotEmpty(dto.getFinprodId())){
            wflValidInputParameter.setBusinessDataPk3(dto.getFinprodId());
        }
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
            if (StringUtil.isNotEmpty(dto.getSecTypeModel())) {
                boolean flag = cashFlowDiscount001Service.haveDataCheck(dto);
                if (flag) {
                    errorList.add(new ValidMessage("该资产类型已配置曲线，不可重复配置！"));
                }
            }
            if(StringUtil.isNotEmpty(dto.getSecDetailTypeModel())){
                boolean flag = cashFlowDiscount001Service.haveDataCheck(dto);
                if (flag) {
                    errorList.add(new ValidMessage("该资产细分类已配置曲线，不可重复配置！"));
                }
            }
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                boolean flag = cashFlowDiscount001Service.haveDataCheck(dto);
                if (flag) {
                    errorList.add(new ValidMessage("该资产已配置曲线，不可重复配置！"));
                }
            }
        }
        //modify by yanya on 20210823 增加生效失效日期的判定
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            dto.setSeqNoCon(dto.getSeqNo());
            dto.setEffDateCon(DateUtil.format(dto.getEffDate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
            boolean flag = cashFlowDiscount001Service.haveDataCheck(dto);
            if (flag) {
                errorList.add(new ValidMessage("该资产细分类已配置该生效日期的曲线，不可重复配置！"));
            }
        }
        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }
}
