package com.joyintech.tams.common.base.entity;

public class ValInterfTemplate extends ValInterfTemplateKey {
    /**
     * 模版中文名称
     */
    private String tmpCName;

    /**
     * DTO名称
     */
    private String dtoName;

    /**
     * 模板类型
     */
    private String tmpType;

    /**
     * 系统类型
     */
    private String systemid;

    /**
     * 取得 模版中文名称
     * @return 模版中文名称
     */
    public String getTmpCName() {
        return tmpCName;
    }

    /**
     * 设置 模版中文名称
     * @param tmpCName 模版中文名称
     */
    public void setTmpCName(String tmpCName) {
        this.tmpCName = tmpCName == null ? null : tmpCName.trim();
    }

    /**
     * 取得 DTO名称
     * @return DTO名称
     */
    public String getDtoName() {
        return dtoName;
    }

    /**
     * 设置 DTO名称
     * @param dtoName DTO名称
     */
    public void setDtoName(String dtoName) {
        this.dtoName = dtoName == null ? null : dtoName.trim();
    }

    /**
     * 取得 模板类型
     * @return 模板类型
     */
    public String getTmpType() {
        return tmpType;
    }

    /**
     * 设置 模板类型
     * @param tmpType 模板类型
     */
    public void setTmpType(String tmpType) {
        this.tmpType = tmpType == null ? null : tmpType.trim();
    }

    /**
     * 取得 系统类型
     * @return 系统类型
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * 设置 系统类型
     * @param systemid 系统类型
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }
}