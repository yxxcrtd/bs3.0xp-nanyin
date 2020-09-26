/*
 * 文件名：OtcOptionBookDetailProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.otcoptionbookdetail.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年12月31日
 * @see OtcOptionBookDetailProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OtcOptionBookDetailProcessorService {
    
    /**
     * 标的类共通方法Service
     */
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;
    
    /**
     * 计算类共通方法service
     */
    @Autowired
    private CalculationCommonService calculationCommonService;
    
    /**
     * 财务类共通方法service
     */
    @Autowired
    private AccountingCommonService accountingCommonService;
    
    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;
    
    /**
     * process 处理
     * <一句话功能简述>
     * <功能详细描述>
     * @param param1
     * @return
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param1.get(GlobalConstant.READER_RESULT);
        // 若reader传递的参数或交易明细为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListFinProductDealInfoComDTO() == null || acctEntity.getListFinProductDealInfoComDTO().isEmpty()) {
            return null;
        }
        // 定义返回值
        BookGenerateResult returnResult = new BookGenerateResult();
        
        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String bokDetailIdNow = acctEntity.getBokDetailId();// 账务明细代码
        TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 会计场景
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();
        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 金融产品交易信息list
        Long taskPri = listFinProductDealInfoComDTONow.get(0).getTaskPri();// 任务优先级（信息列表的任务优先级都是一致的，故取第一个）
        
        //定义变量
        // 定义变量，获取科目余额相关数据
        BigDecimal varCostSubjectAmt = BigDecimal.ZERO;// 变量.成本科目余额
        BigDecimal varCostSubjectNum = BigDecimal.ZERO;// 变量.成本科目数量
        BigDecimal varGyjzxbdSubjectAmt = BigDecimal.ZERO;// 变量.公允价值变动科目余额
        
        //交易处理
        for(FinProductDealInfoComDTO dealNow : listFinProductDealInfoComDTONow) {
            //调用共通设计->【详细设计-标的类共通方法设计.xlsx】->交易信息查询SHEET
            List<TradeInfoDto> listTradeInfoDto = finProductCommonBatchService.getTradeInfoDtoList(dealNow.getFinprodId(), dealNow.getFinprodType(), dealNow.getBranch());
            if(StringUtil.isEmptyList(listTradeInfoDto)) {
                continue;
            }
            //定义 变量.标的信息 = 上述返回值.get(0);
            TradeInfoDto tradeInfoDto = listTradeInfoDto.get(0);
            
            //调用计算类方法共通【查询汇率】;
            //BigDecimal eExRate = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), acctEntity.getStandardCcy(), happenDateNow);
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
            BigDecimal eExRate = BigDecimal.ONE;
            if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
                List<CurrencyMarketDto> currencyMarketList = finProductCommonBatchService.getCurrencyMarket(bookDateNow, booksetIdNow,
                        acctEntity.getStandardCcy(), dealNow.getCcy());
                if (!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null) {
                    eExRate = currencyMarketList.get(0).getClosePrice();
                }
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/

            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();
            //ad.记账场景树节点 = 会计场景;
            ad.setTreeNode(bookSetTreeNodNow);
            
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();
            
            //accountSceneMap设置如下：
            accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
            accountSceneMap.put(BookConstant.TYPE_1, tradeInfoDto.getType1());// 分类1 - 变量.交易信息.分类1
            
            if(StringUtil.equals(tradeInfoDto.getExcPs(),DictCodeList.交易方向_买入.getValue())) {
                if(StringUtil.equals(dealNow.getPs(), DictCodeList.交易方向_买入.getValue())) {
                    /** modify by yanya on 20200120 tableId修正 start**/
                    //ad.setTableId("期权交易_开仓买入");
                    ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_开仓买入.getValue());
                    /** modify by yanya on 20200120 tableId修正 end**/
                    
                    accountSceneMap.put(BookConstant.BOOK_REMARK, DictCodeListTableId.会计场景代码_期权交易_开仓买入.getCodeName() + "登账");// 备注
                    
                    //accountAmountMap设置
                    //核算数量<-交易明细.交易份额
                    accountAmountMap.put(BookConstant.ACCQUA, dealNow.getShareAmt());
                    //交易金额<-交易明细.交易金额
                    accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
                    //成本金额<-交易明细.全价总额
                    accountAmountMap.put(BookConstant.COST, dealNow.getFpriceAmt());
                    //交易费用<-交易明细.全价总额 - 交易明细.交易金额
                    accountAmountMap.put(BookConstant.QQFEE, ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getTradeAmt()));
                    
                    //定义 本币金额元素Map
                    Map<String, BigDecimal> bCyyAccountAmountMap = new HashMap<String, BigDecimal>();
                    for (String keyNow : accountAmountMap.keySet()) {
                        accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
                        //本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);
                        bCyyAccountAmountMap.put(keyNow + BookConstant.BCCYAMT,
                                ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(keyNow), eExRate), NumberEnum.MONEY));
                    }
                    accountAmountMap.putAll(bCyyAccountAmountMap);
                    
                    //交易金额_本位币<-成本金额_本位币 - 期权费_本位币
                    accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT,
                        ArithmeticUtil.subtract(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT),
                                accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT)));
                    
                    accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                } 
                else if (StringUtil.equals(dealNow.getPs(), DictCodeListExt3.交易方向_提前行权.getValue())
                        || StringUtil.equals(dealNow.getPs(), DictCodeListExt3.交易方向_到期行权.getValue())
                        || StringUtil.equals(dealNow.getPs(), DictCodeListExt1.交易方向_放弃行权.getValue())){
                    /** modify by yanya on 20200121 tableId修正 start**/
                    //ad.setTableId("期权交易_买入方期权到期");
                    ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_买入方期权到期.getValue());
                    /** modify by yanya on 20200121 tableId修正 end**/
                    accountSceneMap.put(BookConstant.BOOK_REMARK, DictCodeListTableId.会计场景代码_期权交易_买入方期权到期.getCodeName() + "登账");// 备注
                
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance costSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.买入期权成本.getValue(), taskPri);
                    if(costSubjectBalance != null) {
                        //变量.成本科目余额 = DTO.科目余额
                        varCostSubjectAmt = costSubjectBalance.getBalance();
                        //变量.成本科目数量 = DTO.数量
                        varCostSubjectNum = costSubjectBalance.getNum();
                    }
                    
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance gyjzbdSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.买入期权公允价值变动.getValue(), taskPri);
                    if(gyjzbdSubjectBalance != null) {
                        //变量.公允价值变动科目余额 = DTO.科目余额
                        varGyjzxbdSubjectAmt = gyjzbdSubjectBalance.getBalance();
                    }
                    
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance yfqqfSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_贷方.getValue(), DictCodeBaseSubjectNatrue.其他应付款期权费.getValue(), taskPri);
                    //定义 变量.应付期权费 = 应付费用DTO.科目余额
                    BigDecimal yfqqfBalance = BigDecimal.ZERO;
                    if(yfqqfSubjectBalance != null) {
                        yfqqfBalance = yfqqfSubjectBalance.getBalance();
                    }
                    
                    //accountAmountMap设置
                    //数量金额 <-变量.成本科目数量
                    accountAmountMap.put(BookConstant.ACCQUA, varCostSubjectNum);
                    //交易成本<-变量.成本科目余额
                    accountAmountMap.put(BookConstant.COST, varCostSubjectAmt);
                    //期权费<-变量.应付期权费
                    accountAmountMap.put(BookConstant.QQFEE, yfqqfBalance);
                    //定义 变量.交易金额 = 交易明细.交易金额
                    BigDecimal tradeAmount = dealNow.getTradeAmt();
                    //if(StringUtil.equals(dealNow.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())) {
                    if(StringUtil.equals(dealNow.getPsPayment(), DictCodeList.收付方向_收入.getValue())) {
                        //交易金额_借<-变量.交易金额
                        accountAmountMap.put(BookConstant.DEALAMT_D, tradeAmount);
                    } else {
                        //交易金额_贷<-交易明细.交易金额
                        accountAmountMap.put(BookConstant.DEALAMT_C, tradeAmount);
                        //变量.交易金额<-变量.交易金额 * -1
                        if(tradeAmount != null) {
                            tradeAmount = tradeAmount.negate();
                        }
                    }
                    
                    if(varGyjzxbdSubjectAmt.compareTo(BigDecimal.ZERO)>0) {
                        //公允价值变动_贷<-变量.公允价值变动余额
                        accountAmountMap.put(BookConstant.FLOAT_C,varGyjzxbdSubjectAmt);
                        //公允价值变动损益_借<-变量.公允价值变动余额
                        accountAmountMap.put(BookConstant.FLOAT_INGPL_D,varGyjzxbdSubjectAmt);
                    } else {
                        //公允价值变动_借<-变量.公允价值变动余额*-1
                        accountAmountMap.put(BookConstant.FLOAT_D,varGyjzxbdSubjectAmt.negate());
                        //公允价值变动损益_贷<-变量.公允价值变动余额*-1
                        accountAmountMap.put(BookConstant.FLOAT_INGPL_C,varGyjzxbdSubjectAmt.negate());
                    }
                    //定义 变量.轧差金额 = 变量.交易金额 + 变量.应付期权费 - 变量.成本科目余额
                    BigDecimal netAmt =  ArithmeticUtil.doSub(ArithmeticUtil.doAdd(tradeAmount,yfqqfBalance),varCostSubjectAmt);
                    if(netAmt.compareTo(BigDecimal.ZERO)>0) {
                        //轧差金额_贷<-变量.轧差金额
                        accountAmountMap.put(BookConstant.DSCLOSS_C, netAmt); 
                    } else {
                        //轧差金额_借<-变量.轧差金额*-1
                        accountAmountMap.put(BookConstant.DSCLOSS_D, netAmt.negate());
                    }
                    
                    //定义 本币金额元素Map
                    Map<String, BigDecimal> bCyyAccountAmountMap = new HashMap<String, BigDecimal>();
                    for (String keyNow : accountAmountMap.keySet()) {
                        accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
                        //本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);
                        bCyyAccountAmountMap.put(keyNow + BookConstant.BCCYAMT,
                                ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(keyNow),eExRate),NumberEnum.MONEY));
                    }
                    accountAmountMap.putAll(bCyyAccountAmountMap);
                    
                    //定义  变量.轧差金额_本位币
                    BigDecimal bNetAmt = BigDecimal.ZERO;
                    //if(StringUtil.equals(dealNow.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())) {
                    if(StringUtil.equals(dealNow.getPsPayment(), DictCodeList.收付方向_收入.getValue())) {
                        //变量.轧差金额_本位币 = 期权费_本位币 + 交易金额_借_本位币 - 交易成本_本位币                                                                
                        bNetAmt = ArithmeticUtil.doSub(ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.DEALAMT_D + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT)),accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT));
                    } else {
                        //变量.轧差金额_本位币  = 期权费_本位币 - 交易金额_贷_本位币 - 交易成本_本位币                                                                
                        bNetAmt = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT),
                            accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT),accountAmountMap.get(BookConstant.DEALAMT_C + BookConstant.BCCYAMT)); 
                    }
                    
                    if(bNetAmt.compareTo(BigDecimal.ZERO)>0) {
                       //轧差金额_贷_本位币 <- 变量.轧差金额_本位币
                        accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, bNetAmt); 
                    } else {
                       //轧差金额_借_本位币 <-变量.轧差金额_本位币*-1    
                        accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, bNetAmt.negate());
                    }
                    
                    //本位币 <-交易类账务处理结果.本位币                           
                    accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                }
                
            } 
            else if(StringUtil.equals(tradeInfoDto.getExcPs(),DictCodeList.交易方向_卖出.getValue())) {
                if(StringUtil.equals(dealNow.getPs(), DictCodeList.交易方向_买入.getValue())) {
                    /** modify by yanya on 20200121 tableId修正 start**/
                    //ad.setTableId("期权交易-开仓卖出");
                    ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_开仓卖出.getValue());
                    /** modify by yanya on 20200121 tableId修正 end**/
                    accountSceneMap.put(BookConstant.BOOK_REMARK, DictCodeListTableId.会计场景代码_期权交易_开仓卖出.getCodeName() + "登账");// 备注
                    
                    //accountAmountMap设置
                    //核算数量<-交易明细.交易份额
                    accountAmountMap.put(BookConstant.ACCQUA, dealNow.getShareAmt());
                    //交易金额<-交易明细.交易金额
                    accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
                    //成本金额<-交易明细.全价总额
                    accountAmountMap.put(BookConstant.COST, dealNow.getFpriceAmt());
                    //期权费<-交易明细.全价总额 - 交易明细.交易金额
                    accountAmountMap.put(BookConstant.QQFEE, ArithmeticUtil.subtract(dealNow.getFpriceAmt(), dealNow.getTradeAmt()));
                    
                  //定义 本币金额元素Map
                    Map<String, BigDecimal> bCyyAccountAmountMap = new HashMap<String, BigDecimal>();
                    for (String keyNow : accountAmountMap.keySet()) {
                        accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
                        //本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);
                        bCyyAccountAmountMap.put(keyNow + BookConstant.BCCYAMT,
                                ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(keyNow), eExRate),NumberEnum.MONEY));
                    }
                    accountAmountMap.putAll(bCyyAccountAmountMap);
                    
                    //交易金额_本位币<-成本金额_本位币 - 交易费用_本位币
                    accountAmountMap.put(BookConstant.DEALAMT+BookConstant.BCCYAMT,
                        ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT),
                                accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT)));
                    
                    accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                } 
                else if (StringUtil.equals(dealNow.getPs(), DictCodeListExt3.交易方向_提前行权.getValue())
                        || StringUtil.equals(dealNow.getPs(), DictCodeListExt3.交易方向_到期行权.getValue())
                        || StringUtil.equals(dealNow.getPs(), DictCodeListExt1.交易方向_放弃行权.getValue())){
                    /** modify by yanya on 20200121 tableId修正 start**/
                    //ad.setTableId("期权交易_卖出方期权到期");
                    ad.setTableId(DictCodeListTableId.会计场景代码_期权交易_卖出方期权到期交易.getValue());
                    /** modify by yanya on 20200121 tableId修正 end**/
                    accountSceneMap.put(BookConstant.BOOK_REMARK, DictCodeListTableId.会计场景代码_期权交易_卖出方期权到期交易.getCodeName() + "登账");// 备注

                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance costSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_贷方.getValue(), DictCodeBaseSubjectNatrue.卖出期权成本.getValue(), taskPri);
                    if(costSubjectBalance != null) {
                        //变量.成本科目余额 = DTO.科目余额
                        varCostSubjectAmt = costSubjectBalance.getBalance();
                        //变量.成本科目数量 = DTO.数量
                        varCostSubjectNum = costSubjectBalance.getNum();
                    }
                    
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance gyjzbdSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_贷方.getValue(), DictCodeBaseSubjectNatrue.卖出期权公允价值变动.getValue(), taskPri);
                    if(gyjzbdSubjectBalance != null) {
                        //变量.公允价值变动科目余额 = DTO.科目余额
                        varGyjzxbdSubjectAmt = gyjzbdSubjectBalance.getBalance();
                    }
                    
                    //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->科目余额查询-期待方向SHEET
                    SubjectBalance yfqqfSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailIdNow, happenDateNow, bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.其他应收款期权费.getValue(), taskPri);
                    BigDecimal ysqqfBalance = BigDecimal.ZERO;
                    if(yfqqfSubjectBalance != null) {
                        //定义 变量.应付期权费 = 应付费用DTO.科目余额
                        ysqqfBalance = yfqqfSubjectBalance.getBalance();
                    }
                    
                    //accountAmountMap设置
                    //核算数量 <- 变量.成本科目数量
                    accountAmountMap.put(BookConstant.ACCQUA, varCostSubjectNum);
                    //成本金额 <- 变量.成本科目余额
                    accountAmountMap.put(BookConstant.COST, varCostSubjectAmt);
                    //期权费 <- 变量.应收期权费
                    accountAmountMap.put(BookConstant.QQFEE, ysqqfBalance);
                    //定义 变量.交易金额 = 交易明细.交易金额
                    BigDecimal tradeAmount = dealNow.getTradeAmt();
                    //if(StringUtil.equals(dealNow.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())) {
                    if(StringUtil.equals(dealNow.getPsPayment(), DictCodeList.收付方向_收入.getValue())) {
                        //交易金额_借<-交易明细.交易金额
                        accountAmountMap.put(BookConstant.DEALAMT_D, tradeAmount);
                        //变量.交易金额<-变量.交易金额 * -1
                        if(tradeAmount != null) {
                            tradeAmount = tradeAmount.negate();
                        }
                    } else {
                        //交易金额_贷<-变量.交易金额
                        accountAmountMap.put(BookConstant.DEALAMT_C, tradeAmount);

                    }
                    
                    if(varGyjzxbdSubjectAmt.compareTo(BigDecimal.ZERO)>0) {
                        //公允价值变动_借<-变量.公允价值变动余额
                        accountAmountMap.put(BookConstant.FLOAT_D,varGyjzxbdSubjectAmt);
                        //公允价值变动损益_贷<-变量.公允价值变动余额
                        accountAmountMap.put(BookConstant.FLOAT_INGPL_C,varGyjzxbdSubjectAmt);
                    } else {
                        //公允价值变动_贷<-变量.公允价值变动余额*-1
                        accountAmountMap.put(BookConstant.FLOAT_C,varGyjzxbdSubjectAmt.negate());
                        //公允价值变动损益_借<-变量.公允价值变动余额*-1
                        accountAmountMap.put(BookConstant.FLOAT_INGPL_D,varGyjzxbdSubjectAmt.negate());
                    }
                    //定义 变量.轧差金额 = 变量.交易金额 - 变量.应付期权费 - 变量.成本科目余额 错误修正
                    //定义		变量.轧差金额 = 变量.交易金额 + 变量.应收期权费 - 变量.成本科目余额
                    BigDecimal netAmt =  ArithmeticUtil.doSub(
                            ArithmeticUtil.doAdd(tradeAmount, ysqqfBalance), varCostSubjectAmt);
                    if(netAmt.compareTo(BigDecimal.ZERO)>0) {
                        //轧差金额_借<-变量.轧差金额
                        accountAmountMap.put(BookConstant.DSCLOSS_D, netAmt); 
                    } else {
                        //轧差金额_贷<-变量.轧差金额*-1
                        accountAmountMap.put(BookConstant.DSCLOSS_C, netAmt.negate());
                    }
                    
                    //定义 本币金额元素Map
                    Map<String, BigDecimal> bCyyAccountAmountMap = new HashMap<String, BigDecimal>();
                    for (String keyNow : accountAmountMap.keySet()) {
                        accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
                        //本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);
                        bCyyAccountAmountMap.put(keyNow + BookConstant.BCCYAMT,
                                ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(keyNow), eExRate),NumberEnum.MONEY));
                    }
                    accountAmountMap.putAll(bCyyAccountAmountMap);
                    
                    //定义  变量.轧差金额_本位币
                    BigDecimal bNetAmt = BigDecimal.ZERO;
                    //if(StringUtil.equals(dealNow.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())) {
                    if(StringUtil.equals(dealNow.getPsPayment(), DictCodeList.收付方向_收入.getValue())) {
                        //变量.轧差金额_本位币 = 成本金额_本位币 + 交易金额_借_本位币 - 期权费_本位币
                        bNetAmt = ArithmeticUtil.doSub(
                                ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT),
                                    accountAmountMap.get(BookConstant.DEALAMT_D + BookConstant.BCCYAMT)),
                                accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT));
                    } else {
                        //变量.轧差金额_本位币  = 成本金额_本位币  - 交易金额_贷_本位币 - 期权费_本位币
                        bNetAmt = ArithmeticUtil.doSub(
                                accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT),
                                accountAmountMap.get(BookConstant.DEALAMT_C + BookConstant.BCCYAMT),
                                accountAmountMap.get(BookConstant.QQFEE + BookConstant.BCCYAMT));
                    }
                    
                    if(bNetAmt.compareTo(BigDecimal.ZERO)>0) {
                       //轧差金额_贷_本位币 <- 变量.轧差金额_本位币
                        accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, bNetAmt); 
                    } else {
                       //轧差金额_借_本位币 <-变量.轧差金额_本位币*-1    
                        accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, bNetAmt.negate());
                    }
                    
                    //本位币 <-交易类账务处理结果.本位币                           
                    accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                
                }
            }
            
            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素
            
            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            returnResult.getValBokBookdetailBatch().addAll(
                resultNowNow.getValBokBookdetailBatch());
        }
        return returnResult;
    }
    
    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     * 
     * @param booksetId 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDate 发生日
     * @param expBalanceDirect 期待余额方向
     * @param bookDate 入账日
     * @param baseSubjectNature 基础科目性质
     * @param taskPri 任务优先级
     * @return subjectBalance 科目余额信息
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetId, String bokDetailId, Date happenDate, Date bookDate,
                                                     String expBalanceDirect, String baseSubjectNature, Long taskPri) {
     // 定义返回实体类
        SubjectBalance subjectBalance = null;
        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }

        // 设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDate);
        searchDto.setBookDate(bookDate);
        searchDto.setTaskPri(taskPri);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询 - 期待方向】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto,
            expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            subjectBalance = subjectBalanceList.get(0);
        }

        return subjectBalance;
    }
}
