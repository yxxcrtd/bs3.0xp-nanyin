package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysActionlog extends BaseEntity {
    /**
     * 日志编号
     */
    private String logId;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 要素代码
     */
    private String elementId;

    /**
     * 客户端IP地址
     */
    private String clientIp;

    /**
     * 方法参数
     */
    private String methodPara;

    /**
     * 取得 日志编号
     * @return 日志编号
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置 日志编号
     * @param logId 日志编号
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 要素代码
     * @return 要素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 要素代码
     * @param elementId 要素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 客户端IP地址
     * @return 客户端IP地址
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 设置 客户端IP地址
     * @param clientIp 客户端IP地址
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    /**
     * 取得 方法参数
     * @return 方法参数
     */
    public String getMethodPara() {
        return methodPara;
    }

    /**
     * 设置 方法参数
     * @param methodPara 方法参数
     */
    public void setMethodPara(String methodPara) {
        this.methodPara = methodPara == null ? null : methodPara.trim();
    }
}