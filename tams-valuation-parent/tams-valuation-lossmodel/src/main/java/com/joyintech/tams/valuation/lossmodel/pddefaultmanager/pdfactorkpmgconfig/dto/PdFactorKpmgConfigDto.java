package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class PdFactorKpmgConfigDto extends BaseEntity implements ITableRow, ProcessEntity {
	
	private static final long serialVersionUID = 0L;
	
	/**
	 * 唯一标识
	 */
	private String seqno;
	/**
	 * 年份
	 */
	private String vouchYear;
	/**
	 * 综合系数
	 */
	private BigDecimal combinedFactor;
	

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
    private transient List<PdFactorKpmgConfigDto> selectedItem;
    
    
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	@PrimaryKey(Field.PK_1)
	@NotEmpty(fieldName = "PdFactorKpmgConfig001.vouchYear", groups = {ValidateGroupEdit.class})
	public String getVouchYear() {
		return vouchYear;
	}

	public void setVouchYear(String vouchYear) {
		this.vouchYear = vouchYear;
	}

	@Amount(fieldName = "PdFactorKpmgConfig001.combinedFactor", maxIntBit = 16, maxDecBit = 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorKpmgConfig001.combinedFactor", groups = {ValidateGroupEdit.class})
	public BigDecimal getCombinedFactor() {
		return combinedFactor;
	}
	public void setCombinedFactor(BigDecimal combinedFactor) {
		this.combinedFactor = combinedFactor;
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

	public List<PdFactorKpmgConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<PdFactorKpmgConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	
}
