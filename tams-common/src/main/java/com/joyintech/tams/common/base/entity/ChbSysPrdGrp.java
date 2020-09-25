package com.joyintech.tams.common.base.entity;

public class ChbSysPrdGrp extends ChbSysPrdGrpKey {
    /**
     * 产品组合名称
     */
    private String prdGrpName;

    /**
     * 取得 产品组合名称
     * @return 产品组合名称
     */
    public String getPrdGrpName() {
        return prdGrpName;
    }

    /**
     * 设置 产品组合名称
     * @param prdGrpName 产品组合名称
     */
    public void setPrdGrpName(String prdGrpName) {
        this.prdGrpName = prdGrpName == null ? null : prdGrpName.trim();
    }
}