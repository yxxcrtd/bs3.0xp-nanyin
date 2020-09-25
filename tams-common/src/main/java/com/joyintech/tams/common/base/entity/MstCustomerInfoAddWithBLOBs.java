package com.joyintech.tams.common.base.entity;

public class MstCustomerInfoAddWithBLOBs extends MstCustomerInfoAdd {
    /**
     * 经营范围
     */
    private String busiScope;

    /**
     * 主营业务
     */
    private String mainBusi;

    /**
     * 取得 经营范围
     * @return 经营范围
     */
    public String getBusiScope() {
        return busiScope;
    }

    /**
     * 设置 经营范围
     * @param busiScope 经营范围
     */
    public void setBusiScope(String busiScope) {
        this.busiScope = busiScope == null ? null : busiScope.trim();
    }

    /**
     * 取得 主营业务
     * @return 主营业务
     */
    public String getMainBusi() {
        return mainBusi;
    }

    /**
     * 设置 主营业务
     * @param mainBusi 主营业务
     */
    public void setMainBusi(String mainBusi) {
        this.mainBusi = mainBusi == null ? null : mainBusi.trim();
    }
}