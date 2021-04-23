package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/5/20
 */
public enum Prefixion {
    ENTRUST_PREFIXION("Entrust前缀","e"),
    INTEGRATEDENTRUST_PREFIXION("IntegratedEntrust前缀","i");

    public String value;
    public String name;

    Prefixion(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
