/*
 * 文件名：ReportMenuPowerConfig001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：huwei
 * 修改时间：2018年11月06日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.reportpower.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.disclosure.information.reportpower.dto.ReportMenuPowerConfig001Dto;
import com.joyintech.tams.disclosure.information.reportpower.service.ReportMenuPowerConfig001Service;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.base.entity.SysRole;


/**
 * 报表菜单权限配置
 *
 * @author huwei
 * @version 1.0 2018年11月06日
 * @see ReportMenuPowerConfig001
 * @since 1.0
 */
@Controller
@PageId("ReportMenuPowerConfig002")
@RequestMapping("/disclosure/information/reportpower/ReportMenuPowerConfig002")
public class ReportMenuPowerConfig002Controller extends BaseController{
    
    @Autowired
    private ReportMenuPowerConfig001Service reportMenuPowerConfig001Service;
    
	/**
     * 初始化
     * {@inheritDoc}
     */
    public String init(Model model) {
        String flag= RequestUtil.getParam("flag");
        if("Y".equals(flag)){
            //来自批量分配
            model.addAttribute("flag", RequestUtil.getPageParam().get("flag"));
            model.addAttribute("datas", RequestUtil.getPageParam().get("datas")); 
        }else{
            model.addAttribute("reportName", RequestUtil.getPageParam().get("reportName"));
            model.addAttribute("repId", RequestUtil.getPageParam().get("repId"));
        }     
        return super.init(model);
    }
    /**
     * 查询角色表的全部数据
     * @return 所有角色列表
     */
    @ResponseBody
    @RequestMapping("findAllRole")
    public List<SysRole> findAllRole() {
        return reportMenuPowerConfig001Service.selectForPage();

    }
    
    /**
     * 查询报表角色数据
     * @return 所有角色列表
     */
    @ResponseBody
    @RequestMapping("findUserRole")
    public List<SysRole> findUserRole() {
        return reportMenuPowerConfig001Service.findUserRole(RequestUtil.getPageParam());

    }
    /**
     * 保存按钮
     * @return 所有角色列表
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save() throws Exception {
        String flag= RequestUtil.getParam("flag");
        String msg = StringUtils.EMPTY;
        if("Y".equals(flag)){
         //来自批量
            String arr= RequestUtil.getParam("arr");
            String datas= RequestUtil.getParam("datas");
            List<ReportMenuPowerConfig001Dto> listarrdto = JsonUtils.json2List(arr, ReportMenuPowerConfig001Dto.class);
            List<ReportMenuPowerConfig001Dto> listdatasdto = JsonUtils.json2List(datas, ReportMenuPowerConfig001Dto.class);
            reportMenuPowerConfig001Service.saveAll(listarrdto,listdatasdto);
            msg = "修改成功";
            
        }else {
            String arr= RequestUtil.getParam("arr");
            List<ReportMenuPowerConfig001Dto> listdto = JsonUtils.json2List(arr, ReportMenuPowerConfig001Dto.class);
            String repId= RequestUtil.getParam("repId");
            reportMenuPowerConfig001Service.save(listdto,repId);
            msg = "修改成功";
        }
		return new AjaxResult(msg);
    }
}
