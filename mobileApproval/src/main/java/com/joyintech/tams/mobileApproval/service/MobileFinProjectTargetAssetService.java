/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.tams.invest.asset.project.service.FinProjectTargetAssetService;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobileFinProjectTargetAssetService<br>
 * <b>描述：</b>项目类标的<br>
 * <b>作者：</b>YH<br>
 * <b>时间：</b>2020/5/15 14:21<br>
 */
@Service
public class MobileFinProjectTargetAssetService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;

    @Autowired
	private FinProjectTargetAssetService finProjectTargetAssetService;


    public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		FinProjectTargetAssetDto dto = JsonUtils.json2Object(businessData, FinProjectTargetAssetDto.class);

		//查询融资人
		Map<String,Object> param = new HashMap<>();
		param.put("IS_FINANCIER","Y");
		param.put("initValue",dto.getFinancier());
		Page<SelectOptionDto> result = finProjectTargetAssetService.getFinancier(param);
	 	dto.setFinancier(result.getRoot().get(0).getText());
	//	dto.setFinancier();


		//2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
//    	获取交易日历
		if (dto.getCalendarId() != null) {
			dto.setCalendarId(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
		}
//		境内外
		dto.setBlnArea(SysDataUtil.getNameFromDataDict(DictCode.境内外, dto.getBlnArea()));
//		设置投资顾问
		if(null!=dto.getInvestAdviser()){
			dto.setInvestAdviser(mobileApprovalMapper.getCustomerName(dto.getInvestAdviser()));
		}
//      是否非标
		dto.setIsNonStandard(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIsNonStandard()));

//		是否保本
		dto.setCapiIncomeFeature(SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, dto.getCapiIncomeFeature()));
//    	资产来源
		dto.setAssetSource(SysDataUtil.getNameFromDataDict(DictCode.资产来源, dto.getAssetSource()));
//    	项目投向行业
		dto.setProjInvestIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getProjInvestIndustry()));
//    	项目投向行业2
		dto.setProjInvestIndustry2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getProjInvestIndustry2()));
//    	获取担保人名称
		if (dto.getSponsor() != null) {
			dto.setSponsor(mobileApprovalMapper.getCustomerName(dto.getSponsor()));
		}
//    	获取融资人名称
		if (dto.getFinancier() != null) {
			dto.setFinancierName(mobileApprovalMapper.getCustomerName(dto.getFinancier()));
		}
//    	获取授信主体名称
		if (dto.getActualCreditProvid() != null) {
			dto.setActualCreditProvid(mobileApprovalMapper.getCustomerName(dto.getActualCreditProvid()));
		}
//    	资产投资性质
		dto.setInvestNature(SysDataUtil.getNameFromDataDict(DictCode.投资性质, dto.getInvestNature()));
//------------------------------------------------------- 	

//		计息要素    	
//    	息票品种
		dto.getFinInterestFactorDto().setCouponSpecies(SysDataUtil.getNameFromDataDict(DictCode.息票品种, dto.getFinInterestFactorDto().getCouponSpecies()));
//    	利率类型
		dto.getFinInterestFactorDto().setIntType(SysDataUtil.getNameFromDataDict(DictCode.利率类型, dto.getFinInterestFactorDto().getIntType()));
//    	利息分配方式
		dto.getFinInterestFactorDto().setBasisStyle(SysDataUtil.getNameFromDataDict(DictCode.利息分配方式, dto.getFinInterestFactorDto().getBasisStyle()));
//    	基准利率调整规则
		dto.getFinInterestFactorDto().setResetType(SysDataUtil.getNameFromDataDict(DictCode.重置类型_基准利率调整规则, dto.getFinInterestFactorDto().getResetType()));
//    	延后时间单位
		dto.getFinInterestFactorDto().setObserveBefUnit(SysDataUtil.getNameFromDataDict(DictCode.提前观察单位_延后时间单位, dto.getFinInterestFactorDto().getObserveBefUnit()));
//		浮动利率
		dto.getFinInterestFactorDto().setBenchmarkId(SysDataUtil.getNameFromDataDict(DictCode.利率行情, dto.getFinInterestFactorDto().getBenchmarkId()));
//    	利息利率方式
		dto.getFinInterestFactorDto().setCalRateType(SysDataUtil.getNameFromDataDict(DictCode.计息利率方式, dto.getFinInterestFactorDto().getCalRateType()));
