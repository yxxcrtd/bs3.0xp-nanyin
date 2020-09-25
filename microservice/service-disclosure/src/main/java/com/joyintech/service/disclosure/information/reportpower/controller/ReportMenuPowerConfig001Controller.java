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

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.reportpower.dto.ReportMenuPowerConfig001Dto;
import com.joyintech.service.disclosure.information.reportpower.service.ReportMenuPowerConfig001Service;


/**
 * 报表菜单权限配置
 *
 * @author huwei
 * @version 1.0 2018年11月06日
 * @see ReportMenuPowerConfig001Controller
 * @since 1.0
 */
@RestController(value="serviceReportMenuPowerConfig001Controller")
@RequestMapping("/ReportMenuPowerConfig001")
public class ReportMenuPowerConfig001Controller extends BaseController{
    
    @Autowired
    private ReportMenuPowerConfig001Service reportMenuPowerConfig001Service;
     
	/**
     * 初始化
     * {@inheritDoc}
     */
    public String init(Model model) {
        return super.init(model);
    }
    /**
     * 查询按钮查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ReportMenuPowerConfig001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return reportMenuPowerConfig001Service.findPage(page, length, param);
    }
    
    
    @PostMapping("/findAll")
    public List<ReportMenuPowerConfig001Dto> findAll(@RequestBody Map<String, Object> param)
        throws Exception {
        return reportMenuPowerConfig001Service.findAll(param);
    }
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getDelete")
    public void getDelete(String repId){
//        String repId= RequestUtil.getParam("repId");
        reportMenuPowerConfig001Service.getDelete(repId);
    }
    
    /**
     * 权限分配
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
//    @RequestMapping("powerAssign")
//    @ResponseBody
//    public void powerAssign(){
//        String datas = this.getParam("datas");
//        System.out.println(datas);
//    }
    /**
     * 导出信息excel
     * @return ResponseEntity
     * @throws Exception Exception
     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        String datas = this.getParam("datas");
//        String datas1=datas.replace("[", "");
//        String datas2=datas1.replace("]", "");
//        List<ReportMenuPowerConfig001Dto> listdto;
//        if (datas2 == null||datas2.length()==0) {
//            listdto = (List<ReportMenuPowerConfig001Dto>)reportMenuPowerConfig001Service.findAll(
//                RequestUtil.getPageParam());
//        }
//        else {
//            listdto = JsonUtils.json2List(datas, ReportMenuPowerConfig001Dto.class);
//        }
//        File outFile = reportMenuPowerConfig001Service.makeFile(
//            "/com/joyintech/tams/disclosure/information/reportpower/config/export_ReportMenuPowerConfig001.xml",            
//            listdto);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date())
//                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
//                                 + SystemGlobalConstant.POINT
//                                 + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }
}
