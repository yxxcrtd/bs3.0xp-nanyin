package com.joyintech.service.riskrds.limitmanagement.rskevent.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventItemRule;

public class RskEvent002Dto extends RskEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8813939139338674203L;

	/**
	 * 画面处理模式
	 */
	private String pageModel;

	/**
	 * 操作类型 - 工作流参数
	 */
	private String operateType;

	/**
	 * 画面Id - 工作流参数
	 */
	private String pageId;

	/**
	 * 按钮ID - 工作流参数
	 */
	private String elementId;

	/**
	 * 配置信息
	 */
	private List<RskEventItemRule> paramList;
	/**
	 * 风险特征组
	 */
	private List<List<RskEventItemRule>> paramGroup;

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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public List<RskEventItemRule> getParamList() {
		return paramList;
	}

	public void setParamList(List<RskEventItemRule> paramList) {
		this.paramList = paramList;
	}

	public List<List<RskEventItemRule>> getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(List<List<RskEventItemRule>> paramGroup) {
		this.paramGroup = paramGroup;
	}

}
