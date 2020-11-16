/*
 * 文件名：DeltRightDealBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.deltrightdealbook.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
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
import com.joyintech.tams.valuation.batch.common.calculation.entity.DealTypesFeeDto;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 债权类交易账务批处理 Processor逻辑Service
 * 
 * @author weipeng
 * @version 1.0 2018年6月6日
 * @see DeltRightDealBookProcessorService
 * @since 1.0
 */
@Service
public class DeltRightDealBookProcessorService {

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
     * 计算类共通
     */
    @Autowired
    private CalculationCommonService calculationCommonService;

    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

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
        
        /**
         * 变量.其他应收款-利息科目余额 = MAP（KEY：分支序号，VALUE:科目余额）
         */
        Map<String, BigDecimal> varQtyskxSubjectAmt = new HashMap<>();
        
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
            /**add by taoran 20200103 start**/
            // 汇率
            BigDecimal changeRate = BigDecimal.ONE;
            if(StringUtil.isNotEmpty(dealNow.getCcy()) && !dealNow.getCcy().equals(acctEntity.getStandardCcy())){
                // 调用共通汇率查询
            	//changeRate = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), acctEntity.getStandardCcy(), acctEntity.getHappenDate());
                /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
                //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
                List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
                        acctEntity.getStandardCcy(), dealNow.getCcy());
                if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                    changeRate = currencyMarketList.get(0).getClosePrice();
                }
                /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
            }
            /**add by taoran 20200103 end**/
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


            // if（交易方向 = "买入"）
            if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())) {
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_收益型资产_买入.getValue());

                // 记账金额元素map塞值
                // 定义 变量.溢折价 = 交易明细.交易金额 - 交易明细.交易本金 - 交易明细.利息总额
                BigDecimal spilledPrice = ArithmeticUtil.doSub(dealNow.getTradeAmt(),
                    dealNow.getPrinAmt(), dealNow.getIntAmt());
                accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));// 本息和 = 交易金额
                accountAmountMap.put(BookConstant.COST,
                    ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));// 本金金额 = 交易本金
                accountAmountMap.put(BookConstant.INTAMT,
                    ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));// 应收利息=交易明细.利息总额
                // IF 变量.溢折价 >= 0
                if (spilledPrice.compareTo(BigDecimal.ZERO) >= 0) {
                    // 溢折价_借 = 变量.溢折价
                    accountAmountMap.put(BookConstant.DISCNT_PREAMT_D,
                        ArithmeticUtil.round(spilledPrice, NumberEnum.MONEY));
                }
                else {
                    // 溢折价_贷 = 变量.溢折价*-1
                    accountAmountMap.put(BookConstant.DISCNT_PREAMT_C,
                        ArithmeticUtil.round(
                            ArithmeticUtil.multiply(spilledPrice, new BigDecimal("-1")),
                            NumberEnum.MONEY));
                }
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 变量.本息和_本位币
                BigDecimal dealAmtBccy = BigDecimal.ZERO;
                if (spilledPrice.compareTo(BigDecimal.ZERO) >= 0) {
                    // 变量.本息和_本位币 = 	本金金额_本位币 + 应收利息_本位币 + 溢折价(借)_本位币
                	dealAmtBccy = ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)
                    				, accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)
                    				, accountAmountMap.get(BookConstant.DISCNT_PREAMT_D + BookConstant.BCCYAMT));
                }
                else {
                	// 变量.本息和_本位币 = 	本金金额_本位币 + 应收利息_本位币 - 溢折价(贷)_本位币
                	dealAmtBccy = ArithmeticUtil.doSub(ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)
                    				, accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT))
                    				, accountAmountMap.get(BookConstant.DISCNT_PREAMT_C + BookConstant.BCCYAMT));
                }
                //本息和_本位币<-变量.本息和_本位币		
                accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, 
                		ArithmeticUtil.round(dealAmtBccy, NumberEnum.MONEY.getCode(), null));
                /**add by taoran 20200103 end**/
                
                // 记账金额元素币种信息塞值--key是随记账金额map的
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
                // 更新成本科目余额、成本科目数量、应收利息科目余额和溢折价科目余额
                // 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易本金
                costBalance = ArithmeticUtil.doAdd(costBalance, dealNow.getPrinAmt());
                // 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
                costQuantity = ArithmeticUtil.doAdd(costQuantity, dealNow.getShareAmt());
                // 变量.溢折价科目余额 = 变量.溢折价科目余额 + 变量.溢折价
                spilledPriceBalance = ArithmeticUtil.doAdd(spilledPriceBalance, spilledPrice);
                // 变量.应计利息科目余额 = 变量.应计利息科目余额 + 交易明细.利息总额
                interestBalance = ArithmeticUtil.doAdd(interestBalance, dealNow.getIntAmt());
            }
            // else if（交易方向 = "卖出"）
            else if (DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_收益型资产_卖出.getValue());

                /** modify by yanya on 20200106 实付通道费修改 start**/
                // 费用处理共通
