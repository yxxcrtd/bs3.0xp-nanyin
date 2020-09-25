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
 * @see ReportTreeMenuConfiguration004Controller
 * @since 1.0
 */
@Controller
@PageId("ReportTreeMenuConfiguration004")
@RequestMapping("/disclosure/information/config/reporttree/ReportTreeMenuConfiguration004")
public class ReportTreeMenuConfiguration004Controller extends BaseController{
    
    @Autowired
    private ReportTreeMenuConfiguration001Service reportTreeMenuConfiguration001Service;
    
    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
        model.addAttribute("treeName", RequestUtil.getPageParam().get("Name"));
        model.addAttribute("treeId", RequestUtil.getPageParam().get("ID"));
        model.addAttribute("pId", RequestUtil.getPageParam().get("pId"));
        return super.init(model);
    }
    
    /**
     * 编辑
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getEdit")
    @ResponseBody
    public void getInterGroup(){
        reportTreeMenuConfiguration001Service.getEdit(RequestUtil.getPageParam());
    }
}
