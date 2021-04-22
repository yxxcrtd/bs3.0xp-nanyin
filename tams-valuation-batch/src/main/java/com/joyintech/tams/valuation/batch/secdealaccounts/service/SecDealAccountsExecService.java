/*
 * 文件名：SecDealAccountsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.secdealaccounts.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.batch.common.accounting.entity.LossAmtInputDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accounting.service.LossCommonBatchService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.batch.common.sect1handle.entity.SecT1HandleParamterDto;
import com.joyintech.tams.valuation.batch.secdealaccounts.dto.DealParamterDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.ValExecFeeDiffDto;
import com.joyintech.tams.valuation.common.batch.losscommon.service.LossCommonService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券交易账务处理(回售、赎回确认交易)
 * @author yanya
 * @version 1.0 2021年03月16日
 * @see SecDealAccountsExecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecDealAccountsExecService {

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
     * 标的共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;
    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    /**
     * 计算类共通
     */
    @Autowired
    private CalculationCommonService calculationCommonService;
    /**
     * 资产减值
     */
    @Autowired
    private LossCommonService lossCommonService;
    @Autowired
    private LossCommonBatchService lossCommonBatchService;


    /**
     * 公允价值类的情况场景处理(行权回售/行权赎回)
     * @param rstBaseinfo 标的信息
     * @param returnResult 返回结果
     * @param dealNow 交易信息
     * @param acctEntity 合并交易信息
     * @param param 参数
     * @param paramT1 t+1参数
     * @param varSecDealFeeSubject 费用损益计入方式
     * @param tableId 场景
     */
    public void createFvtplExec(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                                 FinProductDealInfoComDTO dealNow,
                                 FinProductDealInfoResult acctEntity, DealParamterDto param,
                                 SecT1HandleParamterDto paramT1, String varSecDealFeeSubject,
                                 String tableId) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();
        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);

        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
                "公允价值类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);

        // 定义 变量.买卖价差 = 0
        BigDecimal varMmjc = BigDecimal.ZERO;

        //调用【交易信息查询共通方法设计-行权交易差额】
        ValExecFeeDiffDto valExecFeeDiffDto = finProductCommonService.calculationFeeDetailDiff(acctEntity.getBooksetId(), acctEntity.getBookDate(),
                dealNow.getBokDetailId(), rstBaseinfo, dealNow);

        //金额元素.实收金额								<-		变量.行权差额.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(valExecFeeDiffDto.getTradeAmtDiff(), NumberEnum.MONEY));
        //if(变量.行权差额.利息差额 >= 0)
        if(valExecFeeDiffDto.getIntAmtDiff().compareTo(BigDecimal.ZERO) >= 0) {
            //金额元素.利息差额（贷）							<-	变量.行权差额.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(valExecFeeDiffDto.getIntAmtDiff(), NumberEnum.MONEY));
        }
        else {
            //金额元素.利息差额（借）							<-	变量.行权差额.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(valExecFeeDiffDto.getIntAmtDiff(), new BigDecimal(-1)), NumberEnum.MONEY));
        }
        //调用赋值方法
        varMmjc = this.createExecFeeAmount(dealNow.getPs(), tableId, accountAmountMap, valExecFeeDiffDto, varMmjc);
        // 变量.费用损益计入方式 = '计入投资收益'情况
        if (DictCodeList.债券交易费用损益计入方式_计入投资收益.getValue().equals(varSecDealFeeSubject)) {
            // 变量.买卖价差 <- 0
            varMmjc = BigDecimal.ZERO;
        }
        else {
            //调用赋值方法
            varMmjc = this.createExecFeeOutAmount(dealNow.getPs(), tableId, accountAmountMap, valExecFeeDiffDto, varMmjc);
        }
        //调用账务明细代码赋值方法
        this.createExecFeeScene(accountSceneMap, valExecFeeDiffDto);
        //变量.行权差额.交易各种费用.不随交易费、通道费已在之前计算了
        //变量.买卖价差 =	变量.买卖价差 + 变量.行权差额.交易金额- 变量.行权差额.交易各种费用.不随交易费用 + 变量.行权差额.交易各种费用.通道费 - 变量.行权差额.利息差额
        varMmjc =ArithmeticUtil.doSub(
                ArithmeticUtil.doAdd(varMmjc, valExecFeeDiffDto.getTradeAmtDiff()),
                valExecFeeDiffDto.getIntAmtDiff()
        );
        // 变量.买卖价差>= 0
        if (varMmjc.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C, ArithmeticUtil.round(varMmjc, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D, ArithmeticUtil.round(ArithmeticUtil.multiply(varMmjc, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        /** modify by yanya on 20210316 债券行权改造 end**/
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }

        /** modify by yanya on 20191231 增加本位币处理 start**/
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        BigDecimal 汇率 = this.getCurrencyMarket(acctEntity, dealNow);
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义 变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(valExecFeeDiffDto.getIntAmtDiff(), NumberEnum.MONEY), 汇率), NumberEnum.MONEY);
        //定义 变量.公允价值变动(贷)_本位币 = round(变量.公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 公允价值变动贷本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(cjGyJzBd, 汇率), NumberEnum.MONEY);
        //定义 变量.变量.冲减公允价值变动（借)_本位币 = round(变量.变量.冲减公允价值变动,共通金额小数位) * 变量.汇率
//        BigDecimal 冲减公允价值变动借本位币 = ArithmeticUtil.round(ArithmeticUtil.multiply(cjGyJzBd, 汇率), NumberEnum.MONEY);

        /**定义 变量.买卖价差_本位币 =	(交易金额_本位币 + 变量.变量.冲减公允价值变动（借)_本位币 + 实付通道费_本位币 + 结算手续费支出 + 印花税支出_本位币
         + 经手费支出_本位币 + 证管费支出_本位币 + 结算费支出_本位币 + 佣金支出_本位币 + 交易手续费支出_本位币)
         - 	(成本金额_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
         + 结算手续费_本位币 + 变量.公允价值变动(贷)_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT+ BookConstant.BCCYAMT),//交易金额_本位币
                this.calcAddFeeOutAmount(dealNow.getPs(), tableId, accountAmountBCCYAMTMap, valExecFeeDiffDto)
        );//交易手续费支出_本位币
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.FACEVALUE + BookConstant.BCCYAMT),//成本金额_本位币
                this.calcAddFeeAmount(dealNow.getPs(), tableId, accountAmountBCCYAMTMap, valExecFeeDiffDto),
                利息差额贷本位币);//变量.利息差额(贷)_本位币); //利息金额_本位币
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币 <- 变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
        }
        else {
            //买卖价差（贷）_本位币<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

    }

    /**
     * 摊余成本类的情况场景处理(行权回售/行权赎回)
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易信息
     * @param acctEntity 合并信息
     * @param param 参数
     * @param paramT1 t+1参数
     * @param tableId 场景
     * @param offsetImptDateType 还本收息冲抵减值准备处理日
     */
    public void createACExec(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                              FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param,
                              SecT1HandleParamterDto paramT1, String tableId, String offsetImptDateType) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);
        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        //定义 		变量.买卖价差		=		0
        BigDecimal 买卖价差 = BigDecimal.ZERO;
        //调用【交易信息查询共通方法设计-行权交易差额】
        ValExecFeeDiffDto valExecFeeDiffDto = finProductCommonService.calculationFeeDetailDiff(acctEntity.getBooksetId(), acctEntity.getBookDate(),
                dealNow.getBokDetailId(), rstBaseinfo, dealNow);
        //变量.利息差额 = 变量.行权差额.利息差额
        BigDecimal 利息差额 = valExecFeeDiffDto.getIntAmtDiff();
        BigDecimal 冲抵减值金额 = BigDecimal.ZERO;
        //IF 数据字典：还本收息冲抵减值准备处理日.资金到账日 eq 变量.还本收息冲抵减值准备处理日
        if(DictCodeListExt3.还本收息冲抵减值准备处理日_资金到账日.getValue().equals(offsetImptDateType)){
            LossAmtInputDto dto = this.getLossAmtInputDto(acctEntity, dealNow,
                    DictCodeListExt1.现金流一级类型_利息.getValue(),
                    dealNow.getTradeAmt(),
                    //减值准备余额
                    param.getVarJzzbSubjectAmt(),
                    //当前EAD金额 = 变量.成本科目余额 + 变量.应计利息科目余额 + 变量.其他应收款本金汇总余额 + 变量.其他应收款利息汇总月 + 变量.逾期利息汇总余额
                    ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
                            param.getVarLxSubjectAmt(),
                            param.getVarQtyskBjSubjectSumAmt(),
                            param.getVarQtyskLxSubjectSumAmt(),
                            param.getVarYqfxSubjectSumAmt()),
                    //应收款余额
                    param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch())));
            冲抵减值金额 = lossCommonBatchService.calcLossAmtForDur(dto);
        }
        //金额元素.实收金额								<-		变量.行权差额.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(valExecFeeDiffDto.getTradeAmtDiff(), NumberEnum.MONEY));
        //调用赋值方法
        买卖价差 = this.createExecFeeAmount(dealNow.getPs(), tableId, accountAmountMap, valExecFeeDiffDto, 买卖价差);
        //变量.买卖价差=		变量.行权差额.交易金额 -  变量.行权差额.交易各种费用.不随交易费用 + 变量.行权差额.交易各种费用.通道费 - 变量.利息差额
        买卖价差 =ArithmeticUtil.doSub(
                ArithmeticUtil.doAdd(买卖价差, valExecFeeDiffDto.getTradeAmtDiff()),
                利息差额
        );
        //减值准备  <- 变量.冲抵减值金额 * -1
        accountAmountMap.put(BookConstant.IMPAMT,
                ArithmeticUtil.round(ArithmeticUtil.multiply(冲抵减值金额,new BigDecimal(-1)), NumberEnum.MONEY));
        //if(变量.利息差额 >= 0)
        if(利息差额.compareTo(BigDecimal.ZERO) >= 0) {
            //金额元素.利息差额（贷）							<-	变量.行权差额.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(利息差额, NumberEnum.MONEY));
        }
        else {
            //金额元素.利息差额（借）							<-	变量.行权差额.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(利息差额, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        // 变量.买卖价差>= 0
        if (买卖价差.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C, ArithmeticUtil.round(买卖价差, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(买卖价差, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        //调用账务明细代码赋值方法
        this.createExecFeeScene(accountSceneMap, valExecFeeDiffDto);
        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = this.getCurrencyMarket(acctEntity, dealNow);
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(利息差额, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币	 =	(实收金额_本位币 + 实付通道费_本位币)
         - 	(面值_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
         + 结算手续费_本位币 + 冲减溢折价（贷）_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)//实收金额_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                this.calcAddFeeAmount(dealNow.getPs(), tableId, accountAmountBCCYAMTMap, valExecFeeDiffDto),//费用
                利息差额贷本位币
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 变量.减值准备科目余额 = 变量.减值准备科目余额 - 变量.减值使用
        param.setVarJzzbSubjectAmt(ArithmeticUtil.subtract(param.getVarJzzbSubjectAmt(), 冲抵减值金额));

        // if（金额元素.减值准备 ！= 0）
        if (冲抵减值金额.compareTo(BigDecimal.ZERO) != 0) {
            BokStatLossPosiiton statLoss = null;
            for (ValBokBookdetailBatch detail : resultNowNow.getValBokBookdetailBatch()) {
                // 变量.账务明细 = 调用账务明细生成共通返回结果中 基础科目性质 ="摊余成本类资产减值"的第一条数据
                if (DictCodeBaseSubjectNatrue.摊余成本类资产减值.getValue().equals(
                        detail.getBaseSubjectNature())) {
                    // 调用【减值相关共通-减值使用明细数据生成】
                    statLoss = lossCommonService.setLossInfoUsed(detail, 冲抵减值金额.negate(),
                            accountAmountBCCYAMTMap.get(BookConstant.IMPAMT + BookConstant.BCCYAMT),
                            param.getVarJzzbSubjectAmt(),
                            calculationCommonService.doMultiply(param.getVarJzzbSubjectAmt(), 汇率),
                            dealNow.getTradeId());
                    break;
                }
            }
            if (statLoss != null) {
                // 变量.统计分析明细表（资产减值）减值使用列表.add(上述返回值)
                returnResult.getBokStatLossPosiitonList().add(statLoss);
            }
        }

    }

    /**
     * 综合收益情况的情况场景处理(行权回售/行权赎回)
     * @param rstBaseinfo 标的信息
     * @param returnResult 场景返回结果
     * @param dealNow 交易信息
     * @param acctEntity 合并信息
     * @param param 参数
     * @param paramT1 t+1参数
     * @param tableId 场景
     * @param offsetImptDateType 还本收息冲抵减值准备处理日
     */
    public void createFvociExec(TargetFinProduct rstBaseinfo, BookGenerateResult returnResult,
                             FinProductDealInfoComDTO dealNow, FinProductDealInfoResult acctEntity, DealParamterDto param,
                             SecT1HandleParamterDto paramT1, String tableId,String offsetImptDateType) {
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = new HashMap<>();
        accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = new HashMap<>();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = new HashMap<>();

        // 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        ad.setTableId(tableId);
        // accountSceneMap设置
        // 备注
        accountSceneMap.put(BookConstant.BOOK_REMARK,
                "摊余成本类债券资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");

        // 公共场景赋值
        getAccountSceneMap(accountSceneMap, dealNow, rstBaseinfo);
        //定义 		变量.买卖价差		=		0
        BigDecimal 买卖价差 = BigDecimal.ZERO;
        //调用【交易信息查询共通方法设计-行权交易差额】
        ValExecFeeDiffDto valExecFeeDiffDto = finProductCommonService.calculationFeeDetailDiff(acctEntity.getBooksetId(), acctEntity.getBookDate(),
                dealNow.getBokDetailId(), rstBaseinfo, dealNow);
        //变量.利息差额 = 变量.行权差额.利息差额
        BigDecimal 利息差额 = valExecFeeDiffDto.getIntAmtDiff();
        BigDecimal 冲抵减值金额 = BigDecimal.ZERO;
        //IF 数据字典：还本收息冲抵减值准备处理日.资金到账日 eq 变量.还本收息冲抵减值准备处理日
        if(DictCodeListExt3.还本收息冲抵减值准备处理日_资金到账日.getValue().equals(offsetImptDateType)){
            LossAmtInputDto dto = this.getLossAmtInputDto(acctEntity, dealNow,
                    DictCodeListExt1.现金流一级类型_利息.getValue(),
                    dealNow.getTradeAmt(),
                    //减值准备余额
                    param.getVarJzzbSubjectAmt(),
                    //当前EAD金额 = 变量.成本科目余额 + 变量.应计利息科目余额 + 变量.其他应收款本金汇总余额 + 变量.其他应收款利息汇总月 + 变量.逾期利息汇总余额
                    ArithmeticUtil.doAdd(param.getVarCostSubjectAmt(),
                            param.getVarLxSubjectAmt(),
                            param.getVarQtyskBjSubjectSumAmt(),
                            param.getVarQtyskLxSubjectSumAmt(),
                            param.getVarYqfxSubjectSumAmt()),
                    //应收款余额
                    param.getVarQtyskxSubjectAmt().get(String.valueOf(dealNow.getBranch())));
            冲抵减值金额 = lossCommonBatchService.calcLossAmtForDur(dto);
        }
        //金额元素.实收金额								<-		变量.行权差额.交易金额
        accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(valExecFeeDiffDto.getTradeAmtDiff(), NumberEnum.MONEY));
        //调用赋值方法
        买卖价差 = this.createExecFeeAmount(dealNow.getPs(), tableId, accountAmountMap, valExecFeeDiffDto, 买卖价差);
        //变量.买卖价差=		变量.行权差额.交易金额 -  变量.行权差额.交易各种费用.不随交易费用 + 变量.行权差额.交易各种费用.通道费 - 变量.利息差额
        买卖价差 =ArithmeticUtil.doSub(
                ArithmeticUtil.doAdd(买卖价差, valExecFeeDiffDto.getTradeAmtDiff()),
                利息差额
        );
        //减值准备  <- 变量.冲抵减值金额 * -1
        accountAmountMap.put(BookConstant.IMPAMT,
                ArithmeticUtil.round(ArithmeticUtil.multiply(冲抵减值金额,new BigDecimal(-1)), NumberEnum.MONEY));
        //if(变量.利息差额 >= 0)
        if(利息差额.compareTo(BigDecimal.ZERO) >= 0) {
            //金额元素.利息差额（贷）							<-	变量.行权差额.利息差额
            accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(利息差额, NumberEnum.MONEY));
        }
        else {
            //金额元素.利息差额（借）							<-	变量.行权差额.利息差额*-1
            accountAmountMap.put(BookConstant.INCOMEAMT_D,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(利息差额, new BigDecimal(-1)), NumberEnum.MONEY));
        }
        // 变量.买卖价差>= 0
        if (买卖价差.compareTo(BigDecimal.ZERO) >= 0) {
            // 买卖价差（贷） <-变量.买卖价差
            accountAmountMap.put(BookConstant.DSCLOSS_C, ArithmeticUtil.round(买卖价差, NumberEnum.MONEY));
        }
        else {
            // 买卖价差（借） <-变量.买卖价差*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D,
                    ArithmeticUtil.round(ArithmeticUtil.multiply(买卖价差, new BigDecimal(-1)), NumberEnum.MONEY));
        }

        //调用账务明细代码赋值方法
        this.createExecFeeScene(accountSceneMap, valExecFeeDiffDto);

        // 因为与记账金额元素map的key及数量一致，故采用循环
        for (String keyNow : accountAmountMap.keySet()) {
            accountCurrencyMap.put(keyNow, dealNow.getCcy());// value值塞入当前交易币种
        }
        // 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
        BigDecimal 汇率 = this.getCurrencyMarket(acctEntity, dealNow);
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        //本位币金额元素赋值 循环金额元素
        for (String key : accountAmountMap.keySet()) {
            accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率),NumberEnum.MONEY));
        }
        /** 本位币 币种**/
        accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
        //记账金额元素 putALl 随记账本位币金额map
        accountAmountMap.putAll(accountAmountBCCYAMTMap);
        //accountAmountMap再次设置如下：
        //定义		变量.利息差额(贷)_本位币 = round(变量.利息差额,共通金额小数位) * 变量.汇率
        BigDecimal 利息差额贷本位币 = ArithmeticUtil.round(
                ArithmeticUtil.multiply(ArithmeticUtil.round(利息差额, NumberEnum.MONEY), 汇率),
                NumberEnum.MONEY);
        /**定义 变量.买卖价差_本位币	 =	(实收金额_本位币 + 实付通道费_本位币)
         - 	(面值_本位币 + 印花税_本位币 + 经手费_本位币 + 证管费_本位币 + 结算费_本位币 + 佣金_本位币 + 交易手续费_本位币
         + 结算手续费_本位币 + 冲减溢折价（贷）_本位币 + 变量.利息差额(贷)_本位币 + 利息金额_本位币) **/
        BigDecimal 买卖价差本位币1 = ArithmeticUtil.doAdd(
                accountAmountBCCYAMTMap.get(BookConstant.DEALAMT + BookConstant.BCCYAMT)//实收金额_本位币
        );
        BigDecimal 买卖价差本位币2 = ArithmeticUtil.doAdd(
                this.calcAddFeeAmount(dealNow.getPs(), tableId, accountAmountBCCYAMTMap, valExecFeeDiffDto),//费用
                利息差额贷本位币
        );
        BigDecimal 买卖价差本位币 = ArithmeticUtil.doSub(买卖价差本位币1, 买卖价差本位币2);
        //if（变量.买卖价差_本位币>= 0）
        if(买卖价差本位币.compareTo(BigDecimal.ZERO) >= 0) {
            //买卖价差（贷）_本位币	<-	变量.买卖价差_本位币
            accountAmountMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT,
                    ArithmeticUtil.round(买卖价差本位币, NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_C + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        else {
            //买卖价差（借）_本位币	<-	变量.买卖价差_本位币*-1
            accountAmountMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, ArithmeticUtil.round(
                    ArithmeticUtil.multiply(买卖价差本位币, new BigDecimal(-1)), NumberEnum.MONEY));
            accountCurrencyMap.put(BookConstant.DSCLOSS_D + BookConstant.BCCYAMT, standardCcyNow);// value值塞入当前交易币种
        }
        /** modify by yanya on 20200102 增加本位币处理 end**/

        // map塞入场景要素管理中
        // 记账场景要素
        ad.setAccountSceneMap(accountSceneMap);
        // 记账金额要素
        ad.setAccountAmountMap(accountAmountMap);
        // 记账金额币种要素
        ad.setAccountCurrencyMap(accountCurrencyMap);

        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

        // 变量.减值准备科目余额 = 变量.减值准备科目余额 - 变量.减值使用
        param.setVarJzzbSubjectAmt(ArithmeticUtil.subtract(param.getVarJzzbSubjectAmt(), 冲抵减值金额));
        // if（金额元素.减值准备 ！= 0）
        if (冲抵减值金额.compareTo(BigDecimal.ZERO) != 0) {
            BokStatLossPosiiton statLoss = null;
            for (ValBokBookdetailBatch detail : resultNowNow.getValBokBookdetailBatch()) {
                // 变量.账务明细 = 调用账务明细生成共通返回结果中 基础科目性质 ="摊余成本类资产减值"的第一条数据
                if (DictCodeBaseSubjectNatrue.其它综合收益_减值.getValue().equals(
                        detail.getBaseSubjectNature())) {
                    // 调用【减值相关共通-减值使用明细数据生成】
                    statLoss = lossCommonService.setLossInfoUsed(detail, 冲抵减值金额.negate(),
                            accountAmountBCCYAMTMap.get(BookConstant.IMPAMT + BookConstant.BCCYAMT),
                            param.getVarJzzbSubjectAmt(),
                            calculationCommonService.doMultiply(param.getVarJzzbSubjectAmt(), 汇率),
                            dealNow.getTradeId());
                    break;
                }
            }
            if (statLoss != null) {
                // 变量.统计分析明细表（资产减值）减值使用列表.add(上述返回值)
                returnResult.getBokStatLossPosiitonList().add(statLoss);
            }
        }

    }


    /**
     * 公共场景赋值
     *
     * @param accountSceneMap 场景要素 map
     * @param dealNow 金融产品交易信息
     * @param rstBaseinfo 标的信息
     * @see
     * @since 1.0
     */
    private void getAccountSceneMap(Map<String, Object> accountSceneMap,
                                    FinProductDealInfoComDTO dealNow,
                                    TargetFinProduct rstBaseinfo) {
        // 境内外
        accountSceneMap.put(BookConstant.BLN_AREA, rstBaseinfo.getBlnArea());
        // 分类1
        accountSceneMap.put(BookConstant.TYPE_1, rstBaseinfo.getType1());
        // 分类2
        accountSceneMap.put(BookConstant.TYPE_2, rstBaseinfo.getType2());
        // 分类3
        accountSceneMap.put(BookConstant.TYPE_3, rstBaseinfo.getType3());
        // 分类4
        accountSceneMap.put(BookConstant.TYPE_4, rstBaseinfo.getType4());
        // 分类5
        accountSceneMap.put(BookConstant.TYPE_5, rstBaseinfo.getType5());
        // 分类6
        accountSceneMap.put(BookConstant.TYPE_6, rstBaseinfo.getType6());
        // 交易编号
        accountSceneMap.put(BookConstant.TRADE_ID, dealNow.getTradeId());

        // 冲正标识
        accountSceneMap.put(BookConstant.OFFSET_FLAG, DictCodeList.冲正标识_正常交易.getValue());

        if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_申购确认.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())
                || DictCodeListExt1.交易方向_债转股.getValue().equals(dealNow.getPs())
                // ADD BY CAOGUO ON 20201016 减值相关修改
                || DictCodeListExt3.交易方向_清理核销.getValue().equals(dealNow.getPs())) {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
                    ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.QTY));
        }
        else {
            // 发生数量->交易明细.交易份额
            accountSceneMap.put(BookConstant.HAPPEN_NUMBER, BigDecimal.ZERO);
        }
        if ((DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
                && !DictCodeListExt1.交易市场_一级市场.getValue().equals(dealNow.getTradeMarketMode()))
                || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())) {
            if (DictCodeListExt1.估值交易市场_银行间.getValue().equals(dealNow.getTradeMarket())) {
                accountSceneMap.put(BookConstant.ONWAY_DATE, dealNow.getTradeDate().compareTo(
                        dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate());
            }
            /** modify by yanya else if（交易明细.交易场所 = "上交所" OR 交易明细.交易场所 = "深交所"）**/
            else if(DictCodeListExt1.估值交易市场_上交所.getValue().equals(dealNow.getTradeMarket())
                    || DictCodeListExt1.估值交易市场_深交所.getValue().equals(dealNow.getTradeMarket())) {
                //在途日期				<-		交易明细.交易日 + 1
                accountSceneMap.put(BookConstant.ONWAY_DATE, DateUtil.addDay(dealNow.getTradeDate(), 1));
            }
            else {
                //accountSceneMap.put(BookConstant.ONWAY_DATE, DateUtils.addDays(dealNow.getTradeDate(), 1));
                /** modify by yanya 在途日期	<-	交易明细.交易日 == 交割日？Null：交割日	**/
                accountSceneMap.put(BookConstant.ONWAY_DATE, dealNow.getTradeDate().compareTo(
                        dealNow.getSettleDate()) == 0 ? null : dealNow.getSettleDate());
            }
        }

        if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_申购.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_行权_回售.getValue().equals(dealNow.getPs())
                || DictCodeList.交易方向_行权_赎回.getValue().equals(dealNow.getPs())) {
            // 登记托管机构 <- 变量.标的信息.登记托管机构
            accountSceneMap.put(BookConstant.REGIST_ORG, rstBaseinfo.getRegistOrg());
        }
    }

    /**
     * 冲减减值计算DTO初始化
     * @param acctEntity
     * @param dealNow
     * @return
     */
    private LossAmtInputDto getLossAmtInputDto(FinProductDealInfoResult acctEntity,FinProductDealInfoComDTO dealNow,
                                               String flag,//01 本金  02 利息
                                               BigDecimal happenAmt,//发生金额
                                               BigDecimal lossBalAmt,//减值准备余额
                                               BigDecimal EADAmt, //当前EAD金额
                                               BigDecimal receAmt //应收款项余额
    ){
        LossAmtInputDto dto = new LossAmtInputDto();
        //本息标识
        dto.setFlag(flag);
        //账套代码
        dto.setBooksetId(acctEntity.getBooksetId());
        //账务明细代码
        dto.setBokDetailId(acctEntity.getBokDetailId());
        //批处理优先级
        dto.setTaskPri(dealNow.getTaskPri());
        //分支序号
        dto.setBranch(dealNow.getBranch());
        //发生日
        dto.setHappenDate(acctEntity.getHappenDate());
        //记账日
        dto.setBookDate(acctEntity.getBookDate());
        //发生金额
        dto.setHappenAmt(ArithmeticUtil.round(happenAmt, NumberEnum.MONEY));
        //减值准备余额
        dto.setLossBalAmt(lossBalAmt);
        //当前EAD金额 = 变量.成本科目余额 + 变量.应计利息科目余额  + 变量.其他应收款本金汇总余额 + 变量.其他应收款利息汇总月 + 变量.逾期利息汇总余额
        dto.setEADAmt(EADAmt);
        //应收款余额
        dto.setReceAmt(receAmt);
        //业务类型
        dto.setBusiType(dealNow.getFinprodType());
        return dto;
    }

    /**
     * 判断金额数据与0比较
     * @param amt
     * @return
     */
    private int judgeAmtZero(BigDecimal amt) {
        if(amt == null) {
            return -1;
        }
        else {
            return amt.compareTo(BigDecimal.ZERO);
        }
    }

    /**
     * 格式化金额元素赋值时的金额
     * @param amt
     * @return
     */
    private BigDecimal fomartAmountAmt(BigDecimal amt) {
        if(amt == null) {
            return amt;
        }
        else {
            return ArithmeticUtil.round(amt, NumberEnum.MONEY);
        }
    }

    /**
     * 金额乘以负值，并格式
     * @param amt
     * @return
     */
    private BigDecimal fomartAmountAmtNegation(BigDecimal amt) {
        if(amt == null) {
            return amt;
        }
        return ArithmeticUtil.round(ArithmeticUtil.multiply(amt, new BigDecimal(-1)), NumberEnum.MONEY);
    }

    /**
     * 获取汇率
     * @param acctEntity
     * @param dealNow
     * @return
     */
    private BigDecimal getCurrencyMarket(FinProductDealInfoResult acctEntity, FinProductDealInfoComDTO dealNow) {
        BigDecimal 汇率 = BigDecimal.ONE;
        String standardCcyNow = acctEntity.getStandardCcy();//本位币
        if(!dealNow.getCcy().equals(acctEntity.getStandardCcy())) {
            //调用计算类	方法共通【汇率查询】
            //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
            List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(acctEntity.getBookDate(), acctEntity.getBooksetId(),
                    standardCcyNow, dealNow.getCcy());
            if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                汇率 = currencyMarketList.get(0).getClosePrice();
            }
            /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
        }
        return 汇率;
    }
    /**
     * 行权的费用金额元素赋值
     * @param ps
     * @param tableId
     * @param accountAmountMap
     * @param valExecFeeDiffDto
     * @param varMmjc
     */
    private BigDecimal createExecFeeAmount(String ps, String tableId, Map<String, BigDecimal> accountAmountMap,
                                     ValExecFeeDiffDto valExecFeeDiffDto, BigDecimal varMmjc) {
        if(StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)) {
            // 印花税
            if(this.judgeAmtZero(valExecFeeDiffDto.getStampDutyDiff()) >= 0) {
                accountAmountMap.put(BookConstant.STAMPTAX_C, fomartAmountAmt(valExecFeeDiffDto.getStampDutyDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.STAMPTAX_D, fomartAmountAmtNegation(valExecFeeDiffDto.getStampDutyDiff()));
            }
            // 经手费
            if(this.judgeAmtZero(valExecFeeDiffDto.getBrokerageDiff()) >= 0) {
                accountAmountMap.put(BookConstant.HANDFEE_C, this.fomartAmountAmt(valExecFeeDiffDto.getBrokerageDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.HANDFEE_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getBrokerageDiff()));
            }
            // 证管费
            if(this.judgeAmtZero(valExecFeeDiffDto.getCertificateFeeDiff()) >= 0) {
                accountAmountMap.put(BookConstant.CERTIFEE_C, this.fomartAmountAmt(valExecFeeDiffDto.getCertificateFeeDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.CERTIFEE_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getCertificateFeeDiff()));
            }
            // 结算费
            if(this.judgeAmtZero(valExecFeeDiffDto.getClearingFeeDiff()) >= 0) {
                accountAmountMap.put(BookConstant.SETTLEFEE_C, this.fomartAmountAmt(valExecFeeDiffDto.getClearingFeeDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.SETTLEFEE_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getClearingFeeDiff()));
            }
            // 佣金
            if(this.judgeAmtZero(valExecFeeDiffDto.getCommissionDiff()) >= 0) {
                accountAmountMap.put(BookConstant.COMMISSION_C, this.fomartAmountAmt(valExecFeeDiffDto.getCommissionDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.COMMISSION_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getCommissionDiff()));
            }
            // 交易手续费
            if(this.judgeAmtZero(valExecFeeDiffDto.getTransactionFeeDiff()) >= 0) {
                accountAmountMap.put(BookConstant.TRADEFEE_C, this.fomartAmountAmt(valExecFeeDiffDto.getTransactionFeeDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.TRADEFEE_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getTransactionFeeDiff()));
            }
            // 结算手续费
            if(this.judgeAmtZero(valExecFeeDiffDto.getSettlementFeeDiff()) >= 0) {
                accountAmountMap.put(BookConstant.SETTLEMENTFEE_C, this.fomartAmountAmt(valExecFeeDiffDto.getSettlementFeeDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.SETTLEMENTFEE_D, this.fomartAmountAmtNegation(valExecFeeDiffDto.getSettlementFeeDiff()));
            }
            //通道费
            if(this.judgeAmtZero(valExecFeeDiffDto.getChlFeeDiff()) >= 0) {
                accountAmountMap.put(BookConstant.CHLAMT_D, this.fomartAmountAmt(valExecFeeDiffDto.getChlFeeDiff()));
            }
            else {
                accountAmountMap.put(BookConstant.CHLAMT_C, this.fomartAmountAmtNegation(valExecFeeDiffDto.getChlFeeDiff()));
            }
//            // 交易费用 <- 变量.交易各种费用.交易费用
//            accountAmountMap.put(BookConstant.FEE, this.fomartAmountAmt(valExecFeeDiffDto.getFeeAmtDiff()));
        }
        else if(StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps)) {
            // 印花税
            accountAmountMap.put(BookConstant.STAMPTAX, this.fomartAmountAmt(valExecFeeDiffDto.getStampDutyDiff()));
            // 经手费
            accountAmountMap.put(BookConstant.HANDFEE, this.fomartAmountAmt(valExecFeeDiffDto.getBrokerageDiff()));
            // 证管费
            accountAmountMap.put(BookConstant.CERTIFEE, this.fomartAmountAmt(valExecFeeDiffDto.getCertificateFeeDiff()));
            // 结算费
            accountAmountMap.put(BookConstant.SETTLEFEE, this.fomartAmountAmt(valExecFeeDiffDto.getClearingFeeDiff()));
            // 佣金
            accountAmountMap.put(BookConstant.COMMISSION, this.fomartAmountAmt(valExecFeeDiffDto.getCommissionDiff()));
            // 交易手续费
            accountAmountMap.put(BookConstant.TRADEFEE, this.fomartAmountAmt(valExecFeeDiffDto.getTransactionFeeDiff()));
            // 结算手续费
            accountAmountMap.put(BookConstant.SETTLEMENTFEE, this.fomartAmountAmt(valExecFeeDiffDto.getSettlementFeeDiff()));
            //通道费
            accountAmountMap.put(BookConstant.CHLAMT, this.fomartAmountAmt(valExecFeeDiffDto.getChlFeeDiff()));
//            // 交易费用 <- 变量.交易各种费用.交易费用
//            accountAmountMap.put(BookConstant.FEE, this.fomartAmountAmt(valExecFeeDiffDto.getFeeAmtDiff()));
        }
        varMmjc = ArithmeticUtil.doAdd(
                this.fomartAmountAmt(valExecFeeDiffDto.getChlFeeDiff()),
                    ArithmeticUtil.doSub(varMmjc,
                        this.fomartAmountAmt(valExecFeeDiffDto.getStampDutyDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getBrokerageDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getCertificateFeeDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getClearingFeeDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getCommissionDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getTransactionFeeDiff()),
                        this.fomartAmountAmt(valExecFeeDiffDto.getSettlementFeeDiff())
                    )
                );
        return varMmjc;
    }


    /**
     * 行权的费用支出金额元素赋值
     * @param ps
     * @param tableId
     * @param accountAmountMap
     * @param valExecFeeDiffDto
     * @param varMmjc
     */
    private BigDecimal createExecFeeOutAmount(String ps, String tableId, Map<String, BigDecimal> accountAmountMap,
                                     ValExecFeeDiffDto valExecFeeDiffDto, BigDecimal varMmjc) {
        if(StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)) {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            BigDecimal VAR_STAMPTAX_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getStampDutyDiff(), valExecFeeDiffDto.getStampDutyForDealPayDiff());
            if(this.judgeAmtZero(VAR_STAMPTAX_OUT) >= 0) {
                accountAmountMap.put(BookConstant.STAMPTAX_OUT_D, this.fomartAmountAmt(VAR_STAMPTAX_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.STAMPTAX_OUT_C, this.fomartAmountAmtNegation(VAR_STAMPTAX_OUT));
            }
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            BigDecimal VAR_HANDFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getBrokerageDiff(), valExecFeeDiffDto.getBrokerageForDealPayDiff());
            if(this.judgeAmtZero(VAR_HANDFEE_OUT) >= 0) {
                accountAmountMap.put(BookConstant.HANDFEE_OUT_D, this.fomartAmountAmt(VAR_HANDFEE_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.HANDFEE_OUT_C, this.fomartAmountAmtNegation(VAR_HANDFEE_OUT));
            }
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            BigDecimal VAR_CERTIFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCertificateFeeDiff(), valExecFeeDiffDto.getCertificateFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_CERTIFEE_OUT) >= 0) {
                accountAmountMap.put(BookConstant.CERTIFEE_OUT_D, this.fomartAmountAmt(VAR_CERTIFEE_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.CERTIFEE_OUT_C, this.fomartAmountAmtNegation(VAR_CERTIFEE_OUT));
            }
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            BigDecimal VAR_SETTLEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getClearingFeeDiff(), valExecFeeDiffDto.getClearingFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_SETTLEFEE_OUT) >= 0) {
                accountAmountMap.put(BookConstant.SETTLEFEE_OUT_D, this.fomartAmountAmt(VAR_SETTLEFEE_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.SETTLEFEE_OUT_C, this.fomartAmountAmtNegation(VAR_SETTLEFEE_OUT));
            }
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            BigDecimal VAR_COMMISSION_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCommissionDiff(), valExecFeeDiffDto.getCommissionForDealPayDiff());
            if(this.judgeAmtZero(VAR_COMMISSION_OUT) >= 0) {
                accountAmountMap.put(BookConstant.COMMISSION_OUT_D, this.fomartAmountAmt(VAR_COMMISSION_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.COMMISSION_OUT_C, this.fomartAmountAmtNegation(VAR_COMMISSION_OUT));
            }
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            BigDecimal VAR_TRADEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getTransactionFeeDiff(), valExecFeeDiffDto.getTransactionFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_TRADEFEE_OUT) >= 0) {
                accountAmountMap.put(BookConstant.TRADEFEE_OUT_D, this.fomartAmountAmt(VAR_TRADEFEE_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.TRADEFEE_OUT_C, this.fomartAmountAmtNegation(VAR_TRADEFEE_OUT));
            }
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            BigDecimal VAR_SETTLEMENTFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getSettlementFeeDiff(), valExecFeeDiffDto.getSettlementFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_SETTLEMENTFEE_OUT) >= 0) {
                accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT_D, this.fomartAmountAmt(VAR_SETTLEMENTFEE_OUT));
            }
            else {
                accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT_C, this.fomartAmountAmtNegation(VAR_SETTLEMENTFEE_OUT));
            }
            //变量.买卖价差 = 金额元素.印花税支出 + 金额元素.经手费支出 + 金额元素.证管费支出 + 金额元素.结算费支出
            // + 金额元素.佣金支出 + 金额元素.交易手续费支出 + 金额元素.结算手续费支出
            varMmjc = ArithmeticUtil.doAdd(varMmjc,
                    this.fomartAmountAmt(VAR_STAMPTAX_OUT),
                    this.fomartAmountAmt(VAR_HANDFEE_OUT),
                    this.fomartAmountAmt(VAR_CERTIFEE_OUT),
                    this.fomartAmountAmt(VAR_SETTLEFEE_OUT),
                    this.fomartAmountAmt(VAR_COMMISSION_OUT),
                    this.fomartAmountAmt(VAR_TRADEFEE_OUT),
                    this.fomartAmountAmt(VAR_SETTLEMENTFEE_OUT));
        }
        else if(StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps)) {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            BigDecimal VAR_STAMPTAX_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getStampDutyDiff(), valExecFeeDiffDto.getStampDutyForDealPayDiff());
            accountAmountMap.put(BookConstant.STAMPTAX_OUT, this.fomartAmountAmt(VAR_STAMPTAX_OUT));
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            BigDecimal VAR_HANDFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getBrokerageDiff(), valExecFeeDiffDto.getBrokerageForDealPayDiff());
            accountAmountMap.put(BookConstant.HANDFEE_OUT, this.fomartAmountAmt(VAR_HANDFEE_OUT));
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            BigDecimal VAR_CERTIFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCertificateFeeDiff(), valExecFeeDiffDto.getCertificateFeeForDealPayDiff());
            accountAmountMap.put(BookConstant.CERTIFEE_OUT, this.fomartAmountAmt(VAR_CERTIFEE_OUT));
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            BigDecimal VAR_SETTLEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getClearingFeeDiff(), valExecFeeDiffDto.getClearingFeeForDealPayDiff());
            accountAmountMap.put(BookConstant.SETTLEFEE_OUT, this.fomartAmountAmt(VAR_SETTLEFEE_OUT));
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            BigDecimal VAR_COMMISSION_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCommissionDiff(), valExecFeeDiffDto.getCommissionForDealPayDiff());
            accountAmountMap.put(BookConstant.COMMISSION_OUT, this.fomartAmountAmt(VAR_COMMISSION_OUT));
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            BigDecimal VAR_TRADEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getTransactionFeeDiff(), valExecFeeDiffDto.getTransactionFeeForDealPayDiff());
            accountAmountMap.put(BookConstant.TRADEFEE_OUT, this.fomartAmountAmt(VAR_TRADEFEE_OUT));
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            BigDecimal VAR_SETTLEMENTFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getSettlementFeeDiff(), valExecFeeDiffDto.getSettlementFeeForDealPayDiff());
            accountAmountMap.put(BookConstant.SETTLEMENTFEE_OUT, this.fomartAmountAmt(VAR_SETTLEMENTFEE_OUT));
            //变量.买卖价差=	金额元素.印花税支出 + 金额元素.经手费支出 + 金额元素.证管费支出 + 金额元素.结算费支出 + 金额元素.佣金支出 + 金额元素.交易手续费支出 + 金额元素.结算手续费支出
            varMmjc = ArithmeticUtil.doAdd(varMmjc,
                    this.fomartAmountAmt(VAR_STAMPTAX_OUT),
                    this.fomartAmountAmt(VAR_HANDFEE_OUT),
                    this.fomartAmountAmt(VAR_CERTIFEE_OUT),
                    this.fomartAmountAmt(VAR_SETTLEFEE_OUT),
                    this.fomartAmountAmt(VAR_COMMISSION_OUT),
                    this.fomartAmountAmt(VAR_TRADEFEE_OUT),
                    this.fomartAmountAmt(VAR_SETTLEMENTFEE_OUT));
        }
        return varMmjc;
    }

    /**
     * 记账元素账务明细代码赋值
     * @param accountSceneMap
     * @param valExecFeeDiffDto
     */
    private void createExecFeeScene(Map<String, Object> accountSceneMap, ValExecFeeDiffDto valExecFeeDiffDto) {
        // 印花税账务明细代码 <- 变量.交易各种费用.印花税账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_STAMPTAX, valExecFeeDiffDto.getStampDutyCode());
        // 经手费账务明细代码 <- 变量.交易各种费用.经手费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_HANDFEE, valExecFeeDiffDto.getBrokerageCode());
        // 证管费账务明细代码 <- 变量.交易各种费用.证管费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_CERTIFEE, valExecFeeDiffDto.getCertificateFeeCode());
        // 结算费账务明细代码 <- 变量.交易各种费用.结算费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEFEE, valExecFeeDiffDto.getClearingFeeCode());
        // 佣金账务明细代码 <- 变量.交易各种费用.佣金账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_COMMISSION, valExecFeeDiffDto.getCommissionCode());
        // 交易手续费账务明细代码 <- 变量.交易各种费用.交易手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_TRADEFEE, valExecFeeDiffDto.getTransactionFeeCode());
        // 结算手续费账务明细代码 <- 变量.交易各种费用.结算手续费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_SETTLEMENTFEE, valExecFeeDiffDto.getSettlementFeeCode());
        // 通道费账务明细代码 <- 变量.交易各种费用.通道费账务明细代码
        accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, valExecFeeDiffDto.getChlFeeCode());
    }

    /**
     * 计算费用累加金额
     * @param ps
     * @param tableId
     * @param accountAmountBCCYAMTMap
     * @param valExecFeeDiffDto
     * @return
     */
    private BigDecimal calcAddFeeAmount(String ps, String tableId, Map<String, BigDecimal> accountAmountBCCYAMTMap,
                                        ValExecFeeDiffDto valExecFeeDiffDto) {
        BigDecimal 累加值 = BigDecimal.ZERO;
        if(StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)) {
            // 印花税
            if(this.judgeAmtZero(valExecFeeDiffDto.getStampDutyDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_D + BookConstant.BCCYAMT));
            }
            // 经手费
            if(this.judgeAmtZero(valExecFeeDiffDto.getBrokerageDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_D + BookConstant.BCCYAMT));
            }
            // 证管费
            if(this.judgeAmtZero(valExecFeeDiffDto.getCertificateFeeDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_D + BookConstant.BCCYAMT));
            }
            // 结算费
            if(this.judgeAmtZero(valExecFeeDiffDto.getClearingFeeDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_D + BookConstant.BCCYAMT));
            }
            // 佣金
            if(this.judgeAmtZero(valExecFeeDiffDto.getCommissionDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_D + BookConstant.BCCYAMT));
            }
            // 交易手续费
            if(this.judgeAmtZero(valExecFeeDiffDto.getTransactionFeeDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_D + BookConstant.BCCYAMT));
            }
            // 结算手续费
            if(this.judgeAmtZero(valExecFeeDiffDto.getSettlementFeeDiff()) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_C + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_D + BookConstant.BCCYAMT));
            }
            // 通道费
            if(this.judgeAmtZero(valExecFeeDiffDto.getChlFeeDiff()) >= 0) {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.CHLAMT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.CHLAMT_C + BookConstant.BCCYAMT));
            }
        }
        else if(StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps)) {
            累加值 = ArithmeticUtil.doSub(
                    ArithmeticUtil.doAdd(
                    accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX + BookConstant.BCCYAMT),//印花税_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.HANDFEE + BookConstant.BCCYAMT),//经手费_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE + BookConstant.BCCYAMT),//证管费_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE + BookConstant.BCCYAMT),//结算费_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.COMMISSION + BookConstant.BCCYAMT),//佣金_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE + BookConstant.BCCYAMT),//交易手续费_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE + BookConstant.BCCYAMT)),//结算手续费_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CHLAMT + BookConstant.BCCYAMT)//通道费_本位币
            );
        }
        return 累加值;
    }

    /**
     * 计算费用累加金额
     * @param ps
     * @param tableId
     * @param accountAmountBCCYAMTMap
     * @param valExecFeeDiffDto
     * @return
     */
    private BigDecimal calcAddFeeOutAmount(String ps, String tableId, Map<String, BigDecimal> accountAmountBCCYAMTMap,
                                        ValExecFeeDiffDto valExecFeeDiffDto) {
        BigDecimal 累加值 = BigDecimal.ZERO;
        if(StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)) {
            // 印花税支出 <- 变量.交易各种费用.印花税 + 变量.交易各种费用.随交易支付_印花税
            BigDecimal VAR_STAMPTAX_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getStampDutyDiff(), valExecFeeDiffDto.getStampDutyForDealPayDiff());
            if(this.judgeAmtZero(VAR_STAMPTAX_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT_C + BookConstant.BCCYAMT));
            }
            // 经手费支出 <- 变量.交易各种费用.经手费 + 变量.交易各种费用.随交易支付_经手费
            BigDecimal VAR_HANDFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getBrokerageDiff(), valExecFeeDiffDto.getBrokerageForDealPayDiff());
            if(this.judgeAmtZero(VAR_HANDFEE_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT_C + BookConstant.BCCYAMT));
            }
            // 证管费支出 <- 变量.交易各种费用.证管费 + 变量.交易各种费用.随交易支付_证管费
            BigDecimal VAR_CERTIFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCertificateFeeDiff(), valExecFeeDiffDto.getCertificateFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_CERTIFEE_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT_C + BookConstant.BCCYAMT));
            }
            // 结算费支出 <- 变量.交易各种费用.结算费 + 变量.交易各种费用.随交易支付_结算费
            BigDecimal VAR_SETTLEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getClearingFeeDiff(), valExecFeeDiffDto.getClearingFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_SETTLEFEE_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT_C + BookConstant.BCCYAMT));
            }
            // 佣金支出 <- 变量.交易各种费用.佣金 + 变量.交易各种费用.随交易支付_佣金
            BigDecimal VAR_COMMISSION_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getCommissionDiff(), valExecFeeDiffDto.getCommissionForDealPayDiff());
            if(this.judgeAmtZero(VAR_COMMISSION_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT_C + BookConstant.BCCYAMT));
            }
            // 交易手续费支出 <- 变量.交易各种费用.交易手续费 + 变量.交易各种费用.随交易支付_交易手续费
            BigDecimal VAR_TRADEFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getTransactionFeeDiff(), valExecFeeDiffDto.getTransactionFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_TRADEFEE_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT_C + BookConstant.BCCYAMT));
            }
            // 结算手续费支出 <- 变量.交易各种费用.结算手续费 + 变量.交易各种费用.随交易支付_结算手续费
            BigDecimal VAR_SETTLEMENTFEE_OUT = ArithmeticUtil.doAdd(valExecFeeDiffDto.getSettlementFeeDiff(), valExecFeeDiffDto.getSettlementFeeForDealPayDiff());
            if(this.judgeAmtZero(VAR_SETTLEMENTFEE_OUT) >= 0) {
                累加值 = ArithmeticUtil.doAdd(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT_D + BookConstant.BCCYAMT));
            }
            else {
                累加值 = ArithmeticUtil.doSub(累加值, accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT_C + BookConstant.BCCYAMT));
            }
        }
        else if(StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps)) {
            累加值 = ArithmeticUtil.doAdd(
                    accountAmountBCCYAMTMap.get(BookConstant.SETTLEMENTFEE_OUT + BookConstant.BCCYAMT),//结算手续费支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.STAMPTAX_OUT + BookConstant.BCCYAMT),//印花税支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.HANDFEE_OUT + BookConstant.BCCYAMT),//经手费支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.CERTIFEE_OUT + BookConstant.BCCYAMT),//证管费支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.SETTLEFEE_OUT + BookConstant.BCCYAMT),//结算费支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.COMMISSION_OUT + BookConstant.BCCYAMT),//佣金支出_本位币
                    accountAmountBCCYAMTMap.get(BookConstant.TRADEFEE_OUT + BookConstant.BCCYAMT));//交易手续费支出_本位币
        }
        return 累加值;
    }

}
