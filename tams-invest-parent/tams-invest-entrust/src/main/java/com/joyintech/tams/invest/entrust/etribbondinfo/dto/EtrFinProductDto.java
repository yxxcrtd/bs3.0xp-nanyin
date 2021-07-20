package com.joyintech.tams.invest.entrust.etribbondinfo.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrFinProductDto
 * @since 1.0
 */
public class EtrFinProductDto {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 证券管理账户/通道代码
     */
    private String secAcctId;

    /**
     * 组合T日可用现金
     */
    private BigDecimal zhkyxj_t;

    /**
     * 组合T+1日可用现金
     */
    private BigDecimal zhkyxj_t1;

    /**
     * 托管户T日可用现金
     */
    private BigDecimal tghkyxj_t;

    /**
     * 托管户T+1日可用现金
     */
    private BigDecimal tghkyxj_t1;

    /**
     * 通道T日可用现金
     */
    private BigDecimal tdkyxj_t;

    /**
     * 通道T+1日可用现金
     */
    private BigDecimal tdkyxj_t1;

    /**
     * 资产持仓券面总额
     */
    private BigDecimal zccc_qmze;

    /**
     * 资产可用券面总额
     */
    private BigDecimal zcky_qmze;

    /**
     * 债券名称
     */
    private String secName;

    /**
     * 债券分类
     */
    private String secType;

    /**
     * 债券分类
     */
    private String secTypeName;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 发行人
     */
    private String issuerName;

    /**
     * 债券名称
     */
    private String secId;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 剩余期限(债券估值行情)
     */
    private BigDecimal lastqx;

    /**
     * 下一付息日
     */
    private String lastPaydate;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 利率类型
     */
    private String rateTypeName;

    /**
     * 票面利率
     */
    private BigDecimal rate;

    /**
     * 债项评级
     */
    private String zxGrade;

    /**
     * 主体评级
     */
    private String ztGrade;

    /**
     * 含权标识
     */
    private String xqFlg;

    /**
     * 下一行权日
     */
    private String xqdate;

    /**
     * 最新净价
     */
    private BigDecimal newPrice;

    /**
     * 估价净价（行权）
     */
    private BigDecimal priceXq;

    /**
     * 最新收益率
     */
    private BigDecimal newRate;

    /**
     * 估价净价（推荐）
     */
    private BigDecimal priceTj;

    /**
     * 估值收益率
     */
    private BigDecimal gzRate;
    
    

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public BigDecimal getZhkyxj_t() {
        return zhkyxj_t;
    }

    public void setZhkyxj_t(BigDecimal zhkyxj_t) {
        this.zhkyxj_t = zhkyxj_t;
    }

    public BigDecimal getZhkyxj_t1() {
        return zhkyxj_t1;
    }

    public void setZhkyxj_t1(BigDecimal zhkyxj_t1) {
        this.zhkyxj_t1 = zhkyxj_t1;
    }

    public BigDecimal getTghkyxj_t() {
        return tghkyxj_t;
    }

    public void setTghkyxj_t(BigDecimal tghkyxj_t) {
        this.tghkyxj_t = tghkyxj_t;
    }

    public BigDecimal getTghkyxj_t1() {
        return tghkyxj_t1;
    }

    public void setTghkyxj_t1(BigDecimal tghkyxj_t1) {
        this.tghkyxj_t1 = tghkyxj_t1;
    }

    public BigDecimal getTdkyxj_t() {
        return tdkyxj_t;
    }

    public void setTdkyxj_t(BigDecimal tdkyxj_t) {
        this.tdkyxj_t = tdkyxj_t;
    }

    public BigDecimal getTdkyxj_t1() {
        return tdkyxj_t1;
    }

    public void setTdkyxj_t1(BigDecimal tdkyxj_t1) {
        this.tdkyxj_t1 = tdkyxj_t1;
    }

    public BigDecimal getZccc_qmze() {
        return zccc_qmze;
    }

    public void setZccc_qmze(BigDecimal zccc_qmze) {
        this.zccc_qmze = zccc_qmze;
    }

    public BigDecimal getZcky_qmze() {
        return zcky_qmze;
    }

    public void setZcky_qmze(BigDecimal zcky_qmze) {
        this.zcky_qmze = zcky_qmze;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public BigDecimal getLastqx() {
        return lastqx;
    }

    public void setLastqx(BigDecimal lastqx) {
        this.lastqx = lastqx;
    }

    public String getLastPaydate() {
        return lastPaydate;
    }

    public void setLastPaydate(String lastPaydate) {
        this.lastPaydate = lastPaydate;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getZxGrade() {
        return zxGrade;
    }

    public void setZxGrade(String zxGrade) {
        this.zxGrade = zxGrade;
    }

    public String getZtGrade() {
        return ztGrade;
    }

    public void setZtGrade(String ztGrade) {
        this.ztGrade = ztGrade;
    }

    public String getXqFlgName() {
        if (StringUtil.isEmpty(this.xqFlg) || StringUtil.equals(this.xqFlg, "无")) {
            return "无";
        }
        if (this.xqFlg.contains(";")){
            return Arrays.stream(this.xqFlg.split(";"))
                    .map(i->SysDataUtil.getNameFromDataDict(DictCode.行权方式, i))
                    .collect(Collectors.joining(","));
        }else{
            return SysDataUtil.getNameFromDataDict(DictCode.行权方式, getXqFlg());
        }
    }

    public String getXqFlg() {
        return xqFlg;
    }

    public void setXqFlg(String xqFlg) {
        this.xqFlg = xqFlg;
    }

    public String getXqdate() {
        return xqdate;
    }

    public void setXqdate(String xqdate) {
        this.xqdate = xqdate;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getPriceXq() {
        return priceXq;
    }

    public void setPriceXq(BigDecimal priceXq) {
        this.priceXq = priceXq;
    }

    public BigDecimal getNewRate() {
        return newRate;
    }

    public void setNewRate(BigDecimal newRate) {
        this.newRate = newRate;
    }

    public BigDecimal getPriceTj() {
        return priceTj;
    }

    public void setPriceTj(BigDecimal priceTj) {
        this.priceTj = priceTj;
    }

    public BigDecimal getGzRate() {
        return gzRate;
    }

    public void setGzRate(BigDecimal gzRate) {
        this.gzRate = gzRate;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getRateTypeName() {
        if (StringUtil.isEmpty(getRateType())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.利率类型, getRateType());
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }

    public String getSecTypeName() {
        if (StringUtil.isEmpty(getSecType())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, getSecType());
    }

    public void setSecTypeName(String secTypeName) {
        this.secTypeName = secTypeName;
    }
}
