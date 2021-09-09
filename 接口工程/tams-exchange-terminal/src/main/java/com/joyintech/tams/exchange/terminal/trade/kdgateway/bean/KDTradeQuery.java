package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDTradeQuery {    
    private String USER_CODE;
    private String MARKETS;
    private String SECU_ACC;
    private String SECU_CODE;
    private String TRD_ID;
    private String ORDER_ID;
    private String BIZ_NO;
    private String BRANCH;
    private String ACCOUNT;
    private String EXT_INST;
    private String BOARDS;
    private String RECORD_SN;//记录数
    private String R_COUNT;//本次查询笔数
    private String IS_WITHDRAW;//是否撤单
    private String SECU_CLS;//证券类别
    private String IS_FILTER_HKT;//是否过滤港股通
    private String OUT_ORDER_FLAG;//数据返回标志

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getMARKETS() {
        return MARKETS;
    }

    public void setMARKETS(String MARKETS) {
        this.MARKETS = MARKETS;
    }

    public String getSECU_ACC() {
        return SECU_ACC;
    }

    public void setSECU_ACC(String SECU_ACC) {
        this.SECU_ACC = SECU_ACC;
    }

    public String getSECU_CODE() {
        return SECU_CODE;
    }

    public void setSECU_CODE(String SECU_CODE) {
        this.SECU_CODE = SECU_CODE;
    }

    public String getTRD_ID() {
        return TRD_ID;
    }

    public void setTRD_ID(String TRD_ID) {
        this.TRD_ID = TRD_ID;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public String getBIZ_NO() {
        return BIZ_NO;
    }

    public void setBIZ_NO(String BIZ_NO) {
        this.BIZ_NO = BIZ_NO;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public String getEXT_INST() {
        return EXT_INST;
    }

    public void setEXT_INST(String EXT_INST) {
        this.EXT_INST = EXT_INST;
    }

    public String getBOARDS() {
        return BOARDS;
    }

    public void setBOARDS(String BOARDS) {
        this.BOARDS = BOARDS;
    }

    public String getRECORD_SN() {
        return RECORD_SN;
    }

    public void setRECORD_SN(String RECORD_SN) {
        this.RECORD_SN = RECORD_SN;
    }

    public String getR_COUNT() {
        return R_COUNT;
    }

    public void setR_COUNT(String r_COUNT) {
        R_COUNT = r_COUNT;
    }

    public String getIS_WITHDRAW() {
        return IS_WITHDRAW;
    }

    public void setIS_WITHDRAW(String IS_WITHDRAW) {
        this.IS_WITHDRAW = IS_WITHDRAW;
    }

    public String getSECU_CLS() {
        return SECU_CLS;
    }

    public void setSECU_CLS(String SECU_CLS) {
        this.SECU_CLS = SECU_CLS;
    }

    public String getIS_FILTER_HKT() {
        return IS_FILTER_HKT;
    }

    public void setIS_FILTER_HKT(String IS_FILTER_HKT) {
        this.IS_FILTER_HKT = IS_FILTER_HKT;
    }

    public String getOUT_ORDER_FLAG() {
        return OUT_ORDER_FLAG;
    }

    public void setOUT_ORDER_FLAG(String OUT_ORDER_FLAG) {
        this.OUT_ORDER_FLAG = OUT_ORDER_FLAG;
    }
}
