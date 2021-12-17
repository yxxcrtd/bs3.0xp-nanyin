package com.joyintech.tams.exchange.quoteterminal.service;


public interface IQuoteClient {
    String getQuoteType();

    boolean isInit();

    void setInitStatus(boolean initStatus);

    void init();

    void release();

}
