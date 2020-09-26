/*
 * 文件名：TaxScheme004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.validator.TaxScheme004DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 附加税则表
 * @author zhangwei
 * @version 1.0 2018年10月17日
 * @see TaxScheme004Dto
 * @since 1.0
 */
@PageId("TaxScheme004")
@ValidLogic(targetClass = TaxScheme004DtoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class TaxScheme004Dto extends BaseEntity implements ProcessEntity {

	/**
     * 税则代码
     */
    @PrimaryKey(value=Field.PK_1)
    private String taxId;

    /**
     * 方案代码
     */
    @PrimaryKey(value=Field.PK_2)
    private String schId;

    /**
     * 附加税类型
     */
    @PrimaryKey(value=Field.PK_3)
    @NotBlank(message="附加税类型不能为空!", groups= {ValidateGroupEdit.class})
    private String addTaxType;

    /**
     * 应税税率
     */
    @NotNull(message="应税税率不能为空!", groups= {ValidateGroupEdit.class})
    @Max(value=100, message="应税税率不能大于100%！", groups= {ValidateGroupEdit.class})
    @Min(value=0, message="应税税率不能小于0%！", groups= {ValidateGroupEdit.class})
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    @PrimaryKey(value=Field.PK_4)
    @NotBlank(message="生效日不能为空!", groups= {ValidateGroupEdit.class})
    private String effDate;

    /**
     * 通道协议代码
     */
    private String chlCpctId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 处理操作
     */
    private String operateType;
    
	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getSchId() {
		return schId;
	}

	public void setSchId(String schId) {
		this.schId = schId;
	}

	public String getAddTaxType() {
		return addTaxType;
	}

	public void setAddTaxType(String addTaxType) {
		this.addTaxType = addTaxType;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public String getChlCpctId() {
		return chlCpctId;
	}

	public void setChlCpctId(String chlCpctId) {
		this.chlCpctId = chlCpctId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    
    
	
}
