/*
 * 文件名：MstOpicsParaMante002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：胡伟
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstopicsparamante.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.joyintech.tams.invest.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.service.MstOpicsParaManteService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * OPICS参数维护信息新建
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaMante002Controller
 * @since 1.0
 */
@Controller
@PageId("MstOpicsParaMante002")
@RequestMapping("/invest/basicdata/mstopicsparamante/MstOpicsParaMante002")
public class MstOpicsParaMante002Controller extends BaseController {

    /**
     * OPICS参数维护业务层
     */
    @Autowired
    private MstOpicsParaManteService mstOpicsParaManteService;

    /**
     * 初始化方法{@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String paraType = getParam("paraType");
        String itemId = getParam("itemId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paraType", paraType);
        map.put("itemId", itemId);
        if (!WorkFlowUtil.isNotFromToDoList()) {
            String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
            MstOpicsParaManteDto dto = mstOpicsParaManteService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", dto);
        }
        else if (StringUtil.isNotEmpty(paraType) && StringUtil.isNotEmpty(itemId)) {
            MstOpicsParaManteDto dto = mstOpicsParaManteService.selectByPrimaryKey(map);
            model.addAttribute("entity", dto);
        }
        /*
         * 交易状态的下拉框赋值
         */
        setSelectOptionToModel(model, DictCode.OPICS参数类型);
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstOpicsParaManteDto dto, BindingResult result) {

        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto 实体类
     * @param br 结果集
     * @return AjaxResult
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstOpicsParaManteDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstOpicsParaManteDto> processParameter = new ProcessParameter<MstOpicsParaManteDto>();
        WorkFlowParameter<MstOpicsParaManteDto> params = new WorkFlowParameter<MstOpicsParaManteDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if (StringUtil.isEmpty(dto.getItemId())) {
            dto.setItemId(IDGenerator.generate());
        }
        params.setBusinessCode(ietm -> ietm.getItemId());
        params.setBusinessType(DictCodeList.共通业务类型_OPICS参数维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("MstOpicsParaMante002.itemId") + "(" + entity.getItemId()
                      + ")" + "-" + this.getMessage("MstOpicsParaMante002.itemName") + "("
                      + entity.getItemName() + ")"
                      + this.getMessage("MstOpicsParaMante002.paraType") + "("
                      + entity.getParaType() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstOpicsParaManteService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