//---------------------------------------------------------------------    	


//		付息信息
//    	计息营业日准则
		dto.getFinPayInterestStdInfoDto().setEndTdayRule(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, dto.getFinPayInterestStdInfoDto().getEndTdayRule()));
//    	付息营业日准则
		dto.getFinPayInterestStdInfoDto().setPayTdayRule(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, dto.getFinPayInterestStdInfoDto().getPayTdayRule()));
//    	付息频率
		dto.getFinPayInterestStdInfoDto().setPayCycle(SysDataUtil.getNameFromDataDict(DictCode.频率, dto.getFinPayInterestStdInfoDto().getPayCycle()));
//    	推算方式
		dto.getFinPayInterestStdInfoDto().setCalcRule(SysDataUtil.getNameFromDataDict(DictCode.推算方式, dto.getFinPayInterestStdInfoDto().getCalcRule()));
//-------------------------------------------------------------------


//    	还本信息
//    	还款周期
		dto.getBussFinRepaymentDto().setPayCycle(SysDataUtil.getNameFromDataDict(DictCode.频率, dto.getBussFinRepaymentDto().getPayCycle()));
//    	是否还本不带息
		dto.getBussFinRepaymentDto().setIsPayIntFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinRepaymentDto().getIsPayIntFlag()));
//-------------------------------------------------------------------    	


//    	评级信息
		List<MstBondCreditRating> mstBondCreditRatingList = dto.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
		for (MstBondCreditRating vo : mstBondCreditRatingList) {
//    		评级类型
			vo.setoType(SysDataUtil.getNameFromDataDict(DictCode.评级类型, vo.getoType()));
//    		长短期
			vo.setShortLongTerm(SysDataUtil.getNameFromDataDict(DictCode.长期短期, vo.getShortLongTerm()));
//    		评级结果
			vo.setGradeResult(SysDataUtil.getNameFromDataDict(DictCode.评级结果, vo.getGradeResult()));
//    		评级机构
			vo.setGradeOrgId(SysDataUtil.getNameFromDataDict(DictCode.评级机构, vo.getGradeOrgId()));
		}
//    	-------------------------------------------------------------------


//    	其他信息
//    	是否含回购条款
		dto.getBussPostInvestInfoDto().setIsRepClause(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussPostInvestInfoDto().getIsRepClause()));
//    	部门内部资产分类
		dto.getBussPostInvestInfoDto().setAssetInsideType(SysDataUtil.getNameFromDataDict(DictCode.部门内部资产分类, dto.getBussPostInvestInfoDto().getAssetInsideType()));
//    	底层资产类型
		dto.getBussPostInvestInfoDto().setAssetBotType(SysDataUtil.getNameFromDataDict(DictCode.底层资产类型, dto.getBussPostInvestInfoDto().getAssetBotType()));

		List<FinProductCredit> finProductCreditList = dto.getBussFinProductCreditDto().getFinProductCreditList();
		for (FinProductCredit vo : finProductCreditList) {
			if (vo.getCreditFlag() != null) {
				vo.setCreditFlag(mobileApprovalMapper.getCustomerName(vo.getCreditFlag()));
				vo.setCreditType(SysDataUtil.getNameFromDataDict(DictCode.增信方式, vo.getCreditType()));
			}
		}

//		-------------------------------------------------------------------



//      获取repAssetType
		String repAssetType = dto.getRepAssetType();
		if (repAssetType .equals("1202")  || repAssetType .equals("1203")  || repAssetType.equals("1204")  || repAssetType.equals("1205")  || repAssetType.equals("2202")
				|| repAssetType.equals("1206")  || repAssetType.equals("1207")  || repAssetType.equals("1208")  || repAssetType.equals("1209")  || repAssetType.equals("2201")) {

//    	资产/负债类别
			dto.getBussRepAssetCreditNonstdInfoDto().setRepAssetType(SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, dto.getBussRepAssetCreditNonstdInfoDto().getRepAssetType()));
//		收/受益权类型
			dto.getBussRepAssetCreditNonstdInfoDto().setRepProfitType(SysDataUtil.getNameFromDataDict(DictCode.收受益权类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepProfitType()));
//		是否属于买入返售
			dto.getBussRepAssetCreditNonstdInfoDto().setRepIsResale(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepIsResale()));
