/*
 * 文件名：ProfitDistributionBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalanceSearchDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountDriver;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.dto.ProdChannelFeeCalBookReaderDto;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BookAccountCommonService;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 利润分配批处理 - Service
 * 
 * @author chengzhong
 * @version 1.0 2018年6月5日
 * @see ProdChannelFeeCalBookProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProdChannelFeeCalBookProcessorService {

	/**
	 * 批处理账务相关公用方法
	 */
	@Autowired
	private AccountingCommonService accountCommonService;

	@Autowired
	private BookAccountCommonService bookAccountCommonService;

	/**
	 * 记账引擎
	 */
	@Autowired
	private BookAccountGenLogic bookLogic;

	@Autowired
	private DBUtil dBUtil;

	/**
	 * process 数据处理
	 * 
	 * @return BookGenerateResult 入账结果
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	public BookGenerateResult execute(Map<String, Object> params) throws Exception {
		// 获取reader返回值
		ProdChannelFeeCalBookReaderDto acctEntity = (ProdChannelFeeCalBookReaderDto) params
				.get(GlobalConstant.READER_RESULT);

		// 若reader传递的参数为空，不进行后续处理
		if (acctEntity == null) {
			return null;
		}

		BookGenerateResult returnResult = new BookGenerateResult();

		// 账务明细若为空赋初始值
		if (returnResult.getValBokBookdetailBatch() == null) {
			returnResult.setValBokBookdetailBatch(new ArrayList<ValBokBookdetailBatch>());
		}
		returnResult.setProdId(acctEntity.getBooksetId());
		// 1.数据准备
		// 变量.实收资本余额
		BigDecimal varSszb = BigDecimal.ZERO;
		// 1.1 通道产品的实收资本余额查询
		SubjectBalanceSearchDto searchDto = new SubjectBalanceSearchDto();

		// 发生日 = 利润分配账务处理结果.发生日
		searchDto.setHappenDate(acctEntity.getHappenDate());
		// 入账日 = 利润分配账务处理结果.入账日
		searchDto.setBookDate(acctEntity.getBookDate());
		searchDto.setBaseSubjectNatureList(
				new ArrayList<String>(Arrays.asList(DictCodeBaseSubjectNatrue.实收资本.getValue())));
		if (acctEntity.getBooksetIdSonList() != null && acctEntity.getBooksetIdSonList().size() > 0) {
			for (String booksetIdSon : acctEntity.getBooksetIdSonList()) {
				if (StringUtils.isNotEmpty(booksetIdSon)) {
					// 账套代码 = 利润分配账务处理结果.账套代码
					searchDto.setBooksetId(booksetIdSon);
					List<SubjectBalance> listSubjectBalance = accountCommonService
							.getSubjectBalanceExpectDirection(searchDto, DictCodeList.余额方向_贷方.getValue());
					if (listSubjectBalance != null && listSubjectBalance.size() > 0) {
						if(listSubjectBalance.get(0).getBalance().compareTo(BigDecimal.ZERO)<0){
							//返回值.科目余额 <0
							varSszb = ArithmeticUtil.doAdd(varSszb, listSubjectBalance.get(0).getBalance());
						}
					}
				}
			}
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
		// 记账场景要素map塞值
		// 账套代码 -> 交易类账务处理结果.账套代码
		accountSceneMap.put(BookConstant.BOOKSET_ID, acctEntity.getBooksetId());
		// 发生日 -> 交易类账务处理结果.发生日
		accountSceneMap.put(BookConstant.HAPPEN_DATE, acctEntity.getHappenDate());
		// 入账日 -> 交易类账务处理结果.入账日
		accountSceneMap.put(BookConstant.BOOK_DATE, acctEntity.getBookDate());
		// 账套日期 -> 交易明细.账套代码
		accountSceneMap.put(BookConstant.BOOKSET_DATE, acctEntity.getBooksetDate());
		// 任务代码 -> 交易明细.任务代码
		accountSceneMap.put(BookConstant.TASK_ID, acctEntity.getTaskId());
		// 任务优先级 -> 交易明细.任务优先级
		accountSceneMap.put(BookConstant.TASK_PRI, acctEntity.getTaskPri());
		// 凭证类型 -> 【日终:01】
		accountSceneMap.put(BookConstant.BOOK_TYPE, DictCodeList.凭证类型_日终.getValue());
		// 本位币 -> 交易明细.本位币
		accountSceneMap.put(BookConstant.B_CCY, acctEntity.getStandardCcy());
		// 账务明细代码1->交易类账务处理结果.账务明细代码
		BokDetailId valBokDetailId = new BokDetailId();
		valBokDetailId.setBusiId(acctEntity.getBusiId());
		valBokDetailId.setBusiDetailType(acctEntity.getBusiDetailType());
		valBokDetailId.setBokDetailId(acctEntity.getBokDetailId());
		accountSceneMap.put(BookConstant.BOK_DETAIL_ID1, valBokDetailId);
		// 是否累计余额
		accountSceneMap.put(BookConstant.IS_ACC_BALANCE, DictCodeList.是否_是.getValue());
		// 交易编号 - > (计提类生成的账户明细，不需要塞交易编号)
		accountSceneMap.put(BookConstant.TRADE_ID, null);
		// 冲正标识 - （往账务明细（当前表））默认塞入正常
		accountSceneMap.put(BookConstant.OFFSET_FLAG, DictCodeList.冲正标识_正常交易.getValue());
		// 是否0入账
		accountSceneMap.put(BookConstant.IS_ZERO_ACCOUNT, null);

		accountSceneMap.put(BookConstant.BOOK_REMARK, "通道费计提");
		// 会计场景设置
		ad.setTableId(DictCodeListTableId.会计场景代码_通道费_计提.getValue());
		// 塞入记账场景树节点 = 会计场景
		ad.setTreeNode(acctEntity.getBookSetTreeNode());

		// 应收收入 <- 变量.应计利息
		accountAmountMap.put(BookConstant.ACCRUED_CHARDES, varSszb.negate());

		// 应收收入 <- 计提明细.交易币种
		accountCurrencyMap.put(BookConstant.ACCRUED_CHARDES, acctEntity.getStandardCcy());


		// map塞入场景要素管理中
		ad.setAccountSceneMap(accountSceneMap);// 记账场景要素
		ad.setAccountAmountMap(accountAmountMap);// 记账金额要素
		ad.setAccountCurrencyMap(accountCurrencyMap);// 记账金额币种要素

		// 获取待处理任务信息
		SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo) params.get("BATCHINFO");
		// 记账凭证生成
		BookGenerateResult resultNowNow = bookLogic.genAccount(ad, batchExecuteInfo);
		// 因为当前属于同一个维度，故将生成的账务名中的账务明细（当前表）数据，累加到返回数据中
		returnResult.getValBokBookdetailBatch().addAll(resultNowNow.getValBokBookdetailBatch());

		return returnResult;
	}

	/**
	 * 应付利润 <功能详细描述>
	 * 
	 * @param acctEntity
	 * @param subjectBalance
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ValBokBookdetailBatch getYflrBookdetail(ProfitDistributionReaderDto acctEntity,
			SubjectBalance subjectBalance, List<BokSubject> listBokSubject, SubjectBalance subjectBalanceEx,
			ValBokBookdetailBatch frfpYflrBookdetail2) {

		ValBokBookdetailBatch detail = new ValBokBookdetailBatch();
		getBookdetail(acctEntity, subjectBalance, detail);
		// 基础科目性质
		detail.setBaseSubjectNature(DictCodeBaseSubjectNatrue.应付利润.getValue());
		// 账务明细代码
		BokDetailId bokDetailId = new BokDetailId();
		bokDetailId.setBusiId(acctEntity.getFinprodId());
		bokDetailId.setBusiDetailType(acctEntity.getFinprodType());
		detail.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
		// 业务明细代码
		detail.setBusiId(acctEntity.getFinprodId());
		// 分支序号，无分支序号的存0
		detail.setBranch(BookConstant.DEFALUT_BRANCH);
		// 科目号
		if (listBokSubject != null && listBokSubject.size() > 0) {
			for (BokSubject sSubject : listBokSubject) {
				if (DictCodeBaseSubjectNatrue.应付利润.getValue().equals(sSubject.getBaseSubjectNature())) {
					detail.setSubjectNo(sSubject.getSubjectNo());
					break;
				}
			}
		}
		// 获取科目余额方向
		String balFlag = accountCommonService.getBalFlag(detail.getSubjectNo(), detail.getBooksetId());
		// 四级科目号
		detail.setDetailSubjectNo(null);
		// 借贷方向 为1结果的余额方向相反
		if (DictCodeList.余额方向_贷方.getValue().equals(frfpYflrBookdetail2.getCdFlag())) {
			detail.setCdFlag(DictCodeList.余额方向_借方.getValue());
		} else {
			detail.setCdFlag(DictCodeList.余额方向_贷方.getValue());
		}
		// 原币发生额
		detail.setoHappenAmt(frfpYflrBookdetail2.getoHappenAmt());
		// 凭证编号
		detail.setBookId(bookAccountCommonService.genBookId(detail));
		// 计算余额
		calBalance(balFlag, subjectBalanceEx, detail, false);
		return detail;
	}

	/**
	 * 利润分配_应付利润1 <功能详细描述>
	 * 
	 * @param acctEntity
	 * @param subjectBalance
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ValBokBookdetailBatch getLrfpYflrBookdetail1(ProfitDistributionReaderDto acctEntity,
			SubjectBalance subjectBalance, List<BokSubject> listBokSubject, SubjectBalance subjectBalanceEx) {

		ValBokBookdetailBatch detail = new ValBokBookdetailBatch();
		getBookdetail(acctEntity, subjectBalance, detail);
		// 基础科目性质
		detail.setBaseSubjectNature(DictCodeBaseSubjectNatrue.利润分配_应付利润.getValue());
		// 账务明细代码
		BokDetailId bokDetailId = new BokDetailId();
		bokDetailId.setBusiId(acctEntity.getFinprodId());
		bokDetailId.setBusiDetailType(acctEntity.getFinprodType());
		detail.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
		// 业务明细代码
		detail.setBusiId(acctEntity.getFinprodId());
		// 分支序号，无分支序号的存0
		detail.setBranch(BookConstant.DEFALUT_BRANCH);
		// 科目号
		if (listBokSubject != null && listBokSubject.size() > 0) {
			for (BokSubject sSubject : listBokSubject) {
				if (DictCodeBaseSubjectNatrue.利润分配_应付利润.getValue().equals(sSubject.getBaseSubjectNature())) {
					detail.setSubjectNo(sSubject.getSubjectNo());
					break;
				}
			}
		}
		// 获取科目余额方向
		String balFlag = accountCommonService.getBalFlag(detail.getSubjectNo(), detail.getBooksetId());
		// 四级科目号
		detail.setDetailSubjectNo(null);
		// 借贷方向 为1结果的余额方向相反
		if (DictCodeList.余额方向_贷方.getValue().equals(subjectBalance.getBalFlagHappenDate())) {
			detail.setCdFlag(DictCodeList.余额方向_贷方.getValue());
		} else {
			detail.setCdFlag(DictCodeList.余额方向_借方.getValue());
		}
		// 凭证编号
		detail.setBookId(bookAccountCommonService.genBookId(detail));
		// 计算余额
		calBalance(balFlag, subjectBalanceEx, detail, true);
		return detail;
	}

	/**
	 * 利润分配_应付利润1 <功能详细描述>
	 * 
	 * @param acctEntity
	 * @param subjectBalance
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ValBokBookdetailBatch getLrfpYflrBookdetail2(ProfitDistributionReaderDto acctEntity,
			SubjectBalance subjectBalance, List<BokSubject> listBokSubject, SubjectBalance subjectBalanceEx) {

		ValBokBookdetailBatch detail = new ValBokBookdetailBatch();
		getBookdetail(acctEntity, subjectBalance, detail);
		// 基础科目性质
		detail.setBaseSubjectNature(DictCodeBaseSubjectNatrue.利润分配_应付利润.getValue());
		// 账务明细代码
		BokDetailId bokDetailId = new BokDetailId();
		bokDetailId.setBusiId(acctEntity.getFinprodId());
		bokDetailId.setBusiDetailType(acctEntity.getFinprodType());
		detail.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));
		// 业务明细代码
		detail.setBusiId(acctEntity.getFinprodId());
		// 分支序号，无分支序号的存0
		detail.setBranch(BookConstant.DEFALUT_BRANCH);
		// 科目号
		if (listBokSubject != null && listBokSubject.size() > 0) {
			for (BokSubject sSubject : listBokSubject) {
				if (DictCodeBaseSubjectNatrue.利润分配_应付利润.getValue().equals(sSubject.getBaseSubjectNature())) {
					detail.setSubjectNo(sSubject.getSubjectNo());
					break;
				}
			}
		}
		// 获取科目余额方向
		String balFlag = accountCommonService.getBalFlag(detail.getSubjectNo(), detail.getBooksetId());
		// 四级科目号
		detail.setDetailSubjectNo(null);
		// 借贷方向 为1结果的余额方向相反
		if (DictCodeList.余额方向_贷方.getValue().equals(subjectBalanceEx.getBalFlagHappenDate())) {
			detail.setCdFlag(DictCodeList.余额方向_借方.getValue());
		} else {
			detail.setCdFlag(DictCodeList.余额方向_贷方.getValue());
		}
		// 凭证编号
		detail.setBookId(bookAccountCommonService.genBookId(detail));

		// 变量.发生额
		detail.setoHappenAmt(subjectBalanceEx.getBalance());

		// 计算余额
		calBalance(balFlag, subjectBalanceEx, detail, true);
		return detail;
	}

	/**
	 * 待分配利润 <功能详细描述>
	 * 
	 * @param acctEntity
	 * @param subjectBalance
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ValBokBookdetailBatch getDfplrBookdetail(ProfitDistributionReaderDto acctEntity,
			SubjectBalance subjectBalance) {

		ValBokBookdetailBatch detail = new ValBokBookdetailBatch();
		getBookdetail(acctEntity, subjectBalance, detail);
		// 基础科目性质
		detail.setBaseSubjectNature(subjectBalance.getBaseSubjectNature());
		// 账务明细代码
		BokDetailId bokDetailId = new BokDetailId();
		bokDetailId.setBusiId(acctEntity.getFinprodId());
		bokDetailId.setBusiDetailType(acctEntity.getFinprodType());
		detail.setBokDetailId(bookAccountCommonService.genMd5BokDetailId(bokDetailId));

		// 业务明细代码
		detail.setBusiId(acctEntity.getFinprodId());
		// 分支序号，无分支序号的存0
		detail.setBranch(BookConstant.DEFALUT_BRANCH);
		// 科目号
		detail.setSubjectNo(subjectBalance.getSubjectNo());
		// 获取科目余额方向
		String balFlag = accountCommonService.getBalFlag(detail.getSubjectNo(), detail.getBooksetId());
		// 四级科目号
		detail.setDetailSubjectNo(subjectBalance.getDetailSubjectNo());
		// 借贷方向 为1.1结果的余额方向相反
		if (DictCodeList.余额方向_贷方.getValue().equals(subjectBalance.getBalFlagHappenDate())) {
			detail.setCdFlag(DictCodeList.余额方向_借方.getValue());
		} else {
			detail.setCdFlag(DictCodeList.余额方向_贷方.getValue());
		}
		// 凭证编号
		detail.setBookId(bookAccountCommonService.genBookId(detail));
		// 账务明细代码
		detail.setBokDetailId(subjectBalance.getBokDetailId());
		// 业务明细代码
		detail.setBusiId(subjectBalance.getBusiId());
		// 计算余额
		calBalance(balFlag, subjectBalance, detail, false);
		return detail;
	}

	/**
	 * 获取账务明细 <功能详细描述>
	 * 
	 * @param acctEntity
	 * @param subjectBalance
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void getBookdetail(ProfitDistributionReaderDto acctEntity, SubjectBalance subjectBalance,
			ValBokBookdetailBatch detail) {
		// 流水
		detail.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
		// 账套代码
		detail.setBooksetId(acctEntity.getBooksetId());
		// 场景代码
		detail.setTableId(DictCodeListTableId.会计场景代码_产品_货币型_应付利润结转.getValue());
		// 交易编号
		detail.setTradeId(null);
		// 任务代码
		detail.setTaskId(acctEntity.getTaskId());
		// 任务优先级
		detail.setTaskPri(acctEntity.getTaskPri());
		// 发生日期
		detail.setHappenDate(acctEntity.getHappenDate());
		// 入账日期
		detail.setBookDate(acctEntity.getBookDate());
		// 账套日期
		detail.setBooksetDate(acctEntity.getBooksetDate());
		// 在途日期，对于T+1交割的债券交易，存交割日
		detail.setOnwayDate(null);
		// 原币
		detail.setoCcy(subjectBalance.getOCcy());
		// 原币发生额
		detail.setoHappenAmt(subjectBalance.getBalance());
		// 本位币
		detail.setbCcy(acctEntity.getStandardCcy());
		// 发生数量
		detail.setHappenNumber(null);
		// 结息日
		detail.setMdate(null);
		// 凭证编号
		detail.setBookId(null);
		// 凭证说明
		detail.setBookRemark(SysDataUtil.getNameFromDataDict(DictCode.会计场景代码, detail.getTableId()));
		// 冲正标识
		detail.setOffsetFlag(DictCodeList.冲正标识_正常交易.getValue());
		// 冲正日期
		detail.setOffsetDate(null);
		// 反向流水号，冲正存正常流水，正常存冲正流水
		// 凭证类型：实时、日终、手工
		detail.setBookType(DictCodeList.凭证类型_日终.getValue());
		// 处理序列
		detail.setBookOrder(new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param data
	 * @param detail
	 * @see
	 * @since 1.0
	 */
	private void calBalance(String balFlag, SubjectBalance data, ValBokBookdetailBatch detail, boolean isLeijiYue) {
		balFlag = balFlag != null ? balFlag : DictCodeList.余额方向_借贷双方.getValue();
		if (StringUtils.isEmpty(data.getBalFlagHappenDate())) {
			data.setBalFlagHappenDate(DictCodeList.余额方向_借方.getValue());
		}
		Map<String, Object> mapHappenDate = accountCommonService.balanceDirectDealAccrualAddBalance(
				StringUtils.isEmpty(balFlag) ? detail.getCdFlag() : balFlag,
				StringUtils.isEmpty(data.getBalFlagHappenDate()) ? detail.getCdFlag() : data.getBalFlagHappenDate(),
				data.getBalance() == null ? BigDecimal.ZERO : data.getBalance(),
				data.getNum() == null ? BigDecimal.ZERO : data.getNum(), detail.getCdFlag(), detail.getoHappenAmt(),
				detail.getHappenNumber(),
				/** MODIFY BY WUJIE ON 20190724 START
				null,null
				**/
				data.getbAmt(),detail.getbHappenAmt()
				/**MODIFY BY WUJIE ON 20190724 END**/
				);
		// 余额
		detail.setoAmtHappenDate(ArithmeticUtil.round((BigDecimal) mapHappenDate.get("balance"), NumberEnum.MONEY));
		// 数量
		detail.setNumAmtHappenDate(ArithmeticUtil.round((BigDecimal) mapHappenDate.get("num"), NumberEnum.MONEY));
		// 余额方向
		detail.setBalFlagHappenDate((String) mapHappenDate.get("balDirect"));

		// 本位币发生额
		detail.setbHappenAmt(detail.getoHappenAmt());
		// 本位币余额（发生日）
		detail.setbAmtHappenDate(detail.getoAmtHappenDate());

		if (isLeijiYue) {
			data.setBalFlagHappenDate(detail.getBalFlagHappenDate());
			data.setBalance(ArithmeticUtil.round((BigDecimal) mapHappenDate.get("balance"), NumberEnum.MONEY));
			data.setNum(ArithmeticUtil.round((BigDecimal) mapHappenDate.get("num"), NumberEnum.MONEY));
			/**ADD BY WUJIE ON 20190724 START**/
			data.setbAmt(ArithmeticUtil.round((BigDecimal) mapHappenDate.get("benbalance"), NumberEnum.MONEY));
			/**ADD BY WUJIE ON 20190724 END**/
		}
	}

	// /**
	// * 生成明细科目
	// *
	// * @param acctEntity
	// * @param subjectBalance
	// * @see
	// * @since 1.0
	// */
	// public BokSubjectDetail genDetailSubjectCode(ProfitDistributionReaderDto
	// acctEntity,
	// SubjectBalance subjectBalance) {
	// BokDetailId valBokDetailId = new BokDetailId();
	// // 账套代码
	// valBokDetailId.setBooksetId(acctEntity.getBooksetId());
	// valBokDetailId.setBokDetailId(subjectBalance.getBokDetailId());
	//
	// // 查找账务明细代码表
	// BokDetailId bokDetailId =
	// valBokDetailIdMapper.selectByPrimaryKey(valBokDetailId);
	// // 组装明细科目号
	// BokSubjectDetail valBokSubjectDetail = new BokSubjectDetail();
	// if (bokDetailId != null) {
	// // 账套代码
	// valBokSubjectDetail.setBooksetId(acctEntity.getBooksetId());
	// // 账务明细代码
	// valBokSubjectDetail.setBokDetailId(subjectBalance.getBokDetailId());
	// // 科目号
	// valBokSubjectDetail.setSubjectNo(subjectBalance.getSubjectNo());
	// // 基础科目性质
	// valBokSubjectDetail.setBaseSubjectNature(subjectBalance.getBaseSubjectNature());
	// // 四级科目号
	// valBokSubjectDetail.setDetailSubjectNo(
	// subjectBalance.getSubjectNo() + ValStringUtil.getNotNullStr(
	// bokDetailId.getDetailSubjectNoTail()));
	// // 四级科目号尾号
	// valBokSubjectDetail.setDetailSubjectNoTail(valBokDetailId.getDetailSubjectNoTail());
	// // 是否计息
	// valBokSubjectDetail.setAccIntFlag(null);
	// // 是否允许透支
	// valBokSubjectDetail.setOverdrawnFlag(null);
	// }
	// return valBokSubjectDetail;
	// }
}
