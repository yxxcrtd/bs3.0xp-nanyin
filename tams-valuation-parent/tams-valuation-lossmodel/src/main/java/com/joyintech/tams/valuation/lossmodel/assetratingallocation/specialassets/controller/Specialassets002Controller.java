/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service.AssetCategories001Service;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service.Specialassets002Service;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service.LossGivenDefaultMaintain001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 附件信息--业务组件
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see Specialassets002Controller
 * @since 1.0
 */
@Controller
@PageId("Specialassets002")
@RequestMapping("/model/impairment/assetratingallocation/specialassets/Specialassets002")
public class Specialassets002Controller extends BaseController{
	@Autowired
	private Specialassets002Service specialassets002Service;
    @Autowired
    private AssetCategories001Service assetCategories001Service;
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.评级机构, DictCode.评级类型_减值);
        this.setSelectOptionToModel(model, DictCode.评级机构类型);
        this.setSelectOptionToModel(model, DictCode.证券品种);
    	 String workflowId = getParam("workflowId");
         String creditTagId = getParam("seqno");
         // 待办跳转
         if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
             model.addAttribute("workflowId", workflowId);
             model.addAttribute("isTodoList", getParam("isTodoList"));
             ModAssetGradeSpecialConfigDto CreditTagInfo = specialassets002Service.getBussinessEntityByWorkflowId(workflowId);

             model.addAttribute("entity", CreditTagInfo);

         }
         //修改
         else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam("seqno"))) {

        	 ModAssetGradeSpecialConfigDto CreditTagInfo = specialassets002Service.getBussinessEntityByIndId(creditTagId);
        	 model.addAttribute("SEQNO", this.getParam("seqno"));
             model.addAttribute(GlobalConstant.ENTITY, CreditTagInfo);
             model.addAttribute("pageModel", "1");
         }
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) ModAssetGradeSpecialConfigDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) ModAssetGradeSpecialConfigDto dto, BindingResult br) throws Exception {
        // 新建、修改校验
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 调用共通自动生成额度标签代码
        if (StringUtil.isEmpty(dto.getSeqno())) {
            dto.setSeqno(IDGenerator.generate());
        }
        ProcessParameter<ModAssetGradeSpecialConfigDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ModAssetGradeSpecialConfigDto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 授权代码作为业务编号
        String businessCode = dto.getFinprodId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(DictCodeListExt3.共通业务类型_特殊资产评级配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产代码",
                    GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                    "资产名称", GlobalConstant.OPEN_PARENTHESIS, entity.getFinprodName(),
                    GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = specialassets002Service.start(processParameter, dto);
        return new AjaxResult(msg);
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
        List<SelectOptionDto> selectOptionList = new ArrayList<>();
        String finprodId =(String)param.get("finprodId");
        //根据产品获取产品的证券品种
        String secType = specialassets002Service.getSecTypeByFinprodId(param);
        if(StringUtil.isNotEmpty(finprodId)){
            param.put("dict_code_parent", DictCode.证券品种.getValue());
            param.put("dict_code_child", DictCode.评级类型_减值.getValue());
            param.put("item_code_parent", secType);
            selectOptionList =assetCategories001Service.getLinkSelectOption(param);;
        }
        return selectOptionList;
    }

}
