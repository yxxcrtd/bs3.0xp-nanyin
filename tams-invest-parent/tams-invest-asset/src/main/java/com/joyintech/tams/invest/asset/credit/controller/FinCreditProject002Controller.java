/*
 * 文件名：FinCreditProject002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.asset.credit.dto.InterfCreditItemInfoDto;
import com.joyintech.tams.invest.asset.credit.service.FinCreditProjectService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 *  信贷项目信息撤销
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月16日
 * @see FinCreditProject002Controller
 * @since 1.0
 */
@Controller
@PageId("FinCreditProject002")
@RequestMapping("/invest/asset/credit/FinCreditProject002")
public class FinCreditProject002Controller extends BaseController{

    @Autowired
    private FinCreditProjectService finCreditProjectService;
    @Override
    public String init(Model model){
        model.addAttribute("cashOutNo", this.getParam("cashOutNo"));
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody InterfCreditItemInfoDto dto) {
        dto.setIsRevoke(DictCodeList.是否_是.getValue());
        finCreditProjectService.update(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    
    
}
