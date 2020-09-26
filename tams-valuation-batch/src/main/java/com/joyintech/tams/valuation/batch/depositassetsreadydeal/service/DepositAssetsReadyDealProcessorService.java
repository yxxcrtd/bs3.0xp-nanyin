/*
 * 文件名：DepositAssetsReadyDealProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.depositassetsreadydeal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListTableId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoComDTO;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.FinCashPlanEx;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * 存款资产预交易账务处理 --- processService
 * 
 * @author yangjian
 * @version 1.0 2018年9月21日
 * @see DepositAssetsReadyDealProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositAssetsReadyDealProcessorService {

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
     * 公用方法services
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 标的共通方法类
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * process 数据处理
     * 
     * @param param1 参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unused")
    public BookGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult)param1.get(GlobalConstant.READER_RESULT);

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
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String bokDetailId = acctEntity.getBokDetailId();// 账务明细代码
        TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 会计场景
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();// 入账日期
        List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 金融产品交易信息list
        //Long taskPri = listFinProductDealInfoComDTONow.get(0).getTaskPri();// 任务优先级（信息列表的任务优先级都是一致的，故取第一个）
        String standardCcy = listFinProductDealInfoComDTONow.get(0).getStandardCcy(); // 本位币币种

        // 1.数据准备
        // 1.1 定义变量
        // 定义 变量.成本科目余额
        BigDecimal costBalance = BigDecimal.ZERO;
        // 定义 变量.成本科目数量
        BigDecimal costQuantity = BigDecimal.ZERO;
        // 定义 变量.应收利息科目余额
        BigDecimal interestBalance = BigDecimal.ZERO;

        // 2.调用共通查询发生日前一日余额
        // 交易期待方向——借方
        String expBalanceDirect = DictCodeList.余额方向_借方.getValue();
        SubjectBalance subjectBalance = new SubjectBalance();
        // 2.1.查询最新的成本余额
        subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow, bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.成本.getValue(), null, null);
        // 变量.成本科目余额 = 上述共通返回的科目余额
        costBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO : subjectBalance.getBalance();
        // 变量.成本科目数量 = 上述共通返回的数量
        costQuantity = subjectBalance.getNum() == null ? BigDecimal.ZERO : subjectBalance.getNum();

        // 2.2.查询最新的应计利息
        subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow, bookDateNow, expBalanceDirect, DictCodeBaseSubjectNatrue.应计利息.getValue(), null, null);
        // 变量.应计利息科目余额 =
        interestBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO : subjectBalance.getBalance();

        // 3.处理交易
        // 循环 交易类账务处理结果.交易明细列表,进行逻辑处理
        for (FinProductDealInfoComDTO dealNow : listFinProductDealInfoComDTONow) {

            // 变量.标的信息 = 查询标的信息
            TargetFinProduct targetFinProduct = this.getTargetFinProduct(dealNow.getFinprodId());

            // 组装记账元素
            // 初始化定义 - 记账场景要素管理
            AccountDriver ad = new AccountDriver();
            // 初始化定义 - 存放该笔记账的场景要素 map定义
            Map<String, Object> accountSceneMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额要素 map定义
            Map<String, BigDecimal> accountAmountMap = new HashMap<>();
            // 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
            Map<String, Object> accountCurrencyMap = new HashMap<>();

            // 设置账务引擎参数
            ad.setTreeNode(bookSetTreeNodNow);// 塞入记账场景树节点 = 会计场景

            // 记账场景要素map塞值,(调用共通方法-交易类场景元素赋值)
            accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);
            // 境内外 = 变量.交易信息.境内外
            accountSceneMap.put(BookConstant.BLN_AREA, targetFinProduct.getBlnArea());
            // 资产类型
            accountSceneMap.put(BookConstant.ASSET_TYPE, targetFinProduct.getFinprodType());
            // 分类1
            accountSceneMap.put(BookConstant.TYPE_1, targetFinProduct.getType1());
            // 分类2
            accountSceneMap.put(BookConstant.TYPE_2, targetFinProduct.getType2());
            // 分类3
            accountSceneMap.put(BookConstant.TYPE_3, targetFinProduct.getType3());
            // 分类4
            accountSceneMap.put(BookConstant.TYPE_4, targetFinProduct.getType4());
            // 分类5
            accountSceneMap.put(BookConstant.TYPE_5, targetFinProduct.getType5());
            // 分类6
            accountSceneMap.put(BookConstant.TYPE_6, targetFinProduct.getType6());
            // 是否累计余额->是
            accountSceneMap.put(BookConstant.IS_ACC_BALANCE, DictCodeList.是否_是.getValue());
            // 备注 = "存款资产"+ 交易方向数据字典对应显示值 +"交易登账"
            accountSceneMap.put(BookConstant.BOOK_REMARK, "存款资产" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, dealNow.getPs()) + "交易登账");

            // 定义 变量.汇率 = 1
            BigDecimal 汇率 = BigDecimal.ONE;
            // 本币与原币不等
            if(!StringUtil.equals(dealNow.getCcy(),standardCcy)) {
                //调用计算类 方法共通【汇率查询】
                //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
                /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
                //汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
                List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
                    standardCcy, targetFinProduct.getCcy());
                if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                    汇率 = currencyMarketList.get(0).getClosePrice();
                }
            }
            
            // 交易方向 = "收息"
            if (DictCodeList.交易方向_收息.getValue().equals(dealNow.getPs())&&DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(targetFinProduct.getType1())) {
                // ad.会计场景代码="存放同业-收息"
                ad.setTableId(DictCodeListTableId.会计场景代码_存放同业_收息.getValue());

                // (1) 查询交易的到期日对应的应计利息
                SubjectBalance profitSubjectBlance = this.getSubjectBalance(booksetIdNow, bokDetailId, DateUtil.addDay(dealNow.getMdate(), -1), bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.应计利息.getValue(), null, null);
                // 定义：变量.应计利息 = 上述共通返回的科目余额
                BigDecimal profitBlance = profitSubjectBlance.getBalance() == null ? BigDecimal.ZERO : profitSubjectBlance.getBalance();

                // (2) 查询交易的到期日是否为付息截止日
                // 调用共通方法-【标的类共通方法设计】-付息截止日判断SHEET
                FinCashPlanDto finCashPlanDto = finProductCommonService.payDeadlineJudgment(dealNow.getFinprodId(), dealNow.getMdate());
                // 定义：否付息截止日
                Boolean isfDeadlineNow = null;
                // 定义：现金流计划表DTO
                FinCashPlanEx finCashPlanExNow = new FinCashPlanEx();
                // 赋值
                if (finCashPlanDto != null) {
                    isfDeadlineNow = finCashPlanDto.isfDeadline();
                    finCashPlanExNow = finCashPlanDto.getFinCashPlanEx();
                }

                // (3) 查询交易的到期日对应的成本数量
                SubjectBalance costSubjectBlance = this.getSubjectBalance(booksetIdNow, bokDetailId, DateUtil.addDay(dealNow.getMdate(), -1), bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.成本.getValue(), null, null);
                // 定义:变量.到期日成本科目数量= 上述共通返回的数量
                BigDecimal mdateCostNum = costSubjectBlance.getNum() == null ? BigDecimal.ZERO : costSubjectBlance.getNum();

                // 上述（2）返回值为 false。即（是否付息截止日为false）
                if (isfDeadlineNow != null && !isfDeadlineNow) {
                    // 变量.当前本金 = 调用共通计算类共通【当前未还本金（DTO.账套日期,DTO.金融产品代码,变量.标的信息.发行规模）】
                    BigDecimal costNow = finProductCommonService.getUnpaidPrinAmt(dealNow.getBooksetDate(), dealNow.getFinprodId(), targetFinProduct.getIssueAmt());

                    // 变量.交易份额
                    BigDecimal dealNum = BigDecimal.ZERO;
                    if (BigDecimal.ZERO.compareTo(costNow) != 0) {
                        // 变量.交易份额 = (现金流计划表DTO.计划支付本金 / 变量.当前本金) * 变量.到期日成本科目数量 --- 【调用中间处理值设置有效小数位】
                        dealNum = ArithmeticUtil.multiply(ArithmeticUtil.divide(finCashPlanExNow.getPrinAmt(), costNow, NumberEnum.ALL_PRECISION.getCode()), mdateCostNum);
                    }

                    if (BigDecimal.ZERO.compareTo(mdateCostNum) != 0) {
                        // 变量.应计利息 = 变量.应计利息 * 变量.交易份额 / 变量.到期日成本科目数量
                        profitBlance = ArithmeticUtil.divide(ArithmeticUtil.multiply(profitBlance, dealNum), mdateCostNum);
                        // 调用金额共通方法设置有效小数位
                        profitBlance = ArithmeticUtil.round(profitBlance, NumberEnum.MONEY);
                    }
                }

                // 金额元素设值
                // 应收利息 <-变量.应计利息
                accountAmountMap.put("INTAMT", profitBlance);
                // 应收利息_本位币 <- 变量.应计利息 * 汇率
                accountAmountMap.put("INTAMT" + BookConstant.BCCYAMT, 
                    ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), null, profitBlance, 汇率));

                // 基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);

                // 全局变量的更新
                // 变量.应计利息科目余额 = 变量.应计利息科目余额 - 变量.应计利息
                interestBalance = ArithmeticUtil.doSub(interestBalance, profitBlance);

            }
            // 交易方向 = "还本" || 交易方向 = "到期"
            else if (DictCodeList.交易方向_还本.getValue().equals(dealNow.getPs()) || DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                // ad.会计场景代码="存放同业-到期或卖出"
                ad.setTableId(DictCodeListTableId.会计场景代码_存放同业_还本_到期.getValue());

                // 查询交易的到期日对应的科目余额
                SubjectBalance costSubjectBlance = this.getSubjectBalance(booksetIdNow, bokDetailId, DateUtil.addDay(dealNow.getMdate(), -1), bookDateNow, DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.成本.getValue(), null, null);
                // 定义：变量.结转日成本科目余额
                BigDecimal subjectBlanceForJz = BigDecimal.ZERO;
                // 定义：变量.结转日成本科目数量
                BigDecimal subjectNumForJz = BigDecimal.ZERO;
                // 赋值
                if (costSubjectBlance != null) {
                    // 变量.结转日成本科目余额 = 科目余额
                    subjectBlanceForJz = costSubjectBlance.getBalance();
                    // 变量.结转日成本科目数量 = 数量
                    subjectNumForJz = costSubjectBlance.getNum();
                }

                // 变量.交易份额 = 变量.结转日成本科目数量 * 交易明细.单位净价 ---【调用金额元素设置有效小数位】
                BigDecimal dealNumNow = ArithmeticUtil.round(ArithmeticUtil.multiply(subjectNumForJz, dealNow.getUnitCprice()), NumberEnum.MONEY);;
                // 变量.交易金额 = 变量.结转日成本科目数量 * 交易明细.单位净价 ---【调用金额元素设置有效小数位】
                BigDecimal dealMoneyNow = ArithmeticUtil.round(ArithmeticUtil.multiply(subjectNumForJz, dealNow.getUnitCprice()), NumberEnum.MONEY);;
                // 变量.交易本金 = 变量.结转日成本科目数量 * 交易明细.单位净价 ---【调用金额元素设置有效小数位】
                BigDecimal dealCostNow = ArithmeticUtil.round(ArithmeticUtil.multiply(subjectNumForJz, dealNow.getUnitCprice()), NumberEnum.MONEY);;

                // 定义：变量.冲减成本 = 0
                BigDecimal cjCost = BigDecimal.ZERO;
                // 定义：变量.冲减数量 = 0
                BigDecimal cjNum = BigDecimal.ZERO;

                // 根据交易方向进行区分
                if (DictCodeList.交易方向_到期.getValue().equals(dealNow.getPs())) {
                    // 变量.冲减成本 = 变量.结转日成本科目余额
                    cjCost = subjectBlanceForJz;
                    // 变量.冲减数量 = 变量.结转日成本科目数量
                    cjNum = subjectNumForJz;
                }
                else {
                    // 变量.冲减成本 = 变量.结转日成本科目余额 * 变量.交易份额/变量.结转日成本科目数量
                    cjCost = ArithmeticUtil.divide(ArithmeticUtil.multiply(subjectBlanceForJz, dealNumNow), subjectNumForJz);
                    // 变量.冲减数量 = 变量.交易份额
                    cjNum = dealNumNow;
                }

                // 调用金额共通方法设置有效小数位
                cjCost = ArithmeticUtil.round(cjCost, NumberEnum.MONEY);
                cjNum = ArithmeticUtil.round(cjNum, NumberEnum.MONEY);

                // 金额元素设值
                // 交易金额 <-变量.交易本金
                accountAmountMap.put("COST", dealCostNow);
                // 交易金额_本位币 <- 交易金额  * 汇率
                accountAmountMap.put("COST" + BookConstant.BCCYAMT, 
                    ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), null, dealCostNow, 汇率));
                // 基础科目性质
                Map<String, Map<String, Object>> accountNatrueMap = new HashMap<>();
                Map<String, Object> accountNatrueBranchMap = new HashMap<>();
                accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
                accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
                ad.setAccountNatrueMap(accountNatrueMap);
                // 发生数量->冲减数量
                accountSceneMap.put(BookConstant.HAPPEN_NUMBER, cjNum);
                // 全局变量的更新
                // 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
                costBalance = ArithmeticUtil.doSub(costBalance, cjCost);
                // 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
                costQuantity = ArithmeticUtil.doSub(costQuantity, cjNum);
            }

            // 记账金额元素币种信息塞值
            // 因为与记账金额元素map的key及数量一致，故采用循环
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, dealNow.getCcy());// 币种 - 交易明细.交易币种
            }

            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, new SysBatchExecInfo());
            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());
        }

        return returnResult;
    }

    /**
     * 调用公用方法：【科目余额查询 - 期待方向】，获取科目余额信息中的科目余额数据
     * 
     * @param booksetIdNow 账套代码
     * @param bokDetailId 账务明细代码
     * @param happenDateNow 发生日
     * @param bookDateNow 入账日
     * @param expBalanceDirect 期待余额方向
     * @param baseSubjectNature 基础科目性质
     * @param branch 分支号
     * @param taskPri 任务优先级
     * @return SubjectBalance 科目余额
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetIdNow, String bokDetailId, Date happenDateNow, Date bookDateNow, String expBalanceDirect, String baseSubjectNature, Long branch, Long taskPri) {
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
        searchDto.setBookDate(bookDateNow);
        searchDto.setBranch(branch);
        searchDto.setTaskPri(taskPri);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询-期待方向 】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto, expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            balance = subjectBalanceList.get(0);
        }

        return balance;
    }

    /**
     * 获取标的基本信息
     * 
     * @param bokDetailId 账务明细代码
     * @return TargetFinProduct 标的基本信息
     * @see
     * @since 1.0
     */
    private TargetFinProduct getTargetFinProduct(String bokDetailId) {
        // 定义查询list
        List<TargetSearchDto> targetSearchDtoList = new ArrayList<TargetSearchDto>();
        // 定义查询类型 实体类
        TargetSearchDto targetSearchDto = new TargetSearchDto();
        targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
        // 查询实体类塞入查询实体类List中
        targetSearchDtoList.add(targetSearchDto);

        // 获取标的信息
        Map<TargetSearchDto, Object> targetMap = finProductCommonService.getTargetInfo(bokDetailId, targetSearchDtoList);
        TargetFinProduct targetFinProduct = (TargetFinProduct)targetMap.get(targetSearchDto);

        // 确保不为null
        if (targetFinProduct == null) {
            targetFinProduct = new TargetFinProduct();
        }

        return targetFinProduct;
    }
}
