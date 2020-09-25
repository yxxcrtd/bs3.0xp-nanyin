/*
 * 文件名：RealTimeValCommonUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.InterestBasisDaysParameter;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade.MockTradeInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 组合实时估值共通util
 * 
 * @author etliu
 * @version 1.0 2020年4月17日
 * @see RealTimeValCommonUtil
 * @since 1.0
 */
public class RealTimeValCommonUtil {
	/**
	 * 投资目的
	 */
	public static final String 投资目的 ="999999";
	/**
	 * 投管资产类别：00现金
	 */
	public static final String 投管资产类别_现金 ="00";
	/**
	 * 资产负债标识 01：资产
	 */
	public static final String 资产负债标识_资产 = "01";
	/**
	 * 资产负债标识 02：负债
	 */
	public static final String 资产负债标识_负债 = "02";
	/**
	 * 现金流类型 01：本金
	 */
	public static final String 现金流类型_本金 = "01";
	/**
	 * 现金流类型 01：利息
	 */
	public static final String 现金流类型_利息 = "02";
	/**
	 * 债券t+1成本处理方式 01：交割日处理
	 */
	public static final String 债券T_1成本处理方式_交割日处理 = "01";
	/**
	 * 债券t+1成本处理方式 02：交易日处理
	 */
	public static final String 债券T_1成本处理方式_交易日处理 = "02";
	/**
	 * 估值类型01：资产估值
	 */
	public static final String 估值类型_资产估值 = "01";
	/**
	 * 估值类型02：划款交易
	 */
	public static final String 估值类型_划款交易 = "02";
	/**
	 * 估值类型03：产品存续期交易
	 */
	public static final String 估值类型_产品存续期交易 = "03";
	/**
	 * 估值类型03：费用交易
	 */
	public static final String 估值类型_费用交易 = "04";

	/**
	 * 现金类型01：再投
	 */
	public static final String 现金类型_再投 = "01";
	/**
	 * 现金类型02：融资
	 */
	public static final String 现金类型_融资 = "02";
	/**
	 * 现金类型03：保证金
	 */
	public static final String 现金类型_保证金 = "03";

	/**
	 * 资产类型 MOCK：模拟资产
	 */
	public static final String 资产类型_模拟资产 = "MOCK";

	/**
	 * 资产类型 00：现金
	 */
	public static final String 资产类型_现金 = "00";

	/**
	 * 成本处理方式 01：交割日处理 
	 */
	public static final String 成本处理方式_交割日处理 = "01";
	
	/**
	 * 成本处理方式 02：交易日处理 
	 */
	public static final String 成本处理方式_交易日处理 = "02";
	
	/**
	 * 费用类型00：综合费率 
	 */
	public static final String 费用类型_综合费率 = "00";

	// 定义 过程中小数位数 = 6
	// 定义 高精度小数位数 = 14
	// 定义 金额小数位数 = 2
	// 定义 汇率小数位数 = 6
	// 定义 单位净值小数位数 = 6
	// 定义 万份收益小数位数 = 6
	public static final int PROCESS_SCALE = 6;
	public static final int HIGT_SCALE = 14;
	public static final int AMOUNT_SCALE = 2;
	public static final int RATE_SCALE = 6;
	public static final int UNITCPRICE_SCALE = 6;
	public static final int YIELD_SCALE = 6;

    private static Logger logger = LoggerFactory.getLogger(RealTimeValCommonUtil.class);

	/**
	 * 资产负债判断
	 * 
	 * @param finProductType 金融产品分类表Entity
	 * @return 资产负债标识 01：资产，02：负债
	 */
	public static String judge资产负债标识(FinProductType finProductType) {
		if (finProductType == null) {
			return null;
		}

		String 资产负债标识 = null;

		String[] type8List1 = { DictCodeList.平安资产类型_债券.getValue(), DictCodeList.平安资产类型_基金.getValue(),
				DictCodeList.平安资产类型_计划类资产_利率型.getValue(), DictCodeList.平安资产类型_计划类资产_净值型.getValue(),
				DictCodeList.平安资产类型_存款.getValue(), DictCodeList.平安资产类型_项目类资产.getValue(),
				DictCodeList.平安资产类型_计划类资产_货币型.getValue(), DictCodeListExt2.平安资产类型_外汇即期.getValue(),
				DictCodeListExt2.平安资产类型_外汇远期.getValue(), DictCodeListExt2.平安资产类型_外汇掉期.getValue(),
				DictCodeListExt2.平安资产类型_利率互换.getValue(), DictCodeListExt3.平安资产类型_期权.getValue(),
				DictCodeListExt2.平安资产类型_股票.getValue() };
		String[] type8List2 = { DictCodeList.平安资产类型_质押式回购.getValue(), DictCodeListExt2.平安资产类型_债券借贷.getValue(),
				DictCodeList.平安资产类型_拆借.getValue(), DictCodeList.平安资产类型_买断式回购.getValue() };

		if (Arrays.asList(type8List1).contains(finProductType.getType8())) {
			资产负债标识 = 资产负债标识_资产;
		} else if (Arrays.asList(type8List2).contains(finProductType.getType8())) {
			String[] type7List1 = { DictCodeList.回购拆借方向_拆出.getValue(), DictCodeList.回购拆借方向_逆回购.getValue(),
					DictCodeList.回购拆借方向_融出.getValue() };
			String[] type7List2 = { DictCodeList.回购拆借方向_拆入.getValue(), DictCodeList.回购拆借方向_正回购.getValue(),
					DictCodeList.回购拆借方向_融入.getValue() };
			if (Arrays.asList(type7List1).contains(finProductType.getType7())) {
				资产负债标识 = 资产负债标识_资产;
			} else if (Arrays.asList(type7List2).contains(finProductType.getType7())) {
				资产负债标识 = 资产负债标识_负债;
			}
		}

		return 资产负债标识;
	}

