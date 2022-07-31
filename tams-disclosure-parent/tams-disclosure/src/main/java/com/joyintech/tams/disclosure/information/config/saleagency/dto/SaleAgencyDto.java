
package com.joyintech.tams.disclosure.information.config.saleagency.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdPartner;
import com.joyintech.tams.common.base.entity.DisSaleAgency;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.saleagency.validator.SaleAgencyValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("SaleAgency002")
@ValidLogic(targetClass = SaleAgencyValidator.class, groups = {ValidateGroupEdit.class,
		ValidateGroupDelete.class})
public class SaleAgencyDto extends DisSaleAgency implements ProcessEntity{

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
	private transient List<SaleAgencyDto> selectedItem;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private String pageModel;

	/**
	 * 产品名称
	 */
	private String prodName;

	/**
	 * 产品系列
	 */
	private String prodSeries;

	/**
	 * 产品代销机构关联dto
	 */
	private List<ProdSaleAgencyDto> prodSaleAgencyDtoList;

	/**
	 * 更新时间str
	 * @return
	 */
	private String updateTimeStr;

	/**
	 * 产品系列(多个用逗号分割拼接)
	 * @return
	 */
	private String prodSeriesStr;

	/**
	 * 维护类型
	 * @return
	 */
	private String maintenanceType;

	/**
	 * 产品代码(多选)
	 * @return
	 */
	private String prodIds;

	/**
	 * 产品代码
	 */
	private String prodId;

	/**
	 * 代销机构id
	 * @return
	 */
	private String agencyIds;

	/**
	 * 选中的节点id
	 * @return
	 */
	private List<String> nodes;

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	public String getAgencyIds() {
		return agencyIds;
	}

	public void setAgencyIds(String agencyIds) {
		this.agencyIds = agencyIds;
	}

	public String getProdIds() {
		return prodIds;
	}

	public void setProdIds(String prodIds) {
		this.prodIds = prodIds;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getProdSeriesStr() {
		return prodSeriesStr;
	}

	public void setProdSeriesStr(String prodSeriesStr) {
		this.prodSeriesStr = prodSeriesStr;
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

	public List<SaleAgencyDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<SaleAgencyDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	/*@Override
	@NotEmpty(fieldName = "代销机构",groups={ValidateGroupEdit.class})
	public String getAgencyName() {
		return super.getAgencyName();
	}*/

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	/*@Override
	@NotEmpty(fieldName = "协议起始时间",groups={ValidateGroupEdit.class})
	public Date getAgreementVdate() {
		return super.getAgreementVdate();
	}

	@Override
	@NotEmpty(fieldName = "协议终止时间",groups={ValidateGroupEdit.class})
	public Date getAgreementMdate() {
		return super.getAgreementMdate();
	}*/

	public String getAgreementVdateStr(){
		return DateUtil.format(this.getAgreementVdate());
	}

	public String getAgreementMdateStr(){
		return DateUtil.format(this.getAgreementMdate());
	}

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public List<ProdSaleAgencyDto> getProdSaleAgencyDtoList() {
		return prodSaleAgencyDtoList;
	}

	public void setProdSaleAgencyDtoList(List<ProdSaleAgencyDto> prodSaleAgencyDtoList) {
		this.prodSaleAgencyDtoList = prodSaleAgencyDtoList;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
