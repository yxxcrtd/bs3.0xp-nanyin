/*
 * 文件名：DeltRightDealPreprocessingBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.deltrightdealpreprocessingbook.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.*;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 债权类交易账务批处理 Processor逻辑Service
 * 
 * @author chengzhong
 * @version 1.0 2018年6月6日
 * @see DeltRightDealPreprocessingBookProcessorService
 * @since 1.0
 */
@Service
public class DeltRightDealPreprocessingBookProcessorService {

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 标的类共通方法
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    @Autowired
    private CalculationCommonService calculationCommonService;

    /**
     * process 数据处理
     * 
     * @param param1 参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param1.get(
            GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }

        // 定义返回值
        BookGenerateResult returnResult = new BookGenerateResult();
        // 防止报空指针异常
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }
        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 交易类账务处理结果.账套代码
        String bokDetailId = acctEntity.getBokDetailId();// 交易类账务处理结果.账务明细代码
        TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 交易类账务处理结果.会计场景
        Date happenDateNow = acctEntity.getHappenDate();// 交易类账务处理结果.发生日期
        Date bookDateNow = acctEntity.getBookDate();// 交易类账务处理结果.入账日期
        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 金融产品交易信息list
        // 没有明细
        if (listFinProductDealInfoComDTONow == null
            || listFinProductDealInfoComDTONow.size() == 0) {
            return null;
        }
        // 1.数据准备
        // 1.1 定义变量
        // 成本科目余额
        BigDecimal costBalance = BigDecimal.ZERO;
        // 成本科目数量
        BigDecimal costQuantity = BigDecimal.ZERO;
        // 应计利息余额
        BigDecimal interestBalance = BigDecimal.ZERO;
        // 溢折价科目余额
        BigDecimal spilledPriceBalance = BigDecimal.ZERO;
        
        Long taskPri = listFinProductDealInfoComDTONow.get(0).getTaskPri();
        // 2.调用共通查询发生日前一日余额
        // 交易期待方向——借方
        String expBalanceDirect = DictCodeList.余额方向_借方.getValue();
        SubjectBalance subjectBalance = new SubjectBalance();
        // 2.1.成本类科目余额 -【 调用共通方法：科目余额查询,获取成本类科目余额 】
        subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow,
            bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.计划类资产_成本.getValue(), null,
            taskPri);
        // 成本类科目余额赋值
        costBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO : 
            subjectBalance.getBalance();
        // 成本类科目数量赋值
        costQuantity = subjectBalance.getNum() == null ? BigDecimal.ZERO :
            subjectBalance.getNum();

        // 2.2.应计利息类科目余额 -【 调用共通方法：科目余额查询,获取应收利息类科目余额 】
        subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow,
            bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.应计利息.getValue(), null,
            taskPri);
        interestBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO :
            subjectBalance.getBalance();

        // 2.3.溢折价类科目余额
        subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow,
            bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.计划类资产_利息调整.getValue(), null,
            taskPri);
        spilledPriceBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO :
            subjectBalance.getBalance();

        // 3.处理交易
        // 交易类账务处理结果list,进行逻辑处理
        for (FinProductDealInfoComDTO dealNow : listFinProductDealInfoComDTONow) {
        	
        	/**add by taoran 20200103 start**/
            // 汇率
            BigDecimal changeRate = BigDecimal.ONE;
            if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
                // 调用共通汇率查询
            	//changeRate = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), acctEntity.getStandardCcy(), acctEntity.getHappenDate());
                /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
                List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
                        acctEntity.getStandardCcy(), dealNow.getCcy());
                if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                    changeRate = currencyMarketList.get(0).getClosePrice();
                }
                /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
            }
            /**add by taoran 20200103 end**/
            
            TargetSearchDto targetSearchDto = new TargetSearchDto();
            targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
            List<TargetSearchDto> queryCriteria = new ArrayList<>();
            queryCriteria.add(targetSearchDto);
            // 查询标的信息
            Map<TargetSearchDto, Object> targetSearchMap = finProductCommonService.getTargetInfo(
                dealNow.getFinprodId(), queryCriteria);
            // 获取标的信息
            TargetFinProduct targetFinProduct = (TargetFinProduct)targetSearchMap.get(
                targetSearchDto);

            AccountDriver ad = new AccountDriver();// 凭证类型、账套代码、交易编号全部去掉了。
            ad.setTreeNode(bookSetTreeNodNow);// 塞入记账场景树节点 = 会计场景

            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();
            accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
            // 共通元素记账场景要素map设值 accountSceneMap
            // 境内外 = 变量.交易信息.境内外
            accountSceneMap.put(BookConstant.BLN_AREA, targetFinProduct.getBlnArea());
            //资产类型
            accountSceneMap.put(BookConstant.ASSET_TYPE, targetFinProduct.getFinprodType());
            //分类1
            accountSceneMap.put(BookConstant.TYPE_1, targetFinProduct.getType1());
            //分类2
            accountSceneMap.put(BookConstant.TYPE_2, targetFinProduct.getType2());
            //分类3
            accountSceneMap.put(BookConstant.TYPE_3, targetFinProduct.getType3());
            //分类4
            accountSceneMap.put(BookConstant.TYPE_4, targetFinProduct.getType4());
            //分类5
            accountSceneMap.put(BookConstant.TYPE_5, targetFinProduct.getType5());
            //分类6
            accountSceneMap.put(BookConstant.TYPE_6, targetFinProduct.getType6());
            // 是否累计余额->是
            accountSceneMap.put(BookConstant.IS_ACC_BALANCE, DictCodeList.是否_是.getValue());
            
            // 备注 = "债权类资产"+ 交易方向数据字典对应显示值 +"交易登账"
            accountSceneMap.put(BookConstant.BOOK_REMARK,
                "债权类资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs())
                                                          + "交易登账");
            if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())) {
                // 发生数量->交易明细.交易份额
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.QTY));
            }
            else if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // 发生数量->交易明细.交易份额
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, ArithmeticUtil.round(costQuantity, NumberEnum.QTY));
            }
            else {
                // 发生数量->交易明细.交易份额
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, BigDecimal.ZERO);
            }

            // else if（交易方向 = "收息"）
            if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())) {
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_收益型资产_收息.getValue());
                // 定义 变量.到期日前一日= 交易明细.到期日 -1
                Date mYestday = DateUtil.addDay(dealNow.getMdate(), -1);
                // (1) 查询交易的到期日对应的应计利息 调用金额共通方法设置有效小数位
                // 【weipeng-note: 确定 计划类资产_应收利息 数据字典是什么,当前先传入"JHLZC_YSLX"；设计中的分支号没有对应需传的参数】
                BigDecimal interest = this.getSubjectBalance(booksetIdNow, bokDetailId, mYestday,
                    bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.应计利息.getValue(), null,
                    null).getBalance();
                interest = interest == null ? BigDecimal.ZERO : interest;
                // (2) 查询交易的到期日是否为付息截止日
                // 定义 变量.是否付息截止日 调用共通方法-【标的类共通方法设计】-付息截止日
                boolean payDeadlineFlag = false;
                FinCashPlanDto ginCashPlanDto = finProductCommonService.payDeadlineJudgment(dealNow.getFinprodId(),
                    dealNow.getMdate());
                payDeadlineFlag = ginCashPlanDto.isfDeadline();
                //现金流计划表DTO
                FinCashPlanEx finCashPlanEx = ginCashPlanDto.getFinCashPlanEx();
                //(3) 查询交易的到期日对应的应计利息
                BigDecimal varCbNum = this.getSubjectBalance(booksetIdNow, bokDetailId, mYestday,
                    bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.计划类资产_成本.getValue(), null,
                    null).getNum();
                
                // if 变量.是否付息截止日 为 false）
                if (!payDeadlineFlag) {
                    //变量.当前本金 = 调用共通计算类共通【当前未还本金（DTO.账套日期,DTO.金融产品代码,变量.标的信息.发行规模）】
                    BigDecimal varPrinAmt = finProductCommonService.getUnpaidPrinAmt(
                        dealNow.getBooksetDate(), dealNow.getFinprodId(),
                        targetFinProduct.getIssueAmt());
                    //变量.交易份额 = 现金流计划表DTO.计划支付本金 / 变量.当前本金
                    BigDecimal varShareAmt = ArithmeticUtil.divide(finCashPlanEx.getPrinAmt() == null 
                        ? BigDecimal.ZERO : finCashPlanEx.getPrinAmt(), varPrinAmt, NumberEnum.ALL_PRECISION.getCode());
                    varShareAmt = ArithmeticUtil.multiply(varShareAmt, varCbNum);
                    //变量.应计利息 = 变量.应计利息 * 变量.交易份额 / 变量.成本科目数量
                    interest = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(interest, varShareAmt), varCbNum,
                        NumberEnum.MONEY.getCode());
                }

                // 记账金额元素map塞值 accountAmountMap设置如下：
                // 应计利息 = 变量.应计利息
                accountAmountMap.put(BookConstant.INTAMT, interest);
                accountAmountMap.put(BookConstant.OTHER_INTAMT, interest);
                /**add by taoran 20200103 start**/
                //应收利息_本位币<-	应收利息
                accountAmountMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, calculationCommonService.doMultiply(interest, changeRate));
                accountAmountMap.put(BookConstant.OTHER_INTAMT + BookConstant.BCCYAMT, calculationCommonService.doMultiply(interest, changeRate));
                /**add by taoran 20200103 end**/
                // accountCurrencyMap设置如下：
                // 应计利息 = 交易明细.交易币种
                accountCurrencyMap.put(BookConstant.INTAMT, dealNow.getCcy());
                accountCurrencyMap.put(BookConstant.OTHER_INTAMT, dealNow.getCcy());
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
                
                //基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                
                // 全局变量的更新
                // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.应计利息
                interestBalance = ArithmeticUtil.doSub(interestBalance, interest);
                
            }
            // else if（交易方向 = "还本/提前终止/到期"）
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs())
                     || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // 定义 变量.到期日前一日= 交易明细.到期日 -1
                Date mYestday = DateUtil.addDay(dealNow.getMdate(), -1);
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_收益型资产_还本到期.getValue());
                // 记账金额元素map塞值 accountAmountMap设置如下：
                // 定义 变量.结转日成本科目余额 = 科目余额
                SubjectBalance tDaySubjectBalance = this.getSubjectBalance(booksetIdNow,
                    bokDetailId, mYestday, bookDateNow, expBalanceDirect,
                    DictCodeBaseSubjectNatrue.计划类资产_成本.getValue(), null, null);
                BigDecimal tDayCostBalance = tDaySubjectBalance.getBalance() == null ? BigDecimal.ZERO : tDaySubjectBalance.getBalance();
                // 定义 变量.结转日成本科目数量 = 科目数量
                BigDecimal tDayCostQuantity = tDaySubjectBalance.getNum() == null ? BigDecimal.ZERO : tDaySubjectBalance.getNum();

                // 定义 变量.结转日溢折价科目余额 = 科目余额
                BigDecimal tDaySpiiledPriBalance = this.getSubjectBalance(booksetIdNow,
                    bokDetailId, mYestday, bookDateNow, expBalanceDirect,
                    DictCodeBaseSubjectNatrue.计划类资产_利息调整.getValue(), null, null).getBalance();
                tDaySpiiledPriBalance = tDaySpiiledPriBalance == null ? BigDecimal.ZERO : tDaySpiiledPriBalance;
                
                //变量.交易份额 = 变量.成本科目数量 * 交易明细.单位净价
                BigDecimal varShareAmt = ArithmeticUtil.multiply(tDayCostQuantity, dealNow.getUnitCprice());
                //变量.交易金额 = 变量.成本科目数量 * 交易明细.单位净价
                BigDecimal varDealAmt = ArithmeticUtil.multiply(tDayCostQuantity, dealNow.getUnitCprice());
                //变量.交易本金 = 变量.成本科目数量 * 交易明细.单位净价
                BigDecimal varPrinAmt = ArithmeticUtil.multiply(tDayCostQuantity, dealNow.getUnitCprice());

                    
                // 定义 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 交易明细.交易份额/变量.成本科目数量
                BigDecimal scourSpilledPrice = ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(tDaySpiiledPriBalance, varShareAmt),
                    tDayCostQuantity);
                // 调用金额共通方法设置有效小数位
                scourSpilledPrice = ArithmeticUtil.round(scourSpilledPrice, NumberEnum.MONEY);
                // 定义 = 变量.冲减成本 = 0
                BigDecimal scourCost = BigDecimal.ZERO;
                // 定义 变量.冲减数量 = 0
                BigDecimal scourQuantity = BigDecimal.ZERO;
                // 定义 变量.买卖差价 = 0
                BigDecimal saleDiffer = BigDecimal.ZERO;

                // if（交易方向 = "到期"）
                if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                    // 变量.冲减溢折价 = 变量.结转日溢折价科目余额
                    scourSpilledPrice = tDaySpiiledPriBalance;
                    // 变量.冲减成本 = 变量.结转日成本科目余额
                    scourCost = tDayCostBalance;
                    // 变量.冲减数量 <- 变量.结转日成本科目数量
                    scourQuantity = tDayCostQuantity;
                }
                else {
                    // 变量.冲减溢折价 = 变量.结转日溢折价科目余额 * 变量.交易份额/变量.成本科目数量
                    scourSpilledPrice = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(tDaySpiiledPriBalance, varShareAmt),
                        tDayCostQuantity, NumberEnum.MONEY.getCode());
                    // 调用金额共通方法设置有效小数位
                    scourSpilledPrice = ArithmeticUtil.round(scourSpilledPrice, NumberEnum.MONEY);
                    
                    // 变量.冲减成本 = 变量.结转日成本科目余额 * 变量.交易份额/变量.成本科目数量
                    scourCost = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(tDayCostBalance, varShareAmt),
                        tDayCostQuantity, NumberEnum.MONEY.getCode());
                    // 调用金额共通方法设置有效小数位
                    scourCost = ArithmeticUtil.round(scourCost, NumberEnum.MONEY);
                    // 变量.冲减数量 <- 变量.交易份额
                    scourQuantity = ArithmeticUtil.round(varShareAmt, NumberEnum.MONEY);
                }
                // 变量.买卖价差 = 变量.交易本金 - （变量.冲减成本+变量.冲减溢折价） 取两位小数 modify by yanya on 20200228
                saleDiffer = ArithmeticUtil.round(ArithmeticUtil.doSub(varPrinAmt, scourCost,
                    scourSpilledPrice), NumberEnum.MONEY);

                // 交易金额 <- 变量.交易金额
                accountAmountMap.put(BookConstant.OTHER_COST,
                    ArithmeticUtil.round(varDealAmt, NumberEnum.MONEY));
                // 面值 <- 变量.冲减成本
                accountAmountMap.put(BookConstant.COST,
                    ArithmeticUtil.round(scourCost, NumberEnum.MONEY));

                // if（变量.买卖价差>= 0）
                if (saleDiffer.compareTo(BigDecimal.ZERO) >= 0) {
                    // 买卖价差（贷）<- 变量.买卖价差
                    accountAmountMap.put(BookConstant.INCOME_C, saleDiffer);
                }
                else {
                    // 买卖价差（借）= 变量.买卖价差*-1
                    accountAmountMap.put(BookConstant.INCOME_D,
                        ArithmeticUtil.round(
                            ArithmeticUtil.multiply(saleDiffer, new BigDecimal("-1")),
                            NumberEnum.MONEY));
                }
                // IF 变量.冲减溢折价 >= 0
                if (scourSpilledPrice.compareTo(BigDecimal.ZERO) >= 0) {
                    // 溢折价_贷 <- 变量.冲减溢折价
                    accountAmountMap.put(BookConstant.DSCINC_C,
                        ArithmeticUtil.round(scourSpilledPrice, NumberEnum.MONEY));
                }
                else {
                    // 溢折价_借 <- 变量.冲减溢折价*-1
                    accountAmountMap.put(BookConstant.DSCINC_D,
                        ArithmeticUtil.round(
                            ArithmeticUtil.multiply(scourSpilledPrice, new BigDecimal("-1")),
                            NumberEnum.MONEY));
                }
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, ArithmeticUtil.round(scourQuantity, NumberEnum.QTY));
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, 
                	    calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 变量.买卖价差_本位币
                BigDecimal saleDifferBccy = BigDecimal.ZERO;
                if (scourSpilledPrice.compareTo(BigDecimal.ZERO) >= 0) {
                    //变量.买卖价差_本位币 = 	交易金额_本位币 - (面值_本位币 + 溢折价(贷)_本位币)
                	saleDifferBccy = ArithmeticUtil.round(ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.OTHER_COST + BookConstant.BCCYAMT),
                						ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT), 
            									accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT))),NumberEnum.MONEY);
                }
                else {
                	// 	变量.买卖价差_本位币 = 	交易金额_本位币 + 溢折价(借)_本位币 - 面值_本位币
                	saleDifferBccy = ArithmeticUtil.round(ArithmeticUtil.doSub(
                				ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.OTHER_COST + BookConstant.BCCYAMT), 
									accountAmountMap.get(BookConstant.DSCINC_D + BookConstant.BCCYAMT)),
                						accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)),NumberEnum.MONEY);
                }
                if(saleDifferBccy.compareTo(BigDecimal.ZERO) >= 0) {
                	//买卖价差（贷）_本位币   <- 变量.买卖价差_本位币						
                	accountAmountMap.put(BookConstant.INCOME_C + BookConstant.BCCYAMT, 
                			ArithmeticUtil.round(saleDifferBccy, NumberEnum.MONEY.getCode(), null));
                }
                else {
                	//买卖价差（借）_本位币	<-	变量.买卖价差_本位币 * -1							
                	accountAmountMap.put(BookConstant.INCOME_D + BookConstant.BCCYAMT, 
                			ArithmeticUtil.round(saleDifferBccy.negate(), NumberEnum.MONEY.getCode(), null));
                }
                /**add by taoran 20200103 end**/
                
                // accountCurrencyMap设置如下：
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
                //基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                
                // 全局变量更新
                // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
                costBalance = ArithmeticUtil.doSub(costBalance, scourCost);
                // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
                costQuantity = ArithmeticUtil.doSub(costQuantity, scourQuantity);
                // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
                spilledPriceBalance = ArithmeticUtil.doSub(spilledPriceBalance, scourSpilledPrice);
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
     * @param booksetIdNow 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDateNow 发生日
     * @param bookDate 入账日
     * @param expBalanceDirect 期待余额方向
     * @param baseSubjectNature 基础科目性质
     * @param branch 分支号
     * @return SubjectBalance 科目余额
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetIdNow, String bokDetailId,
                                             Date happenDateNow, Date bookDate,
                                             String expBalanceDirect, String baseSubjectNature,
                                             Long branch, Long taskPri) {
        // 定义返回的科目余额
        SubjectBalance balance = new SubjectBalance();

        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }

        // 设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetIdNow);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDateNow);
        searchDto.setBookDate(bookDate);
        searchDto.setBranch(branch);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        searchDto.setTaskPri(taskPri);
        // 调用公用方法：【科目余额查询-期待方向 】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDto, expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            balance = subjectBalanceList.get(0);
        }

        return balance;
    }

}
