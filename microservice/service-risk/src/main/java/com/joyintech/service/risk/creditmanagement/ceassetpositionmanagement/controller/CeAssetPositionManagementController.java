/*
 * 文件名：CeAssetPositionManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;
import com.joyintech.service.risk.creditmanagement.ceassetpositionmanagement.service.CeAssetPositionManagementService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月16日
 * @see CeAssetPositionManagementController
 * @since 1.0
 */
@RestController("CeAssetPositionManagementCtl_dtx")
@RequestMapping("/CeAssetPositionManagement")
public class CeAssetPositionManagementController {
    
    /**
     * CeAssetPositionManagementService
     */
    @Autowired
    private CeAssetPositionManagementService ceAssetPositionManagementService;
    
    /**
     * 
     * 画面查询方法
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CeAssetPositionManagement001Dto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return ceAssetPositionManagementService.selectForPage(page, length, param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 委外资产联想下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetCodeInfo")
    public List<SelectOptionDto> queryAssetCodeInfo(@RequestBody Map<String, Object> param) {
        return ceAssetPositionManagementService.queryAssetCodeInfo(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 委外组合代码联想下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryGroupIdInfo")
    public List<SelectOptionDto> queryGroupIdInfo(@RequestBody Map<String, Object> param) {
        return ceAssetPositionManagementService.queryGroupIdInfo(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 校验资产代码是否已存在
     * @param assetCode 资产代码
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getAssetIdByMarketId")
    public String getAssetIdByMarketId(String marketId) {
        return ceAssetPositionManagementService.getAssetIdByMarketId(marketId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 校验资产代码是否已存在
     * @param assetCode 资产代码
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("check0")
    public int check0(String assetType, String assetCode) {
        return ceAssetPositionManagementService.check0(assetType, assetCode);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 校验资产代码是否已存在
     * @param assetCode 资产代码
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("check1")
    public int check1(String assetCode) {
        return ceAssetPositionManagementService.check1(assetCode);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 导入数据
     * @param ceAssetPositionManagement
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateCeAssetPositionManagement")
    public int updateCeAssetPositionManagement(@RequestBody List<CeAssetPositionManagement001Dto> ceAssetPositionManagement) {
        return ceAssetPositionManagementService.updateCeAssetPositionManagement(ceAssetPositionManagement);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 根据组合代码和日期删除底层导入数据
     * @param ptlid
     * @param valdate
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteByPtlid")
    public int deleteByPtlid(@RequestBody Map<String, Object> map) {
        return ceAssetPositionManagementService.deleteByPtlid(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 更新占用授信额度
     * @param ptlid
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateLinesByPtlId")
    public int updateLinesByPtlId(@RequestBody String ptlid) {
        return ceAssetPositionManagementService.updateLinesByPtlId(ptlid);
    }
}
