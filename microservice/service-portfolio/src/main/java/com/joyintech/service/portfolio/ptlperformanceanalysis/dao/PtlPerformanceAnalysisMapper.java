/*
 * 文件名：PtlPositionAnalysisMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlperformanceanalysis.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis001Dto;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.invest.common.evaluationentrance.dto.DailyYieldDto;

import org.springframework.stereotype.Repository;



/**
 * 组合持仓分析
 * @author wanghb
 * @version 1.0 2019年1月25日
 * @see PtlPerformanceAnalysisMapper
 * @since 1.0
 */
@Repository
public interface PtlPerformanceAnalysisMapper {
	
	/**
	 * 查询有效数据
	 * @param map
	 * @return
	 */
	List<PtlPerformanceAnalysis001Dto> queryEffectiveData(Map<String, Object> map);

	/**
	 * 查询某个时间区间中的明细信息，按照产品和收益类型分组后，时间升序，（非分组函数）
	 * @param map
	 * @return
	 */
	List<PtlPerformanceAnalysis001Dto> queryDetailList(Map<String, Object> map);
	
	
	
	List<PtlPerformanceAnalysis001Dto> queryPageList(Map<String, Object> map);
	
	List<PtlPerformanceAnalysis001Dto> queryPageList1(Map<String, Object> map);
	
	
    /**
     * 指标设置树结构数据-》组合持仓分析报表
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<TargetDetail> getAllTreeNode(Map<String, Object> map);
    
    
    /**
     * 获取动态列-》组合持仓分析报表
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<TargetDetail> getDataTableCols(Map<String, Object> map);

    
    List<TargetDetail> getTableColumn();
    
    List<TargetDetail> getDefaultTableCols(Map<String, Object> map);
    
    
    List<DailyYieldDto> queryDailyYieldDto(Map<String, Object> map);
    
    List<DailyYieldDto> queryYieldByCurveBenchmark(Map<String, Object> map); 
    
    //获取利率的收益率
    List<DailyYieldDto> queryIntYieldByBenchmark(Map<String, Object> map);  
    
    //获取曲线的收益率
    List<DailyYieldDto> queryCurveYieldByBenchmark(Map<String, Object> map);
    
    //指数行情
    List<DailyYieldDto> queryQuoYieldByBenchmark(Map<String, Object> map);
    
    DailyYieldDto queryQuoYieldToVdate(Map<String, Object> map);
    
    
    List<DailyYieldDto> queryMarkBaiscYield(Map<String,Object> param);
    
    //查询基准成分表，获取权重信息
    List<MstIndexComponent> queryIndexComponent(String indexId);
    
    
}
