package com.joyintech.tams.disclosure.information.config.repRelDeal.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.service.RepRelDealService;
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
@PageId("RepRelDeal002")
@RequestMapping("/disclosure/information/config/repRelDeal/RepRelDeal002")
public class RepRelDeal002Controller extends BaseController {

    @Autowired
    private RepRelDealService repRelDealService;


    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Map<String, Object> parm = RequestUtil.getPageParam();
        String pageModel = this.getParam("pageModel");
        if(parm.containsKey("effdate")){
            parm.put("effdate", DateUtil.parse(parm.get("effdate").toString()));
        }
        if (!StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, pageModel)) {
            String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
            if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
                RepRelDealDto dto = repRelDealService.getBussinessEntityByWorkflowId(
                        workflowId);
                model.addAttribute(GlobalConstant.ENTITY, dto);
                // 判断逐渐是否为空
            } else {
                // 编辑
                RepRelDealDto page = repRelDealService.selectData(parm);
                model.addAttribute(GlobalConstant.ENTITY, page);
            }
        }
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",sysDate);
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("queryProdIdInfo2")
    public List<SelectOptionDto> queryProdIdInfo2() {
        return repRelDealService.queryProdIdInfo2(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) RepRelDealDto dto,
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
            ValidateGroupEdit.class}) RepRelDealDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<RepRelDealDto> processParameter = new ProcessParameter<RepRelDealDto>();
        WorkFlowParameter<RepRelDealDto> params = new WorkFlowParameter<RepRelDealDto>();
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            String code = IDGenerator.generate(36);
            dto.setUuid(code);
        }
        params.setPageId("RepRelDeal002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_关联交易情况维护.getValue());
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
        String msg = repRelDealService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