	/**
	 * 存续期交易生成 <功能详细描述>
	 * 
	 * @param 现金流计划列表
	 * @param 币种
	 * @param 数量
	 * @param 利息总额
	 * @param 投资目的
	 * @param 全量现金流计划列表
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static List<MockTrdProductDealDto> generate存续期交易(List<MockFinCashPlanDto> 现金流计划列表, String 币种, BigDecimal 数量,
			BigDecimal 利息总额, String 投资目的, List<MockFinCashPlanDto> 全量现金流计划列表) {
		List<MockTrdProductDealDto> result = new ArrayList<MockTrdProductDealDto>();
		// 遍历
		现金流计划列表.forEach(entity -> {
			FinCashPlan finCashPlan = entity.getFinCashPlan();
			String cashType = "";
			if (StringUtil.equals(DictCodeList.现金流类型_中间收息.getValue(), finCashPlan.getCashType())
					|| StringUtil.equals(DictCodeList.现金流类型_到期收息.getValue(), finCashPlan.getCashType())) {
				cashType = 现金流类型_利息;
			} else if (StringUtil.equals(DictCodeList.现金流类型_中间还本.getValue(), finCashPlan.getCashType())
					|| StringUtil.equals(DictCodeList.现金流类型_到期还本.getValue(), finCashPlan.getCashType())) {
				cashType = 现金流类型_本金;
			}
			MockTrdProductDealDto mockTrdProductDealDto = new MockTrdProductDealDto();
			mockTrdProductDealDto.setTradeId(IDGenerator.generate(32));// 交易编号
			mockTrdProductDealDto.setFinprodId(finCashPlan.getFinprodId()); // 金融产品代码
			mockTrdProductDealDto.setFinprodType2(finCashPlan.getFinprodType2());// 金融产品类型（投管分类）
			mockTrdProductDealDto.setBranch(Long.parseLong(DateUtil.format(finCashPlan.getMdate()) + cashType));
			mockTrdProductDealDto.setTradeDate(finCashPlan.getMdate());// 交易日
			mockTrdProductDealDto.setVdate(finCashPlan.getVdate());// 起息日
			mockTrdProductDealDto.setMdate(finCashPlan.getMdate());// 到期日
			mockTrdProductDealDto.setSettleDate(finCashPlan.getMdate()); // 交割日
			mockTrdProductDealDto.setCcy(币种); // 币种
			mockTrdProductDealDto.setUnitCprice(
					ArithmeticUtil.divide(finCashPlan.getPrinAmt(), finCashPlan.getCashBaseamt(), HIGT_SCALE)); // 单位净价
			if (StringUtil.equals(现金流类型_本金, cashType)) {
				mockTrdProductDealDto
						.setShareAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(数量, finCashPlan.getPrinAmt()),
								finCashPlan.getCashBaseamt(), AMOUNT_SCALE));// 交易份额
				mockTrdProductDealDto.setPrinAmt(mockTrdProductDealDto.getShareAmt());// 交易本金
			}
			if (StringUtil.equals(现金流类型_利息, cashType)) {
				mockTrdProductDealDto.setIntAmt(利息总额);// 利息总额
			}
			mockTrdProductDealDto.setFpriceAmt(
					ArithmeticUtil.doAdd(mockTrdProductDealDto.getPrinAmt(), mockTrdProductDealDto.getIntAmt())); // 全价总额
			mockTrdProductDealDto.setTradeAmt(BigDecimal.ZERO);// 交易金额
			mockTrdProductDealDto.setPs(StringUtil.equals(现金流类型_利息, cashType) ? DictCodeList.交易方向_收息.getValue()
					: DictCodeList.交易方向_还本.getValue()); // 交易方向
			mockTrdProductDealDto.setInvAim(投资目的);// 投资目的
			mockTrdProductDealDto.setIsClean(DictCodeList.是否_是.getValue()); // 是否结清
			result.add(mockTrdProductDealDto);

			// 中间还本 还要生成一笔利息的
			if (StringUtil.equals(DictCodeList.现金流类型_中间还本.getValue(), finCashPlan.getCashType())) {
				MockTrdProductDealDto mockTrdProductDealDto2 = new MockTrdProductDealDto();
				BeanUtil.copyPropertiesIgnoreNull(mockTrdProductDealDto2, mockTrdProductDealDto);
				mockTrdProductDealDto2.setShareAmt(null);
				mockTrdProductDealDto2.setPrinAmt(null);
				mockTrdProductDealDto2
						.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(利息总额, finCashPlan.getPrinAmt()),
								finCashPlan.getCashBaseamt(), AMOUNT_SCALE));// 利息总额
				mockTrdProductDealDto2.setPs(DictCodeList.交易方向_收息.getValue()); // 交易方向
				result.add(mockTrdProductDealDto2);
			}

			// 现金流计划扩展.是否已支付 = 否 生成到账交易
			if (StringUtil.equals(entity.getPaidOrNot(), DictCodeList.是否_否.getValue())) {
				MockTrdProductDealDto mockTrdProductDealDto3 = new MockTrdProductDealDto();
				BeanUtil.copyPropertiesIgnoreNull(mockTrdProductDealDto3, mockTrdProductDealDto);
				mockTrdProductDealDto3.setTradeAmt(mockTrdProductDealDto3.getFpriceAmt());// 交易金额
				// 利息 且变量.现金流计划扩展.是否支付利息 = 否时
				Date 计划支付日 = null;
				if ((StringUtil.equals(DictCodeList.现金流类型_中间还本.getValue(), finCashPlan.getCashType())
						|| StringUtil.equals(现金流类型_利息, cashType))
						&& StringUtil.equals(DictCodeList.是否_否.getValue(), finCashPlan.getIsPayInt())) {
					// 遍历 入参.全量现金流计划列表 现金流类型为中间收息或到期收息，且调整后的计算结束日大于入参.日期且整后的计算结束日日期最小的记录的调整后的计划支付日
					Date 计算结束日 = null;
					for (MockFinCashPlanDto 全量现金流计划 : 全量现金流计划列表) {
						if (全量现金流计划.getFinCashPlan() != null
								&& (StringUtil.equals(DictCodeList.现金流类型_中间收息.getValue(), 全量现金流计划.getFinCashPlan().getCashType())
										|| StringUtil.equals(DictCodeList.现金流类型_到期收息.getValue(),
												全量现金流计划.getFinCashPlan().getCashType()))
								&& DateUtil.compareDate(finCashPlan.getMdate(),全量现金流计划.getFinCashPlan().getVdate()) > 0
								&& DateUtil.compareDate(全量现金流计划.getFinCashPlan().getMdate(),finCashPlan.getMdate()) > 0) {
							if (计算结束日 == null) {
								计算结束日 = 全量现金流计划.getFinCashPlan().getMdate();
								计划支付日 = 全量现金流计划.getFinCashPlan().getPayDate();
							} else if (DateUtil.compareDate(计算结束日, 全量现金流计划.getFinCashPlan().getMdate()) > 0) {
								计算结束日 = 全量现金流计划.getFinCashPlan().getMdate();
								计划支付日 = 全量现金流计划.getFinCashPlan().getPayDate();
							}
						}
					}
				}
				if (计划支付日 != null) {
					if (StringUtil.equals(现金流类型_利息, cashType))
						mockTrdProductDealDto3.setSettleDate(计划支付日);
				}
				result.add(mockTrdProductDealDto3);
				// 中间还本 还要生成一笔利息的
				if (StringUtil.equals(DictCodeList.现金流类型_中间还本.getValue(), finCashPlan.getCashType())) {
					MockTrdProductDealDto mockTrdProductDealDto4 = new MockTrdProductDealDto();
					BeanUtil.copyPropertiesIgnoreNull(mockTrdProductDealDto4, mockTrdProductDealDto);
					mockTrdProductDealDto4.setShareAmt(null);
					mockTrdProductDealDto4.setPrinAmt(null);
					mockTrdProductDealDto4
							.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(利息总额, finCashPlan.getPrinAmt()),
									finCashPlan.getCashBaseamt(), AMOUNT_SCALE));// 利息总额
					mockTrdProductDealDto4.setTradeAmt(mockTrdProductDealDto4.getFpriceAmt());// 交易金额
					mockTrdProductDealDto4.setPs(DictCodeList.交易方向_收息.getValue()); // 交易方向
					if (计划支付日 != null) {
						mockTrdProductDealDto4.setSettleDate(计划支付日);
					}
					result.add(mockTrdProductDealDto4);
				}
			}
		});

		return result;
	}

	/**
	 * 
	 * 交易类标的存续期交易生成 <功能详细描述>
	 * 
	 * @param 资产基本信息列表
	 * @param 组合资产估值
	 * @param 资产端交易列表
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static List<MockTrdProductDealDto> generate交易类标的存续期交易(MockAssetInfoDto 资产基本信息列表,
			MockPtlSecValuationDto 组合资产估值, List<MockTrdProductDealDto> 资产端交易列表) {
		List<MockTrdProductDealDto> 交易类标的存续期交易result = new ArrayList<MockTrdProductDealDto>();
		if (资产基本信息列表 == null || 资产基本信息列表.getFinTradeProduct() == null || 组合资产估值 == null) {
			return 交易类标的存续期交易result;
		}
		boolean 生成到期交易flag = true;
		for (MockTrdProductDealDto entity : 资产端交易列表) {
			if (StringUtil.equals(DictCodeList.交易方向_到期.getValue(), entity.getPs())
					|| StringUtil.equals(DictCodeList.交易方向_还本到账.getValue(), entity.getPs())) {
				生成到期交易flag = false;
				break;
			}
		}
		if (生成到期交易flag) {
			MockTrdProductDealDto 到期交易 = new MockTrdProductDealDto();
			FinTradeProduct finTradeProduct = 资产基本信息列表.getFinTradeProduct();
			到期交易.setTradeId(IDGenerator.generate(32));// 交易编号
			到期交易.setFinprodId(finTradeProduct.getFinprodId()); // 金融产品代码
			到期交易.setFinprodType2(finTradeProduct.getFinprodType2());// 金融产品类型（投管分类）
			到期交易.setBranch(0L);
			到期交易.setTradeDate(finTradeProduct.getMdate());// 交易日
			到期交易.setVdate(finTradeProduct.getVdate());// 起息日
			到期交易.setMdate(finTradeProduct.getMdate());// 到期日
			到期交易.setPayDate(finTradeProduct.getMpayDate());// 交付日
			到期交易.setSettleDate(finTradeProduct.getMdate()); // 交割日
			到期交易.setCcy(finTradeProduct.getCcy()); // 币种
			到期交易.setUnitCprice(BigDecimal.ZERO); // 单位净价
			到期交易.setShareAmt(组合资产估值.getShareAmt());// 交易份额
			到期交易.setPrinAmt(组合资产估值.getDscCostAmt());// 交易本金
			到期交易.setIntAmt(组合资产估值.getTdyIntincexp());// 利息总额
			到期交易.setFpriceAmt(ArithmeticUtil.doAdd(到期交易.getPrinAmt(), 到期交易.getIntAmt())); // 全价总额
			到期交易.setTradeAmt(到期交易.getFpriceAmt());// 交易金额
			到期交易.setPs(DictCodeList.交易方向_到期.getValue()); // 交易方向
			到期交易.setIsClean(DictCodeList.是否_是.getValue()); // 是否结清
			到期交易.setTradeMarket(finTradeProduct.getTradeMarket()); // 交易场所
			交易类标的存续期交易result.add(到期交易);
		}
		return 交易类标的存续期交易result;
	}

	/**
	 * 
	 * 行权交易生成 <功能详细描述>
	 * 
	 * @param 行权计划列表
	 * @param 币种
	 * @param 数量
	 * @param 利息总额
	 * @param 投资目的
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static List<MockTrdProductDealDto> generate行权交易(List<FinExercisePlan> 行权计划列表, String 币种, BigDecimal 数量,
			BigDecimal 利息总额, String 投资目的) {
		// TODO 行权交易生成
		List<MockTrdProductDealDto> 行权交易列表 = new ArrayList<MockTrdProductDealDto>();
		for(FinExercisePlan finExercisePlan:行权计划列表) {
			if(finExercisePlan==null) {
				continue;
			}
			MockTrdProductDealDto 行权交易 = new MockTrdProductDealDto();
			行权交易.setTradeId(IDGenerator.generate(32));// 交易编号
			行权交易.setFinprodId(finExercisePlan.getFinprodId()); // 金融产品代码
			行权交易.setFinprodType2(finExercisePlan.getFinprodType2());// 金融产品类型（投管分类）
			行权交易.setBranch(0L);
			行权交易.setTradeDate(finExercisePlan.getExerciseDate());// 交易日
			行权交易.setSettleDate(finExercisePlan.getActExerciseDate()); // 交割日
			行权交易.setCcy(币种); // 币种
			行权交易.setUnitCprice(finExercisePlan.getExercisePrice()==null?new BigDecimal(100):finExercisePlan.getExercisePrice()); // 单位净价
			行权交易.setShareAmt(数量==null?BigDecimal.ZERO:数量.setScale(AMOUNT_SCALE));// 交易份额
			行权交易.setPrinAmt(ArithmeticUtil.multiply(AMOUNT_SCALE,RoundingMode.HALF_UP,数量,行权交易.getUnitCprice()));// 交易本金
			行权交易.setIntAmt(利息总额==null?BigDecimal.ZERO:利息总额.setScale(AMOUNT_SCALE));// 利息总额
			行权交易.setTradeAmt(ArithmeticUtil.doAdd(行权交易.getPrinAmt(), 行权交易.getIntAmt()));// 交易金额
			行权交易.setPs(finExercisePlan.getExerciseType()); // 交易方向
			行权交易.setInvAim(投资目的);// 投资目的
			行权交易.setIsClean(DictCodeList.是否_是.getValue()); // 是否结清
			行权交易列表.add(行权交易);
		}

		return 行权交易列表;
	}

	/**
	 * 
	 * T+N交易处理默认方法 <功能详细描述>
	 * 
	 * @param 资产端t_N交易列表
	 * @param 基准日期
	 * @param 资产基本信息
	 * @param t_N单资产变化
	 * @param t_N现金变化
	 * @param 摊销重算map
	 * @see
	 * @since 1.0
	 */
	public static void 交易处理默认方法T_N(List<MockTrdProductDealDto> 资产端t_N交易列表, String 基准日期, MockAssetInfoDto 资产基本信息,
			Map<String, MockSingleAssetChanging> t_N单资产变化, Map<MockCashChangingKey, BigDecimal> t_N现金变化,
			Map<Date, Boolean> 摊销重算map) {
		资产端t_N交易列表.forEach(entity -> {
			// 交易方向 in（"收息到账","还本到账","到期","卖出","提前终止","收取","逾期返售"，"到期行权","提前行权","放弃行权"）
			List<String> 交易方向List = Arrays.asList(DictCodeList.交易方向_收息到账.getValue(), DictCodeList.交易方向_还本到账.getValue(),
					DictCodeList.交易方向_到期.getValue(), DictCodeList.交易方向_卖出.getValue(), DictCodeList.交易方向_提前终止.getValue(),
					DictCodeList.交易方向_收取.getValue(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue(),
					DictCodeListExt1.交易方向_到期行权.getValue(), DictCodeListExt1.交易方向_提前行权.getValue(),
					DictCodeListExt1.交易方向_放弃行权.getValue());
			if (交易方向List.contains(entity.getPs())) {
				MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
				资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt().negate());
				单资产变化赋值T_N(t_N单资产变化, 基准日期, 资产变动信息);
				现金变化赋值T_N(t_N现金变化, 基准日期, entity.getCashProcessedOrNot(),
						new MockCashChangingKey(entity.getSettleDate(), 现金类型_再投, entity.getCcy()),
						entity.getTradeAmt());
			} else {
				MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
				资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt());
				单资产变化赋值T_N(t_N单资产变化, 基准日期, 资产变动信息);
				现金变化赋值T_N(t_N现金变化, 基准日期, entity.getCashProcessedOrNot(),
						new MockCashChangingKey(entity.getSettleDate(), 现金类型_再投, entity.getCcy()),
						entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt().negate());
			}
		});
	}

	/**
	 * 
	 * T+N资产变化赋值 <功能详细描述>
	 * 
	 * @param t_N资产变化
	 * @param key
	 * @param date
	 * @param mockSingleAssetChanging
	 * @see
	 * @since 1.0
	 */
	public static void 资产变化赋值T_N(Map<String, Map<String, MockSingleAssetChanging>> t_N资产变化, String key, String date,
			MockSingleAssetChanging mockSingleAssetChanging) {
		if (t_N资产变化 == null || t_N资产变化.get(key) == null) {
			return;
		}
		MockSingleAssetChanging 资产变动信息 = t_N资产变化.get(key).get(date);
		if (资产变动信息 != null) {
			资产变动信息.setCost(ArithmeticUtil.doAdd(资产变动信息.getCost(), mockSingleAssetChanging.getCost()));// 成本
			资产变动信息.setAmount(ArithmeticUtil.doAdd(资产变动信息.getAmount(), mockSingleAssetChanging.getAmount()));// 数量
			资产变动信息.setAccrualCost(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualCost(), mockSingleAssetChanging.getAccrualCost()));// 计提成本
			资产变动信息.setAccrualAmount(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualAmount(), mockSingleAssetChanging.getAccrualAmount()));// 计提数量
			资产变动信息.setAccrualInterest(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualInterest(), mockSingleAssetChanging.getAccrualInterest()));// 计提利息
			资产变动信息.setDelayPayAmt(
					ArithmeticUtil.doAdd(资产变动信息.getDelayPayAmt(), mockSingleAssetChanging.getDelayPayAmt()));// 清算款
			资产变动信息.setInTdyIntincexp(
					ArithmeticUtil.doAdd(资产变动信息.getInTdyIntincexp(), mockSingleAssetChanging.getInTdyIntincexp()));// 收入端应计利息
			资产变动信息.setOutTdyIntincexp(
					ArithmeticUtil.doAdd(资产变动信息.getOutTdyIntincexp(), mockSingleAssetChanging.getOutTdyIntincexp()));// 支出端应计利息
		} else {
			Map<String, MockSingleAssetChanging> map = new HashMap<String, MockSingleAssetChanging>();
			map.put(date, mockSingleAssetChanging);
			t_N资产变化.put(key, map);
		}
	}

	/**
	 * 
	 * T+N单资产变化赋值 <功能详细描述>
	 * 
	 * @param t_N单资产变化
	 * @param date
	 * @param mockSingleAssetChanging
	 * @see
	 * @since 1.0
	 */
	public static void 单资产变化赋值T_N(Map<String, MockSingleAssetChanging> t_N单资产变化, String date,
			MockSingleAssetChanging mockSingleAssetChanging) {
		if (t_N单资产变化 == null) {
			return;
		}
		MockSingleAssetChanging 资产变动信息 = t_N单资产变化.get(date);
		if (资产变动信息 != null) {
			资产变动信息.setCost(ArithmeticUtil.doAdd(资产变动信息.getCost(), mockSingleAssetChanging.getCost()));// 成本
			资产变动信息.setAmount(ArithmeticUtil.doAdd(资产变动信息.getAmount(), mockSingleAssetChanging.getAmount()));// 数量
			资产变动信息.setAccrualCost(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualCost(), mockSingleAssetChanging.getAccrualCost()));// 计提成本
			资产变动信息.setAccrualAmount(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualAmount(), mockSingleAssetChanging.getAccrualAmount()));// 计提数量
			资产变动信息.setAccrualInterest(
					ArithmeticUtil.doAdd(资产变动信息.getAccrualInterest(), mockSingleAssetChanging.getAccrualInterest()));// 计提利息
			资产变动信息.setDelayPayAmt(
					ArithmeticUtil.doAdd(资产变动信息.getDelayPayAmt(), mockSingleAssetChanging.getDelayPayAmt()));// 清算款
			资产变动信息.setInTdyIntincexp(
					ArithmeticUtil.doAdd(资产变动信息.getInTdyIntincexp(), mockSingleAssetChanging.getInTdyIntincexp()));// 收入端应计利息
			资产变动信息.setOutTdyIntincexp(
					ArithmeticUtil.doAdd(资产变动信息.getOutTdyIntincexp(), mockSingleAssetChanging.getOutTdyIntincexp()));// 支出端应计利息
		} else {
			t_N单资产变化.put(date, mockSingleAssetChanging);
		}
	}

	/**
	 * 
	 * T+N负债变化赋值 <功能详细描述>
	 * 
	 * @param t_N负债变化
	 * @param date
	 * @param liabilitiesChanging
	 * @see
	 * @since 1.0
	 */
	public static void 负债变化赋值T_N(Map<String, LiabilitiesChanging> t_N负债变化, String date,
			LiabilitiesChanging liabilitiesChanging) {
		if (t_N负债变化 == null) {
			return;
		}
		LiabilitiesChanging 负债变动信息 = t_N负债变化.get(date);
		if (负债变动信息 != null) {
			负债变动信息.setActualCapital(
					ArithmeticUtil.doAdd(负债变动信息.getActualCapital(), liabilitiesChanging.getActualCapital()));// 实收资本
			负债变动信息.setCustomProfit(
					ArithmeticUtil.doAdd(负债变动信息.getCustomProfit(), liabilitiesChanging.getCustomProfit()));// 客户收益
			负债变动信息.setCashFund(ArithmeticUtil.doAdd(负债变动信息.getCashFund(), liabilitiesChanging.getCashFund()));// 清算款
		}
	}

	/**
	 * 
	 * T+N现金变化赋值 <功能详细描述>
	 * 
	 * @param t_N现金变化
	 * @param 基准日期
	 * @param 是否已处理现金
	 * @param key
	 * @param value
	 * @see
	 * @since 1.0
	 */
	public static void 现金变化赋值T_N(Map<MockCashChangingKey, BigDecimal> t_N现金变化, String 基准日期, String 是否已处理现金,
			MockCashChangingKey key, BigDecimal value) {
		// 入参.基准日期 >= 入参.KEY.日期 and 是否已处理现金 = "是"
		if (key.getDate() == null || (DateUtil.compareDate(DateUtil.parse(基准日期), key.getDate()) >= 0
				&& StringUtil.equals(是否已处理现金, DictCodeList.是否_是.getValue()))) {
			return;
		}
		// 入参.value = 0 or 入参.value = null
		if (value == null || BigDecimal.ZERO.compareTo(value) == 0 || t_N现金变化 == null) {
			return;
		}
		BigDecimal 金额 = t_N现金变化.get(key);
		if (金额 != null) {
			金额 = ArithmeticUtil.doAdd(金额, value);
		} else {
			t_N现金变化.put(key, value);
		}
	}

	/**
	 * 
	 * T+N资产变化合并处理 <功能详细描述>
	 * 
	 * @param t_N资产变化
	 * @param t_N单资产变化
	 * @param 金融产品代码
	 * @param 投资目的
	 * @see
	 * @since 1.0
	 */
	public static void 资产变化合并处理T_N(Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> t_N资产变化,
			Map<String, MockSingleAssetChanging> t_N单资产变化, String 金融产品代码, String 投资目的) {
		if (t_N资产变化 != null) {
			t_N资产变化.put(new MockSingleAssetChangingKey(金融产品代码, 投资目的), t_N单资产变化);
		}
	}

	/**
	 * 
	 * T+N现金变化合并处理 <功能详细描述>
	 * 
	 * @param t_N现金变化_汇总目标
	 * @param t_N现金变化_元素
	 * @see
	 * @since 1.0
	 */
	public static void 现金变化合并处理T_N(Map<MockCashChangingKey, BigDecimal> t_N现金变化_汇总目标, Map<MockCashChangingKey, BigDecimal> t_N现金变化_元素) {
		// 遍历 t_N现金变化_元素
		Iterator<Entry<MockCashChangingKey, BigDecimal>> iterator = t_N现金变化_元素.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<MockCashChangingKey, BigDecimal> entry = iterator.next();
			MockCashChangingKey key = entry.getKey();
			BigDecimal 金额 = t_N现金变化_汇总目标.get(key);
			if (金额 != null) {
				金额 = ArithmeticUtil.doAdd(金额, entry.getValue());
			} else {
				t_N现金变化_汇总目标.put(key, entry.getValue());
			}
		}
	}

	/**
	 * 资产价值计算
	 * 
	 * @param mockPtlSecValuationDto 组合资产估值扩展
	 * @param ccy                    本币
	 * @param exchargeQuoteList      汇率行情列表
	 */
	public static void calc资产价值(MockPtlSecValuationDto mockPtlSecValuationDto, String ccy,
			List<MockMstExchargeQuoteDto> exchargeQuoteList) {
		// 没有返回值，直接修改参数mockPtlSecValuationDto的属性
		BigDecimal rate = BigDecimal.ONE;
		if (StringUtil.equals(mockPtlSecValuationDto.getCcy(), ccy)) {
			// TODO 变量.汇率 =
			// 调用汇率获取（基础币种：入参.本币,报价币种：入参.组合资产估值扩展.原币，汇率行情列表：入参.汇率行情列表）
			rate = 汇率获取(ccy,mockPtlSecValuationDto.getDelayPayAmtCcy(),exchargeQuoteList);
		}
		List<String> typeList1 = Arrays.asList(DictCodeList.平安资产类型_债券.getValue(), DictCodeList.平安资产类型_基金.getValue(),
				DictCodeList.平安资产类型_计划类资产_利率型.getValue(), DictCodeList.平安资产类型_计划类资产_净值型.getValue(),
				DictCodeList.平安资产类型_存款.getValue(), DictCodeList.平安资产类型_项目类资产.getValue(),
				DictCodeList.平安资产类型_计划类资产_货币型.getValue(), DictCodeList.平安资产类型_质押式回购.getValue(),
				DictCodeList.平安资产类型_拆借.getValue(), DictCodeList.平安资产类型_买断式回购.getValue(), 资产类型_模拟资产,
				DictCodeListExt2.平安资产类型_股票.getValue());

		List<String> typeList2 = Arrays.asList(DictCodeListExt2.平安资产类型_债券借贷.getValue(),
				DictCodeListExt3.平安资产类型_期权.getValue());

		List<String> typeList3 = Arrays.asList(DictCodeListExt2.平安资产类型_利率互换.getValue());

		List<String> typeList4 = Arrays.asList(DictCodeListExt2.平安资产类型_外汇即期.getValue(),
				DictCodeListExt2.平安资产类型_外汇远期.getValue(), DictCodeListExt2.平安资产类型_外汇掉期.getValue());

		List<String> typeList5 = Arrays.asList(资产类型_现金);

		if (typeList1.contains(mockPtlSecValuationDto.getTgAssetType())) {
			// 组合资产估值扩展.资产价值 = (组合资产估值扩展.摊销总成本+当日应计利息+公允价值变动+待清算资金)*变量.汇率
			mockPtlSecValuationDto
					.setAssetValue(ArithmeticUtil.multiply(ArithmeticUtil.doAdd(mockPtlSecValuationDto.getDscCostAmt(),
							mockPtlSecValuationDto.getTdyIntincexp(), mockPtlSecValuationDto.getTdyFloatIngpl(),
							mockPtlSecValuationDto.getDelayPayAmt()), rate));
		} else if (typeList2.contains(mockPtlSecValuationDto.getTgAssetType())) {
			// 组合资产估值扩展.资产价值 = (组合资产估值扩展.当日应计利息+公允价值变动+待清算资金)*变量.汇率
			mockPtlSecValuationDto
					.setAssetValue(ArithmeticUtil.multiply(
							ArithmeticUtil.doAdd(mockPtlSecValuationDto.getTdyIntincexp(),
									mockPtlSecValuationDto.getTdyFloatIngpl(), mockPtlSecValuationDto.getDelayPayAmt()),
							rate));
		} else if (typeList3.contains(mockPtlSecValuationDto.getTgAssetType())) {
			// 组合资产估值扩展.资产价值 = (组合资产估值扩展.收入端应计利息-支出端应计利息)*变量.汇率
			mockPtlSecValuationDto.setAssetValue(ArithmeticUtil.multiply(ArithmeticUtil.doSub(
					mockPtlSecValuationDto.getInTdyIntincexp(), mockPtlSecValuationDto.getOutTdyIntincexp()), rate));
		} else if (typeList4.contains(mockPtlSecValuationDto.getTgAssetType())) {
			if (StringUtil.equals(mockPtlSecValuationDto.getDelayPayAmtCcy(), ccy)) {
				// 调用汇率获取（基础币种：入参.本币,报价币种：入参.组合资产估值扩展.待清算资金币种，汇率行情列表：入参.汇率行情列表）
				rate = 汇率获取(ccy,mockPtlSecValuationDto.getDelayPayAmtCcy(),exchargeQuoteList);
			}
			// 组合资产估值扩展.资产价值 = 组合资产估值扩展.摊销总成本*变量.汇率+待清算资金*变量.待清算汇率
			mockPtlSecValuationDto.setAssetValue(ArithmeticUtil.multiply(ArithmeticUtil
					.doAdd(mockPtlSecValuationDto.getDscCostAmt(), mockPtlSecValuationDto.getDelayPayAmt()), rate));

		} else if (typeList5.contains(mockPtlSecValuationDto.getTgAssetType())) {
			// 组合资产估值扩展.资产价值 = (组合资产估值扩展.摊销总成本 + 当日应计利息）*变量.汇率
			mockPtlSecValuationDto.setAssetValue(ArithmeticUtil.multiply(ArithmeticUtil
					.doAdd(mockPtlSecValuationDto.getDscCostAmt(), mockPtlSecValuationDto.getTdyIntincexp()), rate));
		}
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param 基础币种
	 * @param 报价币种
	 * @param exchargeQuoteList
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static BigDecimal 汇率获取(String 基础币种, String 报价币种,
			List<MockMstExchargeQuoteDto> exchargeQuoteList) {
		BigDecimal result = BigDecimal.ONE;
		for(MockMstExchargeQuoteDto mockMstExchargeQuoteDto:exchargeQuoteList) {
			if(mockMstExchargeQuoteDto==null) {
				continue;
			}
			if(StringUtil.equals(mockMstExchargeQuoteDto.getBasisCyy(), 基础币种)
					&&StringUtil.equals(mockMstExchargeQuoteDto.getQuotCyy(), 报价币种)) {
				return ArithmeticUtil.multiply(RATE_SCALE, RoundingMode.HALF_UP, mockMstExchargeQuoteDto.getPrice(),
						mockMstExchargeQuoteDto.getQuotUnit()==null?BigDecimal.ONE:new BigDecimal(mockMstExchargeQuoteDto.getQuotUnit()));
			}else if(StringUtil.equals(mockMstExchargeQuoteDto.getBasisCyy(), 报价币种)
						&&StringUtil.equals(mockMstExchargeQuoteDto.getQuotCyy(), 基础币种)) {
					return ArithmeticUtil.divide(BigDecimal.ONE, ArithmeticUtil.multiply(mockMstExchargeQuoteDto.getPrice(),
							mockMstExchargeQuoteDto.getQuotUnit()==null?BigDecimal.ONE:new BigDecimal(mockMstExchargeQuoteDto.getQuotUnit()))
							,RATE_SCALE);
			}
		}
		return result;
	}

	/**
	 * 根据交易方向排序
	 *
	 * @param 交易列表
	 * @param 交易排序规则
	 * @param 业务类型
	 * @return
	 */
	public static MockTradeInfoDto sortTrdDealDtoByPs(List<MockTrdProductDealDto> 交易列表, List<BokTradeOrder> 交易排序规则, String 业务类型) {
		MockTradeInfoDto 交易信息 = new MockTradeInfoDto();
		交易信息.setAssetTradeList(交易列表);
		交易排序规则 = 交易排序规则.stream().filter(x ->
				StringUtil.equals(x.getBusiType(), 业务类型)).collect(Collectors.toList());
		if (交易排序规则 == null || 交易排序规则.isEmpty()) {
			return 交易信息;
		}
		// 交易顺序转Map
		Map<String, Long> orderMap = 交易排序规则.stream()
				.collect(JoyinCollectors.toMap(BokTradeOrder::getPs, BokTradeOrder::getPsOrder));
		// 按交易日排序
		Comparator<MockTrdProductDealDto> bySettleDate = Comparator
				.comparing(MockTrdProductDealDto::getSettleDate, Comparator.nullsFirst(Comparator.naturalOrder()));
		// 日志记录SET
		Set<String> notConfigedPs = new HashSet<>();
		// 按交易方向排序
		Comparator<MockTrdProductDealDto> byPs = Comparator.comparing(MockTrdProductDealDto::getPs, (s1, s2) -> {
			// 如果没有配置则放到最后处理
			if (!orderMap.containsKey(s1)) {
				notConfigedPs.add(s1);
				return 1;
			}
			if (!orderMap.containsKey(s2)) {
				notConfigedPs.add(s2);
				return -1;
			}
			return orderMap.get(s1).compareTo(orderMap.get(s2));
		});
		// 按是否结清排序
		Comparator<MockTrdProductDealDto> byIsClean = Comparator.comparing(MockTrdProductDealDto::getIsClean,
				Comparator.nullsFirst(Comparator.naturalOrder()));
		// 按交易金额排序
		Comparator<MockTrdProductDealDto> byTradeAmt = Comparator.comparing(MockTrdProductDealDto::getTradeAmt,
				Comparator.nullsFirst(Comparator.naturalOrder()));
		// 交易金额排序方式
		if (ValDictCodeList.排序类型_降序.getValue().equals(CacheUtil.getSysParam("TRADEAMT_SORT_TYPE"))) {
			byTradeAmt = byTradeAmt.reversed();
		}
		// 根据优先级依次排序  交易日>交易方向>是否结清>交易金额
		Collections.sort(交易信息.getAssetTradeList(),
				bySettleDate.thenComparing(byPs.thenComparing(byIsClean.thenComparing(byTradeAmt))));
		logger.warn("存在未配置顺序的交易方向:BUSI_TYPE[" + 交易信息.getBusiType() + "]PS" + notConfigedPs);

		return 交易信息;
	}

	/**
	 * 计算计息基础天数
	 *
	 * @param input InterestBasisDaysParameter
	 * @return
	 * @see
	 * @since 1.0
	 */
	public static int calcInterestBasisDays(InterestBasisDaysParameter input) {
		assert (StringUtil.isNotEmpty(input.getBasis()));
		if (StringUtil.equals(input.getBasis(), ValDictCodeList.计息基础_债券类资产_A_A.getValue())) {
			assert (input.getPayDateBegin() != null && input.getVdate() != null
					&& input.getMdate() != null && StringUtil.isNotEmpty(input.getCalMethod()));
			if (StringUtil.equals(input.getCalMethod(), DictCodeList.推算方式_首次支付日.getValue())) {
				assert (input.getFirstPayDate() != null);
			}
			if (StringUtil.equals(input.getCalMethod(), DictCodeList.推算方式_起息日向后.getValue())) {
				return IntStream.iterate(0, i -> i + 1).filter(i -> {
					if (input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getVdate(), i, 0, 0)) >= 0
							&& input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getVdate(), i + 1, 0, 0)) < 0) {
						return true;
					}
					return false;
				}).map(i -> {
					return (int) DateUtil.getBetweenDayNumber(
							DateUtil.addDate(input.getVdate(), i, 0, 0),
							DateUtil.addDate(input.getVdate(), i + 1, 0, 0));
				}).findFirst().orElse(365);
			} else if (StringUtil.equals(input.getCalMethod(), DictCodeList.推算方式_到期日向前.getValue())) {
				return IntStream.iterate(0, i -> i + 1).filter(i -> {
					if (input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getMdate(), -i - 1, 0, 0)) >= 0
							&& input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getMdate(), -i, 0, 0)) < 0) {
						return true;
					}
					return false;
				}).map(i -> {
					return (int) DateUtil.getBetweenDayNumber(
							DateUtil.addDate(input.getMdate(), -i - 1, 0, 0),
							DateUtil.addDate(input.getMdate(), -i, 0, 0));
				}).findFirst().orElse(365);
			} else if (StringUtil.equals(input.getCalMethod(), DictCodeList.推算方式_首次支付日.getValue())) {
				return IntStream.iterate(0, i -> i + 1).filter(i -> {
					if (input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getFirstPayDate(), i, 0, 0)) >= 0
							&& input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getFirstPayDate(), i + 1, 0, 0)) < 0) {
						return true;
					}
					return false;
				}).map(i -> {
					return (int) DateUtil.getBetweenDayNumber(
							DateUtil.addDate(input.getFirstPayDate(), i, 0, 0),
							DateUtil.addDate(input.getFirstPayDate(), i + 1, 0, 0));
				}).findFirst().orElse(365);
			} else {
				return IntStream.iterate(0, i -> i + 1).filter(i -> {
					if (input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getFirstPayDate(), i, 0, 0)) >= 0
							&& input.getPayDateBegin().compareTo(
							DateUtil.addDate(input.getFirstPayDate(), i + 1, 0, 0)) < 0) {
						return true;
					}
					return false;
				}).map(i -> {
					return (int) DateUtil.getBetweenDayNumber(
							DateUtil.addDate(input.getFirstPayDate(), i, 0, 0),
							DateUtil.addDate(input.getFirstPayDate(), i + 1, 0, 0));
				}).findFirst().orElse(365);
			}
		} else if (StringUtil.equals(input.getBasis(), ValDictCodeList.计息基础_债券类资产_A_365.getValue())) {
			return 365;
		} else if (StringUtil.equals(input.getBasis(), ValDictCodeList.计息基础_债券类资产_A_360.getValue())
				|| StringUtil.equals(input.getBasis(),
				ValDictCodeList.计息基础_债券类资产_30_360.getValue())
				|| StringUtil.equals(input.getBasis(), ValDictCodeList.计息基础_债券类资产_30U_360.getValue())
		) {
			return 360;
		} else {
			return 365;
		}
	}
    
}