//                DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
//                    dealNow.getValTradeFeeDetailList(),dealNow.getProdId(),dealNow.getPortfolioId());
                DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(dealNow.getValTradeFeeDetailList(),
                        dealNow.getProdId(), dealNow.getPortfolioId(), dealNow.getFinprodId());
                //通道费账务明细代码	<-	变量.交易各种费用.通道费账务明细代码
                //通道费业务明细代码	<-	变量.交易各种费用.通道费业务明细代码
                BokDetailId chlFeeCode = dealTypesFeeDto.getChlFeeCode();
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, chlFeeCode);

                // 实付通道费 <-变量.交易各种费用.通道费
//                accountAmountMap.put(BookConstant.CHLAMT,
//                    ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
//                accountCurrencyMap.put(BookConstant.CHLAMT, dealNow.getCcy());
                accountAmountMap.put(BookConstant.REAL_CHLAMT,
                        ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
                accountCurrencyMap.put(BookConstant.REAL_CHLAMT, dealNow.getCcy());
                // 实付通道管理费 <- 变量.交易各种费用.通道管理费
                // 实付通道销售费 <- 变量.交易各种费用.通道销售费
                // 实付通道托管费 <- 变量.交易各种费用.通道托管费
                /** modify by yanya on 20200106 实付通道费修改 end**/

                // 记账金额元素map塞值
                // 定义 变量.冲减溢折价 = 变量.溢折价科目余额 * 交易明细.交易份额/变量.成本科目数量 调用金额共通方法设置有效小数位
                BigDecimal scourSpilledPrice = ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(spilledPriceBalance, dealNow.getShareAmt()), costQuantity,
                    NumberEnum.MONEY.getCode());
                // 定义 买卖价差 = 交易明细.交易金额 - (交易明细.交易份额 + 变量.冲减溢折价) - 交易明细.利息总额
                /** modify by yanya on 20200226 **/
                // 实收金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
