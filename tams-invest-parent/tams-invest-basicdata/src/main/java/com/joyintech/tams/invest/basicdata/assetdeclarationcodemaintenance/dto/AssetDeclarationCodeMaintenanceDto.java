package com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.assetdeclarationcodemaintenance.validator.AssetDeclarationCodeMaintenanceValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("AssetDeclarationCodeMaintenance001")
@ValidLogic(targetClass = AssetDeclarationCodeMaintenanceValidator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class AssetDeclarationCodeMaintenanceDto extends BaseEntity implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型 - 工作流参数
	 */
	private String operateType;

	/**
	 * 按钮ID - 工作流参数
	 */
	private String elementId;

	/**
	 * 画面Id - 工作流参数
	 */
	private String pageId;

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

	private String othType;

	public String getOthType() {
		return othType;
	}

	public void setOthType(String othType) {
		this.othType = othType;
	}

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

	@PrimaryKey(value = Field.PK_1)
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

    @PrimaryKey(value = Field.PK_2)
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

	@Length(fieldName = "AssetDeclarationCodeMaintenance001.othCode", max = 32, groups = { ValidateGroupEdit.class })
	@NotEmpty(fieldName = "AssetDeclarationCodeMaintenance001.othCode", groups = { ValidateGroupEdit.class })
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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
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
