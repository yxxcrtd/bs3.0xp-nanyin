package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 机构授信实体
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see LimitManageInfoDto
 * @since 1.0
 */
public class LimitManageInfoDto  extends CreditLines {

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
    private List<LimitManageInfoDto> selectedItem;
    
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


	public List<LimitManageInfoDto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<LimitManageInfoDto> selectedItem) {
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
	
	private List<CreditItemInfoDto> creditLabelItem;
    
    public List<CreditItemInfoDto> getCreditLabelItem() {
		return creditLabelItem;
	}

	public void setCreditLabelItem(List<CreditItemInfoDto> creditLabelItem) {
		this.creditLabelItem = creditLabelItem;
	}
    /**
     * 修改时间
     */
  
	private Date timeUpdate;
	    
    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
    
    private String upTime;


    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
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
       	if (StringUtil.isNotEmpty(this.getCodeStatus())) {
       		codeStatusText = SysDataUtil.getNameFromDataDict(DictCode.额度管理状态,this.getCodeStatus());
    	} 
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
       	if (StringUtil.isNotEmpty(this.getCcy())) {
       		ccyText = SysDataUtil.getNameFromDataDict(DictCode.币种,this.getCcy());
    	} 
		return ccyText;
	}

	public void setCcyText(String ccyText) {
		this.ccyText = ccyText;
	}
}
