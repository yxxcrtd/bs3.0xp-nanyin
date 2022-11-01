package com.joyintech.tams.disclosure.information.config.hugeinfopro.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;

import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.service.HugeInfoPro001Service;
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

import java.util.*;

@PageId("HugeInfoPro002")
@Controller
@RequestMapping("/disclosure/information/config/hugeinfopro/HugeInfoPro002")
public class HugeInfoPro002Controller extends BaseController {

    @Autowired
    private HugeInfoPro001Service hugeInfoPro001Service;

    /**
     * 业务编号组件
     */
//    @Autowired(required=false)
//    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodRegistNo = getParam("prodRegistNo");
        String redemptionId = getParam("redemptionId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(redemptionId)) {
            // 根据主键获取业务实体
            HugeInfoPro001Dto entity = hugeInfoPro001Service.selectByPrimaryKey(redemptionId);
            model.addAttribute("entity", entity);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            HugeInfoPro001Dto entity = hugeInfoPro001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) HugeInfoPro001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        if (StringUtil.equals(dto.getOperateType(),"new")) {
            dto.setRptIssueDateStr(DateUtil.format(dto.getRptIssueDate(),"yyyy/MM/dd"));
            List<HugeInfoPro001Dto> paramList =new ArrayList<>(1);
            paramList.add(dto);
            List<HugeInfoPro001Dto> list = hugeInfoPro001Service.selectHugeInfoByKey(paramList);
            if (list.size()>0) {
                 AjaxResult ajax=new AjaxResult();
                 ajax.setStatuCode(500);
                 ajax.setMessage("系统已存在相同子产品代码+报告发布日期的数据！", MessageType.错误消息);
                return ajax;
            }
        }

        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) HugeInfoPro001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        ProcessParameter<HugeInfoPro001Dto> processParameter = new ProcessParameter<HugeInfoPro001Dto>();
        WorkFlowParameter<HugeInfoPro001Dto> params = new WorkFlowParameter<HugeInfoPro001Dto>();
//        String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator .generate();
        if(StringUtil.equals(dto.getOperateType(),"new")){
            String code = IDGenerator.generate(36);
            dto.setRedemptionId(code);
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
        }
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
//        dto.setCreateTime(sysDate);
//        dto.setUpdateTime(sysDate);
//        dto.setCreateUser(CommonUtil.getUserName());
//        dto.setUpdateUser(CommonUtil.getUserName());
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdRegistNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_巨额赎回信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品登记编码"+ "(" + entity.getProdRegistNo()+ ")"
                        +  "-" + "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = hugeInfoPro001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId() {
        return hugeInfoPro001Service.getProdId(RequestUtil.getPageParam());
    }
}
