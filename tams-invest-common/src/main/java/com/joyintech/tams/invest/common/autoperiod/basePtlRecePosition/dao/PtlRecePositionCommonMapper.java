package com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.trade.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应收款项持仓共通Mapper
 *
 * @author zhouxiuping
 * @version 1.0 2019年12月26日
 * @see PtlRecePositionCommonMapper
 * @since 1.0
 */
@Repository
public interface PtlRecePositionCommonMapper {

    /**
     * 检索组合关联资金账户共通
     *
     * @param portfolioId 组合代码
     * @param ccy         币种
     * @param capacctType 资金账户类型
     * @return 组合关联资金账户
     */
    List<StlCapacctInfo> selectProdCapacctInfoList(@Param("portfolioId") String portfolioId, @Param("ccy") String ccy,
                                                   @Param("capacctType") String capacctType);

    /**
     * 检索产品关联资金账户共通
     *
     * @param finprodId   产品代码
     * @param ccy         币种
     * @param capacctType 资金账户类型
     * @return 产品关联资金账户
     */
    List<StlCapacctInfo> selectProdCapacctInfoListForProd(@Param("finprodId") String finprodId, @Param("ccy") String ccy,
                                                          @Param("capacctType") String capacctType);

    /**
     * 检索通道关联资金账户共通
     *
     * @param chlId 产品代码
     * @return 通道关联资金账户
     */
    List<StlCapacctInfo> selectChlCapacctInfoList(String chlId);

    /**
     * 检索交易关联资金账户共通
     *
     * @param secManageAcctId 证券托管户ID
     * @param ccy             币种
     * @return 交易关联资金账户
     */
    List<StlCapacctInfo> selectDealCapacctInfoList(@Param("secManageAcctId") String secManageAcctId, @Param("ccy") String ccy);

    /**
     * 查询可用持仓数量
     *
     * @param PtlRecePositionDto 组合可用持仓查询Dto
     * @return 可用持仓
     */
    BigDecimal selectAvailablePositionAmount(PtlRecePosition PtlRecePositionDto);

    /**
     * 查询指令持仓
     *
     * @param ptlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    List<PtlRecePosition> selectCommandPosition(PtlRecePositionFlow ptlRecePositionFlow);

    /**
     * 更新指令持仓
     * <功能详细描述>
     *
     * @param ptlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    int updateCommandPosition(PtlRecePositionFlow ptlRecePositionFlow);

    /**
     * 查询指令持仓最大日期
     * <功能详细描述>
     *
     * @param ptlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    Date selectCommandPositionMaxDate(PtlRecePositionFlow ptlRecePositionFlow);

    /**
     * 根据区间日期更新指令持仓
     * <功能详细描述>
     *
     * @param portfolioId
     * @param secAcctId
     * @param finprodId
     * @param branch
     * @param hodingType
     * @param amount
     * @param minDate
     * @param maxDate
     * @return
     * @see
     * @since 1.0
     */
    int updateCommandPositionByCDate(@Param("portfolioId") String portfolioId, @Param("secAcctId") String secAcctId,
                                     @Param("finprodId") String finprodId, @Param("branch") Long branch, @Param("hodingType") String hodingType,
                                     @Param("amount") BigDecimal amount, @Param("minDate") Date minDate, @Param("maxDate") Date maxDate);

    /**
     * 查询指令可用现金
     * <功能详细描述>
     *dto
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectCommandAvailableCash(PtlRecePositionFlow dto);

    /**
     * 检索指令现金余额数据
     * <功能详细描述>
     *
     * @param PtlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    List<PtlRecePosition> selectCommandBalance(PtlRecePositionFlow PtlRecePositionFlow);

    /**
     * 更新指令现金余额
     * <功能详细描述>
     *
     * @param PtlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    int updateCommandBalance(PtlRecePositionFlow PtlRecePositionFlow);

    /**
     * 根据区间日期查询现金余额
     * <功能详细描述>
     *
     * @param ptlRecePositionFlow
     * @return
     * @see
     * @since 1.0
     */
    Date selectCommandBalanceMaxCdate(PtlRecePositionFlow ptlRecePositionFlow);

    /**
     * 根据区间日期更新现金余额
     * <功能详细描述>
     *
     * @param portfolioId
     * @param acctId
     * @param chlFinprodId
     * @param hodingType
     * @param ccy
     * @param amount
     * @param minDate
     * @param maxDate
     * @return
     * @see
     * @since 1.0
     */
    int updateCommandBalanceByCDate(@Param("portfolioId") String portfolioId, @Param("acctId") String acctId,
                                    @Param("chlFinprodId") String chlFinprodId, @Param("hodingType") String hodingType,
                                    @Param("ccy") String ccy, @Param("amount") BigDecimal amount,
                                    @Param("minDate") Date minDate, @Param("maxDate") Date maxDate);