//		是否有预期收益率
			dto.getBussRepAssetCreditNonstdInfoDto().setRepRateFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepRateFlag()));
//    	发行机构类型（规模）
			dto.getBussRepAssetCreditNonstdInfoDto().setRepFinancierType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussRepAssetCreditNonstdInfoDto().getRepFinancierType1()));
//    	发行机构类型（经济类型）
			dto.getBussRepAssetCreditNonstdInfoDto().setRepFinancierType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussRepAssetCreditNonstdInfoDto().getRepFinancierType2()));
//    	发行机构类型（技术领域）
			dto.getBussRepAssetCreditNonstdInfoDto().setRepFinancierType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussRepAssetCreditNonstdInfoDto().getRepFinancierType3()));
//    	交易流通场所
			dto.getBussRepAssetCreditNonstdInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, dto.getBussRepAssetCreditNonstdInfoDto().getRepTradeMarket()));
//    	发行机构所属行业1
			dto.getBussRepAssetCreditNonstdInfoDto().setRepIndustry1(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussRepAssetCreditNonstdInfoDto().getRepIndustry1()));
//    	发行机构所属行业2
			dto.getBussRepAssetCreditNonstdInfoDto().setRepIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussRepAssetCreditNonstdInfoDto().getRepIndustryLevel2()));
//    	融资项目所属行业
			dto.getBussRepAssetCreditNonstdInfoDto().setRepProIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussRepAssetCreditNonstdInfoDto().getRepProIndustry()));
//    	融资项目所属行业2
			dto.getBussRepAssetCreditNonstdInfoDto().setRepProIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussRepAssetCreditNonstdInfoDto().getRepProIndustryLevel2()));
//		项目是否属于重点监控行业和领域
			dto.getBussRepAssetCreditNonstdInfoDto().setRepIndustryFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepIndustryFlag()));
//		重点监控行业和领域类别
			dto.getBussRepAssetCreditNonstdInfoDto().setRepMonitType(SysDataUtil.getNameFromDataDict(DictCode.重点监控行业和领域类别, dto.getBussRepAssetCreditNonstdInfoDto().getRepMonitType()));
//		担保方式
			dto.getBussRepAssetCreditNonstdInfoDto().setRepGuaType(SysDataUtil.getNameFromDataDict(DictCode.担保方式, dto.getBussRepAssetCreditNonstdInfoDto().getRepGuaType()));
//		融资人主体评级
			dto.getBussRepAssetCreditNonstdInfoDto().setRepFinancierGrade(SysDataUtil.getNameFromDataDict(DictCode.资产评级, dto.getBussRepAssetCreditNonstdInfoDto().getRepFinancierGrade()));
//		资产外部评级
			dto.getBussRepAssetCreditNonstdInfoDto().setRepOutGrade(SysDataUtil.getNameFromDataDict(DictCode.资产评级, dto.getBussRepAssetCreditNonstdInfoDto().getRepOutGrade()));
//		融资人所属地区
			dto.getBussRepAssetCreditNonstdInfoDto().setRepFinArea(SysDataUtil.getNameFromDataDict(DictCode.行政区划_市, dto.getBussRepAssetCreditNonstdInfoDto().getRepFinArea()));
//		投向金融资产投资公司或其附属机构发行的私募股权投资基金	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepPrivatefundFlag()));
//		投向“公共私营合作项目”（PPP）的部分		
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepPppflag()));
//		小微企业信贷资产流转和收益权转让			
			dto.getBussRepAssetCreditNonstdInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepSmallcredit()));
//		投向地方政府融资平台的部分	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPrivateprdFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepPrivateprdFlag()));
//		投向市场化债转股相关产品的部分	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepBondtostockFlag()));
//		受让形式	repAssignee	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepAssignee(SysDataUtil.getNameFromDataDict(DictCode.受让形式, dto.getBussRepAssetCreditNonstdInfoDto().getRepAssignee()));
//		计息类型	repCouponSpecies		
			dto.getBussRepAssetCreditNonstdInfoDto().setRepCouponSpecies(SysDataUtil.getNameFromDataDict(DictCode.计息类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepCouponSpecies()));
//		利息分布方式	repIntpayRule		
			dto.getBussRepAssetCreditNonstdInfoDto().setRepIntpayRule(SysDataUtil.getNameFromDataDict(DictCode.利息分布方式, dto.getBussRepAssetCreditNonstdInfoDto().getRepIntpayRule()));
