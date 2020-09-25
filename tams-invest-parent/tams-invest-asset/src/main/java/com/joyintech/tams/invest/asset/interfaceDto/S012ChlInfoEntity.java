package com.joyintech.tams.invest.asset.interfaceDto;

public class S012ChlInfoEntity {
    private  String CHL_ID;//通道代码
    private  String  CHL_NAME;//通道名称
    private  String REMARK;//备注

    public String getCHL_ID() {
        return CHL_ID;
    }

    public void setCHL_ID(String CHL_ID) {
        this.CHL_ID = CHL_ID;
    }

    public String getCHL_NAME() {
        return CHL_NAME;
    }

    public void setCHL_NAME(String CHL_NAME) {
        this.CHL_NAME = CHL_NAME;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
