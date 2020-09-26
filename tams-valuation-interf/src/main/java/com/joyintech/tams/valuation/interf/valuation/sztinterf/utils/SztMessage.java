package com.joyintech.tams.valuation.interf.valuation.sztinterf.utils;

import java.nio.charset.Charset;

import com.hex.connector.command.CommandMsg;
import com.hex.connector.util.StringReader;

public class SztMessage extends CommandMsg {
    public static final int MR2_MAXLEN_PKGID = 0x40;
    public static final int MR2_MAXLEN_USERDATA = 0x100;


    private String fromUserId;
    private String toUserId;
    private String fromAppId;
    private String toAppId;

    private String userData1="";
    private String userData2="";
    private String corrPkgId="";

    private byte ucBizType;
    private String expiredTime="";


    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromAppId() {
        return fromAppId;
    }

    public void setFromAppId(String fromAppId) {
        this.fromAppId = fromAppId;
    }

    public String getToAppId() {
        return toAppId;
    }

    public void setToAppId(String toAppId) {
        this.toAppId = toAppId;
    }

    public String getUserData1() {
        return userData1;
    }

    public void setUserData1(String userData1) {
        this.userData1 = userData1;
    }

    public String getUserData2() {
        return userData2;
    }

    public void setUserData2(String userData2) {
        this.userData2 = userData2;
    }

    public String getCorrPkgId() {
        return corrPkgId;
    }

    public void setCorrPkgId(String corrPkgId) {
        this.corrPkgId = corrPkgId;
    }

    public byte getUcBizType() {
        return ucBizType;
    }

    public void setUcBizType(byte ucBizType) {
        this.ucBizType = ucBizType;
    }

    public static final int MR2_MSG_MIN_LENGTH=0x20*4+0x40*2+0x100*2+4;


    public static SztMessage parseFrom(byte[] msgData, Charset charset) {
        if (msgData.length < MR2_MSG_MIN_LENGTH){
            return null;
        }
        StringReader reader=new StringReader(msgData);
        SztMessage msg=new SztMessage();

        msg.setId(reader.readStr(MR2_MAXLEN_PKGID,charset));
        msg.setCorrPkgId(reader.readStr(MR2_MAXLEN_PKGID, charset));
        
        msg.setFromUserId(reader.readStr(0x20, charset));
        msg.setFromAppId(reader.readStr(0x20, charset));
        msg.setToUserId(reader.readStr(0x20, charset));
        msg.setToAppId(reader.readStr(0x20, charset));

        msg.setUserData1(reader.readStr(MR2_MAXLEN_USERDATA, charset));
        msg.setUserData2(reader.readStr(MR2_MAXLEN_USERDATA, charset));

        msg.setBody(reader.readLastStr(charset));

        return msg;
    }


    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

}
