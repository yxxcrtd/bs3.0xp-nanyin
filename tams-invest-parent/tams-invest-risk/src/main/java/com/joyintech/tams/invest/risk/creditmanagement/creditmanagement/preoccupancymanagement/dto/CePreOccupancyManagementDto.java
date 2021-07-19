/*
 * 文件名：CePreOccupancyManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesPre;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.validator.CePreOccupancyManagementValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年1月8日
 * @see CePreOccupancyManagementDto
 * @since 1.0
 */
@PageId("CePreOccupancyManagement002")
@ValidLogic(targetClass = CePreOccupancyManagementValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class CePreOccupancyManagementDto extends CreditLinesPre implements ITableRow,ProcessEntity{

    
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
    private List<CePreOccupancyManagementDto> selectedItem;
    
    /**
     * 授信品种
     */
    private List<CreditLinesPre> creditQuoteItem;
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
    
    private String creditLinesSeqno;
    
    
    @Override
    @PrimaryKey(value=Field.PK_1)
	public String getSeqno() {
		return StringUtil.isNotEmpty(super.getSeqno())?super.getSeqno():StringUtil.EMPTY;
	}

    /**
     * 机构名称
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CePreOccupancyManagement002.institutionName", groups = {ValidateGroupEdit.class})
    public String getCustomerId(){
        return super.getCustomerId();
    }

    /**
     * 生效日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CePreOccupancyManagement002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    /**
     * 到期日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CePreOccupancyManagement002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    /**
     * 授信额度
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CePreOccupancyManagement002.creditTotalAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getCreditAmt(){
        return super.getCreditAmt();
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


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}


	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}


	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}


	public List<CePreOccupancyManagementDto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<CePreOccupancyManagementDto> selectedItem) {
		this.selectedItem = selectedItem;
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

	public List<CreditLinesPre> getCreditQuoteItem() {
		return creditQuoteItem;
	}

	public void setCreditQuoteItem(List<CreditLinesPre> creditQuoteItem) {
		this.creditQuoteItem = creditQuoteItem;
	}

	public String getCreditLinesSeqno() {
		return creditLinesSeqno;
	}

	public void setCreditLinesSeqno(String creditLinesSeqno) {
		this.creditLinesSeqno = creditLinesSeqno;
	}
	
    
}
