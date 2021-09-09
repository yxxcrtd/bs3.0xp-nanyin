package com.joyintech.tams.exchange.terminal.trade.jsd.protocol;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;

public class MessageBean {
    private ProtocolInfo protocolInfo;
    private int reqId;
    private String functionCode;
    private boolean isHandledZeroPackage;
    private int count;
    private int totalCount;
    public char isTotal;

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public boolean isHandledZeroPackage() {
        return isHandledZeroPackage;
    }

    public void setHandledZeroPackage(boolean handledZeroPackage) {
        isHandledZeroPackage = handledZeroPackage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ProtocolInfo getProtocolInfo() {
        return protocolInfo;
    }

    public void setProtocolInfo(ProtocolInfo protocolInfo) {
        this.protocolInfo = protocolInfo;
    }

    public char getIsTotal() {
        return isTotal;
    }

    public void setIsTotal(char isTotal) {
        this.isTotal = isTotal;
    }

}
