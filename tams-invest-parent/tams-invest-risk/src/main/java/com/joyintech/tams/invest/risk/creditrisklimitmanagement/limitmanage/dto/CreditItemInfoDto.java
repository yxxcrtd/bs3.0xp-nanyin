package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.validator.CreditItemInfoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@ValidLogic(targetClass = CreditItemInfoValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class CreditItemInfoDto extends CreditTagInfo  implements ProcessEntity{
	
	private String seqno;

	   /**
     * 额度分类
     */
    private String creditTypeText;
    
	public String getCreditTypeText() {
		return creditTypeText;
	}

	public void setCreditTypeText(String creditTypeText) {
		this.creditTypeText = creditTypeText;
	}


	
	
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
        return super.getCreditTagId();
    }

  
    /**
     * 取得 最大投资期限
     * @return 最大投资期限
     */
    @NotEmpty(fieldName = "LimitManage003.term", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "LimitManage003.creditAmt", groups = {ValidateGroupEdit.class})
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
    @NotEmpty(fieldName = "LimitManage003.mdate", groups = {ValidateGroupEdit.class})
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
    
    /**
     * 取得 机构类型
     * @return 机构类型
     */
    @NotEmpty(fieldName = "LimitManage003.orgType", groups = {ValidateGroupEdit.class})
    public String getOrgType() {
        return super.getOrgType();
    }

    
    /**
     * 取得 额度分类
     * @return 额度分类
     */
    @NotEmpty(fieldName = "LimitManage003.creditType", groups = {ValidateGroupEdit.class})
    public String getCreditType() {
        return super.getCreditType();
    }

    /**
     * 取得 风险系数（%）
     * @return 风险系数（%）
     */
    @NotEmpty(fieldName = "LimitManage003.riskRatio", groups = {ValidateGroupEdit.class})
    public BigDecimal getRiskRatio() {
        return super.getRiskRatio();
    }

 
    /**
     * 取得 期限值
     * @return 期限值
     */
    public Long getTermValue() {
        return super.getTermValue();
    }


    /**
     * 取得 期限单位
     * @return 期限单位
     */
    @NotEmpty(fieldName = "LimitManage003.termUnit", groups = {ValidateGroupEdit.class})
    public String getTermUnit() {
        return super.getTermUnit();
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
    
    /**
     * 修改时间
     */
    private String upTime;


    public String getUpTime() {
        if (getTimeUpdate() != null) {
            return DateUtil.format(getTimeUpdate(), "yyyy-MM-dd HH:mm:ss");
        }
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