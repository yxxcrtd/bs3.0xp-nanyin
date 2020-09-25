package com.joyintech.tams.disclosure.busicommon.financialstatements.assetliailitystatement.service;
//package com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.service;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.google.common.base.Function;
//import com.google.common.collect.Maps;
//import com.joyintech.framework.export.CommonExportService;
//import com.joyintech.framework.select.SuggestDto;
//import com.joyintech.tams.disclosure.interf.common.busicommon.common.constants.DictCodeList;
//import com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.common.AssetLiabilityStatementEnum;
//import com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.common.AssetLiabilityStatementUtil;
//import com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.dao.AssetLiabilityStatementQuery001Mapper;
//import com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.dto.AssetLiabilityStatementQuery001Dto;
//import com.joyintech.tams.disclosure.interf.common.busicommon.financialstatements.assetliailitystatement.dto.AssetLiabilityStatementQuery001SearchDto;
//
///**
// * 资产负债表-账套明细列表
// * 
// * @author QuJianZhong
// * @version 1.0 2018年11月22日
// * @see AssetLiabilityStatementQuery002Service
// * @since 1.0
// */
//@Service
//public class AssetLiabilityStatementQuery002Service extends CommonExportService<AssetLiabilityStatementQuery001Dto> {
//
//	@Autowired
//	private AssetLiabilityStatementQuery001Mapper mapper;
//	
//	/**
//	 * 查询科目余额表
//	 * 
//	 * @param dto
//	 * @return
//	 * @throws ParseException
//	 */
//	public List<AssetLiabilityStatementQuery001Dto> findBokBalance(AssetLiabilityStatementQuery001SearchDto dto)
//			throws ParseException {
//		List<AssetLiabilityStatementQuery001Dto> list = mapper.selectBokBalance(dto);
//		return list;
//	}
//	
//	/**
//	 * 获得 资产报表数据（账套明细列表查询）
//	 * 
//	 * @param dto
//	 * @return
//	 * @throws ParseException
//	 */
//	public List<AssetLiabilityStatementQuery001Dto> findAssetsStatementAll(AssetLiabilityStatementQuery001SearchDto dto)
//			throws ParseException {
//		List<AssetLiabilityStatementQuery001Dto> retList = new ArrayList<AssetLiabilityStatementQuery001Dto>();
//		// 全账套
//		List<SuggestDto> accountList = dto.getAccountList();
//		// 项目名
//		String itemName = dto.getItemName();
//		AssetLiabilityStatementQuery001Dto tempDto = null;
//		for (SuggestDto account : accountList) {
//			dto.setBooksetId(account.getCode());
//			dto.setSubjectNoList(AssetLiabilityStatementEnum.valuesfromDictCode(DictCodeList.科目类别_资产类));
//			dto.setBalanceDate(analyseBalanceDate(dto, AssetLiabilityStatementUtil.ENDING_BALANCE));// 设置期末日期
//			List<AssetLiabilityStatementQuery001Dto> endList = findBokBalance(dto); // 期末余额列表
//	
//			dto.setBalanceDate(analyseBalanceDate(dto, AssetLiabilityStatementUtil.INITIAL_BALANCE));// 设置年初日期
//			List<AssetLiabilityStatementQuery001Dto> initList = findBokBalance(dto); // 年初余额列表
//	
//			List<AssetLiabilityStatementQuery001Dto> dtoList = AssetLiabilityStatementUtil.getAssetList(); // 获得资产表样
//			assembleDtoList(dtoList, endList, AssetLiabilityStatementUtil.ENDING_BALANCE, true);
//			assembleDtoList(dtoList, initList, AssetLiabilityStatementUtil.INITIAL_BALANCE, true);
//			dtoList.addAll(AssetLiabilityStatementUtil.getAssetListSum(dtoList));
//			
//			// 找到项目对应的
//			Map<String, AssetLiabilityStatementQuery001Dto> map = Maps.uniqueIndex(dtoList, new Function<AssetLiabilityStatementQuery001Dto, String>(){
//
//				@Override
//				public String apply(AssetLiabilityStatementQuery001Dto t) {
//					return t.getItemName();
//				}
//	        	
//	        });
//	        tempDto = map.get(itemName);
//	        tempDto.setBooksetId(account.getCode());
//	        retList.add(tempDto);
//		}
//		return retList;
//	}
//	
//	/**
//	 * 获得 负债和所有者权益报表数据（账套明细列表查询）
//	 * 
//	 * @param dto
//	 * @return
//	 * @throws ParseException
//	 */
//	public List<AssetLiabilityStatementQuery001Dto> findLiabilityInterestStatementAll(
//			AssetLiabilityStatementQuery001SearchDto dto) throws ParseException {
//		List<AssetLiabilityStatementQuery001Dto> retList = new ArrayList<AssetLiabilityStatementQuery001Dto>();
//		// 全账套
//		List<SuggestDto> accountList = dto.getAccountList();
//		// 项目名
//		String itemName = dto.getItemName();
//		AssetLiabilityStatementQuery001Dto tempDto = null;
//		for (SuggestDto account : accountList) {
//			dto.setBooksetId(account.getCode());
//			dto.setSubjectNoList(
//					AssetLiabilityStatementEnum.valuesfromDictCode(DictCodeList.科目类别_负债类, DictCodeList.科目类别_所有者权益类));
//			dto.setBalanceDate(analyseBalanceDate(dto, AssetLiabilityStatementUtil.ENDING_BALANCE));// 设置期末日期
//			List<AssetLiabilityStatementQuery001Dto> endList = findBokBalance(dto); // 期末余额列表
//	
//			dto.setBalanceDate(analyseBalanceDate(dto, AssetLiabilityStatementUtil.INITIAL_BALANCE));// 设置年初日期
//			List<AssetLiabilityStatementQuery001Dto> initList = findBokBalance(dto); // 年初余额列表
//	
//			List<AssetLiabilityStatementQuery001Dto> dtoList = AssetLiabilityStatementUtil.getLiabilityInterestList(); // 获得负债和所有者权益报表表样
//			assembleDtoList(dtoList, endList, AssetLiabilityStatementUtil.ENDING_BALANCE, false);
//			assembleDtoList(dtoList, initList, AssetLiabilityStatementUtil.INITIAL_BALANCE, false);
//			assembleLiaIntSum(dtoList);
//			dtoList.addAll(AssetLiabilityStatementUtil.getLiabilityInterestListSum(dtoList));
//			
//			// 找到项目对应的
//	        Map<String, AssetLiabilityStatementQuery001Dto> map = Maps.uniqueIndex(dtoList, new Function<AssetLiabilityStatementQuery001Dto, String>(){
//
//				@Override
//				public String apply(AssetLiabilityStatementQuery001Dto t) {
//					return t.getItemName();
//				}
//	        	
//	        });
//	        tempDto = map.get(itemName);
//	        tempDto.setBooksetId(account.getCode());
//	        retList.add(tempDto);
//		}
//		return retList;
//	}
//
//	/**
//	 * 分析余额日期
//	 * 
//	 * @param dto         查询条件
//	 * @param balanceType 期末、年初
//	 * @return
//	 * @throws ParseException
//	 */
//	public Date analyseBalanceDate(AssetLiabilityStatementQuery001SearchDto dto, String balanceType)
//			throws ParseException {
//		int year = 0;
//		int month = -1;
//		int day = 0;
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
//		calendar.set(Calendar.MINUTE, 0);// 分
//		calendar.set(Calendar.SECOND, 0);// 秒
//		calendar.set(Calendar.MILLISECOND, 0);// 毫秒
//
//		String[] strArr = dto.getReportDate().split(",")[0].split("-");
//		if (DictCodeListExt1.报表期限类型_年报.getValue().equals(dto.getReportTermType())) {// 年底
//			year = Integer.valueOf(strArr[0]);
//			month += 12;
//			day = 31;
//		}
//		if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(dto.getReportTermType())) {
//			year = Integer.valueOf(strArr[0]);
//			if (DictCodeListExt1.半年_上半年.getValue().equals(dto.getReportTerm())) {
//				month += 6;
//				day = 30;
//			} else {
//				month += 12;
//				day = 31;
//			}
//		}
//		if (DictCodeListExt1.报表期限类型_季报.getValue().equals(dto.getReportTermType())) {
//			year = Integer.valueOf(strArr[0]);
//			if (DictCodeListExt1.季度_第一季度.getValue().equals(dto.getReportTerm())) {
//				month += 3;
//				day = 31;
//			} else if (DictCodeListExt1.季度_第二季度.getValue().equals(dto.getReportTerm())) {
//				month += 6;
//				day = 30;
//			} else if (DictCodeListExt1.季度_第三季度.getValue().equals(dto.getReportTerm())) {
//				month += 9;
//				day = 30;
//			} else {
//				month += 12;
//				day = 31;
//			}
//		}
//		if (DictCodeListExt1.报表期限类型_月报.getValue().equals(dto.getReportTermType())) {// 月底：当前月的下个月的第0天
//			year = Integer.valueOf(strArr[0]);
//			month += Integer.valueOf(strArr[1]) + 1;
//			day = 0;
//		}
//		if (DictCodeListExt1.报表期限类型_日报.getValue().equals(dto.getReportTermType())) {
//			year = Integer.valueOf(strArr[0]);
//			month += Integer.valueOf(strArr[1]);
//			day = Integer.valueOf(strArr[2]);
//		}
//
//		if (AssetLiabilityStatementUtil.ENDING_BALANCE.equals(balanceType)) {// 期末
//			calendar.set(year, month, day);
//			return calendar.getTime();
//		} else {// 年初
//			calendar.set(year, 0, 1);
//			return calendar.getTime();
//		}
//	}
//
//	/**
//	 * 组装 DTOList
//	 * 
//	 * @param dtoList     表样
//	 * @param list        结果集
//	 * @param balanceType 操作余额类型
//	 * @return
//	 */
//	private void assembleDtoList(List<AssetLiabilityStatementQuery001Dto> dtoList,
//			List<AssetLiabilityStatementQuery001Dto> list, String balanceType, boolean assetFlag) {
//		for (AssetLiabilityStatementQuery001Dto dto : dtoList) {
//			List<AssetLiabilityStatementEnum> subjectTypeList = dto.getSubjectTypeList();
//			for (AssetLiabilityStatementQuery001Dto item : list) {
//				List<AssetLiabilityStatementEnum> enumList = AssetLiabilityStatementEnum
//						.enumsfromValue(item.getSubjectNo());
//				enumList.retainAll(subjectTypeList);
//				if (enumList.size() > 0) {
//					setInitOrEnd(dto, item, balanceType, assetFlag);
//					dto.setSubjectNo(item.getSubjectNo());
//				}
//			}
//		}
//	}
//
//	/**
//	 * 资产总计和所有者权益总计
//	 * 
//	 * @return
//	 */
//	private void assembleLiaIntSum(List<AssetLiabilityStatementQuery001Dto> dtoList) {
//		BigDecimal liabilityEndSum = BigDecimal.ZERO;
//		BigDecimal liabilityInitSum = BigDecimal.ZERO;
//		BigDecimal interestEndSum = BigDecimal.ZERO;
//		BigDecimal interestInitSum = BigDecimal.ZERO;
//		List<String> liabilityList = AssetLiabilityStatementEnum.valuesfromDictCode(DictCodeList.科目类别_负债类);
//		List<String> interestList = AssetLiabilityStatementEnum.valuesfromDictCode(DictCodeList.科目类别_所有者权益类);
//		for (AssetLiabilityStatementQuery001Dto dto : dtoList) {
//			if (liabilityList.contains(dto.getSubjectNo())) {
//				liabilityEndSum = liabilityEndSum.add(dto.getEndingBalance());
//				liabilityInitSum = liabilityInitSum.add(dto.getInitialBalance());
//			}
//			if (AssetLiabilityStatementUtil.LIABILITY_SUM.equals(dto.getItemName())) {
//				dto.setEndingBalance(liabilityEndSum);
//				dto.setInitialBalance(liabilityInitSum);
//			}
//			if (interestList.contains(dto.getSubjectNo())) {
//				interestEndSum = interestEndSum.add(dto.getEndingBalance());
//				interestInitSum = interestInitSum.add(dto.getInitialBalance());
//			}
//			if (AssetLiabilityStatementUtil.INTEREST_SUM.equals(dto.getItemName())) {
//				dto.setEndingBalance(interestEndSum);
//				dto.setInitialBalance(interestInitSum);
//			}
//		}
//	}
//
//	/**
//	 * 设置年初或期末余额
//	 * 
//	 * @param dto         容器
//	 * @param item        结果项
//	 * @param balanceType 操作余额类型
//	 * @param             dto.balFlag 期待方向
//	 * @param amtType     使用金额类型
//	 */
//	private void setInitOrEnd(AssetLiabilityStatementQuery001Dto dto, AssetLiabilityStatementQuery001Dto item,
//			String balanceType, boolean assetFlag) {
//		String amtType = dto.getUseAmtType();
//		boolean specialFlag = false; // 特殊处理 表示
//		if (AssetLiabilityStatementUtil.ENDING_BALANCE.equals(balanceType)) {
//
//			if (AssetLiabilityStatementUtil.O_AMT.equals(amtType)) {
//				// 应付账款: 设计变更，特殊处理。（单个项目下包含多子科目时，存在一个使用金额与其他不同）
//				if (AssetLiabilityStatementEnum.资产类_其他资产_应付账款.getValue().equals(item.getSubjectNo())
//						|| AssetLiabilityStatementEnum.负债类_其他负债_应付账款.getValue().equals(item.getSubjectNo())) {
//					specialFlag = true;
//					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
//						item.setEndingBalance(item.getoDAmt());
//					}
//					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
//						item.setEndingBalance(item.getoCAmt());
//					}
//				} else {
//					item.setEndingBalance(item.getoAmt());
//				}
//			}
//
//			// 资产、负债特殊项处理
//			if (assetFlag) {
//				if (inSpecialSubject(item.getSubjectNo())) {
//					if (DictCodeList.余额方向_借方.getValue().equals(item.getBalFlag())) {
//						item.setEndingBalance(item.getoAmt());
//					} else {
//						item.setEndingBalance(BigDecimal.ZERO);
//					}
//				}
//			} else {
//				if (inSpecialSubject(item.getSubjectNo())) {
//					if (DictCodeList.余额方向_贷方.getValue().equals(item.getBalFlag())) {
//						item.setEndingBalance(item.getoAmt());
//					} else {
//						item.setEndingBalance(BigDecimal.ZERO);
//					}
//				}
//			}
//			if (!StringUtils.equals(item.getBalFlag(), dto.getBalFlag()) &&
//					AssetLiabilityStatementUtil.O_AMT.equals(amtType) && 
//						!specialFlag && !inSpecialSubject(item.getSubjectNo())) {// 实际余额方向与期望不同时 *-1
//																				// (仅当取值为原币余额时)
//				dto.setEndingBalance(dto.getEndingBalance().add(item.getEndingBalance().negate()));
//			} else {
//				dto.setEndingBalance(dto.getEndingBalance().add(item.getEndingBalance()));
//			}
//		} else {
//			if (AssetLiabilityStatementUtil.O_AMT.equals(amtType)) {
//				// 应付账款: 设计变更，特殊处理。（单个项目下包含多子科目时，存在一个使用金额与其他不同）
//				if (AssetLiabilityStatementEnum.资产类_其他资产_应付账款.getValue().equals(item.getSubjectNo())
//						|| AssetLiabilityStatementEnum.负债类_其他负债_应付账款.getValue().equals(item.getSubjectNo())) {
//					specialFlag = true;
//					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
//						item.setInitialBalance(item.getoDAmt());
//					}
//					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
//						item.setInitialBalance(item.getoCAmt());
//					}
//				} else {
//					item.setInitialBalance(item.getoAmt());
//				}
//			}
//			
//			// 资产、负债特殊项处理
//			if (assetFlag) {
//				if (inSpecialSubject(item.getSubjectNo())) {
//					if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
//						item.setInitialBalance(item.getoAmt());
//					} else {
//						item.setInitialBalance(BigDecimal.ZERO);
//					}
//				}
//			} else {
//				if (inSpecialSubject(item.getSubjectNo())) {
//					if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
//						item.setInitialBalance(item.getoAmt());
//					} else {
//						item.setInitialBalance(BigDecimal.ZERO);
//					}
//				}
//			}
//
//			if (!StringUtils.equals(dto.getBalFlag(), item.getBalFlag()) &&
//					AssetLiabilityStatementUtil.O_AMT.equals(amtType) && 
//						!specialFlag && !inSpecialSubject(item.getSubjectNo())) {// 实际余额方向与期望不同时 *-1
//																				// (仅当取值为原币余额时)
//				dto.setInitialBalance(dto.getInitialBalance().add(item.getInitialBalance().negate()));
//			} else {
//				dto.setInitialBalance(dto.getInitialBalance().add(item.getInitialBalance()));
//			}
//		}
//	}
//	
//	private boolean inSpecialSubject(String subjectNo) {
//		return AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖.getValue().equals(subjectNo) ||
//				AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇结售汇.getValue().equals(subjectNo) ||
//				AssetLiabilityStatementEnum.负债类_衍生金融负债_金融掉期.getValue().equals(subjectNo) ||
//					AssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资.getValue().equals(subjectNo) ||
//						AssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具.getValue().equals(subjectNo) || 
//							AssetLiabilityStatementEnum.负债类_衍生金融负债_套期工具.getValue().equals(subjectNo) ||
//								AssetLiabilityStatementEnum.负债类_衍生金融负债_被套期项目.getValue().equals(subjectNo) ||
//									AssetLiabilityStatementEnum.负债类_应付证券清算款.getValue().equals(subjectNo) ||
//										AssetLiabilityStatementEnum.负债类_其他负债_应付账款.getValue().equals(subjectNo);
//	}
//}
