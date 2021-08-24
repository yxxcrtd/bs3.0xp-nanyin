package com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class ModStockDivitrateConfigInfoDto extends BaseEntity implements ProcessEntity{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String seqno;
	/**
	 * 资产代码
	 */
	private String finprodId;
	/**
	 * 资产名称
	 */
	private String finProdName;
	/**
	 * 资产类型
	 */
	private String secTypeModel;
	/**
	 * 资产细分类
	 */
	private String secDetailTypeModel;
	/**
	 * 预期股息增长率
	 */
    private BigDecimal stockIntarte;
	
	/**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 选中的数据
     */
    private transient List<ModStockDivitrateConfigInfoDto> selectedItem;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	@PrimaryKey(Field.PK_1)
	@NotEmpty(fieldName = "ModStockDivitrateConfig001.finprodId", groups = {ValidateGroupEdit.class})
	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	
	@NotEmpty(fieldName = "ModStockDivitrateConfig001.finProdName", groups = {ValidateGroupEdit.class})
	public String getFinProdName() {
		return finProdName;
	}

	public void setFinProdName(String finProdName) {
		this.finProdName = finProdName;
	}
	@PrimaryKey(Field.PK_2)
	@NotEmpty(fieldName = "ModStockDivitrateConfig001.secTypeModel", groups = {ValidateGroupEdit.class})
	public String getSecTypeModel() {
		return secTypeModel;
	}
	
	public String getSecTypeModelStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产类型, this.getSecTypeModel());
	}

	public void setSecTypeModel(String secTypeModel) {
		this.secTypeModel = secTypeModel;
	}
	
	@PrimaryKey(Field.PK_3)
	@NotEmpty(fieldName = "ModStockDivitrateConfig001.secDetailTypeModel", groups = {ValidateGroupEdit.class})
	public String getSecDetailTypeModel() {
		return secDetailTypeModel;
	}

	public void setSecDetailTypeModel(String secDetailTypeModel) {
		this.secDetailTypeModel = secDetailTypeModel;
	}
	
	
	public String getSecDetailTypeModelStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.资产分类, this.getSecDetailTypeModel());
	}

	@Amount(fieldName = "ModStockDivitrateConfig001.stockIntarte", maxIntBit = 30, maxDecBit= 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "ModStockDivitrateConfig001.stockIntarte", groups = {ValidateGroupEdit.class})
	public BigDecimal getStockIntarte() {
		return stockIntarte;
	}

	public void setStockIntarte(BigDecimal stockIntarte) {
		this.stockIntarte = stockIntarte;
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

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<ModStockDivitrateConfigInfoDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ModStockDivitrateConfigInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
    
    
    
	
	
}
