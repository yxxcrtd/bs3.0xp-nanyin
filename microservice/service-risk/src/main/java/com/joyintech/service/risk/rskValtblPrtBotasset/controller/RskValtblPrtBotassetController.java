/*
 * 文件名：RskValtblPrtBotassetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskValtblPrtBotasset.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.service.risk.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;

/**
 *  估值表底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetController
 * @since 1.0
 */
@RestController("rskValtblPrtBotassetCtl_dtx")
@RequestMapping("/RskValtblPrtBotasset")
public class RskValtblPrtBotassetController {

    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;
    
    @GetMapping("selectBotAssetMapByPtlId")
    public List<RskValtblPrtBotassetMapDto> selectBotAssetMap(String ptlId){
        return rskValtblPrtBotassetService.selectBotAssetMap(ptlId);
    }
    
    @PostMapping("findPage/{page}/{length}")
    public Page<RskValtblPrtBotassetMapDto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) {
        return rskValtblPrtBotassetService.selectForPage(page, length, param);
    }
    
    /**
     * 更新(取消)底层资产映射assetId
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody RskValtblPrtBotassetMapDto dto){
        rskValtblPrtBotassetService.update(dto);
    }
    
    
    /**
     * 底层资产映射保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @PostMapping("writebotAssetMap")
    public void writebotAssetMap(@RequestBody RskValtblPrtBotassetMapDto dto){
        rskValtblPrtBotassetService.writebotAssetMap(dto);
    }
    
    @GetMapping("selectAssetById")
    public RskValtblPrtBotassetMapDto selectAssetById(String finprodId){
        return rskValtblPrtBotassetService.selectAssetById(finprodId);
    }
    
    @GetMapping("selectWindBondAssetById")
    public RskValtblPrtBotassetMapDto selectWindBondAssetById(String finprodId){
        return rskValtblPrtBotassetService.selectWindBondAssetById(finprodId);
    }
    
    /**
     * 获取未映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCountForValUnmapped")
    public Integer getCountForValUnmapped(@RequestBody Map<String,Object> paramMap){
        return rskValtblPrtBotassetService.getCountForValUnmapped(paramMap);
    }
    
    /**
     * 获取不同映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCountForValDiffmapped")
    public Integer getCountForValDiffmapped(@RequestBody Map<String,Object> paramMap){
        return rskValtblPrtBotassetService.getCountForValDiffmapped(paramMap);
    }
    
    /**
     * 应用未映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    @PostMapping("applyMapForValUnmapped")
    public void applyMapForValUnmapped(@RequestBody Map<String,Object> paramMap){
        rskValtblPrtBotassetService.applyMapForValUnmapped(paramMap);
    }
    
    /**
     * 应用不同映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    @PostMapping("applyMapForValDiffmapped")
    public void applyMapForValDiffmapped(@RequestBody Map<String,Object> paramMap){
        rskValtblPrtBotassetService.applyMapForValDiffmapped(paramMap);
    }
    /**
     * 获取无标的信息下拉框
     * @param paramMap
     * @return
     */
    @RequestMapping("selectUnTargetAsset")
    public List<SelectOptionDto> selectUnTargetAsset(@RequestBody Map<String,Object> paramMap){
        List<SelectOptionDto> result=rskValtblPrtBotassetService.selectUnTargetAsset(paramMap);
        return result;
    }

    /**
     * @Description: 查询未映射科目组装树
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    @PostMapping("selectUnMapSubject")
    public List<RskValtblPrtBotassetMapDto>selectUnMapSubject(@RequestBody Map<String,Object> paramMap){
        return rskValtblPrtBotassetService.selectUnMapSubject(paramMap);
    }
    /**
     * @Description: 查询已映射科目 展示在表格
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    @PostMapping("selectMapSubjectInfoForPage/{page}/{length}")
    public Page<RskValtblPrtBotassetMapDto>selectMapSubjectInfoForPage(@RequestBody Map<String,Object> paramMap,
                                                                       @PathVariable("page") int page, @PathVariable("length") int length){
        return rskValtblPrtBotassetService.selectMapSubjectInfoForPage(paramMap,page,length);
    }
    /**
     * @Description: 映射&应用
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date:
     */
    @PostMapping("applyMapForSubject")
    public void applyMapForSubject(@RequestBody Map<String,Object> paramMap){
        rskValtblPrtBotassetService.applyMapForSubject(paramMap);
    }
    /**
     * @Description: 取消映射&应用
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    @PostMapping("cancelApplyMapForSubject")
    public void cancelApplyMapForSubject(@RequestBody Map<String,Object> paramMap){
        rskValtblPrtBotassetService.cancelApplyMapForSubject(paramMap);
    }
    /**
     * 获取资产维护界面维护的资产
     * @param paramMap
     * @return
     */
    @RequestMapping("selectRskAsset/{page}/{length}")
    public Page<SelectOptionDto> selectRskAsset(@PathVariable("page") int page, @PathVariable("length") int length, @RequestBody Map<String, Object> paramMap){
        return rskValtblPrtBotassetService.selectRskAsset(page,length,paramMap);
    }
    /**
     * 获取底层资产要素的信息下拉框
     * @param paramMap
     * @return
     */
 /*   @RequestMapping("selectRepAssetDept")
    public List<SelectOptionDto> selectRepAssetDept(@RequestBody Map<String,Object> paramMap){
        List<SelectOptionDto> result=rskValtblPrtBotassetService.selectRepAssetDept(paramMap);
        return result;
    }*/
}
