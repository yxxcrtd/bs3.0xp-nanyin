package com.joyintech.tams.common.base.entity;

public class InterfHttpLogWithBLOBs extends InterfHttpLog {
    /**
     * 发送的字符串
     */
    private String sendString;

    /**
     * 接收的字符串
     */
    private String acceptString;

    /**
     * 取得 发送的字符串
     * @return 发送的字符串
     */
    public String getSendString() {
        return sendString;
    }

    /**
     * 设置 发送的字符串
     * @param sendString 发送的字符串
     */
    public void setSendString(String sendString) {
        this.sendString = sendString == null ? null : sendString.trim();
    }

    /**
     * 取得 接收的字符串
     * @return 接收的字符串
     */
    public String getAcceptString() {
        return acceptString;
    }

    /**
     * 设置 接收的字符串
     * @param acceptString 接收的字符串
     */
    public void setAcceptString(String acceptString) {
        this.acceptString = acceptString == null ? null : acceptString.trim();
    }
}