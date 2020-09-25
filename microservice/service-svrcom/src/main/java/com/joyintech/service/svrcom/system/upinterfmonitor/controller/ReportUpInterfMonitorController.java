/*
 * 文件名：ReportUpInterfMonitor001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统
 * 修改人：hujing
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统
 */
package com.joyintech.service.svrcom.system.upinterfmonitor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.system.upinterfmonitor.dto.ReportUpInterfMonitor001Dto;
import com.joyintech.tams.report.system.upinterfmonitor.service.ReportUpInterfMonitor001Service;


/**
 * 上游接口文件读取处理监控服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see ReportUpInterfMonitorController
 * @since 1.0
 */
@RestController
@RequestMapping("/ReportUpInterfMonitor")
public class ReportUpInterfMonitorController {

	@Autowired
	private ReportUpInterfMonitor001Service  reportUpInterfMonitor001Service;

	
	/**
     * 数据文件名下拉框
     * @return 数据文件名列表
     */
    @GetMapping("gethandleFileName")
    public List<SelectOptionDto> gethandleFileName() {
        return reportUpInterfMonitor001Service.queryHandleFileNameList();
    } 
    
    /**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ReportUpInterfMonitor001Dto> search(@RequestBody Map<String, Object> param) {
        return reportUpInterfMonitor001Service.searchForPage(param);
    }

}
