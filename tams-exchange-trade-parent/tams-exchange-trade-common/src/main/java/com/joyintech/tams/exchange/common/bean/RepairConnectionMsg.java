package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.net.protocol.FunctionCode;

public class RepairConnectionMsg {

    private FunctionCode functionCode;
    private long requestId;
    private int dateType;
    private Object obj;

    public RepairConnectionMsg() {
    }


    public RepairConnectionMsg(FunctionCode functionCode, long requestId, int dateType, Object obj) {
        this.functionCode = functionCode;
        this.requestId = requestId;
        this.dateType = dateType;
        this.obj = obj;
    }

    public FunctionCode getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(FunctionCode functionCode) {
        this.functionCode = functionCode;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
