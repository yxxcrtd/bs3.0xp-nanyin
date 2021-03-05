package com.joyintech.tams.svreport.chb.assetinfodetail.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
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
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.AssetInfoDetailValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

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
}