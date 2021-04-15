package com.joyintech.tams.invest.common.position.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.position.dto.PtlPositionFlowCfg;
import com.joyintech.tams.invest.common.position.dto.PtlPositionScene;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommonPtlPositionMapper {
	List<PtlPositionScene> getAllPtlPositionScene();

	List<PtlPositionFlowCfg> getAllPtlPositionFlowCfg();

	String selectAcctIdByPortfolioId(Map<String, Object> properties);

	String selectAcctIdBySecAcctId(Map<String, Object> properties);

	BigDecimal getEtrFinishAmt(Map<String, Object> params);

	List<EtrCashPositionFlow> selectEtrCashPositionFlowByEntrustId(Map<String, Object> properties);

	List<EtrSecPositionFlow> selectEtrSecPositionFlowByEntrustId(Map<String, Object> properties);

	List<PtlCashPositionFlow> selectPtlCashPositionFlowByTradeId(Map<String, Object> properties);

	List<PtlSecPositionFlow> selectPtlSecPositionFlowByTradeId(Map<String, Object> properties);

	List<PtlCashPositionFlow> selectPtlCashPositionFlowByEntrustId(Map<String, Object> properties);

	List<PtlSecPositionFlow> selectPtlSecPositionFlowByEntrustId(Map<String, Object> properties);
	
    /**
     * 
     * 获取平台日
     * <功能详细描述>
     * @return 平台日
     * @see
     * @since 1.0
     */
    Date getSysDate();

	EtrCashPosition selectEtrCashPosition(EtrCashPositionFlow etrCashPositionFlow);

	int updateEtrCashPosition(EtrCashPosition etrCashPosition);

	EtrSecPosition selectEtrSecPosition(EtrSecPositionFlow etrSecPositionFlow);

	int updateEtrSecPosition(EtrSecPosition etrSecPosition);

	PtlSecPosition selectPtlSecPosition(PtlSecPositionFlow ptlSecPositionFlow);

	int updatePtlSecPosition(PtlSecPosition ptlSecPosition);

	PtlCashPosition selectPtlCashPosition(PtlCashPositionFlow ptlCashPositionFlow);

	int updatePtlCashPosition(PtlCashPosition ptlCashPosition);

	String getPreTrdCounterId(String tradeId);

	String getFinTradeProduct(String tradeId);

	List<EtrSecPositionFlow> selectEtrSecPositionFlowByFin(Map<String, Object> param);
}