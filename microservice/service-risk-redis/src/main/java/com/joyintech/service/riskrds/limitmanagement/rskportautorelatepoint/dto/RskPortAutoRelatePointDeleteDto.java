package com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskIsupIndicator;

public class RskPortAutoRelatePointDeleteDto extends RskIsupIndicator {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 规则编号
     */
    private String ruleId;

    /**
     * 规则编号
     */
    private String rId;

    /**
     * 规则名称
     */
    private String ruleName;

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
    private List<RskPortAutoRelatePointDeleteDto> selectedItem;

    public String getRuleId() {
        return ruleId;
    }

    @Override
    public String getIndId() {
        return super.getIndId();
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    public List<RskPortAutoRelatePointDeleteDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RskPortAutoRelatePointDeleteDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

}
