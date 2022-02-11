package com.joyintech.tams.exchange.quoteterminal.sse.binary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sse.binary")
public class SSEExchangeConfigure {
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private int port;
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
                ", heartbeatInterval=" + heartbeatInterval +
                ", targetCompId='" + targetCompId + '\'' +
                ", senderCompId='" + senderCompId + '\'' +
                ", version='" + version + '\'' +
                ", reconnect=" + reconnect +
                ", reconnectInterval=" + reconnectInterval +
                '}';
    }
}
