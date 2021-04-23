package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

public class InstructionOpBean implements Serializable {
    private String instructionId;
    private String recStatus;
    private String trader;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }
}