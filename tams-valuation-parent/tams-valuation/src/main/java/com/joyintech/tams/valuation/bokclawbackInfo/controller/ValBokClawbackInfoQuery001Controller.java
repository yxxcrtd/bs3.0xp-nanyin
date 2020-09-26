/*
 * 文件名：ValBokClawbackInfoQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.bokclawbackInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery001Dto;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery002Dto;
import com.joyintech.tams.valuation.bokclawbackInfo.service.ValBokClawbackInfoQuery001Service;
import com.joyintech.tams.valuation.bokclawbackInfo.service.ValBokClawbackInfoQuery002Service;

/**
 * 回补信息查询
 * 
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery001Controller
 * @since 1.0
 */
@PageId("ValBokClawbackInfoQuery001")
@Controller
@RequestMapping("/valuation/bokclawbackInfo/ValBokClawbackInfoQuery001")
public class ValBokClawbackInfoQuery001Controller extends BaseController {

    @Autowired
    private ValBokClawbackInfoQuery001Service valBokClawbackInfoQuery001Service;

    @Autowired
    private ValBokClawbackInfoQuery002Service valBokClawbackInfoQuery002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询回补信息
     * 
     * @return
     * @throws Exception
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValBokClawbackInfoQuery001Dto> findPage()
        throws Exception {
        return valBokClawbackInfoQuery001Service.findPage(RequestUtil.getPageParam());

    }

    @ResponseBody
    @RequestMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId()
        throws Exception {
        return valBokClawbackInfoQuery001Service.getBooksetId(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/countVouchs")
    public int countVouchs()
        throws Exception {
        ValBokClawbackInfoQuery002Dto dto = valBokClawbackInfoQuery002Service.findBokVouch(RequestUtil.getPageParam());
        return dto == null ? 0 : 1 ;
    }

}
