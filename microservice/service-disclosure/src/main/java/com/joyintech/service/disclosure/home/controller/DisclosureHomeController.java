/*
 * 文件名：ReportHomeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月15日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.service.disclosure.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.disclosure.home.dto.InfoAreaDto;
import com.joyintech.service.disclosure.home.dto.RepFtpPathDto;
import com.joyintech.service.disclosure.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.service.disclosure.home.service.DisclosureHomeService;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;


/**
 * 主页Controller
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年5月15日
 * @see ReportHomeController
 * @since 1.0
 */
@RestController(value="serviceDisclosureHomeController")
@RequestMapping("/disclosureHome")
public class DisclosureHomeController extends BaseController {

    /**
     * 业务dao
     */
    @Autowired
    private DisclosureHomeService reportHomeService;
    
    
    /**
     * 获取表头数据
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getdataTableCols")
    public List<RepFtpPath> getdataTableCols()
        throws Exception {
        List<RepFtpPath> pathList = reportHomeService.getdataTableCols();
        return pathList;
    }
    
    /**
     * 查询 待披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("queryPageListDpl")
    public List<RepFtpPathDto> queryPageListDpl(@RequestBody Map<String, Object> param)throws Exception {
//        Map<String, Object> parmMap = new HashMap<String, Object>();
////        parmMap.put("searchDate",this.getParam("searchDate"));
//        parmMap.put("searchDateStart",this.getParam("searchDateStart"));
//        parmMap.put("searchDateEnd",this.getParam("searchDateEnd"));
        return reportHomeService.queryPageListDpl(param);
    }
    /**
     * 查询已披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("queryListYpl")
    public List<RepFtpPathDto> queryPageListYpl(@RequestBody Map<String, Object> param)throws Exception {
//        Map<String, Object> parmMap = new HashMap<String, Object>();
//        parmMap.put("sysDate",DateUtil.format(DateUtil.now(), "yyyy-MM-dd"));
        return reportHomeService.queryListYpl(param);
    }

    
    @PostMapping("queryYplFail")
    public List<RepFtpPathDto> queryYplFail(@RequestBody Map<String, Object> param)throws Exception {
//        Map<String, Object> parmMap = new HashMap<String, Object>();
//        parmMap.put("sysDate",DateUtil.format(DateUtil.now(), "yyyy-MM-dd"));
        return reportHomeService.queryYplFail(param);
    }
    /**
     * 查询 预计披露
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("queryPageListYjpl")
    public List<RepFtpPathDto> queryPageListYjpl(@RequestBody Map<String, Object> param)throws Exception {
        return reportHomeService.queryPageListYjpl(param);
    }
    
    /**
    *
    * 取得信息表区域的数据
    *
    * @return 信息表区域的统计数据
    * @throws Exception Exception
    */
    @PostMapping("searchInfoArea")
    public List<InfoAreaDto> searchInfoArea(@RequestBody Map<String, Object> param) throws Exception {
        return reportHomeService.searchInfoArea(param);
    }
    /**
    *
    * 取得报送信息登记的数据
    *
    * @return 报送信息登记的统计数据
    * @throws Exception Exception
    */
    @PostMapping("searchReportInfo")
    public List<SubmitInformationRegistrationDto> searchReportInfo(@RequestBody Map<String, Object> param) throws Exception {
        return reportHomeService.searchReportInfo(param);
    }
    /**
    *
    * 取得可批量导出删除模板的数据
    *
    * @return 可批量导出删除模板的统计数据
    * @throws Exception Exception
    */
    @PostMapping("searcheBatchxportTemplates")
    public List<InfoAreaDto> searcheBatchxportTemplates(@RequestBody Map<String, Object> param) throws Exception {
        return reportHomeService.searcheBatchxportTemplates(param);
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
   
   @PostMapping("getRolePageId")
   public List<String> getRolePageId(@RequestBody String userName) {
	  // userName = userName.contains("admin")?null:userName;//admin用户查询出全部权限
   		return reportHomeService.getRolePageId(userName);
   }
}
