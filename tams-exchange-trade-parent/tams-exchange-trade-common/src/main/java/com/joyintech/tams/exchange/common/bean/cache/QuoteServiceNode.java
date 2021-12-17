package com.joyintech.tams.exchange.common.bean.cache;

import com.joyintech.tams.exchange.common.db.domain.KeyMethodInterface;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class QuoteServiceNode implements Serializable, KeyMethodInterface<String> {
    private static final long serialVersionUID = 8746162803409799734L;
    private String nodeName;

    private int nodeWeight;

    private int interval;

    private long lastLiveTime;

    @Id
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public int getNodeWeight() {
        return nodeWeight;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setNodeWeight(int nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    public long getLastLiveTime() {
        return lastLiveTime;
    }

    public void setLastLiveTime(long lastLiveTime) {
        this.lastLiveTime = lastLiveTime;
    }

    @Override
    public String getId() {
        return nodeName;
    }

    @Override
    public void setId(String id) {
        this.nodeName = nodeName;
    }
}