//		结构档次	repStructGrade		
			dto.getBussRepAssetCreditNonstdInfoDto().setRepStructGrade(SysDataUtil.getNameFromDataDict(DictCode.结构档次, dto.getBussRepAssetCreditNonstdInfoDto().getRepStructGrade()));
//		还本方式	repRepaymentType
			dto.getBussRepAssetCreditNonstdInfoDto().setRepRepaymentType(SysDataUtil.getNameFromDataDict(DictCode.还本方式, dto.getBussRepAssetCreditNonstdInfoDto().getRepRepaymentType()));
//		分期还本条款标识	repPeriodRepaymentFlag	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPeriodRepaymentFlag(SysDataUtil.getNameFromDataDict(DictCode.分期还本条款标识, dto.getBussRepAssetCreditNonstdInfoDto().getRepPeriodRepaymentFlag()));
//		含权类型	repContainType	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepContainType(SysDataUtil.getNameFromDataDict(DictCode.含权类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepContainType()));
//		行权方式	repExerciseType		
			dto.getBussRepAssetCreditNonstdInfoDto().setRepExerciseType(SysDataUtil.getNameFromDataDict(DictCode.资产行权方式, dto.getBussRepAssetCreditNonstdInfoDto().getRepExerciseType()));
//		永续条款类型	repPerpetualClauseType	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPerpetualClauseType(SysDataUtil.getNameFromDataDict(DictCode.永续条款类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepPerpetualClauseType()));
//		利息递延条款类型	repDeferredInterestType
			dto.getBussRepAssetCreditNonstdInfoDto().setRepDeferredInterestType(SysDataUtil.getNameFromDataDict(DictCode.利息递延条款类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepDeferredInterestType()));
//		递延利息是否计息	repDeferredInterestInt
			dto.getBussRepAssetCreditNonstdInfoDto().setRepDeferredInterestInt(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepDeferredInterestInt()));
//		部分赎回标识	repPartRedemFlag	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPartRedemFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetCreditNonstdInfoDto().getRepPartRedemFlag()));
//		抵质押物类型	repPledgeType
			dto.getBussRepAssetCreditNonstdInfoDto().setRepPledgeType(SysDataUtil.getNameFromDataDict(DictCode.抵质押物类型, dto.getBussRepAssetCreditNonstdInfoDto().getRepPledgeType()));
//		担保性质	repNatureGuarantee
			dto.getBussRepAssetCreditNonstdInfoDto().setRepNatureGuarantee(SysDataUtil.getNameFromDataDict(DictCode.担保性质, dto.getBussRepAssetCreditNonstdInfoDto().getRepNatureGuarantee()));
//		担保人与融资人关系	repRelationFinan	
			dto.getBussRepAssetCreditNonstdInfoDto().setRepRelationFinan(SysDataUtil.getNameFromDataDict(DictCode.担保人与融资人关系, dto.getBussRepAssetCreditNonstdInfoDto().getRepRelationFinan()));

//设置其他中债报送模板为空
			dto.setBussRepAssetAstBillInfoDto(null);
			dto.setBussRepFundInfoDto(null);
			dto.setBussRepOtherInvestTargetInfoDto(null);
			dto.setBussRepEquityInfoDto(null);
			dto.setBussFinBondRepInfoDto(null);
		} else if (repAssetType.equals("1201")  || repAssetType.equals("1210")) {


//    	BussRepAssetAstBillInfoDto
//    	交易流通场所
			dto.getBussRepAssetAstBillInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, dto.getBussRepAssetAstBillInfoDto().getRepTradeMarket()));
//		是否属于买入返售
			dto.getBussRepAssetAstBillInfoDto().setRepIsResale(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepIsResale()));
//		是否为收/受益权	
			dto.getBussRepAssetAstBillInfoDto().setRepIsRight(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepIsRight()));
//		类型
			dto.getBussRepAssetAstBillInfoDto().setRepAstBillType(SysDataUtil.getNameFromDataDict(DictCode.票据类信用证类资产类型, dto.getBussRepAssetAstBillInfoDto().getRepAstBillType()));
//		投向金融资产投资公司或其附属机构发行的私募股权投资基金	
			dto.getBussRepAssetAstBillInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepPrivatefundFlag()));
