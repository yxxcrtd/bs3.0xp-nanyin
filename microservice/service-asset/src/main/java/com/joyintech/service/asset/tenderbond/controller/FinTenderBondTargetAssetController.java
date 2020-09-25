/*
 * 文件名：FinTenderBondTargetAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.tenderbond.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.asset.tenderbond.dto.FinTenderBondTargetAssetDto;
import com.joyintech.service.asset.tenderbond.service.FinTenderBondTargetAssetService;

/**
 * 一级债券标的信息
 * @author wangpeng
 * @version 1.0 2019年7月4日
 * @see FinTenderBondTargetAssetController
 * @since 1.0
 */
@RestController("FinTenderBondTargetAsset_dtx")
@RequestMapping("/FinTenderBondTargetAsset")
public class FinTenderBondTargetAssetController {

    @Autowired
    private FinTenderBondTargetAssetService finTenderBondTargetAssetService;
    
    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinTenderBondTargetAssetDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finTenderBondTargetAssetService.selectForPage(page, length, param);
    }
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinTenderBondTargetAssetDto dto) throws Exception {
        finTenderBondTargetAssetService.insert(dto);
    }
    // 一级市场新债日历插入 by zhaodongdong start 20191116
    /**
     * 一级市场新债日历插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("getInsert")
    public void getInsert(@RequestBody FinTenderBondTargetAssetDto dto) throws Exception {
        finTenderBondTargetAssetService.getInsert(dto);
    }
 // 一级市场新债日历插入 by zhaodongdong end 20191116
    /**
     * 修改
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody FinTenderBondTargetAssetDto dto) throws Exception{
        finTenderBondTargetAssetService.update(dto);
    }
    
    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody FinTenderBondTargetAssetDto dto) throws Exception{
        finTenderBondTargetAssetService.delete(dto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getBasicInfo")
    public FinTenderBondTargetAssetDto getBasicInfo(String finprodId) throws Exception {
        return finTenderBondTargetAssetService.getBasicInfo(finprodId);
    }
    
    /**
     * 市场代码是否重复
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectCountByfinprodMarketId")
    public int selectCountByfinprodMarketId(String finprodMarketId) {
        return finTenderBondTargetAssetService.selectCountByfinprodMarketId(finprodMarketId);
    }
    
}
