package com.joyintech.service.trade.buyoutrepo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDetailDto;
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDto;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;

@Repository
public interface TrdOutRightRepoTradeMapper {

	
	/**
	 * 列表查询
	 * @param pageParam 查询参数
	 * @return 买断式回购
	 */
	List<TrdOutRightRepoTradeDto> queryForPage(Map<String, Object> pageParam);
	
	/**
	 * 查询债券代码下拉框
	 * @param map 查询参数
	 * @return 债券代码下拉框
	 */
	List<SelectOptionDto> querySecFinprodIdList(Map<String, Object> map);
	
	
	/** 根据交易编号查询买断式回购信息
	 * @param tradeId 交易编号
	 * @return 买断式回购信息
	 */
	TrdOutRightRepoTradeDetailDto queryBuyOutRepoByTradeId(String tradeId);

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为实际）
     *
     * @param param param
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    List<PtlSecPositionFlow> selectPtlSecPositionFlow(Map<String, Object> param);
    
    /** 检查日期区间是否有 付息计划
     * @param param 参数
     * @return 是否有 付息计划
     */
    int checkDateInPayPlan(Map<String, Object> param);
    
    /** 查询委托的剩余额度
     * @param param 参数
     * @return 
     */
    BigDecimal selectDoEntrustMoney(Map<String, Object> param);
    
    /** 根据交易编号删除质押品信息
     * @param tradeId 交易编号
     * @return Db
     */
    int deleteFinDealPledgeByTradeId(String tradeId);
    
    /** 根据交易编号删除交易类金融产品
     * @param tradeId 交易编号
     * @return Db
     */
    int deleteFinTradeProductByTradeId(String tradeId);

	long deletePtlSecPositionFlow(Map<String, Object> param);

	void updatePosition(PtlSecPosition position);
	/**
	 * 根据委托代码查询首次净价区间
	 * @param entrustId
	 * @return
	 */
    TrdOutRightRepoTradeDto getfCpriceBtw(@Param("entrustId") String entrustId);

	/**
	 * 根据参数删除组合持仓流水
	 *
	 * @param param 参数列表
	 * @return Boolean
	 * @see
	 * @since 1.0
	 */
    Boolean deletePtlSecPositionFlowByParm(Map<String, Object> param);

    List<FinCashPlan> getFinCashPlanForRepoRate(Map<String, Object> param);
    
    String findCeTradeIdByTradeId(String tradeId);
}
