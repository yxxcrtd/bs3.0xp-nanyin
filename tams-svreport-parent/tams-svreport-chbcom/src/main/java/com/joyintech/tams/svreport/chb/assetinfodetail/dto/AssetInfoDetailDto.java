package com.joyintech.tams.svreport.chb.assetinfodetail.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.AssetInfoDetailValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述:CHB_ASSET_INFO_SUB表的实体类,用于前台分类展示
 * DateUtil
 * @version
 * @author: Administrator
 * @创建时间: 2019-01-21
 */
@TableName("CHB_ASSET_INFO")
@PageId("AssetInfoRegist002")
@ValidLogic(targetClass = AssetInfoDetailValidator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class AssetInfoDetailDto extends ChbAssetInfo implements ITableRow,ProcessEntity {
	
	
	private  String dataStatus;

	private String vDateStr;

	private String mDateStr;

	private String  errMsg;

	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 要素id
	 */
	@PrimaryKey(Field.PK_1)
	private String elementId;

	/**
	 * 目的和意义以及可能会用到的地方
	 */
	private String pageModel;

	/**
	 * 选择数据
	 */
	private List<AssetInfoDetailDto> selectedItem;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getvDateStr() {
		return vDateStr;
	}

	public void setvDateStr(String vDateStr) {
		this.vDateStr = vDateStr;
	}

	public String getmDateStr() {
		return mDateStr;
	}

	public void setmDateStr(String mDateStr) {
		this.mDateStr = mDateStr;
	}

	private SvrBussFinBondRepInfoDto svrBussFinBondRepInfoDto;

	private SvrBussRepDepositInfoDto svrBussRepDepositInfoDto;

	private SvrBussRepDlRepoInfoDto svrBussRepDlRepoInfoDto;

	private SvrBussRepPlacementInfoDto svrBussRepPlacementInfoDto;

	private  SvrBussQDIISharesInfoDto    svrBussQDIISharesInfoDto;

	private SvrBussRepAssetAstBillInfoDto svrBussRepAssetAstBillInfoDto;

	private SvrBussRepAssetCreditNonstdInfoDto svrBussRepAssetCreditNonstdInfoDto;

    private  SvrBussRepAssetMngProdInfoDto  svrBussRepAssetMngProdInfoDto;

    private  SvrBussRepEquityInfoDto   svrBussRepEquityInfoDto;

	private  SvrBussRepFinanciallInfoDto   svrBussRepFinanciallInfoDto;

	private  SvrBussRepFundInfoDto   svrBussRepFundInfoDto;

	private  SvrBussRepNobleMetalInfoDto   svrBussRepNobleMetalInfoDto;

	private  SvrBussRepOtherInfoDto   svrBussRepOtherInfoDto;

	private  SvrBussRepOtherInvestTargetInfoDto   svrBussRepOtherInvestTargetInfoDto;

	private  SvrBussRepOthKindInfoDto   svrBussRepOthKindInfoDto;

	private  SvrBussRepOutInvestAgrInfoDto   svrBussRepOutInvestAgrInfoDto;

	private  SvrBussRepQDIIBondInfoDto   svrBussRepQDIIBondInfoDto;

	private  SvrBussRepQDIIdemolitionInfoDto   svrBussRepQDIIdemolitionInfoDto;

	private  SvrBussRepQDIIStructureInfoDto   svrBussRepQDIIStructureInfoDto;


	public SvrBussQDIISharesInfoDto getSvrBussQDIISharesInfoDto() {
		return svrBussQDIISharesInfoDto;
	}

	public void setSvrBussQDIISharesInfoDto(SvrBussQDIISharesInfoDto svrBussQDIISharesInfoDto) {
		this.svrBussQDIISharesInfoDto = svrBussQDIISharesInfoDto;
	}

	public SvrBussRepAssetAstBillInfoDto getSvrBussRepAssetAstBillInfoDto() {
		return svrBussRepAssetAstBillInfoDto;
	}

	public void setSvrBussRepAssetAstBillInfoDto(SvrBussRepAssetAstBillInfoDto svrBussRepAssetAstBillInfoDto) {
		this.svrBussRepAssetAstBillInfoDto = svrBussRepAssetAstBillInfoDto;
	}

	public SvrBussRepAssetCreditNonstdInfoDto getSvrBussRepAssetCreditNonstdInfoDto() {
		return svrBussRepAssetCreditNonstdInfoDto;
	}

	public void setSvrBussRepAssetCreditNonstdInfoDto(SvrBussRepAssetCreditNonstdInfoDto svrBussRepAssetCreditNonstdInfoDto) {
		this.svrBussRepAssetCreditNonstdInfoDto = svrBussRepAssetCreditNonstdInfoDto;
	}

	public SvrBussRepAssetMngProdInfoDto getSvrBussRepAssetMngProdInfoDto() {
		return svrBussRepAssetMngProdInfoDto;
	}

	public void setSvrBussRepAssetMngProdInfoDto(SvrBussRepAssetMngProdInfoDto svrBussRepAssetMngProdInfoDto) {
		this.svrBussRepAssetMngProdInfoDto = svrBussRepAssetMngProdInfoDto;
	}

	public SvrBussRepEquityInfoDto getSvrBussRepEquityInfoDto() {
		return svrBussRepEquityInfoDto;
	}

	public void setSvrBussRepEquityInfoDto(SvrBussRepEquityInfoDto svrBussRepEquityInfoDto) {
		this.svrBussRepEquityInfoDto = svrBussRepEquityInfoDto;
	}

	public SvrBussRepFinanciallInfoDto getSvrBussRepFinanciallInfoDto() {
		return svrBussRepFinanciallInfoDto;
	}

	public void setSvrBussRepFinanciallInfoDto(SvrBussRepFinanciallInfoDto svrBussRepFinanciallInfoDto) {
		this.svrBussRepFinanciallInfoDto = svrBussRepFinanciallInfoDto;
	}

	public SvrBussRepFundInfoDto getSvrBussRepFundInfoDto() {
		return svrBussRepFundInfoDto;
	}

	public void setSvrBussRepFundInfoDto(SvrBussRepFundInfoDto svrBussRepFundInfoDto) {
		this.svrBussRepFundInfoDto = svrBussRepFundInfoDto;
	}

	public SvrBussRepNobleMetalInfoDto getSvrBussRepNobleMetalInfoDto() {
		return svrBussRepNobleMetalInfoDto;
	}

	public void setSvrBussRepNobleMetalInfoDto(SvrBussRepNobleMetalInfoDto svrBussRepNobleMetalInfoDto) {
		this.svrBussRepNobleMetalInfoDto = svrBussRepNobleMetalInfoDto;
	}

	public SvrBussRepOtherInfoDto getSvrBussRepOtherInfoDto() {
		return svrBussRepOtherInfoDto;
	}

	public void setSvrBussRepOtherInfoDto(SvrBussRepOtherInfoDto svrBussRepOtherInfoDto) {
		this.svrBussRepOtherInfoDto = svrBussRepOtherInfoDto;
	}

	public SvrBussRepOtherInvestTargetInfoDto getSvrBussRepOtherInvestTargetInfoDto() {
		return svrBussRepOtherInvestTargetInfoDto;
	}

	public void setSvrBussRepOtherInvestTargetInfoDto(SvrBussRepOtherInvestTargetInfoDto svrBussRepOtherInvestTargetInfoDto) {
		this.svrBussRepOtherInvestTargetInfoDto = svrBussRepOtherInvestTargetInfoDto;
	}

	public SvrBussRepOthKindInfoDto getSvrBussRepOthKindInfoDto() {
		return svrBussRepOthKindInfoDto;
	}

	public void setSvrBussRepOthKindInfoDto(SvrBussRepOthKindInfoDto svrBussRepOthKindInfoDto) {
		this.svrBussRepOthKindInfoDto = svrBussRepOthKindInfoDto;
	}

	public SvrBussRepOutInvestAgrInfoDto getSvrBussRepOutInvestAgrInfoDto() {
		return svrBussRepOutInvestAgrInfoDto;
	}

	public void setSvrBussRepOutInvestAgrInfoDto(SvrBussRepOutInvestAgrInfoDto svrBussRepOutInvestAgrInfoDto) {
		this.svrBussRepOutInvestAgrInfoDto = svrBussRepOutInvestAgrInfoDto;
	}

	public SvrBussRepQDIIBondInfoDto getSvrBussRepQDIIBondInfoDto() {
		return svrBussRepQDIIBondInfoDto;
	}

	public void setSvrBussRepQDIIBondInfoDto(SvrBussRepQDIIBondInfoDto svrBussRepQDIIBondInfoDto) {
		this.svrBussRepQDIIBondInfoDto = svrBussRepQDIIBondInfoDto;
	}

	public SvrBussRepQDIIdemolitionInfoDto getSvrBussRepQDIIdemolitionInfoDto() {
		return svrBussRepQDIIdemolitionInfoDto;
	}

	public void setSvrBussRepQDIIdemolitionInfoDto(SvrBussRepQDIIdemolitionInfoDto svrBussRepQDIIdemolitionInfoDto) {
		this.svrBussRepQDIIdemolitionInfoDto = svrBussRepQDIIdemolitionInfoDto;
	}

	public SvrBussRepQDIIStructureInfoDto getSvrBussRepQDIIStructureInfoDto() {
		return svrBussRepQDIIStructureInfoDto;
	}

	public void setSvrBussRepQDIIStructureInfoDto(SvrBussRepQDIIStructureInfoDto svrBussRepQDIIStructureInfoDto) {
		this.svrBussRepQDIIStructureInfoDto = svrBussRepQDIIStructureInfoDto;
	}

	public SvrBussFinBondRepInfoDto getSvrBussFinBondRepInfoDto() {
		return svrBussFinBondRepInfoDto;
	}

	public void setSvrBussFinBondRepInfoDto(SvrBussFinBondRepInfoDto svrBussFinBondRepInfoDto) {
		this.svrBussFinBondRepInfoDto = svrBussFinBondRepInfoDto;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDataStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_处理状态, this.getDataStatus());
	}


	public SvrBussRepDepositInfoDto getSvrBussRepDepositInfoDto() {
		return svrBussRepDepositInfoDto;
	}

	public void setSvrBussRepDepositInfoDto(SvrBussRepDepositInfoDto svrBussRepDepositInfoDto) {
		this.svrBussRepDepositInfoDto = svrBussRepDepositInfoDto;
	}

	public SvrBussRepDlRepoInfoDto getSvrBussRepDlRepoInfoDto() {
		return svrBussRepDlRepoInfoDto;
	}

	public void setSvrBussRepDlRepoInfoDto(SvrBussRepDlRepoInfoDto svrBussRepDlRepoInfoDto) {
		this.svrBussRepDlRepoInfoDto = svrBussRepDlRepoInfoDto;
	}

	/**
	 * 资产负债类别
	 * @return
	 */
	public String getAssetTypeStr() {

		return SysDataUtil.getNameFromDataDict(DictCode.资产_负债类别, this.getAssetType());
	}
	/**
	 * 资产_负债二级类别
	 * @return
	 */
	public String getAssetType2Str() {

		return SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, this.getAssetType2());
	}
	/**
	 * 资产一级类型
	 * @return
	 */
	public String getOrgAssetType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产一级分类, this.getOrgAssetType1());
	}
	/**
	 * 资产二级类型
	 * @return
	 */
	public String getOrgAssetType2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产二级分类, this.getOrgAssetType2());
	}


