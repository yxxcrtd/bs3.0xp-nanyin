package com.joyintech.service.risk.assetinfodetail.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussFinBondRepInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussQDIISharesInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepAssetAstBillInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepAssetCreditNonstdInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepAssetMngProdInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepDepositInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepDlRepoInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepEquityInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepFundInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepNobleMetalInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepOthKindInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepOtherInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepOtherInvestTargetInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepOutInvestAgrInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepPlacementInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepQDIIBondInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepQDIIStructureInfoRiskDto;
import com.joyintech.service.risk.assetinfodetail.dto.asset.SvrBussRepQDIIdemolitionInfoRiskDto;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 描述:CHB_ASSET_INFO_SUB表的实体类,用于前台分类展示
 * DateUtil
 * @version
 * @author: Administrator
 * @创建时间: 2019-01-21
 */
public class AssetInfoDetailRiskDto extends RskAssetInfo implements ITableRow,ProcessEntity {


	private  String dataStatus;

	private String vDateStr;

	private String mDateStr;

	private String  errMsg;

	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 选择数据
	 */
	private List<AssetInfoDetailRiskDto> selectedItem;

	/**
	 * 要素id
	 */
	private String elementId;

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

	private SvrBussFinBondRepInfoRiskDto svrBussFinBondRepInfoRiskDto;

	private SvrBussRepDepositInfoRiskDto svrBussRepDepositInfoRiskDto;

	private SvrBussRepDlRepoInfoRiskDto svrBussRepDlRepoInfoRiskDto;

	private SvrBussRepPlacementInfoRiskDto svrBussRepPlacementInfoRiskDto;

	private  SvrBussQDIISharesInfoRiskDto    svrBussQDIISharesInfoRiskDto;

	private SvrBussRepAssetAstBillInfoRiskDto svrBussRepAssetAstBillInfoRiskDto;

	private SvrBussRepAssetCreditNonstdInfoRiskDto svrBussRepAssetCreditNonstdInfoRiskDto;

    private  SvrBussRepAssetMngProdInfoRiskDto  svrBussRepAssetMngProdInfoRiskDto;

    private  SvrBussRepEquityInfoRiskDto   svrBussRepEquityInfoRiskDto;

	private  SvrBussRepFinanciallInfoRiskDto   svrBussRepFinanciallInfoRiskDto;

	private  SvrBussRepFundInfoRiskDto   svrBussRepFundInfoRiskDto;

	private  SvrBussRepNobleMetalInfoRiskDto   svrBussRepNobleMetalInfoRiskDto;

	private  SvrBussRepOtherInfoRiskDto   svrBussRepOtherInfoRiskDto;

	private  SvrBussRepOtherInvestTargetInfoRiskDto   svrBussRepOtherInvestTargetInfoRiskDto;

	private  SvrBussRepOthKindInfoRiskDto   svrBussRepOthKindInfoRiskDto;

	private  SvrBussRepOutInvestAgrInfoRiskDto   svrBussRepOutInvestAgrInfoRiskDto;

	private  SvrBussRepQDIIBondInfoRiskDto   svrBussRepQDIIBondInfoRiskDto;

	private  SvrBussRepQDIIdemolitionInfoRiskDto   svrBussRepQDIIdemolitionInfoRiskDto;

	private  SvrBussRepQDIIStructureInfoRiskDto   svrBussRepQDIIStructureInfoRiskDto;
	
	private  FinProductAdd  finProductAdd;

	public FinProductAdd getFinProductAdd() {
		return finProductAdd;
	}

	public void setFinProductAdd(FinProductAdd finProductAdd) {
		this.finProductAdd = finProductAdd;
	}

	private FinProductType finProductType;

	public FinProductType getFinProductType() {
		return finProductType;
	}

	public void setFinProductType(FinProductType finProductType) {
		this.finProductType = finProductType;
	}

	public SvrBussQDIISharesInfoRiskDto getSvrBussQDIISharesInfoRiskDto() {
		return svrBussQDIISharesInfoRiskDto;
	}

	public void setSvrBussQDIISharesInfoRiskDto(SvrBussQDIISharesInfoRiskDto svrBussQDIISharesInfoRiskDto) {
		this.svrBussQDIISharesInfoRiskDto = svrBussQDIISharesInfoRiskDto;
	}

	public SvrBussRepAssetAstBillInfoRiskDto getSvrBussRepAssetAstBillInfoRiskDto() {
		return svrBussRepAssetAstBillInfoRiskDto;
	}

