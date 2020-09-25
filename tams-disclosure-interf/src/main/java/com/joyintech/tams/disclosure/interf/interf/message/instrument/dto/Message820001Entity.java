package com.joyintech.tams.disclosure.interf.interf.message.instrument.dto;


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
    private String ChnlCd;
    //签约机构Id
    private String SgnBrchId;
    //实时标志
    private String RlTmFlg;
    //预约时间戳
    private String BookTmStmp;
    //客户号
    private String ClientNo;
    //账号
    private String AcctNo;
    //手机号码
    private String MobileNo;
    //消息内容
    private String MsgCntnt;

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
        return ChnlCd;
    }

    public void setChnlCd(String chnlCd) {
        ChnlCd = chnlCd;
    }


    public String getSgnBrchId() {
        return SgnBrchId;
    }

    public void setSgnBrchId(String sgnBrchId) {
        SgnBrchId = sgnBrchId;
    }


    public String getRlTmFlg() {
        return RlTmFlg;
    }

    public void setRlTmFlg(String rlTmFlg) {
        RlTmFlg = rlTmFlg;
    }


    public String getBookTmStmp() {
        return BookTmStmp;
    }

    public void setBookTmStmp(String bookTmStmp) {
        BookTmStmp = bookTmStmp;
    }


    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }


    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }


    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }


    public String getMsgCntnt() {
        return MsgCntnt;
    }

    public void setMsgCntnt(String msgCntnt) {
        MsgCntnt = msgCntnt;
    }
}
