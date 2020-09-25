package com.joyintech.service.portfolio.feepay.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.product.dto.FinProductExpireBaseTrdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.BokPositionPrdfee;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.PrdLayerParam;
import com.joyintech.tams.common.base.entity.PtlFeePosition;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayFeeDetailDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayPositionDetailDto;
import com.joyintech.service.portfolio.feepay.service.InvestFeePayService;

/**
 * @author malinlin
 *
 */
@RestController("backInvestFeePayController_dtx")
@RequestMapping("/InvestFeePay")
public class InvestFeePayController {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePayService investFeePayService;


    /**
     * 查询产品费用支付信息
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return 费用计划信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<InvestFeePayDto> selectForPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return investFeePayService.selectForPage(page, length, param);
    }
    
    /**
     * 证券账户账号联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecAcctId")
    public List<SuggestDto> getSecAcctId(String secAcctId)
        throws Exception {
        List<SuggestDto> temp = investFeePayService.getSecAcctId(secAcctId);
        return temp;
    }

    /**
     * 查询证券账户下产品关联的费用（不包含已有付费计划）
     * @param map 参数
     * @return 费用信息
     */
    @PostMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryFeeInfoList(map);
    }

    /**
     * 查询证券账户下产品关联的费用(不包括付费计划)
     * @param map 参数
     * @return 费用信息
     */
    @PostMapping("queryFeeInfoListEx")
    public List<SelectOptionDto> queryFeeInfoListEx(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryFeeInfoListEx(map);
    }

    /**
     * 查询费用上次计提结束日
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @return 上次计提结束日
     */
    @GetMapping("queryLastPayDate")
    public String queryLastPayDate(String secAcctId, String feeType) {
        return investFeePayService.queryLastPayDate(secAcctId, feeType);
    }

    /**
     * 查询费用待支付费用
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param regTrustOrg 登记托管机构
     * @return 待支付费用
     */
    @GetMapping("queryUnpayPosition")
    public BigDecimal queryUnpayPosition(String secAcctId, String feeType, String cdate, String regTrustOrg) {
        return investFeePayService.queryUnpayPosition(secAcctId, feeType, cdate, regTrustOrg);
    }

    /**
     * 查询结算服务费、交易手续费、佣金联动带出默认费用
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @return 待支付费用
     */
    @GetMapping("queryPayTradeAmt")
    public BigDecimal queryPayTradeAmt(String secAcctId, String org, String cdate, String feeType) {
        return investFeePayService.queryPayTradeAmt(secAcctId, org, cdate, feeType);
    }

    /**
     * 查询是否有小于当前日期的待处理的计划
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    @GetMapping("checkUndoPlanExists")
    public int checkUndoPlanExists(String cashId, String cashNum) {
        return investFeePayService.checkUndoPlanExists(cashId, cashNum);
    }

    /** 根据现金流代码和到期日查询具体付费计划
     * @param map 参数
     * @return 付费计划
     */
    @PostMapping("queryPendingFeeInfoByCashId")
    public InvestFeePayDto queryPendingFeeInfoByCashId(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryPendingFeeInfoByCashId(map);
    }

    /**
     * 查询待支付费用
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param finprodId 金融产品代码
     * @param branch 分支序号
     * @return 待支付费用
     */
    @GetMapping("queryFeeAmtByFeeType")
    public BigDecimal queryFeeAmtByFeeType(String feeType, String cdate, String finprodId, String branch) {
        return investFeePayService.queryFeeAmtByFeeType(feeType, cdate, finprodId, branch);
    }
    
    /**
     * 组合费用明细列表
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param secAcctId 证券账户代码
     * @param ptlId 组合代码
     * @return 费用明细
     */
    @GetMapping("queryPayFeeInfoList")
    public List<InvestFeePayPositionDetailDto> queryPayFeeInfoList(String secAcctId, String org, 
                                                                   String cdate, String feeType) {
        return investFeePayService.queryPayFeeInfoList(secAcctId, org, cdate, feeType);
    }

    /**
     * 费用明细查看
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param secAcctId 证券账户代码
     * @param ptlId 组合代码
     * @return 费用明细
     */
    @GetMapping("queryFeePositionDetail")
    public List<InvestFeePayPositionDetailDto> queryFeePositionDetail(String feeType, String startDate, String enddate, 
                                                                      String secAcctId, String ptlId) {
        return investFeePayService.queryFeePositionDetail(feeType, startDate, enddate, secAcctId, ptlId);
    }

    /** 根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    @GetMapping("queryFeeDetailByTradeId")
    public List<InvestFeePayFeeDetailDto> queryFeeDetailByTradeId(String tradeId) {
        return investFeePayService.queryFeeDetailByTradeId(tradeId);
    }

    /**
     *  根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    @GetMapping("queryTrdTradeFeeDetailByTradeId")
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(String tradeId) {
        return investFeePayService.queryTrdTradeFeeDetailByTradeId(tradeId);
    }

    /**
     *  根据交易编号查询费用明细信息
     * @param map 交易编号
     * @return 费用明细信息
     */
    @PostMapping("queryFeePositionByPtlId")
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryFeePositionByPtlId(map);
    }


    /**
     *  根据交易编号查询费用明细信息
     * @param map 交易编号
     * @return 费用明细信息
     */
    @PostMapping("queryFeePositionByPtlLayeredId")
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryFeePositionByPtlLayeredId(map);
    }

    @PostMapping("queryPayCycleByCashId")
    public String queryPayCycleByCashId(@RequestBody Map<String, Object> map) {
        return investFeePayService.queryPayCycleByCashId(map);
    }

    /**
     * 查询累计未结清费用
     *
     * @param ptlId 组合代码
     * @param feeId 费用代码
     * @param payDate 支付日期
     * @param isClean 是否结清
     * @return 累计未结清费用
     */
    @GetMapping("queryUnCleanFee")
    public BigDecimal queryUnCleanFee(String ptlId, String feeId, String payDate, String finprodId) {
        return investFeePayService.queryUnCleanFee(ptlId, feeId, payDate, finprodId);
    }

    /**
     * 查询 计提开始日
     *
     * @param finprodId 金融产品代码
     * @param cdate 计算日
     * @return 累计未结清费用
     */
    @GetMapping("queryAccrStrDate")
    public Date queryAccrStrDate(String finprodId, String cdate) {
        return investFeePayService.queryAccrStrDate(finprodId, cdate);
    }

    /**
     * 查询产品剩余现金
     *
     * @param ptlId 组合代码
     * @param payDate 支付日期
     * @return 剩余现金
     */
    @GetMapping("calResiPrincipal")
    public BigDecimal calResiPrincipal(String ptlId, String payDate) {
        return investFeePayService.calResiPrincipal(ptlId, payDate);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型
     *
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    @GetMapping("selectCashType2")
    public String selectCashType2(String finprodId, String feeId) {
        return investFeePayService.selectCashType2(finprodId, feeId);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型(分层)
     *
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    @GetMapping("selectLayyerdCashType2")
    public String selectLayyerdCashType2(String finprodId, String feeId) {
        return investFeePayService.selectLayyerdCashType2(finprodId, feeId);
    }


    @PostMapping("checkpayAmt")
    public Integer checkpayAmt(@RequestBody Map<String, Object> map) {
        return investFeePayService.checkpayAmt(map);
    }

    @PostMapping("checkpayAmtrule")
    public Integer checkpayAmtrule(@RequestBody Map<String, Object> map) {
        return investFeePayService.checkpayAmtrule(map);
    }

    @PostMapping("selectFeeByTradeId")
    public InvestFeePayDto selectFeeByTradeId(@RequestBody Map<String, Object> map) {
        return investFeePayService.selectFeeByTradeId(map);
    }

    @PostMapping("selectCashId")
    public String selectCashId(@RequestBody Map<String, Object> map) {
        return investFeePayService.selectCashId(map);
    }

    @PostMapping("selectFeeId")
    public MstFeeinfo selectFeeId(@RequestBody Map<String, Object> map) {
        return investFeePayService.selectFeeId(map);
    }

    /**
     * 查询产品分层产品
     * @param finprodId 母产品id
     * @return 子产品列表
     */
    @GetMapping("queryPrdLayerListByPid")
    public List<PrdLayerParam> queryPrdLayerListByPid(String finprodId) {
        return investFeePayService.queryPrdLayerListByPid(finprodId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertBussFlowData")
    public String insertBussFlowData(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        return investFeePayService.insertBussFlowData(trdCommonWflParam);
    }

    @PostMapping("doInsertRevoke")
    public String doInsertRevoke(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        return investFeePayService.insertBussFlowData(trdCommonWflParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doCompleteRevoke")
    public String doCompleteRevoke(@RequestBody InvestFeePayDto entity) {
        return investFeePayService.doCompleteRevoke(entity);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdCommonParam trdCommonParam) {
        return investFeePayService.doComplete(trdCommonParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doCompleteInsert")
    public String doCompleteInsert(@RequestBody TrdCommonParam trdCommonParam) {
        return investFeePayService.doCompleteInsert(trdCommonParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertBatchData")
    public String insertBatchData(@RequestBody FinProductExpireBaseTrdDto expireBaseTrdDto) {
        return investFeePayService.insertBatchData(expireBaseTrdDto);
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    @GetMapping("isInFlowDataExisted")
    public Boolean isInFlowDataExisted(String tradeId) {
        return investFeePayService.isInFlowDataExisted(tradeId);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    @GetMapping("isAlreadyRevoked")
    public Boolean isAlreadyRevoked(String tradeId) {
        return investFeePayService.isAlreadyRevoked(tradeId);
    }
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    @GetMapping("queryStlPayOrdersByTradeId")
    public List<StlPayOrders> queryStlPayOrdersByTradeId(String tradeId){
        return investFeePayService.queryStlPayOrdersByTradeId(tradeId);
    }
    
    /**
     * 查询证券账户登记托管机构
     * @param secAcctId 证券账户
     * @return 登记托管机构
     * @see
     * @since 1.0
     */
    @GetMapping("querySecAcctOrg")
    public String querySecAcctOrg(String secAcctId) {
        return investFeePayService.querySecAcctOrg(secAcctId);
    }
    
    /**
     * 根据汇总交易编号查询金融产品交易
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryTrdProductDealByTotleTradeId")
    public List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId) {
        return investFeePayService.queryTrdProductDealByTotleTradeId(tradeId);
    }
    
    /**
     * 根据组合id查询产品信息
     * @param ptlId 组合id
     * @return 产品信息
     * @see
     * @since 1.0
     */
    @GetMapping("queryByPtlId")
    public InvestFeePayDto queryByPtlId(String ptlId) {
        return investFeePayService.queryByPtlId(ptlId);
    }
}
