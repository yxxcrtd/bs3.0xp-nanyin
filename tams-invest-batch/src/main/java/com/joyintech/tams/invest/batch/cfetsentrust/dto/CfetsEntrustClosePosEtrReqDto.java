package com.joyintech.tams.invest.batch.cfetsentrust.dto;

/**
 * 头寸信息查询，请求参数
 */
public class CfetsEntrustClosePosEtrReqDto {
    /**
     * 业务类型
     */
    private String busiType;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 关闭编号
     */
    private String closeId;
    /**
     * 系统动作
     */
    private String systemAct;
    /**
     * 业务动作
     */
    private String busiAct;

    /**
     * 金融产品类型
     * @return
     */
    private String finprodType2;

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSystemAct() {
        return systemAct;
    }

    public void setSystemAct(String systemAct) {
        this.systemAct = systemAct;
    }

    public String getBusiAct() {
        return busiAct;
    }

    public void setBusiAct(String busiAct) {
        this.busiAct = busiAct;
    }

    public String getCloseId() {
        return closeId;
    }

    public void setCloseId(String closeId) {
        this.closeId = closeId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
}
