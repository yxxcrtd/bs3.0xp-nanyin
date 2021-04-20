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
     * 交易号
     */
    private String dealno;

    /**
     * 债券代码
     */
    private String secid;

    /**
     * 组合代码
     */
    private String profolioId;

    /**
     * 币种代码
     */
    private String ccy;

    /**
     * 投资类型
     */
    private String invtype;

    /**
     * 买卖标识
     */
    private String ps;

    /**
     * 价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal price8;

    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield8;

    /**
     * 交易对手号代码
     */
    private String cno;

    /**
     * 票值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceamt;

    /**
     * 本金金额,即券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 结算金额，即全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settamt;

    /**
     * 产品
     */
    private String product;

    /**
     * 业务类型
     */
    private String prodtype;

    /**
     * 交易员
     */
    private String trad;

    /**
     * 转换利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal convintamt;

    /**
     * E有效D删除
     */
    private String effectflag;

    /**
     * 经纪人
     */
    private String brok;

    /**
     * 录入日期
     */
    private Date inputdate;

    /**
     * 交易日期
     */
    private Date dealdate;

    /**
     * 交割日
     */
    private Date settdate;

    /**
     * 冲销日期
     */
    private Date revdate;

    /**
     * 更新的次数
     */
    private Long updatecounter;

    /**
     * 原始交易编号
     */
    private String fedealno;

    /**
     * 备注
     */
    private String dealtext;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 对手方交易员
     */
    private String clienttrader;

    /**
     * 对方交易员电话
     */
    private String countertelephone;

    /**
     * 托管账号
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
     * 交易手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealFeeamt;

    /**
     * 结算服务费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settFeeamt;

    /**
     * 取得 交易号
     * @return 交易号
     */
    public String getDealno() {
        return dealno;
    }

    /**
     * 设置 交易号
     * @param dealno 交易号
     */
    public void setDealno(String dealno) {
        this.dealno = dealno == null ? null : dealno.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券代码
     * @param secid 债券代码
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getProfolioId() {
        return profolioId;
    }

    /**
     * 设置 组合代码
     * @param profolioId 组合代码
     */
    public void setProfolioId(String profolioId) {
        this.profolioId = profolioId == null ? null : profolioId.trim();
    }

    /**
     * 取得 币种代码
     * @return 币种代码
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种代码
     * @param ccy 币种代码
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 投资类型
     * @return 投资类型
     */
    public String getInvtype() {
        return invtype;
    }

    /**
     * 设置 投资类型
     * @param invtype 投资类型
     */
    public void setInvtype(String invtype) {
        this.invtype = invtype == null ? null : invtype.trim();
    }

    /**
     * 取得 买卖标识
     * @return 买卖标识
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 买卖标识
     * @param ps 买卖标识
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 价格
     * @return 价格
     */
    public BigDecimal getPrice8() {
        return price8;
    }

    /**
     * 设置 价格
     * @param price8 价格
     */
    public void setPrice8(BigDecimal price8) {
        this.price8 = price8;
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYield8() {
        return yield8;
    }

    /**
     * 设置 收益率
     * @param yield8 收益率
     */
    public void setYield8(BigDecimal yield8) {
        this.yield8 = yield8;
    }

    /**
     * 取得 交易对手号代码
     * @return 交易对手号代码
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置 交易对手号代码
     * @param cno 交易对手号代码
     */
    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    /**
     * 取得 票值
     * @return 票值
     */
    public BigDecimal getFaceamt() {
        return faceamt;
    }

    /**
     * 设置 票值
     * @param faceamt 票值
     */
    public void setFaceamt(BigDecimal faceamt) {
        this.faceamt = faceamt;
    }

    /**
     * 取得 本金金额,即券面总额
     * @return 本金金额,即券面总额
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 本金金额,即券面总额
     * @param prinamt 本金金额,即券面总额
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 取得 结算金额，即全价总额
     * @return 结算金额，即全价总额
     */
    public BigDecimal getSettamt() {
        return settamt;
    }

    /**
     * 设置 结算金额，即全价总额
     * @param settamt 结算金额，即全价总额
     */
    public void setSettamt(BigDecimal settamt) {
        this.settamt = settamt;
    }

    /**
     * 取得 产品
     * @return 产品
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置 产品
     * @param product 产品
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 业务类型
     * @param prodtype 业务类型
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 交易员
     * @return 交易员
     */
    public String getTrad() {
        return trad;
    }

    /**
     * 设置 交易员
     * @param trad 交易员
     */
    public void setTrad(String trad) {
        this.trad = trad == null ? null : trad.trim();
    }

    /**
     * 取得 转换利息金额
     * @return 转换利息金额
     */
    public BigDecimal getConvintamt() {
        return convintamt;
    }

    /**
     * 设置 转换利息金额
     * @param convintamt 转换利息金额
     */
    public void setConvintamt(BigDecimal convintamt) {
        this.convintamt = convintamt;
    }

    /**
     * 取得 E有效D删除
     * @return E有效D删除
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 E有效D删除
     * @param effectflag E有效D删除
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 经纪人
     * @return 经纪人
     */
    public String getBrok() {
        return brok;
    }

    /**
     * 设置 经纪人
     * @param brok 经纪人
     */
    public void setBrok(String brok) {
        this.brok = brok == null ? null : brok.trim();
    }

    /**
     * 取得 录入日期
     * @return 录入日期
     */
    public Date getInputdate() {
        return inputdate;
    }

    /**
     * 设置 录入日期
     * @param inputdate 录入日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getDealdate() {
        return dealdate;
    }

    /**
     * 设置 交易日期
     * @param dealdate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealdate(Date dealdate) {
        this.dealdate = dealdate;
    }

    /**
     * 取得 交割日
     * @return 交割日
     */
    public Date getSettdate() {
        return settdate;
    }

    /**
     * 设置 交割日
     * @param settdate 交割日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdate(Date settdate) {
        this.settdate = settdate;
    }

    /**
     * 取得 冲销日期
     * @return 冲销日期
     */
    public Date getRevdate() {
        return revdate;
    }

    /**
     * 设置 冲销日期
     * @param revdate 冲销日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevdate(Date revdate) {
        this.revdate = revdate;
    }

    /**
     * 取得 更新的次数
     * @return 更新的次数
     */
    public Long getUpdatecounter() {
        return updatecounter;
    }

    /**
     * 设置 更新的次数
     * @param updatecounter 更新的次数
     */
    public void setUpdatecounter(Long updatecounter) {
        this.updatecounter = updatecounter;
    }

    /**
     * 取得 原始交易编号
     * @return 原始交易编号
     */
    public String getFedealno() {
        return fedealno;
    }

    /**
     * 设置 原始交易编号
     * @param fedealno 原始交易编号
     */
    public void setFedealno(String fedealno) {
        this.fedealno = fedealno == null ? null : fedealno.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getDealtext() {
        return dealtext;
    }

    /**
     * 设置 备注
     * @param dealtext 备注
     */
    public void setDealtext(String dealtext) {
        this.dealtext = dealtext == null ? null : dealtext.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 更新时间
     * @param lstmntdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 对手方交易员
     * @return 对手方交易员
     */
    public String getClienttrader() {
        return clienttrader;
    }

    /**
     * 设置 对手方交易员
     * @param clienttrader 对手方交易员
     */
    public void setClienttrader(String clienttrader) {
        this.clienttrader = clienttrader == null ? null : clienttrader.trim();
    }

    /**
     * 取得 对方交易员电话
     * @return 对方交易员电话
     */
    public String getCountertelephone() {
        return countertelephone;
    }

    /**
     * 设置 对方交易员电话
     * @param countertelephone 对方交易员电话
     */
    public void setCountertelephone(String countertelephone) {
        this.countertelephone = countertelephone == null ? null : countertelephone.trim();
    }

    /**
     * 取得 托管账号
     * @return 托管账号
     */
    public String getOurdepositaccountnumber() {
        return ourdepositaccountnumber;
    }

    /**
     * 设置 托管账号
     * @param ourdepositaccountnumber 托管账号
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

    /**
     * 取得 交易手续费
     * @return 交易手续费
     */
    public BigDecimal getDealFeeamt() {
        return dealFeeamt;
    }

    /**
     * 设置 交易手续费
     * @param dealFeeamt 交易手续费
     */
    public void setDealFeeamt(BigDecimal dealFeeamt) {
        this.dealFeeamt = dealFeeamt;
    }

    /**
     * 取得 结算服务费
     * @return 结算服务费
     */
    public BigDecimal getSettFeeamt() {
        return settFeeamt;
    }

    /**
     * 设置 结算服务费
     * @param settFeeamt 结算服务费
     */
    public void setSettFeeamt(BigDecimal settFeeamt) {
        this.settFeeamt = settFeeamt;
    }
}