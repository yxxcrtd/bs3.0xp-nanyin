/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepDetailsShow002Contoller
 * @since 1.0
 */

@Controller
@PageId("RepDetailsShow002")
@RequestMapping("/disclosure/information/reveal/detailshow/RepDetailsShow002")
public class RepDetailsShow002Contoller extends BaseController {
	

	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    
    public String init(Model model) {
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.REPORT_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
        model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
        return super.init(model);
    }

    
}