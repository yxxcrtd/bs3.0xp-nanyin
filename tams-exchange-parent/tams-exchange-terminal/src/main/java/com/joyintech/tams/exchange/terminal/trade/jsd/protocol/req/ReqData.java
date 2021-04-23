package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;

public class ReqData {
    public String sourceAddr = "";
    public String reqId;
    public String functionCode;
    public String yybId;
    public String entrustType;
    public int maxPackageLen = 5534;
    public int maxPackageNum = 100;

    public ReqData(String sourceAddr, String reqId, String functionCode, String yybId, String entrustType) {
        this.sourceAddr = sourceAddr;
        this.reqId = reqId;
        this.functionCode = functionCode;
        this.yybId = yybId;
        this.entrustType = entrustType;
    }

    public ReqData(String sourceAddr, String reqId, String functionCode, String yybId,
                   String entrustType, int maxPackageLen, int maxPackageNum) {
        this(sourceAddr, reqId, functionCode, yybId, entrustType);
        this.maxPackageNum = maxPackageNum;
        this.maxPackageLen = maxPackageLen;
    }

    @Override
    public String toString() {
        String ret = "R|" + sourceAddr + "|" + reqId + "|" + functionCode + "|" + yybId + "|" + entrustType + "|";
        return functionCode.equals("0") ? ret : ret + maxPackageLen + "|" + maxPackageNum + "|";
    }
}
