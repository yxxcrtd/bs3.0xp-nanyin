/*
 * 文件名：ProdPassAssetBonusProcessService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年3月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.channelprodautocal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListTableId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.ValBooksetTodoDto;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 通道产品自动结转Process逻辑处理
 * 
 * @author weipeng
 * @version 1.0 2019年3月15日
 * @see ChannelProdAutoCalProcessService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelProdAutoCalProcessService {

    /**
     * 记账引擎
     */
    @Autowired
    private BookAccountGenLogic bookLogic;

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 共通方法service
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResult execute(Map<String, Object> param) {
        // 获取reader返回值
        ValBooksetTodoDto acctEntity = (ValBooksetTodoDto)param.get(GlobalConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (acctEntity == null) {
            return null;
        }

        // 定义此process的返回变量
        BookGenerateResult returnResult = new BookGenerateResult();

        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }
        returnResult.setProdId(acctEntity.getBooksetId());
        // 1.数据准备
        // 1.1 待分配利润科目最新余额查询
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        // 账套代码 = 账务处理结果.账套代码
        searchDto.setBooksetId(acctEntity.getBooksetId());
        // 发生日 = 账务处理结果.发生日
        searchDto.setHappenDate(acctEntity.getHappenDate());
        // 入账日 = 账务处理结果.入账日
        searchDto.setBookDate(acctEntity.getBookDate());
        searchDto.setBaseSubjectNatureList(
            new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.应付利润.getValue())));
        searchDto.setBokDetailId(acctEntity.getBokDetailId());// 账务明细代码
        SubjectBalance subjectBalance = this.getSubjectBalance(searchDto,
            DictCodeList.余额方向_贷方.getValue());
        // 定义 变量.应付利润余额 = 返回值.科目余额
        BigDecimal profitAmt = BigDecimal.ZERO;

        BigDecimal profitAmtYb = BigDecimal.ZERO;
        if (subjectBalance != null) {
        	/**MODIFY BY WUJIE ON 20190819 START
            profitAmt = subjectBalance.getBalance();
            **/
        	profitAmt = subjectBalance.getbAmt();
        	/**MODIFY BY WUJIE ON 20190819 END**/

            /**add BY xr  ON 20200723 START **/
            profitAmtYb = subjectBalance.getBalance();
        }
        // 组装记账元素
        // 初始化定义 - 记账场景要素管理
        AccountDriver ad = new AccountDriver();
        // 初始化定义 - 存放该笔记账的场景要素 map定义
        Map<String, Object> accountSceneMap = Maps.newHashMap();
        // 初始化定义 - 存放该笔记账的相关金额要素 map定义
        Map<String, BigDecimal> accountAmountMap = Maps.newHashMap();
        // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
        Map<String, Object> accountCurrencyMap = Maps.newHashMap();

        // 记账场景要素map塞值,(调用共通方法-计提类场景元素赋值)
        accountSceneMap = bookCommonLogic.accrualElementAssignForPass(acctEntity);
        accountSceneMap.put(BookConstant.BOOK_REMARK, "产品(货币型)-红利再投");
        accountSceneMap.put(BookConstant.TASK_PRI, param.get(BookConstant.TASK_PRI));
        // 会计场景设置
        ad.setTableId(DictCodeListTableId.会计场景代码_产品_货币型_红利再投.getValue()); // ad.会计场景代码="产品_货币型_红利再投"
        // 塞入记账场景树节点 = 会计场景
        ad.setTreeNode(acctEntity.getBookSetTreeNode());
        // 客户收益（应付利润）  <- 变量.应付利润余额 （借贷方向分录已改为恒借）
        //发生数量
        accountSceneMap.put(BookConstant.HAPPEN_NUMBER,
            ArithmeticUtil.round(profitAmt, NumberEnum.MONEY));
        
        
        // 应付利润（借） = 变量.应付利润余额
        accountAmountMap.put(BookConstant.CUSTREV_D,
            ArithmeticUtil.round(profitAmtYb, NumberEnum.MONEY));
        
        // 应付利润（借）_本位币 = 变量.应付利润余额
        accountAmountMap.put(BookConstant.CUSTREV_D+BookConstant.BCCYAMT,
            ArithmeticUtil.round(profitAmt, NumberEnum.MONEY));
        
        
        // 申购份额（实收资本）  <- 变量.应付利润余额 （借贷方向分录已改为恒贷）
        // 实收资本（贷） = 变量.应付利润余额
        accountAmountMap.put(BookConstant.TRADE_QTY_C,
            ArithmeticUtil.round(profitAmt, NumberEnum.MONEY));
        
        // 实收资本（贷）_本位币 = 变量.应付利润余额
        accountAmountMap.put(BookConstant.TRADE_QTY_C+BookConstant.BCCYAMT,
            ArithmeticUtil.round(profitAmt, NumberEnum.MONEY));
        
        
        // 记账金额元素币种信息塞值--key是随记账金额map的
        for (String key : accountAmountMap.keySet()) {
            accountCurrencyMap.put(key, acctEntity.getStandardCcy());
        }
        //本位币设置
        accountCurrencyMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
        
        // map塞入场景要素管理中
        ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
        ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
        ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");
        // 记账凭证生成
        BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);
        // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
        returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        return resultNowNow;
    }

    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     * 
     * @param booksetId 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDate 发生日
     * @param bookDate 入账日
     * @param expBalanceDirect 期待余额方向
     * @param baseSubjectNature 基础科目性质
     * @param taskPri 任务优先级a
     * @return subjectBalance 科目余额信息
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(SubjectBalanceSearchDto searchDto,
                                             String expBalanceDirect) {
        // 定义返回实体类
        SubjectBalance subjectBalance = null;
        // 调用公用方法：【科目余额查询 - 期待方向】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDto, expBalanceDirect);
        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            subjectBalance = subjectBalanceList.get(0);
        }

        return subjectBalance;
    }

}
