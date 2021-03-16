package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;


/**
 * 余额、份额核查共通
 * @author wdb
 *
 */
@Repository
public interface InvestorsQualityCheckMJQFEYEComMapper {	
	/**
	 * 错误记录数查询
	 * --余额、份额核查共通
	 * @param param
	 * @return
	 */
	BigDecimal cntMJQFEYETolCom(Map<String, Object> param);

	/**
	 * 错误记录信息查询
	 * --余额、份额核查共通
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchMJQFEYERecCom(Map<String, Object> param);
}
