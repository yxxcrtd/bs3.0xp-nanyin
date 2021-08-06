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

//@PageId("OpenAccount002")
//@ValidLogic(targetClass = OpenAccountValidator.class, groups = {ValidateGroupEdit.class,
//    ValidateGroupDelete.class})
public class PdFactorConfigDto extends BaseEntity implements ITableRow, ProcessEntity {
	
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
	 * 季度
	 */
	private String vouchQuarter;
	
	/**
	 * 乐观因子
	 */
	private BigDecimal optimisticFactor; 
	/**
	 * 乐观权重
	 */
//	private String optimisticWeight;
	/**
	 * 基础因子
	 */
	private BigDecimal basicFactor;
	/**
	 * 基础权重
	 */
//	private String basicWeight;
	/**
	 * 悲观因子
	 */
	private BigDecimal pessimismFactor;
	/**
	 * 悲观权重
	 */
//	private String pessimismWeight;
	
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
    private transient List<PdFactorConfigDto> selectedItem;
    
    
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	@PrimaryKey(Field.PK_1)
	@NotEmpty(fieldName = "PdFactorConfig001.vouchYear", groups = {ValidateGroupEdit.class})
	public String getVouchYear() {
		return vouchYear;
	}

	public void setVouchYear(String vouchYear) {
		this.vouchYear = vouchYear;
	}
	
	@PrimaryKey(Field.PK_2)
	@NotEmpty(fieldName = "PdFactorConfig001.vouchQuarter", groups = {ValidateGroupEdit.class})
	public String getVouchQuarter() {
		return vouchQuarter;
	}
	
	public String getVouchQuarterStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.季度, this.getVouchQuarter());
    }
	
	public void setVouchQuarter(String vouchQuarter) {
		this.vouchQuarter = vouchQuarter;
	}
	
	@Amount(fieldName = "PdFactorConfig001.optimisticFactor", maxIntBit = 16, maxDecBit = 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.optimisticFactor", groups = {ValidateGroupEdit.class})
	public BigDecimal getOptimisticFactor() {
		return optimisticFactor;
	}

	public void setOptimisticFactor(BigDecimal optimisticFactor) {
		this.optimisticFactor = optimisticFactor;
	}
	
	/*
	 * @Amount(fieldName = "PdFactorConfig001.optimisticWeight", maxIntBit = 3,
	 * maxDecBit = 2, groups = {ValidateGroupEdit.class})
	 * 
	 * @NotEmpty(fieldName = "PdFactorConfig001.optimisticWeight", groups =
	 * {ValidateGroupEdit.class}) public String getOptimisticWeight() { return
	 * optimisticWeight; }
	 * 
	 * public void setOptimisticWeight(String optimisticWeight) {
	 * this.optimisticWeight = optimisticWeight; }
	 */
	
	@Amount(fieldName = "PdFactorConfig001.basicFactor", maxIntBit = 16, maxDecBit = 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.basicFactor", groups = {ValidateGroupEdit.class})
	public BigDecimal getBasicFactor() {
		return basicFactor;
	}

	public void setBasicFactor(BigDecimal basicFactor) {
		this.basicFactor = basicFactor;
	}
	
	/*
	 * @Amount(fieldName = "PdFactorConfig001.basicWeight", maxIntBit = 3, maxDecBit
	 * = 2, groups = {ValidateGroupEdit.class})
	 * 
	 * @NotEmpty(fieldName = "PdFactorConfig001.basicWeight", groups =
	 * {ValidateGroupEdit.class}) public String getBasicWeight() { return
	 * basicWeight; }
	 * 
	 * public void setBasicWeight(String basicWeight) { this.basicWeight =
	 * basicWeight; }
	 */
	
	@Amount(fieldName = "PdFactorConfig001.pessimismFactor", maxIntBit = 16, maxDecBit = 4, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "PdFactorConfig001.pessimismFactor", groups = {ValidateGroupEdit.class})
	public BigDecimal getPessimismFactor() {
		return pessimismFactor;
	}

	public void setPessimismFactor(BigDecimal pessimismFactor) {
		this.pessimismFactor = pessimismFactor;
	}
	
	/*
	 * @Amount(fieldName = "PdFactorConfig001.pessimismWeight", maxIntBit = 3,
	 * maxDecBit = 2, groups = {ValidateGroupEdit.class})
	 * 
	 * @NotEmpty(fieldName = "PdFactorConfig001.pessimismWeight", groups =
	 * {ValidateGroupEdit.class}) public String getPessimismWeight() { return
	 * pessimismWeight; }
	 * 
	 * public void setPessimismWeight(String pessimismWeight) { this.pessimismWeight
	 * = pessimismWeight; }
	 */

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

	public List<PdFactorConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<PdFactorConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	
}
