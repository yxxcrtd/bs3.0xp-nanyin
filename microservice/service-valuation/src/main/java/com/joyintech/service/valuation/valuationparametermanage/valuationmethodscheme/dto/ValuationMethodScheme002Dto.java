/*
 * 文件名：ValuationSolutionManage002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.validator.ValuationMethodScheme002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;




/**
 * 估值方法方案
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme002Dto
 * @since 1.0
 */
@PageId("ValuationMethodScheme002")
@ValidLogic(targetClass = ValuationMethodScheme002Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValuationMethodScheme002Dto extends BaseEntity implements ProcessEntity{


    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 7954026792970307460L;

	/**
     * 方案代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotBlank(message = "方案代码不能为空！", groups= {ValidateGroupEdit.class})
    private String schemeId;
    
    /**
     * 旧方案代码
     */
    private String oldSchemeId;

    /**
     * 方案名称
     */
    @PrimaryKey(value=Field.PK_2)
    @NotBlank(message = "方案名称不能为空！", groups= {ValidateGroupEdit.class})
    private String schemeName;

    /**
     * 方案描述
     */
    private String schemeDescription;

    /**
     * 是否默认方案
     */
    @NotBlank(message = "是否默认不能为空！", groups= {ValidateGroupEdit.class})
    private String isdefault;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 处理操作
     */
    private String operateType;
    
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	public String getOldSchemeId() {
		return oldSchemeId;
	}

	public void setOldSchemeId(String oldSchemeId) {
		this.oldSchemeId = oldSchemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeDescription() {
		return schemeDescription;
	}

	public void setSchemeDescription(String schemeDescription) {
		this.schemeDescription = schemeDescription;
	}

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

	


    
	
	
}
