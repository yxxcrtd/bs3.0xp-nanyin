package com.joyintech.tams.valuation.common.batch.bookcommon.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokFinProductValuationPara;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPayRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashPlan;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinClawbackCalcRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardAccrsche;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardCalRule;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetPerfRewardRule;

public class BokBooksetCacheInfo implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 产品代码
     */
    private String finproductId;
    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 账套全称
     */
    private String booksetFullname;

    /**
     * 账套类别
     */
    private String booksetType;

    /**
     * 核算方式
     */
    private String acctMode;

    /**
     * 特殊核算模型
     */
    private String bokSpecMode;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 模板账套代码
     */
    private String booksetIdTmpl;

    /**
     * 会计准则
     */
    private String acctStandard;

    /**
     * 账套开始日
     */
    private Date startDate;

    /**
     * 账套结束日
     */
    private Date endDate;
    
     /**
      * 入账日
      * 
      */
    private Date   bookDate;
    
    
    /**
     * 账套日期
     * 
     */
    private Date   bookSetDate;
    
    /**
     * 发生日期
     * 
     */
    private Date   happenDate;
     
    /**
     * 所属影子定价方案- 主键
     */
    private String shadowPriceBooksetSchemeId;
    /**
     * 所属影子定价方案
     */
    private String shadowPriceSchemeId;
    
    /**
     * 所属影子定价方案类型 
     */
    private String shadowPriceSchemeType;
    
    
    /**
     * 所属税务方案- 主键
     */
    private String taxBooksetSchemeId;
    /**
     * 所属税务方案
     */
    private String taxSchemeId;
    /**
     * 所属税务方案类型 
     */
    private String taxSchemeType;
    
    
    /**
     * 所属估值方案 - 主键
     */
    private String booksetSchemeId;
    /**
     * 所属估值方案 
     */
    private String schemeId;
    
    /**
     * 所属估值方案类型 
     */
    private String schemeType;
    
      
    /**
     * 账套参数
     */
    private List<BokBooksetExt> bokBooksetExtList;
       
   
    /**
     * 含权债参数摊销设置
     */
    private List<BokFinProductValuationPara> bokFinProductValuationPara;
 
    
    
    /**
     * 标的信息
     */
    private List<TargetFinProduct> targetFinProductList;

    /**
     * 现金流计划
     */
    private List<TargetCashPlan> targetCashPlan;

    /**
     * 现金流支付规则
     */
    private List<TargetCashPayRule> targetCashPayRuleList;
    /**
     * 现金流计算规则
     */
    private List<TargetCashCalcRule> targetCashCalcRuleList;

    /**
     * 业绩报酬计提计划
     */
    private List<TargetPerfRewardAccrsche> targetPerfRewardAccrscheList;

    /**
     * 业绩报酬计提规则
     */
    private List<TargetPerfRewardRule> targetPerfRewardRuleList;
  
    /**
     * 业绩回补计算规则
     */
    private List<TargetPerfRewardCalRule> targetPerfRewardCalRuleList;
  
    /**
     * 业绩报酬计算规则
     */
    private List<TargetFinClawbackCalcRule> targetFinClawbackCalcRuleList;
  
    
    
    
    public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	/**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 账套名称
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 账套全称
     * @return 账套全称
     */
    public String getBooksetFullname() {
        return booksetFullname;
    }

    /**
     * 设置 账套全称
     * @param booksetFullname 账套全称
     */
    public void setBooksetFullname(String booksetFullname) {
        this.booksetFullname = booksetFullname == null ? null : booksetFullname.trim();
    }

    /**
     * 取得 账套类别
     * @return 账套类别
     */
    public String getBooksetType() {
        return booksetType;
    }

    /**
     * 设置 账套类别
     * @param booksetType 账套类别
     */
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType == null ? null : booksetType.trim();
    }

    /**
     * 取得 核算方式
     * @return 核算方式
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 核算方式
     * @param acctMode 核算方式
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode == null ? null : acctMode.trim();
    }

    /**
     * 取得 特殊核算模型
     * @return 特殊核算模型
     */
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    /**
     * 设置 特殊核算模型
     * @param bokSpecMode 特殊核算模型
     */
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode == null ? null : bokSpecMode.trim();
    }

    /**
     * 取得 核算主体代码
     * @return 核算主体代码
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码
     * @param acctSubjectId 核算主体代码
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId == null ? null : acctSubjectId.trim();
    }

    /**
     * 取得 核算主体类别
     * @return 核算主体类别
     */
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    /**
     * 设置 核算主体类别
     * @param acctSubjectType 核算主体类别
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType == null ? null : acctSubjectType.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy == null ? null : standardCcy.trim();
    }

    /**
     * 取得 模板账套代码
     * @return 模板账套代码
     */
    public String getBooksetIdTmpl() {
        return booksetIdTmpl;
    }

    /**
     * 设置 模板账套代码
     * @param booksetIdTmpl 模板账套代码
     */
    public void setBooksetIdTmpl(String booksetIdTmpl) {
        this.booksetIdTmpl = booksetIdTmpl == null ? null : booksetIdTmpl.trim();
    }

    /**
     * 取得 会计准则
     * @return 会计准则
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    /**
     * 设置 会计准则
     * @param acctStandard 会计准则
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard == null ? null : acctStandard.trim();
    }

    /**
     * 取得 账套开始日
     * @return 账套开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 账套开始日
     * @param startDate 账套开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 账套结束日
     * @return 账套结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 账套结束日
     * @param endDate 账套结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

	public List<TargetFinProduct> getTargetFinProductList() {
		return targetFinProductList;
	}

	public void setTargetFinProductList(List<TargetFinProduct> targetFinProductList) {
		this.targetFinProductList = targetFinProductList;
	}

	public List<TargetCashPlan> getTargetCashPlan() {
		return targetCashPlan;
	}

	public void setTargetCashPlan(List<TargetCashPlan> targetCashPlan) {
		this.targetCashPlan = targetCashPlan;
	}

	public List<TargetCashPayRule> getTargetCashPayRuleList() {
		return targetCashPayRuleList;
	}

	public void setTargetCashPayRuleList(List<TargetCashPayRule> targetCashPayRuleList) {
		this.targetCashPayRuleList = targetCashPayRuleList;
	}

	public List<TargetCashCalcRule> getTargetCashCalcRuleList() {
		return targetCashCalcRuleList;
	}

	public void setTargetCashCalcRuleList(List<TargetCashCalcRule> targetCashCalcRuleList) {
		this.targetCashCalcRuleList = targetCashCalcRuleList;
	}

	public List<TargetPerfRewardAccrsche> getTargetPerfRewardAccrscheList() {
		return targetPerfRewardAccrscheList;
	}

	public void setTargetPerfRewardAccrscheList(List<TargetPerfRewardAccrsche> targetPerfRewardAccrscheList) {
		this.targetPerfRewardAccrscheList = targetPerfRewardAccrscheList;
	}

	public List<TargetPerfRewardRule> getTargetPerfRewardRuleList() {
		return targetPerfRewardRuleList;
	}

	public void setTargetPerfRewardRuleList(List<TargetPerfRewardRule> targetPerfRewardRuleList) {
		this.targetPerfRewardRuleList = targetPerfRewardRuleList;
	}

	public List<TargetPerfRewardCalRule> getTargetPerfRewardCalRuleList() {
		return targetPerfRewardCalRuleList;
	}

	public void setTargetPerfRewardCalRuleList(List<TargetPerfRewardCalRule> targetPerfRewardCalRuleList) {
		this.targetPerfRewardCalRuleList = targetPerfRewardCalRuleList;
	}

	public List<TargetFinClawbackCalcRule> getTargetFinClawbackCalcRuleList() {
		return targetFinClawbackCalcRuleList;
	}

	public void setTargetFinClawbackCalcRuleList(List<TargetFinClawbackCalcRule> targetFinClawbackCalcRuleList) {
		this.targetFinClawbackCalcRuleList = targetFinClawbackCalcRuleList;
	}

	

	public Date getBookSetDate() {
		return bookSetDate;
	}

	public void setBookSetDate(Date bookSetDate) {
		this.bookSetDate = bookSetDate;
	}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	public String getShadowPriceBooksetSchemeId() {
		return shadowPriceBooksetSchemeId;
	}

	public void setShadowPriceBooksetSchemeId(String shadowPriceBooksetSchemeId) {
		this.shadowPriceBooksetSchemeId = shadowPriceBooksetSchemeId;
	}

	public String getShadowPriceSchemeId() {
		return shadowPriceSchemeId;
	}

	public void setShadowPriceSchemeId(String shadowPriceSchemeId) {
		this.shadowPriceSchemeId = shadowPriceSchemeId;
	}

	public String getTaxBooksetSchemeId() {
		return taxBooksetSchemeId;
	}

	public void setTaxBooksetSchemeId(String taxBooksetSchemeId) {
		this.taxBooksetSchemeId = taxBooksetSchemeId;
	}

	public String getTaxSchemeId() {
		return taxSchemeId;
	}

	public void setTaxSchemeId(String taxSchemeId) {
		this.taxSchemeId = taxSchemeId;
	}

	public String getBooksetSchemeId() {
		return booksetSchemeId;
	}

	public void setBooksetSchemeId(String booksetSchemeId) {
		this.booksetSchemeId = booksetSchemeId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getShadowPriceSchemeType() {
		return shadowPriceSchemeType;
	}

	public void setShadowPriceSchemeType(String shadowPriceSchemeType) {
		this.shadowPriceSchemeType = shadowPriceSchemeType;
	}

	public String getTaxSchemeType() {
		return taxSchemeType;
	}

	public void setTaxSchemeType(String taxSchemeType) {
		this.taxSchemeType = taxSchemeType;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public List<BokBooksetExt> getBokBooksetExtList() {
		return bokBooksetExtList;
	}

	public void setBokBooksetExtList(List<BokBooksetExt> bokBooksetExtList) {
		this.bokBooksetExtList = bokBooksetExtList;
	}

	public List<BokFinProductValuationPara> getBokFinProductValuationPara() {
		return bokFinProductValuationPara;
	}

	public void setBokFinProductValuationPara(List<BokFinProductValuationPara> bokFinProductValuationPara) {
		this.bokFinProductValuationPara = bokFinProductValuationPara;
	}

	public String getFinproductId() {
		return finproductId;
	}

	public void setFinproductId(String finproductId) {
		this.finproductId = finproductId;
	}

	
}