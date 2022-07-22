package com.joyintech.tams.disclosure.information.config.proelementadj.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.tams.disclosure.information.config.proelementadj.service.ProElementAdj001Service;
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

import java.util.List;
import java.util.stream.Collectors;

@PageId("ProElementAdj002")
@Controller
@RequestMapping("/disclosure/information/config/proelementadj/ProElementAdj002")
public class ProElementAdj002Controller extends BaseController {

    @Autowired
    private ProElementAdj001Service proElementAdj001Service;
    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.是否});
        String workflowId = getParam("workflowId");
        String prodAdjustId =getParam("prodAdjustId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() &&  StringUtil.isNotEmpty(prodAdjustId)) {
            // 根据主键获取业务实体
            ProElementAdj001Dto entity = proElementAdj001Service.selectByPrimaryKey(prodAdjustId);
            model.addAttribute("entity", entity);
        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProElementAdj001Dto entity = proElementAdj001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProElementAdj001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProElementAdj001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        ProcessParameter<ProElementAdj001Dto> processParameter = new ProcessParameter<ProElementAdj001Dto>();
        WorkFlowParameter<ProElementAdj001Dto> params = new WorkFlowParameter<ProElementAdj001Dto>();
        if(StringUtil.equals(dto.getOperateType(),"new")){
            String code = IDGenerator.generate(36);
            dto.setProdAdjustId(code);
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
        }
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdRegistNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品要素调整.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品登记编码"+ "(" + entity.getProdRegistNo()+ ")"
                        +  "-" + "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = proElementAdj001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }






//    @ResponseBody
//    @RequestMapping("/getProdId")
//    public Page<SelectOptionDto> getProdId()
//            throws Exception {
//        Page<SelectOptionDto> temp = proElementAdj001Service.getProdId(RequestUtil.getPageParam());
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

        return proElementAdj001Service.getRegistNo(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId() {
        return proElementAdj001Service.getProdId(RequestUtil.getPageParam());
    }
}
