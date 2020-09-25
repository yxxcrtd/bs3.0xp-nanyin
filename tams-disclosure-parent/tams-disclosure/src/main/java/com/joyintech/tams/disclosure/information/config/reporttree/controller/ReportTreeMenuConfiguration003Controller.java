/*
 * 文件名：ReportTreeMenuConfiguration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.information.config.reporttree.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;

/**
 * 增加子级
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月6日
 * @see ReportTreeMenuConfiguration003Controller
 * @since 1.0
 */
@Controller
@PageId("ReportTreeMenuConfiguration003")
@RequestMapping("/disclosure/information/config/reporttree/ReportTreeMenuConfiguration003")
public class ReportTreeMenuConfiguration003Controller extends BaseController{
    
    @Autowired
    private ReportTreeMenuConfiguration001Service reportTreeMenuConfiguration001Service;
    
    /**
     * 页面初始化，显示画面<br> 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
        model.addAttribute("PId", RequestUtil.getPageParam().get("pid"));
        return super.init(model);
    }
    
    /**
     * 增加父节点
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAddSonNode")
    @ResponseBody
    public AjaxResult getInterGroup(){
        AjaxResult ajaxResult=
            reportTreeMenuConfiguration001Service.AddSonNode(RequestUtil.getPageParam());
        return ajaxResult;
    }
}
