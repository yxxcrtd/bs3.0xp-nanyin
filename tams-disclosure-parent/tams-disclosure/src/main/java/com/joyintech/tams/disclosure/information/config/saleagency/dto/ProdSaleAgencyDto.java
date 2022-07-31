
package com.joyintech.tams.disclosure.information.config.saleagency.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdAgency;
import com.joyintech.tams.common.base.entity.DisSaleAgency;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("SaleAgency001")
public class ProdSaleAgencyDto extends DisProdAgency implements ProcessEntity{

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
	private transient List<ProdSaleAgencyDto> selectedItem;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String pageModel;

	/**
	 * 协议起始时间
	 */
	private Date agreementVdate;

	/**
	 * 协议终止时间
	 */
	private Date agreementMdate;

	/**
	 * 更新时间str
	 * @return
	 */
	private String updateTimeStr;

	/**
	 * 产品名称
	 * @return
	 */
	private String prodName;

	/**
	 * 产品系列
	 * @return
	 */
	private String prodSeries;

	/**
	 * 代销机构名称
	 * @return
	 */
	private String agencyName;

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
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

	public List<ProdSaleAgencyDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ProdSaleAgencyDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	/**
	 * 取得 协议起始时间
	 * @return 协议起始时间
	 */
	public Date getAgreementVdate() {
		return agreementVdate;
	}

	/**
	 * 设置 协议起始时间
	 * @param agreementVdate 协议起始时间
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setAgreementVdate(Date agreementVdate) {
		this.agreementVdate = agreementVdate;
	}

	/**
	 * 取得 协议终止时间
	 * @return 协议终止时间
	 */
	public Date getAgreementMdate() {
		return agreementMdate;
	}

	/**
	 * 设置 协议终止时间
	 * @param agreementMdate 协议终止时间
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setAgreementMdate(Date agreementMdate) {
		this.agreementMdate = agreementMdate;
	}

	public String getAgreementVdateStr(){
		return DateUtil.format(this.getAgreementVdate());
	}

	public String getAgreementMdateStr(){
		return DateUtil.format(this.getAgreementMdate());
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
