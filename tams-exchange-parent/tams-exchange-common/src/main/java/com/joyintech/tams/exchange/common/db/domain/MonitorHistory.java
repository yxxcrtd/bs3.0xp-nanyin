package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;
import java.util.Date;

public class MonitorHistory implements Serializable, KeyMethodInterface<String> {
    private String id;

    private String recordId;

    private String recordTime;

    private String recordName;

    private Integer recordValue;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime == null ? null : recordTime.trim();
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName == null ? null : recordName.trim();
    }

    public Integer getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(Integer recordValue) {
        this.recordValue = recordValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}