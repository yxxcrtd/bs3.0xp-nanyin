/*
 * 文件名：CreditLinesDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.creditmanagement.ceorg.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.framework.base.dto.UploadFile;

/**
 * 
 * 机构授信实体
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CreditLinesOrgDto
 * @since 1.0
 */
public class CreditLinesOrgDto  extends CreditLines {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
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
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    
    /**
     * 选择数据
     */
    private List<CreditLinesOrgDto> selectedItem;
    
    /**
     * 授信品种
     */
    private List<CreditLines> creditQuoteItem;
    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;
    
    /**
     * 画面Id - 组合机构代码
     */
    private String insitituteCode;
    /**
     * 画面Id - 机构名称
     */
    private String customerName;

    /*private String holdMaturity;

	@Override
	public String getHoldMaturity() {
		return holdMaturity;
	}

	@Override
	public void setHoldMaturity(String holdMaturity) {
		this.holdMaturity = holdMaturity;
	}*/

	// add by yushilong 非法非法人主体授信额度（亿元）字段新增 20200629 start
	/**
	 * 画面Id - 非法人主体授信额度（亿元）
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal nlpscreditline;

	public BigDecimal getNlpscreditline() {
		return nlpscreditline;
	}


	public void setNlpscreditline(BigDecimal nlpscreditline) {
		this.nlpscreditline = nlpscreditline;
	}
	// add by yushilong 非法非法人主体授信额度（亿元）字段新增 20200629 end

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


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


	public String getInsitituteCode() {
		return insitituteCode;
	}


	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public List<CreditLinesOrgDto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<CreditLinesOrgDto> selectedItem) {
		this.selectedItem = selectedItem;
	}


	public List<CreditLines> getCreditQuoteItem() {
		return creditQuoteItem;
	}


	public void setCreditQuoteItem(List<CreditLines> creditQuoteItem) {
		this.creditQuoteItem = creditQuoteItem;
	}


	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}


	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
	
	private List<CreditConfInfoDto> creditLabelItem;
    
    public List<CreditConfInfoDto> getCreditLabelItem() {
		return creditLabelItem;
	}

	public void setCreditLabelItem(List<CreditConfInfoDto> creditLabelItem) {
		this.creditLabelItem = creditLabelItem;
	}

	// delete by yushilong 风险授信查询结果内容修改 20200629 start
	/**
     * 是否集团客户
     * @param creditCondition 授信使用条件
     *//*
    private String isGroupCustomer;
    
    public String getIsGroupCustomer() {
		return isGroupCustomer;
	}

	public void setIsGroupCustomer(String isGroupCustomer) {
		this.isGroupCustomer = isGroupCustomer;
	}
    *//**
     * 所属集团客户名称
     * @param creditCondition 授信使用条件
     *//*

	private String parentCustomerName;

	public String getParentCustomerName() {
		return parentCustomerName;
	}

	public void setParentCustomerName(String parentCustomerName) {
		this.parentCustomerName = parentCustomerName;
	}*/
	// delete by yushilong 风险授信查询结果内容修改 20200629 end

}
