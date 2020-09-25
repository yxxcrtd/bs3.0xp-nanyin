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

import java.util.List;

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
	
    
}
