package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValDlTarget extends BaseEntity {
    /**
     * 拆借标的代码
     */
    private String dlId;

    /**
     * 拆借标的名称
     */
    private String dlName;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 期限品种
     */
    private String termKind;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 资产类型
     */
    private String assetClass;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 拆借利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dlRate;

    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 到期利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mIntAmt;

    /**
     * 到期本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mPrinAmt;

    /**
     * 到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mTradeAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 核算分类
     */
    private String valClas;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

    /**
     * 取得 拆借标的代码
     * @return 拆借标的代码
     */
    public String getDlId() {
        return dlId;
    }

    /**
     * 设置 拆借标的代码
     * @param dlId 拆借标的代码
     */
    public void setDlId(String dlId) {
        this.dlId = dlId == null ? null : dlId.trim();
    }

    /**
     * 取得 拆借标的名称
     * @return 拆借标的名称
     */
    public String getDlName() {
        return dlName;
    }

    /**
     * 设置 拆借标的名称
     * @param dlName 拆借标的名称
     */
    public void setDlName(String dlName) {
        this.dlName = dlName == null ? null : dlName.trim();
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 期限品种
     * @return 期限品种
     */
    public String getTermKind() {
        return termKind;
    }

    /**
     * 设置 期限品种
     * @param termKind 期限品种
     */
    public void setTermKind(String termKind) {
        this.termKind = termKind == null ? null : termKind.trim();
    }

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
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 拆借利率
     * @return 拆借利率
     */
    public BigDecimal getDlRate() {
        return dlRate;
    }

    /**
     * 设置 拆借利率
     * @param dlRate 拆借利率
     */
    public void setDlRate(BigDecimal dlRate) {
        this.dlRate = dlRate;
    }

    /**
     * 取得 名义本金
     * @return 名义本金
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 名义本金
     * @param capital 名义本金
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 到期利息
     * @return 到期利息
     */
    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    /**
     * 设置 到期利息
     * @param mIntAmt 到期利息
     */
    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    /**
     * 取得 到期本金
     * @return 到期本金
     */
    public BigDecimal getmPrinAmt() {
        return mPrinAmt;
    }

    /**
     * 设置 到期本金
     * @param mPrinAmt 到期本金
     */
    public void setmPrinAmt(BigDecimal mPrinAmt) {
        this.mPrinAmt = mPrinAmt;
    }

    /**
     * 取得 到期金额
     * @return 到期金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期金额
     * @param mTradeAmt 到期金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
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
     * 取得 核算分类
     * @return 核算分类
     */
    public String getValClas() {
        return valClas;
    }

    /**
     * 设置 核算分类
     * @param valClas 核算分类
     */
    public void setValClas(String valClas) {
        this.valClas = valClas == null ? null : valClas.trim();
    }

    /**
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiProfFeature() {
        return capiProfFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiProfFeature 本金收益特征
     */
    public void setCapiProfFeature(String capiProfFeature) {
        this.capiProfFeature = capiProfFeature == null ? null : capiProfFeature.trim();
    }
    //add by liupengfei for 多级法人改造 S
    /**
     * 所属机构
     */
    private String belongInstitutionCode;

    /**
     * Gets the value of belongInstitutionCode.
     *
     * @return the value of belongInstitutionCode
     */
    @Override
    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    /**
     * Sets the belongInstitutionCode.
     *
     * <p>You can use getBelongInstitutionCode() to get the value of belongInstitutionCode</p>
     *
     * @param belongInstitutionCode belongInstitutionCode
     */
    @Override
    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    //add by liupengfei for 多级法人改造 E
}