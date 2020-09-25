package com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskRuleInfo;


public class RskPortAutoRelatePoint002Dto extends RskRuleInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * 分类维度
     */
    private String classifyDimension;
    /**
     * 分类维度值,多个用逗号分隔
     */
    private String classifyDimensionVal;

    /**
     * 连接符
     */
    private String linkSign;

    /**
     * 参数序号
     */
    private Long paramNum;
    
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面参数
     */
    private String pageModel;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 选择数据
     */
    private List<RskPortAutoRelatePoint002Dto> selectedItem;

	public String getRuleId() {
		return super.getRuleId();
	}
	public String getRuleName() {
		return super.getRuleName();
	}

	
	public String getClassifyDimension() {
		return classifyDimension;
	}

	public void setClassifyDimension(String classifyDimension) {
		this.classifyDimension = classifyDimension;
	}

	public String getClassifyDimensionVal() {
		return classifyDimensionVal;
	}

	public void setClassifyDimensionVal(String classifyDimensionVal) {
		this.classifyDimensionVal = classifyDimensionVal;
	}

	public String getLinkSign() {
		return linkSign;
	}

	public void setLinkSign(String linkSign) {
		this.linkSign = linkSign;
	}

	public Long getParamNum() {
		return paramNum;
	}

	public void setParamNum(Long paramNum) {
		this.paramNum = paramNum;
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
	public List<RskPortAutoRelatePoint002Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RskPortAutoRelatePoint002Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
    
	

}
