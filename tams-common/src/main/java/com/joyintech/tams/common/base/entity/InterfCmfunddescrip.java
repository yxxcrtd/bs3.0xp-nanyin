package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfCmfunddescrip extends InterfCmfunddescripKey {
    /**
     * 前端代码
     */
    private String fInfoFrontCode;

    /**
     * 后端代码
     */
    private String fInfoBackendCode;

    /**
     * 名称
     */
    private String fInfoFullname;

    /**
     * 简称
     */
    private String fInfoName;

    /**
     * 基金类型
     */
    private String fInfoType;

    /**
     * 基金类型2
     */
    private String fInfoType2;

    /**
     * 投资类型
     */
    private String fInfoFirstinvesttype;

    /**
     * 发行日期
     */
    private String fInfoIssuedate;

    /**
     * 成立日期
     */
    private String fInfoSetupdate;

    /**
     * 到期日期
     */
    private String fInfoMaturitydate;

    /**
     * 发行份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fIssueTotalunit;

    /**
     * 管理费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoManagementfeeratio;

    /**
     * 托管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoCustodianfeeratio;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 业绩比较基准
     */
    private String fInfoBenchmark;

    /**
     * 交易所
     */
    private String fInfoExchmarket;

    /**
     * 基金管理人ID
     */
    private String fInfoCorpFundmanagementid;

    /**
     * 托管人id
     */
    private String fInfoCustodianbankid;

    /**
     * 买入确认日
     */
    private String pconfirmspeed;

    /**
     * 卖出确认日
     */
    private String sconfirmspeed;

    /**
     * 是否属于政府投资基金
     */
    private String repisgovinvest;

    /**
     * 政府投资基金投向
     */
    private String repgovinvestfor;

    /**
     * 行业1
     */
    private String repindustry1;

    /**
     * 行业2
     */
    private String repindustry2;

    /**
     * 登记备案机构
     */
    private String repregistorg;

    /**
     * 基金投资资产
     */
    private String repfundinvestasset;

    /**
     * 基金托管机构名称
     */
    private String reptrustpartyname;

    /**
     * 交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它
     */
    private String market2;

    /**
     * 分红方式：红利再投
     */
    private String paytype;

    /**
     * 分红频率
     */
    private String paycycle;

    /**
     * 分红日
     */
    private Short dayvalue;

    /**
     * 是否日复利
     */
    private String isCompoundInt;

    // add by jiangpeng on 20200721 公募基金标的 接口 start
    /**
     * 认购起始日
     */
    private String fIssueOefStartdateinst;

    /**
     * 认购终止日
     */
    private String fIssueOefDnddateinst;
    // add by jiangpeng on 20200721 公募基金标的 接口 end

    /**
     * 取得 前端代码
     * @return 前端代码
     */
    public String getfInfoFrontCode() {
        return fInfoFrontCode;
    }

    /**
     * 设置 前端代码
     * @param fInfoFrontCode 前端代码
     */
    public void setfInfoFrontCode(String fInfoFrontCode) {
        this.fInfoFrontCode = fInfoFrontCode == null ? null : fInfoFrontCode.trim();
    }

    /**
     * 取得 后端代码
     * @return 后端代码
     */
    public String getfInfoBackendCode() {
        return fInfoBackendCode;
    }

    /**
     * 设置 后端代码
     * @param fInfoBackendCode 后端代码
     */
    public void setfInfoBackendCode(String fInfoBackendCode) {
        this.fInfoBackendCode = fInfoBackendCode == null ? null : fInfoBackendCode.trim();
    }

    /**
     * 取得 名称
     * @return 名称
     */
    public String getfInfoFullname() {
        return fInfoFullname;
    }

    /**
     * 设置 名称
     * @param fInfoFullname 名称
     */
    public void setfInfoFullname(String fInfoFullname) {
        this.fInfoFullname = fInfoFullname == null ? null : fInfoFullname.trim();
    }

    /**
     * 取得 简称
     * @return 简称
     */
    public String getfInfoName() {
        return fInfoName;
    }

    /**
     * 设置 简称
     * @param fInfoName 简称
     */
    public void setfInfoName(String fInfoName) {
        this.fInfoName = fInfoName == null ? null : fInfoName.trim();
    }

    /**
     * 取得 基金类型
     * @return 基金类型
     */
    public String getfInfoType() {
        return fInfoType;
    }

    /**
     * 设置 基金类型
     * @param fInfoType 基金类型
     */
    public void setfInfoType(String fInfoType) {
        this.fInfoType = fInfoType == null ? null : fInfoType.trim();
    }

    /**
     * 取得 基金类型2
     * @return 基金类型2
     */
    public String getfInfoType2() {
        return fInfoType2;
    }

    /**
     * 设置 基金类型2
     * @param fInfoType2 基金类型2
     */
    public void setfInfoType2(String fInfoType2) {
        this.fInfoType2 = fInfoType2 == null ? null : fInfoType2.trim();
    }

    /**
     * 取得 投资类型
     * @return 投资类型
     */
    public String getfInfoFirstinvesttype() {
        return fInfoFirstinvesttype;
    }

    /**
     * 设置 投资类型
     * @param fInfoFirstinvesttype 投资类型
     */
    public void setfInfoFirstinvesttype(String fInfoFirstinvesttype) {
        this.fInfoFirstinvesttype = fInfoFirstinvesttype == null ? null : fInfoFirstinvesttype.trim();
    }

    /**
     * 取得 发行日期
     * @return 发行日期
     */
    public String getfInfoIssuedate() {
        return fInfoIssuedate;
    }

    /**
     * 设置 发行日期
     * @param fInfoIssuedate 发行日期
     */
    public void setfInfoIssuedate(String fInfoIssuedate) {
        this.fInfoIssuedate = fInfoIssuedate == null ? null : fInfoIssuedate.trim();
    }

    /**
     * 取得 成立日期
     * @return 成立日期
     */
    public String getfInfoSetupdate() {
        return fInfoSetupdate;
    }

    /**
     * 设置 成立日期
     * @param fInfoSetupdate 成立日期
     */
    public void setfInfoSetupdate(String fInfoSetupdate) {
        this.fInfoSetupdate = fInfoSetupdate == null ? null : fInfoSetupdate.trim();
    }

    /**
     * 取得 到期日期
     * @return 到期日期
     */
    public String getfInfoMaturitydate() {
        return fInfoMaturitydate;
    }

    /**
     * 设置 到期日期
     * @param fInfoMaturitydate 到期日期
     */
    public void setfInfoMaturitydate(String fInfoMaturitydate) {
        this.fInfoMaturitydate = fInfoMaturitydate == null ? null : fInfoMaturitydate.trim();
    }

    /**
     * 取得 发行份额
     * @return 发行份额
     */
    public BigDecimal getfIssueTotalunit() {
        return fIssueTotalunit;
    }

    /**
     * 设置 发行份额
     * @param fIssueTotalunit 发行份额
     */
    public void setfIssueTotalunit(BigDecimal fIssueTotalunit) {
        this.fIssueTotalunit = fIssueTotalunit;
    }

    /**
     * 取得 管理费
     * @return 管理费
     */
    public BigDecimal getfInfoManagementfeeratio() {
        return fInfoManagementfeeratio;
    }

    /**
     * 设置 管理费
     * @param fInfoManagementfeeratio 管理费
     */
    public void setfInfoManagementfeeratio(BigDecimal fInfoManagementfeeratio) {
        this.fInfoManagementfeeratio = fInfoManagementfeeratio;
    }

    /**
     * 取得 托管费
     * @return 托管费
     */
    public BigDecimal getfInfoCustodianfeeratio() {
        return fInfoCustodianfeeratio;
    }

    /**
     * 设置 托管费
     * @param fInfoCustodianfeeratio 托管费
     */
    public void setfInfoCustodianfeeratio(BigDecimal fInfoCustodianfeeratio) {
        this.fInfoCustodianfeeratio = fInfoCustodianfeeratio;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 业绩比较基准
     * @return 业绩比较基准
     */
    public String getfInfoBenchmark() {
        return fInfoBenchmark;
    }

    /**
     * 设置 业绩比较基准
     * @param fInfoBenchmark 业绩比较基准
     */
    public void setfInfoBenchmark(String fInfoBenchmark) {
        this.fInfoBenchmark = fInfoBenchmark == null ? null : fInfoBenchmark.trim();
    }

    /**
     * 取得 交易所
     * @return 交易所
     */
    public String getfInfoExchmarket() {
        return fInfoExchmarket;
    }

    /**
     * 设置 交易所
     * @param fInfoExchmarket 交易所
     */
    public void setfInfoExchmarket(String fInfoExchmarket) {
        this.fInfoExchmarket = fInfoExchmarket == null ? null : fInfoExchmarket.trim();
    }

    /**
     * 取得 基金管理人ID
     * @return 基金管理人ID
     */
    public String getfInfoCorpFundmanagementid() {
        return fInfoCorpFundmanagementid;
    }

    /**
     * 设置 基金管理人ID
     * @param fInfoCorpFundmanagementid 基金管理人ID
     */
    public void setfInfoCorpFundmanagementid(String fInfoCorpFundmanagementid) {
        this.fInfoCorpFundmanagementid = fInfoCorpFundmanagementid == null ? null : fInfoCorpFundmanagementid.trim();
    }

    /**
     * 取得 托管人id
     * @return 托管人id
     */
    public String getfInfoCustodianbankid() {
        return fInfoCustodianbankid;
    }

    /**
     * 设置 托管人id
     * @param fInfoCustodianbankid 托管人id
     */
    public void setfInfoCustodianbankid(String fInfoCustodianbankid) {
        this.fInfoCustodianbankid = fInfoCustodianbankid == null ? null : fInfoCustodianbankid.trim();
    }

    /**
     * 取得 买入确认日
     * @return 买入确认日
     */
    public String getPconfirmspeed() {
        return pconfirmspeed;
    }

    /**
     * 设置 买入确认日
     * @param pconfirmspeed 买入确认日
     */
    public void setPconfirmspeed(String pconfirmspeed) {
        this.pconfirmspeed = pconfirmspeed == null ? null : pconfirmspeed.trim();
    }

    /**
     * 取得 卖出确认日
     * @return 卖出确认日
     */
    public String getSconfirmspeed() {
        return sconfirmspeed;
    }

    /**
     * 设置 卖出确认日
     * @param sconfirmspeed 卖出确认日
     */
    public void setSconfirmspeed(String sconfirmspeed) {
        this.sconfirmspeed = sconfirmspeed == null ? null : sconfirmspeed.trim();
    }

    /**
     * 取得 是否属于政府投资基金
     * @return 是否属于政府投资基金
     */
    public String getRepisgovinvest() {
        return repisgovinvest;
    }

    /**
     * 设置 是否属于政府投资基金
     * @param repisgovinvest 是否属于政府投资基金
     */
    public void setRepisgovinvest(String repisgovinvest) {
        this.repisgovinvest = repisgovinvest == null ? null : repisgovinvest.trim();
    }

    /**
     * 取得 政府投资基金投向
     * @return 政府投资基金投向
     */
    public String getRepgovinvestfor() {
        return repgovinvestfor;
    }

    /**
     * 设置 政府投资基金投向
     * @param repgovinvestfor 政府投资基金投向
     */
    public void setRepgovinvestfor(String repgovinvestfor) {
        this.repgovinvestfor = repgovinvestfor == null ? null : repgovinvestfor.trim();
    }

    /**
     * 取得 行业1
     * @return 行业1
     */
    public String getRepindustry1() {
        return repindustry1;
    }

    /**
     * 设置 行业1
     * @param repindustry1 行业1
     */
    public void setRepindustry1(String repindustry1) {
        this.repindustry1 = repindustry1 == null ? null : repindustry1.trim();
    }

    /**
     * 取得 行业2
     * @return 行业2
     */
    public String getRepindustry2() {
        return repindustry2;
    }

    /**
     * 设置 行业2
     * @param repindustry2 行业2
     */
    public void setRepindustry2(String repindustry2) {
        this.repindustry2 = repindustry2 == null ? null : repindustry2.trim();
    }

    /**
     * 取得 登记备案机构
     * @return 登记备案机构
     */
    public String getRepregistorg() {
        return repregistorg;
    }

    /**
     * 设置 登记备案机构
     * @param repregistorg 登记备案机构
     */
    public void setRepregistorg(String repregistorg) {
        this.repregistorg = repregistorg == null ? null : repregistorg.trim();
    }

    /**
     * 取得 基金投资资产
     * @return 基金投资资产
     */
    public String getRepfundinvestasset() {
        return repfundinvestasset;
    }

    /**
     * 设置 基金投资资产
     * @param repfundinvestasset 基金投资资产
     */
    public void setRepfundinvestasset(String repfundinvestasset) {
        this.repfundinvestasset = repfundinvestasset == null ? null : repfundinvestasset.trim();
    }

    /**
     * 取得 基金托管机构名称
     * @return 基金托管机构名称
     */
    public String getReptrustpartyname() {
        return reptrustpartyname;
    }

    /**
     * 设置 基金托管机构名称
     * @param reptrustpartyname 基金托管机构名称
     */
    public void setReptrustpartyname(String reptrustpartyname) {
        this.reptrustpartyname = reptrustpartyname == null ? null : reptrustpartyname.trim();
    }

    /**
     * 取得 交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它
     * @return 交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它
     */
    public String getMarket2() {
        return market2;
    }

    /**
     * 设置 交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它
     * @param market2 交易流通场所：01 银行间市场、02 商业银行柜台市场、03 上海交易所、04 深圳交易所、99 其它
     */
    public void setMarket2(String market2) {
        this.market2 = market2 == null ? null : market2.trim();
    }

    /**
     * 取得 分红方式：红利再投
     * @return 分红方式：红利再投
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * 设置 分红方式：红利再投
     * @param paytype 分红方式：红利再投
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    /**
     * 取得 分红频率
     * @return 分红频率
     */
    public String getPaycycle() {
        return paycycle;
    }

    /**
     * 设置 分红频率
     * @param paycycle 分红频率
     */
    public void setPaycycle(String paycycle) {
        this.paycycle = paycycle == null ? null : paycycle.trim();
    }

    /**
     * 取得 分红日
     * @return 分红日
     */
    public Short getDayvalue() {
        return dayvalue;
    }

    /**
     * 设置 分红日
     * @param dayvalue 分红日
     */
    public void setDayvalue(Short dayvalue) {
        this.dayvalue = dayvalue;
    }

    /**
     * 取得 是否日复利
     * @return 是否日复利
     */
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    /**
     * 设置 是否日复利
     * @param isCompoundInt 是否日复利
     */
    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt == null ? null : isCompoundInt.trim();
    }

  //交易所基金类型
    private String exFundType;
    
    //交易所基金简称
    private String exFundAbbrName;
    
    //申购赎回代码  
    private String purRedCode;
    
    //申购赎回简称
    private String purRedAbbrName;
    
    //单笔申购金额下限
    private BigDecimal singlePurLowerAmount;
    
    //单笔赎回份额下限
    private BigDecimal singleRedLowerShare;
    
    //ETF最小申购赎回单位
    private String purAndRedMinUnitETF;
    
    //ETF类型
    private String etfType;
    
    //分级基金
    private String graFundType;
    
    //母基金代码
    private String pFinprodId;
    
    //母基金名称
    private String pFinprodName;
    
    //计息营业日准则
    private String endTdayRule;
    
    //付息营业日准则
    private String payTdayRule;
    
    public String getfIssueOefStartdateinst() {
        return fIssueOefStartdateinst;
    }

    /**
     * 认购起始日
     */
    public void setfIssueOefStartdateinst(String fIssueOefStartdateinst) {
        this.fIssueOefStartdateinst = fIssueOefStartdateinst;
    }

    /**
     * 认购终止日
     * @return 认购终止日
     */
    public String getfIssueOefDnddateinst() {
        return fIssueOefDnddateinst;
    }

    /**
     * 认购终止日
     */
    public void setfIssueOefDnddateinst(String fIssueOefDnddateinst) {
        this.fIssueOefDnddateinst = fIssueOefDnddateinst;
    }

    // add by jiangpeng on 20200721 公募基金标的 接口 end


    /**
     * 发行机构类型（按规模划分）
     */
    private String issuerScaleType;

    /**
     * 发行机构类型（按技术领域划分）
     */
    private String issuerTechnologyType;

    /**
     * 发行机构类型（按经济领域划分）
     */
    private String issuerEconomicType;

    /**
     * 交易所基金简称
     */
    private String trmFundAbbr;

    /**
     * 交易所基金类型
     */
    private String trmFundType;

    /**
     * 申购赎回代码
     */
    private String purRedId;

    /**
     * 申购赎回简称
     */
    private String purRedAbbr;

    /**
     * 单笔申购金额下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal purStartLow;

    /**
     * 单笔赎回份额下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redStartLow;


    /**
     * ETF最小申购赎回单位
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal etfMinPrunit;

    /**
     * 基金公司名称
     */
    private String fundCompanyName;


    public String getIssuerScaleType() {
        return issuerScaleType;
    }

	public void setExFundType(String exFundType) {
		this.exFundType = exFundType;
	}

	public String getExFundAbbrName() {
		return exFundAbbrName;
	}

	public void setExFundAbbrName(String exFundAbbrName) {
		this.exFundAbbrName = exFundAbbrName;
	}

	public String getPurRedCode() {
		return purRedCode;
	}

	public void setPurRedCode(String purRedCode) {
		this.purRedCode = purRedCode;
	}

	public String getPurRedAbbrName() {
		return purRedAbbrName;
	}

	public void setPurRedAbbrName(String purRedAbbrName) {
		this.purRedAbbrName = purRedAbbrName;
	}

	public BigDecimal getSinglePurLowerAmount() {
		return singlePurLowerAmount;
	}

	public void setSinglePurLowerAmount(BigDecimal singlePurLowerAmount) {
		this.singlePurLowerAmount = singlePurLowerAmount;
	}

	public BigDecimal getSingleRedLowerShare() {
		return singleRedLowerShare;
	}

	public void setSingleRedLowerShare(BigDecimal singleRedLowerShare) {
		this.singleRedLowerShare = singleRedLowerShare;
	}

	public String getPurAndRedMinUnitETF() {
		return purAndRedMinUnitETF;
	}

	public void setPurAndRedMinUnitETF(String purAndRedMinUnitETF) {
		this.purAndRedMinUnitETF = purAndRedMinUnitETF;
	}

	public String getEtfType() {
		return etfType;
	}

	public void setEtfType(String etfType) {
		this.etfType = etfType;
	}

	public String getGraFundType() {
		return graFundType;
	}

	public void setGraFundType(String graFundType) {
		this.graFundType = graFundType;
	}

	public String getpFinprodId() {
		return pFinprodId;
	}

	public void setpFinprodId(String pFinprodId) {
		this.pFinprodId = pFinprodId;
	}

	public String getpFinprodName() {
		return pFinprodName;
	}

	public void setpFinprodName(String pFinprodName) {
		this.pFinprodName = pFinprodName;
	}

	public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}

    public String getExFundType() {
        return exFundType;
    }

    public void setIssuerScaleType(String issuerScaleType) {
        this.issuerScaleType = issuerScaleType;
    }

    public String getIssuerTechnologyType() {
        return issuerTechnologyType;
    }

    public void setIssuerTechnologyType(String issuerTechnologyType) {
        this.issuerTechnologyType = issuerTechnologyType;
    }

    public String getIssuerEconomicType() {
        return issuerEconomicType;
    }

    public void setIssuerEconomicType(String issuerEconomicType) {
        this.issuerEconomicType = issuerEconomicType;
    }

    public String getTrmFundAbbr() {
        return trmFundAbbr;
    }

    public void setTrmFundAbbr(String trmFundAbbr) {
        this.trmFundAbbr = trmFundAbbr;
    }

    public String getTrmFundType() {
        return trmFundType;
    }

    public void setTrmFundType(String trmFundType) {
        this.trmFundType = trmFundType;
    }

    public String getPurRedId() {
        return purRedId;
    }

    public void setPurRedId(String purRedId) {
        this.purRedId = purRedId;
    }

    public String getPurRedAbbr() {
        return purRedAbbr;
    }

    public void setPurRedAbbr(String purRedAbbr) {
        this.purRedAbbr = purRedAbbr;
    }

    public BigDecimal getPurStartLow() {
        return purStartLow;
    }

    public void setPurStartLow(BigDecimal purStartLow) {
        this.purStartLow = purStartLow;
    }

    public BigDecimal getRedStartLow() {
        return redStartLow;
    }

    public void setRedStartLow(BigDecimal redStartLow) {
        this.redStartLow = redStartLow;
    }

    public BigDecimal getEtfMinPrunit() {
        return etfMinPrunit;
    }

    public void setEtfMinPrunit(BigDecimal etfMinPrunit) {
        this.etfMinPrunit = etfMinPrunit;
    }

    public String getFundCompanyName() {
        return fundCompanyName;
    }

    public void setFundCompanyName(String fundCompanyName) {
        this.fundCompanyName = fundCompanyName;
    }
}