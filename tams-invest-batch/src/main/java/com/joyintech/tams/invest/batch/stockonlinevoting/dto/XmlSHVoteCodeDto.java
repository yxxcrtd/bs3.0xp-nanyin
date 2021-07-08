package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSHVoteCodeDto {

    @XmlElement(name = "votecode")
    private String votecode;

    /**
     * 最后交易时间
     */
    @XmlElement(name = "lasttradedate")
    private String lasttradedate;

    /**
     *
     */
    @XmlElement(name = "regdate")
    private String regdate;

    /**
     *
     */
    @XmlElement(name = "stype")
    private String stype;

    /**
     *
     */
    @XmlElement(name = "sabbr")
    private String sabbr;

    public String getLasttradedate() {
        return lasttradedate;
    }

    public void setLasttradedate(String lasttradedate) {
        this.lasttradedate = lasttradedate;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSabbr() {
        return sabbr;
    }

    public void setSabbr(String sabbr) {
        this.sabbr = sabbr;
    }

    public String getVotecode() {
        return votecode;
    }

    public void setVotecode(String votecode) {
        this.votecode = votecode;
    }
}