	public void setSvrBussRepAssetAstBillInfoRiskDto(SvrBussRepAssetAstBillInfoRiskDto svrBussRepAssetAstBillInfoRiskDto) {
		this.svrBussRepAssetAstBillInfoRiskDto = svrBussRepAssetAstBillInfoRiskDto;
	}

	public SvrBussRepAssetCreditNonstdInfoRiskDto getSvrBussRepAssetCreditNonstdInfoRiskDto() {
		return svrBussRepAssetCreditNonstdInfoRiskDto;
	}

	public void setSvrBussRepAssetCreditNonstdInfoRiskDto(SvrBussRepAssetCreditNonstdInfoRiskDto svrBussRepAssetCreditNonstdInfoRiskDto) {
		this.svrBussRepAssetCreditNonstdInfoRiskDto = svrBussRepAssetCreditNonstdInfoRiskDto;
	}

	public SvrBussRepAssetMngProdInfoRiskDto getSvrBussRepAssetMngProdInfoRiskDto() {
		return svrBussRepAssetMngProdInfoRiskDto;
	}

	public void setSvrBussRepAssetMngProdInfoRiskDto(SvrBussRepAssetMngProdInfoRiskDto svrBussRepAssetMngProdInfoRiskDto) {
		this.svrBussRepAssetMngProdInfoRiskDto = svrBussRepAssetMngProdInfoRiskDto;
	}

	public SvrBussRepEquityInfoRiskDto getSvrBussRepEquityInfoRiskDto() {
		return svrBussRepEquityInfoRiskDto;
	}

	public void setSvrBussRepEquityInfoRiskDto(SvrBussRepEquityInfoRiskDto svrBussRepEquityInfoRiskDto) {
		this.svrBussRepEquityInfoRiskDto = svrBussRepEquityInfoRiskDto;
	}

	public SvrBussRepFinanciallInfoRiskDto getSvrBussRepFinanciallInfoRiskDto() {
		return svrBussRepFinanciallInfoRiskDto;
	}

	public void setSvrBussRepFinanciallInfoRiskDto(SvrBussRepFinanciallInfoRiskDto svrBussRepFinanciallInfoRiskDto) {
		this.svrBussRepFinanciallInfoRiskDto = svrBussRepFinanciallInfoRiskDto;
	}

	public SvrBussRepFundInfoRiskDto getSvrBussRepFundInfoRiskDto() {
		return svrBussRepFundInfoRiskDto;
	}

	public void setSvrBussRepFundInfoRiskDto(SvrBussRepFundInfoRiskDto svrBussRepFundInfoRiskDto) {
		this.svrBussRepFundInfoRiskDto = svrBussRepFundInfoRiskDto;
	}

	public SvrBussRepNobleMetalInfoRiskDto getSvrBussRepNobleMetalInfoRiskDto() {
		return svrBussRepNobleMetalInfoRiskDto;
	}

	public void setSvrBussRepNobleMetalInfoRiskDto(SvrBussRepNobleMetalInfoRiskDto svrBussRepNobleMetalInfoRiskDto) {
		this.svrBussRepNobleMetalInfoRiskDto = svrBussRepNobleMetalInfoRiskDto;
	}

	public SvrBussRepOtherInfoRiskDto getSvrBussRepOtherInfoRiskDto() {
		return svrBussRepOtherInfoRiskDto;
	}

	public void setSvrBussRepOtherInfoRiskDto(SvrBussRepOtherInfoRiskDto svrBussRepOtherInfoRiskDto) {
		this.svrBussRepOtherInfoRiskDto = svrBussRepOtherInfoRiskDto;
	}

	public SvrBussRepOtherInvestTargetInfoRiskDto getSvrBussRepOtherInvestTargetInfoRiskDto() {
		return svrBussRepOtherInvestTargetInfoRiskDto;
	}

	public void setSvrBussRepOtherInvestTargetInfoRiskDto(SvrBussRepOtherInvestTargetInfoRiskDto svrBussRepOtherInvestTargetInfoRiskDto) {
		this.svrBussRepOtherInvestTargetInfoRiskDto = svrBussRepOtherInvestTargetInfoRiskDto;
	}

	public SvrBussRepOthKindInfoRiskDto getSvrBussRepOthKindInfoRiskDto() {
		return svrBussRepOthKindInfoRiskDto;
	}

