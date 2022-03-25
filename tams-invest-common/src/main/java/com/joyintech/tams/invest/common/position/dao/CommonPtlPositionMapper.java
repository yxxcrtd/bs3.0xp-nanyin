package com.joyintech.tams.invest.common.position.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.position.dto.PtlPositionFlowCfg;
import com.joyintech.tams.invest.common.position.dto.PtlPositionScene;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface CommonPtlPositionMapper {
	List<PtlPositionScene> getAllPtlPositionScene();

	List<PtlPositionFlowCfg> getAllPtlPositionFlowCfg();

	String selectAcctIdByPortfolioId(Map<String, Object> properties);

	String selectAcctIdBySecAcctId(Map<String, Object> properties);

	BigDecimal getEtrFinishAmt(Map<String, Object> params);

	// modify by etliu 20220227 start
	// 广农商二阶段 GNS-953:【广农商场内清算】深交所协议回购 正/逆回购首期，手工交收-日终清结算后，头寸更新有误
	BigDecimal getDayEndEtrFinishAmt(Map<String, Object> params);
	// modify by etliu 20220227 end

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

	// add by etliu 20220116 start
	// GZYHTZJY-1145: 【场内对账清结算1231】深交所协议回购首期交易，日终头寸更新有误【解决日期：2022-01-14】】
	String selectHoldInvAim(Map<String, Object> param);
	// add by etliu 20220116 end

	FinProductOthCode selectFinProductOthCode(Map<String, Object> map);

	FinProductQuto selectFinProductQuto(Map<String, Object> map);
}