package com.joyintech.service.risk.creditmanagement.creditlinemanage.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCode;

public class CreditTagInfoDto extends CreditTagInfo {
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

    /**
     * 授信占用方式
     */
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
     * 期限值
     */
    private Long termValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 期限单位
     */
    private String termUnit;

 
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
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
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
     * 取得 期限值
     * @return 期限值
     */
    public Long getTermValue() {
        return termValue;
    }

    /**
     * 设置 期限值
     * @param termValue 期限值
     */
    public void setTermValue(Long termValue) {
        this.termValue = termValue;
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
     * 取得 期限单位
     * @return 期限单位
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * 设置 期限单位
     * @param termUnit 期限单位
     */
    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit == null ? null : termUnit.trim();
    }
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
    
    
    
    
    private String deductRuleText;
    
 	public String getDeductRuleText() {
 	    if (StringUtil.isNotEmpty(this.getDeductRule())) {
 	    	deductRuleText = SysDataUtil.getNameFromDataDict(DictCode.扣减规则,this.getDeductRule());
 		}
 		return deductRuleText;
 	}

 	public void setDeductRuleText(String deductRuleText) {
 		this.deductRuleText = deductRuleText;
 	}
 	
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
 	
    private String issueTypeText;

    public String getIssueTypeText() {   
        if (StringUtil.isNotEmpty(this.getIssueType())) {
        	issueTypeText = SysDataUtil.getNameFromDataDict(DictCode.发行方式,this.getIssueType());
    	} 
        return issueTypeText;
    }
 
    public void setIssueTypeText(String issueTypeText) {
        this.issueTypeText = issueTypeText == null ? null : issueTypeText.trim();
    }

  
    private String isFxText;

    public String getIsFxText() {     	   
        if (StringUtil.isNotEmpty(this.getIsFx())) {
        	isFxText = SysDataUtil.getNameFromDataDict(DictCode.是否,this.getIsFx());
    	}         
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
        if (StringUtil.isNotEmpty(this.getCurPair())) {
        	curPairText = SysDataUtil.getNameFromDataDict(DictCode.货币对,this.getCurPair());
		}        	
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
    	if (StringUtil.isNotEmpty(this.getTermUnit())) {
    		termUnitText = SysDataUtil.getNameFromDataDict(DictCode.期限种类,this.getTermUnit());
    	} 
        return termUnitText;
    }

    public void setTermUnitText(String termUnitText) {
        this.termUnitText = termUnitText == null ? null : termUnitText.trim();
    }
    
    private String isPerpetualSecText;

    public String getIsPerpetualSecText() {  
    	if (StringUtil.isNotEmpty(this.getIssueType())) {
    		isPerpetualSecText = SysDataUtil.getNameFromDataDict(DictCode.是否,this.getIsPerpetualSec());
    	} 
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
       	if (StringUtil.isNotEmpty(this.getCreditUsingType())) {
       		creditUsingTypeText = SysDataUtil.getNameFromDataDict(DictCode.授信占用方式,this.getCreditUsingType());
    	} 
        return creditUsingTypeText;
    }

    /**
     * 设置 授信占用方式
     * @param creditUsingType 授信占用方式
     */
    public void setCreditUsingTypeText(String creditUsingTypeText) {
        this.creditUsingTypeText = creditUsingTypeText == null ? null : creditUsingTypeText.trim();
    }
}