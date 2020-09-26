/*
 * 文件名：ValBokClawbackInfoQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery002Dto;
import com.joyintech.tams.valuation.bokclawbackInfo.service.ValBokClawbackInfoQuery002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 回补信息查询
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery002Controller
 * @since 1.0
 */
@PageId("ValBokClawbackInfoQuery002")
@Controller
@RequestMapping("/valuation/bokclawbackInfo/ValBokClawbackInfoQuery002")
public class ValBokClawbackInfoQuery002Controller extends BaseController {

    @Autowired
    private ValBokClawbackInfoQuery002Service valBokClawbackInfoQuery002Service;

    @Override
    public String init(Model model) {
        ValBokClawbackInfoQuery002Dto entity = valBokClawbackInfoQuery002Service.findBokVouch(RequestUtil.getPageParam());
        model.addAttribute(GlobalConstant.ENTITY, entity);
        return super.init(model);
    }

}
