/*
 * 文件名：RskValAssetCodeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.risklevel.risklevelimport.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.risk.risklevel.risklevelimport.dto.RiskLevelImportDto;
import com.joyintech.service.risk.risklevel.risklevelimport.service.RiskLevelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCodeService;

/**
 *  资产科目映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RiskLevelImportController
 * @since 1.0
 */
@RestController("RiskLevelImportCtl_dtx")
@RequestMapping("/RiskLevelImport")
public class RiskLevelImportController {

    @Autowired
    private RiskLevelImportService riskLevelImportService;
    
    

    /**
     * 初始化明细查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<RiskLevelImportDto> selectForPage(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return riskLevelImportService.selectForPage(page, length, param);
    }
    

    /**
     * 删除
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public void delete(@RequestBody List<RiskLevelImportDto> equityList){
        riskLevelImportService.delete(equityList);
     }
 
    
    /**
     * 判断映射名称是否重复
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("save")
    public void save(@RequestBody List<RiskLevelImportDto> equityList){
           riskLevelImportService.save(equityList);
    }
    

    @PostMapping("queryFinprodMarketId")
    public String queryFinprodMarketId(@RequestBody Map<String, Object> param){
        return riskLevelImportService.queryFinprodMarketId(param);
    }
}
