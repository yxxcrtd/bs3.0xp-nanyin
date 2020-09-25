/*
 * 文件名：ReportHomeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月15日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.disclosure.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.home.dto.InfoAreaDto;
import com.joyintech.tams.disclosure.home.dto.RevalInfo;
import com.joyintech.tams.disclosure.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.tams.disclosure.home.service.DisclosureHomeService;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;


/**
 * 主页Controller
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月15日
 * @see ReportHomeController
 * @since 1.0
 */
@Controller
@PageId("disclosureHome")
@RequestMapping("/disclosure")
public class DisclosureHomeController extends BaseController {

    /**
     * 业务dao
     */
    @Autowired
    private DisclosureHomeService reportHomeService;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 获取表头数据
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/getdataTableCols", produces = "text/plain;charset=utf-8") //produces:解决json中文乱码
    @ResponseBody
    public String getdataTableCols()
        throws Exception {
        List<RepFtpPath> pathList = reportHomeService.getdataTableCols();
        String jsonStr = "";
        if (pathList != null && pathList.size() > 0) {
            List<Map<String, Object>> colsList = new ArrayList<Map<String, Object>>();
            Map<String, Object> colsMap;
            boolean sortable = false;
            for (RepFtpPath path : pathList) {
                colsMap = new HashMap<String, Object>();
                colsMap.put("field", "repId" + path.getId());
                colsMap.put("title", path.getName());
                colsMap.put("sortable", sortable);
                colsMap.put("type", "String");
                colsList.add(colsMap);
            }
            jsonStr = JsonUtils.object2Json(colsList);
        }
        return jsonStr;
    }
    
    /**
     * 查询 待披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("searchdataDpl")
    @ResponseBody
    public List<RevalInfo> queryPageListDpl()throws Exception {
        Map<String, Object> parmMap = new HashMap<String, Object>();
//        parmMap.put("searchDate",this.getParam("searchDate"));
        parmMap.put("searchDateStart",this.getParam("searchDateStart"));
        parmMap.put("searchDateEnd",this.getParam("searchDateEnd"));
        return reportHomeService.queryPageListDpl(parmMap);
    }
    /**
     * 查询已披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/searchdataYpl")
    @ResponseBody
    public List<RevalInfo> queryPageListYpl()throws Exception {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("sysDate",DateUtil.format(DateUtil.now(), "yyyy-MM-dd"));
        return reportHomeService.queryListYpl(parmMap);
    }

    /**
     * 查询 预计披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/searchdataYjpl")
    @ResponseBody
    public List<RevalInfo> queryPageListYjpl()throws Exception {
        return reportHomeService.queryPageListYjpl(null);
    }
    
    /**
    *
    * 取得信息表区域的数据
    *
    * @return 信息表区域的统计数据
    * @throws Exception Exception
    */
    @RequestMapping("/searchInfoArea")
    @ResponseBody
    public Map<String,InfoAreaDto> searchInfoArea() throws Exception {
        return reportHomeService.searchInfoArea();
    }
    /**
    *
    * 取得报送信息登记的数据
    *
    * @return 报送信息登记的统计数据
    * @throws Exception Exception
    */
    @RequestMapping("/searchReportInfo")
    @ResponseBody
    public Map<String,SubmitInformationRegistrationDto> searchReportInfo() throws Exception {
        return reportHomeService.searchReportInfo(this.getParam("searchDate"));
    }
    /**
    *
    * 取得可批量导出删除模板的数据
    *
    * @return 可批量导出删除模板的统计数据
    * @throws Exception Exception
    */
    @RequestMapping("/searcheBatchexportTemplates")
    @ResponseBody
    public Map<String,InfoAreaDto> searcheBatchxportTemplates() throws Exception {
        return reportHomeService.searcheBatchxportTemplates();
    }
    /**
     *
     * 取得可批量导出删除模板的数据
     *
     * @return 可批量导出删除模板的统计数据
     * @throws Exception Exception
     */
    @RequestMapping("/getSystemNow")
    @ResponseBody
    public AjaxResult getSystemNow(){
        AjaxResult result = new AjaxResult();
//        result.setData(DateUtil.format(DateUtil.now()));
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("startDate", DateUtil.format(DateUtil.addWeeks(DateUtil.now(), -1)));//系统日期前一周
        dataMap.put("endDate", DateUtil.format(DateUtil.addWeeks(DateUtil.now(), 3)));//系统日期后三周
        dataMap.put("sysDate", DateUtil.format(DateUtil.now()));
        result.setData(dataMap);
        return result;
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
       AjaxResult result = new AjaxResult();
       Map<String, String> dataMap = new HashMap<String, String>();
       String HOLD_INFO_SOURCE = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SYS_PARA_HOLD_INFO_SOURCE);
       dataMap.put("HOLD_INFO_SOURCE", HOLD_INFO_SOURCE);
       dataMap.put("HOLD_INFO_SOURCE_01", "01");
       result.setData(dataMap);
       return result;
   }
}
