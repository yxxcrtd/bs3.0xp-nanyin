package com.joyintech.tams.exchange.quoteterminal.service;

import javax.annotation.PostConstruct;

public abstract class QuoteClient implements IQuoteClient{
    private boolean hasInit = false;

    @Override
    public boolean isInit() {
        return hasInit;
    }

    @Override
    public void setInitStatus(boolean initStatus) {
        hasInit = initStatus;
    }

    @PostConstruct
    public abstract void addClient();
}
