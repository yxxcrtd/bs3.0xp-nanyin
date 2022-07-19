
package com.joyintech.tams.disclosure.information.config.prodassetinfo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdAsset;
import com.joyintech.tams.common.base.entity.DisProdPartner;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.validator.ProdAssetInfoValidator;
import com.joyintech.tams.disclosure.information.config.prodpartner.validator.ProdPartnerInfoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("ProdAssetInfo002")
@ValidLogic(targetClass = ProdAssetInfoValidator.class, groups = {ValidateGroupEdit.class,
		ValidateGroupDelete.class})
public class ProdAssetInfoDto extends DisProdAsset implements ProcessEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String elementId;

	/**
	 * 选中的数据
	 */
	private transient List<ProdAssetInfoDto> selectedItem;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String pageModel;

	/**
	 * 产品名称
	 */
	private String prodName;

	/**
	 * 更新时间str
	 * @return
	 */
	private String updateTimeStr;

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

	public List<ProdAssetInfoDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ProdAssetInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	@Override
	@NotEmpty(fieldName = "产品主代码",groups={ValidateGroupEdit.class})
	public String getProdId() {
		return super.getProdId();
	}

	@NotEmpty(fieldName = "产品名称",groups={ValidateGroupEdit.class})
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Override
	@NotEmpty(fieldName = "穿透前固定收益类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getFixPreRatio() {
		return super.getFixPreRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透后固定收益类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getFixAfterRatio() {
		return super.getFixAfterRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透前权益类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getEquityPreRatio() {
		return super.getEquityPreRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透后权益类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getEquityAfterRatio() {
		return super.getEquityAfterRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透前商品及金融衍生品类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getFdPreRatio() {
		return super.getFdPreRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透后商品及金融衍生品类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getFdAfterRatio() {
		return super.getFdAfterRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透前混合类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getMixPreRatio() {
		return super.getMixPreRatio();
	}

	@Override
	@NotEmpty(fieldName = "穿透后混合类资产占总资产比例",groups={ValidateGroupEdit.class})
	public BigDecimal getMixAfterRatio() {
		return super.getMixAfterRatio();
	}

	@Override
	@NotEmpty(fieldName = "生效日",groups={ValidateGroupEdit.class})
	public Date getEffDate() {
		return super.getEffDate();
	}


	@NotEmpty(fieldName = "生效日截止日期",groups={ValidateGroupEdit.class})
	public Date getEffEndDate() {
		return super.getEffEndDate();
	}

	public String getStatusStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, super.getDataStatus());
	}

	public String getEffDateStr(){
		return DateUtil.format(this.getEffDate());
	}

	public String getEffEndDateStr(){
		return DateUtil.format(this.getEffEndDate());
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
