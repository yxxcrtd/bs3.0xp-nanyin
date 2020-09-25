package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfTradRepoasst extends BaseEntity {
    /**
     * 债券ID
     */
    private String secid;

    /**
     * “R”+“YYYYMMDD”+自增3位数编号（从001开始，依日期为周期）。例如：B20100302001
     */
    private String tradeid;

    /**
     * 自增流水号
     */
    private Long seqNo;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal convrate;

    /**
     * 券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal facetotalamt;

    /**
     * 成交金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealamt;

    /**
     * 债券全价（含利息）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dprice;

    /**
     * 债券净价（不含利息）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cprice;

    /**
     * 全价总额，单位 元
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dpriceamt;

    /**
     * 净价总额单位 元
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceamt;

    /**
     * 应计利息（百元券） 单位 元
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accuir;

    /**
     * 应计利息总额，单位 元
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accuiramt;

    /**
     * 到期净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcprice;

    /**
     * 到期净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcpriceamt;

    /**
     * 到期全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mdprice;

    /**
     * 到期全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mdpriceamt;

    /**
     * 更新人员
     */
    private String lstmntuser;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * null
     */
    private String secname;

    /**
     * 取得 债券ID
     * @return 债券ID
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券ID
     * @param secid 债券ID
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 “R”+“YYYYMMDD”+自增3位数编号（从001开始，依日期为周期）。例如：B20100302001
     * @return “R”+“YYYYMMDD”+自增3位数编号（从001开始，依日期为周期）。例如：B20100302001
     */
    public String getTradeid() {
        return tradeid;
    }

    /**
     * 设置 “R”+“YYYYMMDD”+自增3位数编号（从001开始，依日期为周期）。例如：B20100302001
     * @param tradeid “R”+“YYYYMMDD”+自增3位数编号（从001开始，依日期为周期）。例如：B20100302001
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid == null ? null : tradeid.trim();
    }

    /**
     * 取得 自增流水号
     * @return 自增流水号
     */
    public Long getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 自增流水号
     * @param seqNo 自增流水号
     */
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getConvrate() {
        return convrate;
    }

    /**
     * 设置 折算比例
     * @param convrate 折算比例
     */
    public void setConvrate(BigDecimal convrate) {
        this.convrate = convrate;
    }

    /**
     * 取得 券面总额
     * @return 券面总额
     */
    public BigDecimal getFacetotalamt() {
        return facetotalamt;
    }

    /**
     * 设置 券面总额
     * @param facetotalamt 券面总额
     */
    public void setFacetotalamt(BigDecimal facetotalamt) {
        this.facetotalamt = facetotalamt;
    }

    /**
     * 取得 成交金额
     * @return 成交金额
     */
    public BigDecimal getDealamt() {
        return dealamt;
    }

    /**
     * 设置 成交金额
     * @param dealamt 成交金额
     */
    public void setDealamt(BigDecimal dealamt) {
        this.dealamt = dealamt;
    }

    /**
     * 取得 债券全价（含利息）
     * @return 债券全价（含利息）
     */
    public BigDecimal getDprice() {
        return dprice;
    }

    /**
     * 设置 债券全价（含利息）
     * @param dprice 债券全价（含利息）
     */
    public void setDprice(BigDecimal dprice) {
        this.dprice = dprice;
    }

    /**
     * 取得 债券净价（不含利息）
     * @return 债券净价（不含利息）
     */
    public BigDecimal getCprice() {
        return cprice;
    }

    /**
     * 设置 债券净价（不含利息）
     * @param cprice 债券净价（不含利息）
     */
    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    /**
     * 取得 全价总额，单位 元
     * @return 全价总额，单位 元
     */
    public BigDecimal getDpriceamt() {
        return dpriceamt;
    }

    /**
     * 设置 全价总额，单位 元
     * @param dpriceamt 全价总额，单位 元
     */
    public void setDpriceamt(BigDecimal dpriceamt) {
        this.dpriceamt = dpriceamt;
    }

    /**
     * 取得 净价总额单位 元
     * @return 净价总额单位 元
     */
    public BigDecimal getCpriceamt() {
        return cpriceamt;
    }

    /**
     * 设置 净价总额单位 元
     * @param cpriceamt 净价总额单位 元
     */
    public void setCpriceamt(BigDecimal cpriceamt) {
        this.cpriceamt = cpriceamt;
    }

    /**
     * 取得 应计利息（百元券） 单位 元
     * @return 应计利息（百元券） 单位 元
     */
    public BigDecimal getAccuir() {
        return accuir;
    }

    /**
     * 设置 应计利息（百元券） 单位 元
     * @param accuir 应计利息（百元券） 单位 元
     */
    public void setAccuir(BigDecimal accuir) {
        this.accuir = accuir;
    }

    /**
     * 取得 应计利息总额，单位 元
     * @return 应计利息总额，单位 元
     */
    public BigDecimal getAccuiramt() {
        return accuiramt;
    }

    /**
     * 设置 应计利息总额，单位 元
     * @param accuiramt 应计利息总额，单位 元
     */
    public void setAccuiramt(BigDecimal accuiramt) {
        this.accuiramt = accuiramt;
    }

    /**
     * 取得 到期净价
     * @return 到期净价
     */
    public BigDecimal getMcprice() {
        return mcprice;
    }

    /**
     * 设置 到期净价
     * @param mcprice 到期净价
     */
    public void setMcprice(BigDecimal mcprice) {
        this.mcprice = mcprice;
    }

    /**
     * 取得 到期净价总额
     * @return 到期净价总额
     */
    public BigDecimal getMcpriceamt() {
        return mcpriceamt;
    }

    /**
     * 设置 到期净价总额
     * @param mcpriceamt 到期净价总额
     */
    public void setMcpriceamt(BigDecimal mcpriceamt) {
        this.mcpriceamt = mcpriceamt;
    }

    /**
     * 取得 到期全价
     * @return 到期全价
     */
    public BigDecimal getMdprice() {
        return mdprice;
    }

    /**
     * 设置 到期全价
     * @param mdprice 到期全价
     */
    public void setMdprice(BigDecimal mdprice) {
        this.mdprice = mdprice;
    }

    /**
     * 取得 到期全价总额
     * @return 到期全价总额
     */
    public BigDecimal getMdpriceamt() {
        return mdpriceamt;
    }

    /**
     * 设置 到期全价总额
     * @param mdpriceamt 到期全价总额
     */
    public void setMdpriceamt(BigDecimal mdpriceamt) {
        this.mdpriceamt = mdpriceamt;
    }

    /**
     * 取得 更新人员
     * @return 更新人员
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 更新人员
     * @param lstmntuser 更新人员
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
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
     * 取得 null
     * @return null
     */
    public String getSecname() {
        return secname;
    }

    /**
     * 设置 null
     * @param secname null
     */
    public void setSecname(String secname) {
        this.secname = secname == null ? null : secname.trim();
    }
}