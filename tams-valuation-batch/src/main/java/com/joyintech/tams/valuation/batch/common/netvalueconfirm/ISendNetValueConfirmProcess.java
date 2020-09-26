package com.joyintech.tams.valuation.batch.common.netvalueconfirm;

@FunctionalInterface
public interface ISendNetValueConfirmProcess {
    abstract void sendNetValueConfirm(String execId);
}