    /**
     * 查询已完成额度
     *
     * @param entrustId 委托代码
     * @return
     */
    BigDecimal getEtrFinishAmt(String entrustId);

    /**
     * 查询剩余需要冲销的金额
     *
     * @param map 委托代码
     * @return 剩余需要冲销的金额
     */
    BigDecimal getEtrSurplusAmt(Map<String, Object> map);

    /**
     * 根据债券信息和日期获取最新市价
     *
     * @param map 参数
     * @return 最新市价
     */
    BigDecimal findLatestMarketPrice(Map<String, Object> map);

    /**
     * 根据债券信息和日期获取最新市价(接口表)
     *
     * @param map 参数
     * @return 最新市价
     */
    BigDecimal findLatestInterfMarketPrice(Map<String, Object> map);

    /**
     * 根据股票代码获取最新收盘价
     *
     * @param finprodId
     * @return
     */
    BigDecimal getClosePrice(@Param("finprodId") String finprodId, @Param("dealDate") Date dealDate);

    /**
     * 根据股票代码获取最新收盘价
     *
     * @param finprodId
     * @return
     */
    BigDecimal getInterfClosePrice(@Param("finprodId") String finprodId, @Param("dealDate") Date dealDate);

    PtlSecValution selectByPrimaryKey(PtlSecValutionKey key);

    PtlSecValution selectPtlSecValutionData(PtlSecValutionKey paramDto);

    BigDecimal selectFirstUnitCpriceMax(String entrustId);

    BigDecimal selectEtrPledgeSurAmt(@Param("portfolioId") String portfolioId, @Param("finprodId") String finprodId, @Param("secId") String secId);

    /**
     * 查询交易所净额结算资产（债券）
     *
     * @param finprodId
     * @return
     */
    String selectFinProduct(@Param("finprodId") String finprodId);

    /**
     * 查询交易所净额结算资产（债券质押式正回购、债券质押式逆回购 ）
     *
     * @param finprodId
     * @return
     */
    String selectFinTradeProduct(@Param("finprodId") String finprodId);

    /**
     * 查询手工调整流水数据
     *
     * @param dto
     * @return
     */
    BigDecimal selectManualCashFlow(PtlRecePositionFlow dto);

    BigDecimal selectCashPosition(PtlRecePositionFlow positionFlowDto);

    Date selectCashPositionMaxCdate(PtlRecePositionFlow positionFlowDto);

    String selectMaxCdate(PtlRecePositionFlow positionFlowDto);

    void updatePosition(Map<String, Object> params);

    BigDecimal selectSecPosition(PtlRecePositionFlow positionFlowDto);

    Date selectSecPositionMaxCdate(PtlRecePositionFlow positionFlowDto);

    String selectSecMaxCdate(PtlRecePositionFlow positionFlowDto);

    void updateSecPosition(Map<String, Object> params);

    List<RepoInfo> findRepoInfo(String finprodId);

    List<PtlRecePositionFlow> selectAllPositionByTotle(String tradeId);

    List<PtlRecePositionFlow> selectAllCashPositionByTotle(String tradeId);

    /**
     * 查询最新计划类净值型资产净值
     *
     * @param params
     * @return
     */
    BigDecimal selectPlanNetValue(Map<String, Object> params);

    SecInfoModelDto getSecInfoModel(HashMap<String, Object> map);

    List<FinCashCalcRule> selectBondsIntResetList(Map<String, Object> param);

    List<FinCashPlanDto> getFinCashPlan(HashMap<String, Object> map);

    BigDecimal getCurSecAmt(Map<String, Object> param);

    /**
     * 查询交易对手的性质
     *
     * @param param
     * @return check结果
     */
    String getCounterType(Map<String, Object> param);

    BigDecimal selectCommandPositionFlow(PtlRecePositionFlow ptlRecePositionFlow);

    List<PtlRecePosition> genPtlPeceAvailableIntPosition(Map<String, Object> param);

    List<String> genLixiBranchList(Map<String, Object> lixiMap);
    /**
     * 获取还本不带息的存续期累计还本不带息金额
     *
     * @param param
     * @return
     */
    BigDecimal getRepayAmt(Map<String, Object> param);
    /**
     * 获取存续期单个利息的还本不带息的列表
     *
     * @param param
     * @return
     */

    List<PtlRecePosition> getRepayAmtList(Map<String, Object> param);
    /**
     * 更新应收款持仓的支付日
     *
     * @param param
     * @return
     */
   int updateRecePositionPayDate(Map<String, Object> param);
}
