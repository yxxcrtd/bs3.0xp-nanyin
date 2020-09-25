package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfTradSecdeal extends BaseEntity {
    /**
     * ???
     */
    private String dealno;

    /**
     * ????
     */
    private String secid;

    /**
     * ????
     */
    private String profolioId;

    /**
     * ????
     */
    private String ccy;

    /**
     * ????
     */
    private String invtype;

    /**
     * ????
     */
    private String ps;

    /**
     * ??
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal price8;

    /**
     * ???
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield8;

    /**
     * ???????
     */
    private String cno;

    /**
     * ??
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceamt;

    /**
     * ????,?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * ??????????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settamt;

    /**
     * ??
     */
    private String product;

    /**
     * ????
     */
    private String prodtype;

    /**
     * ???
     */
    private String trad;

    /**
     * ??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal convintamt;

    /**
     * E??D??
     */
    private String effectflag;

    /**
     * ???
     */
    private String brok;

    /**
     * ????
     */
    private Date inputdate;

    /**
     * ????
     */
    private Date dealdate;

    /**
     * ???
     */
    private Date settdate;

    /**
     * ????
     */
    private Date revdate;

    /**
     * ?????
     */
    private Long updatecounter;

    /**
     * ??????
     */
    private String fedealno;

    /**
     * ??
     */
    private String dealtext;

    /**
     * ????
     */
    private Date lstmntdate;

    /**
     * ??????
     */
    private String clienttrader;

    /**
     * ???????
     */
    private String countertelephone;

    /**
     * ????
     */
    private String ourdepositaccountnumber;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal strikeyield;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyprice;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accruedinterestamt;

    /**
     * null
     */
    private String settleSpeed;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 取得 ???
     * @return ???
     */
    public String getDealno() {
        return dealno;
    }

    /**
     * 设置 ???
     * @param dealno ???
     */
    public void setDealno(String dealno) {
        this.dealno = dealno == null ? null : dealno.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 ????
     * @param secid ????
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getProfolioId() {
        return profolioId;
    }

    /**
     * 设置 ????
     * @param profolioId ????
     */
    public void setProfolioId(String profolioId) {
        this.profolioId = profolioId == null ? null : profolioId.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ????
     * @param ccy ????
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getInvtype() {
        return invtype;
    }

    /**
     * 设置 ????
     * @param invtype ????
     */
    public void setInvtype(String invtype) {
        this.invtype = invtype == null ? null : invtype.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ????
     * @param ps ????
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 ??
     * @return ??
     */
    public BigDecimal getPrice8() {
        return price8;
    }

    /**
     * 设置 ??
     * @param price8 ??
     */
    public void setPrice8(BigDecimal price8) {
        this.price8 = price8;
    }

    /**
     * 取得 ???
     * @return ???
     */
    public BigDecimal getYield8() {
        return yield8;
    }

    /**
     * 设置 ???
     * @param yield8 ???
     */
    public void setYield8(BigDecimal yield8) {
        this.yield8 = yield8;
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置 ???????
     * @param cno ???????
     */
    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    /**
     * 取得 ??
     * @return ??
     */
    public BigDecimal getFaceamt() {
        return faceamt;
    }

    /**
     * 设置 ??
     * @param faceamt ??
     */
    public void setFaceamt(BigDecimal faceamt) {
        this.faceamt = faceamt;
    }

    /**
     * 取得 ????,?????
     * @return ????,?????
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 ????,?????
     * @param prinamt ????,?????
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 取得 ??????????
     * @return ??????????
     */
    public BigDecimal getSettamt() {
        return settamt;
    }

    /**
     * 设置 ??????????
     * @param settamt ??????????
     */
    public void setSettamt(BigDecimal settamt) {
        this.settamt = settamt;
    }

    /**
     * 取得 ??
     * @return ??
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置 ??
     * @param product ??
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 ????
     * @param prodtype ????
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getTrad() {
        return trad;
    }

    /**
     * 设置 ???
     * @param trad ???
     */
    public void setTrad(String trad) {
        this.trad = trad == null ? null : trad.trim();
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public BigDecimal getConvintamt() {
        return convintamt;
    }

    /**
     * 设置 ??????
     * @param convintamt ??????
     */
    public void setConvintamt(BigDecimal convintamt) {
        this.convintamt = convintamt;
    }

    /**
     * 取得 E??D??
     * @return E??D??
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 E??D??
     * @param effectflag E??D??
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getBrok() {
        return brok;
    }

    /**
     * 设置 ???
     * @param brok ???
     */
    public void setBrok(String brok) {
        this.brok = brok == null ? null : brok.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Date getInputdate() {
        return inputdate;
    }

    /**
     * 设置 ????
     * @param inputdate ????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 设置 ????
     * @param dealdate ????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * 取得 ???
     * @return ???
     */
    public Date getSettdate() {
        return settdate;
    }

    /**
     * 设置 ???
     * @param settdate ???
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdate(Date settdate) {
        this.settdate = settdate;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Date getRevdate() {
        return revdate;
    }

    /**
     * 设置 ????
     * @param revdate ????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevdate(Date revdate) {
        this.revdate = revdate;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public Long getUpdatecounter() {
        return updatecounter;
    }

    /**
     * 设置 ?????
     * @param updatecounter ?????
     */
    public void setUpdatecounter(Long updatecounter) {
        this.updatecounter = updatecounter;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getFedealno() {
        return fedealno;
    }

    /**
     * 设置 ??????
     * @param fedealno ??????
     */
    public void setFedealno(String fedealno) {
        this.fedealno = fedealno == null ? null : fedealno.trim();
    }

    /**
     * 取得 ??
     * @return ??
     */
    public String getDealtext() {
        return dealtext;
    }

    /**
     * 设置 ??
     * @param dealtext ??
     */
    public void setDealtext(String dealtext) {
        this.dealtext = dealtext == null ? null : dealtext.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 ????
     * @param lstmntdate ????
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getClienttrader() {
        return clienttrader;
    }

    /**
     * 设置 ??????
     * @param clienttrader ??????
     */
    public void setClienttrader(String clienttrader) {
        this.clienttrader = clienttrader == null ? null : clienttrader.trim();
    }

    /**
     * 取得 ???????
     * @return ???????
     */
    public String getCountertelephone() {
        return countertelephone;
    }

    /**
     * 设置 ???????
     * @param countertelephone ???????
     */
    public void setCountertelephone(String countertelephone) {
        this.countertelephone = countertelephone == null ? null : countertelephone.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getOurdepositaccountnumber() {
        return ourdepositaccountnumber;
    }

    /**
     * 设置 ????
     * @param ourdepositaccountnumber ????
     */
    public void setOurdepositaccountnumber(String ourdepositaccountnumber) {
        this.ourdepositaccountnumber = ourdepositaccountnumber == null ? null : ourdepositaccountnumber.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getStrikeyield() {
        return strikeyield;
    }

    /**
     * 设置 null
     * @param strikeyield null
     */
    public void setStrikeyield(BigDecimal strikeyield) {
        this.strikeyield = strikeyield;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getDirtyprice() {
        return dirtyprice;
    }

    /**
     * 设置 null
     * @param dirtyprice null
     */
    public void setDirtyprice(BigDecimal dirtyprice) {
        this.dirtyprice = dirtyprice;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getAccruedinterestamt() {
        return accruedinterestamt;
    }

    /**
     * 设置 null
     * @param accruedinterestamt null
     */
    public void setAccruedinterestamt(BigDecimal accruedinterestamt) {
        this.accruedinterestamt = accruedinterestamt;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 null
     * @param settleSpeed null
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed == null ? null : settleSpeed.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 null
     * @param unitInt null
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }
}