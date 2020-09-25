package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quotesendinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class FxQuoteSendInfoDto extends BaseEntity {

    //流水号
    private String seqno;

    //报价流水号
    private String pseqno;

    /**内部报价编号*/
    private String nbquoteid;

    //报价类型
    private String quotetype;

    //业务类型
    private String businesstype;

    //发送状态
    private String sendstatus;

    //错误代码
    private String errorcode;

    //错误描述
    private String errormessage;

    //新建人员
    private String creatorid;

    //新建时间
    private Date createdtime;

    //更新人员
    private String lastmodifierid;

    //更新时间
    private Date lastmodifiedtime;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getPseqno() {
        return pseqno;
    }

    public void setPseqno(String pseqno) {
        this.pseqno = pseqno;
    }

    public String getNbquoteid() {
        return nbquoteid;
    }

    public void setNbquoteid(String nbquoteid) {
        this.nbquoteid = nbquoteid;
    }

    public String getQuotetype() {
        return quotetype;
    }

    public void setQuotetype(String quotetype) {
        this.quotetype = quotetype;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getLastmodifierid() {
        return lastmodifierid;
    }

    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }



}
