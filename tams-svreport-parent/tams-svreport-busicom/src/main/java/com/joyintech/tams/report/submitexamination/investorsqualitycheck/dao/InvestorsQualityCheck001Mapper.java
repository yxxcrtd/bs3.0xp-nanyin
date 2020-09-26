package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;


/**
 * 投资者质量核查Dao
 * @author hpp
 *
 */
@Repository
public interface InvestorsQualityCheck001Mapper {
	/**
	 * 查询左侧-核查项合格率信息
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchInvestorsPassRate(Map<String, Object> param);
	
	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件类型
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableZJLX(Map<String, Object> param);
	
	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件匹配
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableZJPP(Map<String, Object> param);
	
	/**
	 * 查询右侧-不合格数据的详细信息
	 * --及时性
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableJSX(Map<String, Object> param);

}
