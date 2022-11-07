package com.joyintech.tams.disclosure.information.config.accountamount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.accountamount.service.AccountAmount001Service;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.service.SubBalanceInfoPro001Service;
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

@PageId("AccountAmount002")
@Controller
@RequestMapping("/disclosure/information/config/accountamount/AccountAmount002")
public class AccountAmount002Controller extends BaseController {

    @Autowired
    private AccountAmount001Service accountAmount001Service;
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.科目号);
        String workflowId = getParam("workflowId");
        String booksetId = getParam("booksetId");
        String dateStart = getParam("dateStartStr");
        String dateEnd = getParam("dateEndStr");
        String subjectNo=getParam("subjectNo");
        HashMap map = new HashMap();
        map.put("booksetId",booksetId);
        map.put("dateStart",dateStart);
        map.put("dateEnd",dateEnd);
        map.put("subjectNo",subjectNo);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(booksetId)) {
            // 根据主键获取业务实体
            AccountAmount001Dto entity = accountAmount001Service.selectByPrimaryKey(map);
            model.addAttribute("entity", entity);
        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            AccountAmount001Dto entity = accountAmount001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) AccountAmount001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) AccountAmount001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        ProcessParameter<AccountAmount001Dto> processParameter = new ProcessParameter<AccountAmount001Dto>();
        WorkFlowParameter<AccountAmount001Dto> params = new WorkFlowParameter<AccountAmount001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSubjectNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_科目余额信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "科目号"+ "(" + entity.getSubjectNo()+ ")"
                        +  "-" + "产品主代码"+ "(" + entity.getBooksetId() + ")"
                        +  "-" + "开始日期"+ "(" + entity.getDateStartStr() + ")"
                        +  "-" + "结束日期"+ "(" + entity.getDateEndStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = accountAmount001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }




    @ResponseBody
    @RequestMapping("findProdId")
    public List<SelectOptionDto> findProdId() {
        return accountAmount001Service.findProdId(RequestUtil.getPageParam());
    }

}
