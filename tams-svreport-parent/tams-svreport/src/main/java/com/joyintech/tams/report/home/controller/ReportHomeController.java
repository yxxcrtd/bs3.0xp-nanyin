/*
 * 文件名：ReportHomeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月15日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.home.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.report.home.dto.InfoAreaDto;
import com.joyintech.tams.report.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.tams.report.home.service.ReportHomeMService;


/**
 * 主页Controller
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月15日
 * @see ReportHomeController
 * @since 1.0
 */
@Controller
@PageId("SvreportHome001")
@RequestMapping("/system/reportHome")
public class ReportHomeController extends BaseController {

    /**
     * 业务dao
     */
    @Autowired
    private ReportHomeMService reportHomeMService;
    /**
    *
    * 取得信息表区域的数据
    *
    * @return 信息表区域的统计数据
    */
    @RequestMapping("/searchInfoArea")
    @ResponseBody
    public Map<String,InfoAreaDto> searchInfoArea() {
        return reportHomeMService.searchInfoArea();
    }
    /**
    *
    * 取得报送信息登记的数据
    *
    * @return 报送信息登记的统计数据
    */
    @RequestMapping("/searchReportInfo")
    @ResponseBody
    public Map<String,SubmitInformationRegistrationDto> searchReportInfo() {
    	List<String> menulist = reportHomeMService.selectMenulist();
        return reportHomeMService.searchReportInfo(this.getParam("searchDate"),menulist);
    }
    /**
    *
    * 取得可批量导出删除模板的数据
    *
    * @return 可批量导出删除模板的统计数据
    */
    @RequestMapping("/searcheBatchexportTemplates")
    @ResponseBody
    public Map<String,InfoAreaDto> searcheBatchxportTemplates() {
        return reportHomeMService.searcheBatchxportTemplates();
    }
    /**
     *
     * 取得可批量导出删除模板的数据
     *
     * @return 可批量导出删除模板的统计数据
     */
    @RequestMapping("/getSystemNow")
    @ResponseBody
    public AjaxResult getSystemNow(){
        return reportHomeMService.getSystemNow();
    }

    /**
    *
    * 取得持有信息数据来源
    *
    * @return 持有信息数据来源
    */
   @RequestMapping("/getHoldInfoSourceInfo")
   @ResponseBody
   public AjaxResult getHoldInfoSourceInfo() {
       return reportHomeMService.getHoldInfoSourceInfo();
   }
}
