package com.joyintech.tams.common.base.entity;

public class SttStressScene extends SttStressSceneKey {
    /**
     * 压测业务类型，数据字典[压测业务类型]
     */
    private String sttBusiType;

    /**
     * 取得 压测业务类型，数据字典[压测业务类型]
     * @return 压测业务类型，数据字典[压测业务类型]
     */
    public String getSttBusiType() {
        return sttBusiType;
    }

    /**
     * 设置 压测业务类型，数据字典[压测业务类型]
     * @param sttBusiType 压测业务类型，数据字典[压测业务类型]
     */
    public void setSttBusiType(String sttBusiType) {
        this.sttBusiType = sttBusiType == null ? null : sttBusiType.trim();
    }
}