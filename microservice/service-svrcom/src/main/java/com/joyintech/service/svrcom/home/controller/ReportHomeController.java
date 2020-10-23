/*
 * 文件名：ReportHomeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月15日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.service.svrcom.home.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.report.home.dto.InfoAreaDto;
import com.joyintech.tams.report.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.tams.report.home.service.ReportHomeService;


/**
 * ReportHome服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see ReportHomeController
 * @since 1.0
 */
@RestController("backReportHomeController")
@RequestMapping("/ReportHome")
public class ReportHomeController extends BaseController {

    /**
     * 业务dao
     */
    @Autowired
    private ReportHomeService reportHomeService;
    /**
    *
    * 取得信息表区域的数据
    *
    * @return 信息表区域的统计数据
    */
    @GetMapping("/searchInfoArea")
    public Map<String,InfoAreaDto> searchInfoArea() {
        return reportHomeService.searchInfoArea();
    }
    /**
    *
    * 取得报送信息登记的数据
    *
    * @return 报送信息登记的统计数据
    */
    @PostMapping("/searchReportInfo")
    public Map<String,SubmitInformationRegistrationDto> searchReportInfo(@RequestBody Map<String, Object> param) {
        return reportHomeService.searchReportInfo((String)param.get("searchDate"),(List<String>) param.get("reportlist"));
    }
    /**
    *
    * 取得可批量导出删除模板的数据
    *
    * @return 可批量导出删除模板的统计数据
    */
    @GetMapping("/searcheBatchexportTemplates")
    public Map<String,InfoAreaDto> searcheBatchxportTemplates() {
        return reportHomeService.searcheBatchxportTemplates();
    }
    

    @GetMapping("/selectMenulist")
    public List<String> selectMenulist() {
        return reportHomeService.selectMenulist();
    }
    
}
