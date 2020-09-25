/**
 * 
 */
package com.joyintech.service.product.feepay.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokPositionPrdfee;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.common.base.entity.PtlFeePosition;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.feepay.dto.FinProductFeePayDto;
import com.joyintech.service.product.feepay.dto.FinProductFeePayFeeDetailDto;

/**
 * @author zhangliang
 */
@Repository
public interface FinProductFeePayMapper {

    /**
     * 查询待处理的费用计划信息
     * 
     * @param map 参数
     * @return 费用计划信息
     */
    List<FinProductFeePayDto> selectForPage(Map<String, Object> map);

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
    String queryLastPayDate1(Map<String, Object> map);
    
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
     * 查询增值税待支付费用
     * 
     * @param map 参数
     * @return 增值税待支付费用
     */
    List<PtlFeePosition> queryTaxUnpayPosition(Map<String, Object> map);

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
    FinProductFeePayDto queryPendingFeeInfoByCashId(Map<String, Object> map);

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

    /**计提明细查看
     * @param map 费用类型
     * @return 计提明细
     */
    List<BokPositionPrdfee> queryFeePositionDetail(Map<String, Object> map);
    
    /** 根据交易编号查询增值税信息
     * @param tradeId 交易编号
     * @return 增值税信息
     */
    List<TrdTradeFeeDetail> queryTaxDetailByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    List<FinProductFeePayFeeDetailDto> queryFeeDetailByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据交易编号查询组合交易
     * @param tradeId 交易编号
     * @return 组合交易
     */
    PtlTrade queryPtlTradeByTradeId(@Param("tradeId") String tradeId);
    
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
    List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlId(Map<String, Object> map);
    
    /** 根据组合代码查询费用明细信息
     * @param map 参数
     * @return 费用明细信息
     */
    List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(Map<String, Object> map);
    
    /** 根据组合代码查询费用明细信息
     * @param map 参数
     * @return 费用明细信息
     */
    String queryPayCycleByCashId(Map<String, Object> map);
    
    
    /**
     * 查询组合的产品及其募集币种
     * 
     * @param ptlId 组合代码
     * @return 募集币种
     * @see
     * @since 1.0
     */
    FinProductFeePayDto queryByPtlId(Map<String, Object> map);
    
    
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
     * 查询质押债券的结算手续费
     * @param map
     * @return
     */
    BigDecimal querypledgePay(Map<String, Object> map);

    /**
     * 通过交易编号查询费用信息
     * @param map
     * @return
     */
    FinProductFeePayDto selectFeeByTradeId(Map<String,Object> map);
    
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
     * 根据产品Id和费用Id查询费用信息
     * @param finprodId
     * @param feeId
     * @return
     */
    MstFeeinfo selectMstFeeInfo(@Param("finprodId") String finprodId, @Param("feeId") String feeId,@Param("enddate") String enddate);
}
