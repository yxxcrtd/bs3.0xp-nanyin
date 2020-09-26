/*
 * 文件名：ValAssetsFundQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.tams.valuation.assets.fund.dto.ValCashPlanDto;
import com.joyintech.tams.valuation.assets.fund.service.ValAssetsFundQuery001Service;
import com.joyintech.tams.valuation.assets.fund.service.ValAssetsFundQuery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 基金类资产详情页面
 * 
 * @author zhangwei
 * @version 1.0 2018年7月20日
 * @see ValAssetsFundQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetsFundQuery002")
@RequestMapping("/valuation/assets/fund/ValAssetsFundQuery002")
public class ValAssetsFundQuery002Controller extends BaseController {

    @Autowired
    private ValAssetsFundQuery002Service valAssetsFundQuery002Service;

    @Autowired
    private ValAssetsFundQuery001Service valAssetsFundQuery001Service;

    /**
     * 详情页初始化页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        String finprodId = getParam("finprodId");
        // 操作类型
        String operateType = this.getParam("operateType");
        // 基金类型
        String fundType = DictCodeList.基金类型_货币基金.getValue();
        // 境内外
        String blnArea = DictCodeList.境内外_境内.getValue();
        // 是否按日复利
        String isCompoundInt = DictCodeList.是否_是.getValue();
        // 本金收益特征
        String capiIncomeFeature = DictCodeList.产品收益标识_保证收益.getValue();

        // 分红频率
        /**MOIDFY BY WUJIE ON 20190706 START
         String payCycle = DictCodeList.基金分红频率_日.getValue();
         **/
        String payCycle = DictCodeList.频率_日.getValue();
        /**MOIDFY BY WUJIE ON 20190706 END**/

        // 营业日准则
        // String tdayRule = DictCodeList.营业日准则_向后调整.getValue();
        String endTdayRule = DictCodeList.营业日准则_向后调整.getValue();
        String payTdayRule = DictCodeList.营业日准则_向后调整.getValue();
        // 推算方式
        String calcRule = DictCodeList.推算方式_起息日向后.getValue();
        // 交易场所
        String tradeMarket = DictCodeListExt1.交易场所类别_场内.getValue();
        // 交易日历
        String calendarId = DictCodeList.交易日历_中国银行间交易日历.getValue();
        // 申购确认速度
        String purSpeed = DictCodeList.申购确认速度_T_0.getValue();
        // 赎回确认速度
        String redSpeed = DictCodeList.赎回确认速度_T_0.getValue();
        // 发行日
        String idate = "";
        // 分红规则-分红日（取当前日期）
        String sdate = DateUtil.format(new Date(), "yyyy-MM-dd");
        //收益类型
        String profitType = null;
        // 判断是修改操作还是新建操作
        if (StringUtils.isNotEmpty(finprodId)) {
            ValAssetsFundQuery002Dto dto = new ValAssetsFundQuery002Dto();
            // 判断是修改还是双击查看
            if (operateType.equals("view")) {
                dto = valAssetsFundQuery002Service.findByfinprodId(finprodId);
                // 操作类型：双击查看edit
                dto.setOperateType(operateType);
            }
            else {
                dto = valAssetsFundQuery002Service.findByfinprodId(finprodId);
                if (dto.getPlanList().size() <= 0) {
                    List<ValCashPlanDto> planList = new ArrayList<>();
                    ValCashPlanDto valCashPlanDto = new ValCashPlanDto();
                    planList.add(valCashPlanDto);
                    dto.setPlanList(planList);
                }
                // 操作类型：修改edit
                dto.setOperateType(operateType);
            }

            // 修改操作
            model.addAttribute("newOrEdit", true);
            profitType = dto.getProfitType();
            fundType = dto.getFundType();
            blnArea = dto.getBlnArea();
            isCompoundInt = dto.getIsCompoundInt();
            capiIncomeFeature = dto.getCapiIncomeFeature();
            payCycle = dto.getPayCycle();
            // tdayRule = dto.getTdayRule();
            calcRule = dto.getCalcRule();
            tradeMarket = dto.getTradeMarket();
            calendarId = dto.getCalendarId();
            purSpeed = "0" + dto.getPurSpeed() + "";
            redSpeed = "0" + dto.getRedSpeed() + "";

            // 发行日
            idate = DateUtil.format(dto.getIdate(), "yyyy-MM-dd");
            sdate = DateUtil.format(dto.getSdate(), "yyyy-MM-dd");

            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", finprodId);
            // 判断是否存在交易
            boolean flag = valAssetsFundQuery001Service.checkFindprodId(params);
            if (flag) {
                model.addAttribute("trade", "true");
            }
            model.addAttribute("entity", dto);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            ValAssetsFundQuery002Dto dto = valAssetsFundQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
         // 修改操作
            model.addAttribute("newOrEdit", true);
            profitType =  dto.getProfitType();
            fundType = dto.getFundType();
            blnArea = dto.getBlnArea();
            isCompoundInt = dto.getIsCompoundInt();
            capiIncomeFeature = dto.getCapiIncomeFeature();
            payCycle = dto.getPayCycle();
            //tdayRule = dto.getTdayRule();
            calcRule = dto.getCalcRule();
            tradeMarket = dto.getTradeMarket();
            calendarId = dto.getCalendarId();
            purSpeed = "0" + dto.getPurSpeed() + "";
            redSpeed = "0" + dto.getRedSpeed() + "";

            // 发行日
            idate = DateUtil.format(dto.getIdate(), "yyyy-MM-dd");
            sdate = DateUtil.format(dto.getSdate(), "yyyy-MM-dd");

            model.addAttribute("entity", dto);
        }
        else {
            // 新建操作
            model.addAttribute("newOrEdit", false);
            ValAssetsFundQuery002Dto dto = new ValAssetsFundQuery002Dto();
            List<ValCashPlanDto> planList = new ArrayList<>();
            ValCashPlanDto valCashPlanDto = new ValCashPlanDto();
            valCashPlanDto.setMdate(new Date());
            planList.add(valCashPlanDto);
            dto.setPlanList(planList);
            // 操作类型：新建new
            dto.setOperateType(operateType);
            model.addAttribute("entity", dto);
        }
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易场所类别);
        this.setSelectOptionToModel(model, DictCode.境内外);
        this.setSelectOptionToModel(model, DictCode.本金收益特征);
        this.setSelectOptionToModel(model, DictCode.基金分红频率);
        this.setSelectOptionToModel(model, DictCode.营业日准则);
        this.setSelectOptionToModel(model, DictCode.推算方式);
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.交易日历);
        this.setSelectOptionToModel(model, DictCode.申购确认速度);
        this.setSelectOptionToModel(model, DictCode.赎回确认速度);
        //<!-- modify by huangjian on 20190116 start -->
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
        //<!-- modify by huangjian on 20190116 end -->
        this.setSelectOptionToModel(model, DictCode.收益类型);
        model.addAttribute("profitType", profitType);
        model.addAttribute("fundType", fundType);
        model.addAttribute("blnArea", blnArea);
        model.addAttribute("isCompoundInt", isCompoundInt);
        model.addAttribute("capiIncomeFeature", capiIncomeFeature);
        model.addAttribute("payCycle", payCycle);
        // model.addAttribute("tdayRule", tdayRule);
        model.addAttribute("endTdayRule", endTdayRule);
        model.addAttribute("payTdayRule", payTdayRule);
        model.addAttribute("calcRule", calcRule);
        model.addAttribute("tradeMarket", tradeMarket);
        model.addAttribute("calendarId", calendarId);
        model.addAttribute("purSpeed", purSpeed);
        model.addAttribute("redSpeed", redSpeed);
        model.addAttribute("idate", idate);
        model.addAttribute("sdate", sdate);

