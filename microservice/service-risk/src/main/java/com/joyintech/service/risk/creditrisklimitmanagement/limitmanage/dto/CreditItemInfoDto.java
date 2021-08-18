package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCode;

public class CreditItemInfoDto extends CreditTagInfo  {

	
	private String seqno;
	
	
    private String customerId;

    /**
     * 批复到期日
     */
    private Date mdate;

    
    /**
     * 授信分项代码
     */
    private String creditMstId;

    /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 最大投资期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal term;

    /**
     * 授信使用条件
     */
    private String creditCondition;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 授信分项代码
     * @return 授信分项代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信分项代码
     * @param seqno 授信分项代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditLabelSeqno 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 最大投资期限
     * @return 最大投资期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 设置 最大投资期限
     * @param maxTerm 最大投资期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 取得 授信使用条件
     * @return 授信使用条件
     */
    public String getCreditCondition() {
        return creditCondition;
    }

    /**
     * 设置 授信使用条件
     * @param creditCondition 授信使用条件
     */
    public void setCreditCondition(String creditCondition) {
        this.creditCondition = creditCondition == null ? null : creditCondition.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;
    /**
     * 取得 授信额度
     * @return 授信额度
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * 设置 授信额度
     * @param creditAmt 授信额度
     */
    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     * @param customerId 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }
    
    private String customerName;

    public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	 /**
     * 取得 批复到期日
     * @return 批复到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 批复到期日
     * @param mdate 批复到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    
    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }
    

	  /**
	  * 额度分类
	  */
    private String creditTypeText;
 
	public String getCreditTypeText() {
 	    if (StringUtil.isNotEmpty(this.getCreditType())) {
 	    	creditTypeText = SysDataUtil.getNameFromDataDict(DictCode.额度分类,this.getCreditType());
 		}
		return creditTypeText;
	}

	public void setCreditTypeText(String creditTypeText) {
		this.creditTypeText = creditTypeText;
	}
	
	   /**
     * 取得 机构类型
     * @return 机构类型
     */
    @NotEmpty(fieldName = "LimitManage003.orgType", groups = {ValidateGroupEdit.class})
    public String getOrgType() {
        return super.getOrgType();
    }
   

    /**
     * 修改时间
     */
    private String upTime;


    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
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
    
    /**
     * 叠加授信额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmtAdd;
    /**
     * 取得 叠加授信额度
     * @return 叠加授信额度
     */
    public BigDecimal getCreditAmtAdd() {
        return creditAmtAdd;
    }

    /**
     * 设置 叠加授信额度
     * @param creditAmtAdd 叠加授信额度
     */
    public void setCreditAmtAdd(BigDecimal creditAmtAdd) {
        this.creditAmtAdd = creditAmtAdd;
    }
}