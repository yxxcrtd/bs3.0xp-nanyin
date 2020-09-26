/*
 * 文件名：DepositeAssetTransBookService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.depositeassettransbook.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 存款类资产交易账务处理 process相关业务处理
 * 
 * @author weipeng
 * @version 1.0 2018年5月23日
 * @see DepositeAssetTransBookService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetTransBookService {

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
	 * 计算类共通方法
	 */
	@Autowired
	private CalculationCommonService calculationCommonService;

	/**
	 * process 数据处理
	 * 
	 * @param param1
	 *            参数
	 * @return BookGenerateResult
	 * @see
	 * @since 1.0
	 */
	public BookGenerateResult execute(Map<String, Object> param1) {
		// 获取reader返回值
		FinProductDealInfoResult acctEntity = (FinProductDealInfoResult) param1.get(GlobalConstant.READER_RESULT);

		// 若reader传递的参数为空，不进行后续处理
		if (acctEntity == null) {
			return null;
		}

		// 定义返回值
		BookGenerateResult returnResult = new BookGenerateResult();
		// 防止报空指针异常
		if (returnResult.getValBokBookdetailBatch() == null || acctEntity.getListFinProductDealInfoComDTO() == null
				|| acctEntity.getListFinProductDealInfoComDTO().isEmpty()) {
			return null;
		}
		// 获取从reader中拿到的值
		String booksetIdNow = acctEntity.getBooksetId();// 账套代码
		String bokDetailId = acctEntity.getBokDetailId();// 账务明细代码
		TreeNode bookSetTreeNodNow = acctEntity.getBookSetTreeNode();// 会计场景
		Date happenDateNow = acctEntity.getHappenDate();// 发生日期
		Date bookDateNow = acctEntity.getBookDate();// 入账日期
		List<FinProductDealInfoComDTO> listFinProductDealInfoComDTONow = acctEntity.getListFinProductDealInfoComDTO(); // 金融产品交易信息list
		Long taskPri = listFinProductDealInfoComDTONow.get(0).getTaskPri();// 任务优先级（信息列表的任务优先级都是一致的，故取第一个）
		/** modify by yanya on 20191230 增加本位币处理 start**/
		String standardCcyNow = acctEntity.getStandardCcy();
		/** modify by yanya on 20191230 增加本位币处理 end**/

		// 1. 数据准备
		// 1.1 定义变量
		// 定义 变量.成本科目余额
		BigDecimal costBalance = BigDecimal.ZERO;
		// 定义 变量.成本科目数量
		BigDecimal costQuantity = BigDecimal.ZERO;
		// 定义 变量.应收利息科目余额
		BigDecimal interestBalance = BigDecimal.ZERO;
		// 变量.其他应收款-利息科目余额MAP
		Map<Long, BigDecimal> otherInterestBalanceMap = new HashMap<Long, BigDecimal>();

		// 2.调用共通查询发生前一日余额
		// 交易期待方向——借方
		String expBalanceDirect = DictCodeList.余额方向_借方.getValue();

		// 2.1 获取成本类科目余额 -【 调用共通方法：科目余额查询,获取成本类科目余额 】
		// 基础科目性质 : 成本
		SubjectBalance subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow, bookDateNow,
				expBalanceDirect, DictCodeBaseSubjectNatrue.成本.getValue(), null, taskPri);
		// 成本类科目余额赋值
		costBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO : subjectBalance.getBalance();
		// 成本类科目数量赋值
		costQuantity = subjectBalance.getNum() == null ? BigDecimal.ZERO : subjectBalance.getNum();

		// 2.2 获取应收利息类科目余额 -【 调用共通方法：科目余额查询,获取成本类科目余额 】
		// 基础科目性质 : 应计利息
		subjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId, happenDateNow, bookDateNow, expBalanceDirect,
				DictCodeBaseSubjectNatrue.应计利息.getValue(), null, taskPri);
		// 变量.应计利息余额 = DTO.科目余额
		interestBalance = subjectBalance.getBalance() == null ? BigDecimal.ZERO : subjectBalance.getBalance();

		// 3. 交易处理
		// 循环 交易类账务处理结果.交易明细列表,进行逻辑处理
		for (FinProductDealInfoComDTO dealNow : listFinProductDealInfoComDTONow) {

			// 获取标的信息.金融产品类型
			TargetFinProduct targetFinProduct = this.getTargetFinProduct(dealNow.getFinprodId());

			AccountDriver ad = new AccountDriver();// 凭证类型、账套代码、交易编号全部去掉了。
			ad.setTreeNode(bookSetTreeNodNow);// 塞入记账场景树节点 = 会计场景

			// 初始化定义 - 存放该笔记账的场景要素 map定义
			Map<String, Object> accountSceneMap = new HashMap<>();
			// 初始化定义 - 存放该笔记账的相关金额要素 map定义
			Map<String, BigDecimal> accountAmountMap = new HashMap<>();
			// 初始化定义 - 存放该笔记账的相关金额对应的币种 map定义
			Map<String, Object> accountCurrencyMap = new HashMap<>();

			// 调用共通场景元素赋值-交易类场景元素
			accountSceneMap = bookCommonLogic.transactionElementAssign(acctEntity, dealNow);

			// accountSceneMap塞值
			// 备注 = 存款类资产交易登账
			accountSceneMap.put(BookConstant.BOOK_REMARK, "存款类资产交易登账");
			// 分类1 <- 变量.交易信息.分类1
			accountSceneMap.put(BookConstant.TYPE_1, targetFinProduct.getType1());
			// 分类2 <- 变量.交易信息.分类2
			accountSceneMap.put(BookConstant.TYPE_2, targetFinProduct.getType2());

			// if（交易方向 = "买入"）
			if (DictCodeList.交易方向_买入.getValue().equals(dealNow.getPs())) {
				// 设置账务引擎参数
				ad.setTableId(DictCodeListTableId.会计场景代码_存放同业_起息或买入.getValue());// ad.会计场景代码=存款类资产买入;

				// 记账金额元素map塞值
				// 本金金额 <- 交易明细.交易本金
				accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
				// 本息和 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT,
						ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
				// 应收利息 <- 交易明细.利息总额
				accountAmountMap.put(BookConstant.INTAMT, ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY));

				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}
				/** modify by yanya on 20191230 增加本位币处理 start**/
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				//if(交易明细.币种 != 交易类账务处理结果..本位币){
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
                    /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
                    if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
                        汇率 = currencyMarketList.get(0).getClosePrice();
                    }
                    /** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/

					//本币本金金额	<-	本金金额 * 汇率	用舍位处理后的结果计算并保留2位小数
					BigDecimal 本币本金金额 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.COST), 汇率),
							NumberEnum.MONEY);
					//本币应收利息	<-	应收利息 * 汇率	用舍位处理后的结果计算并保留2位小数
					BigDecimal 本币应收利息 = ArithmeticUtil.round(ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.INTAMT), 汇率),
							NumberEnum.MONEY);
					//本币本息和	<-	本币本金金额 + 本币应收利息
					BigDecimal 本币本息和 =  ArithmeticUtil.doAdd(本币本金金额, 本币应收利息);
					//本币本金金额
					accountAmountBCCYAMTMap.put(BookConstant.COST + BookConstant.BCCYAMT, 本币本金金额);
					// 本币应收利息
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, 本币应收利息);
					// 本币本息和
					accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, 本币本息和);
				}
				else {
					//本币本金金额 <- 本金金额
					//accountAmountBCCYAMTMap.put(BookConstant.COST + BookConstant.BCCYAMT, accountAmountMap.get(BookConstant.COST));
					//本币应收利息 <- 应收利息
					//accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, accountAmountMap.get(BookConstant.INTAMT));
					//本币本息和 <- 本息和
					//accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, accountAmountMap.get(BookConstant.DEALAMT));
					for (String key : accountAmountMap.keySet()) {
						accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT, accountAmountMap.get(key));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				/** modify by yanya on 20191230 增加本位币处理 end**/

				// 全局变量的更新
				// 变量.成本科目余额 = 变量.成本科目余额 + 交易明细.交易本金
				costBalance = ArithmeticUtil.doAdd(costBalance, dealNow.getPrinAmt());
				// 变量.成本科目数量 = 变量.成本科目数量 + 交易明细.交易份额
				costQuantity = ArithmeticUtil.doAdd(costQuantity, dealNow.getShareAmt());
				// 变量.应收利息科目余额 = 变量.应收利息科目余额 + 交易明细.利息总额
				interestBalance = ArithmeticUtil.doAdd(interestBalance, dealNow.getIntAmt());
			}
			// 交易方向 = 收息到账
			else if (DictCodeList.交易方向_收息到账.getValue().equals(dealNow.getPs())) {

				ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_存放同业收息.getValue());// ad.会计场景代码=存款类资产收息;

				// 定义:变量.其他应收款-利息科目余额 = 变量.其他应收款 - 利息科目余额MAP.get(交易明细.分支序号)
				BigDecimal otherInterestBalanc = otherInterestBalanceMap.get(dealNow.getBranch());

				// 变量.其他应收款-利息科目余额 == null
				if (otherInterestBalanc == null) {
					// if(变量.标的信息.分类1 = 【数据字典】.存款类资产类型.活期类资产)
					if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
						// 查询交易的到期日对应的应计利息
						SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
								DateUtil.addDay(dealNow.getMdate(), -1), dealNow.getBooksetDate(),
								DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.应计利息.getValue(),
								null, null);

						// 非空判断
						if (otherSubjectBalance != null) {
							// 变量.应收利息 = 上述共通返回的科目余额
							otherInterestBalanc = otherSubjectBalance.getBalance();
							// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.应收利息)
							otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
						}

					} else {
						// 查询交易的到期日对应的应计利息
						SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
								dealNow.getHappenDate(), dealNow.getBooksetDate(), DictCodeList.余额方向_借方.getValue(),
								DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getBranch(), taskPri);

						// 非空判断
						if (otherSubjectBalance != null) {
							// 变量.其他应收款-利息科目余额 = 上述共通返回的科目余额
							otherInterestBalanc = otherSubjectBalance.getBalance();
							// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
							otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
						}
					}

				}

				// 调用金额共通方法设置有效小数位
				otherInterestBalanc = ArithmeticUtil.round(otherInterestBalanc, NumberEnum.MONEY);

				/** modify by yanya on 20191230 增加本位币、通道费 处理 start**/
				// 记账金额元素map塞值
