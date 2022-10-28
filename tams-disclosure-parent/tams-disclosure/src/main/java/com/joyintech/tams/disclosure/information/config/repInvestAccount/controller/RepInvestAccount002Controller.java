package com.joyintech.tams.disclosure.information.config.repInvestAccount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.service.RepInvestAccountService;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
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
import java.util.List;
import java.util.Map;

@Controller
@PageId("RepInvestAccount002")
@RequestMapping("/disclosure/information/config/repInvestAccount/RepInvestAccount002")
public class RepInvestAccount002Controller extends BaseController {
    @Autowired
    private RepInvestAccountService repInvestAccountService;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Map<String, Object> parm = RequestUtil.getPageParam();
        String pageModel = this.getParam("pageModel");
        if (!StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, pageModel)) {
            String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
            if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
                RepInvestAccountDto dto = repInvestAccountService.getBussinessEntityByWorkflowId(
                        workflowId);
                model.addAttribute(GlobalConstant.ENTITY, dto);
            } else {
                // 编辑
                RepInvestAccountDto page = repInvestAccountService.selectData(parm);
                model.addAttribute(GlobalConstant.ENTITY, page);
            }
        }
        this.setSelectOptionToModel(model, DictCodeExt1.托管账户);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("queryProdIdInfo2")
    public List<SelectOptionDto> queryProdIdInfo2() {
        return repInvestAccountService.queryProdIdInfo2(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/selectProdName")
    public  Map<String, String> selectProdName(String prodId){
        return repInvestAccountService.selectProdName(prodId);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepInvestAccountDto dto,
                            BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 数据保存操作
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepInvestAccountDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<RepInvestAccountDto> processParameter = new ProcessParameter<RepInvestAccountDto>();
        WorkFlowParameter<RepInvestAccountDto> params = new WorkFlowParameter<RepInvestAccountDto>();
        params.setPageId("RepInvestAccount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_投资账户信息维护.getValue());
        params.setComment(entity -> "产品主代码" + "(" + entity.getProdId() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

//        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        Date sysDate = new Date();
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = repInvestAccountService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    @ResponseBody
    @RequestMapping("checkExistData")
    public Boolean checkExistData(@RequestBody RepInvestAccountDto dto){
        RepInvestAccountDto repInvestAccountDto = repInvestAccountService.checkExistData(dto);
        if(repInvestAccountDto == null){
            return false;
        }else{
            return false;
        }
    }

}
