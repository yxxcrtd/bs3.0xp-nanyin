package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValOptTradeSubject extends BaseEntity {
    /**
     * 期权交易标的代码
     */
    private String finprodId;

    /**
     * 期权交易标的类型
     */
    private String finprodType;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 资产类型
     */
    private String assetClass;

    /**
     * 回购利率
     */
    private Long term;

    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prin;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 外汇交易方向
     */
    private String excPs;

    /**
     * 期权费类型
     */
    private String optFeePayType;

    /**
     * 期权费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optFeeRate;

    /**
     * 期权费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optFeeAmt;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 取得 期权交易标的代码
     * @return 期权交易标的代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 期权交易标的代码
     * @param finprodId 期权交易标的代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 期权交易标的类型
     * @return 期权交易标的类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 期权交易标的类型
     * @param finprodType 期权交易标的类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 合同编号
     * @return 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 合同编号
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 合同名称
     * @return 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置 合同名称
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * 设置 资产类型
     * @param assetClass 资产类型
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass == null ? null : assetClass.trim();
    }

    /**
     * 取得 回购利率
     * @return 回购利率
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 回购利率
     * @param term 回购利率
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 名义本金
     * @return 名义本金
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 名义本金
     * @param prin 名义本金
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
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
     * 取得 外汇交易方向
     * @return 外汇交易方向
     */
    public String getExcPs() {
        return excPs;
    }

    /**
     * 设置 外汇交易方向
     * @param excPs 外汇交易方向
     */
    public void setExcPs(String excPs) {
        this.excPs = excPs == null ? null : excPs.trim();
    }

    /**
     * 取得 期权费类型
     * @return 期权费类型
     */
    public String getOptFeePayType() {
        return optFeePayType;
    }

    /**
     * 设置 期权费类型
     * @param optFeePayType 期权费类型
     */
    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType == null ? null : optFeePayType.trim();
    }

    /**
     * 取得 期权费率
     * @return 期权费率
     */
    public BigDecimal getOptFeeRate() {
        return optFeeRate;
    }

    /**
     * 设置 期权费率
     * @param optFeeRate 期权费率
     */
    public void setOptFeeRate(BigDecimal optFeeRate) {
        this.optFeeRate = optFeeRate;
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 期权费
     * @return optFeeAmt
     */
    public BigDecimal getOptFeeAmt() {
        return optFeeAmt;
    }

    /**
     * 设置 期权费
     * @param optFeeAmt 期权费
     */
    public void setOptFeeAmt(BigDecimal optFeeAmt) {
        this.optFeeAmt = optFeeAmt;
    }
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}