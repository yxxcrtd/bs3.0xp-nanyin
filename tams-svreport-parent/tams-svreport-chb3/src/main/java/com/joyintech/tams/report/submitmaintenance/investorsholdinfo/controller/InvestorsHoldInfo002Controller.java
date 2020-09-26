/*
 * 文件名：InvestorsHoldInfo002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.controller;

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
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo002Service;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfoMService;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;


/**
 * 投资者持有信息新建
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月8日
 * @see InvestorsHoldInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("InvestorsHoldInfo002")
@RequestMapping("/svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo002")
public class InvestorsHoldInfo002Controller extends BaseController {
    /**
     * 逻辑业务处理bean
     */
    @Autowired
    private InvestorsHoldInfoMService investorsHoldInfoMService;
    
    /**
     * 页面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        //pageMode不为空且其不为新建时
        if(map.containsKey(GlobalConstant.DETAIL_PAGE_MODEL_KEY)&&
            !StringUtil.equals(map.get(GlobalConstant.DETAIL_PAGE_MODEL_KEY), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            ChbInvestholdInfo entity = investorsHoldInfoMService.getEntityByKey(map);
            model.addAttribute("entity", entity);
        }
        
        model.addAttribute("REGIST_CODE", JsonUtils.objectArray2Json(investorsHoldInfoMService.queryAllRegistCode().toArray()));
        model.addAttribute("CNY", DictCodeListExt1.币种_中债_人民币.getValue());
        super.setSelectOptionToModel(model, DictCode.币种_中债);
        return super.init(model);
    }
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) InvestorsHoldInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param dto 数据
     * @param result 检查结果
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doSave")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) InvestorsHoldInfo001Dto dto, BindingResult result) {
        if(result.hasErrors()){
            return new AjaxResult(result);
        }
        investorsHoldInfoMService.doSaveOrUpdate(dto);
        return new AjaxResult(this.getMessage("保存成功!"));
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param dto 数据
     * @param result 检查结果
     * @return 返回结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCnyAmt")
    public AjaxResult dealCnyAmt(){
        AjaxResult result  = new AjaxResult();
        result.setData(investorsHoldInfoMService.dealCnyAmt(RequestUtil.getPageParam()));
        return result;
    }
}
