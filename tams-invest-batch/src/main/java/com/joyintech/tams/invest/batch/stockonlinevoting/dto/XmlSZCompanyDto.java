package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSZCompanyDto {
    /**
     * 公司资产代码
     */
    @XmlElement(name = "UnderlyingSecurityID")
    private String underlyingSecurityID;

    /**
     * 公司资产名称
     */
    @XmlElement(name = "CompanySymbol")
    private String companySymbol;

    /**
     * 股东大会编号
     */
    @XmlElement(name = "MeetingSeq")
    private String meetingSeq;

    /**
     * 股东大会开始日期
     */
    @XmlElement(name = "meetingdatebegin")
    private String meetingdatebegin;

    /**
     * 股东大会结束日期
     */
    @XmlElement(name = "meetingdateend")
    private String meetingdateend;

    /**
     * 股东大会名称
     */
    @XmlElement(name = "meetingdesc")
    private String meetingdesc;

    /**
     * 股东大会类型
     */
    @XmlElement(name = "meetingtype")
    private String meetingtype;

    /**
     *
     */
    @XmlElement(name = "votecodelist")
    private List<XmlSZVoteCodeDto> votecodelist;

    /**
     *
     */
    @XmlElement(name = "volist")
    private List<XmlSZVoteDto> volist;

    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    public String getCompanySymbol() {
        return companySymbol;
    }

    public void setCompanySymbol(String companySymbol) {
        this.companySymbol = companySymbol;
    }

    public String getMeetingSeq() {
        return meetingSeq;
    }

    public void setMeetingSeq(String meetingSeq) {
        this.meetingSeq = meetingSeq;
    }

    public String getMeetingdatebegin() {
        return meetingdatebegin;
    }

    public void setMeetingdatebegin(String meetingdatebegin) {
        this.meetingdatebegin = meetingdatebegin;
    }

    public String getMeetingdateend() {
        return meetingdateend;
    }

    public void setMeetingdateend(String meetingdateend) {
        this.meetingdateend = meetingdateend;
    }

    public String getMeetingdesc() {
        return meetingdesc;
    }

    public void setMeetingdesc(String meetingdesc) {
        this.meetingdesc = meetingdesc;
    }

    public String getMeetingtype() {
        return meetingtype;
    }

    public void setMeetingtype(String meetingtype) {
        this.meetingtype = meetingtype;
    }

    public List<XmlSZVoteCodeDto> getVotecodelist() {
        return votecodelist;
    }

    public void setVotecodelist(List<XmlSZVoteCodeDto> votecodelist) {
        this.votecodelist = votecodelist;
    }

    public List<XmlSZVoteDto> getVolist() {
        return volist;
    }

    public void setVolist(List<XmlSZVoteDto> volist) {
        this.volist = volist;
    }
}