//				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())
//						&& DictCodeListExt1.活期类存放同业收息方式_计入成本.getValue()
//								.equals(CacheUtil.getSysParam(DictCode.活期类存放同业收息方式.getValue()))) {
//					// 实收成本 <- 交易明细.交易金额
//					accountAmountMap.put(BookConstant.COST,
//							ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
//					//MODIFY JINMAODONG 成本科目数量需要调整
//					accountSceneMap.put(BookConstant.HAPPEN_NUMBER, dealNow.getTradeAmt());
//
//				}
//				else{
//					// 实收金额 <- 交易明细.交易金额
//					accountAmountMap.put(BookConstant.DEALAMT,
//							ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
//				}

				//实收金额 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT, ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
				//通道费处理
				//调用 共通【费用处理共通】  参考本设计书sheet【费用处理共通】 定义 变量.交易各种费用 = 返回值
				DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(dealNow.getValTradeFeeDetailList(),
						dealNow.getProdId(), dealNow.getPortfolioId(), dealNow.getFinprodId());
				//实付通道费	<-	变量.交易各种费用.通道费
				BigDecimal 实付通道费 = dealTypesFeeDto.getChlFee();
				accountAmountMap.put(BookConstant.REAL_CHLAMT, ArithmeticUtil.round(实付通道费, NumberEnum.MONEY));
				//通道费账务明细代码 <-	变量.交易各种费用.通道费账务明细代码
				//通道费业务明细代码 <- 变量.交易各种费用.通道费业务明细代码
				BokDetailId chlFeeCode = dealTypesFeeDto.getChlFeeCode();
				accountSceneMap.put(BookConstant.BOK_DETAIL_ID3, chlFeeCode);

                // 定义：金额元素.应收利息
				BigDecimal yslxForJe = BigDecimal.ZERO;

				// if（交易明细.是否结清 = "是"）
				if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
					// 金额元素.应收利息 <- 交易明细.交易金额
					yslxForJe = ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY);
					accountAmountMap.put(BookConstant.INTAMT, yslxForJe);
					// 利息差额 <- 0
					accountAmountMap.put(BookConstant.INCOMEAMT, BigDecimal.ZERO);

				} else {
					// 金额元素：应收利息 <- 变量.其他应收款-利息科目余额
					yslxForJe = otherInterestBalanc == null ? BigDecimal.ZERO : otherInterestBalanc;
					accountAmountMap.put(BookConstant.INTAMT, yslxForJe);
					// 交易明细.交易金额 - 金额元素.应收利息 > 0
					//if 交易明细.交易金额 - 金额元素.应收利息  -实付通道费 > 0
					BigDecimal subVal = ArithmeticUtil.doSub(dealNow.getTradeAmt(), yslxForJe, 实付通道费);
					if (subVal.compareTo(BigDecimal.ZERO) > 0) {
						// 利息差额_贷 <- 交易明细.交易金额 - 金额元素.应收利息 - 实付通道费
						accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(subVal,
								NumberEnum.MONEY.getCode(), null));
					} else {
						// 利息差额_借 <- （交易明细.交易金额 - 金额元素.应收利息 - 实付通道费）*-1
						accountAmountMap.put(BookConstant.INCOMEAMT_D,
								ArithmeticUtil.round(ArithmeticUtil.multiply(new BigDecimal("-1"), subVal), NumberEnum.MONEY));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
                    //基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
					//本币实收金额/本币实收成本（COST）<- 实收金额/本币实收成本（COST） * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币实收金额 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.DEALAMT), 汇率),
							NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, 本币实收金额);
					//本币应收利息 <- 应收利息 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币应收利息 = ArithmeticUtil.round(ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.INTAMT), 汇率),
							NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, 本币应收利息);
					//本币实付通道费	<-	实付通道费 * 汇率
					BigDecimal 本币实付通道费 = ArithmeticUtil.round(ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.REAL_CHLAMT), 汇率),
							NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT, 本币实付通道费);
					//if 交易明细.利息总额 - 变量.冲减应收利息 - 实付通道费	>0
					BigDecimal subVal = ArithmeticUtil.doSub(本币实收金额, 本币应收利息, 本币实付通道费);
					if (subVal.compareTo(BigDecimal.ZERO) > 0) {
						//本币利息差额_贷	 <-	本币实收金额/本币实收成本(COST)- 本币应收利息 - 本币实付通道费	保留金额小数位
						accountAmountBCCYAMTMap.put(BookConstant.INCOMEAMT_C + BookConstant.BCCYAMT, ArithmeticUtil.round(subVal,
								NumberEnum.MONEY.getCode(), null));
					}
					//else
					else {
						//本币利息差额_借	<-	（本币实收金额/本币实收成本（COST）- 本币应收利息 - 本币实付通道费) * -1	保留金额小数位
						accountAmountBCCYAMTMap.put(BookConstant.INCOMEAMT_D + BookConstant.BCCYAMT,
								ArithmeticUtil.round(ArithmeticUtil.multiply(new BigDecimal("-1"), subVal), NumberEnum.MONEY));
					}

				}
				else {
					//本币实收金额/本币实收成本（COST）<- 实收金额/实收成本（COST）
					//本币应收利息<-		应收利息
					//本币利息差额_贷/借 <-	利息差额_贷/借
					//本币实付通道费 <-	实付通道费
					for (String key : accountAmountMap.keySet()) {
						accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT, accountAmountMap.get(key));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				/** modify by yanya on 20191230 增加本位币、通道费 处理 end**/

				// 更新全局变量
				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
					// 变量.其他应收款-利息科目余额 = 变量.应计利息科目余额 - 金额元素.应收利息
					//otherInterestBalanc = ArithmeticUtil.doSub(interestBalance, yslxForJe);
					//变量.应收利息科目余额	=变量.应收利息科目余额 - 变量.冲减应收利息
					interestBalance = ArithmeticUtil.doSub(interestBalance, yslxForJe);
				} else {
					// 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 金额元素.应收利息
					otherInterestBalanc = ArithmeticUtil.doSub(otherInterestBalanc, yslxForJe);
					// 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
					otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
				}

				// 基础科目性质
				Map<String, Map<String, Object>> accountNatrueMap = new HashMap<String, Map<String, Object>>();
				Map<String, Object> accountNatrueBranchMap = new HashMap<String, Object>();
				accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
					accountNatrueMap.put(DictCodeBaseSubjectNatrue.应计利息.getValue(), accountNatrueBranchMap);
				} else {
					accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
				}
				ad.setAccountNatrueMap(accountNatrueMap);

			}
			// 交易方向 = 卖出
			else if (DictCodeList.交易方向_卖出.getValue().equals(dealNow.getPs())
					|| DictCodeList.交易方向_提前支取.getValue().equals(dealNow.getPs())) {
				ad.setTableId(DictCodeListTableId.会计场景代码_存放同业_到期或卖出.getValue());// ad.会计场景代码=会计场景代码_存放同业_到期或卖出;
				// 定义 变量.冲减应收利息
				BigDecimal scourInterest = BigDecimal.ZERO;
				// 计算冲减成本
				BigDecimal scourCost = BigDecimal.ZERO;
				// 计算冲减数量
				BigDecimal scourQuantity = BigDecimal.ZERO;
				// 活期存款类资产判断
				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
					// 定义 变量.冲减应收利息 交易明细.利息金额
					scourInterest = dealNow.getIntAmt();
					// 计算冲减成本 变量.成本科目余额 * 交易明细.交易份额/变量.成本科目数量
					scourCost = ArithmeticUtil.divide(ArithmeticUtil.multiply(costBalance, dealNow.getShareAmt()),
							costQuantity, NumberEnum.MONEY.getCode());
					// 计算冲减数量 交易明细.交易份额
					scourQuantity = dealNow.getShareAmt();
				} else {
					// 定义 变量.冲减应收利息 ,设置有效小数位
					scourInterest = ArithmeticUtil.divide(
							ArithmeticUtil.multiply(interestBalance, dealNow.getShareAmt()), costQuantity,
							NumberEnum.MONEY.getCode());
					// 计算冲减成本
					scourCost = ArithmeticUtil.divide(ArithmeticUtil.multiply(costBalance, dealNow.getShareAmt()),
							costQuantity, NumberEnum.MONEY.getCode());
					// 计算冲减数量
					scourQuantity = dealNow.getShareAmt();
				}
				// 记账金额元素map塞值
				// 本息和 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT, dealNow.getTradeAmt());
				// 本金金额 <- 变量.冲减成本
				accountAmountMap.put(BookConstant.COST, scourCost);
				// 应收利息 <- 变量.冲减应收利息
				accountAmountMap.put(BookConstant.INTAMT, scourInterest);

                /** modify by yanya on 20191231 增加本位币、通道费 处理 start**/
                //调用 共通【费用处理共通】  参考本设计书sheet【费用处理共通】
				DealTypesFeeDto dealTypesFeeDto = calculationCommonService.quaryDealTypesFeeamt(dealNow.getValTradeFeeDetailList(),
						dealNow.getProdId(), dealNow.getPortfolioId(), dealNow.getFinprodId());
				//实付通道费	<-	变量.交易各种费用.通道费
				BigDecimal 实付通道费 = dealTypesFeeDto.getChlFee();
				accountAmountMap.put(BookConstant.REAL_CHLAMT, ArithmeticUtil.round(实付通道费, NumberEnum.MONEY));
				//通道费账务明细代码 <-	变量.交易各种费用.通道费账务明细代码
				//通道费业务明细代码 <- 变量.交易各种费用.通道费业务明细代码
				BokDetailId chlFeeCode = dealTypesFeeDto.getChlFeeCode();
				accountSceneMap.put(BookConstant.BOK_DETAIL_ID2, chlFeeCode);

				//定义变量 交易明细.利息总额 - 变量.冲减应收利息 - 实付通道费
				BigDecimal subVal = ArithmeticUtil.doSub(dealNow.getIntAmt(), scourInterest, 实付通道费);
				// if 交易明细.利息总额 - 变量.冲减应收利息 - 实付通道费 >0
				if (subVal.compareTo(BigDecimal.ZERO) > 0) {
					// 利息差额_贷 <- 交易明细.利息总额 - 变量.应收利息 - 实付通道费
					accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(subVal, NumberEnum.MONEY.getCode(), null));
				} else {
					// 利息差额_借 <- （交易明细.利息总额 - 变量.应收利息 - 实付通道费 ）*-1
					accountAmountMap.put(BookConstant.INCOMEAMT_D, ArithmeticUtil.round(ArithmeticUtil.multiply(new BigDecimal("-1"), subVal),
							NumberEnum.MONEY));
				}

				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
				}
				//本币实收金额/本币实收成本（COST）<- 实收金额/本币实收成本（COST） * 汇率	用舍位处理后的结果计算并保留金额小数位
				for (String key : accountAmountMap.keySet()) {
					//本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);保留金额元素小数位数
					accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
							ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率), NumberEnum.MONEY));
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				if (subVal.compareTo(BigDecimal.ZERO) > 0) {
					//本息和_本位币<- 本金金额_本位币 + 应收利息_本位币 + 本币实付通道费_本位币 + 本币利息差额_贷_本位币
					BigDecimal 本息和 = ArithmeticUtil.doAdd(
							accountAmountBCCYAMTMap.get(BookConstant.COST + BookConstant.BCCYAMT),
							accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),
							accountAmountBCCYAMTMap.get(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT),
							accountAmountBCCYAMTMap.get(BookConstant.INCOMEAMT_C + BookConstant.BCCYAMT));
					accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(本息和 ,NumberEnum.MONEY));
					accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);
				}
				else {
					//本息和_本位币 <- 本金金额_本位币 + 应收利息_本位币 + 本币实付通道费_本位币 - 本币利息差额_借_本位币
					BigDecimal 本息和 = ArithmeticUtil.doSub(
							ArithmeticUtil.doAdd(
									accountAmountBCCYAMTMap.get(BookConstant.COST + BookConstant.BCCYAMT),
									accountAmountBCCYAMTMap.get(BookConstant.INTAMT + BookConstant.BCCYAMT),
									accountAmountBCCYAMTMap.get(BookConstant.REAL_CHLAMT + BookConstant.BCCYAMT)),
							accountAmountBCCYAMTMap.get(BookConstant.INCOMEAMT_D + BookConstant.BCCYAMT)
					);
					accountAmountMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, ArithmeticUtil.round(本息和 ,NumberEnum.MONEY));
					accountCurrencyMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, standardCcyNow);
				}

				/** modify by yanya on 20191231 增加本位币、通道费 处理 end**/

				// 更新全局变量
				// 变量.成本科目余额 = 变量.成本科目余额 - 变量.冲减成本
				costBalance = ArithmeticUtil.doSub(costBalance, scourCost);
				// 变量.成本科目数量 = 变量.成本科目数量 - 变量.冲减数量
				costQuantity = ArithmeticUtil.doSub(costQuantity, scourQuantity);
				// 变量.应收利息科目余额 = 变量.应收利息科目余额 - 变量.冲减应收利息
				interestBalance = ArithmeticUtil.doSub(interestBalance, scourInterest);

			}
			// 交易方向 = 还本到账
			else if (DictCodeList.交易方向_还本到账.getValue().equals(dealNow.getPs())) {

				// ad.会计场景代码=存放同业还本到账;
				ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_存放同业还本_到期.getValue());

				// 记账金额元素map塞值
				// 交易金额 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT,
						ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));
				// 交易本金 <- 交易明细.全价总额
				accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getFpriceAmt(), NumberEnum.MONEY));

				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}

				/** modify by yanya on 20191231 增加本位币 处理 start**/
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
				}
				//本币实收金额/本币实收成本（COST）<- 实收金额/本币实收成本（COST） * 汇率	用舍位处理后的结果计算并保留金额小数位
				for (String key : accountAmountMap.keySet()) {
					//本币金额元素Map.put(金额元素+_本位币 , accountAmountMap.get(key) * 变量.汇率);保留金额元素小数位数
					accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT,
							ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(key), 汇率), NumberEnum.MONEY));
				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				/** modify by yanya on 20191231 增加本位币 处理 end**/

				// 基础科目性质
				Map<String, Map<String, Object>> accountNatrueMap = new HashMap<String, Map<String, Object>>();
				Map<String, Object> accountNatrueBranchMap = new HashMap<String, Object>();
				accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
				accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
				ad.setAccountNatrueMap(accountNatrueMap);

			}
			// 交易方向 = 应收款项转入
			else if (DictCodeList.交易方向_应收款项转入.getValue().equals(dealNow.getPs())) {

				// ad.会计场景代码="应收款项转仓(转入)"
				ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转入.getValue());

				// 定义:变量.其他应收款-利息科目余额 = 变量.其他应收款 - 利息科目余额MAP.get(交易明细.分支序号)
				BigDecimal otherInterestBalanc = otherInterestBalanceMap.get(dealNow.getBranch());

				// 变量.其他应收款-利息科目余额 == null
				if (otherInterestBalanc == null) {
					// 查询交易的到期日对应的应计利息
					SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
							dealNow.getHappenDate(), dealNow.getBooksetDate(), DictCodeList.余额方向_借方.getValue(),
							DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getBranch(), taskPri);

					// 非空判断
					if (otherSubjectBalance != null) {
						// 变量.其他应收款-利息科目余额 = 上述共通返回的科目余额
						otherInterestBalanc = otherSubjectBalance.getBalance();
						// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
						otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
					}
				}

				// 调用金额共通方法设置有效小数位
				otherInterestBalanc = ArithmeticUtil.round(otherInterestBalanc, NumberEnum.MONEY);

				// 记账场景元素map塞值
				// 应收款项-利息 <- 交易明细.应计利息总额
				BigDecimal yskxlxForJe = ArithmeticUtil.round(dealNow.getIntAmt(), NumberEnum.MONEY);
				accountAmountMap.put(BookConstant.INTAMT, yskxlxForJe);
				// 逾期款项-本金 <- 交易明细.交易本金
				accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
				// 实收金额 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT,
						ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}
				/** modify by yanya on 20191230 增加本位币 处理 start**/
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
					//本币逾期款项-本金<-		逾期款项-本金 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币本金 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.COST), 汇率), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.COST + BookConstant.BCCYAMT, 本币本金);
					//本币应收款项-利息	<-		应收款项-利息 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币利息 = ArithmeticUtil.round(ArithmeticUtil.multiply(accountAmountMap.get(BookConstant.INTAMT), 汇率), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, 本币利息);
					//本币实收金额	<-		本币逾期款项-本金 + 本币应收款项-利息
					BigDecimal 本币实收金额 = ArithmeticUtil.round(ArithmeticUtil.doAdd(本币本金, 本币利息), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, 本币实收金额);

				}
				else {
					//本币应收款项-利息	<-		应收款项-利息
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, yskxlxForJe);
					//本币逾期款项-本金	<-		逾期款项-本金
					accountAmountBCCYAMTMap.put(BookConstant.COST + BookConstant.BCCYAMT,
							ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
					//本币实收金额	<-		实收金额
					accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT,
							ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				/** modify by yanya on 20191230 增加本位币 处理 end**/

				// 更新全局变量
				// 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 + 金额元素.应收款项-利息
				otherInterestBalanc = ArithmeticUtil.doAdd(otherInterestBalanc, yskxlxForJe);
				// 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
				otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);

				// 基础科目性质
				Map<String, Map<String, Object>> accountNatrueMap = new HashMap<String, Map<String, Object>>();
				Map<String, Object> accountNatrueBranchMap = new HashMap<String, Object>();
				accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
				accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
				accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
				ad.setAccountNatrueMap(accountNatrueMap);

			}
			// 交易方向 = 应收款项转出
			else if (DictCodeList.交易方向_应收款项转出.getValue().equals(dealNow.getPs())) {

				// ad.会计场景代码="应收款项转仓(转出)"
				ad.setTableId(DictCodeListTableId.会计场景代码_应收款项转仓转出.getValue());

				/** add by yanya on 20200117 增加类型：资产类型、境内外 start**/
				// 境内外 = 变量.交易信息.境内外
				accountSceneMap.put(BookConstant.BLN_AREA, targetFinProduct.getBlnArea());
				//资产类型
				accountSceneMap.put(BookConstant.ASSET_TYPE, targetFinProduct.getFinprodType());
				/** add by yanya on 20200117 增加类型：资产类型、境内外 end**/

				// 定义:变量.其他应收款-利息科目余额 = 变量.其他应收款 - 利息科目余额MAP.get(交易明细.分支序号)
				BigDecimal otherInterestBalanc = otherInterestBalanceMap.get(dealNow.getBranch());

				// 变量.其他应收款-利息科目余额 == null
				if (otherInterestBalanc == null) {
					// 查询交易的到期日对应的应计利息
					SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
							dealNow.getHappenDate(), dealNow.getBooksetDate(), DictCodeList.余额方向_借方.getValue(),
							DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getBranch(), taskPri);

					// 非空判断
					if (otherSubjectBalance != null) {
						// 变量.其他应收款-利息科目余额 = 上述共通返回的科目余额
						otherInterestBalanc = otherSubjectBalance.getBalance();
						// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
						otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
					}
				}

				// 调用金额共通方法设置有效小数位
				otherInterestBalanc = ArithmeticUtil.round(otherInterestBalanc, NumberEnum.MONEY);

				// 记账场景元素map塞值
				// 定义：变量.应计利息
				BigDecimal yjlxNow = BigDecimal.ZERO;
				// 定义：变量.轧差金额
				BigDecimal zcjeNow = BigDecimal.ZERO;

				/** modify by yanya on 20200116 start**/
				// if（交易明细.是否结清 = "否"）
				if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
				/** modify by yanya on 20200116 end**/
					// 变量.应计利息 <- 交易明细.应计利息总额
					yjlxNow = dealNow.getIntAmt();
					// 变量.轧差金额 <- 0
					zcjeNow = BigDecimal.ZERO;
				} else {
					// 变量.应计利息 <- 变量.其他应收款-利息科目余额
					yjlxNow = otherInterestBalanc;
					// 变量.轧差金额 = 交易明细.应计利息总额 - 变量.应计利息
					zcjeNow = ArithmeticUtil.doSub(dealNow.getIntAmt(), yjlxNow);

					// 变量.轧差金额 >= 0
					if (zcjeNow.compareTo(BigDecimal.ZERO) >= 0) {
						// 轧差金额(贷) <- 变量.利息差额(变量.轧差金额)
						accountAmountMap.put(BookConstant.NETAMT_C, ArithmeticUtil.round(zcjeNow, NumberEnum.MONEY));
					} else {
						// 轧差金额(借) <- 变量.利息差额(变量.轧差金额)*-1
						accountAmountMap.put(BookConstant.NETAMT_D,
								ArithmeticUtil.round(zcjeNow, NumberEnum.MONEY).abs());
					}
				}

				// 应收款项-利息 <- 变量.应计利息
				BigDecimal yskxlxForJe = ArithmeticUtil.round(yjlxNow, NumberEnum.MONEY);
				accountAmountMap.put(BookConstant.INTAMT, yskxlxForJe);
				// 逾期款项-本金 <- 交易明细.交易本金
				accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY));
				// 实收金额 <- 交易明细.交易金额
				accountAmountMap.put(BookConstant.DEALAMT,
						ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY));

				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}

				/** modify by yanya on 20191230 增加本位币 处理 start**/
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
					//本币应收款项-利息	<-	本金金额 * 汇率
					BigDecimal 本币利息 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.INTAMT), 汇率), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, 本币利息);
					//本币逾期款项-本金	<-	逾期款项-本金 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币本金 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.COST), 汇率), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.COST + BookConstant.BCCYAMT, 本币本金);
					//本币实收金额	<-		实收金额 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币实收金额 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.DEALAMT), 汇率), NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.DEALAMT + BookConstant.BCCYAMT, 本币实收金额);
					//变量.本币轧差金额 = 本币实收金额 - 本币逾期款项-本金 - 本币应收款项-利息
					BigDecimal 本币轧差金额 = ArithmeticUtil.doSub(本币实收金额, 本币本金, 本币利息);
					//if  变量.本币轧差金额 >= 0
					if(本币轧差金额.compareTo(BigDecimal.ZERO) > 0) {
						//本币轧差金额(贷) <-	变量.本币轧差金额
						accountAmountBCCYAMTMap.put(BookConstant.NETAMT_C + BookConstant.BCCYAMT, ArithmeticUtil.round(本币轧差金额, NumberEnum.MONEY));
					}
					else {
						//本币轧差金额(借) <-	变量.本币轧差金额*-1
						accountAmountBCCYAMTMap.put(BookConstant.NETAMT_D + BookConstant.BCCYAMT,
								ArithmeticUtil.round(ArithmeticUtil.multiply(本币轧差金额, new BigDecimal(-1)), NumberEnum.MONEY));
					}

				}
				else {
					for (String key : accountAmountMap.keySet()) {
						accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT, accountAmountMap.get(key));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);
				/** modify by yanya on 20191230 增加本位币 处理 end**/

				// 更新全局变量
				// 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 金额元素.应收款项-利息
				otherInterestBalanc = ArithmeticUtil.doSub(otherInterestBalanc, yskxlxForJe);
				// 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
				otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);

				// 基础科目性质
				Map<String, Map<String, Object>> accountNatrueMap = new HashMap<String, Map<String, Object>>();
				Map<String, Object> accountNatrueBranchMap = new HashMap<String, Object>();
				accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
				accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_本金.getValue(), accountNatrueBranchMap);
				accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
				ad.setAccountNatrueMap(accountNatrueMap);

			}
			// 交易方向_红利再投 add by yanya on 20191230
			else if (DictCodeList.交易方向_红利再投.getValue().equals(dealNow.getPs())) {

				//ad.setTableId(DictCodeListTableId.会计场景代码_资金收款_存放同业收息.getValue());// ad.会计场景代码=存款类资产收息;
				ad.setTableId(DictCodeListTableId.会计场景代码_存放同业_红利再投.getValue());// ad.会计场景代码=存放同业_红利再投;

				// 定义:变量.其他应收款-利息科目余额 = 变量.其他应收款 - 利息科目余额MAP.get(交易明细.分支序号)
				BigDecimal otherInterestBalanc = otherInterestBalanceMap.get(dealNow.getBranch());

				// 变量.其他应收款-利息科目余额 == null
				if (otherInterestBalanc == null) {
					// if(变量.标的信息.分类1 = 【数据字典】.存款类资产类型.活期类资产)
					if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
						// 查询交易的到期日对应的应计利息
						SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
								DateUtil.addDay(dealNow.getMdate(), -1), dealNow.getBooksetDate(),
								DictCodeList.余额方向_借方.getValue(), DictCodeBaseSubjectNatrue.应计利息.getValue(),
								null, null);

						// 非空判断
						if (otherSubjectBalance != null) {
							// 变量.应收利息 = 上述共通返回的科目余额
							otherInterestBalanc = otherSubjectBalance.getBalance();
							// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.应收利息)
							otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
						}

					} else {
						// 查询交易的到期日对应的应计利息
						SubjectBalance otherSubjectBalance = this.getSubjectBalance(booksetIdNow, bokDetailId,
								dealNow.getHappenDate(), dealNow.getBooksetDate(), DictCodeList.余额方向_借方.getValue(),
								DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), dealNow.getBranch(), taskPri);

						// 非空判断
						if (otherSubjectBalance != null) {
							// 变量.其他应收款-利息科目余额 = 上述共通返回的科目余额
							otherInterestBalanc = otherSubjectBalance.getBalance();
							// 变量.其他应收款-利息科目余额MAP.put(交易明细.分支序号，变量.其他应收款-利息科目余额)
							otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
						}
					}

				}

				// 调用金额共通方法设置有效小数位
				otherInterestBalanc = ArithmeticUtil.round(otherInterestBalanc, NumberEnum.MONEY);

				//实收成本(COST) <- 交易明细.交易份额
				//accountAmountMap.put(BookConstant.COST, ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.MONEY));
				//转投金额 <-		交易明细.交易份额
				//实收成本(COST) <- 交易明细.交易份额
				accountAmountMap.put(BookConstant.TRADE_QTY, ArithmeticUtil.round(dealNow.getShareAmt(), NumberEnum.MONEY));

				// 定义：金额元素.应收利息
				BigDecimal yslxForJe = BigDecimal.ZERO;

				// if（交易明细.是否结清 = "是"）
				if (DictCodeList.是否_否.getValue().equals(dealNow.getIsClean())) {
					// 金额元素.应收利息 <- 交易明细.交易金额
					//yslxForJe = ArithmeticUtil.round(dealNow.getTradeAmt(), NumberEnum.MONEY);
					// 金额元素.应收利息 <- 交易明细.交易本金 modify by yanya on 20200216
					yslxForJe = ArithmeticUtil.round(dealNow.getPrinAmt(), NumberEnum.MONEY);
					accountAmountMap.put(BookConstant.INTAMT, yslxForJe);
					// 利息差额 <- 0
					accountAmountMap.put(BookConstant.INCOMEAMT, BigDecimal.ZERO);

				} else {
					// 金额元素：应收利息 <- 变量.其他应收款-利息科目余额
					yslxForJe = otherInterestBalanc == null ? BigDecimal.ZERO : otherInterestBalanc;
					accountAmountMap.put(BookConstant.INTAMT, yslxForJe);
					// 交易明细.交易金额 - 金额元素.应收利息 > 0
					//if 交易明细.交易金额 - 金额元素.应收利息  > 0
					//if	交易明细.交易本金 - 金额元素.应收利息
					//BigDecimal subVal = ArithmeticUtil.doSub(dealNow.getTradeAmt(), yslxForJe);
					BigDecimal subVal = ArithmeticUtil.doSub(dealNow.getPrinAmt(), yslxForJe);
					if (subVal.compareTo(BigDecimal.ZERO) > 0) {
						// 利息差额_贷 <- 交易明细.交易本金 - 金额元素.应收利息
						accountAmountMap.put(BookConstant.INCOMEAMT_C, ArithmeticUtil.round(subVal,
								NumberEnum.MONEY.getCode(), null));
					} else {
						// 利息差额_借 <- （交易明细.交易本金 - 金额元素.应收利息）*-1
						accountAmountMap.put(BookConstant.INCOMEAMT_D,
								ArithmeticUtil.round(ArithmeticUtil.multiply(new BigDecimal("-1"), subVal), NumberEnum.MONEY));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账金额map的
				for (String key : accountAmountMap.keySet()) {
					accountCurrencyMap.put(key, dealNow.getCcy());
				}
				// 初始化定义 - 存放该笔本位币记账的相关金额要素 map定义
				Map<String, BigDecimal> accountAmountBCCYAMTMap = new HashMap<>();
				BigDecimal 汇率 = BigDecimal.ONE;
				if(!dealNow.getCcy().equals(standardCcyNow)) {
					//调用计算类	方法共通【汇率查询】
					//基础币种 = 交易明细.币种 and 报价币种 = 交易类账务处理结果.本位币 and 日期 = 交易类账务处理结果.发生日
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 start**/
					//汇率 = calculationCommonService.queryExchangeRateForTrade(dealNow.getCcy(), standardCcyNow, happenDateNow);
					List<CurrencyMarketDto> currencyMarketList = finProductCommonService.getCurrencyMarket(bookDateNow, booksetIdNow,
							standardCcyNow, dealNow.getCcy());
					if(!StringUtil.isEmptyList(currencyMarketList) && currencyMarketList.get(0) != null){
						汇率 = currencyMarketList.get(0).getClosePrice();
					}
					/** modify by yanya on 20200108 汇率查询方法变更调用标的类共通汇率查询方法 end**/
					//本币实收金额/本币实收成本（COST）<- 实收金额/本币实收成本（COST） * 汇率	用舍位处理后的结果计算并保留金额小数位
					//本币实收金额/本币实收成本（COST）<- 转投金额（TRADE_QTY） * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币实收金额 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.TRADE_QTY), 汇率),
							NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.TRADE_QTY + BookConstant.BCCYAMT, 本币实收金额);
					//本币应收利息 <- 应收利息 * 汇率	用舍位处理后的结果计算并保留金额小数位
					BigDecimal 本币应收利息 = ArithmeticUtil.round(calculationCommonService.doMultiply(accountAmountMap.get(BookConstant.INTAMT), 汇率),
							NumberEnum.MONEY);
					accountAmountBCCYAMTMap.put(BookConstant.INTAMT + BookConstant.BCCYAMT, 本币应收利息);
					//if 交易明细.利息总额 - 变量.冲减应收利息	>0
					BigDecimal subVal = ArithmeticUtil.doSub(本币实收金额, 本币应收利息);
					if (subVal.compareTo(BigDecimal.ZERO) > 0) {
						//本币利息差额_贷	 <-	本币实收金额/本币实收成本(COST)- 本币应收利息	保留金额小数位
						accountAmountBCCYAMTMap.put(BookConstant.INCOMEAMT_C + BookConstant.BCCYAMT, ArithmeticUtil.round(subVal,
								NumberEnum.MONEY.getCode(), null));
					}
					//else
					else {
						//本币利息差额_借	<-	（本币实收金额/本币实收成本（COST）- 本币应收利息) * -1	保留金额小数位
						accountAmountBCCYAMTMap.put(BookConstant.INCOMEAMT_D + BookConstant.BCCYAMT,
								ArithmeticUtil.round(ArithmeticUtil.multiply(new BigDecimal("-1"), subVal), NumberEnum.MONEY));
					}

				}
				else {
					//本币实收金额/本币实收成本（COST）<- 实收金额/实收成本（COST）
					//本币应收利息<-		应收利息
					//本币利息差额_贷/借 <-	利息差额_贷/借
					for (String key : accountAmountMap.keySet()) {
						accountAmountBCCYAMTMap.put(key + BookConstant.BCCYAMT, accountAmountMap.get(key));
					}
				}
				// 记账金额元素币种信息塞值--key是随记账本位币金额map的
				for (String key : accountAmountBCCYAMTMap.keySet()) {
					accountCurrencyMap.put(key, standardCcyNow);
				}
				/** 本位币 币种**/
				accountCurrencyMap.put(BookConstant.B_CCY, standardCcyNow);
				//记账金额元素 putALl 随记账本位币金额map
				accountAmountMap.putAll(accountAmountBCCYAMTMap);

				// 更新全局变量
				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
					// 变量.其他应收款-利息科目余额 = 变量.应计利息科目余额 - 金额元素.应收利息
					//otherInterestBalanc = ArithmeticUtil.doSub(interestBalance, yslxForJe);
					//变量.应收利息科目余额	=变量.应收利息科目余额 - 变量.冲减应收利息
					interestBalance = ArithmeticUtil.doSub(interestBalance, yslxForJe);
				} else {
					// 变量.其他应收款-利息科目余额 = 变量.其他应收款-利息科目余额 - 金额元素.应收利息
					otherInterestBalanc = ArithmeticUtil.doSub(otherInterestBalanc, yslxForJe);
					// 将变更后的余额更新到变量.其他应收款-利息科目余额MAP中
					otherInterestBalanceMap.put(dealNow.getBranch(), otherInterestBalanc);
				}

				// 基础科目性质
				Map<String, Map<String, Object>> accountNatrueMap = new HashMap<String, Map<String, Object>>();
				Map<String, Object> accountNatrueBranchMap = new HashMap<String, Object>();
				accountNatrueBranchMap.put(BookConstant.BRANCH, dealNow.getBranch());
				if (DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(targetFinProduct.getType1())) {
					accountNatrueMap.put(DictCodeBaseSubjectNatrue.应计利息.getValue(), accountNatrueBranchMap);
				} else {
					accountNatrueMap.put(DictCodeBaseSubjectNatrue.其他应收款_利息.getValue(), accountNatrueBranchMap);
				}
				ad.setAccountNatrueMap(accountNatrueMap);

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
	 * @param booksetIdNow
	 *            账套代码
	 * @param bokDetailId
	 *            账务明细代码
	 * @param happenDateNow
	 *            发生日
	 * @param bookDateNow
	 *            入账日
	 * @param expBalanceDirect
	 *            期待余额方向
	 * @param baseSubjectNature
	 *            基础科目性质
	 * @param branch
	 *            分支号
	 * @param taskPri
	 *            任务优先级
	 * @return SubjectBalance 科目余额
	 * @see
	 * @since 1.0
	 */
	private SubjectBalance getSubjectBalance(String booksetIdNow, String bokDetailId, Date happenDateNow,
			Date bookDateNow, String expBalanceDirect, String baseSubjectNature, Long branch, Long taskPri) {
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
		List<SubjectBalance> subjectBalanceList = accountingCommonService.getSubjectBalanceExpectDirection(searchDto,
				expBalanceDirect);

		// 获取科目余额
		if (subjectBalanceList != null && subjectBalanceList.size() > 0) {
			balance = subjectBalanceList.get(0);
		}

		return balance;
	}

	/**
	 * 调用共通方法：[标的类共通方法-查询交易信息]，获取标的交易信息
	 * 
	 * @param finprodId
	 *            金融产品代码【必输项】
	 * @param finprodType
	 *            金融产品类型【必输项】
	 * @param branch
	 *            分支序号
	 * @return ValFinProductDeal 标的查询
	 * @see
	 * @since 1.0
	 */
