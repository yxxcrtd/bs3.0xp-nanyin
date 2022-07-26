package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail001Service;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service.UnClaimAssetDetail002Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("UnClaimAssetDetail002")
@RequestMapping("/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002")
public class UnClaimAssetDetail002Controller extends BaseController {

    @Autowired
    private UnClaimAssetDetail001Service unClaimAssetDetail001Service;

    @Autowired
    private UnClaimAssetDetail002Service unClaimAssetDetail002Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String nostdAssetId = getParam("nostdAssetId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(nostdAssetId)) {
            // 根据主键获取业务实体
            UnClaimAssetDetailDto entity = unClaimAssetDetail002Service.selectDetail(nostdAssetId);
            List list = entity.getUncliamList();
            model.addAttribute("uncliamList",list);
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        else if( !WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            UnClaimAssetDetailDto entity = unClaimAssetDetail002Service.getBussinessEntityByWorkflowId(workflowId);
            List list = entity.getUncliamList();
            model.addAttribute("uncliamList",list);
            model.addAttribute(GlobalConstant.ENTITY, entity);

        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) UnClaimAssetDetailDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) UnClaimAssetDetailDto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        ProcessParameter<UnClaimAssetDetailDto> processParameter = new ProcessParameter<UnClaimAssetDetailDto>();
        WorkFlowParameter<UnClaimAssetDetailDto> params = new WorkFlowParameter<UnClaimAssetDetailDto>();
//        String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator .generate();
        String code = IDGenerator.generate(36);
        dto.setNostdAssetId(code);
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_非标债权类资产明细维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "非标资产编号"+ "(" + entity.getNostdAssetId()+ ")"
                        +  "-" + "主产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = unClaimAssetDetail002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @RequestMapping("selectDetailList")
    @ResponseBody
    public List<UnClaimAssetDetailDto> selectDetailList() {
        String nostdAssetId = this.getParam("nostdAssetId");
        return unClaimAssetDetail002Service.selectDetailList(nostdAssetId);
    }


}