	public void setSvrBussRepOthKindInfoRiskDto(SvrBussRepOthKindInfoRiskDto svrBussRepOthKindInfoRiskDto) {
		this.svrBussRepOthKindInfoRiskDto = svrBussRepOthKindInfoRiskDto;
	}

	public SvrBussRepOutInvestAgrInfoRiskDto getSvrBussRepOutInvestAgrInfoRiskDto() {
		return svrBussRepOutInvestAgrInfoRiskDto;
	}

	public void setSvrBussRepOutInvestAgrInfoRiskDto(SvrBussRepOutInvestAgrInfoRiskDto svrBussRepOutInvestAgrInfoRiskDto) {
		this.svrBussRepOutInvestAgrInfoRiskDto = svrBussRepOutInvestAgrInfoRiskDto;
	}

	public SvrBussRepQDIIBondInfoRiskDto getSvrBussRepQDIIBondInfoRiskDto() {
		return svrBussRepQDIIBondInfoRiskDto;
	}

	public void setSvrBussRepQDIIBondInfoRiskDto(SvrBussRepQDIIBondInfoRiskDto svrBussRepQDIIBondInfoRiskDto) {
		this.svrBussRepQDIIBondInfoRiskDto = svrBussRepQDIIBondInfoRiskDto;
	}

	public SvrBussRepQDIIdemolitionInfoRiskDto getSvrBussRepQDIIdemolitionInfoRiskDto() {
		return svrBussRepQDIIdemolitionInfoRiskDto;
	}

	public void setSvrBussRepQDIIdemolitionInfoRiskDto(SvrBussRepQDIIdemolitionInfoRiskDto svrBussRepQDIIdemolitionInfoRiskDto) {
		this.svrBussRepQDIIdemolitionInfoRiskDto = svrBussRepQDIIdemolitionInfoRiskDto;
	}

	public SvrBussRepQDIIStructureInfoRiskDto getSvrBussRepQDIIStructureInfoRiskDto() {
		return svrBussRepQDIIStructureInfoRiskDto;
	}

	public void setSvrBussRepQDIIStructureInfoRiskDto(SvrBussRepQDIIStructureInfoRiskDto svrBussRepQDIIStructureInfoRiskDto) {
		this.svrBussRepQDIIStructureInfoRiskDto = svrBussRepQDIIStructureInfoRiskDto;
	}

	public SvrBussFinBondRepInfoRiskDto getSvrBussFinBondRepInfoRiskDto() {
		return svrBussFinBondRepInfoRiskDto;
	}

	public void setSvrBussFinBondRepInfoRiskDto(SvrBussFinBondRepInfoRiskDto svrBussFinBondRepInfoRiskDto) {
		this.svrBussFinBondRepInfoRiskDto = svrBussFinBondRepInfoRiskDto;
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


	public SvrBussRepDepositInfoRiskDto getSvrBussRepDepositInfoRiskDto() {
		return svrBussRepDepositInfoRiskDto;
	}

	public void setSvrBussRepDepositInfoRiskDto(SvrBussRepDepositInfoRiskDto svrBussRepDepositInfoRiskDto) {
		this.svrBussRepDepositInfoRiskDto = svrBussRepDepositInfoRiskDto;
	}

	public SvrBussRepDlRepoInfoRiskDto getSvrBussRepDlRepoInfoRiskDto() {
		return svrBussRepDlRepoInfoRiskDto;
	}

	public void setSvrBussRepDlRepoInfoRiskDto(SvrBussRepDlRepoInfoRiskDto svrBussRepDlRepoInfoRiskDto) {
		this.svrBussRepDlRepoInfoRiskDto = svrBussRepDlRepoInfoRiskDto;
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


	public SvrBussRepPlacementInfoRiskDto getSvrBussRepPlacementInfoRiskDto() {
		return svrBussRepPlacementInfoRiskDto;
	}

	public void setSvrBussRepPlacementInfoRiskDto(SvrBussRepPlacementInfoRiskDto svrBussRepPlacementInfoRiskDto) {
		this.svrBussRepPlacementInfoRiskDto = svrBussRepPlacementInfoRiskDto;
	}
	
	public String getCcyStr() {
		 return SysDataUtil.getNameFromDataDict(DictCode.币种_中债, super.getCcy());
	}
	
	public String getTradeMarketStr() {
		 return SysDataUtil.getNameFromDataDict(DictCode.交易流通场所_中债, super.getTradeMarket());
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<AssetInfoDetailRiskDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<AssetInfoDetailRiskDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
}