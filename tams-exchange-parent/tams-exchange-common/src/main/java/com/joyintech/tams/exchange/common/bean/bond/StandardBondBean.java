package com.joyintech.tams.exchange.common.bean.bond;

import java.io.Serializable;

public class StandardBondBean implements Serializable {
    private int szStandardBondTotal;//深证标准券可用总数量
    private int shStandardBondTotal;//上海标准券可用总数量

    public int getSzStandardBondTotal() {
        return szStandardBondTotal;
    }

    public void setSzStandardBondTotal(int szStandardBondTotal) {
        this.szStandardBondTotal = szStandardBondTotal;
    }

    public int getShStandardBondTotal() {
        return shStandardBondTotal;
    }

    public void setShStandardBondTotal(int shStandardBondTotal) {
        this.shStandardBondTotal = shStandardBondTotal;
    }
}
