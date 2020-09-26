package com.joyintech.tams.report.interf.message.instrument.dto;


/**
 * Created by Administrator on 2017/7/14 0014.
 * 单笔短信转发实体类（01）
 */
public class Message820001Entity {
    //报文长度
    private String packlen;
    //交易码
    private String tradecode;
    //渠道代码
    private String chnlCd;
    //签约机构Id
    private String sgnBrchId;
    //实时标志
    private String rlTmFlg;
    //预约时间戳
    private String bookTmStmp;
    //客户号
    private String clientNo;
    //账号
    private String acctNo;
    //手机号码
    private String mobileNo;
    //消息内容
    private String msgCntnt;

    public String getPacklen() {
        return packlen;
    }

    public void setPacklen(String packlen) {
        this.packlen = packlen;
    }

    public String getTradecode() {
        return tradecode;
    }

    public void setTradecode(String tradecode) {
        this.tradecode = tradecode;
    }


    public String getChnlCd() {
        return chnlCd;
    }

    public void setChnlCd(String chnlCd) {
        this.chnlCd = chnlCd;
    }


    public String getSgnBrchId() {
        return sgnBrchId;
    }

    public void setSgnBrchId(String sgnBrchId) {
        this.sgnBrchId = sgnBrchId;
    }


    public String getRlTmFlg() {
        return rlTmFlg;
    }

    public void setRlTmFlg(String rlTmFlg) {
        this.rlTmFlg = rlTmFlg;
    }


    public String getBookTmStmp() {
        return bookTmStmp;
    }

    public void setBookTmStmp(String bookTmStmp) {
        this.bookTmStmp = bookTmStmp;
    }


    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }


    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }


    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public String getMsgCntnt() {
        return msgCntnt;
    }

    public void setMsgCntnt(String msgCntnt) {
        this.msgCntnt = msgCntnt;
    }
}
