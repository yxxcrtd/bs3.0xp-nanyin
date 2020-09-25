package com.joyintech.tams.common.base.entity;

public class ValInterfDtoMatch extends ValInterfDtoMatchKey {
    /**
     * 模版中文名称
     */
    private String tmpCName;

    /**
     * 模版字段中文名
     */
    private String tmpColCName;

    /**
     * DTO字段英文名
     */
    private String dtoColEName;

    /**
     * DTO字段中文名
     */
    private String dtoColCName;

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
     * 取得 模版字段中文名
     * @return 模版字段中文名
     */
    public String getTmpColCName() {
        return tmpColCName;
    }

    /**
     * 设置 模版字段中文名
     * @param tmpColCName 模版字段中文名
     */
    public void setTmpColCName(String tmpColCName) {
        this.tmpColCName = tmpColCName == null ? null : tmpColCName.trim();
    }

    /**
     * 取得 DTO字段英文名
     * @return DTO字段英文名
     */
    public String getDtoColEName() {
        return dtoColEName;
    }

    /**
     * 设置 DTO字段英文名
     * @param dtoColEName DTO字段英文名
     */
    public void setDtoColEName(String dtoColEName) {
        this.dtoColEName = dtoColEName == null ? null : dtoColEName.trim();
    }

    /**
     * 取得 DTO字段中文名
     * @return DTO字段中文名
     */
    public String getDtoColCName() {
        return dtoColCName;
    }

    /**
     * 设置 DTO字段中文名
     * @param dtoColCName DTO字段中文名
     */
    public void setDtoColCName(String dtoColCName) {
        this.dtoColCName = dtoColCName == null ? null : dtoColCName.trim();
    }
}