/*
 * 文件名：MoneyTaAutoCalProcessorProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.moneytaautocal.dao.MoneyTaAutoCalCommonMapper;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.BokBookDetilDto;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.MoneyTaAutoCalResult;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.TradeInofoGenerateResult;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.util.DateUtil;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalProcessorProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalProcessorService {

    @Autowired
    private MoneyTaAutoCalCommonMapper moneyTaAutoCalReaderMapper;

    @Autowired
    private AccountingCommonService accountingCommonService;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public TradeInofoGenerateResult execute(Map<String, Object> param) {

        // 获取reader返回值
        MoneyTaAutoCalResult acctEntity = (MoneyTaAutoCalResult)param.get(GlobalConstant.READER_RESULT);

        // 定义此process的返回变量
        TradeInofoGenerateResult rtnResult = new TradeInofoGenerateResult();
        // 金融产品交易
        List<TrdProductDeal> listTrdProductDeal = Lists.newArrayList();
        // 估值核算交易扩展表
        List<TrdProductDealAdd> listTrdProductDealExt = Lists.newArrayList();
        // 入账信息表
        List<BokDealAccounting> listBokDealAccounting = Lists.newArrayList();
        // 估值核算交易扩展表
        List<BokDealAccountingDetail> listBokDealAccountingDetail = Lists.newArrayList();

        // 二、根据账套生成交易
        // LOOP DTO ->变量.账套明细
        for (BokBookDetilDto item : acctEntity.getListBokBookDetilDto()) {
            // 1.是否存在已交易生成
            Map<String, Object> map = Maps.newHashMap();
            map.put("busiId", item.getBusiId());
            map.put("busiDetailType", item.getBusiDetailType());
            map.put("branch", item.getBranch());
            map.put("happenDate", acctEntity.getHappenDate());
            map.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
//            map.put("booksetId", acctEntity.getBooksetId());
            map.put("booksetId", item.getBooksetId());
            
            map.put("ps", new String[] {DictCodeList.交易方向_产品红利转投.getValue()});
            int amounts = moneyTaAutoCalReaderMapper.countTrade(map);

            // if（数量>0 ） return;
            if (amounts > 0) {
                break;
            }

            // 2.应付利润科目查询
            // 调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
            SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
            searchDto.setHappenDate(DateUtil.addDay(acctEntity.getHappenDate(), -1));
            searchDto.setBookDate(acctEntity.getBookDate());
            searchDto.setBokDetailId(item.getBokDetailId());
//            searchDto.setBooksetId(acctEntity.getBooksetId());
            searchDto.setBooksetId(item.getBooksetId());
            searchDto.setBaseSubjectNatureList(Arrays.asList(new String[] {DictCodeBaseSubjectNatrue.应付利润.getValue()}));
            List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                searchDto, DictCodeList.借贷方向_贷.getValue());

            // 变量.应付利润余额
            BigDecimal balance = BigDecimal.ZERO;

            if (CollectionUtils.isNotEmpty(listSubjectBalance)) {
                // 变量.应付利润余额 = 上述返回的科目余额
                balance = listSubjectBalance.get(0).getBalance();
            }

            // 3.生成交易
            // if(变量.应付利润余额!=0)
            if (BigDecimal.ZERO.compareTo(balance) != 0) {
                Map<String, Object> tradeParam = Maps.newHashMap();
                tradeParam.put("balance", balance);
                tradeParam.put("busiId", item.getBusiId());
                tradeParam.put("busiDetailType", item.getBusiDetailType());
                tradeParam.put("branch", item.getBranch());
//                tradeParam.put("standardCcy", acctEntity.getStandardCcy());
                tradeParam.put("standardCcy", item.getStandardCcy());
                tradeParam.put("happenDate", acctEntity.getHappenDate());
                tradeParam.put("ps", DictCodeList.交易方向_产品红利转投.getValue());
                tradeParam.put("busiType", DictCodeList.业务类型_货币型TA交易.getValue());

//                tradeParam.put("booksetId", acctEntity.getBooksetId());
                tradeParam.put("booksetId", item.getBooksetId());
                tradeParam.put("bokDetailId", item.getBokDetailId());
                Map<String, Object> tradeInfo = this.generatorTradeInfo(tradeParam);

                // 金融产品交易
                listTrdProductDeal.add((TrdProductDeal)tradeInfo.get("TRD_PRODUCT_DEAL"));
                // 估值核算交易扩展表
                listTrdProductDealExt.add((TrdProductDealAdd)tradeInfo.get("TRD_PRODUCT_DEAL_EXT"));
                // 入账信息表
                listBokDealAccounting.add((BokDealAccounting)tradeInfo.get("BOK_DEAL_ACCOUNTING"));
                // 入账账务明细表
                listBokDealAccountingDetail.add((BokDealAccountingDetail)tradeInfo.get("BOK_DEAL_ACCOUNTING_DETAIL"));
            }
        }

        // 金融产品交易
        rtnResult.setListTrdProductDeal(listTrdProductDeal);
        // 估值核算交易扩展表
        rtnResult.setListTrdProductDealExt(listTrdProductDealExt);
        // 入账信息表
        rtnResult.setListBokDealAccounting(listBokDealAccounting);
        // 估值核算交易扩展表
        rtnResult.setListBokDealAccountingDetail(listBokDealAccountingDetail);
        return rtnResult;
    }

    /**
     * 生成的交易信息列表
     * 
     * @param tradeParam
     * @param rtnResult
     */
    private Map<String, Object> generatorTradeInfo(Map<String, Object> tradeParam) {
        Map<String, Object> map = Maps.newHashMap();
        BigDecimal balance = (BigDecimal)tradeParam.get("balance");
        String busiId = (String)tradeParam.get("busiId");
        String busiDetailType = (String)tradeParam.get("busiDetailType");
        Long branch = (Long)tradeParam.get("branch");
        String standardCcy = (String)tradeParam.get("standardCcy");
        Date happenDate = (Date)tradeParam.get("happenDate");
        String ps = (String)tradeParam.get("ps");
        String busiType = (String)tradeParam.get("busiType");

        String booksetId = (String)tradeParam.get("booksetId");
        String bokDetailId = (String)tradeParam.get("bokDetailId");

        String tradeId = interfCommonService.getTradeid(ps, happenDate, "A");

        // 金融产品交易
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        // 交易编号 自动生成 交易编号共通
        trdProductDeal.setTradeId(tradeId);
        // 业务类型 参数.业务类型
        trdProductDeal.setBusiType(busiType);
        // 金融产品代码 参数.金融产品代码
        trdProductDeal.setFinprodId(busiId);
        // 金融产品类型 参数.金融产品类型
        trdProductDeal.setFinprodType(busiDetailType);
        // 分支序号 参数.分支序号
        trdProductDeal.setBranch(branch);
        // 交易日 参数.发生日期
        trdProductDeal.setTradeDate(happenDate);
        // 交割日 参数.发生日期
        trdProductDeal.setSettleDate(happenDate);
        // 交易币种 参数.币种
        trdProductDeal.setCcy(standardCcy);
        // 净价总额 0
        trdProductDeal.setCpriceAmt(BigDecimal.ZERO);
        // 交易份额 金额
        trdProductDeal.setShareAmt(balance);
        // 交易本金 金额
        trdProductDeal.setPrinAmt(balance);
        // 利息总额 0
        trdProductDeal.setIntAmt(BigDecimal.ZERO);
        // 全价总额 0
        trdProductDeal.setFpriceAmt(BigDecimal.ZERO);
        // 随交易支付费用 0
        trdProductDeal.setFeeAmt(BigDecimal.ZERO);
        // 交易金额 0
        trdProductDeal.setTradeAmt(BigDecimal.ZERO);
        // 未随交易支付费用 0
        trdProductDeal.setFeeAmtUnpay(BigDecimal.ZERO);
        // 总交易费用 0
        trdProductDeal.setFeeAmt(BigDecimal.ZERO);
        // 交易方向 参数.交易方向
        trdProductDeal.setPs(ps);
        // 交易状态 正常
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());;
        // 交易对手类型 "外部" 数据字典
        // trdProductDeal.setCounterType(DictCodeListExt1.交易对手类型_外部交易对手.getValue());

        // 估值核算交易扩展表
        TrdProductDealAdd trdProductDealExt = new TrdProductDealAdd();
        // 交易编号 金融产品交易已生成的交易编号
        trdProductDealExt.setTradeId(tradeId);
        // 理财产品代码 参数.理财产品代码 
        // trdProductDealExt.setProdId(booksetId);
        // 是否有结算指令 否
        // trdProductDealExt.setHasOrder(DictCodeList.是否_否.getValue());
        // 生成方式 "批处理生成"
        trdProductDealExt.setGenType(DictCodeListExt1.生成方式_批处理生成.getValue());

        // 入账信息表
        BokDealAccounting bokDealAccounting = new BokDealAccounting();
        // 账套代码 DTO.账套代码
        bokDealAccounting.setBooksetId(booksetId);
        // 业务编号 金融产品交易已生成的交易编号
        bokDealAccounting.setBusiNo(tradeId);
        // 业务表 "金融产品表"
        bokDealAccounting.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 录入日 参数.发生日期
        bokDealAccounting.setBooksetDate(happenDate);
        // 入账日 参数.发生日期
        bokDealAccounting.setBookDate(happenDate);
        // 实际发生日 参数.发生日期
        bokDealAccounting.setHappenDate(happenDate);
        // 是否已入账 否
        bokDealAccounting.setIsAccounted(DictCodeList.是否_否.getValue());

        // 入账账务明细表
        BokDealAccountingDetail bokDealAccountingDetail = new BokDealAccountingDetail();
        // 账套代码 DTO.账套代码
        bokDealAccountingDetail.setBooksetId(booksetId);
        // 业务编号 金融产品交易已生成的交易编号
        bokDealAccountingDetail.setBusiNo(tradeId);
        // 业务表 "金融产品表"
        bokDealAccountingDetail.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务类型 参数.业务类型
        bokDealAccountingDetail.setBusiType(busiType);
        // 账务明细代码 参数.账务明细代码
        bokDealAccountingDetail.setBokDetailId(bokDetailId);

        // 金融产品交易
        map.put("TRD_PRODUCT_DEAL", trdProductDeal);
        // 估值核算交易扩展表
        map.put("TRD_PRODUCT_DEAL_EXT", trdProductDealExt);
        // 入账信息表
        map.put("BOK_DEAL_ACCOUNTING", bokDealAccounting);
        // 入账账务明细表
        map.put("BOK_DEAL_ACCOUNTING_DETAIL", bokDealAccountingDetail);

        return map;
    }

}
