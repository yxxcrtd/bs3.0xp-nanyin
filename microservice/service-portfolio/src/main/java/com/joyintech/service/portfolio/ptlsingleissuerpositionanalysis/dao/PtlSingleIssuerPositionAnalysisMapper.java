package com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
/**
 * 
 * @author pancz
 *  注解
 */
@Repository
public interface PtlSingleIssuerPositionAnalysisMapper {
	/**
	 * 
	 * @param map 输入条件
	 * @return  查询结果
	 */
	List<SelectOptionDto> getPublisher(Map<String,Object> map);
	
	/**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlSingleIssuerPositionAnalysisDto> select(Map<String, Object> pageParam);
    
    /**
     * 查询界面导出事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlSingleIssuerPositionAnalysisDto> selectForExport(Map<String, Object> pageParam);
	/**
	 * 查询界面查询事件
	 *
	 * @param pageParam 页面参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	List<PtlSingleIssuerPositionAnalysisDto> selectAll(Map<String, Object> pageParam);
}
