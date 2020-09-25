/*
 * 文件名：ValRepurchaseAssetQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.repurchase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.repurchase.dto.ValRepurchaseAssetQuery001Dto;
import com.joyintech.service.valuation.assets.repurchase.service.ValRepurchaseAssetQuery001Service;

/**
 * 回购类资产查询
 * 
 * @author wangqidong
 * @version 1.0 2018年10月19日
 * @see ValRepurchaseAssetQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceValRepurchaseAssetQuery001Controller")
@RequestMapping("/ValRepurchaseAssetQuery001")
public class ValRepurchaseAssetQuery001Controller extends BaseController {

    /**
     * valRepurchaseAssetQuery001Service
     */
    @Autowired
    private ValRepurchaseAssetQuery001Service valRepurchaseAssetQuery001Service;


    /**
     * 资产下拉框初始化
     * 
     * @param request request
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getAsset")
    public List<SelectOptionDto> getAsset(@RequestBody Map<String,Object> param) {
        return valRepurchaseAssetQuery001Service.getAsset(param);
    }

    /**
     * 根据条件获取表格数据
     * 
     * @return Page<ValRepurchaseAssetQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValRepurchaseAssetQuery001Dto> findPage(@RequestBody Map<String,Object> map,
			@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valRepurchaseAssetQuery001Service.findPage(page,length,map);
    }

    @PostMapping("/findAll")
    public List<ValRepurchaseAssetQuery001Dto> findAll(@RequestBody Map<String, Object> param){
    	 return valRepurchaseAssetQuery001Service.findAll(param);
    }
}
