package com.joyintech.tams.disclosure.information.config.profailureinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.service.ProFailureInfo001Service;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
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

@PageId("ProFailureInfo002")
@Controller
@RequestMapping("/disclosure/information/config/profailureinfo/ProFailureInfo002")
public class ProFailureInfo002Controller extends BaseController {

    @Autowired
    private ProFailureInfo001Service proFailureInfo001Service;
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodRegistNo = getParam("prodRegistNo");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(prodRegistNo)) {
            // 根据主键获取业务实体
            ProFailureInfo001Dto entity = proFailureInfo001Service.selectByPrimaryKey(prodRegistNo);
            model.addAttribute("entity", entity);
        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProFailureInfo001Dto entity = proFailureInfo001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProFailureInfo001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProFailureInfo001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
        ProcessParameter<ProFailureInfo001Dto> processParameter = new ProcessParameter<ProFailureInfo001Dto>();
        WorkFlowParameter<ProFailureInfo001Dto> params = new WorkFlowParameter<ProFailureInfo001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdRegistNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品不成立信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品登记编码"+ "(" + entity.getProdRegistNo()+ ")"
                        +  "-" + "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = proFailureInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }






//    @ResponseBody
//    @RequestMapping("/getProdId")
//    public Page<SelectOptionDto> getProdId()
//            throws Exception {
//        Page<SelectOptionDto> temp = proFailureInfo001Service.getProdId(RequestUtil.getPageParam());
//        return temp;
//    }


    /**
     * 获取页面下拉选信息(产品登记编码）
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getRegistNo")
    public List<SelectOptionDto> getRegistNo()
            throws Exception {

        return proFailureInfo001Service.getRegistNo(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId() {
        return proFailureInfo001Service.getProdId(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("checkExistData")
    public Boolean checkExistData(@RequestBody ProFailureInfo001Dto dto){
        ProFailureInfo001Dto proFailureInfo001Dto = proFailureInfo001Service.isRepeated2(dto);
        if(proFailureInfo001Dto != null && StringUtil.equals(proFailureInfo001Dto.getDataStatus(),"D")){
            return true;
        }else{
            return false;
        }
    }


}