//                accountAmountMap.put(BookConstant.INTAMT,
//                    ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));
                BigDecimal varCjyjlx = BigDecimal.ZERO;
                if(dealNow.getShareAmt().compareTo(costQuantity)> 0){
                    varCjyjlx = ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(interestBalance, dealNow.getShareAmt()), costQuantity,
                            NumberEnum.MONEY.getCode());
                    // 冲减应计利息方式 01、按比例冲减 02、按实付利息冲减
                    String offsetIntamtMode = CacheUtil.getSysParam("OFFSET_INTAMT_MODE_JHL");
                    if (StringUtils.isNotEmpty(offsetIntamtMode)
                            && offsetIntamtMode.equals(DictCodeList.冲减应计利息方式_按实付利息冲减.getValue())) {
                        varCjyjlx = dealNow.getIntAmt();
                    }
                }else{
                    varCjyjlx = interestBalance;
                }
                accountAmountMap.put(BookConstant.INTAMT,
                        ArithmeticUtil.round(varCjyjlx, NumberEnum.MONEY));

                //定义  变量.买卖价差=交易明细.交易金额 - (交易明细.交易份额 + 变量.冲减溢折价)  - 交易明细.利息总额 - 各种变量.交易各种费用.通道费
                BigDecimal saleDiffer = ArithmeticUtil.doSub(dealNow.getTradeAmt(),
                        dealNow.getShareAmt(), scourSpilledPrice, interestBalance,
                        accountAmountMap.get(BookConstant.REAL_CHLAMT));

                // 本金金额 <-交易明细.交易份额
                accountAmountMap.put(BookConstant.COST,
                    ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.MONEY));
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

                // IF （变量.买卖价差>= 0）
                if (saleDiffer.compareTo(BigDecimal.ZERO) >= 0) {
                    // 买卖价差（贷）= 变量.买卖价差
                    accountAmountMap.put(BookConstant.INCOME_C, saleDiffer);
                }
                else {
                    // 买卖价差（借）= 变量.买卖价差*-1
                    accountAmountMap.put(BookConstant.INCOME_D,
                        ArithmeticUtil.round(
                            ArithmeticUtil.multiply(saleDiffer, new BigDecimal("-1")),
                            NumberEnum.MONEY));
                }
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 变量.买卖价差_本位币	
                BigDecimal saleDifferBccy = BigDecimal.ZERO;
                if (scourSpilledPrice.compareTo(BigDecimal.ZERO) >= 0) {
                    // 变量.买卖价差_本位币 = 实收金额_本位币 + 实付通道费_本位币 - (本金金额_本位币 + 溢折价(贷)_本位币 + 应收利息_本位币)
                	saleDifferBccy = ArithmeticUtil.doSub(ArithmeticUtil.doAdd(
                					accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)
                    				//, accountAmountMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)),
                                    , accountAmountMap.get(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT)),
                    				ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)
                            				, accountAmountMap.get(BookConstant.DSCINC_C + BookConstant.BCCYAMT)
                            				, accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)));
                }
                else {
                	//变量.买卖价差_本位币 = 	实收金额_本位币 + 实付通道费_本位币 + 溢折价(借)_本位币 - (本金金额_本位币 + 应收利息_本位币)																											
                	saleDifferBccy = ArithmeticUtil.doSub(ArithmeticUtil.doAdd(
        					accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)
            				//, accountAmountMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)
                            , accountAmountMap.get(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT)
            				, accountAmountMap.get(BookConstant.DSCINC_D + BookConstant.BCCYAMT)),
            				ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)
                    				, accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)));
                }
                if(saleDifferBccy.compareTo(BigDecimal.ZERO) > 0) {
                	//买卖价差（贷）_本位币	<-	变量.买卖价差_本位币						
                	accountAmountMap.put(BookConstant.INCOME_C + BookConstant.BCCYAMT, ArithmeticUtil.round(saleDifferBccy, NumberEnum.MONEY.getCode(), null));
                }
                else {
                	//买卖价差（借）_本位币 <- 变量.买卖价差_本位币 * -1							
                	accountAmountMap.put(BookConstant.INCOME_D + BookConstant.BCCYAMT, ArithmeticUtil.round(saleDifferBccy.negate(), NumberEnum.MONEY.getCode(), null));
                }
                /**add by taoran 20200103 end**/
                
                // 记账金额元素币种信息塞值--key是随记账金额map的
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
                // 更新全局变量
                // 变量.成本科目余额 = 变量.成本科目余额 - 交易明细.交易本金
                costBalance = ArithmeticUtil.doSub(costBalance, dealNow.getPrinAmt());
                // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
                costQuantity = ArithmeticUtil.doSub(costQuantity, dealNow.getShareAmt());
                // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.冲减应计利息
                interestBalance = ArithmeticUtil.doSub(interestBalance, dealNow.getIntAmt());
                // 变量.溢折价科目余额 = 变量.溢折价科目余额 - 变量.冲减溢折价
                spilledPriceBalance = ArithmeticUtil.doSub(spilledPriceBalance, scourSpilledPrice);
            }
            // else if（交易方向 = "收息到账"）
            else if (DictCodeList.交易方向_收息到账.getValue().equals(dealNow.getPs())) {
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_收益型资产收息.getValue());

                // 查询交易的到期日对应的应计利息
                // 定义 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
                BigDecimal qtyskxSubjectAmt = varQtyskxSubjectAmt.get(
                    String.valueOf(dealNow.getBranch()));
                if (qtyskxSubjectAmt == null) {
                    // 查询交易的到期日对应的应计利息
                    SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
                    searchDao.setBooksetId(dealNow.getBooksetId());
                    searchDao.setBokDetailId(dealNow.getBokDetailId());
                    searchDao.setHappenDate(dealNow.getHappenDate());
                    searchDao.setBookDate(dealNow.getBookDate());
                    searchDao.setTaskPri(dealNow.getTaskPri());
                    searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                        Arrays.asList(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue())));
                    searchDao.setBranch(dealNow.getBranch());
                    List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                        searchDao, DictCodeList.余额方向_借方.getValue());
                    if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                        // 变量.应计利息余额 = 科目余额
                        qtyskxSubjectAmt = listSubjectBalance.get(0).getBalance();
                        varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()),
                            qtyskxSubjectAmt);
                    }
                }
                // 记账金额元素map塞值 accountAmountMap设置如下：
                /** modify by yanya on 20200106 实付通道费修改 start**/
                // 费用处理共通
