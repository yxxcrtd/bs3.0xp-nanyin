package com.joyintech.tams.common.base.entity;

public class InterfEsbLogWithBLOBs extends InterfEsbLog {
    /**
     * 发送数据
     */
    private String sendData;

    /**
     * 接收数据
     */
    private String receiveData;

    /**
     * 取得 发送数据
     * @return 发送数据
     */
    public String getSendData() {
        return sendData;
    }

    /**
     * 设置 发送数据
     * @param sendData 发送数据
     */
    public void setSendData(String sendData) {
        this.sendData = sendData == null ? null : sendData.trim();
    }

    /**
     * 取得 接收数据
     * @return 接收数据
     */
    public String getReceiveData() {
        return receiveData;
    }

    /**
     * 设置 接收数据
     * @param receiveData 接收数据
     */
    public void setReceiveData(String receiveData) {
        this.receiveData = receiveData == null ? null : receiveData.trim();
    }
}