/*
 * 文件名：LendDrawBookDetailProcessService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.lenddrawbookdetail.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListTableId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.calculation.entity.PrincipalInfo;
import com.joyintech.tams.valuation.batch.common.calculation.entity.QuoteInfo;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.common.finproduct.service.FinProductCommonService;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TradeInfoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;

/**
 * 拆借计提账务处理 process相关业务处理
 * 
 * @author yangjian
 * @version 1.0 2018年5月3日
 * @see LendDrawBookDetailProcessService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendDrawBookDetailProcessService {

    /**
     * 共通方法service
     */
    @Autowired
    private FinProductCommonService finProductCommonService;

    /**
     * 共通方法service
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * 计算类共通方法
     */
    @Autowired
    private CalculationCommonService calculationCommonService;

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
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BookGenerateResultWithBokSummary execute(Map<String, Object> param) {
    	// 获取reader返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult)param.get(
            GlobalConstant.READER_RESULT);

        // 若reader传递的参数或计提基础信息列表为空，不进行后续处理
        if (acctEntity == null || acctEntity.getListDrawBaseInfoSelectComDTO() == null
            || acctEntity.getListDrawBaseInfoSelectComDTO().isEmpty()) {
            return null;
        }

        // 定义返回值
        BookGenerateResultWithBokSummary returnResult = new BookGenerateResultWithBokSummary();

        // 账务明细若为空赋初始值
        if (returnResult.getValBokBookdetailBatch() == null) {
            returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
        }

        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");

        // 获取从reader中拿到的值
        String booksetIdNow = acctEntity.getBooksetId();// 账套代码
        String bokDetailIdNow = acctEntity.getBokDetailId();// 账务明细代码
        String busiId = acctEntity.getBusiId();// 业务明细代码
        String invAimNow = acctEntity.getInvAim();// 投资目的
        TreeNode bookSetTreeNodeNow = acctEntity.getBookSetTreeNode();// 会计场景
        Date happenDateNow = acctEntity.getHappenDate();// 发生日期
        Date bookDateNow = acctEntity.getBookDate();// 入账日期
        List<DrawBaseInfoSelectComDTO> listDrawBaseInfoSelectComDTONow = acctEntity.getListDrawBaseInfoSelectComDTO(); // 计提基础信息列表
        Long taskPri = listDrawBaseInfoSelectComDTONow.get(0).getTaskPri();// 任务优先级（计提基础信息列表的任务优先级都是一致的，故取第一个）

        // 定义变量
        BigDecimal costSjtAmtVar = BigDecimal.ZERO;// 变量.成本科目余额
        BigDecimal interestVar = BigDecimal.ZERO;// 变量.应收利息
        BigDecimal interestBlanceForYstVar = BigDecimal.ZERO;// 变量.应收利息前一日余额
        BigDecimal qualityBalance = BigDecimal.ZERO; // 变量.数量余额

        // 获取报价信息列表
        List<QuoteInfo> quoteInfoList = this.getQuoteInfoList(busiId);

        // 账套核算参数查询
        List<String> paramCodeList = new ArrayList<String>();
        // DL_ACCR_TYPE 账套配置表参数项_拆借计提方式
        paramCodeList.add(ValDictCodeList.账套配置表参数项_拆借计提方式.getValue());
        // 调用账务类共通（DTO.账套代码, 参数项：如下）
        List<BokBooksetExt> listBooksetExt = accountingCommonService.getValBokBooksetExt(
            booksetIdNow, paramCodeList);
        // 变量.计提方式 = 根据DTO.现金流二级类型 对应的 产品费用计提方式_XX费
        String calType = "";
        if (!CollectionUtils.isEmpty(listBooksetExt)) {
            // 获取第一条即可
            calType = listBooksetExt.get(0).getParamValue();
        }

        // 循环 计提基础信息列表 进行逻辑处理
        for (DrawBaseInfoSelectComDTO dto : listDrawBaseInfoSelectComDTONow) {

            // 通过业务明细代码，获取标的交易信息
            TradeInfoDto dealInfoForTarget = this.getTargetInfoDto(dto.getBusiId(),
                dto.getBusiDetailType(), dto.getBranch());
            // 保证获取的标的交易信息不为空
            if (dealInfoForTarget == null) {
                continue;
            }

            // 获取标的交易信息到期日的前一天
            // Date mdateYsdWithTarget = DateUtil.addDay(dealInfoForTarget.getMdate(), -1);
            // 获取标的交易 的 本金金额（到期本金）
            // BigDecimal prinAmtWithTarget = dealInfoForTarget.getMPrinAmt() == null ?
            // BigDecimal.ZERO : dealInfoForTarget.getMPrinAmt();
            // 获取标的交易 的 利息总额（到期利息）
            // BigDecimal intAmtWithTarget = dealInfoForTarget.getMIntAmt() == null ?
            // BigDecimal.ZERO : dealInfoForTarget.getMIntAmt();
            // 获取标的金融产品类型（原金融产品类型）
            String finprodType = dealInfoForTarget.getOFinprodType();
            // 获取标的起息日
            // Date vdateForTarget = dealInfoForTarget.getVdate();

            // 定义此处传递的基础科目性质 - (默认为：变量.基础科目性质 = 数据字典.基础科目性质.应计利息)
            String baseSubjectNatrueNow = DictCodeBaseSubjectNatrue.应计利息.getValue();
            // 定义 期待余额方向
            String expBalanceDirect = "";

            // case 拆出 then 借方 else 贷方
            //
            if (DictCodeListExt1.金融产品类型_拆出.getValue().equals(finprodType)
                || DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)) {
                expBalanceDirect = DictCodeList.余额方向_借方.getValue();
            }
            else {
                expBalanceDirect = DictCodeList.余额方向_贷方.getValue();
                // 若标的金融产品类型为“拆入”，则 变量.基础科目性质 = 数据字典.基础科目性质.应付赎回款
                // baseSubjectNatrueNow = DictCodeBaseSubjectNatrue.应付赎回款.getValue();
            }
            // 获取初始成本科目余额 case 拆出 then 借方 else 贷方
            SubjectBalance balanceDto = this.getSubjectBalance(booksetIdNow, bokDetailIdNow,
                happenDateNow, bookDateNow, expBalanceDirect,
                DictCodeBaseSubjectNatrue.成本.getValue(), invAimNow, taskPri);
            // 变量.成本科目余额 = DTO.科目余额
            costSjtAmtVar = balanceDto.getBalance();
            // 变量.数量余额 = DTO.数量余额
            qualityBalance = balanceDto.getNum();

            // 获取应计利息前一日余额 case 拆出 then 借方 else 贷方
            interestBlanceForYstVar = this.getSubjectBalance(booksetIdNow, bokDetailIdNow,
                happenDateNow, bookDateNow, expBalanceDirect, baseSubjectNatrueNow, invAimNow,
                taskPri).getBalance();

            // 结果集发生日 [小于] 标的到期日
            if (happenDateNow.compareTo(dealInfoForTarget.getMdate()) < 0) {
                // if（变量.计提方式 = 【数据字典】.计提方式."区间倒减"）
                if (DictCodeList.计提方式_区间倒减.getValue().equals(calType)) {
                    // 定义 变量.计提开始日
                    Date vDate = null;
                    // 变量.当前计划 = 调用标的类共通【当前付息区间查询（参数.发生日,计提类账务处理结果.业务明细代码）】 (理论上是必有数据的，故下面不做为空处理)
                    List<TargetCashPlan> listCashPlan = finProductCommonService.getCurrentIntRange(
                        dto.getBusiId(), happenDateNow);
                    TargetCashPlan targetCashPlan = new TargetCashPlan();
                    if (listCashPlan != null && listCashPlan.size() > 0) {
                        targetCashPlan = listCashPlan.get(0);
                        vDate = targetCashPlan.getVdate();
                    }
                    else {
                        vDate = dealInfoForTarget.getVdate();
                    }

                    // 当前计划 不为空判断，避免空指针错误
                    if (vDate != null) {
                        // 定义本金列表
                        PrincipalInfo principalInfo = new PrincipalInfo();
                        principalInfo.setChangeDate(vDate);// 变动日 - 当前计划.计息开始日(未调整的计算开始日)
                        principalInfo.setPrinAmount(costSjtAmtVar);// 本金金额 - 变量.成本科目余额
                        List<PrincipalInfo> principalInfoList = new ArrayList<PrincipalInfo>();
                        principalInfoList.add(principalInfo);
                        // 获取总利息金额[调用共通方法：：计算类共通方法 - 通用计算]
                        BigDecimal totalInterestAmt = calculationCommonService.calCommon(vDate,
                            DateUtil.addDay(happenDateNow, 1), principalInfoList,
                            quoteInfoList).getTotalInterestAmt();;

                        // 变量.应收利息 = 总利息金额 - 变量.应收利息前一日余额
                        interestVar = ArithmeticUtil.doSub(totalInterestAmt,
                            interestBlanceForYstVar);
                        // 调用金额共通方法设置有效小数位
                        interestVar = ArithmeticUtil.round(interestVar, NumberEnum.MONEY);
                    }
                }
                else {
                    // 定义 小于等于参数.发生日的最新报价信息
                    QuoteInfo latestQuoteInfo = new QuoteInfo();
                    // 从报价列表中获取最新报价
                    for (QuoteInfo item : quoteInfoList) {
                        if (DateUtil.compareDate(happenDateNow, item.getIssueDate()) >= 0) {
                            if (latestQuoteInfo.getIssueDate() == null) {
                                latestQuoteInfo = item;
                            }
                            else if (DateUtil.compareDate(latestQuoteInfo.getIssueDate(),
                                item.getIssueDate()) <= 0) {
                                latestQuoteInfo = item;
                            }
                        }
                    }
                    List<QuoteInfo> latestQuoteInfoList = new ArrayList<QuoteInfo>();
                    latestQuoteInfoList.add(latestQuoteInfo);
                    // 定义本金列表
                    PrincipalInfo principalInfo = new PrincipalInfo();
                    // 变动日 - 变量.发生日
                    principalInfo.setChangeDate(happenDateNow);
                    // 本金金额 - 变量.成本科目余额
                    principalInfo.setPrinAmount(costSjtAmtVar);
                    List<PrincipalInfo> principalInfoList = new ArrayList<PrincipalInfo>();
                    principalInfoList.add(principalInfo);
                    // IF (交易DTO.金融产品类型 != 同业借款 )
                    if (!DictCodeListExt1.金融产品类型_同业借款.getValue().equals(finprodType)) {
                        // IF(参数.发生日 = 交易DTO.到期日 - 1)
                        if (DateUtil.compareDate(happenDateNow,
                            DateUtil.addDay(dealInfoForTarget.getMdate(), -1)) == 0) {
                            // 变量.应收利息 = 变量.成本科目余额*交易DTO.到期利息/交易DTO.名义本金 - 变量.应收利息前一日余额
                            // *金额小数位处理
                            interestVar = ArithmeticUtil.subtract(
                                ArithmeticUtil.divide(
                                    ArithmeticUtil.multiply(costSjtAmtVar,
                                        dealInfoForTarget.getMIntAmt()),
                                    dealInfoForTarget.getPrin()),
                                interestBlanceForYstVar, NumberEnum.MONEY.getCode(), null);
                        }
                        else {
                            // 调用共通设计->【详细设计-计算类共通方法设计.xlsx】->质押式回购计提
                            // 变量.应计利息 = 返回值.发生应计利息
                            interestVar = calculationCommonService.calRepo(dealInfoForTarget,
                                qualityBalance, costSjtAmtVar, DateUtil.addDay(happenDateNow, 1),
                                calType, interestBlanceForYstVar)[1];
                            interestVar = ArithmeticUtil.round(interestVar, NumberEnum.MONEY);
                        }
                    }
                    else {
                        // 获取总利息金额[调用共通方法：：计算类共通方法 - 通用计算]
                        BigDecimal totalInterestAmt = calculationCommonService.calCommon(happenDateNow,
                            DateUtil.addDay(happenDateNow, 1), principalInfoList,
                            latestQuoteInfoList).getTotalInterestAmt();
                        // 变量.应收利息 = 返回值.总利息金额 *金额小数位处理
                        interestVar = ArithmeticUtil.round(totalInterestAmt, NumberEnum.MONEY);
                    }
                }

            }
            else {
                // 变量.应收利息 = 0
                interestVar = BigDecimal.ZERO;
            }

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
            ad.setTreeNode(bookSetTreeNodeNow);// 塞入记账场景树节点 = 会计场景

            // 记账场景要素map塞值,(调用共通方法-计提类场景元素赋值)
            accountSceneMap = bookCommonLogic.accrualElementAssign(acctEntity, dto);
            accountSceneMap.put(BookConstant.BOOK_REMARK, "拆借每日计提");// 备注
            accountSceneMap.put(BookConstant.TYPE_1, dealInfoForTarget.getType1());// 分类1 -
                                                                                   // 变量.交易信息.分类1
            accountSceneMap.put(BookConstant.TYPE_2, dealInfoForTarget.getType2());// 分类2 -
                                                                                   // 变量.交易信息.分类2

            // 根据【标的信息 - 金融产品类型】，判断当前分录场景是【拆放同业（拆入）】还是【拆放同业（同业借款）】
            if (DictCodeListExt1.金融产品类型_拆入.getValue().equals(finprodType)) {// 分录场景为【拆放同业（拆入）】
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_拆入_利息计提.getValue());// ad.会计场景代码

                // 金额元素塞值
                // accountAmountMap.put("ACCQUA", null); // 核算数量 - 暂不塞值
                accountAmountMap.put("INTAMT", interestVar); // 应计利息 - 取值：变量.应收利息

            } // 分录场景为【拆放同业（同业借款）】，即拆出
            else {
                // 设置账务引擎参数
                ad.setTableId(DictCodeListTableId.会计场景代码_拆放同业_计提.getValue());// ad.会计场景代码 = 拆放同业（同业借款）_计提;

                // 金额元素塞值
                // accountAmountMap.put("ACCQUA", null); // 核算数量 - 暂不塞值
                accountAmountMap.put("INTAMT", interestVar); // 应收利息 - 取值：变量.应收利息
            }

            // 记账金额元素币种信息塞值
            // 因为与记账金额元素map的key及数量一致，故采用循环
            for (String keyNow : accountAmountMap.keySet()) {
                accountCurrencyMap.put(keyNow, dto.getoCcy());// value值塞入当前交易币种 （当前为获取计提里面的原币）
            }

            // map塞入场景要素管理中
            ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
            ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
            ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

            // 记账凭证生成
            BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);

            // 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
            returnResult.getValBokBookdetailBatch().addAll(
                resultNowNow.getValBokBookdetailBatch());

            // 3.3 统计分析账务明细汇总信息赋值
            // 定义：变量.计提利率
            BigDecimal jTrate = BigDecimal.ZERO;
            if (quoteInfoList != null) {
                for (QuoteInfo rule : quoteInfoList) {
                    // 报价.生效日 > 计提类账务处理结果.发生日
                    if (rule.getIssueDate().compareTo(acctEntity.getHappenDate()) > 0) {
                        break;
                    }

                    // 变量.计提利率 = 报价temp.生效利率
                    jTrate = rule.getInterest();
                }
            }
            // 定义当日统计分析账务明细汇总信息
            BokStatDetPosiiton bokSummaryTableNew = new BokStatDetPosiiton();
            // 变量.当日统计分析账务明细汇总信息.当前持仓 = 变量.数量余额
            bokSummaryTableNew.setPosAmt(qualityBalance);
            // 变量.当日统计分析账务明细汇总信息.计提利率 = 变量.计提利率
            bokSummaryTableNew.setIntRate(jTrate);
            // 变量.当日统计分析账务明细汇总信息.日摊销收益率 =NULL
            bokSummaryTableNew.setDailydscYield(null);
            // 变量.当日统计分析账务明细汇总信息.当日发生应计利息 = 变量.应计利息
            bokSummaryTableNew.setTdyIntincexpAdd(interestVar);
            // 变量.当日统计分析账务明细汇总信息.当日累计应计利息余额 = 变量.应收利息前一日余额 + 变量.应计利息 
            BigDecimal tdyintincexpNow = ArithmeticUtil.doAdd(interestBlanceForYstVar,
                interestVar);
            bokSummaryTableNew.setTdyIntincexp(tdyintincexpNow);
            // 变量.当日统计分析账务明细汇总信息.当日份额 = 变量.数量余额
            bokSummaryTableNew.setTdyPosition(qualityBalance);
            // 变量.当日统计分析账务明细汇总信息.投资目的 = 计提类账务处理结果.投资目的
            bokSummaryTableNew.setInvAim(invAimNow);
            // 变量.当日统计分析账务明细汇总信息.当日成本 = 变量.成本科目余额
            bokSummaryTableNew.setTdyCostAmt(costSjtAmtVar);
            // 变量.当日统计分析账务明细汇总信息.金融产品类型 = 交易标的DTO.金融产品类型
            bokSummaryTableNew.setFinprodType(dto.getBusiDetailType());
            // 账套代码
            bokSummaryTableNew.setBooksetId(acctEntity.getBooksetId());
            // 发生日期
            bokSummaryTableNew.setHappenDate(acctEntity.getHappenDate());
            // 入账日期
            bokSummaryTableNew.setBookDate(acctEntity.getBookDate());
            // 账务明细代码
            bokSummaryTableNew.setBokDetailId(bokDetailIdNow);
            // 账套日期
            bokSummaryTableNew.setBooksetDate(dto.getBooksetDate());
            // 处理序列
            bokSummaryTableNew.setBookSummaryOrder(
                new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.账务汇总表_处理序列)));
            // 变量.当日统计分析账务明细汇总信息.起息日 = 交易标的DTO.起息日
            bokSummaryTableNew.setVdate(dealInfoForTarget.getVdate());
            // 变量.当日统计分析账务明细汇总信息.到期日 = 交易标的DTO.实际到期日
            bokSummaryTableNew.setMdate(dealInfoForTarget.getMdate());
            // 变量.当日统计分析账务明细汇总信息.剩余期限 = 变量.标的信息.实际到期日-计提类账务处理结果.发生日
            bokSummaryTableNew.setEndDays1(new BigDecimal(DateUtil.getBetweenDayNumber(
                acctEntity.getHappenDate(), dealInfoForTarget.getMdate())));
            // 变量.当日统计分析账务明细汇总信息.剩余存续期 = 变量.标的信息.实际到期日-计提类账务处理结果.发生日
            bokSummaryTableNew.setEndDays2(new BigDecimal(DateUtil.getBetweenDayNumber(
                acctEntity.getHappenDate(), dealInfoForTarget.getMdate())));
            // 变量.当日统计分析账务明细汇总信息.业务明细代码 = 计提类账务处理结果.业务明细代码
            bokSummaryTableNew.setBusiId(acctEntity.getBusiId());

            /** modify by yanya on 20191209 币种 本位币 start **/
            bokSummaryTableNew.setCcy(dealInfoForTarget.getCcy());
            //本位币
            bokSummaryTableNew.setbCcy(bokSummaryTableNew.getCcy());
            /** modify by yanya on 20191209 币种 本位币 end **/
            // 添加到返回值中
            returnResult.getBokSummaryList().add(bokSummaryTableNew);
        }

        return returnResult;
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
     * @param invAim 投资目的
     * @param taskPri 任务优先级
     * @return SubjectBalance 科目余额
     * @see
     * @since 1.0
     */
    private SubjectBalance getSubjectBalance(String booksetId, String bokDetailId, Date happenDate,
                                             Date bookDate, String expBalanceDirect,
                                             String baseSubjectNature, String invAim,
                                             Long taskPri) {
        // 定义返回的科目余额
        SubjectBalance subjectBalance = new SubjectBalance();
        subjectBalance.setBalance(BigDecimal.ZERO); // 初始化科目余额 = 0
        subjectBalance.setNum(BigDecimal.ZERO); // 初始化数余额 = 0

        // 塞入要查询的基础科目性质
        List<String> baseSubjectNatureList = null;
        if (baseSubjectNature != null && baseSubjectNature.length() > 0) {
            baseSubjectNatureList = new ArrayList<String>();
            baseSubjectNatureList.add(baseSubjectNature);
        }

        // 塞入投资目的
        List<String> invAimList = null;
        if (invAim != null && invAim.length() > 0) {
            invAimList = new ArrayList<String>();
            invAimList.add(invAim);
        }

        // 设值科目余额查询参数Dto
        SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();
        searchDto.setBooksetId(booksetId);
        searchDto.setBokDetailId(bokDetailId);
        searchDto.setHappenDate(happenDate);
        searchDto.setBookDate(bookDate);
        searchDto.setInvAimList(invAimList);
        searchDto.setTaskPri(taskPri);
        searchDto.setBaseSubjectNatureList(baseSubjectNatureList);
        // 调用公用方法：【科目余额查询 - 期待方向】,获取科目余额信息
        List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(
            searchDto, expBalanceDirect);

        // 获取科目余额
        if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
            subjectBalance = subjectBalanceList.get(0);
        }

        return subjectBalance;
    }

    /**
     * 获取 报价信息 列表
     * 
     * @param busiId 业务明细代码
     * @return List<QuoteInfo> 报价信息list
     * @see
     * @since 1.0
     */
    private List<QuoteInfo> getQuoteInfoList(String busiId) {
        // 定义查询list
        List<TargetSearchDto> queryCriteriaList = new ArrayList<TargetSearchDto>();
        // 定义[标的查询 - 查询类型dto]
        TargetSearchDto targetSearchDto = new TargetSearchDto();
        targetSearchDto.setQueryType(ValDictCodeList.标的_现金流计算规则.getValue());
        
        // 针对估值一体化：估值【拆借计提】获取产品报价信息，不支持投管【计提式费用】，故只取利息。
        targetSearchDto.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        
        // 塞入list
        queryCriteriaList.add(targetSearchDto);

        // 获取标的信息查询
        Map<TargetSearchDto, Object> targetInfoMap = finProductCommonService.getTargetInfo(busiId,
            queryCriteriaList);

        // 获取[标的_现金流计算规则]List
        @SuppressWarnings("unchecked")
        List<TargetCashCalcRule> targetCashPayRuleList = (List<TargetCashCalcRule>)targetInfoMap.get(
            targetSearchDto);

        // 将[标的_现金流计算规则]List，转换成需要的[报价信息]List。
        List<QuoteInfo> quoteInfoList = new ArrayList<>();
        for (TargetCashCalcRule vo : targetCashPayRuleList) {
            // 定义报价信息实体类
            QuoteInfo quoteInfo = new QuoteInfo();
            quoteInfo.setIssueDate(vo.getEffDate());// 生效日
            quoteInfo.setInterest(vo.getYield());// 利率
            quoteInfo.setAccountBasis(vo.getBasis());// 计息基础

            quoteInfoList.add(quoteInfo);
        }

        return quoteInfoList;
    }
    
    /**
     * 调用共通方法：[标的类共通方法-查询交易信息]，获取标的交易信息
     * 
     * @param finprodId 金融产品代码【必输项】
     * @param finprodType 金融产品类型【必输项】
     * @param branch 分支序号
     * @return ValFinProductDeal 标的查询
     * @see
     * @since 1.0
     */
    private TradeInfoDto getTargetInfoDto(String finprodId, String finprodType, Long branch) {
        // 定义返回值 - 标的交易信息
        TradeInfoDto tradeInfoDtoForReturn = null;

        // 调用共通方法：[标的类共通方法 - 交易信息查询]，获取标的交易信息
        List<TradeInfoDto> tradeInfoDtoList = finProductCommonService.getTradeInfoDtoList(
            finprodId, finprodType, branch);

        // 当查询结果不为空
        if (tradeInfoDtoList != null && tradeInfoDtoList.size() > 0) {
            // 默认取第一条标的信息
            tradeInfoDtoForReturn = tradeInfoDtoList.get(0);
        }// 上述查询结果为空时，调用标的信息查询。
        else{
            // 调用共通设计->【详细设计-标的类共通方法设计.xlsx】->标的信息查询SHEET
            tradeInfoDtoForReturn = this.getTargetFinProductInfo(finprodId);

        }

        return tradeInfoDtoForReturn;
    }
    
    /**
     * 获取标的基本信息
     *
     * @param busiId 业务明显代码
     * @return TargetFinProduct 标的基本信息
     * @see
     * @since 1.0
     */
    private TradeInfoDto getTargetFinProductInfo(String busiId) {
        TradeInfoDto dto = null;
        // 定义查询list
        List<TargetSearchDto> targetSearchDtoList = new ArrayList<TargetSearchDto>();
        // 定义查询类型 实体类
        TargetSearchDto targetSearchDto = new TargetSearchDto();
        targetSearchDto.setQueryType(ValDictCodeList.标的_信息.getValue());
        // 查询实体类塞入查询实体类List中
        targetSearchDtoList.add(targetSearchDto);

        // 获取标的信息
        Map<TargetSearchDto, Object> targetMap = finProductCommonService.getTargetInfo(busiId, targetSearchDtoList);
        TargetFinProduct targetFinProduct = (TargetFinProduct)targetMap.get(targetSearchDto);

        // 确保不为null
        if (targetFinProduct != null) {
            dto = new TradeInfoDto();
            dto.setOFinprodId(targetFinProduct.getoFinprodId());
            dto.setOFinprodType(targetFinProduct.getoFinprodType());
            dto.setVdate(targetFinProduct.getVdate());
            dto.setMdate(targetFinProduct.getMdate());
            dto.setCcy(targetFinProduct.getCcy());
            dto.setType1(targetFinProduct.getType1());
            dto.setType2(targetFinProduct.getType2());
        }
        return dto;
    }

}
