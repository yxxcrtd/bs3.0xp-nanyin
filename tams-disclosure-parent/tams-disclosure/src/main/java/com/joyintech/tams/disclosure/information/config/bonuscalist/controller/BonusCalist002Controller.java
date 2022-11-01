package com.joyintech.tams.disclosure.information.config.bonuscalist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dao.BonusCalist001Mapper;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.service.BonusCalist001Service;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PageId("BonusCalist002")
@Controller
@RequestMapping("/disclosure/information/config/bonuscalist/BonusCalist002")
public class BonusCalist002Controller extends BaseController {
    
    @Autowired
    private BonusCalist001Service bonusCalist001Service;

    @Autowired
    private BonusCalist001Mapper bonusCalist001Mapper;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodRegistNo = getParam("prodRegistNo");
        String bonusListId = getParam("bonusListId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(bonusListId)) {
            // 根据主键获取业务实体
            BonusCalist001Dto entity = bonusCalist001Service.selectByPrimaryKey(bonusListId);
            model.addAttribute("entity", entity);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            BonusCalist001Dto entity = bonusCalist001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) BonusCalist001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        if (StringUtil.equals(dto.getOperateType(),"new")) {
//            List<BonusCalist001Dto> paramList =new ArrayList<>(1);
//            paramList.add(dto);
            List<String> list = bonusCalist001Mapper.isRepeated(dto);
            if (list!=null && list.size()>0&&(list.contains("A")||list.contains("E"))) {
                AjaxResult ajax=new AjaxResult();
                ajax.setStatuCode(500);
                ajax.setMessage("（子产品代码+收益分配基准日）作为联合主键不可以重复！", MessageType.警告消息);
                return ajax;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) BonusCalist001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        ProcessParameter<BonusCalist001Dto> processParameter = new ProcessParameter<BonusCalist001Dto>();
        WorkFlowParameter<BonusCalist001Dto> params = new WorkFlowParameter<BonusCalist001Dto>();
        if(StringUtil.equals(dto.getOperateType(),"new")){
            String code = IDGenerator.generate(36);
            dto.setBonusListId(code);
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
        }
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品分红计算清单维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "子产品代码"+ "(" + entity.getProdId()+ ")"
                        +  "-" + "收益分配基准日"+ "(" + entity.getProfitBaseDateStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = bonusCalist001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId() {
        return bonusCalist001Service.getProdId(RequestUtil.getPageParam());
    }
}
