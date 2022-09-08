package com.joyintech.tams.disclosure.information.config.performance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import com.joyintech.tams.disclosure.information.config.performance.service.Performance001Service;
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

@PageId("Performance002")
@Controller
@RequestMapping("/disclosure/information/config/performance/Performance002")
public class Performance002Controller extends BaseController {

    @Autowired
    private Performance001Service performance001Service;
    
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodId = getParam("prodId");
        String effDate = getParam("effDateStr");
        String expDate = getParam("expDateStr");
        HashMap map = new HashMap();
        map.put("prodId",prodId);
        map.put("effDate",effDate);
        map.put("expDate",expDate);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(prodId)) {
            // 根据主键获取业务实体
            List<Performance001Dto> entityList = performance001Service.selectByPrimaryKey(map);
            Performance001Dto entity =new Performance001Dto();
            entity.setEffDateO(entityList.get(0).getEffDate());
            entity.setExpDateO(entityList.get(0).getExpDate());
            entity.setEffDate(entityList.get(0).getEffDate());
            entity.setExpDate(entityList.get(0).getExpDate());
            entity.setProdId(entityList.get(0).getProdId());
            if (entityList.get(0).getDataType()!=null && StringUtil.equals(entityList.get(0).getDataType(),"01")){
                entity.setZzqj(entityList.get(0).getProportion());
            }
            if (entityList.get(1).getDataType()!=null && StringUtil.equals(entityList.get(1).getDataType(),"02")){
                entity.setHs300(entityList.get(1).getProportion());
            }
            if (entityList.get(2).getDataType()!=null && StringUtil.equals(entityList.get(2).getDataType(),"03")){
                entity.setZzcf(entityList.get(2).getProportion());
            }
            if (entityList.get(3).getDataType()!=null && StringUtil.equals(entityList.get(3).getDataType(),"04")){
                entity.setZz500(entityList.get(3).getProportion());
            }
//            entity.setHs300(entityList.get(0).getProportion());
//            entity.setZz500(entityList.get(1).getProportion());
//            entity.setZzcf(entityList.get(2).getProportion());
//            entity.setZzqj(entityList.get(3).getProportion());
            model.addAttribute("entity", entity);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            Performance001Dto entity = performance001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) Performance001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) Performance001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        ProcessParameter<Performance001Dto> processParameter = new ProcessParameter<Performance001Dto>();
        WorkFlowParameter<Performance001Dto> params = new WorkFlowParameter<Performance001Dto>();
//        if(StringUtil.equals(dto.getOperateType(),"new")){
//            String code = IDGenerator.generate(36);
//            dto.setUuid(code);
//        }
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
//        dto.setCreateTime(sysDate);
//        dto.setUpdateTime(sysDate);
//        dto.setCreateUser(CommonUtil.getUserName());
//        dto.setUpdateUser(CommonUtil.getUserName());
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品业绩比较基准维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = performance001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
