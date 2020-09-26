/*
 * 文件名：ShadowPriceScheme001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.shadowpricescheme.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class ShadowPriceScheme001Dto extends BaseEntity implements ProcessEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作类型
	 */
	private String operateType;
    
    /**
     * 方案代码
     */
	@PrimaryKey(value=Field.PK_1)
	@NotEmpty(fieldName = "ShadowPriceSchemeDetail.schemeId")
    private String schId;

    /**
     * 方案类型
     */
    private String schemeType;

    /**
     * 方案名称
     */
    private String schName;

    /**
     * 方案描述
     */
    private String remark;

    /**
     * 是否默认
     */
    private String isDefault;
    
    /**
     * 原方案Id
     */
    private String orgSchId;

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 方案代码
     * @param schId 方案代码
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 方案类型
     * @return 方案类型
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 方案类型
     * @param schemeType 方案类型
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    /**
     * 取得 方案名称
     * @return 方案名称
     */
    public String getSchName() {
        return schName;
    }

    /**
     * 设置 方案名称
     * @param schName 方案名称
     */
    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
    }

    /**
     * 取得 方案描述
     * @return 方案描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 方案描述
     * @param remark 方案描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 是否默认
     * @return 是否默认
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认
     * @param isDefault 是否默认
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}


	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

    
    /**
     * 获取 原方案Id
     * @return 原方案Id
     */
    public String getOrgSchId() {
        return orgSchId;
    }

    
    /**
     * 设置 原方案Id
     * @param orgSchId 原方案Id
     */
    public void setOrgSchId(String orgSchId) {
        this.orgSchId = orgSchId;
    }
  
}
