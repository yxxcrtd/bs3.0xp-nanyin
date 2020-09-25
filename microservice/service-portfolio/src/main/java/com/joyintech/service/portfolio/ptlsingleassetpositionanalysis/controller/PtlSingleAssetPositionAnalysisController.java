/*
 * 文件名：PtlSingleAssetPositionAnalysisController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.service.PtlSingleAssetPositionAnalysisService;

/**
 * 单一资产持仓分析Controller
 * @author wanghb
 * @version 1.0 2019年1月23日
 * @see PtlSingleAssetPositionAnalysisController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlSingleAssetPositionAnalysis")
public class PtlSingleAssetPositionAnalysisController {
    /**
     * 计划类资产（净值型）Service
     */
    @Autowired
    private PtlSingleAssetPositionAnalysisService ptlSingleAssetPositionAnalysisService;

    /**
     * 画面查询方法
     * @param param 条件
     * @param page 第几页
     * @param length 长度
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectAll(@RequestBody Map<String, Object> param,
                                                                @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlSingleAssetPositionAnalysisService.selectForPage(page,length,param);
    }
    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findList")
    public List<PtlSingleAssetPositionAnalysis001Dto> selectAllList(@RequestBody Map<String, Object> param)
            throws Exception {
        return ptlSingleAssetPositionAnalysisService.selectForList(param);
    }
    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return ptlSingleAssetPositionAnalysisService.getFinprodMarketId(map, page, length);
    }
    
    /**
     * 导出查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPageForExport/{page}/{length}")
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectForExport(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length)
                    throws Exception {
        return ptlSingleAssetPositionAnalysisService.selectForExport(page, length, param);
    }
}
