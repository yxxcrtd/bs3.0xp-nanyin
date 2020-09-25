package com.joyintech.tams.common.base.entity;

public class RskValtblAssetSubMapDtl extends RskValtblAssetSubMapDtlKey {
    /**
     * 目标科目代码

     */
    private String tgtCode;

    /**
     * 是否允许汇总(Y:是,N:否)

     */
    private String allowSum;

    /**
     * 描述

     */
    private String memo;

    /**
     * 取得 目标科目代码

     * @return 目标科目代码

     */
    public String getTgtCode() {
        return tgtCode;
    }

    /**
     * 设置 目标科目代码

     * @param tgtCode 目标科目代码

     */
    public void setTgtCode(String tgtCode) {
        this.tgtCode = tgtCode == null ? null : tgtCode.trim();
    }

    /**
     * 取得 是否允许汇总(Y:是,N:否)

     * @return 是否允许汇总(Y:是,N:否)

     */
    public String getAllowSum() {
        return allowSum;
    }

    /**
     * 设置 是否允许汇总(Y:是,N:否)

     * @param allowSum 是否允许汇总(Y:是,N:否)

     */
    public void setAllowSum(String allowSum) {
        this.allowSum = allowSum == null ? null : allowSum.trim();
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