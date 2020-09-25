/*
 * 文件名：BenchmarkInterestRateInformation002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.dto.MstInterestRateQuotationInfoDto;
import com.joyintech.tams.invest.basicdata.mstinterestratequotationinfo.service.MstInterestRateQuotationInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 利率指数行情新建
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月18日
 * @see MstInterestRateQuotationInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("MstInterestRateQuotationInfo002")
@RequestMapping("/invest/basicdata/mstinterestratequotationinfo/MstInterestRateQuotationInfo002")
public class MstInterestRateQuotationInfo002Controller extends BaseController {

    /**
     * 注入利率行情信息service
     */
    @Autowired
    private MstInterestRateQuotationInfoService mstInterestRateQuotationInfoService;

    /**
     * 新建界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.利率行情, DictCode.币种);
        String intRateId = getParam("intRateId");
        String effDateStr = getParam("effDate");// 时间不匹配未解决\
        String ccy = getParam("ccy");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        // 不来自于代办
        if (StringUtil.isEmpty(workflowId) && StringUtil.isNotEmpty(intRateId)
            && StringUtil.isNotEmpty(effDateStr)) {
            // 根据主键获取业务实体
            Map<String, Object> map = new HashMap<>();
            map.put("intRateId", intRateId);
            map.put("effDate", effDateStr);
            map.put("ccy", ccy);
            MstInterestRateQuotationInfoDto dto = mstInterestRateQuotationInfoService.selectByPrimaryKey(
                map);
            // 设置到model中
//            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
        }
        // 来自于代办
        else if (!WorkFlowUtil.isNotFromToDoList()) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            MstInterestRateQuotationInfoDto dto = mstInterestRateQuotationInfoService.getBussinessEntityByWorkflowId(
                workflowId);
            // 设置到model中
            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    /**
     * 查询、修改和新建按钮点击时的check
     * 
     * @param dto 验证的实体类
     * @param result 错误结果
     * @return AjaxResult 错误结果信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstInterestRateQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * 
     * @param dto 要验证的实体类
     * @param br 验证结果
     * @return 返回验证结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstInterestRateQuotationInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<MstInterestRateQuotationInfoDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<MstInterestRateQuotationInfoDto> params = new WorkFlowParameter<>();
//        dto.setIntRate(dto.getIntRate().divide(new BigDecimal("100")));
        params.setPageId("MstInterestRateQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getIntRateId());
        params.setBusinessType(DictCodeList.共通业务类型_基准利率行情.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> this.getMessage("MstInterestRateQuotationInfo002.intRateId")
                                    + "(" + entity.getIntRateId() + ")" + "-"
                                    + this.getMessage("MstInterestRateQuotationInfo002.effDate")
                                    + "(" + DateUtil.format(entity.getEffDate()) + ")" + "-"
                                    + this.getMessage("MstInterestRateQuotationInfo002.intRate")
                                    + "(" + entity.getIntRate() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);
        // 流程开始
        String msg = mstInterestRateQuotationInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
