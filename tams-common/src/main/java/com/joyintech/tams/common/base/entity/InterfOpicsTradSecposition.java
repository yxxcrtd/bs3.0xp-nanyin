package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfOpicsTradSecposition extends BaseEntity {
    /**
     * 分支代码
     */
    private String br;

    /**
     * 头寸日期
     */
    private Date cdate;

    /**
     * 债券代码
     */
    private String secid;

    /**
     * 投资类型
     */
    private String invtype;

    /**
     * Cost Center
     */
    private String cost;

    /**
     * PORT
     */
    private String port;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 产品代码
     */
    private String product;

    /**
     * 产品类型代码
     */
    private String prodtype;

    /**
     * 买入成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal purchavgcost;

    /**
     * 今日本金（交割日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 上一日本金（交割日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtyst;

    /**
     * 今日本金（交易日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdprinamt;

    /**
     * 上一日本金（交易日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdprinamtyst;

    /**
     * 账面价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settavgcost;

    /**
     * 上一日账面价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settavgcostyst;

    /**
     * 当日累计应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyintincexp;

    /**
     * 上一日累计应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ystintincexp;

    /**
     * 上月末累计应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pmeintincexp;

    /**
     * 上年末累计应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pyeintincexp;

    /**
     * 当日累计已摊销金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdydscincexp;

    /**
     * 上一日累计已摊销金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ystdscincexp;

    /**
     * 上月末累计已摊销金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pmedscincexp;

    /**
     * 上年末累计已摊销金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pyedscincexp;

    /**
     * 当日累计已实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdysalegain;

    /**
     * 上一日累计已实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ystsalegain;

    /**
     * 上月末累计已实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pmesalegain;

    /**
     * 上年末累计已实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pyesalegain;

    /**
     * 当日累计未实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyuglamt;

    /**
     * 上一日累计未实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ystuglamt;

    /**
     * 上月末累计未实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pmeuglamt;

    /**
     * 上年末累计未实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pyeuglamt;

    /**
     * 当日市值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdymtm;

    /**
     * 上一日市值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ystmtm;

    /**
     * 上月末市值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pmemtm;

    /**
     * 上年末市值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pyemtm;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal clsgprice8;

    /**
     * 当日摊余后成本价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdbookvalue;

    /**
     * 上一日摊余后成本价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdbookvalueyst;

    /**
     * 当日市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdmarketvalue;

    /**
     * 上一日市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdmarketvalueyst;

    /**
     * 当日应计利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accroutst;

    /**
     * 上一日应计利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accroutstyst;

    /**
     * 取得 分支代码
     * @return 分支代码
     */
    public String getBr() {
        return br;
    }

    /**
     * 设置 分支代码
     * @param br 分支代码
     */
    public void setBr(String br) {
        this.br = br == null ? null : br.trim();
    }

    /**
     * 取得 头寸日期
     * @return 头寸日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 头寸日期
     * @param cdate 头寸日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
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
     * 取得 Cost Center
     * @return Cost Center
     */
    public String getCost() {
        return cost;
    }

    /**
     * 设置 Cost Center
     * @param cost Cost Center
     */
    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    /**
     * 取得 PORT
     * @return PORT
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置 PORT
     * @param port PORT
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
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
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置 产品代码
     * @param product 产品代码
     */
    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    /**
     * 取得 产品类型代码
     * @return 产品类型代码
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 产品类型代码
     * @param prodtype 产品类型代码
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 买入成本
     * @return 买入成本
     */
    public BigDecimal getPurchavgcost() {
        return purchavgcost;
    }

    /**
     * 设置 买入成本
     * @param purchavgcost 买入成本
     */
    public void setPurchavgcost(BigDecimal purchavgcost) {
        this.purchavgcost = purchavgcost;
    }

    /**
     * 取得 今日本金（交割日）
     * @return 今日本金（交割日）
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 今日本金（交割日）
     * @param prinamt 今日本金（交割日）
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 取得 上一日本金（交割日）
     * @return 上一日本金（交割日）
     */
    public BigDecimal getPrinamtyst() {
        return prinamtyst;
    }

    /**
     * 设置 上一日本金（交割日）
     * @param prinamtyst 上一日本金（交割日）
     */
    public void setPrinamtyst(BigDecimal prinamtyst) {
        this.prinamtyst = prinamtyst;
    }

    /**
     * 取得 今日本金（交易日）
     * @return 今日本金（交易日）
     */
    public BigDecimal getTdprinamt() {
        return tdprinamt;
    }

    /**
     * 设置 今日本金（交易日）
     * @param tdprinamt 今日本金（交易日）
     */
    public void setTdprinamt(BigDecimal tdprinamt) {
        this.tdprinamt = tdprinamt;
    }

    /**
     * 取得 上一日本金（交易日）
     * @return 上一日本金（交易日）
     */
    public BigDecimal getTdprinamtyst() {
        return tdprinamtyst;
    }

    /**
     * 设置 上一日本金（交易日）
     * @param tdprinamtyst 上一日本金（交易日）
     */
    public void setTdprinamtyst(BigDecimal tdprinamtyst) {
        this.tdprinamtyst = tdprinamtyst;
    }

    /**
     * 取得 账面价值
     * @return 账面价值
     */
    public BigDecimal getSettavgcost() {
        return settavgcost;
    }

    /**
     * 设置 账面价值
     * @param settavgcost 账面价值
     */
    public void setSettavgcost(BigDecimal settavgcost) {
        this.settavgcost = settavgcost;
    }

    /**
     * 取得 上一日账面价值
     * @return 上一日账面价值
     */
    public BigDecimal getSettavgcostyst() {
        return settavgcostyst;
    }

    /**
     * 设置 上一日账面价值
     * @param settavgcostyst 上一日账面价值
     */
    public void setSettavgcostyst(BigDecimal settavgcostyst) {
        this.settavgcostyst = settavgcostyst;
    }

    /**
     * 取得 当日累计应计利息
     * @return 当日累计应计利息
     */
    public BigDecimal getTdyintincexp() {
        return tdyintincexp;
    }

    /**
     * 设置 当日累计应计利息
     * @param tdyintincexp 当日累计应计利息
     */
    public void setTdyintincexp(BigDecimal tdyintincexp) {
        this.tdyintincexp = tdyintincexp;
    }

    /**
     * 取得 上一日累计应计利息
     * @return 上一日累计应计利息
     */
    public BigDecimal getYstintincexp() {
        return ystintincexp;
    }

    /**
     * 设置 上一日累计应计利息
     * @param ystintincexp 上一日累计应计利息
     */
    public void setYstintincexp(BigDecimal ystintincexp) {
        this.ystintincexp = ystintincexp;
    }

    /**
     * 取得 上月末累计应计利息
     * @return 上月末累计应计利息
     */
    public BigDecimal getPmeintincexp() {
        return pmeintincexp;
    }

    /**
     * 设置 上月末累计应计利息
     * @param pmeintincexp 上月末累计应计利息
     */
    public void setPmeintincexp(BigDecimal pmeintincexp) {
        this.pmeintincexp = pmeintincexp;
    }

    /**
     * 取得 上年末累计应计利息
     * @return 上年末累计应计利息
     */
    public BigDecimal getPyeintincexp() {
        return pyeintincexp;
    }

    /**
     * 设置 上年末累计应计利息
     * @param pyeintincexp 上年末累计应计利息
     */
    public void setPyeintincexp(BigDecimal pyeintincexp) {
        this.pyeintincexp = pyeintincexp;
    }

    /**
     * 取得 当日累计已摊销金额
     * @return 当日累计已摊销金额
     */
    public BigDecimal getTdydscincexp() {
        return tdydscincexp;
    }

    /**
     * 设置 当日累计已摊销金额
     * @param tdydscincexp 当日累计已摊销金额
     */
    public void setTdydscincexp(BigDecimal tdydscincexp) {
        this.tdydscincexp = tdydscincexp;
    }

    /**
     * 取得 上一日累计已摊销金额
     * @return 上一日累计已摊销金额
     */
    public BigDecimal getYstdscincexp() {
        return ystdscincexp;
    }

    /**
     * 设置 上一日累计已摊销金额
     * @param ystdscincexp 上一日累计已摊销金额
     */
    public void setYstdscincexp(BigDecimal ystdscincexp) {
        this.ystdscincexp = ystdscincexp;
    }

    /**
     * 取得 上月末累计已摊销金额
     * @return 上月末累计已摊销金额
     */
    public BigDecimal getPmedscincexp() {
        return pmedscincexp;
    }

    /**
     * 设置 上月末累计已摊销金额
     * @param pmedscincexp 上月末累计已摊销金额
     */
    public void setPmedscincexp(BigDecimal pmedscincexp) {
        this.pmedscincexp = pmedscincexp;
    }

    /**
     * 取得 上年末累计已摊销金额
     * @return 上年末累计已摊销金额
     */
    public BigDecimal getPyedscincexp() {
        return pyedscincexp;
    }

    /**
     * 设置 上年末累计已摊销金额
     * @param pyedscincexp 上年末累计已摊销金额
     */
    public void setPyedscincexp(BigDecimal pyedscincexp) {
        this.pyedscincexp = pyedscincexp;
    }

    /**
     * 取得 当日累计已实现损益
     * @return 当日累计已实现损益
     */
    public BigDecimal getTdysalegain() {
        return tdysalegain;
    }

    /**
     * 设置 当日累计已实现损益
     * @param tdysalegain 当日累计已实现损益
     */
    public void setTdysalegain(BigDecimal tdysalegain) {
        this.tdysalegain = tdysalegain;
    }

    /**
     * 取得 上一日累计已实现损益
     * @return 上一日累计已实现损益
     */
    public BigDecimal getYstsalegain() {
        return ystsalegain;
    }

    /**
     * 设置 上一日累计已实现损益
     * @param ystsalegain 上一日累计已实现损益
     */
    public void setYstsalegain(BigDecimal ystsalegain) {
        this.ystsalegain = ystsalegain;
    }

    /**
     * 取得 上月末累计已实现损益
     * @return 上月末累计已实现损益
     */
    public BigDecimal getPmesalegain() {
        return pmesalegain;
    }

    /**
     * 设置 上月末累计已实现损益
     * @param pmesalegain 上月末累计已实现损益
     */
    public void setPmesalegain(BigDecimal pmesalegain) {
        this.pmesalegain = pmesalegain;
    }

    /**
     * 取得 上年末累计已实现损益
     * @return 上年末累计已实现损益
     */
    public BigDecimal getPyesalegain() {
        return pyesalegain;
    }

    /**
     * 设置 上年末累计已实现损益
     * @param pyesalegain 上年末累计已实现损益
     */
    public void setPyesalegain(BigDecimal pyesalegain) {
        this.pyesalegain = pyesalegain;
    }

    /**
     * 取得 当日累计未实现损益
     * @return 当日累计未实现损益
     */
    public BigDecimal getTdyuglamt() {
        return tdyuglamt;
    }

    /**
     * 设置 当日累计未实现损益
     * @param tdyuglamt 当日累计未实现损益
     */
    public void setTdyuglamt(BigDecimal tdyuglamt) {
        this.tdyuglamt = tdyuglamt;
    }

    /**
     * 取得 上一日累计未实现损益
     * @return 上一日累计未实现损益
     */
    public BigDecimal getYstuglamt() {
        return ystuglamt;
    }

    /**
     * 设置 上一日累计未实现损益
     * @param ystuglamt 上一日累计未实现损益
     */
    public void setYstuglamt(BigDecimal ystuglamt) {
        this.ystuglamt = ystuglamt;
    }

    /**
     * 取得 上月末累计未实现损益
     * @return 上月末累计未实现损益
     */
    public BigDecimal getPmeuglamt() {
        return pmeuglamt;
    }

    /**
     * 设置 上月末累计未实现损益
     * @param pmeuglamt 上月末累计未实现损益
     */
    public void setPmeuglamt(BigDecimal pmeuglamt) {
        this.pmeuglamt = pmeuglamt;
    }

    /**
     * 取得 上年末累计未实现损益
     * @return 上年末累计未实现损益
     */
    public BigDecimal getPyeuglamt() {
        return pyeuglamt;
    }

    /**
     * 设置 上年末累计未实现损益
     * @param pyeuglamt 上年末累计未实现损益
     */
    public void setPyeuglamt(BigDecimal pyeuglamt) {
        this.pyeuglamt = pyeuglamt;
    }

    /**
     * 取得 当日市值变动
     * @return 当日市值变动
     */
    public BigDecimal getTdymtm() {
        return tdymtm;
    }

    /**
     * 设置 当日市值变动
     * @param tdymtm 当日市值变动
     */
    public void setTdymtm(BigDecimal tdymtm) {
        this.tdymtm = tdymtm;
    }

    /**
     * 取得 上一日市值变动
     * @return 上一日市值变动
     */
    public BigDecimal getYstmtm() {
        return ystmtm;
    }

    /**
     * 设置 上一日市值变动
     * @param ystmtm 上一日市值变动
     */
    public void setYstmtm(BigDecimal ystmtm) {
        this.ystmtm = ystmtm;
    }

    /**
     * 取得 上月末市值变动
     * @return 上月末市值变动
     */
    public BigDecimal getPmemtm() {
        return pmemtm;
    }

    /**
     * 设置 上月末市值变动
     * @param pmemtm 上月末市值变动
     */
    public void setPmemtm(BigDecimal pmemtm) {
        this.pmemtm = pmemtm;
    }

    /**
     * 取得 上年末市值变动
     * @return 上年末市值变动
     */
    public BigDecimal getPyemtm() {
        return pyemtm;
    }

    /**
     * 设置 上年末市值变动
     * @param pyemtm 上年末市值变动
     */
    public void setPyemtm(BigDecimal pyemtm) {
        this.pyemtm = pyemtm;
    }

    /**
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getClsgprice8() {
        return clsgprice8;
    }

    /**
     * 设置 到期收益率
     * @param clsgprice8 到期收益率
     */
    public void setClsgprice8(BigDecimal clsgprice8) {
        this.clsgprice8 = clsgprice8;
    }

    /**
     * 取得 当日摊余后成本价值
     * @return 当日摊余后成本价值
     */
    public BigDecimal getTdbookvalue() {
        return tdbookvalue;
    }

    /**
     * 设置 当日摊余后成本价值
     * @param tdbookvalue 当日摊余后成本价值
     */
    public void setTdbookvalue(BigDecimal tdbookvalue) {
        this.tdbookvalue = tdbookvalue;
    }

    /**
     * 取得 上一日摊余后成本价值
     * @return 上一日摊余后成本价值
     */
    public BigDecimal getTdbookvalueyst() {
        return tdbookvalueyst;
    }

    /**
     * 设置 上一日摊余后成本价值
     * @param tdbookvalueyst 上一日摊余后成本价值
     */
    public void setTdbookvalueyst(BigDecimal tdbookvalueyst) {
        this.tdbookvalueyst = tdbookvalueyst;
    }

    /**
     * 取得 当日市值
     * @return 当日市值
     */
    public BigDecimal getTdmarketvalue() {
        return tdmarketvalue;
    }

    /**
     * 设置 当日市值
     * @param tdmarketvalue 当日市值
     */
    public void setTdmarketvalue(BigDecimal tdmarketvalue) {
        this.tdmarketvalue = tdmarketvalue;
    }

    /**
     * 取得 上一日市值
     * @return 上一日市值
     */
    public BigDecimal getTdmarketvalueyst() {
        return tdmarketvalueyst;
    }

    /**
     * 设置 上一日市值
     * @param tdmarketvalueyst 上一日市值
     */
    public void setTdmarketvalueyst(BigDecimal tdmarketvalueyst) {
        this.tdmarketvalueyst = tdmarketvalueyst;
    }

    /**
     * 取得 当日应计利息金额
     * @return 当日应计利息金额
     */
    public BigDecimal getAccroutst() {
        return accroutst;
    }

    /**
     * 设置 当日应计利息金额
     * @param accroutst 当日应计利息金额
     */
    public void setAccroutst(BigDecimal accroutst) {
        this.accroutst = accroutst;
    }

    /**
     * 取得 上一日应计利息金额
     * @return 上一日应计利息金额
     */
    public BigDecimal getAccroutstyst() {
        return accroutstyst;
    }

    /**
     * 设置 上一日应计利息金额
     * @param accroutstyst 上一日应计利息金额
     */
    public void setAccroutstyst(BigDecimal accroutstyst) {
        this.accroutstyst = accroutstyst;
    }
}