//        // 工作流ID
//        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
//        if (StringUtil.isNotEmpty(workflowId)) {
//            model.addAttribute(GlobalConstant.ENTITY,
//                valAssetsFundQuery002Service.getBussinessEntityByWorkflowId(workflowId));
//        }
        return super.init(model);
    }

    // /**
    // * 提交 插入
    // *
    // * @return boolean
    // * @see
    // * @since 1.0
    // */
    // @RequestMapping("doCommit")
    // @ResponseBody
    // public AjaxResult doCommit(@Valid @RequestBody ValAssetsFundQuery002Dto dto,
    // BindingResult result) {
    // if (result != null && result.hasErrors()) {
    // return new AjaxResult(result);
    // }
    // valAssetsFundQuery002Service.insert(dto);
    // return new AjaxResult(getMessage("COMMON_I_0001"));
    // }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValAssetsFundQuery002Dto dto, BindingResult result,
                            String operateType) {
        return commonCheck(result, operateType);
    }

    /**
     * 新建走流程
     * 
     * @param dto
     * @param br
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult doSave(@RequestBody @Valid ValAssetsFundQuery002Dto dto, BindingResult br) {

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 启动流程
        ProcessParameter<ValAssetsFundQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetsFundQuery002Dto> params = new WorkFlowParameter<>();

        // 业务代码（资产代码）
        params.setBusinessCode(dto.getFinprodId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        // 页面id
        params.setPageId(this.getPageId());
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("基金类资产新建：", entity.getFinprodId(),
            entity.getFundType()));

        // 塞入流程参数
        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetsFundQuery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * 修改走流程
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    public AjaxResult doUpdate(@RequestBody @Valid ValAssetsFundQuery002Dto dto,
                               BindingResult br) {
        // 启动流程
        ProcessParameter<ValAssetsFundQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValAssetsFundQuery002Dto> params = new WorkFlowParameter<>();

        // 业务代码（资产代码）
        params.setBusinessCode(dto.getFinprodId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_基金类资产.getValue());
        // 页面id
        params.setPageId(this.getPageId());
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("基金类资产修改：", entity.getFinprodId(),
            entity.getFundType()));

        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valAssetsFundQuery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }

}
