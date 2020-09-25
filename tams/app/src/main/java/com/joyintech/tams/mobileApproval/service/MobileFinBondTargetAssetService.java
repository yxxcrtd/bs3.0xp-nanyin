/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobileFinBondTargetAssetService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileFinBondTargetAssetService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	FinBondTargetAssetDto dto = JsonUtils.json2Object(businessData, FinBondTargetAssetDto.class);

    	//2、处理数据字典
//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
//    	获取交易日历
    	dto.setCalendarName(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
//		获取发行人名称
    	dto.setIssuerName(mobileApprovalMapper.getCustomerName(dto.getIssuer()));
//		发行方式    	
    	dto.setIssueType(SysDataUtil.getNameFromDataDict(DictCode.发行方式, dto.getIssueType()));
//		是否wind更新
    	if(dto.getIsAutoUpdate()!=null && "Y".equals(dto.getIsAutoUpdate())) {
    		dto.setIsAutoUpdate("是");
    	}else {
    		dto.setIsAutoUpdate("否");
    	}
//		境内外
    	dto.setBlnArea(SysDataUtil.getNameFromDataDict(DictCode.境内外, dto.getBlnArea()));


//      行权计划
		if((null!=dto.getBusiFinExercisePlanDto()) && (null!=dto.getBusiFinExercisePlanDto().getFinExercisePlanList())) {
			for (FinExercisePlan finExercisePlan : dto.getBusiFinExercisePlanDto().getFinExercisePlanList()) {
				finExercisePlan.setExerciseStatus(SysDataUtil.getNameFromDataDict(DictCode.行权状态, finExercisePlan.getExerciseStatus()));
				finExercisePlan.setExerciseType(SysDataUtil.getNameFromDataDict(DictCode.行权方式, finExercisePlan.getExerciseType()));

			}
		}

//		是否保本
    	dto.setCapiIncomeFeature(SysDataUtil.getNameFromDataDict(DictCode.本金收益特征, dto.getCapiIncomeFeature()));

//		是否金融机构发行    	
    	if(dto.getIsFinOrg()!=null && "Y".equals(dto.getIsFinOrg())) {
    		dto.setIsFinOrg("是");
    	}else {
    		dto.setIsFinOrg("否");
    	}
//    	获取担保人名称
    	dto.setSponsor(mobileApprovalMapper.getCustomerName(dto.getSponsor()));
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
//---------------------------------------------------------------------    	
    	
    	
//		付息信息
//    	营业日准则
    	dto.getFinPayInterestStdInfoDto().setPayTdayRule(SysDataUtil.getNameFromDataDict(DictCode.营业日准则, dto.getFinPayInterestStdInfoDto().getPayTdayRule()));
//    	付息频率
    	dto.getFinPayInterestStdInfoDto().setPayCycle(SysDataUtil.getNameFromDataDict(DictCode.频率, dto.getFinPayInterestStdInfoDto().getPayCycle()));
//    	推算方式
    	dto.getFinPayInterestStdInfoDto().setCalcRule(SysDataUtil.getNameFromDataDict(DictCode.推算方式, dto.getFinPayInterestStdInfoDto().getCalcRule()));
//-------------------------------------------------------------------
    	
//    	评级信息
    	List<MstBondCreditRating> mstBondCreditRatingList=dto.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
    	for(MstBondCreditRating vo :mstBondCreditRatingList){
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
    	
    	
    	
//    	资产/负债类别
    	dto.setRepAssetType(SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, dto.getRepAssetType()));
//    	具体类别
    	dto.getBussFinBondRepInfoDto().setRepSectype(SysDataUtil.getNameFromDataDict(DictCode.具体类别, dto.getBussFinBondRepInfoDto().getRepSectype()));
//    	发行机构类型（规模）
    	dto.getBussFinBondRepInfoDto().setRepPartyType1(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, dto.getBussFinBondRepInfoDto().getRepPartyType1()));
//    	发行机构类型（经济类型）
    	dto.getBussFinBondRepInfoDto().setRepPartyType2(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, dto.getBussFinBondRepInfoDto().getRepPartyType2()));
//    	发行机构类型（技术领域）
    	dto.getBussFinBondRepInfoDto().setRepPartyType3(SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, dto.getBussFinBondRepInfoDto().getRepPartyType3()));
// 人行机构规模
		dto.getBussFinBondRepInfoDto().setRepInstitSize(SysDataUtil.getNameFromDataDict(DictCode.人行机构规模, dto.getBussFinBondRepInfoDto().getRepInstitSize()));

    	//    	发行机构所属行业1
    	dto.getBussFinBondRepInfoDto().setRepIndustry(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, dto.getBussFinBondRepInfoDto().getRepIndustry()));
//    	发行机构所属行业2
    	dto.getBussFinBondRepInfoDto().setRepIndustryLevel2(SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, dto.getBussFinBondRepInfoDto().getRepIndustryLevel2()));
//    	是否有金融资产投资公司发行
    	dto.getBussFinBondRepInfoDto().setRepFinancialComissue(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepFinancialComissue()));
//    	是否商业银行二级资本债
    	dto.getBussFinBondRepInfoDto().setRepBankSecCapital(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepBankSecCapital()));
//    	是否商业银行永续债
    	dto.getBussFinBondRepInfoDto().setRepBankSecPerpetual(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getBussFinBondRepInfoDto().getRepBankSecPerpetual()));
//债券性质
		dto.getBussFinBondRepInfoDto().setRepSecNature(SysDataUtil.getNameFromDataDict(DictCode.债券性质, dto.getBussFinBondRepInfoDto().getRepSecNature()));

    	// 		交易流通场所
    	dto.getBussFinBondRepInfoDto().setRepTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易流通场所, dto.getBussFinBondRepInfoDto().getRepTradeMarket()));
    	
    	
    	//组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
