package com.joyintech.tams.exchange.common.constant;

public enum AgentType {

    KINGDOM_KCBP("金证kcbp", "kcbp"),
    UFX("恒生ufx", "ufx"),
    KD_GATEWAY("金证KDGateway", "KDGateway"),
    JSD("金仕达", "jsd"),
    MOCK("模拟撮合终端", "mock"),
    CTP("ctp交易终端", "ctp");

    public final String value;
    public final String name;

    AgentType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static AgentType agentType(String value) {
        switch (value) {
            case "kcbp":
                return KINGDOM_KCBP;
            case "ufx":
                return UFX;
            case "KDGateway":
                return KD_GATEWAY;
            case "ctp":
                return CTP;
        }
        return null;
    }
}
