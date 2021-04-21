/*
 * 文件名：FinBondTargetAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.projectnet.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import com.joyintech.service.asset.projectnet.service.FinProjectNetTargetAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 项目类资产标的信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAssetController
 * @since 1.0
 */
@RestController("FinProjectNetTargetAsset_dtx")
@RequestMapping("/FinProjectNetTargetAsset")
public class FinProjectNetTargetAssetController {

    @Autowired
    private FinProjectNetTargetAssetService finProjectNetTargetAssetService;
    /**
     *  查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinProjectNetTargetAssetDto> selectAll(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProjectNetTargetAssetService.selectForPage(page, length, param);
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
    public void insert(@RequestBody FinProjectNetTargetAssetDto dto) throws Exception {
        finProjectNetTargetAssetService.insert(dto);
    }
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
    public void update(@RequestBody FinProjectNetTargetAssetDto dto) throws Exception{
        finProjectNetTargetAssetService.update(dto);
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
    public void delete(@RequestBody FinProjectNetTargetAssetDto dto) throws Exception{
        finProjectNetTargetAssetService.delete(dto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getBasicInfo")
    public FinProjectNetTargetAssetDto getBasicInfo(@RequestBody Map<String,Object> paramMap) throws Exception {
        return finProjectNetTargetAssetService.getBasicInfo(paramMap);
    }
    

    @PostMapping("checkMarketExists")
    public String checkMarketExists(@RequestBody String finprodMarketId){
        return finProjectNetTargetAssetService.checkMarketExists(finprodMarketId);
    }

}
