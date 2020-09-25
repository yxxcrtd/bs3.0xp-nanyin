package com.joyintech.service.portfolio.ptlsubfundsmange.dao;

import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.tams.common.base.entity.FinProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PtlSubFundsMange002Mapper {

	/**
	 * 查询数据存在有效或者流程中的数据
	 * 
	 * @param param
	 * @return
	 */
	int queryDataEffOrInWorkFlowCount(Map<String, Object> param);

	/**
	 * 获取资金分配信息(流程中)
	 * 
	 * @param param
	 * @return
	 */
	List<PtlSubFundsMange002Dto> queryPtlSubFundsInWorkFlow(Map<String, Object> param);

	/**
	 * 获取资金分配信息(有效)
	 * 
	 * @param param
	 * @return
	 */
	List<PtlSubFundsMange002Dto> queryPtlSubFundsInEffective(Map<String, Object> param);

	/**
	 * 获取子组合资金分配明细列表
	 * 
	 * @param param
	 * @return
	 */
	List<PtlSubFundsMange002Dto> selectPortfolioDetail(Map<String, Object> param);

	/**
	 * 更新组合现金流水中拆分标识
	 * 
	 * @param param
	 * @return
	 */
	int updatePtlCashPositionFlowBySeqnos(Map<String, Object> param);

	/**
	 * 
	 * @param string
	 * @return
	 */
	FinProduct selectFinProductByFinprodMarketId(String string);

}
