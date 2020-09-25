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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;

/**
 * 报表树形菜单配置
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月6日
 * @see ReportTreeMenuConfiguration001Controller
 * @since 1.0
 */
@Controller
@PageId("ReportTreeMenuConfiguration001")
@RequestMapping("/disclosure/information/config/reporttree/ReportTreeMenuConfiguration001")
public class ReportTreeMenuConfiguration001Controller extends BaseController{
    
    @Autowired  //引入Service
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
        return super.init(model);  //初始化通过super调用BaseController类中的id,通过BaseController类中的id和页面交互
    }
    
    /**
     * 初始化查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
        String tmpCName= RequestUtil.getParam("TmpCName");
        List<TreeNode> treeList = reportTreeMenuConfiguration001Service.getTreeNodeList(tmpCName);
        return treeList;
    }
    
    /**
    *
    * 查询按钮查询事件
    * <功能详细描述>
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<RepInfo> findPage()
        throws Exception {
        return reportTreeMenuConfiguration001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 批量增加报表
     * <一句话功能简述>
     * <功能详细描述>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("getAddAll")
    @ResponseBody
    public AjaxResult getAddAll() throws Exception{
        AjaxResult ajaxResult=
            reportTreeMenuConfiguration001Service.checkData(RequestUtil.getPageParam());
        return ajaxResult;       
    }
    
    /**
     * 设置为报表集按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("edit")
    @ResponseBody
    public AjaxResult edit() throws Exception{
        AjaxResult ajaxResult=
            reportTreeMenuConfiguration001Service.edit(RequestUtil.getPageParam());
        return ajaxResult;       
    }
    
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getDelete")
    @ResponseBody
    public AjaxResult getDelete(){
        String Id = this.getParam("Id");
        String PId = this.getParam("PId");
        AjaxResult aResult =reportTreeMenuConfiguration001Service.getDelete(Id,PId);
        return aResult;
    }
}
