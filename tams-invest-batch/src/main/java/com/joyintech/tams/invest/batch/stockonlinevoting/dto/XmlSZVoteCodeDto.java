package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSZVoteCodeDto {

    /**
     * 最后交易时间
     */
    @XmlElement(name = "LastTradeDate")
    private String lastTradeDate;

    @XmlElement(name = "RegDate")
    private String regDate;

    @XmlElement(name = "ShareClass")
    private String shareClass;

    @XmlElement(name = "Symbol")
    private String symbol;

    @XmlElement(name = "SecurityID")
    private String securityID;

    public String getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(String lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getShareClass() {
        return shareClass;
    }

    public void setShareClass(String shareClass) {
        this.shareClass = shareClass;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSecurityID() {
        return securityID;
    }

    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }
}
