/*
 * 文件名：PtlPositionAnalysisController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlperformanceanalysis.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.TargetDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis001Dto;
import com.joyintech.service.portfolio.ptlperformanceanalysis.dto.PtlPerformanceLineDto;
import com.joyintech.service.portfolio.ptlperformanceanalysis.service.PtlPerformanceAnalysisService;

/**
 * 组合持仓分析
 * @author wangjie1
 * @version 1.0 2020年03月20日
 * @see PtlPerformanceAnalysisController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlPerformanceAnalysis")
public class PtlPerformanceAnalysisController {
    /**
     * 计划类资产（净值型）Service
     */
    @Autowired
    private PtlPerformanceAnalysisService ptlPerformanceAnalysisService;

   
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlPerformanceAnalysis001Dto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length)
		throws Exception {
		return ptlPerformanceAnalysisService.selectForPage(page,length,param);
    }
    
    /**
     * 指标设置树结构数据-》组合持仓分析报表
     * @param param 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAllTreeNode")
    public List<TargetDetail> getAllTreeNode(@RequestBody String param){
        return ptlPerformanceAnalysisService.getAllTreeNode(param);
    }
    
    /**
     * 获取动态列-》组合持仓分析报表
     * @param param 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getDataTableCols")
    public List<TargetDetail> getDataTableCols(@RequestBody String param){
        return ptlPerformanceAnalysisService.getDataTableCols(param);
    }
    
    
    @PostMapping("getTableColumn")
    public List<TargetDetail> getTableColumn(){
        List<TargetDetail> tableColumn = ptlPerformanceAnalysisService.getTableColumn();
        return tableColumn;
    }
    
    @PostMapping("getDefaultTableCols")
    public List<TargetDetail> getDefaultTableCols(@RequestBody String param){
        return ptlPerformanceAnalysisService.getDefaultTableCols(param);
    }
    
    @PostMapping("findLineData")
    public PtlPerformanceLineDto findLineData(@RequestBody Map<String, Object> param){
        return ptlPerformanceAnalysisService.findLineData(param);
    }    
    
    

}
