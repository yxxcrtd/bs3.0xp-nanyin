package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.controller;

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
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.service.ProFailureInfo001Service;
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
import java.util.Map;

@PageId("SubBalanceInfoPro002")
@Controller
@RequestMapping("/disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002")
public class SubBalanceInfoPro002Controller extends BaseController {

    @Autowired
    private SubBalanceInfoPro001Service subBalanceInfoPro001Service;
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.币种});
        String workflowId = getParam("workflowId");
        String booksetId = getParam("booksetId");
        String balanceDate = getParam("balanceDateStr");
        String subjectNo = getParam("subjectNo");
        HashMap map = new HashMap();
        map.put("booksetId",booksetId);
        map.put("balanceDate",balanceDate);
        map.put("subjectNo",subjectNo);
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(booksetId)) {
            // 根据主键获取业务实体
            SubBalanceInfoPro001Dto entity = subBalanceInfoPro001Service.selectByPrimaryKey(map);
            model.addAttribute("entity", entity);
        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            SubBalanceInfoPro001Dto entity = subBalanceInfoPro001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) SubBalanceInfoPro001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) SubBalanceInfoPro001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        ProcessParameter<SubBalanceInfoPro001Dto> processParameter = new ProcessParameter<SubBalanceInfoPro001Dto>();
        WorkFlowParameter<SubBalanceInfoPro001Dto> params = new WorkFlowParameter<SubBalanceInfoPro001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSubjectNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_科目余额信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "科目号"+ "(" + entity.getSubjectNo()+ ")"
                        +  "-" + "产品主代码"+ "(" + entity.getBooksetId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = subBalanceInfoPro001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }






//    @ResponseBody
//    @RequestMapping("/getProdId")
//    public Page<SelectOptionDto> getProdId()
//            throws Exception {
//        Page<SelectOptionDto> temp = subBalanceInfoPro001Service.getProdId(RequestUtil.getPageParam());
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

        return subBalanceInfoPro001Service.getRegistNo(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping("findProdId")
    public List<SelectOptionDto> findProdId() {
        return subBalanceInfoPro001Service.findProdId(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("findProdName")
    public HashMap  findProdName(@RequestBody String booksetId){
        HashMap map = new HashMap();
        return subBalanceInfoPro001Service.findProdName(booksetId);
    }


    @ResponseBody
    @RequestMapping("checkExistData")
    public Boolean checkExistData(@RequestBody SubBalanceInfoPro001Dto dto){
        HashMap map =new HashMap();
        map.put("booksetId",dto.getBooksetId());
        map.put("balanceDate",dto.getBalanceDateStr());
        map.put("subjectNo",dto.getSubjectNo());
        SubBalanceInfoPro001Dto subBalanceInfoPro001Dto = subBalanceInfoPro001Service.selectByPrimaryKey(map);
        if(subBalanceInfoPro001Dto != null && StringUtil.equals(subBalanceInfoPro001Dto.getStatus(),"D")){
            return true;
        }else{
            return false;
        }
    }


}
