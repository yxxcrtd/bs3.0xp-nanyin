/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportconfirm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;



/**
 * 
 * 适配器服务器登录
 * @author panchengzhe
 * @version 1.0 2018年12月18日
 * @see DataAdapterLoginContoller
 * @since 1.0
 */
@Controller(value="serviceDataAdapterLoginContoller")
@PageId("DataAdapterLogin")
@RequestMapping("DataAdapterLogin")
public class DataAdapterLoginContoller extends BaseController {
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	model.addAttribute(ReportGlobalConstant.DATAADAPTER_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.DATAADAPTER_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.DATAADAPTER_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.DATAADAPTER_SERVER_PATH));
        model.addAttribute(ReportGlobalConstant.DATAADAPTER_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.DATAADAPTER_USERNAME));
        model.addAttribute(ReportGlobalConstant.DATAADAPTER_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.DATAADAPTER_PASSWORD));
         return super.init(model);
    }
}
