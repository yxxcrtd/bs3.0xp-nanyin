package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.controller.LimitManage001Controller;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.validator.LimitManageValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @since 1.0
 */
@PageId("LimitManage002")
@ValidLogic(targetClass = LimitManageValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class LimitManageInfo extends CreditLines implements ITableRow,ProcessEntity{

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
     * 授信品种
     */
    private List<CreditLines> creditQuoteItem;
    
    private List<CreditItemInfoDto> creditLabelItem;
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
    
    private String ccy;
    
    @NotEmpty(fieldName = "LimitManage002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public String getSeqno() {
		return StringUtil.isNotEmpty(super.getSeqno())?super.getSeqno():StringUtil.EMPTY;
	}

    @Override
    @PrimaryKey(value=Field.PK_2)
    @Length(fieldName = "LimitManage002.pflsh", max =50, groups = {ValidateGroupEdit.class})
	public String getPflsh() {
		return StringUtil.isNotEmpty(super.getPflsh())?super.getPflsh():StringUtil.EMPTY;
	}

    /**
     * 机构名称
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "LimitManage002.customerName", groups = {ValidateGroupEdit.class})
    public String getCustomerId(){
        return super.getCustomerId();
    }

    /**
     * 生效日
     * {@inheritDoc}
     */
    @Override
    public Date getVdate(){
        return super.getVdate();
    }
    /**
     * 到期日
     * {@inheritDoc}
     */
    @Override
    public Date getMdate(){
        return super.getMdate();
    }
    
    /**
     * 授信额度
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "LimitManage002.creditAmt", groups = {ValidateGroupEdit.class})
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

	public List<CreditLines> getCreditQuoteItem() {
		return creditQuoteItem;
	}

	public void setCreditQuoteItem(List<CreditLines> creditQuoteItem) {
		this.creditQuoteItem = creditQuoteItem;
	}
	
	public List<CreditItemInfoDto> getCreditLabelItem() {
		return creditLabelItem;
	}

	public void setCreditLabelItem(List<CreditItemInfoDto> creditLabelItem) {
		this.creditLabelItem = creditLabelItem;
	}
	
    
    /**
     * 选择数据
     */
    private List<LimitManageInfo> selectedItem;
    
	public List<LimitManageInfo> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<LimitManageInfo> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	 /**
     * 修改时间
     */
    public String getUpdateTimeString() {
        if (getUpdateTime() != null) {
            return DateUtil.format(getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
        }
        return StringUtil.EMPTY;
    }
    
    private Date timeUpdate;
	    
	public Date getTimeUpdate() {
	    return timeUpdate;
	}
	
	public void setTimeUpdate(Date timeUpdate) {
	    this.timeUpdate = timeUpdate;
	}
    
    private String codeStatus;
    
	public String getCodeStatus() {
	    return codeStatus;
	}
	
	public void setCodeStatus(String codeStatus) {
	    this.codeStatus = codeStatus;
	}
    
    private String codeStatusText;

    public String getCodeStatusText() {
        return codeStatusText;
    }
   
    public void setCodeStatusText(String codeStatusText) {
        this.codeStatusText = codeStatusText == null ? null : codeStatusText.trim();
    }
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal days;
    

    public BigDecimal getDays() {
        return days;
    }

    public void setDays(BigDecimal days) {
        this.days = days;
    }
    
    private String ccyText;
    
    public String getCcyText() {
		return ccyText;
	}

	public void setCcyText(String ccyText) {
		this.ccyText = ccyText;
	}

}
