package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao;

import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 投资者质量核查Dao
 * @author hpp
 *
 */
@Repository
public interface InvestorsQualityCheckCPYEComMapper {
	/**
	 * 查询左侧-核查项合格率信息
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> objrec(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件类型
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> cntCPYECom(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件匹配
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchCPYECom(Map<String, Object> param);
}
