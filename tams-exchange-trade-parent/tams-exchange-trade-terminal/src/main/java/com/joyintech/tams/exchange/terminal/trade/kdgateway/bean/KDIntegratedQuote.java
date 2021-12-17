package com.joyintech.tams.exchange.terminal.trade.kdgateway.bean;

public class KDIntegratedQuote {
    private String YXSBXH;//意向申报序号
    private String YXZQDM;//证券代码
    private String YXMMLB;//买卖类别
    private String YXLSH;//意向流水号
    private String YXYHDM;//用户代码
    private String YXJLZT;//记录状态

    // 协议回购非公开报价行情
    private String ORDER_TYPE;//报价类型
    private String TRADER_OUT;//对手方交易员代码
    private String SECU_CODE;//质押券代码
    private String ORDER_QTY;//质押券数量
    private String RATE;//折算比例
    private String PRICE;//回购利率
    private String REPURCHASE_TERM;//回购期限

    public String getYXSBXH() {
        return YXSBXH;
    }

    public void setYXSBXH(String YXSBXH) {
        this.YXSBXH = YXSBXH;
    }

    public String getYXZQDM() {
        return YXZQDM;
    }

    public void setYXZQDM(String YXZQDM) {
        this.YXZQDM = YXZQDM;
    }

    public String getYXMMLB() {
        return YXMMLB;
    }

    public void setYXMMLB(String YXMMLB) {
        this.YXMMLB = YXMMLB;
    }

    public String getYXLSH() {
        return YXLSH;
    }

    public void setYXLSH(String YXLSH) {
        this.YXLSH = YXLSH;
    }

    public String getYXYHDM() {
        return YXYHDM;
    }

    public void setYXYHDM(String YXYHDM) {
        this.YXYHDM = YXYHDM;
    }

    public String getYXJLZT() {
        return YXJLZT;
    }

    public void setYXJLZT(String YXJLZT) {
        this.YXJLZT = YXJLZT;
    }

    public String getORDER_TYPE() {
        return ORDER_TYPE;
    }

    public void setORDER_TYPE(String ORDER_TYPE) {
        this.ORDER_TYPE = ORDER_TYPE;
    }

    public String getTRADER_OUT() {
        return TRADER_OUT;
    }

    public void setTRADER_OUT(String TRADER_OUT) {
        this.TRADER_OUT = TRADER_OUT;
    }

    public String getSECU_CODE() {
        return SECU_CODE;
    }

    public void setSECU_CODE(String SECU_CODE) {
        this.SECU_CODE = SECU_CODE;
    }

    public String getORDER_QTY() {
        return ORDER_QTY;
    }

    public void setORDER_QTY(String ORDER_QTY) {
        this.ORDER_QTY = ORDER_QTY;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getREPURCHASE_TERM() {
        return REPURCHASE_TERM;
    }

    public void setREPURCHASE_TERM(String REPURCHASE_TERM) {
        this.REPURCHASE_TERM = REPURCHASE_TERM;
    }
}
