package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSHCompanyDto {
    /**
     * 公司资产代码
     */
    @XmlElement(name = "companycode")
    private String companycode;

    /**
     * 公司资产名称
     */
    @XmlElement(name = "companyinfo")
    private String companyinfo;

    /**
     * 股东大会编号
     */
    @XmlElement(name = "meetingseq")
    private String meetingseq;

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
    private List<XmlSHVoteCodeDto> votecodelist;

    /**
     *
     */
    @XmlElement(name = "volist")
    private List<XmlSHVoteDto> volist;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCompanyinfo() {
        return companyinfo;
    }

    public void setCompanyinfo(String companyinfo) {
        this.companyinfo = companyinfo;
    }

    public String getMeetingseq() {
        return meetingseq;
    }

    public void setMeetingseq(String meetingseq) {
        this.meetingseq = meetingseq;
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

    public List<XmlSHVoteCodeDto> getVotecodelist() {
        return votecodelist;
    }

    public void setVotecodelist(List<XmlSHVoteCodeDto> votecodelist) {
        this.votecodelist = votecodelist;
    }

    public List<XmlSHVoteDto> getVolist() {
        return volist;
    }

    public void setVolist(List<XmlSHVoteDto> volist) {
        this.volist = volist;
    }
}
