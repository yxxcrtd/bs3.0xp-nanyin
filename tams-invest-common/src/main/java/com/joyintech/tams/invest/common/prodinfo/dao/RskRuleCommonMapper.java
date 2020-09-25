package com.joyintech.tams.invest.common.prodinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.tams.common.base.entity.RskPortfolioIndicator;
import com.joyintech.tams.common.base.entity.RskRulePoolConfig;
import com.joyintech.tams.invest.common.batchList.dao.BatchListMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.risktrialrds.dto.IsupLogDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDetailDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.SupervisionParameterDto;


@Repository
public interface RskRuleCommonMapper extends BatchListMapper<RskPortfolioIndicator> {

	List<RskRulePoolConfig> getRulePoolConfig(@Param("code") String code);

	List<String> getMatchPortData(@Param("sqlWhere") String sqlWhere, @Param("isHas") String isHas, @Param("valDate") String valDate);

	/**
	 * 获取规则关联指标
	 * @param list 各个维度及维度对应的值
	 * @return 规则代码
	 * @see
	 * @since 1.0
	 */
	List<String> selectRuleRelInd(List<String> list);

	/**
	 * 删除指标与组合关联关系
	 * @param list
	 */
	void deletePortIndicator(List<RskPortfolioIndicator> list);

	/**
	 * 插入组合与指标关联操作
	 * @param list
	 */
	@Override
	void insertBatchList(List<RskPortfolioIndicator> list);
}
