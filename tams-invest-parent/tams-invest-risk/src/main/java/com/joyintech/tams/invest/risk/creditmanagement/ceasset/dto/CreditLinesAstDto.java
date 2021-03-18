/*
 * 文件名：CreditLinesDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.validator.CeAssetValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * 资产授信实体
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CreditLinesAstDto
 * @since 1.0
 */
@PageId("CeAsset002")
@ValidLogic(targetClass = CeAssetValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class CreditLinesAstDto  extends CreditLines implements ITableRow,ProcessEntity{

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
    private List<CreditLinesAstDto> selectedItem;
    
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
    
    @Override
    @PrimaryKey(value=Field.PK_1)
	public String getSeqno() {
    	return StringUtil.isNotEmpty(super.getSeqno())?super.getSeqno():StringUtil.EMPTY;
	}

    @Override
    @PrimaryKey(value=Field.PK_3)
    @Length(fieldName = "CeAsset002.pflsh", max =50, groups = {ValidateGroupEdit.class})
	@NotEmpty(fieldName = "CeAsset002.pflsh",groups={ValidateGroupEdit.class})
	public String getPflsh() {
    	return StringUtil.isNotEmpty(super.getPflsh())?super.getPflsh():StringUtil.EMPTY;
	}

    @Override
    @Length(fieldName = "CeAsset002.pflshName", max =60, groups = {ValidateGroupEdit.class})
	public String getPflshName() {
    	return StringUtil.isNotEmpty(super.getPflshName())?super.getPflshName():StringUtil.EMPTY;
	}
    
    /**
     * 资产名称
     * {@inheritDoc}
     */
    @Override
	@PrimaryKey(value=Field.PK_2)
    public String getCustomerId(){
        return super.getCustomerId();
    }

    /**
     * 生效日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CeAsset002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }

	@PrimaryKey(value=Field.PK_4)
	public String getVdateStr(){
		return DateUtil.format(super.getVdate());
	}
    /**
     * 到期日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CeAsset002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    /**
     * 是否占用机构额度
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "CeAsset002.ifHavingOrgAmt", groups = {ValidateGroupEdit.class})
    public String getCreditFlag(){
        return super.getCreditFlag();
    }
    
    /**
     * 授信额度
     * {@inheritDoc}
     */
    @Override
    @Amount(fieldName = "CeAsset002.creditTotalAmt", maxIntBit = 30, maxDecBit = 2, groups = {
            ValidateGroupEdit.class})
    @NotEmpty(fieldName = "CeAsset002.creditTotalAmt", groups = {ValidateGroupEdit.class})
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


	public List<CreditLinesAstDto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<CreditLinesAstDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	@PrimaryKey(value=Field.PK_5)
	public String getInstitutionCode() {
		return super.getInstitutionCode();
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

	/**
	 * 授信配置代码
	 * {@inheritDoc}
	 */
	@Override
	@PrimaryKey(value=Field.PK_6)
	public String getCreditMstId(){
		return super.getCreditMstId();
	}
}
