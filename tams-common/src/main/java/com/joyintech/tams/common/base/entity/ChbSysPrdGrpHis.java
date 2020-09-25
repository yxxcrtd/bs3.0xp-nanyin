package com.joyintech.tams.common.base.entity;

public class ChbSysPrdGrpHis extends ChbSysPrdGrpHisKey {
    /**
     * ������
     */
    private String prdGrpName;

    /**
     * 取得 ������
     * @return ������
     */
    public String getPrdGrpName() {
        return prdGrpName;
    }

    /**
     * 设置 ������
     * @param prdGrpName ������
     */
    public void setPrdGrpName(String prdGrpName) {
        this.prdGrpName = prdGrpName == null ? null : prdGrpName.trim();
    }
}