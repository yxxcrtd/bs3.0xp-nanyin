package com.joyintech.tams.exchange.common.bean.risk;

import java.io.Serializable;

public class EntrustCountBean implements Serializable {
    private long cancelCount = 0;
    private int totalCount = 0;

    public long getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(long cancelCount) {
        this.cancelCount = cancelCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
