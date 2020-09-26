/*
 * 文件名：Exchangerate002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.exchangerate.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate002Dto;
import com.joyintech.tams.report.submitmaintenance.exchangerate.service.Exchangerate002Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tangliang
 * @version 1.0 2017年9月21日
 * @see Exchangerate002Controller
 * @since 1.0
 */
@Controller
@PageId("Exchangerate002")
@RequestMapping("/submitmaintenance/exchangerate/Exchangerate002")
public class Exchangerate002Controller extends BaseController{
    
    /**
     * Exchangerate002Service汇率行情新建
     */
    @Autowired
    private Exchangerate002Service exchangerate002Service;

    /**
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 添加页面类型
        model.addAttribute(GlobalConstant.DETAIL_PAGE_MODEL_KEY, this.getParam("pageModel"));
        super.setSelectOptionToModel(model, DictCode.币种_中债);
        if(this.getParam("pageModel").equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){//编辑
            this.getParam("pageModel");
            Map<String, Object> parm=RequestUtil.getPageParam();
            parm.put("publishDate",DateUtil.parse((String)parm.get("publishDate")));
            model.addAttribute(GlobalConstant.ENTITY,
                    exchangerate002Service.getInfoData(parm));
        }
        return super.init(model);
    }
    /**
     * 画面check<br>
     * 
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkDate")
    public AjaxResult checkDate(@Valid Exchangerate002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
/**
 * 保存数据
 * 
 * @param dto dto
 * @return check结果
 * @see
 * @since 1.0
 */
    @ResponseBody
    @RequestMapping("saveDate")
    public AjaxResult saveDate(Exchangerate002Dto dto) {
        String msg = this.getMessage("REPORT_COMMON_I_0006");
        exchangerate002Service.saveData(dto);
        return new AjaxResult(msg);
    }
}
