/*
 * 文件名：ValSubjectSet001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.dto.TrdAssetLoss001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetloss.service.TrdAssetLossService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 估值核算-财务管理-资产减值管理--预览
 *
 * @author lby
 * @version 1.0 2020年10月10日
 * @see TrdAssetLoss003Controller
 * @since 1.0
 */
@Controller
@PageId("TrdAssetLoss003")
@RequestMapping("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003")
public class TrdAssetLoss003Controller extends BaseController {

    @Autowired
    private TrdAssetLossService trdAssetLossService;

    /**
     *
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.减值测算方式);
        this.setSelectOptionToModel(model, DictCode.减值录入方式);
        model.addAttribute("outValue", DictCodeListExt3.减值测算方式_外部评估.getValue());
        model.addAttribute("expectedCreditLossModel", DictCodeListExt3.减值测算方式_预期信用损失模型.getValue());
        model.addAttribute("rateLossValue", DictCodeListExt3.减值录入方式_按比例减值.getValue());

        String finprodId = this.getParam("finprodId");
        String valDate = this.getParam("valDate");
        String lossTestType = this.getParam("lossTestType");
        String lossRatio = this.getParam("lossRatio");
        String pdValue = this.getParam("pdValue");
        String lgdValue = this.getParam("lgdValue");

        //preview
        if (WorkFlowUtil.isNotFromToDoList()) {
            TrdAssetLoss001Dto entity = new TrdAssetLoss001Dto();
            entity.setFinprodId(finprodId);
            entity.setValDate(DateUtil.parse(valDate));
            entity.setLossTestType(lossTestType);
            if (StringUtil.isNotEmpty(lossRatio)){
                entity.setLossRatio(new BigDecimal(lossRatio));
            }
            if (StringUtil.isNotEmpty(pdValue)){
                entity.setPdValue(new BigDecimal(pdValue));
            }
            if (StringUtil.isNotEmpty(lgdValue)){
                entity.setLgdValue(new BigDecimal(lgdValue));
            }
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }

        model.addAttribute("bankRoom", DictCodeListExt1.交易场所_银行间.getValue());
        model.addAttribute("bankPlat", DictCodeList.交易平台_外汇交易中心.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE") == null ? "01" : CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    /**
     *
     * 债券投资委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAssetLoss001Dto> selectForPage(){
        Page<TrdAssetLoss001Dto> page = trdAssetLossService.selectForPagePreView(
                RequestUtil.getPageParam());

        return page;
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdAssetLoss001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        if(StringUtil.equals(dto.getOperateType(), "preview")){

        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 资产名称联想下拉框
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd()
            throws Exception {
        Page<SelectOptionDto> temp = trdAssetLossService.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }
    private boolean isNumber(String str) {
        //首先判断是否为浮点数
        Pattern pattern1 = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher match = pattern1.matcher(str);
        if (!match.matches()){
            return match.matches();
        }
        Pattern pattern2 = Pattern.compile("^(([1-9\\-]{1}\\d*)|([0]{1}))(\\.(\\d){0,12})?$"); // 判断小数点后12位的数字的正则表达式
        Matcher match2 = pattern2.matcher(str);
        return match2.matches();
    }
}