//	/**
//	 * 资产二级类型
//	 * @return
//	 */
//	public String getStatusStr() {
//		return SysDataUtil.getNameFromDataDict(DictCode.中债对应上游资产二级类型, this.getOrgAssetType2());
//	}


	public SvrBussRepPlacementInfoDto getSvrBussRepPlacementInfoDto() {
		return svrBussRepPlacementInfoDto;
	}

	public void setSvrBussRepPlacementInfoDto(SvrBussRepPlacementInfoDto svrBussRepPlacementInfoDto) {
		this.svrBussRepPlacementInfoDto = svrBussRepPlacementInfoDto;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public List<AssetInfoDetailDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<AssetInfoDetailDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	public String getvdateStr(){
		return null!=getVdate()?DateUtil.format(getVdate()):null;
	}
	public String getmdateStr(){
		return null!=getMdate()?DateUtil.format(getMdate()):null;
	}
	public String getStatusStr() {
		return com.joyintech.tams.report.common.util.SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,getStatus());
	}
	public String getTradeMarketStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.交易流通场所,getTradeMarket());
	}
	/**
	 * 资产三级类型
	 * @return
	 */
	public String getOrgAssetType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产三级分类, this.getOrgAssetType3());
	}
	public String getOrgAssetType4Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产四级分类, this.getOrgAssetType4());
	}
	public String getOrgAssetType5Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产五级分类, this.getOrgAssetType5());
	}
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
	}
	public String getbasisStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.计息基础_中债, this.getBasis());
	}
	public String getDepoTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.存款类型, this.getDepoType());
	}
	public String getTargetTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.标的类别, this.getTargetType());
	}
	public String getChbSectypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.具体类别, this.getChbSectype());
	}
	public String getIssueModeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行方式, this.getIssueMode());
	}
	public String getSecMainGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.主体评级_中债, this.getSecMainGrade());
	}
	public String getPartyType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, this.getPartyType1());
	}
	public String getPartyType2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, this.getPartyType2());
	}
	public String getPartyType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, this.getPartyType3());
	}
	public String getIndustryLevel2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, this.getIndustryLevel2());
	}
	public String getRegistOrgStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, this.getRegistOrg());
	}
	public String getRivalPartyTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.对手方类型, this.getRivalPartyType());
	}
	public String getTargetType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.回购标的类别, this.getTargetType1());
	}
	public String getProfitTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.收受益权类型, this.getProfitType());
	}
	public String getIsResaleStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsResale());
	}
	public String getRateFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getRateFlag());
	}
	public String getCouponSpeciesStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.计息类型, this.getCouponSpecies());
	}
	public String getInterestRuleFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getInterestRuleFlag());
	}
	public String getIntpayRuleStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.利息分布方式, this.getIntpayRule());
	}
	public String getBasisTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.基准利率, this.getBasisType());
	}
	public String getFloatFactorFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getFloatFactorFlag());
	}
	public String getStructGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.结构档次, this.getStructGrade());
	}
	public String getRepaymentTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.还本方式, this.getRepaymentType());
	}
	public String getPeriodRepaymentFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.分期还本条款标识, this.getPeriodRepaymentFlag());
	}
	public String getFinancierType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, this.getFinancierType1());
	}
	public String getFinancierType2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, this.getFinancierType2());
	}
	public String getFinancierType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, this.getFinancierType3());
	}
	public String getProIndustryLevel2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, this.getProIndustryLevel2());
	}
	public String getIndustryFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIndustryFlag());
	}
	public String getMonitTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.重点监控行业和领域类别, this.getMonitType());
	}
	public String getGuaTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.担保方式, this.getGuaType());
	}
	public String getPledgeTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.抵质押物类型, this.getPledgeType());
	}
	public String getNatureGuaranteeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.担保性质, this.getNatureGuarantee());
	}
	public String getRelationFinanStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.担保人与融资人关系, this.getRelationFinan());
	}
	public String getFinancierGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产评级, this.getFinancierGrade());
	}
	public String getOutGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产评级, this.getOutGrade());
	}
	public String getContainTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.含权类型, this.getContainType());
	}
	public String getExerciseTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产行权方式, this.getExerciseType());
	}
	public String getPerpetualClauseTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.永续条款类型, this.getPerpetualClauseType());
	}
	public String getDeferredInterestTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.利息递延条款类型, this.getDeferredInterestType());
	}
	public String getDeferredInterestIntStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getDeferredInterestInt());
	}
	public String getPartRedemFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPartRedemFlag());
	}
	public String getFinAreaStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.行政区划_省市县, this.getFinArea());
	}
	public String getIsRightStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsRight());
	}
	public String getNotstandSecTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.票据类信用证类资产类型, this.getNotstandSecType());
	}
	public String getIsBzhpjStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsBzhpj());
	}
	public String getIndustryShares2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, this.getIndustryShares2());
	}
	public String getSharesTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.股票类型, this.getSharesType());
	}
	public String getInvestStageStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.投资阶段, this.getInvestStage());
	}
	public String getSharesType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, this.getSharesType1());
	}
	public String getSharesType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按技术领域划分, this.getSharesType3());
	}
	public String getSharesType2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按经济类型划分, this.getSharesType2());
	}
	public String getIsPledgeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsPledge());
	}
	public String getDeptEquityStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getDeptEquity());
	}
	public String getTargetType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.标的类别, this.getTargetType3());
	}
	public String getHoldPurposeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.持有目的, this.getHoldPurpose());
	}
	public String getCountryAreaStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区, this.getCountryArea());
	}
	public String getPartyGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产评级, this.getPartyGrade());
	}
	public String getFinancialProductFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getFinancialProductFlag());
	}
	public String getFinancialComissueStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getFinancialComissue());
	}
	public String getTruActTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资金实际投向, this.getTruActType());
	}
	public String getCapUseInduLevel2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, this.getCapUseInduLevel2());
	}
	public String getProPropertyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资管计划属性, this.getProProperty());
	}
	public String getBuyStructStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.购买结构, this.getBuyStruct());
	}
	public String getManageTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.管理方式, this.getManageType());
	}
	public String getInAssetTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.行内资产类别, this.getInAssetType());
	}
	public String getRegistOrg1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.登记备案机构, this.getRegistOrg1());
	}
	public String getProfitFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getProfitFlag());
	}
	public String getIsGovernFundStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsGovernFund());
	}
	public String getGovernInvestFundStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.政府投资基金投向, this.getGovernInvestFund());
	}
	public String getIsDepositFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsDepositFlag());
	}
	public String getPppflagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPppflag());
	}
	public String getBankSecZxzqStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getBankSecZxzq());
	}
	public String getIsBankCapStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsBankCap());
	}
	public String getBankCapitalStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_银行资本补充工具, this.getBankCapital());
	}
	public String getBondtostockFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getBondtostockFlag());
	}
	public String getPrivatefundFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivatefundFlag());
	}
	public String getIsunlistedStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsunlisted());
	}
	public String getPrivateprdFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivateprdFlag());
	}
	public String getPrivateFlagStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivateFlag());
	}
	public String getPbcAssetType1Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.人行资产一级分类, this.getPbcAssetType1());
	}
	public String getPbcAssetType2Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.人行资产二级分类, this.getPbcAssetType2());
	}
	public String getPbcAssetType3Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.人行资产三级分类, this.getPbcAssetType3());
	}
	public String getIsConstracteBusiStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsConstracteBusi());
	}
	public String getConstracteBusiTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.结构化业务类型, this.getConstracteBusiType());
	}
	public String getConstracteGradeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.结构化层级, this.getConstracteGrade());
	}
}