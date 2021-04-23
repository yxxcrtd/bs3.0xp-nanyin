package com.joyintech.tams.exchange.terminal.trade.jsd.protocol;

import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class MsgBean implements Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(MsgBean.class);

    private int msgType;
    private Long requestId;
    private RespLoginHeader respLoginHeader;
    private Object msg;
    private Object entrustMsg;
    private Map<Character, RespLoginData> dataMap;

    public MsgBean() {

    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }


    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RespLoginHeader getRespLoginHeader() {
        return respLoginHeader;
    }

    public void setRespLoginHeader(RespLoginHeader respLoginHeader) {
        this.respLoginHeader = respLoginHeader;
    }

    public Object getEntrustMsg() {
        return entrustMsg;
    }

    public void setEntrustMsg(Object entrustMsg) {
        this.entrustMsg = entrustMsg;
    }

    public Map<Character, RespLoginData> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<Character, RespLoginData> dataMap) {
        this.dataMap = dataMap;
    }

    public MsgBean clone() {
        try {
            return (MsgBean) super.clone();
        } catch (Exception e) {
            logger.warn("clone error.{}", e);
            return new MsgBean();
        }
    }
}
