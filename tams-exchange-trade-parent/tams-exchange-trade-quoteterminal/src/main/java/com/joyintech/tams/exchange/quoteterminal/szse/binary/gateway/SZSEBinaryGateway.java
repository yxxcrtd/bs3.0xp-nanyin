package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.*;

public interface SZSEBinaryGateway {
    /**
     * 频道心跳
     *
     * @param channelHeartbeat 频道心跳
     */
    void onChannelHeartbeat(SZSEBinaryMarketChannelHeartbeat channelHeartbeat);

    /**
     * 心跳消息
     *
     * @param heartbeat 心跳消息
     */
    void onHeartbeat(SZSEBinaryHeartbeat heartbeat);

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
    void onLogon(SZSEBinaryLogon logon);

    /**
     * 登出成功消息
     *
     * @param logout msg
     */
    void onLogout(SZSEBinaryLogout logout);

    /**
     * level 行情推送
     *
     * @param level1MarketSnapshot level1 msg;
     */
    void onLevel1Market(SZSEBinaryLevel1MarketSnapshot level1MarketSnapshot);
}
