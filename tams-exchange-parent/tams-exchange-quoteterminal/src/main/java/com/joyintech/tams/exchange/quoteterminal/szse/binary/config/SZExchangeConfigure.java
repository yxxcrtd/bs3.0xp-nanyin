package com.joyintech.tams.exchange.quoteterminal.szse.binary.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${szse.binary.timeout:10000}")
    private int timeout;

    @Value("${szse.binary.buffer.recv:4194304}")
    private int nettyRecvBufferSize;

    @Value("${szse.binary.buffer.send:4194304}")
    private int nettySendBufferSize;

    @Value("${szse.binary.buffer.write.high:524288}")
    private int nettyWriteBufferHighKB;

    @Value("${szse.binary.buffer.write.low:32768}")
    private int nettyWriteBufferLowKB;

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

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getNettyRecvBufferSize() {
        return nettyRecvBufferSize;
    }

    public void setNettyRecvBufferSize(int nettyRecvBufferSize) {
        this.nettyRecvBufferSize = nettyRecvBufferSize;
    }

    public int getNettySendBufferSize() {
        return nettySendBufferSize;
    }

    public void setNettySendBufferSize(int nettySendBufferSize) {
        this.nettySendBufferSize = nettySendBufferSize;
    }

    public int getNettyWriteBufferHighKB() {
        return nettyWriteBufferHighKB;
    }

    public void setNettyWriteBufferHighKB(int nettyWriteBufferHighKB) {
        this.nettyWriteBufferHighKB = nettyWriteBufferHighKB;
    }

    public int getNettyWriteBufferLowKB() {
        return nettyWriteBufferLowKB;
    }

    public void setNettyWriteBufferLowKB(int nettyWriteBufferLowKB) {
        this.nettyWriteBufferLowKB = nettyWriteBufferLowKB;
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
