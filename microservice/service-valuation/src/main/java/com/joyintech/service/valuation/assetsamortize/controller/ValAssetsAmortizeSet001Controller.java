/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assetsamortize.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.service.valuation.assetsamortize.service.ValAssetsAmortizeSet001Service;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产摊销设置
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet001Controller
 * @since 1.0
 */
@RestController("valAssetsAmortizeSet001Ctl_dtx")
//@RestController(value="serviceValAssetsAmortizeSet001Controller")
@RequestMapping("/ValAssetsAmortizeSet001")
public class ValAssetsAmortizeSet001Controller extends BaseController {

    /**
     * valAssetsAmortizeSet001Service
     */
    @Autowired
    private ValAssetsAmortizeSet001Service valAssetsAmortizeSet001Service;


    
    @PostMapping("/getFinprodId")
    public List<SelectOptionDto> getFinprodId() {
    	  return valAssetsAmortizeSet001Service.getFinprodId();
    }
    
    @PostMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> map) {
    	  return valAssetsAmortizeSet001Service.getBooksetId(map);
    }
    
    /**
     * 表格查询
     * 
     * @return Page<ValAssetsAmortizeSet001Dto>
     * @throws Exception $.trim($('#finprodId').val())
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValAssetsAmortizeSet001Dto> findPage(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valAssetsAmortizeSet001Service.findPage(page,length,map);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findAll")
    public List<ValAssetsAmortizeSet001Dto> findAll(@RequestBody Map<String,Object> map){
        return valAssetsAmortizeSet001Service.findAll(map);
    }

    
    @GetMapping("getCdate")
    public String getCdate(String booksetId) {
    	return valAssetsAmortizeSet001Service.getCdate(booksetId);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ValAssetsAmortizeSet001Dto entity){
         return valAssetsAmortizeSet001Service.doComplete(entity);
    }


    @PostMapping("/getFinprodId2/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId2(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valAssetsAmortizeSet001Service.getFinprodId2(page,length,pageParam);
    }
 
}
