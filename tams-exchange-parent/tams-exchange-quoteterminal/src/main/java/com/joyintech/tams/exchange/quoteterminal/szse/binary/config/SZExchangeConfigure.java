package com.joyintech.tams.exchange.quoteterminal.szse.binary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "szse.binary")
public class SZExchangeConfigure {
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private int port;
    /**
     * 密码
     */
    private String password;
    /**
     * 心跳间隔
     */
    private int heartbeatInterval;
    /**
     * 目标编号
     */
    private String targetCompId;
    /**
     * 发送方编号
     */
    private String senderCompId;
    /**
     * 版本信息
     */
    private String version;
    /**
     * 重连次数
     */
    private int reconnect;
    /**
     * 重连间隔
     */
    private int reconnectInterval;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public String getTargetCompId() {
        return targetCompId;
    }

    public void setTargetCompId(String targetCompId) {
        this.targetCompId = targetCompId;
    }

    public String getSenderCompId() {
        return senderCompId;
    }

    public void setSenderCompId(String senderCompId) {
        this.senderCompId = senderCompId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getReconnect() {
        return reconnect;
    }

    public void setReconnect(int reconnect) {
        this.reconnect = reconnect;
    }

    public int getReconnectInterval() {
        return reconnectInterval;
    }

    public void setReconnectInterval(int reconnectInterval) {
        this.reconnectInterval = reconnectInterval;
    }

    @Override
    public String toString() {
        return "SZExchangeConfigure{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", heartbeatInterval=" + heartbeatInterval +
                ", targetCompId='" + targetCompId + '\'' +
                ", senderCompId='" + senderCompId + '\'' +
                ", version='" + version + '\'' +
                ", reconnect=" + reconnect +
                ", reconnectInterval=" + reconnectInterval +
                '}';
    }
}
