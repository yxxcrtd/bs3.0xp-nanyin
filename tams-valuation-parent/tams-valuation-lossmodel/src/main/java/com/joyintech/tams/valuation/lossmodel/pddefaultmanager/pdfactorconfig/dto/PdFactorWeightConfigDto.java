package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class PdFactorWeightConfigDto extends BaseEntity implements ITableRow, ProcessEntity {
	
	private static final long serialVersionUID = 0L;
	
	/**
	 * 唯一标识
	 */
	private String seqno;
	/**
	 * 乐观权重
	 */
	private BigDecimal optimisticWeight;
	/**
	 * 基础权重
	 */
	private BigDecimal basicWeight;
	/**
	 * 悲观权重
	 */
	private BigDecimal pessimismWeight;
	
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
    private transient List<PdFactorWeightConfigDto> selectedItem;
    
    @PrimaryKey(Field.PK_1)
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	
	@Amount(fieldName = "PdFactorConfig001.optimisticWeight", maxIntBit = 10, maxDecBit = 2, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.optimisticWeight", groups = {ValidateGroupEdit.class})
	public BigDecimal getOptimisticWeight() {
		return optimisticWeight;
	}

	public void setOptimisticWeight(BigDecimal optimisticWeight) {
		this.optimisticWeight = optimisticWeight;
	}
	
	
	@Amount(fieldName = "PdFactorConfig001.basicWeight", maxIntBit = 10, maxDecBit = 2, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.basicWeight", groups = {ValidateGroupEdit.class})
	public BigDecimal getBasicWeight() {
		return basicWeight;
	}

	public void setBasicWeight(BigDecimal basicWeight) {
		this.basicWeight = basicWeight;
	}
	
	@Amount(fieldName = "PdFactorConfig001.pessimismWeight", maxIntBit = 16, maxDecBit = 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.pessimismWeight", groups = {ValidateGroupEdit.class})
	public BigDecimal getPessimismWeight() {
		return pessimismWeight;
	}

	public void setPessimismWeight(BigDecimal pessimismWeight) {
		this.pessimismWeight = pessimismWeight;
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

	public List<PdFactorWeightConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<PdFactorWeightConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	
}
