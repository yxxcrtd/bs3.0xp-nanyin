package com.joyintech.service.portfolio.portfoliointerf.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.dao.BokPositionPrdMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioValuationMapper;
import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

@Service
public class PtlPortfolioValutionService {

	@Autowired
	private PtlPortfolioValuationMapper ptlPortfolioValuationMapper;
	@Autowired
	private BokPositionPrdMapper bokPositionPrdMapper;
	/**
	 * 投管接口（组合估值信息）
	 * @param paramMap
	 * @return
	 */
	public String insertPortfolioValution(Map<String, Object> paramMap) {
		PtlPortfolioValuation ptlPortfolioValuation = new PtlPortfolioValuation();
		ptlPortfolioValuation.setCdate(new Date((long) paramMap.get("cdate")));
		ptlPortfolioValuation.setCcy((String) paramMap.get("ccy"));
		ptlPortfolioValuation.setPortfolioId((String) paramMap.get("portfolioId"));
		ptlPortfolioValuation.setTdyAssetNetvalue((BigDecimal) paramMap.get("tdyAssetNetvalue"));
		ptlPortfolioValuation.setTdyAssetValue((BigDecimal) paramMap.get("tdyAssetValue"));
		ptlPortfolioValuationMapper.insert(ptlPortfolioValuation);
		
		BokPositionPrd bokPositionPrd = new BokPositionPrd();
		bokPositionPrd.setFinprodId((String) paramMap.get("portfolioId"));
		bokPositionPrd.setCdate(new Date((long) paramMap.get("cdate")));
		bokPositionPrd.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
		bokPositionPrd.setTdyShareAmt((BigDecimal) paramMap.get("tdyShareAmt"));
		bokPositionPrd.setTdyUnitValue((BigDecimal) paramMap.get("tdyUnitValue"));
		bokPositionPrd.setTdyUnitValueSum((BigDecimal) paramMap.get("tdyUnitValueSum"));
		bokPositionPrd.setTdyUnpaidIntincexp((BigDecimal) paramMap.get("tdyUnpaidIntincexp"));
		bokPositionPrd.setYield7((BigDecimal) paramMap.get("yield7"));
		bokPositionPrdMapper.insert(bokPositionPrd);
		
		return DictCodeList.是否_是.getValue();
	}

}
