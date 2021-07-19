package com.joyintech.service.risk.creditmanagement.creditrulemanage.dto;

import com.joyintech.tams.common.base.entity.CreditRuleInfo;

public class CreditRuleInfoDto extends CreditRuleInfo {
	 /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 标签名称
     */
    private String creditTagName;

    /**
     * 额度分类
     */
    private String creditType;

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 标签名称
     * @return 标签名称
     */
    public String getCreditTagName() {
        return creditTagName;
    }

    /**
     * 设置 标签名称
     * @param creditTagName 标签名称
     */
    public void setCreditTagName(String creditTagName) {
        this.creditTagName = creditTagName == null ? null : creditTagName.trim();
    }

    /**
     * 取得 额度分类
     * @return 额度分类
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 额度分类
     * @param creditType 额度分类
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

	/**
     * 画面Id - 工作流参数
     */
    private String pageId;

    public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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


	/**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;


}