package com.joyintech.tams.common.base.entity;

public class RskValtblSumsubMapDtl extends RskValtblSumsubMapDtlKey {
    /**
     * 源汇总科目名称
     */
    private String sumName;

    /**
     * 是否有下一列指标(Y:是,N:否)
     */
    private String hasNextIndex;

    /**
     * 下一指标数据类型
     */
    private String nextIndexType;

    /**
     * 取得 源汇总科目名称
     * @return 源汇总科目名称
     */
    public String getSumName() {
        return sumName;
    }

    /**
     * 设置 源汇总科目名称
     * @param sumName 源汇总科目名称
     */
    public void setSumName(String sumName) {
        this.sumName = sumName == null ? null : sumName.trim();
    }

    /**
     * 取得 是否有下一列指标(Y:是,N:否)
     * @return 是否有下一列指标(Y:是,N:否)
     */
    public String getHasNextIndex() {
        return hasNextIndex;
    }

    /**
     * 设置 是否有下一列指标(Y:是,N:否)
     * @param hasNextIndex 是否有下一列指标(Y:是,N:否)
     */
    public void setHasNextIndex(String hasNextIndex) {
        this.hasNextIndex = hasNextIndex == null ? null : hasNextIndex.trim();
    }

    /**
     * 取得 下一指标数据类型
     * @return 下一指标数据类型
     */
    public String getNextIndexType() {
        return nextIndexType;
    }

    /**
     * 设置 下一指标数据类型
     * @param nextIndexType 下一指标数据类型
     */
    public void setNextIndexType(String nextIndexType) {
        this.nextIndexType = nextIndexType == null ? null : nextIndexType.trim();
    }
}