package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName ReissueEntrustQryBean
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/28
 * @Version
 **/
public class ReissueEntrustBean {

    //委托ID
    private String id;

    //localEntrustId
    private String localEntrustId;

    //柜台委托ID
    private String brokerEntrustId;

    //委托状态
    private int entrustStatus;

    //业务类型用以识别普通
    private String entrustProp;

    private boolean isBulk;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalEntrustId() {
        return localEntrustId;
    }

    public void setLocalEntrustId(String localEntrustId) {
        this.localEntrustId = localEntrustId;
    }

    public String getBrokerEntrustId() {
        return brokerEntrustId;
    }

    public void setBrokerEntrustId(String brokerEntrustId) {
        this.brokerEntrustId = brokerEntrustId;
    }

    public int getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(int entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public boolean isBulk() {
        return isBulk;
    }

    public void setBulk(boolean bulk) {
        isBulk = bulk;
    }
}
