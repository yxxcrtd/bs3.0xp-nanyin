package com.joyintech.tams.common.base.entity;

public class SttStressTips extends SttStressTipsKey {
    /**
     * 标签值
     */
    private String tipsValue;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 取得 标签值
     * @return 标签值
     */
    public String getTipsValue() {
        return tipsValue;
    }

    /**
     * 设置 标签值
     * @param tipsValue 标签值
     */
    public void setTipsValue(String tipsValue) {
        this.tipsValue = tipsValue == null ? null : tipsValue.trim();
    }

    /**
     * 取得 字典代码
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }
}