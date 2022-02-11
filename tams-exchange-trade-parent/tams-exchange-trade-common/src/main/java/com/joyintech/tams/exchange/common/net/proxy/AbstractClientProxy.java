package com.joyintech.tams.exchange.common.net.proxy;

import com.joyintech.tams.exchange.common.net.agent.Agent;
import io.netty.channel.Channel;

public abstract class AbstractClientProxy implements Cloneable {
    protected Channel channel = null;
    protected Agent agent = null;

    public Agent getAgent() {
        return agent;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void connEstablished(Channel channel) {
        this.channel = channel;
        connEstablished();
    }

    public abstract void connEstablished();

    public abstract AbstractClientProxy cloneProxy() throws CloneNotSupportedException;

    public abstract void connClosed();

    public abstract void messageReceived(Object objectMessage);
}
