/*
 * 文件名：ReportMenuPowerConfig001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：huwei
 * 修改时间：2018年11月06日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.disclosure.information.reportpower.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.joyintech.service.disclosure.information.reportpower.dto.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.disclosure.information.reportpower.service.ReportMenuPowerConfig001Service;
import com.joyintech.tams.common.base.entity.RepPower;


/**
 * 报表菜单权限配置
 *
 * @author huwei
 * @version 1.0 2018年11月06日
 * @see ReportMenuPowerConfig001
 * @since 1.0
 */
@RestController(value="serviceReportMenuPowerConfig002Controller")
@RequestMapping("/ReportMenuPowerConfig002")
public class ReportMenuPowerConfig002Controller extends BaseController{
    
    @Autowired
    private ReportMenuPowerConfig001Service reportMenuPowerConfig001Service;
    

    /**
     * 查询角色表的全部数据
     * @return 所有角色列表
     */
    @PostMapping("/findAllRole")
    public List<SysRole> findAllRole() {
        return reportMenuPowerConfig001Service.selectForPage();

    }
    
    /**
     * 查询报表角色数据
     * @return 所有角色列表
     */
    @PostMapping("/findUserRole")
    public List<SysRole> findUserRole(@RequestBody Map<String,Object> map) {
        return reportMenuPowerConfig001Service.findUserRole(map);

    }
    /**
     * 保存按钮
     * @return 所有角色列表
     * @throws IOException 
     */
//    @ResponseBody
//    @RequestMapping("save")
//    public AjaxResult save() throws Exception {
//        String flag= RequestUtil.getParam("flag");
//        String msg = StringUtils.EMPTY;
//        if("Y".equals(flag)){
//         //来自批量
//            String arr= RequestUtil.getParam("arr");
//            String datas= RequestUtil.getParam("datas");
//            List<ReportMenuPowerConfig001Dto> listarrdto = JsonUtils.json2List(arr, ReportMenuPowerConfig001Dto.class);
//            List<ReportMenuPowerConfig001Dto> listdatasdto = JsonUtils.json2List(datas, ReportMenuPowerConfig001Dto.class);
//            reportMenuPowerConfig001Service.saveAll(listarrdto,listdatasdto);
//            msg = "修改成功";
//            
//        }else {
//            String arr= RequestUtil.getParam("arr");
//            List<ReportMenuPowerConfig001Dto> listdto = JsonUtils.json2List(arr, ReportMenuPowerConfig001Dto.class);
//            String repId= RequestUtil.getParam("repId");
//            reportMenuPowerConfig001Service.save(listdto,repId);
//            msg = "修改成功";
//        }
//		return new AjaxResult(msg);
//    }
    
    
    @GetMapping("deleteByKey")
    public void deleteByKey(String repId){
    	reportMenuPowerConfig001Service.getDelete(repId);
    }
    
    @PostMapping("save")
    public void save(@RequestBody RepPower repPower){
    	 reportMenuPowerConfig001Service.save(repPower);
    }
}
