package com.joyintech.tams.exchange.common.constant;

public enum AlgorithmName {
    TWAP("Twap", 1),
    VWAP("Vwap", 2),
    TWAP_PLUS("TWAP_Plus", 101),
    VWAP_PLUS("VWAP_Plus", 102),
    TWAP_CORE("TWAP_ Core", 103),
    VWAP_CORE("VWAP_Core", 104),
    POV_CORE("POV_Core", 105),
    PASSTHRU("Passthru", 201);


    public final String name;
    public final int value;

    AlgorithmName(String name, int value) {
        this.value = value;
        this.name = name;
    }
}
