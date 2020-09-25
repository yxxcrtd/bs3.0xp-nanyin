package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrSecPriorityDetail extends BaseEntity {
    /**
     * 设置编号
     */
    private String configId;

    /**
     * 资产一级分类，数据字典，WINDTYPEONE
     */
    private String windtypeone;

    /**
     * 资产二级分类，数据字典，WINDTYPETWO
     */
    private String windtypetwo;

    /**
     * 质押优先级
     */
    private String pledgePri;

    /**
     * 取得 设置编号
     * @return 设置编号
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 设置编号
     * @param configId 设置编号
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 资产一级分类，数据字典，WINDTYPEONE
     * @return 资产一级分类，数据字典，WINDTYPEONE
     */
    public String getWindtypeone() {
        return windtypeone;
    }

    /**
     * 设置 资产一级分类，数据字典，WINDTYPEONE
     * @param windtypeone 资产一级分类，数据字典，WINDTYPEONE
     */
    public void setWindtypeone(String windtypeone) {
        this.windtypeone = windtypeone == null ? null : windtypeone.trim();
    }

    /**
     * 取得 资产二级分类，数据字典，WINDTYPETWO
     * @return 资产二级分类，数据字典，WINDTYPETWO
     */
    public String getWindtypetwo() {
        return windtypetwo;
    }

    /**
     * 设置 资产二级分类，数据字典，WINDTYPETWO
     * @param windtypetwo 资产二级分类，数据字典，WINDTYPETWO
     */
    public void setWindtypetwo(String windtypetwo) {
        this.windtypetwo = windtypetwo == null ? null : windtypetwo.trim();
    }

    /**
     * 取得 质押优先级
     * @return 质押优先级
     */
    public String getPledgePri() {
        return pledgePri;
    }

    /**
     * 设置 质押优先级
     * @param pledgePri 质押优先级
     */
    public void setPledgePri(String pledgePri) {
        this.pledgePri = pledgePri == null ? null : pledgePri.trim();
    }
}