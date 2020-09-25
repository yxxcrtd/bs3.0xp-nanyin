package com.joyintech.tams.common.base.entity;

public class RskValtblStructMapDtl extends RskValtblStructMapDtlKey {
    /**
     * 列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)

     */
    private String srcColDataType;

    /**
     * 指标

     */
    private String tgtIndex;

    /**
     * 描述

     */
    private String memo;

    /**
     * 取得 列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)

     * @return 列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)

     */
    public String getSrcColDataType() {
        return srcColDataType;
    }

    /**
     * 设置 列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)

     * @param srcColDataType 列数据类型(STRING:字符,NUMBER:数值,PERCENT:百分比)

     */
    public void setSrcColDataType(String srcColDataType) {
        this.srcColDataType = srcColDataType == null ? null : srcColDataType.trim();
    }

    /**
     * 取得 指标

     * @return 指标

     */
    public String getTgtIndex() {
        return tgtIndex;
    }

    /**
     * 设置 指标

     * @param tgtIndex 指标

     */
    public void setTgtIndex(String tgtIndex) {
        this.tgtIndex = tgtIndex == null ? null : tgtIndex.trim();
    }

    /**
     * 取得 描述

     * @return 描述

     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 描述

     * @param memo 描述

     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}