//		投向“公共私营合作项目”（PPP）的部分		
			dto.getBussRepAssetAstBillInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepPppflag()));
//		小微企业信贷资产流转和收益权转让			
			dto.getBussRepAssetAstBillInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepSmallcredit()));
//		投向地方政府融资平台的部分	
			dto.getBussRepAssetAstBillInfoDto().setRepPrivateprdFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepPrivateprdFlag()));
//		投向市场化债转股相关产品的部分	
			dto.getBussRepAssetAstBillInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepAssetAstBillInfoDto().getRepBondtostockFlag()));
//设置其他中债报送模板为空
			dto.setBussRepAssetCreditNonstdInfoDto(null);
			dto.setBussRepFundInfoDto(null);
			dto.setBussRepOtherInvestTargetInfoDto(null);
			dto.setBussRepEquityInfoDto(null);
			dto.setBussFinBondRepInfoDto(null);
		} else if (repAssetType.equals("2612") || repAssetType.equals("2401") || repAssetType.equals("2617") || repAssetType.equals("2607")) {
//    	bussRepFundInfoDto
//    	交易流通场所
			dto.getBussRepFundInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, dto.getBussRepFundInfoDto().getRepTradeMarket()));
//    	行业1
			dto.getBussRepFundInfoDto().setRepIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussRepFundInfoDto().getRepIndustry()));
//    	行业2
			dto.getBussRepFundInfoDto().setRepIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussRepFundInfoDto().getRepIndustryLevel2()));
//		投向地方政府融资平台的部分	
			dto.getBussRepFundInfoDto().setRepPrivateprdFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepPrivateprdFlag()));
//		投向市场化债转股相关产品的部分	
			dto.getBussRepFundInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepBondtostockFlag()));
//		投向金融资产投资公司或其附属机构发行的私募股权投资基金	
			dto.getBussRepFundInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepPrivatefundFlag()));
//		投向“公共私营合作项目”（PPP）的部分		
			dto.getBussRepFundInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepPppflag()));
//		小微企业信贷资产流转和收益权转让			
			dto.getBussRepFundInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepSmallcredit()));
//		是否属于政府投资基金			
			dto.getBussRepFundInfoDto().setRepIsGovInvest(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepIsGovInvest()));
//		政府投资基金投向	
			dto.getBussRepFundInfoDto().setRepGovInvestFor(SysDataUtil.getNameFromDataDict(DictCode.政府投资基金投向, dto.getBussRepFundInfoDto().getRepGovInvestFor()));
//		投资阶段	
			dto.getBussRepFundInfoDto().setRepInvestStage(SysDataUtil.getNameFromDataDict(DictCode.投资阶段, dto.getBussRepFundInfoDto().getRepInvestStage()));
//		投资企业类型（技术领域）
			dto.getBussRepFundInfoDto().setRepPartyType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussRepFundInfoDto().getRepPartyType3()));
//		投资企业类型（规模）
			dto.getBussRepFundInfoDto().setRepPartyType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussRepFundInfoDto().getRepPartyType1()));
//		投资企业类型（经济类型）
			dto.getBussRepFundInfoDto().setRepPartyType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussRepFundInfoDto().getRepPartyType2()));
//		是否由金融资产投资公司发行
			dto.getBussRepFundInfoDto().setRepFinancialComissue(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepFinancialComissue()));
//设置其他中债报送模板为空
			dto.setBussRepAssetCreditNonstdInfoDto(null);
			dto.setBussRepAssetAstBillInfoDto(null);
			dto.setBussRepOtherInvestTargetInfoDto(null);
			dto.setBussRepEquityInfoDto(null);
			dto.setBussFinBondRepInfoDto(null);
		} else if (repAssetType.equals("1399")||repAssetType.equals("2299")) {
//    	bussRepOtherInvestTargetInfoDto
//    	交易流通场所
			dto.getBussRepOtherInvestTargetInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, dto.getBussRepOtherInvestTargetInfoDto().getRepTradeMarket()));
//		资产外部评级
			dto.getBussRepOtherInvestTargetInfoDto().setRepOutGrade(SysDataUtil.getNameFromDataDict(DictCode.资产评级, dto.getBussRepOtherInvestTargetInfoDto().getRepOutGrade()));
//    	发行机构类型（规模）
			dto.getBussRepOtherInvestTargetInfoDto().setRepFinancierType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussRepOtherInvestTargetInfoDto().getRepFinancierType1()));
