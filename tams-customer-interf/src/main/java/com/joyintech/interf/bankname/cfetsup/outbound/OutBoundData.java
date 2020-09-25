package com.joyintech.interf.bankname.cfetsup.outbound;


import com.joyintech.interf.bankname.cfetsup.outbound.dto.ICfetsDto;

public class OutBoundData  {
    private String seqno;
    private String operId;
    private String classSimpleName;
    private String businessType;
    private String isResend;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getClassSimpleName() {
        return classSimpleName;
    }

    public void setClassSimpleName(String classSimpleName) {
        this.classSimpleName = classSimpleName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getIsResend() {
        return isResend;
    }

    public void setIsResend(String isResend) {
        this.isResend = isResend;
    }
}
