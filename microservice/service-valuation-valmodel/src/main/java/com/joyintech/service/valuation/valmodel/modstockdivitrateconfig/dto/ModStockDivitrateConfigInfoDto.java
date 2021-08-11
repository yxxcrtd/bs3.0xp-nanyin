package com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ModStockDivitrateConfigInfoDto extends BaseEntity{

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
	@JsonSerialize(using=BigDecimalSerializer.class)
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

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinProdName() {
		return finProdName;
	}

	public void setFinProdName(String finProdName) {
		this.finProdName = finProdName;
	}

	public String getSecTypeModel() {
		return secTypeModel;
	}

	public void setSecTypeModel(String secTypeModel) {
		this.secTypeModel = secTypeModel;
	}

	public String getSecDetailTypeModel() {
		return secDetailTypeModel;
	}

	public void setSecDetailTypeModel(String secDetailTypeModel) {
		this.secDetailTypeModel = secDetailTypeModel;
	}

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