//	private TradeInfoDto getTargetInfo(String finprodId, String finprodType, Long branch) {
//		// 定义返回值 - 标的交易信息
//		TradeInfoDto tradeInfoDtoForReturn = null;
//
//		// 调用共通方法：[标的类共通方法 - 交易信息查询]，获取标的交易信息
//		List<TradeInfoDto> tradeInfoDtoList = finProductCommonService.getTradeInfoDtoList(finprodId, finprodType,
//				branch);
//
//		// 当查询结果不为空
//		if (tradeInfoDtoList != null && tradeInfoDtoList.size() > 0) {
//			// 默认取第一条标的信息
//			tradeInfoDtoForReturn = tradeInfoDtoList.get(0);
//		}
//
//		return tradeInfoDtoForReturn;
//	}

	/**
	 * 获取标的基本信息
	 * 
	 * @param bokDetailId
	 *            账务明细代码
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
		Map<TargetSearchDto, Object> targetMap = finProductCommonService.getTargetInfo(bokDetailId,
				targetSearchDtoList);
		TargetFinProduct targetFinProduct = (TargetFinProduct) targetMap.get(targetSearchDto);

		// 确保不为null
		if (targetFinProduct == null) {
			targetFinProduct = new TargetFinProduct();
		}

		return targetFinProduct;
	}

}
