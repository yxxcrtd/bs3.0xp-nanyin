/*
 * 文件名：ValSubjectWaterQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.service.ValSubjectWaterQuery001Service;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;

/**
 * 科目流水查询
 * 
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValSubjectWaterQuery001Controller
 * @since 1.0
 */

@RestController(value="serviceValSubjectWaterQuery001Controller")
@RequestMapping("/ValSubjectWaterQuery001")
public class ValSubjectWaterQuery001Controller extends BaseController {

    @Autowired
    private ValSubjectWaterQuery001Service valSubjectWaterQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    
    @PostMapping("/findBooksets")
    public List<SelectOptionDto> findBooksets() {
        return valSubjectWaterQuery001Service.findBooksets();
       }
    
    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getSubjectNo/{page}/{length}")
    public Page<SelectOptionDto> getSubjectNo(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valSubjectWaterQuery001Service.getSubjectNo(page,length,pageParam);
       }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
/*    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId(){
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }*/


    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValSubjectWaterQuery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valSubjectWaterQuery001Service.findPage(page, length, param);
    }
    
    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 入账日期区间
        String bookDateBegin = this.getParam("bookDateBegin");
        String bookDateEnd = this.getParam("bookDateEnd");
        if(StringUtil.isNotEmpty(bookDateBegin)){
            paraMap.put("bookDateBegin", DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
        }
        if(StringUtil.isNotEmpty(bookDateEnd)){
            paraMap.put("bookDateEnd", DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
        }
        return paraMap;
    }

    /**
     * 导出数据
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @PostMapping("/getSubjectWater")
    public List<ValSubjectWaterQuery001Dto> getSubjectWater(ValSubjectWaterQuery001SearchDto dto) throws Exception {
		return valSubjectWaterQuery001Service.getSubjectWater(dto);
    }
  
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return valSubjectWaterQuery001Service.export(this.getPageId(), RequestUtil.getPageParam());

    }
}