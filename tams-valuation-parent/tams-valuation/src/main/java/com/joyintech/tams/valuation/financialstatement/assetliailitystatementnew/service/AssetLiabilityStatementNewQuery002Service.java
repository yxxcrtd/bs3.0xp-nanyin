package com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.common.AssetLiabilityStatementNewEnum;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.common.AssetLiabilityStatementNewUtil;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001Dto;
import com.joyintech.tams.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001SearchDto;

/**
 * 资产负债表-账套明细列表
 * 
 * @author QuJianZhong
 * @version 1.0 2018年11月22日
 * @see AssetLiabilityStatementNewQuery002Service
 * @since 1.0
 */
@Service
public class AssetLiabilityStatementNewQuery002Service extends CommonExportService<AssetLiabilityStatementNewQuery001Dto> {

	@Autowired
	private AssetLiabilityStatementNewQuery001Service assetLiabilityStatementNewQuery001Service;

	/**
	 * 查询科目余额表
	 * 
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public List<AssetLiabilityStatementNewQuery001Dto> findBokBalance(AssetLiabilityStatementNewQuery001SearchDto dto)
			throws ParseException {
		List<AssetLiabilityStatementNewQuery001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
						+ "/AssetLiabilityStatementNewQuery001/selectBokBalance"
				, dto,
				AssetLiabilityStatementNewQuery001Dto.class);
		return list;
	}
	
	/**
	 * 获得 资产报表数据（账套明细列表查询）
	 * 
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public List<AssetLiabilityStatementNewQuery001Dto> findAssetsStatementAll(AssetLiabilityStatementNewQuery001SearchDto dto)
			throws ParseException {
		List<AssetLiabilityStatementNewQuery001Dto> retList = new ArrayList<AssetLiabilityStatementNewQuery001Dto>();
		// 全账套
		List<SuggestDto> accountList = dto.getAccountList();
		// 项目名
		String itemName = dto.getItemName();
		String itemCode = dto.getItemCode();
		AssetLiabilityStatementNewQuery001Dto tempDto = null;

		//查询参数.表格类型 = Arrays.asList(DictCodeList.科目类型_资产类.getValue())
		dto.setItemTypes(Arrays.asList(DictCodeList.科目类型_资产类.getValue()));
		dto.setBalanceDate(assetLiabilityStatementNewQuery001Service.analyseBalanceDate(dto, AssetLiabilityStatementNewUtil.ENDING_BALANCE));// 设置期末日期
		List<AssetLiabilityStatementNewQuery001Dto> endList = findBokBalance(dto); // 期末余额列表
		dto.setBalanceDate(assetLiabilityStatementNewQuery001Service.analyseBalanceDate(dto, AssetLiabilityStatementNewUtil.INITIAL_BALANCE));// 设置年初日期
		List<AssetLiabilityStatementNewQuery001Dto> initList = findBokBalance(dto); // 年初余额列表
		//Map<String,List<资产负债表>> 期末资产负债Map = 期末余额列表.stream().map(资产负债表::账套代码)
		//Map<String,List<资产负债表>> 期初资产负债Map = 期初余额列表.stream().map(资产负债表::账套代码)
		Map<String, List<AssetLiabilityStatementNewQuery001Dto>> endMap = endList.stream().collect(Collectors.groupingBy(AssetLiabilityStatementNewQuery001Dto::getBooksetId));
		Map<String, List<AssetLiabilityStatementNewQuery001Dto>> initMap = initList.stream().collect(Collectors.groupingBy(AssetLiabilityStatementNewQuery001Dto::getBooksetId));
		//List<资产负债表> = new List<资产负债表>
		List<AssetLiabilityStatementNewQuery001Dto> liabilityStatementNewQuery001DtoList = new ArrayList<>();
		if (!endMap.isEmpty()) {
			for (Map.Entry<String, List<AssetLiabilityStatementNewQuery001Dto>> entry : endMap.entrySet()) {
				String key = entry.getKey();
				//期末列表
				List<AssetLiabilityStatementNewQuery001Dto> list1 = endMap.get(key);
				//期初列表
				List<AssetLiabilityStatementNewQuery001Dto> list2 = initMap.get(key);
				//定义 资产列表<资产负债表> = 调用处理方法.合并期末余额与期初方法
				List<AssetLiabilityStatementNewQuery001Dto> list1AndList2 = assetLiabilityStatementNewQuery001Service.doMakeDtoDate(list1, list2);
				list1AndList2.addAll(AssetLiabilityStatementNewUtil.getAssetListSum(list1AndList2));
				liabilityStatementNewQuery001DtoList.addAll(list1AndList2);
			}
		}
		List<AssetLiabilityStatementNewQuery001Dto> returnList = new ArrayList<>();
		if (!StringUtil.isEmptyList(liabilityStatementNewQuery001DtoList)) {
			for (AssetLiabilityStatementNewQuery001Dto tmp : liabilityStatementNewQuery001DtoList
			) {
				if (StringUtil.equals(itemCode, tmp.getItemCode())) {
					returnList.add(tmp);
				}
			}
		}
		//return 排序处理 List<资产负债表>.sort(账套代码)
		List<AssetLiabilityStatementNewQuery001Dto> collect = returnList.stream().sorted(Comparator.comparing(AssetLiabilityStatementNewQuery001Dto::getBooksetId)).collect(Collectors.toList());

		return collect;
		//}
	}

	/**
	 * 获得 负债和所有者权益报表数据（账套明细列表查询）
	 * 
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public List<AssetLiabilityStatementNewQuery001Dto> findLiabilityInterestStatementAll(
			AssetLiabilityStatementNewQuery001SearchDto dto) throws ParseException {

		// 全账套
		List<SuggestDto> accountList = dto.getAccountList();
		// 项目名
		String itemName = dto.getItemName();
		String itemCode = dto.getItemCode();
		AssetLiabilityStatementNewQuery001Dto tempDto = null;

		//定义 列表<资产负债表> = new List<资产负债表>
		//
		dto.setItemTypes(Arrays.asList(DictCodeList.科目类型_负债类.getValue(), DictCodeList.科目类型_所有者权益类.getValue()));
		dto.setBalanceDate(assetLiabilityStatementNewQuery001Service.analyseBalanceDate(dto, AssetLiabilityStatementNewUtil.ENDING_BALANCE));// 设置期末日期
		List<AssetLiabilityStatementNewQuery001Dto> endList = assetLiabilityStatementNewQuery001Service.makeLiabilityLnterestList(dto,false); // 期末余额列表
		dto.setBalanceDate(assetLiabilityStatementNewQuery001Service.analyseBalanceDate(dto, AssetLiabilityStatementNewUtil.INITIAL_BALANCE));// 设置年初日期
		List<AssetLiabilityStatementNewQuery001Dto> initList =  assetLiabilityStatementNewQuery001Service.makeLiabilityLnterestList(dto,false); // 年初余额列表
		Map<String,List<AssetLiabilityStatementNewQuery001Dto>> endMap = endList.stream().collect(Collectors.groupingBy(AssetLiabilityStatementNewQuery001Dto::getBooksetId));
		Map<String,List<AssetLiabilityStatementNewQuery001Dto>> initMap = initList.stream().collect(Collectors.groupingBy(AssetLiabilityStatementNewQuery001Dto::getBooksetId));
		List<AssetLiabilityStatementNewQuery001Dto> retList = new ArrayList<AssetLiabilityStatementNewQuery001Dto>();
		if (!endMap.isEmpty()) {
			for (Map.Entry<String, List<AssetLiabilityStatementNewQuery001Dto>> entry : endMap.entrySet()) {
				String key = entry.getKey();
				//期末列表
				List<AssetLiabilityStatementNewQuery001Dto> list1 = endMap.get(key);
				//期初列表
				List<AssetLiabilityStatementNewQuery001Dto> list2 = initMap.get(key);
				List<AssetLiabilityStatementNewQuery001Dto> list1AndList2 = assetLiabilityStatementNewQuery001Service.doMakeDtoDate(list1, list2);
				list1AndList2.addAll(AssetLiabilityStatementNewUtil.getLiabilityInterestListSum(list1AndList2));
				retList.addAll(list1AndList2);
			}
		}


		List<AssetLiabilityStatementNewQuery001Dto> returnList = new ArrayList<>();
		if (!StringUtil.isEmptyList(retList)) {
			for (AssetLiabilityStatementNewQuery001Dto tmp : retList
			) {
				if (StringUtil.equals(itemCode, tmp.getItemCode())) {
					returnList.add(tmp);
				}
			}
		}
		List<AssetLiabilityStatementNewQuery001Dto> collect = returnList.stream().sorted(Comparator.comparing(AssetLiabilityStatementNewQuery001Dto::getBooksetId)).collect(Collectors.toList());
		return collect;
	}

	/**
	 * 分析余额日期
	 * 
	 * @param dto         查询条件
	 * @param balanceType 期末、年初
	 * @return
	 * @throws ParseException
	 */
	public Date analyseBalanceDate(AssetLiabilityStatementNewQuery001SearchDto dto, String balanceType)
			throws ParseException {
		int year = 0;
		int month = -1;
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
		calendar.set(Calendar.MINUTE, 0);// 分
		calendar.set(Calendar.SECOND, 0);// 秒
		calendar.set(Calendar.MILLISECOND, 0);// 毫秒

		String[] strArr = dto.getReportDate().split(",")[0].split("-");
		if (DictCodeListExt1.报表期限类型_年报.getValue().equals(dto.getReportTermType())) {// 年底
			year = Integer.valueOf(strArr[0]);
			month += 12;
			day = 31;
		}
		if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			if (DictCodeListExt1.半年_上半年.getValue().equals(dto.getReportTerm())) {
				month += 6;
				day = 30;
			} else {
				month += 12;
				day = 31;
			}
		}
		if (DictCodeListExt1.报表期限类型_季报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			if (DictCodeListExt1.季度_第一季度.getValue().equals(dto.getReportTerm())) {
				month += 3;
				day = 31;
			} else if (DictCodeListExt1.季度_第二季度.getValue().equals(dto.getReportTerm())) {
				month += 6;
				day = 30;
			} else if (DictCodeListExt1.季度_第三季度.getValue().equals(dto.getReportTerm())) {
				month += 9;
				day = 30;
			} else {
				month += 12;
				day = 31;
			}
		}
		if (DictCodeListExt1.报表期限类型_月报.getValue().equals(dto.getReportTermType())) {// 月底：当前月的下个月的第0天
			year = Integer.valueOf(strArr[0]);
			month += Integer.valueOf(strArr[1]) + 1;
			day = 0;
		}
		if (DictCodeListExt1.报表期限类型_日报.getValue().equals(dto.getReportTermType())) {
			year = Integer.valueOf(strArr[0]);
			month += Integer.valueOf(strArr[1]);
			day = Integer.valueOf(strArr[2]);
		}

