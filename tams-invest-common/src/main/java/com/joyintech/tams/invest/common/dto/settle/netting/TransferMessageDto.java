package com.joyintech.tams.invest.common.dto.settle.netting;

import com.joyintech.framework.base.BaseEntity;

/**
 * @author 姚轶文
 * @create 2019- 11-19 15:12
 */
public class TransferMessageDto extends BaseEntity {

    private String orderid; //业务编号
    private String portid; //组合代码
    private String portname; //组合名称
    private String send; //是否发送指令
    private String sdate; //支付日
    private String amt; //划款金额
    private String ccy; //币种
    private String payno; //付款账号
    private String payname; //付款账号名称
    private String gatno; //收款账号
    private String gatname; //收款账号名称
    private String gatpayno; //大额支付系统号/网点号
    private String status; //指令发送状态
    private String purpose; //用途
    private String remark; //备注

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPortid() {
        return portid;
    }

    public void setPortid(String portid) {
        this.portid = portid;
    }

    public String getPortname() {
        return portname;
    }

    public void setPortname(String portname) {
        this.portname = portname;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getGatno() {
        return gatno;
    }

    public void setGatno(String gatno) {
        this.gatno = gatno;
    }

    public String getGatname() {
        return gatname;
    }

    public void setGatname(String gatname) {
        this.gatname = gatname;
    }

    public String getGatpayno() {
        return gatpayno;
    }

    public void setGatpayno(String gatpayno) {
        this.gatpayno = gatpayno;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
