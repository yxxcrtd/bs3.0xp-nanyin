/*
 * 文件名：ValAssetsFundQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.fund.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.service.valuation.assets.fund.service.ValAssetsFundQuery001Service;

/**
 * 基金类资产列表页面
 * 
 * @author zhangwei
 * @version 1.0 2018年7月25日
 * @see ValAssetsFundQuery001Controller
 * @since 1.0
 */
//@RestController(value="serviceValAssetsFundQuery001Controller")
@RestController("valAssetsFundQuery001Ctl_dtx")
@RequestMapping("/ValAssetsFundQuery001")
public class ValAssetsFundQuery001Controller extends BaseController {

    @Autowired
    private ValAssetsFundQuery001Service valAssetsFundQuery001Service;


  

    /**
     * 根据条件分页查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValAssetsFundQuery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
//        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    	 return valAssetsFundQuery001Service.findPage(page, length, param);
    }

    
    @PostMapping("/findAll")
    public List<ValAssetsFundQuery001Dto> findAll(@RequestBody Map<String, Object> param){
//        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    	 return valAssetsFundQuery001Service.findAll(param);
    }

    /**
     * 下拉框获取：资产
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getAssets")
    public List<SelectOptionDto> getAssets(@RequestBody Map<String,Object> map)
        throws Exception {
        return valAssetsFundQuery001Service.getAssets(map);
    }

    @Transactional
	@DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody ValAssetsFundQuery002Dto dto) throws Exception{
//        finBondTargetAssetService.delete(dto);
    	valAssetsFundQuery001Service.delete(dto.getFinprodId(), dto.getFundType());
    }
    
    @PostMapping("checkFindprodId")
    public boolean checkFindprodId(@RequestBody Map<String, Object> params) {
    	return valAssetsFundQuery001Service.checkFindprodId(params);
    }
    
    @PostMapping("getBusinessDataInFlow")
    public List<String> getBusinessDataInFlow(@RequestBody ProcessMark processMark) {
    	return valAssetsFundQuery001Service.getBusinessDataInFlow(processMark);
    }
    
//    @PostMapping("getBusinessDataInFlow")
//    public List<String> getBusinessDataInFlow(@RequestBody ProcessMark processMark) {
//    	return valAssetsFundQuery001Service.getBusinessDataInFlow(processMark);
//    }
   
}
