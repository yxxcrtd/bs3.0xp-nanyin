package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.*;

public interface SSEBinaryGateway {
    /**
     * 心跳消息
     *
     * @param heartbeat 心跳消息
     */
    void onHeartbeat(SSEBinaryHeartbeat heartbeat);

    /**
     * 已连接
     *
     * @param message 连接消息
     */
    void connected(String message);

    /**
     * 连接断开
     *
     * @param message msg
     */
    void disconnected(String message);

    /**
     * 登陆成功消息
     *
     * @param logon msg
     */
    void onLogon(SSEBinaryLogon logon);

    /**
     * 登出成功消息
     *
     * @param logout msg
     */
    void onLogout(SSEBinaryLogout logout);

    /**
     * level 行情推送
     *
     * @param level1MarketSnapshot level1 msg;
     */
    void onLevel1Market(SSEBinaryLevel1MarketSnapshot level1MarketSnapshot);
}
