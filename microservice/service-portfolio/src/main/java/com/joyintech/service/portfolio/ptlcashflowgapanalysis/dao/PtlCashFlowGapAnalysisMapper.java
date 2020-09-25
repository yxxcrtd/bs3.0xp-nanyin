package com.joyintech.service.portfolio.ptlcashflowgapanalysis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlcashflowgapanalysis.dto.PtlCashFlowGapAnalysisDto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
/**
 * 
 * @author pancz
 *  注解
 */
@Repository
public interface PtlCashFlowGapAnalysisMapper {
	
	/**
     * 获取组合代码
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
	
	/**
	 * 
	 * @param map 输入条件
	 * @return  查询结果
	 */
	List<SelectOptionDto> getFundAccount(Map<String,Object> map);
	
	/**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlCashFlowGapAnalysisDto> select(Map<String, Object> pageParam);
    /**
     * 汇总维度为日期的查询界面查询事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlCashFlowGapAnalysisDto> selectForDate(Map<String, Object> pageParam);
    /**
     * 汇总维度为期限结构查询界面查询事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlCashFlowGapAnalysisDto> selectForTerm(Map<String, Object> pageParam);
}
