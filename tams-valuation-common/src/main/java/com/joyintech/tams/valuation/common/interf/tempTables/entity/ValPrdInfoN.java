package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdInfoN extends BaseEntity {
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
     * 托管银行
     */
    private String truInstitut;

    /**
     * 收益标识
     */
    private String prdIncomeType;

    /**
     * 允许提前终止，是/否
     */
    private String isEndFlag;

    /**
     * 认购币种
     */
    private String ccy;

    /**
     * 默认：人民币(资管无，目前无外币理财)
     */
    private String capitalCcy;

    /**
     * 默认：人民币
     */
    private String profitCcy;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 映射开放形式
     */
    private String issueType;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 是否计税
     */
    private String isTax;

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 挂钩母产品
     */
    private String pmotherPrd;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 是否核算
     */
    private String isAccount;

    /**
     * 是否分层核算
     */
    private String isLayerAccount;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * null
     */
    private String isReconciliation;

    /**
     * 份额小数位
     */
    private String shareDecDigits;

    /**
     * 单位净值小数位
     */
    private String valDecDigits;

    /**
     * 份额尾数处理
     */
    private String shareDecType;

    /**
     * 单位净值尾数处理
     */
    private String valDecType;


    /**
     * 产品费后估值小数位数_估值表展示
     */
    private String showAfterDecDigits;

    /**
     * 浮动管理费_期初单位净值尾数处理方式

     */
    private String floatStarttotalUnitnetType;

    /**
     * 浮动管理费_期初单位净值小数位数

     */
    private String floatStarttotalUnitnetDigits;

    /**
     * 浮动管理费_期初累计净值尾数处理方式

     */
    private String floatStarttotalNetType;

    /**
     * 浮动管理费_期初累计净值小数位数

     */
    private String floatStarttotalNetDigits;

    /**
     * 浮动管理费_期末累计净值尾数处理方式

     */
    private String floatEndtotalNetType;

    /**
     * 浮动管理费_期末累计净值小数位数

     */
    private String floatEndtotalNetDigits;

    /**
     * 浮动管理费_周期年化收益率位数处理方式

     */
    private String floatCyclyearYieldType;

    /**
     * 浮动管理费_周期年化收益率小数位数

     */
    private String floatCyclyearYieldDigits;

    /**
     * 周期年化收益率小数尾数处理方式_估值表展示

     */
    private String showCyclyearYieldType;

    /**
     * 周期年化收益率小数位数_估值表展示

     */
    private String showCyclyearYieldDigits;

    /**
     * 产品费前估值小数尾数处理方式_估值表展示

     */
    private String showBeforeDecType;

    /**
     * 产品费前估值小数位数_估值表展示
     */
    private String showBeforeDecDigits;

    /**
     * 产品费后估值小数尾数处理方式_估值表展示
     */
    private String showAfterDecType;

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
     * 取得 允许提前终止，是/否
     * @return 允许提前终止，是/否
     */
    public String getIsEndFlag() {
        return isEndFlag;
    }

    /**
     * 设置 允许提前终止，是/否
     * @param isEndFlag 允许提前终止，是/否
     */
    public void setIsEndFlag(String isEndFlag) {
        this.isEndFlag = isEndFlag == null ? null : isEndFlag.trim();
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
     * 取得 默认：人民币(资管无，目前无外币理财)
     * @return 默认：人民币(资管无，目前无外币理财)
     */
    public String getCapitalCcy() {
        return capitalCcy;
    }

    /**
     * 设置 默认：人民币(资管无，目前无外币理财)
     * @param capitalCcy 默认：人民币(资管无，目前无外币理财)
     */
    public void setCapitalCcy(String capitalCcy) {
        this.capitalCcy = capitalCcy == null ? null : capitalCcy.trim();
    }

    /**
     * 取得 默认：人民币
     * @return 默认：人民币
     */
    public String getProfitCcy() {
        return profitCcy;
    }

    /**
     * 设置 默认：人民币
     * @param profitCcy 默认：人民币
     */
    public void setProfitCcy(String profitCcy) {
        this.profitCcy = profitCcy == null ? null : profitCcy.trim();
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
     * 取得 实际到期日
     * @return 实际到期日
     */
    public Date getActmdate() {
        return actmdate;
    }

    /**
     * 设置 实际到期日
     * @param actmdate 实际到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
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
     * 取得 映射开放形式
     * @return 映射开放形式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 映射开放形式
     * @param issueType 映射开放形式
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
     * 取得 份额小数位
     * @return 份额小数位
     */
    public String getShareDecDigits() {
        return shareDecDigits;
    }

    /**
     * 设置 份额小数位
     * @param shareDecDigits 份额小数位
     */
    public void setShareDecDigits(String shareDecDigits) {
        this.shareDecDigits = shareDecDigits == null ? null : shareDecDigits.trim();
    }

    /**
     * 取得 单位净值小数位
     * @return 单位净值小数位
     */
    public String getValDecDigits() {
        return valDecDigits;
    }

    /**
     * 设置 单位净值小数位
     * @param valDecDigits 单位净值小数位
     */
    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits == null ? null : valDecDigits.trim();
    }

    /**
     * 取得 份额尾数处理
     * @return 份额尾数处理
     */
    public String getShareDecType() {
        return shareDecType;
    }

    /**
     * 设置 份额尾数处理
     * @param shareDecType 份额尾数处理
     */
    public void setShareDecType(String shareDecType) {
        this.shareDecType = shareDecType == null ? null : shareDecType.trim();
    }

    /**
     * 取得 单位净值尾数处理
     * @return 单位净值尾数处理
     */
    public String getValDecType() {
        return valDecType;
    }

    /**
     * 设置 单位净值尾数处理
     * @param valDecType 单位净值尾数处理
     */
    public void setValDecType(String valDecType) {
        this.valDecType = valDecType == null ? null : valDecType.trim();
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

    /**
     * 取得 产品费后估值小数位数_估值表展示
     * @return 产品费后估值小数位数_估值表展示
     */
    public String getShowAfterDecDigits() {
        return showAfterDecDigits;
    }

    /**
     * 设置 产品费后估值小数位数_估值表展示
     * @param showAfterDecDigits 产品费后估值小数位数_估值表展示
     */
    public void setShowAfterDecDigits(String showAfterDecDigits) {
        this.showAfterDecDigits = showAfterDecDigits == null ? null : showAfterDecDigits.trim();
    }

    /**
     * 取得 浮动管理费_期初单位净值尾数处理方式

     * @return 浮动管理费_期初单位净值尾数处理方式

     */
    public String getFloatStarttotalUnitnetType() {
        return floatStarttotalUnitnetType;
    }

    /**
     * 设置 浮动管理费_期初单位净值尾数处理方式

     * @param floatStarttotalUnitnetType 浮动管理费_期初单位净值尾数处理方式

     */
    public void setFloatStarttotalUnitnetType(String floatStarttotalUnitnetType) {
        this.floatStarttotalUnitnetType = floatStarttotalUnitnetType == null ? null : floatStarttotalUnitnetType.trim();
    }

    /**
     * 取得 浮动管理费_期初单位净值小数位数

     * @return 浮动管理费_期初单位净值小数位数

     */
    public String getFloatStarttotalUnitnetDigit() {
        return floatStarttotalUnitnetDigits;
    }

    /**
     * 设置 浮动管理费_期初单位净值小数位数

     * @param floatStarttotalUnitnetDigit 浮动管理费_期初单位净值小数位数

     */
    public void setFloatStarttotalUnitnetDigit(String floatStarttotalUnitnetDigits) {
        this.floatStarttotalUnitnetDigits = floatStarttotalUnitnetDigits == null ? null : floatStarttotalUnitnetDigits.trim();
    }

    /**
     * 取得 浮动管理费_期初累计净值尾数处理方式

     * @return 浮动管理费_期初累计净值尾数处理方式

     */
    public String getFloatStarttotalNetType() {
        return floatStarttotalNetType;
    }

    /**
     * 设置 浮动管理费_期初累计净值尾数处理方式

     * @param floatStarttotalNetType 浮动管理费_期初累计净值尾数处理方式

     */
    public void setFloatStarttotalNetType(String floatStarttotalNetType) {
        this.floatStarttotalNetType = floatStarttotalNetType == null ? null : floatStarttotalNetType.trim();
    }

    /**
     * 取得 浮动管理费_期初累计净值小数位数

     * @return 浮动管理费_期初累计净值小数位数

     */
    public String getFloatStarttotalNetDigits() {
        return floatStarttotalNetDigits;
    }

    /**
     * 设置 浮动管理费_期初累计净值小数位数

     * @param floatStarttotalNetDigits 浮动管理费_期初累计净值小数位数

     */
    public void setFloatStarttotalNetDigits(String floatStarttotalNetDigits) {
        this.floatStarttotalNetDigits = floatStarttotalNetDigits == null ? null : floatStarttotalNetDigits.trim();
    }

    /**
     * 取得 浮动管理费_期末累计净值尾数处理方式

     * @return 浮动管理费_期末累计净值尾数处理方式

     */
    public String getFloatEndtotalNetType() {
        return floatEndtotalNetType;
    }

    /**
     * 设置 浮动管理费_期末累计净值尾数处理方式

     * @param floatEndtotalNetType 浮动管理费_期末累计净值尾数处理方式

     */
    public void setFloatEndtotalNetType(String floatEndtotalNetType) {
        this.floatEndtotalNetType = floatEndtotalNetType == null ? null : floatEndtotalNetType.trim();
    }

    /**
     * 取得 浮动管理费_期末累计净值小数位数

     * @return 浮动管理费_期末累计净值小数位数

     */
    public String getFloatEndtotalNetDigits() {
        return floatEndtotalNetDigits;
    }

    /**
     * 设置 浮动管理费_期末累计净值小数位数

     * @param floatEndtotalNetDigits 浮动管理费_期末累计净值小数位数

     */
    public void setFloatEndtotalNetDigits(String floatEndtotalNetDigits) {
        this.floatEndtotalNetDigits = floatEndtotalNetDigits == null ? null : floatEndtotalNetDigits.trim();
    }

    /**
     * 取得 浮动管理费_周期年化收益率位数处理方式

     * @return 浮动管理费_周期年化收益率位数处理方式

     */
    public String getFloatCyclyearYieldType() {
        return floatCyclyearYieldType;
    }

    /**
     * 设置 浮动管理费_周期年化收益率位数处理方式

     * @param floatCyclyearYieldType 浮动管理费_周期年化收益率位数处理方式

     */
    public void setFloatCyclyearYieldType(String floatCyclyearYieldType) {
        this.floatCyclyearYieldType = floatCyclyearYieldType == null ? null : floatCyclyearYieldType.trim();
    }

    /**
     * 取得 浮动管理费_周期年化收益率小数位数

     * @return 浮动管理费_周期年化收益率小数位数

     */
    public String getFloatCyclyearYieldDigits() {
        return floatCyclyearYieldDigits;
    }

    /**
     * 设置 浮动管理费_周期年化收益率小数位数

     * @param floatCyclyearYieldDigits 浮动管理费_周期年化收益率小数位数

     */
    public void setFloatCyclyearYieldDigits(String floatCyclyearYieldDigits) {
        this.floatCyclyearYieldDigits = floatCyclyearYieldDigits == null ? null : floatCyclyearYieldDigits.trim();
    }

    /**
     * 取得 周期年化收益率小数尾数处理方式_估值表展示

     * @return 周期年化收益率小数尾数处理方式_估值表展示

     */
    public String getShowCyclyearYieldType() {
        return showCyclyearYieldType;
    }

    /**
     * 设置 周期年化收益率小数尾数处理方式_估值表展示

     * @param showCyclyearYieldType 周期年化收益率小数尾数处理方式_估值表展示

     */
    public void setShowCyclyearYieldType(String showCyclyearYieldType) {
        this.showCyclyearYieldType = showCyclyearYieldType == null ? null : showCyclyearYieldType.trim();
    }

    /**
     * 取得 周期年化收益率小数位数_估值表展示

     * @return 周期年化收益率小数位数_估值表展示

     */
    public String getShowCyclyearYieldDigits() {
        return showCyclyearYieldDigits;
    }

    /**
     * 设置 周期年化收益率小数位数_估值表展示

     * @param showCyclyearYieldDigits 周期年化收益率小数位数_估值表展示

     */
    public void setShowCyclyearYieldDigits(String showCyclyearYieldDigits) {
        this.showCyclyearYieldDigits = showCyclyearYieldDigits == null ? null : showCyclyearYieldDigits.trim();
    }

    /**
     * 取得 产品费前估值小数尾数处理方式_估值表展示

     * @return 产品费前估值小数尾数处理方式_估值表展示

     */
    public String getShowBeforeDecType() {
        return showBeforeDecType;
    }

    /**
     * 设置 产品费前估值小数尾数处理方式_估值表展示

     * @param showBeforeDecType 产品费前估值小数尾数处理方式_估值表展示

     */
    public void setShowBeforeDecType(String showBeforeDecType) {
        this.showBeforeDecType = showBeforeDecType == null ? null : showBeforeDecType.trim();
    }

    /**
     * 取得 产品费前估值小数位数_估值表展示
     * @return 产品费前估值小数位数_估值表展示
     */
    public String getShowBeforeDecDigits() {
        return showBeforeDecDigits;
    }

    /**
     * 设置 产品费前估值小数位数_估值表展示
     * @param showBeforeDecDigits 产品费前估值小数位数_估值表展示
     */
    public void setShowBeforeDecDigits(String showBeforeDecDigits) {
        this.showBeforeDecDigits = showBeforeDecDigits == null ? null : showBeforeDecDigits.trim();
    }

    /**
     * 取得 产品费后估值小数尾数处理方式_估值表展示
     * @return 产品费后估值小数尾数处理方式_估值表展示
     */
    public String getShowAfterDecType() {
        return showAfterDecType;
    }

    /**
     * 设置 产品费后估值小数尾数处理方式_估值表展示
     * @param showAfterDecType 产品费后估值小数尾数处理方式_估值表展示
     */
    public void setShowAfterDecType(String showAfterDecType) {
        this.showAfterDecType = showAfterDecType == null ? null : showAfterDecType.trim();
    }
    //add by liupengfei for 多级法人改造 S
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