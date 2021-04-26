package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdBaseInfo extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 产品估值方案
     */
    private String prdValSche;

    /**
     * 挂钩母产品
     */
    private String pmotherPrd;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 是否核算
     */
    private String isAccount;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 是否分层核算
     */
    private String isLayerAccount;

    /**
     * 托管银行
     */
    private String truInstitut;

    /**
     * 收益标识
     */
    private String prdIncomeType;

    /**
     * 允许提前终止
     */
    private String isEndFlag;

    /**
     * 允许赎回
     */
    private String isAllowFlag;

    /**
     * 认购币种
     */
    private String ccy;

    /**
     * 默认：人民币
     */
    private String capiCcy;

    /**
     * 默认：人民币
     */
    private String profCcy;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 到期兑付日
     */
    private Date actMdate;

    /**
     * 发行期限
     */
    private String term;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 封闭式/开放式
     */
    private String issueType;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 是否分层
     */
    private String isLayer;

    /**
     * 分层类型
     */
    private String layerType;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 是否计税
     */
    private String isTax;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal proprate;

    /**
     * 浮动利率编号
     */
    private String intRateId;

    /**
     * 挂钩利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal linkRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMax;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMin;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * 利率小数位
     */
    private String yeildDecDigits;

    /**
     * 利率处理方式
     */
    private String yeildDecType;

    /**
     * null
     */
    private String isReconciliation;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称
     * @param prodAbbr 产品简称
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
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
     * 取得 产品估值方案
     * @return 产品估值方案
     */
    public String getPrdValSche() {
        return prdValSche;
    }

    /**
     * 设置 产品估值方案
     * @param prdValSche 产品估值方案
     */
    public void setPrdValSche(String prdValSche) {
        this.prdValSche = prdValSche == null ? null : prdValSche.trim();
    }

    /**
     * 取得 挂钩母产品
     * @return 挂钩母产品
     */
    public String getPmotherPrd() {
        return pmotherPrd;
    }

    /**
     * 设置 挂钩母产品
     * @param pmotherPrd 挂钩母产品
     */
    public void setPmotherPrd(String pmotherPrd) {
        this.pmotherPrd = pmotherPrd == null ? null : pmotherPrd.trim();
    }

    /**
     * 取得 投资组合代码
     * @return 投资组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 投资组合代码
     * @param portfolioId 投资组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 是否核算
     * @return 是否核算
     */
    public String getIsAccount() {
        return isAccount;
    }

    /**
     * 设置 是否核算
     * @param isAccount 是否核算
     */
    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount == null ? null : isAccount.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 是否分层核算
     * @return 是否分层核算
     */
    public String getIsLayerAccount() {
        return isLayerAccount;
    }

    /**
     * 设置 是否分层核算
     * @param isLayerAccount 是否分层核算
     */
    public void setIsLayerAccount(String isLayerAccount) {
        this.isLayerAccount = isLayerAccount == null ? null : isLayerAccount.trim();
    }

    /**
     * 取得 托管银行
     * @return 托管银行
     */
    public String getTruInstitut() {
        return truInstitut;
    }

    /**
     * 设置 托管银行
     * @param truInstitut 托管银行
     */
    public void setTruInstitut(String truInstitut) {
        this.truInstitut = truInstitut == null ? null : truInstitut.trim();
    }

    /**
     * 取得 收益标识
     * @return 收益标识
     */
    public String getPrdIncomeType() {
        return prdIncomeType;
    }

    /**
     * 设置 收益标识
     * @param prdIncomeType 收益标识
     */
    public void setPrdIncomeType(String prdIncomeType) {
        this.prdIncomeType = prdIncomeType == null ? null : prdIncomeType.trim();
    }

    /**
     * 取得 允许提前终止
     * @return 允许提前终止
     */
    public String getIsEndFlag() {
        return isEndFlag;
    }

    /**
     * 设置 允许提前终止
     * @param isEndFlag 允许提前终止
     */
    public void setIsEndFlag(String isEndFlag) {
        this.isEndFlag = isEndFlag == null ? null : isEndFlag.trim();
    }

    /**
     * 取得 允许赎回
     * @return 允许赎回
     */
    public String getIsAllowFlag() {
        return isAllowFlag;
    }

    /**
     * 设置 允许赎回
     * @param isAllowFlag 允许赎回
     */
    public void setIsAllowFlag(String isAllowFlag) {
        this.isAllowFlag = isAllowFlag == null ? null : isAllowFlag.trim();
    }

    /**
     * 取得 认购币种
     * @return 认购币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 认购币种
     * @param ccy 认购币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 默认：人民币
     * @return 默认：人民币
     */
    public String getCapiCcy() {
        return capiCcy;
    }

    /**
     * 设置 默认：人民币
     * @param capiCcy 默认：人民币
     */
    public void setCapiCcy(String capiCcy) {
        this.capiCcy = capiCcy == null ? null : capiCcy.trim();
    }

    /**
     * 取得 默认：人民币
     * @return 默认：人民币
     */
    public String getProfCcy() {
        return profCcy;
    }

    /**
     * 设置 默认：人民币
     * @param profCcy 默认：人民币
     */
    public void setProfCcy(String profCcy) {
        this.profCcy = profCcy == null ? null : profCcy.trim();
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
     * 取得 到期兑付日
     * @return 到期兑付日
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 到期兑付日
     * @param actMdate 到期兑付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 取得 发行期限
     * @return 发行期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 发行期限
     * @param term 发行期限
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
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
     * 取得 封闭式/开放式
     * @return 封闭式/开放式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 封闭式/开放式
     * @param issueType 封闭式/开放式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 是否周期型
     * @return 是否周期型
     */
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * 设置 是否周期型
     * @param isCycle 是否周期型
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle == null ? null : isCycle.trim();
    }

    /**
     * 取得 是否分层
     * @return 是否分层
     */
    public String getIsLayer() {
        return isLayer;
    }

    /**
     * 设置 是否分层
     * @param isLayer 是否分层
     */
    public void setIsLayer(String isLayer) {
        this.isLayer = isLayer == null ? null : isLayer.trim();
    }

    /**
     * 取得 分层类型
     * @return 分层类型
     */
    public String getLayerType() {
        return layerType;
    }

    /**
     * 设置 分层类型
     * @param layerType 分层类型
     */
    public void setLayerType(String layerType) {
        this.layerType = layerType == null ? null : layerType.trim();
    }

    /**
     * 取得 托管活期利息计提
     * @return 托管活期利息计提
     */
    public String getIsCalc() {
        return isCalc;
    }

    /**
     * 设置 托管活期利息计提
     * @param isCalc 托管活期利息计提
     */
    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc == null ? null : isCalc.trim();
    }

    /**
     * 取得 是否计税
     * @return 是否计税
     */
    public String getIsTax() {
        return isTax;
    }

    /**
     * 设置 是否计税
     * @param isTax 是否计税
     */
    public void setIsTax(String isTax) {
        this.isTax = isTax == null ? null : isTax.trim();
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
     * 取得 预期收益率
     * @return 预期收益率
     */
    public BigDecimal getProprate() {
        return proprate;
    }

    /**
     * 设置 预期收益率
     * @param proprate 预期收益率
     */
    public void setProprate(BigDecimal proprate) {
        this.proprate = proprate;
    }

    /**
     * 取得 浮动利率编号
     * @return 浮动利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 浮动利率编号
     * @param intRateId 浮动利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 挂钩利率
     * @return 挂钩利率
     */
    public BigDecimal getLinkRate() {
        return linkRate;
    }

    /**
     * 设置 挂钩利率
     * @param linkRate 挂钩利率
     */
    public void setLinkRate(BigDecimal linkRate) {
        this.linkRate = linkRate;
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 系数
     * @param ratio 系数
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getRateMax() {
        return rateMax;
    }

    /**
     * 设置 利率上限
     * @param rateMax 利率上限
     */
    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getRateMin() {
        return rateMin;
    }

    /**
     * 设置 利率下限
     * @param rateMin 利率下限
     */
    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    /**
     * 取得 估值表模板
     * @return 估值表模板
     */
    public String getValuationTemplate() {
        return valuationTemplate;
    }

    /**
     * 设置 估值表模板
     * @param valuationTemplate 估值表模板
     */
    public void setValuationTemplate(String valuationTemplate) {
        this.valuationTemplate = valuationTemplate == null ? null : valuationTemplate.trim();
    }

    /**
     * 取得 利率小数位
     * @return 利率小数位
     */
    public String getYeildDecDigits() {
        return yeildDecDigits;
    }

    /**
     * 设置 利率小数位
     * @param yeildDecDigits 利率小数位
     */
    public void setYeildDecDigits(String yeildDecDigits) {
        this.yeildDecDigits = yeildDecDigits == null ? null : yeildDecDigits.trim();
    }

    /**
     * 取得 利率处理方式
     * @return 利率处理方式
     */
    public String getYeildDecType() {
        return yeildDecType;
    }

    /**
     * 设置 利率处理方式
     * @param yeildDecType 利率处理方式
     */
    public void setYeildDecType(String yeildDecType) {
        this.yeildDecType = yeildDecType == null ? null : yeildDecType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsReconciliation() {
        return isReconciliation;
    }

    /**
     * 设置 null
     * @param isReconciliation null
     */
    public void setIsReconciliation(String isReconciliation) {
        this.isReconciliation = isReconciliation == null ? null : isReconciliation.trim();
    }
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}