//    	发行机构类型（经济类型）
			dto.getBussRepOtherInvestTargetInfoDto().setRepFinancierType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussRepOtherInvestTargetInfoDto().getRepFinancierType2()));
//    	发行机构类型（技术领域）
			dto.getBussRepOtherInvestTargetInfoDto().setRepFinancierType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussRepOtherInvestTargetInfoDto().getRepFinancierType3()));
//		项目是否属于重点监控行业和领域
			dto.getBussRepOtherInvestTargetInfoDto().setRepIndustryFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepIndustryFlag()));
//		重点监控行业和领域类别

			dto.getBussRepOtherInvestTargetInfoDto().setRepMonitType(SysDataUtil.getNameFromDataDict(DictCode.重点监控行业和领域类别, dto.getBussRepOtherInvestTargetInfoDto().getRepMonitType()));
			dto.getBussRepOtherInvestTargetInfoDto().setRepInstitSize(SysDataUtil.getNameFromDataDict(DictCode.人行机构规模, dto.getBussRepOtherInvestTargetInfoDto().getRepInstitSize()));
			//    	融资人所属行业1
			dto.getBussRepOtherInvestTargetInfoDto().setRepIndustry1(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussRepOtherInvestTargetInfoDto().getRepIndustry1()));
//    	融资人所属行业2
			dto.getBussRepOtherInvestTargetInfoDto().setRepIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussRepOtherInvestTargetInfoDto().getRepIndustryLevel2()));
//    	融资项目所属行业
			dto.getBussRepOtherInvestTargetInfoDto().setRepProIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussRepOtherInvestTargetInfoDto().getRepProIndustry()));
//    	融资项目所属行业2
			dto.getBussRepOtherInvestTargetInfoDto().setRepProIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussRepOtherInvestTargetInfoDto().getRepProIndustryLevel2()));
//		投向金融资产投资公司或其附属机构发行的私募股权投资基金	
			dto.getBussRepOtherInvestTargetInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepPrivatefundFlag()));
//		投向“公共私营合作项目”（PPP）的部分		
			dto.getBussRepOtherInvestTargetInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepPppflag()));
//		小微企业信贷资产流转和收益权转让			
			dto.getBussRepOtherInvestTargetInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepSmallcredit()));
//		投向地方政府融资平台的部分	
			dto.getBussRepOtherInvestTargetInfoDto().setRepPrivateprdFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepPrivateprdFlag()));
//		投向市场化债转股相关产品的部分	
			dto.getBussRepOtherInvestTargetInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepBondtostockFlag()));
//		行内资产类别
			dto.getBussRepOtherInvestTargetInfoDto().setRepBankAssetType(SysDataUtil.getNameFromDataDict(DictCode.行内资产类别, dto.getBussRepOtherInvestTargetInfoDto().getRepBankAssetType()));
//		所属国家或地区
			dto.getBussRepOtherInvestTargetInfoDto().setRepCountryArea(SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, dto.getBussRepOtherInvestTargetInfoDto().getRepCountryArea()));
//		是否有预期收益率
			dto.getBussRepOtherInvestTargetInfoDto().setRepContainExpectedYield2(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepContainExpectedYield2()));
//		是否为债转股
			dto.getBussRepOtherInvestTargetInfoDto().setRepDeptEquity(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepOtherInvestTargetInfoDto().getRepDeptEquity()));
//设置其他中债报送模板为空
			dto.setBussRepAssetCreditNonstdInfoDto(null);
			dto.setBussRepAssetAstBillInfoDto(null);
			dto.setBussRepFundInfoDto(null);
			dto.setBussRepEquityInfoDto(null);
			dto.setBussFinBondRepInfoDto(null);
		}
		else if(repAssetType.equals("1301")){
//		投资阶段
			dto.getBussRepEquityInfoDto().setRepInvestStage(SysDataUtil.getNameFromDataDict(DictCode.投资阶段, dto.getBussRepEquityInfoDto().getRepInvestStage()));
//		投资企业类型（技术领域）
			dto.getBussRepEquityInfoDto().setRepPartyType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussRepEquityInfoDto().getRepPartyType3()));
//		投资企业类型（规模）
			dto.getBussRepEquityInfoDto().setRepPartyType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussRepEquityInfoDto().getRepPartyType1()));
