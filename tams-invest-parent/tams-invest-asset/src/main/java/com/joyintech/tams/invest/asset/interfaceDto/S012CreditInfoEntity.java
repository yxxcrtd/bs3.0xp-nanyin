package com.joyintech.tams.invest.asset.interfaceDto;

public class S012CreditInfoEntity {
    private  String CREDIT_FLAG;//增信主体
    private  String  CREDIT_TYPE;//增信方式
    private  String REMARK;//备注

    public String getCREDIT_FLAG() {
        return CREDIT_FLAG;
    }

    public void setCREDIT_FLAG(String CREDIT_FLAG) {
        this.CREDIT_FLAG = CREDIT_FLAG;
    }

    public String getCREDIT_TYPE() {
        return CREDIT_TYPE;
    }

    public void setCREDIT_TYPE(String CREDIT_TYPE) {
        this.CREDIT_TYPE = CREDIT_TYPE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
}
