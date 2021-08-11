package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * 项目额度管理
 * @author chenlei1
 * @version 1.0 2020年03月05日
 * @see ObjectLimitManageInfoDto
 * @since 1.0
 */
public class ObjectLimitManageInfoDto  extends CreditLines {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
	/**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 标签名称
     */
    private String creditTagName;


    /**
     * 风险系数%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal riskRatio;

    /**
     * 授信占用方式
     */
    private String creditUsingType;

    
    private String creditUsingTypeText;


    /**
     * 额度分类
     */
    private String creditType;

    /**
     * 扣减规则
     */
    private String deductRule;

    /**
     * 是否永续债
     */
    private String isPerpetualSec;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 是否外汇
     */
    private String isFx;

    /**
     * 货币对
     */
    private String curPair;

    /**
     * 备注
     */
    private String remark;


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


	public String getPageId() {
		return pageId;
	}


	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
	
    
    private String seqno;
    
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}


	public String getSeqno() {
	   return this.seqno;
	}
   /**
	* 选中列表
	*/
	 private List<ObjectLimitManageInfoDto> selectedItem;
	
	 public List<ObjectLimitManageInfoDto> getSelectedItem() {
		return selectedItem;
	 }

	 public void setSelectedItem(List<ObjectLimitManageInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
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




    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param seqno 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 标签名称
     * @return 标签名称
     */
    public String getCreditTagName() {
        return creditTagName;
    }

    /**
     * 设置 标签名称
     * @param creditTagName 标签名称
     */
    public void setCreditTagName(String creditTagName) {
        this.creditTagName = creditTagName == null ? null : creditTagName.trim();
    }


    /**
     * 取得 风险系数%
     * @return 风险系数%
     */
    public BigDecimal getRiskRatio() {
        return riskRatio;
    }

    /**
     * 设置 风险系数%
     * @param riskRatio 风险系数%
     */
    public void setRiskRatio(BigDecimal riskRatio) {
        this.riskRatio = riskRatio;
    }

    /**
     * 取得 授信占用方式
     * @return 授信占用方式
     */
    public String getCreditUsingType() {
        return creditUsingType;
    }

    /**
     * 设置 授信占用方式
     * @param creditUsingType 授信占用方式
     */
    public void setCreditUsingType(String creditUsingType) {
        this.creditUsingType = creditUsingType == null ? null : creditUsingType.trim();
    }

    /**
     * 取得 额度分类
     * @return 额度分类
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 额度分类
     * @param creditType 额度分类
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 扣减规则
     * @return 扣减规则
     */
    public String getDeductRule() {
        return deductRule;
    }

    /**
     * 设置 扣减规则
     * @param deductRule 扣减规则
     */
    public void setDeductRule(String deductRule) {
        this.deductRule = deductRule == null ? null : deductRule.trim();
    }

    /**
     * 取得 是否永续债
     * @return 是否永续债
     */
    public String getIsPerpetualSec() {
        return isPerpetualSec;
    }

    /**
     * 设置 是否永续债
     * @param isPerpetualSec 是否永续债
     */
    public void setIsPerpetualSec(String isPerpetualSec) {
        this.isPerpetualSec = isPerpetualSec == null ? null : isPerpetualSec.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 发行方式
     * @param issueType 发行方式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }


    /**
     * 取得 是否外汇
     * @return 是否外汇
     */
    public String getIsFx() {
        return isFx;
    }

    /**
     * 设置 是否外汇
     * @param isFx 是否外汇
     */
    public void setIsFx(String isFx) {
        this.isFx = isFx == null ? null : isFx.trim();
    }

    /**
     * 取得 货币对
     * @return 货币对
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 货币对
     * @param curPair 货币对
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair == null ? null : curPair.trim();
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

    /**
     * 期限值
     */
    

    public BigDecimal getTerm() {
		return super.getTerm();
	}

	

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	/**
     * 期限单位
     */
    private String termUnit;
    
    
    
    
    private String deductRuleText;
    
 	public String getDeductRuleText() {
 	
 		return deductRuleText;
 	}

 	public void setDeductRuleText(String deductRuleText) {
 		this.deductRuleText = deductRuleText;
 	}
 	
    private String creditTypeText;
    
 	public String getCreditTypeText() {	 	
 
 		return creditTypeText;
 	}

 	public void setCreditTypeText(String creditTypeText) {
 		this.creditTypeText = creditTypeText;
 	}
 	
    private String issueTypeText;

    public String getIssueTypeText() {   
   
        return issueTypeText;
    }
 
    public void setIssueTypeText(String issueTypeText) {
        this.issueTypeText = issueTypeText == null ? null : issueTypeText.trim();
    }

  
    private String isFxText;

    public String getIsFxText() {     	   
            
        return isFxText;
    }

    public void setIsFxText(String isFxText) {
        this.isFxText = isFxText == null ? null : isFxText.trim();
    }

    /**
     * 取得 货币对
     * @return 货币对
     */
    private String curPairText;

    public String getCurPairText() {
           	
        return curPairText;
    }
    public void setCurPairText(String curPairText) {
        this.curPairText = curPairText == null ? null : curPairText.trim();
    }

    /**
     * 取得 期限单位
     * @return 期限单位
     */
    private String termUnitText;

    public String getTermUnitText() {  
    
        return termUnitText;
    }

    public void setTermUnitText(String termUnitText) {
        this.termUnitText = termUnitText == null ? null : termUnitText.trim();
    }
    
    private String isPerpetualSecText;

    public String getIsPerpetualSecText() {  
    	
        return isPerpetualSecText;
    }

    public void setIsPerpetualSecText(String isPerpetualSecText) {
        this.isPerpetualSecText = isPerpetualSecText == null ? null : isPerpetualSecText.trim();
    }
    
    /**
     * 取得 授信占用方式
     * @return 授信占用方式
     */
    public String getCreditUsingTypeText() {
        return creditUsingTypeText;
    }

    /**
     * 设置 授信占用方式
     * @param creditUsingType 授信占用方式
     */
    public void setCreditUsingTypeText(String creditUsingTypeText) {
        this.creditUsingTypeText = creditUsingTypeText == null ? null : creditUsingTypeText.trim();
    }
    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务类型
     */
    private String busiTypeText;
    /**
     * 审批编号
     */
    private String approId;

    /**
     * 机构类型
     */
    private String orgType;


    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiTypeText(String busiTypeText) {
        this.busiTypeText = busiTypeText == null ? null : busiTypeText.trim();
    }

    public String getBusiTypeText() {
       	if (StringUtil.isNotEmpty(this.getBusiType())) {
       		busiTypeText = SysDataUtil.getNameFromDataDict(DictCode.资产五级分类,this.getBusiType());
    	} 
        return busiTypeText;
    }

    
    /**
     * 取得 审批编号
     * @return 审批编号
     */
    public String getApproId() {
        return approId;
    }

    /**
     * 设置 审批编号
     * @param approId 审批编号
     */
    public void setApproId(String approId) {
        this.approId = approId == null ? null : approId.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置 机构类型
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }
    /**
     * 画面Id - 机构名称
     */
    private String customerName;
    

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
        this.codeStatusText = codeStatusText;
    }
    
    
    /**
     * 期限值
     */
    private BigDecimal endTermValue;

    /**
     * 期限单位
     */
    private String endTermUnit;
    
    /**
     * 取得 期限值
     * @return 期限值
     */
    public BigDecimal getEndTermValue() {
        return endTermValue;
    }

    /**
     * 设置 期限值
     * @param endTermValue 期限值
     */
    public void setEndTermValue(BigDecimal endTermValue) {
        this.endTermValue = endTermValue;
    }

    /**
     * 取得 期限单位
     * @return 期限单位
     */
    public String getEndTermUnit() {
        return endTermUnit;
    }

    /**
     * 设置 期限单位
     * @param termUnit 期限单位
     */
    public void setEndTermUnit(String endTermUnit) {
        this.endTermUnit = endTermUnit == null ? null : endTermUnit.trim();
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
}
