package com.joyintech.tams.exchange.common.bean.cache;

import java.io.Serializable;

public class MasterToken implements Serializable {
    private String masterAddress;
    private long refreshTime;

    public String getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
}
