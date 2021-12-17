package com.joyintech.tams.exchange.common.net.agent;

public enum AgentStatus {
    DEFAULT(-1),
    CONNECTED(0),
    LOGIN(1),
    DISCONNECTED(2);


    public int status;

    AgentStatus(int value) {
        this.status = value;
    }
}