//                DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
//                    dealNow.getValTradeFeeDetailList(),dealNow.getProdId(),dealNow.getPortfolioId());
                DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(dealNow.getValTradeFeeDetailList(),
                        dealNow.getProdId(), dealNow.getPortfolioId(), dealNow.getFinprodId());
                // 实付通道费 <-变量.交易各种费用.通道费
                accountAmountMap.put(BookConstant.REAL_CHLAMT,
                    ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
                accountCurrencyMap.put(BookConstant.REAL_CHLAMT, dealNow.getCcy());
                //通道费账务明细代码 <-	变量.交易各种费用.通道费账务明细代码
                //通道费业务明细代码 <- 变量.交易各种费用.通道费业务明细代码
                BokDetailId chlFeeCode = dealTypesFeeDto.getChlFeeCode();
                accountSceneMap.put(BookConstant.BOK_DETAIL_ID3, chlFeeCode);
                // 实付通道管理费 <- 变量.交易各种费用.通道管理费
                // 实付通道销售费 <- 变量.交易各种费用.通道销售费
                // 实付通道托管费 <- 变量.交易各种费用.通道托管费
                /** modify by yanya on 20200106 实付通道费修改 end**/

                // 实际利息 = 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
                // 定义 变量.应计利息 = 0
                BigDecimal varInterestAccBal = BigDecimal.ZERO;
                
                if(dealNow.getTradeDate().compareTo(dealNow.getMdate())<0){
                	varInterestAccBal = dealNow.getFpriceAmt();
                    // 应计利息 = 交易明细.全价总额
                    accountAmountMap.put(BookConstant.INTAMT, varInterestAccBal);
                }else{
                	 // IF 交易明细.是否结清 = "否"
                    if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
                    	varInterestAccBal = dealNow.getFpriceAmt().compareTo(qtyskxSubjectAmt)<0?dealNow.getFpriceAmt():qtyskxSubjectAmt;
                        // 应计利息 = 交易明细.全价总额
                        accountAmountMap.put(BookConstant.INTAMT, varInterestAccBal);
                    }
                    else {
                    	varInterestAccBal = qtyskxSubjectAmt;
                    }
                }
                // 应计利息 = 变量.应计利息
                accountAmountMap.put(BookConstant.INTAMT, varInterestAccBal);
                /** modify by yanya on 20200106 实付通道费修改 start**/
                // 定义 变量.利息差额 = 交易明细.全价总额 - 变量.应计利息 - 变量.交易各种费用.通道费
                //BigDecimal interstSpread = ArithmeticUtil.doSub(dealNow.getFpriceAmt(),varInterestAccBal);
                BigDecimal interstSpread = ArithmeticUtil.doSub(dealNow.getFpriceAmt(), varInterestAccBal, dealTypesFeeDto.getChlFee());
                /** modify by yanya on 20200106 实付通道费修改 end**/
                // if 变量.利息差额 >= 0
                if (interstSpread.compareTo(BigDecimal.ZERO) >= 0) {
                    // 利息差额(贷) <- 变量.利息差额
                    accountAmountMap.put(BookConstant.NETAMT_C,
                        ArithmeticUtil.round(interstSpread, NumberEnum.MONEY));
                }else {
                    // 利息差额(借) <- 变量.利息差额*-1
                    accountAmountMap.put(BookConstant.NETAMT_D,
                        ArithmeticUtil.round(ArithmeticUtil.multiply(interstSpread, new BigDecimal(-1)),NumberEnum.MONEY));
                }
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 变量.利息差额_本位币 = 实际利息_本位币 + 实付通道费_本位币 - 应收利息_本位币
                BigDecimal interstSpreadBccy = ArithmeticUtil.doSub(ArithmeticUtil.doAdd(
									accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)
									//, accountAmountMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)),
                                    , accountAmountMap.get(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT)),
								 	accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT));
                if(interstSpreadBccy.compareTo(BigDecimal.ZERO) > 0) {
                	//利息差额（贷）_本位币  <- 变量.利息差额_本位币	
                	accountAmountMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, ArithmeticUtil.round(interstSpreadBccy, NumberEnum.MONEY.getCode(), null));
                }
                else {
                	//买卖价差（借）_本位币 <- 变量.买卖价差_本位币 * -1							
                	accountAmountMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, ArithmeticUtil.round(interstSpreadBccy.negate(), NumberEnum.MONEY.getCode(), null));
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
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                // 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 变量.应计利息
                qtyskxSubjectAmt = ArithmeticUtil.subtract(qtyskxSubjectAmt, varInterestAccBal);
                // 全局变量的更新
                // 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
                varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);
            }
            else if (DictCodeList.交易方向_还本到账.getValue().equals(dealNow.getPs())) {
                // ad.会计场景代码="债权类资产还本到账"
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_收益型资产还本.getValue());
                // accountAmountMap设置如下：

                // 费用处理共通
                DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(
                    dealNow.getValTradeFeeDetailList(),dealNow.getProdId(),dealNow.getPortfolioId());
                // 实付通道费 <-变量.交易各种费用.通道费
                accountAmountMap.put(BookConstant.CHLAMT,
                    ArithmeticUtil.round(dealTypesFeeDto.getChlFee(), NumberEnum.MONEY));
                accountCurrencyMap.put(BookConstant.CHLAMT, dealNow.getCcy());
                // 实付通道管理费 <- 变量.交易各种费用.通道管理费
                // 实付通道销售费 <- 变量.交易各种费用.通道销售费
                // 实付通道托管费 <- 变量.交易各种费用.通道托管费

                // 交易金额 <- 交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
                accountAmountMap.put(BookConstant.RECEITAMT,
                    ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));

                //基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 交易金额_本位币 <-	交易本金_本位币 + 实付通道费_本位币
                BigDecimal dealAmtBccy = ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.RECEITAMT + BookConstant.BCCYAMT)
													, accountAmountMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT));
                accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(dealAmtBccy, NumberEnum.MONEY.getCode(), null));
                /**add by taoran 20200103 end**/
                
                // accountCurrencyMap设置如下：
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
            }
            else if (DictCodeList.交易方向_应收款项转入.getValue().equals(dealNow.getPs())) {
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转入.getValue());
                //交易方向
                accountSceneMap.put(BookConstant.PS, DictCodeList.交易方向_应收款项转入.getValue());
                
                //定义 变量.其他应收款-利息科目余额                                            =   变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
                BigDecimal qtyskxSubjectAmt = varQtyskxSubjectAmt.get(
                    String.valueOf(dealNow.getBranch()));
                if (qtyskxSubjectAmt == null) {
                    // 查询交易的到期日对应的应计利息
                    SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
                    searchDao.setBooksetId(dealNow.getBooksetId());
                    searchDao.setBokDetailId(dealNow.getBokDetailId());
                    searchDao.setHappenDate(dealNow.getHappenDate());
                    searchDao.setBookDate(dealNow.getBookDate());
                    searchDao.setTaskPri(dealNow.getTaskPri());
                    searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                        Arrays.asList(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue())));
                    searchDao.setBranch(dealNow.getBranch());
                    List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                        searchDao, DictCodeList.余额方向_借方.getValue());
                    if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                        // 变量.应计利息余额 = 科目余额
                        qtyskxSubjectAmt = listSubjectBalance.get(0).getBalance();
                        varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()),
                            qtyskxSubjectAmt);
                    }
                }
                
                //应收款项-利息                   <-      交易明细.应计利息总额
                accountAmountMap.put(BookConstant.INTAMT,
                    ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));
                //逾期款项-本金                   <-      交易明细.交易本金
                accountAmountMap.put(BookConstant.COST,
                    ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
                //实收金额                    <-      交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
                //全局变量的更新       
                //变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 + 金额元素.应收款项-利息
                qtyskxSubjectAmt = ArithmeticUtil.doAdd(qtyskxSubjectAmt, dealNow.getIntAmt());
                //将变更后的余额更新到变量.其他应收款-利息科目余额MAP中   
                varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);

                //基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 实收金额_本位币 <-	应收款项-利息_本位币 + 逾期款项-本金_本位币
                BigDecimal dealAmtBccy = ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT)
													, accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT));
                accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(dealAmtBccy, NumberEnum.MONEY.getCode(), null));
                /**add by taoran 20200103 end**/
                
                // accountCurrencyMap设置如下：
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
            }
            else if (DictCodeList.交易方向_应收款项转出.getValue().equals(dealNow.getPs())) {
                // 会计场景
                ad.setTreeNode(acctEntity.getBookSetTreeNode());
                ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转出.getValue());
                //交易方向
                accountSceneMap.put(BookConstant.PS, DictCodeList.交易方向_应收款项转出.getValue());
                
                //定义 变量.其他应收款-利息科目余额                                            =   变量.其他应收款-利息科目余额MAP.get(交易明细.分支序号)
                BigDecimal qtyskxSubjectAmt = varQtyskxSubjectAmt.get(
                    String.valueOf(dealNow.getBranch()));
                if (qtyskxSubjectAmt == null) {
                    // 查询交易的到期日对应的应计利息
                    SubjectBalanceSearchDto searchDao = new SubjectBalanceSearchDto();
                    searchDao.setBooksetId(dealNow.getBooksetId());
                    searchDao.setBokDetailId(dealNow.getBokDetailId());
                    searchDao.setHappenDate(dealNow.getHappenDate());
                    searchDao.setBookDate(dealNow.getBookDate());
                    searchDao.setTaskPri(dealNow.getTaskPri());
                    searchDao.setBaseSubjectNatureList(new ArrayList<String>(
                        Arrays.asList(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue())));
                    searchDao.setBranch(dealNow.getBranch());
                    List<SubjectBalance> listSubjectBalance = accountingCommonService.getSubjectBalanceExpectDirection(
                        searchDao, DictCodeList.余额方向_借方.getValue());
                    if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
                        // 变量.应计利息余额 = 科目余额
                        qtyskxSubjectAmt = listSubjectBalance.get(0).getBalance();
                        varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()),
                            qtyskxSubjectAmt);
                    }
                }
                
                //定义        变量.应计利息
                BigDecimal varYjlx = BigDecimal.ZERO;
                //定义      变量.轧差金额
                BigDecimal varZcje = BigDecimal.ZERO;
                //if（交易明细.是否结清 = "否"）
                if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
                    //变量.应计利息                   <-      交易明细.应计利息总额
                    varYjlx = dealNow.getIntAmt();
                    //变量.轧差金额                 <-      0
                    varZcje = BigDecimal.ZERO;
                } else {
                    //变量.应计利息                   <-      变量.其他应收款-利息科目余额
                    varYjlx = qtyskxSubjectAmt;
                    //变量.轧差金额 = 交易明细.应计利息总额 - 变量.应计利息                         
                    varZcje = ArithmeticUtil.subtract(dealNow.getIntAmt(), varYjlx);
                    //if  变量.轧差金额 >= 0                      
                    if (varZcje.compareTo(BigDecimal.ZERO) >= 0) {
                        //轧差金额(贷)                   <-      变量.利息差额
                        accountAmountMap.put(BookConstant.NETAMT_C,
                            ArithmeticUtil.round(varZcje, NumberEnum.MONEY));
                    } else {
                        //轧差金额(借)                   <-      变量.利息差额*-1
                        accountAmountMap.put(BookConstant.NETAMT_D,
                            ArithmeticUtil.round(varZcje.negate(), NumberEnum.MONEY));
                    }
                }

                //应收款项-利息                   <-      交易明细.应计利息总额
                accountAmountMap.put(BookConstant.INTAMT,
                    ArithmeticUtil.round(varYjlx, NumberEnum.MONEY));
                //逾期款项-本金                   <-      交易明细.交易本金
                accountAmountMap.put(BookConstant.COST,
                    ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
                //实收金额                    <-      交易明细.交易金额
                accountAmountMap.put(BookConstant.DEALAMT,
                    ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
                //全局变量的更新       
                //变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 + 金额元素.应收款项-利息
                qtyskxSubjectAmt = ArithmeticUtil.subtract(qtyskxSubjectAmt, dealNow.getIntAmt());
                //将变更后的余额更新到变量.其他应收款-利息科目余额MAP中   
                varQtyskxSubjectAmt.put(String.valueOf(dealNow.getBranch()), qtyskxSubjectAmt);

                //基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                
                /**add by taoran 20200103 start**/
                // 定义 本币金额元素Map
                Map<String, BigDecimal> bccyAmountMap = new HashMap<>();
                for(String key : accountAmountMap.keySet()){
                	bccyAmountMap.put(key + BookConstant.BCCYAMT, calculationCommonService.doMultiply(NumberEnum.MONEY.getCode(), null, accountAmountMap.get(key), changeRate));
                }
                accountAmountMap.putAll(bccyAmountMap);
                //定义 变量.轧差金额_本位币    = 	实收金额_本位币 - (应收款项-利息_本位币 + 逾期款项-本金)	
                BigDecimal varZcjeBccy = ArithmeticUtil.doSub(accountAmountMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT), 
                							ArithmeticUtil.doAdd(accountAmountMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT), 
                									accountAmountMap.get(BookConstant.COST + BookConstant.BCCYAMT)));
                if(varZcjeBccy.compareTo(BigDecimal.ZERO)>0) {
                	//	轧差金额（贷）_本位币 <-	变量.轧差金额_本位币	
                	accountAmountMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, ArithmeticUtil.round(varZcjeBccy, NumberEnum.MONEY.getCode(), null));
                }
                else {
                	//轧差金额（借）_本位币    <-	变量.轧差金额_本位币 * -1	
                	accountAmountMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT, ArithmeticUtil.round(varZcjeBccy.negate(), NumberEnum.MONEY.getCode(), null));
                }
                /**add by taoran 20200103 end**/
                
                // accountCurrencyMap设置如下：
                for (String key : accountAmountMap.keySet()) {
                    accountCurrencyMap.put(key, dealNow.getCcy());
                }
                /**add by taoran 20200103 start**/
                accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
                /**add by taoran 20200103 end**/
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
