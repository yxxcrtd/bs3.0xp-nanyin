package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.quoteloginfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class FxQuoteLogInfoDto extends BaseEntity {


    private static final long serialVersionUID = 1L;

    /**外部报价编号*/
    private String quoteid;
    /**内部报价编号*/
    private String nbquoteid;
    /**发送类型*/
    private String sendtype;
    /**报价类型*/
    private String quotetype;
    /**报文内容*/
    private String message;
    //新建人员
    private String creatorid;

    //新建时间
    private Date createdtime;

    //更新人员
    private String lastmodifierid;

    //更新时间
    private Date lastmodifiedtime;

    public String getQuoteid() {
        return quoteid;
    }

    public void setQuoteid(String quoteid) {
        this.quoteid = quoteid;
    }

    public String getNbquoteid() {
        return nbquoteid;
    }

    public void setNbquoteid(String nbquoteid) {
        this.nbquoteid = nbquoteid;
    }

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
    }

    public String getQuotetype() {
        return quotetype;
    }

    public void setQuotetype(String quotetype) {
        this.quotetype = quotetype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