		if (AssetLiabilityStatementNewUtil.ENDING_BALANCE.equals(balanceType)) {// 期末
			calendar.set(year, month, day);
			return calendar.getTime();
		} else {// 年初
			calendar.set(year, 0, 1);
			return calendar.getTime();
		}
	}

	/**
	 * 组装 DTOList
	 * 
	 * @param dtoList     表样
	 * @param list        结果集
	 * @param balanceType 操作余额类型
	 * @param finType 模板类型
	 * @return
	 */
	private void assembleDtoList(List<AssetLiabilityStatementNewQuery001Dto> dtoList,
			List<AssetLiabilityStatementNewQuery001Dto> list, String balanceType, boolean assetFlag) {
		for (AssetLiabilityStatementNewQuery001Dto dto : dtoList) {
			List<AssetLiabilityStatementNewEnum> subjectTypeList = dto.getSubjectTypeList();
			for (AssetLiabilityStatementNewQuery001Dto item : list) {
			    /**modify by yanya on 20190929 增加根据财务四表模板类型查询配置 start**/
				List<AssetLiabilityStatementNewEnum> enumList = AssetLiabilityStatementNewEnum
						.enumsfromValue(item.getSubjectNo());
				/**modify by yanya on 20190929 增加根据财务四表模板类型查询配置 end**/
				enumList.retainAll(subjectTypeList);
				if (enumList.size() > 0) {
					setInitOrEnd(dto, item, balanceType, assetFlag);
					dto.setSubjectNo(item.getSubjectNo());
				}
			}
		}
	}

	/**
	 * 资产总计和所有者权益总计
	 * 
	 * @return
	 */
	private void assembleLiaIntSum(List<AssetLiabilityStatementNewQuery001Dto> dtoList) {
		BigDecimal liabilityEndSum = BigDecimal.ZERO;
		BigDecimal liabilityInitSum = BigDecimal.ZERO;
		BigDecimal interestEndSum = BigDecimal.ZERO;
		BigDecimal interestInitSum = BigDecimal.ZERO;
		List<String> liabilityList = AssetLiabilityStatementNewEnum.valuesfromDictCode(DictCodeList.科目类型_负债类);
		List<String> interestList = AssetLiabilityStatementNewEnum.valuesfromDictCode(DictCodeList.科目类型_所有者权益类);
		for (AssetLiabilityStatementNewQuery001Dto dto : dtoList) {
			if (liabilityList.contains(dto.getSubjectNo())) {
				liabilityEndSum = liabilityEndSum.add(dto.getEndingBalance());
				liabilityInitSum = liabilityInitSum.add(dto.getInitialBalance());
			}
			if (AssetLiabilityStatementNewUtil.LIABILITY_SUM.equals(dto.getItemName())) {
				dto.setEndingBalance(liabilityEndSum);
				dto.setInitialBalance(liabilityInitSum);
			}
			if (interestList.contains(dto.getSubjectNo())) {
				interestEndSum = interestEndSum.add(dto.getEndingBalance());
				interestInitSum = interestInitSum.add(dto.getInitialBalance());
			}
			if (AssetLiabilityStatementNewUtil.INTEREST_SUM.equals(dto.getItemName())) {
				dto.setEndingBalance(interestEndSum);
				dto.setInitialBalance(interestInitSum);
			}
		}
	}

	/**
	 * 设置年初或期末余额
	 * 
	 * @param dto         容器
	 * @param item        结果项
	 * @param balanceType 操作余额类型
	 * @param             dto.balFlag 期待方向
	 * @param amtType     使用金额类型
	 */
	private void setInitOrEnd(AssetLiabilityStatementNewQuery001Dto dto, AssetLiabilityStatementNewQuery001Dto item,
			String balanceType, boolean assetFlag) {
		String amtType = dto.getUseAmtType();
		boolean specialFlag = false; // 特殊处理 表示
		if (AssetLiabilityStatementNewUtil.ENDING_BALANCE.equals(balanceType)) {

			if (AssetLiabilityStatementNewUtil.B_AMT.equals(amtType)) {
				// 应付账款: 设计变更，特殊处理。（单个项目下包含多子科目时，存在一个使用金额与其他不同）
				if (AssetLiabilityStatementNewEnum.资产类_其他资产_应付账款.getValue().equals(item.getSubjectNo())
						|| AssetLiabilityStatementNewEnum.负债类_其他负债_应付账款.getValue().equals(item.getSubjectNo())) {
					specialFlag = true;
					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
						item.setEndingBalance(item.getbDAmt());
					}
					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
						item.setEndingBalance(item.getbCAmt());
					}
				} else {
					item.setEndingBalance(item.getbAmt());
				}
			}

			// 资产、负债特殊项处理
			if (assetFlag) {
				if (inSpecialSubject(item.getSubjectNo())) {
					if (DictCodeList.余额方向_借方.getValue().equals(item.getBalFlag())) {
						item.setEndingBalance(item.getbAmt());
					} else {
						item.setEndingBalance(BigDecimal.ZERO);
					}
				}
			} else {
				if (inSpecialSubject(item.getSubjectNo())) {
					if (DictCodeList.余额方向_贷方.getValue().equals(item.getBalFlag())) {
						item.setEndingBalance(item.getbAmt());
					} else {
						item.setEndingBalance(BigDecimal.ZERO);
					}
				}
			}
			if (!StringUtils.equals(item.getBalFlag(), dto.getBalFlag()) &&
					AssetLiabilityStatementNewUtil.B_AMT.equals(amtType) &&
						!specialFlag && !inSpecialSubject(item.getSubjectNo())) {// 实际余额方向与期望不同时 *-1
																				// (仅当取值为原币余额时)
				dto.setEndingBalance(dto.getEndingBalance().add(item.getEndingBalance().negate()));
			} else {
				dto.setEndingBalance(dto.getEndingBalance().add(item.getEndingBalance()));
			}
		} else {
			if (AssetLiabilityStatementNewUtil.B_AMT.equals(amtType)) {
				// 应付账款: 设计变更，特殊处理。（单个项目下包含多子科目时，存在一个使用金额与其他不同）
				if (AssetLiabilityStatementNewEnum.资产类_其他资产_应付账款.getValue().equals(item.getSubjectNo())
						|| AssetLiabilityStatementNewEnum.负债类_其他负债_应付账款.getValue().equals(item.getSubjectNo())) {
					specialFlag = true;
					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
						item.setInitialBalance(item.getbDAmt());
					}
					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
						item.setInitialBalance(item.getbCAmt());
					}
				} else {
					item.setInitialBalance(item.getbAmt());
				}
			}
			
			// 资产、负债特殊项处理
			if (assetFlag) {
				if (inSpecialSubject(item.getSubjectNo())) {
					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
						item.setInitialBalance(item.getbAmt());
					} else {
						item.setInitialBalance(BigDecimal.ZERO);
					}
				}
			} else {
				if (inSpecialSubject(item.getSubjectNo())) {
					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
						item.setInitialBalance(item.getbAmt());
					} else {
						item.setInitialBalance(BigDecimal.ZERO);
					}
				}
			}

			if (!StringUtils.equals(dto.getBalFlag(), item.getBalFlag()) &&
					AssetLiabilityStatementNewUtil.B_AMT.equals(amtType) &&
						!specialFlag && !inSpecialSubject(item.getSubjectNo())) {// 实际余额方向与期望不同时 *-1
																				// (仅当取值为原币余额时)
				dto.setInitialBalance(dto.getInitialBalance().add(item.getInitialBalance().negate()));
			} else {
				dto.setInitialBalance(dto.getInitialBalance().add(item.getInitialBalance()));
			}
		}
	}
	
	private boolean inSpecialSubject(String subjectNo) {
		return AssetLiabilityStatementNewEnum.负债类_衍生金融负债_外汇买卖.getValue().equals(subjectNo) ||
				AssetLiabilityStatementNewEnum.负债类_衍生金融负债_外汇结售汇.getValue().equals(subjectNo) ||
				AssetLiabilityStatementNewEnum.负债类_衍生金融负债_金融掉期.getValue().equals(subjectNo) ||
					AssetLiabilityStatementNewEnum.负债类_衍生金融负债_远期投资.getValue().equals(subjectNo) ||
						AssetLiabilityStatementNewEnum.负债类_衍生金融负债_其他衍生工具.getValue().equals(subjectNo) || 
							AssetLiabilityStatementNewEnum.负债类_衍生金融负债_套期工具.getValue().equals(subjectNo) ||
								AssetLiabilityStatementNewEnum.负债类_衍生金融负债_被套期项目.getValue().equals(subjectNo) ||
									AssetLiabilityStatementNewEnum.负债类_应付证券清算款_证券清算款.getValue().equals(subjectNo) ||
										AssetLiabilityStatementNewEnum.负债类_其他负债_应付账款.getValue().equals(subjectNo);
	}
}
