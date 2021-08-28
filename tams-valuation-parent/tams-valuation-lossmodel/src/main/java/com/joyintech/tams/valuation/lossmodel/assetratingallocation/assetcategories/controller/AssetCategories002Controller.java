/*
 * 文件名：ValAssetBook002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories002Dto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service.AssetCategories001Service;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service.AssetCategories002Service;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

/**
 * 模板示例<br>
 * 新建资产大类评级配置
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see AssetCategories002Controller
 * @since 1.0
 */
@Controller
@PageId("AssetCategories002")
@RequestMapping("/model/impairment/assetratingallocation/assetcategories/AssetCategories002")
public class AssetCategories002Controller extends BaseController {

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private AssetCategories002Service valAssetBook002Service;

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private AssetCategories001Service assetCategories001Service;


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
        AssetCategories002Dto dto = new AssetCategories002Dto();
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if (StringUtil.isNotEmpty(seqNo)) {
            dto = valAssetBook002Service.getAssetCategoriesBySeqNo(seqNo);
        }else if (StringUtil.isNotEmpty(workflowId)) {// 流程页面
            dto = valAssetBook002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
        }

        this.setSelectOptionToModel(model, DictCode.资产类型);
        this.setSelectOptionToModel(model, DictCode.资产分类);
        this.setSelectOptionToModel(model, DictCode.评级机构);
        this.setSelectOptionToModel(model, DictCode.评级类型_减值);
        this.setSelectOptionToModel(model, DictCode.评级机构类型);
        this.setSelectOptionToModel(model, DictCode.证券品种);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 修改资产大类评级配置
     * 
     * @param dto 参数
     * @param dto 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    public AjaxResult doUpdate(AssetCategories002Dto dto) {
        ProcessParameter<AssetCategories002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<AssetCategories002Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getAssetType()+"_"+dto.getAssetDetailType());
        // 业务类型
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产大类评级配置.getValue());
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("资产大类评级配置管理修改：", entity.getAssetType()+"_"+entity.getAssetDetailType()));

        if(dto.getSeqNo().isEmpty()){
            dto.setSeqNo(IDGenerator.generate());
        }

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetBook002Service.start(proParam, dto);
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
            ValidateGroupEdit.class}) AssetCategories002Dto dto, BindingResult result) {

        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("AssetCategories002");
        wflValidInputParameter.setBusinessDataPk1(dto.getAssetType());
        wflValidInputParameter.setBusinessDataPk2(dto.getAssetDetailType());
        wflValidInputParameter.setBusinessDataPk3(dto.getGradeOrgId());
        wflValidInputParameter.setBusinessDataPk4(dto.getoType());

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
//            if (StringUtil.isNotEmpty(dto.getAssetType()) && StringUtil.isNotEmpty(dto.getAssetDetailType())) {
                boolean flag = valAssetBook002Service.checkAssetTypeAndAssetDetailType(dto);
                if (flag) {
                    errorList.add(new ValidMessage("该数据已有重复项配置！"));
//                    errorList.add(new ValidMessage("当前资产类别和资产分类已经存在"));
                }
//            }
        }

        if (errorList.size() > 0) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 联动：资产代码找到证券品种 - 获得评级类型
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getGradeType")
    public List<SelectOptionDto> getGradeType() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        String secType  = (String)param.get("assetType");
        List<SelectOptionDto> selectOptionList = new ArrayList<>();
        if(StringUtil.isNotEmpty(secType)){
            param.put("dict_code_parent", DictCode.证券品种.getValue());
            param.put("dict_code_child", DictCode.评级类型_减值.getValue());
            param.put("item_code_parent", secType);
            selectOptionList =assetCategories001Service.getLinkSelectOption(param);
        }
        return selectOptionList;
    }
}
