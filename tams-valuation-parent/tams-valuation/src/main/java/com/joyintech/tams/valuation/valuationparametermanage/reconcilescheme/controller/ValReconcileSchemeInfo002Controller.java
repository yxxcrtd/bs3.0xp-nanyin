/*
 * 文件名：ReconcileSchemeInfo002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo002Dto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.service.ValReconcileSchemeInfo002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 对账规则方案配置新建Controller
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ValReconcileSchemeInfo002")
@RequestMapping("/valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002")
public class ValReconcileSchemeInfo002Controller extends BaseController {

    /** 对账规则方案配置新建Service */
    @Autowired
    private ValReconcileSchemeInfo002Service reconcileSchemeInfo002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model,  DictCode.是否);
        // 获取方案类型---更改方案名称时，赋值使用
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        SelectDto selectDto = this.getSelectDataFromDataDic( DictCode.配置方案类型, null);
        for(SelectOptionDto dto : selectDto.getOptions()){
            if(DictCodeListExt1.配置方案类型_估值表对账方案.getValue().equals(dto.getId())
                //||DictCodeListExt1.配置方案类型_余额表对账方案.getValue().equals(dto.getId())
                ){
                options.add(dto);
            }
        }
        model.addAttribute("SCHEME_TYPE", JsonUtils.objectArray2Json(options.toArray()));
        return super.init(model);
    }
    
    /**
     * 检索账套代码(只取理财账套)
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        return reconcileSchemeInfo002Service.getBooksetId(param);
    }

    /**
     * 画面check
     * 
     * @param dto ReconcileSchemeInfo002DtoValidator
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCheck")
    public AjaxResult check(@Valid @RequestBody ValReconcileSchemeInfo002Dto dto,
                           BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 保存处理
     * 
     * @param record 对账规则方案配置新建信息
     * @param result 保存结果
     * @return 保存结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult saveDataDict(@RequestBody ValReconcileSchemeInfo002Dto record,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        reconcileSchemeInfo002Service.doSave(record);
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }

}