//		投资企业类型（经济类型）
			dto.getBussRepEquityInfoDto().setRepPartyType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussRepEquityInfoDto().getRepPartyType2()));
//		投向金融资产投资公司或其附属机构发行的私募股权投资基金
			dto.getBussRepEquityInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepPrivatefundFlag()));
//		投向“公共私营合作项目”（PPP）的部分
			dto.getBussRepEquityInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepPppflag()));
//		小微企业信贷资产流转和收益权转让
			dto.getBussRepEquityInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepSmallcredit()));
//		投向市场化债转股相关产品的部分
			dto.getBussRepEquityInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepBondtostockFlag()));
//		股票类型
			dto.getBussRepEquityInfoDto().setRepSharesType(SysDataUtil.getNameFromDataDict(DictCode.股票类型, dto.getBussRepEquityInfoDto().getRepSharesType()));
//		是否为质押融资
			dto.getBussRepEquityInfoDto().setRepIsPledge(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepIsPledge()));
//设置其他中债报送模板为空
			dto.setBussRepAssetCreditNonstdInfoDto(null);
			dto.setBussRepAssetAstBillInfoDto(null);
			dto.setBussRepFundInfoDto(null);
			dto.setBussRepOtherInvestTargetInfoDto(null);
			dto.setBussFinBondRepInfoDto(null);

		}










		else if (repAssetType.equals("2101")){
//    	bussFinBondRepInfoDto


//    	资产/负债类别
			dto.getBussFinBondRepInfoDto().setRepAssetType(SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, dto.getBussFinBondRepInfoDto().getRepAssetType()));

//		具体类别
			dto.getBussFinBondRepInfoDto().setRepSectype(SysDataUtil.getNameFromDataDict(DictCode.具体类别, dto.getBussFinBondRepInfoDto().getRepSectype()));
//		 投资企业类型（规模）
			dto.getBussFinBondRepInfoDto().setRepPartyType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussFinBondRepInfoDto().getRepPartyType1()));
//		投资企业类型（经济类型）
			dto.getBussFinBondRepInfoDto().setRepPartyType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussFinBondRepInfoDto().getRepPartyType2()));
//		投资企业类型（技术类型）
			dto.getBussFinBondRepInfoDto().setRepPartyType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussFinBondRepInfoDto().getRepPartyType3()));
//		投向“公共私营合作项目”（PPP）的部分
			dto.getBussFinBondRepInfoDto().setRepPppflag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepEquityInfoDto().getRepPppflag()));

//		发行机构所属行业
			dto.getBussFinBondRepInfoDto().setRepIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussFinBondRepInfoDto().getRepIndustry()));
//		是否由金融资产投资公司发行
			dto.getBussFinBondRepInfoDto().setRepFinancialComissue(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussRepFundInfoDto().getRepFinancialComissue()));
//      是否商业银行二级资本债
			dto.getBussFinBondRepInfoDto().setRepBankSecCapital(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepBankSecCapital()));
//		是否商业银行永续债
			dto.getBussFinBondRepInfoDto().setRepBankSecPerpetual(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepBankSecPerpetual()));

//    	交易流通场所
			dto.getBussFinBondRepInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, dto.getBussFinBondRepInfoDto().getRepTradeMarket()));
//		投向地方政府融资平台的部分
			dto.getBussFinBondRepInfoDto().setRepPrivateprdFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepPrivateprdFlag()));

 //		投向市场化债转股相关产品的部分
			dto.getBussFinBondRepInfoDto().setRepBondtostockFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepBondtostockFlag()));

//		投向金融资产投资公司或其附属机构发行的私募股权投资基金
			dto.getBussFinBondRepInfoDto().setRepPrivatefundFlag(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepPrivatefundFlag()));
//		小微企业信贷资产流转和收益权转让
			dto.getBussFinBondRepInfoDto().setRepSmallcredit(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepSmallcredit()));

			dto.setBussRepAssetCreditNonstdInfoDto(null);
			dto.setBussRepAssetAstBillInfoDto(null);
			dto.setBussRepFundInfoDto(null);
			dto.setBussRepOtherInvestTargetInfoDto(null);
			dto.setBussRepEquityInfoDto(null);
		}








		//    	资产/负债类别
		dto.setRepAssetType(SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, dto.getRepAssetType()));


		//组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
