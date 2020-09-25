package com.joyintech.tams.common.base.entity;

public class BokProductColResult extends BokProductColResultKey {
    /**
     * null
     */
    private String myData;

    /**
     * null
     */
    private String othersideData;

    /**
     * null
     */
    private String needcompare;

    /**
     * null
     */
    private String difference;

    /**
     * 取得 null
     * @return null
     */
    public String getMyData() {
        return myData;
    }

    /**
     * 设置 null
     * @param myData null
     */
    public void setMyData(String myData) {
        this.myData = myData == null ? null : myData.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOthersideData() {
        return othersideData;
    }

    /**
     * 设置 null
     * @param othersideData null
     */
    public void setOthersideData(String othersideData) {
        this.othersideData = othersideData == null ? null : othersideData.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getNeedcompare() {
        return needcompare;
    }

    /**
     * 设置 null
     * @param needcompare null
     */
    public void setNeedcompare(String needcompare) {
        this.needcompare = needcompare == null ? null : needcompare.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDifference() {
        return difference;
    }

    /**
     * 设置 null
     * @param difference null
     */
    public void setDifference(String difference) {
        this.difference = difference == null ? null : difference.trim();
    }
}