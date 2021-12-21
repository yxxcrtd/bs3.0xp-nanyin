package com.joyintech.tams.exchange.service.trade.util.http;

import java.util.Set;

/**
 * 请求参数
 */
public class HttpParameter {

    /**
     * 分组编码
     */
    private String gpcode;
    /**
     * 触发器编码
     */
    private String trigger;
    /**
     * 业务日期
     */
    private String bizdate;
    /**
     * 调用方日期
     */
    private String predate;
    /**
     * 调用方流水号-不超过36位
     */
    private String preseqno;
    /**
     * 路由器编码列表,不传表示执行触发器关联所有路由器
     */
    private Set<String> routers;
    /**
     * 附加参数
     */
    private Object parameter;
    /**
     * 是否异步执行,true:异步,false同步(调用callTimelyTrigger接口时只能是同步,该参数无效)
     */
    private boolean async;
    /**
     * 是否为批量任务接口
     */
    private boolean batch;
    /**
     * 重跑id
     */
    private Integer recallid;

    @SuppressWarnings("unchecked")
    public <T> T getParameter() {
        return (T) parameter;
    }

    public void setParameter(Object bean) {
        this.parameter = bean;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Set<String> getRouters() {
        return routers;
    }

    public void setRouters(Set<String> routers) {
        this.routers = routers;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getGpcode() {
        return gpcode;
    }

    public void setGpcode(String gpcode) {
        this.gpcode = gpcode;
    }

    public String getPredate() {
        return predate;
    }

    public void setPredate(String predate) {
        this.predate = predate;
    }

    public String getPreseqno() {
        return preseqno;
    }

    public void setPreseqno(String preseqno) {
        this.preseqno = preseqno;
    }

    public boolean isBatch() {
        return batch;
    }

    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    public Integer getRecallid() {
        return recallid;
    }

    public void setRecallid(Integer recallid) {
        this.recallid = recallid;
    }

    public String getBizdate() {
        return bizdate;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

}
