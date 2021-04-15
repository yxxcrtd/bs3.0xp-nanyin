package com.joyintech.service.basicdata.assetdeclarationcodemaintenance.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

public class AssetDeclarationCodeMaintenanceDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 金融产品代码
	 */
	private String finprodId;
	
	// add by dubaojun 2020-07-30 画面资产代码展示为市场代码 start
	/**
	 * 金融产品市场代码
	 */
	private String finprodMarketId;
	// add by dubaojun 2020-07-30 画面资产代码展示为市场代码 end


	private String finprodName;

	private String vdateStart;

	private String vdateEnd;

	private String assetsPrice;

	public String getAssetsPrice() {
		return assetsPrice;
	}

	public void setAssetsPrice(String assetsPrice) {
		this.assetsPrice = assetsPrice;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getVdateStart() {
		return vdateStart;
	}

	public void setVdateStart(String vdateStart) {
		this.vdateStart = vdateStart;
	}

	public String getVdateEnd() {
		return vdateEnd;
	}

	public void setVdateEnd(String vdateEnd) {
		this.vdateEnd = vdateEnd;
	}

	/**
	 * 分支序号
	 */
	private String branch;

	/**
	 * 资产类型
	 */
	private String assetsType;
	
	/**
	 * 资产类型名称
	 */
	private String assetsTypeName;
	/**
	 * 其他代码类型，回售代码、质押代码、可转债信用申购代码、可转债转股代码、配债代码、配股代码等等
	 */
	private String othCodeType;

	/**
	 * 其他代码类型，回售代码、质押代码、可转债信用申购代码、可转债转股代码、配债代码、配股代码等等
	 */
	private String othCodeTypeName;
	/**
	 * 其他代码
	 */
	private String othCode;

	/**
	 * 备注
	 */
	private String remark;
	
    /**
     * 选中的数据
     */
    private transient List<AssetDeclarationCodeMaintenanceDto> selectedItem;

    
	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId String
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getOthCodeType() {
		return othCodeType;
	}

	public void setOthCodeType(String othCodeType) {
		this.othCodeType = othCodeType;
	}

	public String getOthCode() {
		return othCode;
	}

	public void setOthCode(String othCode) {
		this.othCode = othCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	public String getAssetsTypeName() {
		return assetsTypeName;
	}

	public void setAssetsTypeName(String assetsTypeName) {
		this.assetsTypeName = assetsTypeName;
	}

	public String getOthCodeTypeName() {
		return othCodeTypeName;
	}

	public void setOthCodeTypeName(String othCodeTypeName) {
		this.othCodeTypeName = othCodeTypeName;
	}

	public List<AssetDeclarationCodeMaintenanceDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<AssetDeclarationCodeMaintenanceDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

}
