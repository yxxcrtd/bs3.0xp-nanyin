package com.joyintech.tams.exchange.common.bean;

public class MonitorBean {
    private String recordTime;

    private Integer recordValue;

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(Integer recordValue) {
        this.recordValue = recordValue;
    }

    public MonitorBean(String recordTime, Integer recordValue) {
        this.recordTime = recordTime;
        this.recordValue = recordValue;
    }
}
