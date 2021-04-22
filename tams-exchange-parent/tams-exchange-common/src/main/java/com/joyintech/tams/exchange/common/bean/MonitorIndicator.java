package com.joyintech.tams.exchange.common.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MonitorIndicator {
    private String categoryName;
    private int scheduleTime;//传入监控间隔，方便前端设置查询间隔
    private Date snapshotTime;
    private Map<String, List<MonitorBean>> recordListMap;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(int scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public Map<String, List<MonitorBean>> getRecordListMap() {
        return recordListMap;
    }

    public void setRecordListMap(Map<String, List<MonitorBean>> recordListMap) {
        this.recordListMap = recordListMap;
    }
}
