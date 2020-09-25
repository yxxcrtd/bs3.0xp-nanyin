package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValInterfServerInfoKey extends BaseEntity {
    /**
     * 服务器IP
     */
    private String serverip;

    /**
     * 资管系统：ZG
     */
    private String systemid;

    /**
     * 发送:S 和 接收:R
     */
    private String rs;

    /**
     * 取得 服务器IP
     * @return 服务器IP
     */
    public String getServerip() {
        return serverip;
    }

    /**
     * 设置 服务器IP
     * @param serverip 服务器IP
     */
    public void setServerip(String serverip) {
        this.serverip = serverip == null ? null : serverip.trim();
    }

    /**
     * 取得 资管系统：ZG
     * @return 资管系统：ZG
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * 设置 资管系统：ZG
     * @param systemid 资管系统：ZG
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }

    /**
     * 取得 发送:S 和 接收:R
     * @return 发送:S 和 接收:R
     */
    public String getRs() {
        return rs;
    }

    /**
     * 设置 发送:S 和 接收:R
     * @param rs 发送:S 和 接收:R
     */
    public void setRs(String rs) {
        this.rs = rs == null ? null : rs.trim();
    }
}