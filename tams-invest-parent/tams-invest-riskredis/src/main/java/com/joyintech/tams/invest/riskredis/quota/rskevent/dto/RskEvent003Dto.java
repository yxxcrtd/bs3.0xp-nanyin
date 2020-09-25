package com.joyintech.tams.invest.riskredis.quota.rskevent.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventParaRel;
import com.joyintech.tams.invest.riskredis.quota.rskevent.validate.RskEvent003Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("RskEvent003")
@ValidLogic(targetClass = RskEvent003Validator.class)
public class RskEvent003Dto extends RskEvent implements ProcessEntity {

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
	 * 风险事件关联指标参数
	 */
	private List<RskEventParaRel> paramList;
	
	/**
	 * 选中的风险事件列表
	 */
	private List<RskEvent003Dto> selectedItem;

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

	public List<RskEventParaRel> getParamList() {
		return paramList;
	}

	public void setParamList(List<RskEventParaRel> paramList) {
		this.paramList = paramList;
	}
	
	public List<RskEvent003Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RskEvent003Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	@PrimaryKey(value = Field.PK_1)
	public String getEventCode() {
		return super.getEventCode();
	}

}
