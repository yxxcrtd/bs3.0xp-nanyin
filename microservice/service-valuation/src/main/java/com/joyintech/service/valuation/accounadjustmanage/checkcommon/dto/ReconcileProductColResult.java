package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

public class ReconcileProductColResult extends ReconcileProductColResultKey {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 本系统数值
     */
    private String myData;

    /**
     * 对方系统数值
     */
    private String othersideData;

    /**
     * 是否比较差异
     */
    private String needcompare;

    /**
     * 差异结果
     */
    private String difference;

    /**
     * 取得 本系统数值
     * @return 本系统数值
     */
    public String getMyData() {
        return myData;
    }

    /**
     * 设置 本系统数值
     * @param myData 本系统数值
     */
    public void setMyData(String myData) {
        this.myData = myData == null ? null : myData.trim();
    }

    /**
     * 取得 对方系统数值
     * @return 对方系统数值
     */
    public String getOthersideData() {
        return othersideData;
    }

    /**
     * 设置 对方系统数值
     * @param othersideData 对方系统数值
     */
    public void setOthersideData(String othersideData) {
        this.othersideData = othersideData == null ? null : othersideData.trim();
    }

    /**
     * 取得 是否比较差异
     * @return 是否比较差异
     */
    public String getNeedcompare() {
        return needcompare;
    }

    /**
     * 设置 是否比较差异
     * @param needcompare 是否比较差异
     */
    public void setNeedcompare(String needcompare) {
        this.needcompare = needcompare == null ? null : needcompare.trim();
    }

    /**
     * 取得 差异结果
     * @return 差异结果
     */
    public String getDifference() {
        return difference;
    }

    /**
     * 设置 差异结果
     * @param difference 差异结果
     */
    public void setDifference(String difference) {
        this.difference = difference == null ? null : difference.trim();
    }
}