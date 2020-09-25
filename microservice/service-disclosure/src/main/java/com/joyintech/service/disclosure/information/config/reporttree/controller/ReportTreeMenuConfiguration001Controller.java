/*
 * 文件名：ReportTreeMenuConfiguration001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.config.reporttree.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.disclosure.information.config.reporttree.dto.ReportTreeMenuConfiguration001Dto;
import com.joyintech.service.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;
import com.joyintech.tams.common.base.entity.RepInfo;

/**
 * 报表树形菜单配置
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月6日
 * @see ReportTreeMenuConfiguration001Controller
 * @since 1.0
 */
@RestController(value="serviceReportTreeMenuConfiguration001Controller")
@RequestMapping("/ReportTreeMenuConfiguration001")
public class ReportTreeMenuConfiguration001Controller extends BaseController{
    
    @Autowired  //引入Service
    private ReportTreeMenuConfiguration001Service reportTreeMenuConfiguration001Service;
    

    /**
     * 初始化查询方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("findAllData")
    public List<ReportTreeMenuConfiguration001Dto> getInterGroup(String tmpCName){
//        String tmpCName= RequestUtil.getParam("TmpCName");
    	List<ReportTreeMenuConfiguration001Dto> treeList = reportTreeMenuConfiguration001Service.getTreeNodeList(tmpCName);
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
    @PostMapping("findPage/{page}/{length}")
    public Page<RepInfo> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return reportTreeMenuConfiguration001Service.findPage(page, length, param);
    }
    /**
     * 批量增加报表
     * <一句话功能简述>
     * <功能详细描述>
     * @throws Exception 
     * @see
     * @since 1.0
     */
//    @RequestMapping("getAddAll")
//    @ResponseBody
//    public AjaxResult getAddAll() throws Exception{
//        AjaxResult ajaxResult=
//            reportTreeMenuConfiguration001Service.checkData(RequestUtil.getPageParam());
//        return ajaxResult;       
//    }
    
    
    @PostMapping("selectByKey")
    public ReportTreeMenuConfiguration001Dto selectByKey(@RequestBody Map<String, Object> param){
    	return reportTreeMenuConfiguration001Service.selectByKey(param);
    }
    
    @PostMapping("AddParentNode")
    public void AddParentNode(@RequestBody Map<String, Object> param){
    	reportTreeMenuConfiguration001Service.AddParentNodecheckData(param);
    }
    
    @GetMapping("selectByPrimaryKey")
    public List<ReportTreeMenuConfiguration001Dto> selectByPrimaryKey(String Id){
    	return reportTreeMenuConfiguration001Service.selectByPrimaryKey(Id);
    }
    
    @PostMapping("edit")
    public void edit(@RequestBody Map<String, Object> param){
    	 reportTreeMenuConfiguration001Service.edit(param);
    }
    
    /**
     * 设置为报表集按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @throws Exception 
     * @see
     * @since 1.0
     */
//    @RequestMapping("edit")
//    @ResponseBody
//    public AjaxResult edit() throws Exception{
//        AjaxResult ajaxResult=
//            reportTreeMenuConfiguration001Service.edit(RequestUtil.getPageParam());
//        return ajaxResult;       
//    }
//    
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
//    @RequestMapping("getDelete")
//    @ResponseBody
//    public AjaxResult getDelete(){
//        String Id = this.getParam("Id");
//        String PId = this.getParam("PId");
//        AjaxResult aResult =reportTreeMenuConfiguration001Service.getDelete(Id,PId);
//        return aResult;
//    }
    
    
    
    @GetMapping("findAllPNode")
    public List<ReportTreeMenuConfiguration001Dto> findAllPNode(String Id){
    	return reportTreeMenuConfiguration001Service.findAllPNode(Id);
    }
    
    
    @GetMapping("getDelete")
    public void getDelete(String Id,String PId){
    	reportTreeMenuConfiguration001Service.getDelete(Id,PId);
    }
}
