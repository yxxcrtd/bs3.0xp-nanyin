/**
 *
 */
package com.joyintech.service.product.feepay.controller;

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
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.feepay.dto.FinProductFeePayDto;
import com.joyintech.service.product.feepay.dto.FinProductFeePayFeeDetailDto;
import com.joyintech.service.product.feepay.service.FinProductFeePayService;

/**
 * @author zhangliang
 *
 */
@RestController("backFinProductFeePayController_dtx")
@RequestMapping("/FinProductFeePay")
public class FinProductFeePayController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePayService finProductFeePayService;


    /**
     * 查询产品费用支付信息
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return 费用计划信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductFeePayDto> selectForPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProductFeePayService.selectForPage(page, length, param);
    }

    /**
     * 查询组合下产品关联的费用（不包含已有付费计划）
     * @param map 参数
     * @return 费用信息
     */
    @PostMapping("queryFeeInfoList")
    public List<SelectOptionDto> queryFeeInfoList(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryFeeInfoList(map);
    }

    /**
     * 查询组合下产品关联的费用(不包括付费计划)
     * @param map 参数
     * @return 费用信息
     */
    @PostMapping("queryFeeInfoListEx")
    public List<SelectOptionDto> queryFeeInfoListEx(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryFeeInfoListEx(map);
    }

    /**
     * 查询费用上次计提结束日
     * @param ptlId 组合代码
     * @param feeId 费用Id
     * @return 上次计提结束日
     */
    @GetMapping("queryLastPayDate")
    public String queryLastPayDate(String ptlId, String feeId, String payCycle,String org) {
        return finProductFeePayService.queryLastPayDate(ptlId, feeId, payCycle,org);
    }

    /**
     * 查询费用待支付费用
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @return 待支付费用
     */
    @GetMapping("queryUnpayPosition")
    public BigDecimal queryUnpayPosition(String ptlId, String feeType, String cdate) {
        return finProductFeePayService.queryUnpayPosition(ptlId, feeType, cdate);
    }

    /**
     * 查询结算服务费、账户维护费联动带出默认费用
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @return 待支付费用
     */
    @GetMapping("queryPayTradeAmt")
    public BigDecimal queryPayTradeAmt(String ptlId, String org, String cdate, String feeType) {
        return finProductFeePayService.queryPayTradeAmt(ptlId, org, cdate, feeType);
    }

    /**
     * 查询增值税待支付费用
     * @param ptlId 组合代码
     * @param cdate 查询日
     * @return 增值税待支付费用
     */
    @GetMapping("queryTaxUnpayPosition")
    public List<PtlFeePosition> queryTaxUnpayPosition(String ptlId, String cdate) {
        return finProductFeePayService.queryTaxUnpayPosition(ptlId, cdate);
    }

    /**
     * 查询是否有小于当前日期的待处理的计划
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    @GetMapping("checkUndoPlanExists")
    public int checkUndoPlanExists(String cashId, String cashNum) {
        return finProductFeePayService.checkUndoPlanExists(cashId, cashNum);
    }

    /** 根据现金流代码和到期日查询具体付费计划
     * @param map 参数
     * @return 付费计划
     */
    @PostMapping("queryPendingFeeInfoByCashId")
    public FinProductFeePayDto queryPendingFeeInfoByCashId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryPendingFeeInfoByCashId(map);
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
        return finProductFeePayService.queryFeeAmtByFeeType(feeType, cdate, finprodId, branch);
    }

    /**计提明细查看
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param finprodId 金融产品代码
     * @return 计提明细
     */
    @GetMapping("queryFeePositionDetail")
    public List<BokPositionPrdfee> queryFeePositionDetail(String feeType, String startDate, String enddate, String finprodId, String branch) {
        return finProductFeePayService.queryFeePositionDetail(feeType, startDate, enddate, finprodId, branch);
    }

    /** 根据交易编号查询增值税信息
     * @param tradeId 交易编号
     * @return 增值税信息
     */
    @GetMapping("queryTaxDetailByTradeId")
    public List<TrdTradeFeeDetail> queryTaxDetailByTradeId(String tradeId) {
        return finProductFeePayService.queryTaxDetailByTradeId(tradeId);
    }

    /** 根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    @GetMapping("queryFeeDetailByTradeId")
    public List<FinProductFeePayFeeDetailDto> queryFeeDetailByTradeId(String tradeId) {
        return finProductFeePayService.queryFeeDetailByTradeId(tradeId);
    }

    /** 根据交易编号查询组合交易
     * @param tradeId 交易编号
     * @return 组合交易
     */
    @GetMapping("queryPtlTradeByTradeId")
    public PtlTrade queryPtlTradeByTradeId(String tradeId) {
        return finProductFeePayService.queryPtlTradeByTradeId(tradeId);
    }

    /**
     *  根据交易编号查询费用明细信息
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    @GetMapping("queryTrdTradeFeeDetailByTradeId")
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(String tradeId) {
        return finProductFeePayService.queryTrdTradeFeeDetailByTradeId(tradeId);
    }

    /**
     *  根据交易编号查询费用明细信息
     * @param map 交易编号
     * @return 费用明细信息
     */
    @PostMapping("queryFeePositionByPtlId")
    public List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryFeePositionByPtlId(map);
    }


    /**
     *  根据交易编号查询费用明细信息
     * @param map 交易编号
     * @return 费用明细信息
     */
    @PostMapping("queryFeePositionByPtlLayeredId")
    public List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryFeePositionByPtlLayeredId(map);
    }

    @PostMapping("queryPayCycleByCashId")
    public String queryPayCycleByCashId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.queryPayCycleByCashId(map);
    }


    /**
     * 查询组合的产品及其募集币种
     *
     * @param ptlId 组合代码
     * @return 募集币种
     * @see
     * @since 1.0
     */
    @GetMapping("queryByPtlId")
    public FinProductFeePayDto queryByPtlId(String ptlId) {
        return finProductFeePayService.queryByPtlId(ptlId);
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
        return finProductFeePayService.queryUnCleanFee(ptlId, feeId, payDate, finprodId);
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
        return finProductFeePayService.queryAccrStrDate(finprodId, cdate);
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
        return finProductFeePayService.calResiPrincipal(ptlId, payDate);
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
        return finProductFeePayService.selectCashType2(finprodId, feeId);
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
        return finProductFeePayService.selectLayyerdCashType2(finprodId, feeId);
    }


    @PostMapping("checkpayAmt")
    public Integer checkpayAmt(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.checkpayAmt(map);
    }

    @PostMapping("checkpayAmtrule")
    public Integer checkpayAmtrule(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.checkpayAmtrule(map);
    }

    @PostMapping("selectFeeByTradeId")
    public FinProductFeePayDto selectFeeByTradeId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.selectFeeByTradeId(map);
    }

    @PostMapping("selectCashId")
    public String selectCashId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.selectCashId(map);
    }

    @PostMapping("selectFeeId")
    public MstFeeinfo selectFeeId(@RequestBody Map<String, Object> map) {
        return finProductFeePayService.selectFeeId(map);
    }

    /**
     * 查询产品分层产品
     * @param finprodId 母产品id
     * @return 子产品列表
     */
    @GetMapping("queryPrdLayerListByPid")
    public List<PrdLayerParam> queryPrdLayerListByPid(String finprodId) {
        return finProductFeePayService.queryPrdLayerListByPid(finprodId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertBussFlowData")
    public String insertBussFlowData(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        return finProductFeePayService.insertBussFlowData(trdCommonWflParam);
    }

    @PostMapping("doInsertRevoke")
    public String doInsertRevoke(@RequestBody TrdCommonWflParam trdCommonWflParam) {
        return finProductFeePayService.insertBussFlowData(trdCommonWflParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doCompleteRevoke")
    public String doCompleteRevoke(@RequestBody FinProductFeePayDto entity) {
        return finProductFeePayService.doCompleteRevoke(entity);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdCommonParam trdCommonParam) {
        return finProductFeePayService.doComplete(trdCommonParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doCompleteInsert")
    public String doCompleteInsert(@RequestBody TrdCommonParam trdCommonParam) {
        return finProductFeePayService.doCompleteInsert(trdCommonParam);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertBatchData")
    public String insertBatchData(@RequestBody FinProductExpireBaseTrdDto expireBaseTrdDto) {
        return finProductFeePayService.insertBatchData(expireBaseTrdDto);
    }
    
    /**
     * 根据产品Id和费用Id查询费用信息 yzg 20191211
     * @param finprodId
     * @param feeId
     * @return
     */
    @GetMapping("selectMstFeeInfo")
    public MstFeeinfo selectMstFeeInfo(String finprodId, String feeId,String enddate) {
    	return finProductFeePayService.selectMstFeeInfo(finprodId,feeId,enddate);
    }
    /**
     * 	查询组合起息日
     * @param ptlId 组合代码
     * @return 
     */
    @GetMapping("queryVdate")
    public String queryVDate(String ptlId) {
        return finProductFeePayService.queryVdate(ptlId);
    }
}
