package com.joyintech.service.portfolio.feepay.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokPositionPrdfee;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.common.base.entity.PtlFeePosition;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayFeeDetailDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayPositionDetailDto;

/**
 * @author malinlin
 */
@Repository
public interface InvestFeePayMapper {

    /**
     * 查询待处理的费用计划信息
     * 
     * @param map 参数
     * @return 费用计划信息
     */
    List<InvestFeePayDto> selectForPage(Map<String, Object> map);
    
    /**
     * 证券账户账号联想下拉框
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    List<SuggestDto> getSecAcctId(@Param("secAcctId")String secAcctId);

    /**
     * 查询组合下产品关联的费用（不包含已有付费计划）
     * 
     * @param map 参数
     * @return 费用信息
     */
    List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);
    
    /**
     * 查询组合下产品关联的费用
     * 
     * @param map 参数
     * @return 费用信息
     */
    List<SelectOptionDto> queryFeeInfoListEx(Map<String, Object> map);

    /**
     * 查询不规则费用上次计提结束日
     * 
     * @param map 参数
     * @return 上次计提结束日
     */
    String queryLastPayDate(Map<String, Object> map);

    /**
     * 查询不规则费用待支付费用
     * 
     * @param map 参数
     * @return 待支付费用
     */
    BigDecimal queryUnpayPosition(Map<String, Object> map);
    /**
     * 查询结算服务费、账户维护费应付费用
     * 
     * @param map 参数
     * @return 待支付费用
     */
    BigDecimal queryPayTradeAmt(Map<String, Object> map);
    /**
     * 查询结算服务费、账户维护费已付费用
     * 
     * @param map 参数
     * @return 待支付费用
     */
    BigDecimal queryPayed(Map<String, Object> map);

    /**
     * 查询是否有小于当前日期的待处理的计划
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    int checkUndoPlanExists(@Param("cashId") String cashId, @Param("cashNum") String cashNum);

    /**
     * 根据现金流代码和到期日查询具体付费计划
     * 
     * @param map 参数
     * @return 付费计划
     */
    InvestFeePayDto queryPendingFeeInfoByCashId(Map<String, Object> map);

    /**
     * 查询待支付费用
     * 
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param finprodId 金融产品代码
     * @param branch 分支序号
     * @return 待支付费用
     */
    BigDecimal queryFeeAmtByFeeType(@Param("feeType") String feeType, @Param("cdate") String cdate,
                                    @Param("finprodId") String finprodId,
                                    @Param("branch") String branch);

    /**
     * 组合费用明细列表
     * @param map 费用类型
     * @return 组合费用明细
     */
    List<InvestFeePayPositionDetailDto> queryPayFeeInfoList(Map<String, Object> map);
    
    /**
     * 费用明细查看
     * @param map 费用类型
     * @return 费用明细
     */
    List<InvestFeePayPositionDetailDto> queryFeePositionDetail(Map<String, Object> map);
    
    /** 根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    List<InvestFeePayFeeDetailDto> queryFeeDetailByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据交易编号查询组合交易
     * @param tradeId 交易编号
     * @return 组合交易
     */
    List<PtlTrade> queryPtlTradeByTradeId(@Param("tradeId") String tradeId);
    
    /**
     *  根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据组合代码查询费用明细信息
     * @param map 参数
     * @return 费用明细信息
     */
    List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId(Map<String, Object> map);
    
    /** 根据组合代码查询费用明细信息
     * @param map 参数
     * @return 费用明细信息
     */
    List<InvestFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(Map<String, Object> map);
    
    /** 根据组合代码查询费用明细信息
     * @param map 参数
     * @return 费用明细信息
     */
    String queryPayCycleByCashId(Map<String, Object> map);
    
    /** 查询累计未结清费用
     * @param map 参数
     * @return 累计未结清费用
     */
    BigDecimal queryUnCleanFee(Map<String, Object> map);
    
    /**
     * 查询产品剩余现金
     * @param map 参数
     * @return 剩余现金
     */
    BigDecimal calResiPrincipal(Map<String, Object> map);
    
    /**
     * 根据产品代码费用Id查现金流二级类型
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    String selectCashType2(@Param("finprodId") String finprodId, @Param("feeId") String feeId);
    
    
    /**
     * 根据产品代码费用Id查现金流二级类型(分层)
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    String selectLayyerdCashType2(@Param("finprodId") String finprodId, @Param("feeId") String feeId);
    
    
    /**
     *查询产品到期时是否有未支付的计提费用   add by dingjunfeng
     * @param map
     * @return
     */
    Integer checkpayAmt(Map<String, Object> map);
    /**
     *查询产品到期时是否有未支付的不规则费用   add by dingjunfeng
     * @param map
     * @return
     */
    Integer checkpayAmtrule(Map<String, Object> ma);

    /**
     * 通过交易编号查询费用信息
     * @param map
     * @return
     */
    InvestFeePayDto selectFeeByTradeId(Map<String,Object> map);
    
    /**
     * 根据产品代码费用Id查现金流ID
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    String selectCashId(Map<String,Object> map);
    /**
     * 根据产品代码，计提日期获取 计提开始日
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    Date queryAccrStrDate(Map<String,Object> map);
    /**
     * 查询产品分层产品
     * @param finprodId 母产品id
     * @return 子产品列表
     */
    List<PrdLayerParam> queryPrdLayerListByPid(@Param("finprodId") String finprodId);
    /**
     * 查询结算服务费、账户维护费应付费用（从头寸中获取）
     * 
     * @param map 参数
     * @return 待支付费用
     */
    BigDecimal queryPayFeeAmtByPosition(Map<String, Object> map);
    
    /**
     * 根据交易编号查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

    /**
     * 根据交易编号查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    List<StlPayOrders> queryStlPayOrdersByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询交易信息
     * @param tradeId 汇总交易编号
     * @return 交易信息
     */
    List<TrdProductDeal> queryTrdProductDealByTotleTradeId(@Param("tradeId") String tradeId);
    
    /**
     * 查询证券账户登记托管机构
     * @param secAcctId 证券账户
     * @return 登记托管机构
     * @see
     * @since 1.0
     */
    String querySecAcctOrg(@Param("secAcctId") String secAcctId);
    
    /**
     * 根据组合id查询产品信息
     * @param ptlId 组合id
     * @return 产品信息
     * @see
     * @since 1.0
     */
    InvestFeePayDto queryByPtlId(Map<String, Object> map);
}
