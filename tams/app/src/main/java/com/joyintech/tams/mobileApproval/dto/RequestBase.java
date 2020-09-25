package com.joyintech.tams.mobileApproval.dto;

/**
 * esb报文头
 * add by houjifei
 */
public class RequestBase {

    //交易流水号
    private String SEQ_NO;

    //交易号
    private String SERVICE_ID;

    //渠道代号
    private String CHANNEL_ID;

    //服务系统标识
    private String SERVER_ID;

    //机构号
    private String BANK_CODE;

    //操作员
    private String USER_ID;

    //授权操作员
    private String AUTH_ID;

    //请求日期
    private String TRAN_DATE;

    //请求时间
    private String TRAN_TIME;

    //认证信息
    private String AUTH_CONTEXT;

    //交易终端号
    private String TRAN_TERM;

    //扩展信息
    private String EXT_HEAD;

    //MAC索引号
    private String MAC_INDEX;

    //MAC值
    private String MAC_VALUE;

    //业务种类
    private String BUSINESS_ID;

    //PIN种子号
    private String PIN_SEED;

    //PIN索引号
    private String PIN_INDEX;

    public String getSEQ_NO() {
        return SEQ_NO;
    }

    public void setSEQ_NO(String SEQ_NO) {
        this.SEQ_NO = SEQ_NO;
    }

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getCHANNEL_ID() {
        return CHANNEL_ID;
    }

    public void setCHANNEL_ID(String CHANNEL_ID) {
        this.CHANNEL_ID = CHANNEL_ID;
    }

    public String getSERVER_ID() {
        return SERVER_ID;
    }

    public void setSERVER_ID(String SERVER_ID) {
        this.SERVER_ID = SERVER_ID;
    }

    public String getBANK_CODE() {
        return BANK_CODE;
    }

    public void setBANK_CODE(String BANK_CODE) {
        this.BANK_CODE = BANK_CODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getAUTH_ID() {
        return AUTH_ID;
    }

    public void setAUTH_ID(String AUTH_ID) {
        this.AUTH_ID = AUTH_ID;
    }

    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }

    public String getTRAN_TIME() {
        return TRAN_TIME;
    }

    public void setTRAN_TIME(String TRAN_TIME) {
        this.TRAN_TIME = TRAN_TIME;
    }

    public String getAUTH_CONTEXT() {
        return AUTH_CONTEXT;
    }

    public void setAUTH_CONTEXT(String AUTH_CONTEXT) {
        this.AUTH_CONTEXT = AUTH_CONTEXT;
    }

    public String getTRAN_TERM() {
        return TRAN_TERM;
    }

    public void setTRAN_TERM(String TRAN_TERM) {
        this.TRAN_TERM = TRAN_TERM;
    }

    public String getEXT_HEAD() {
        return EXT_HEAD;
    }

    public void setEXT_HEAD(String EXT_HEAD) {
        this.EXT_HEAD = EXT_HEAD;
    }

    public String getMAC_INDEX() {
        return MAC_INDEX;
    }

    public void setMAC_INDEX(String MAC_INDEX) {
        this.MAC_INDEX = MAC_INDEX;
    }

    public String getMAC_VALUE() {
        return MAC_VALUE;
    }

    public void setMAC_VALUE(String MAC_VALUE) {
        this.MAC_VALUE = MAC_VALUE;
    }

    public String getBUSINESS_ID() {
        return BUSINESS_ID;
    }

    public void setBUSINESS_ID(String BUSINESS_ID) {
        this.BUSINESS_ID = BUSINESS_ID;
    }

    public String getPIN_SEED() {
        return PIN_SEED;
    }

    public void setPIN_SEED(String PIN_SEED) {
        this.PIN_SEED = PIN_SEED;
    }

    public String getPIN_INDEX() {
        return PIN_INDEX;
    }

    public void setPIN_INDEX(String PIN_INDEX) {
        this.PIN_INDEX = PIN_INDEX;
    }
}
