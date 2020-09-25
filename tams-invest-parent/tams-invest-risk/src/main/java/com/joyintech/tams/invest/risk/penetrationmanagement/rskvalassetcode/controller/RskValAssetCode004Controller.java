/*
 * 文件名：RskValAssetCode004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode004Service;

/**
 * 资产科目映射明细新增
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RskValAssetCode004Controller
 * @since 1.0
 */
@Controller
@PageId("RskValAssetCode004")
@RequestMapping("/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004")
public class RskValAssetCode004Controller extends BaseController{

    @Autowired
    private RskValAssetCode004Service rskValAssetCode004Service;
    @Override
    public String init(Model model) {
        String srcCode = this.getParam("sourceIds");
        String tgtCode = this.getParam("targetIds");
        String id = this.getParam("id");
        RskValtblAssetSubMapDtl entity = new RskValtblAssetSubMapDtl();
        entity.setTgtCode(tgtCode);entity.setSrcCode(srcCode);entity.setId(id);
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        entity.setAllowSum(DictCodeList.是否_是.getValue());
        model.addAttribute("entity", entity);
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskValtblAssetSubMapDtlDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskValtblAssetSubMapDtlDto dto) {
        rskValAssetCode004Service.insert(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
}
