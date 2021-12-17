package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName SystemMonitorBean
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/25 17:01
 * @Version 1.0
 **/
public class ExchangeMonitorBean {

    private int productTradeMsgCount;
    private int consumerTradeMsgCount;
    private int exceptionTradeMsgCount;

    private int productPersistenceCount;
    private int consumerPersistenceCount;
    private int exceptionPersistenceCount;

    private int tradeRAMCount;
    private int entrustRAMCount;
    private int integratedEntrustRAMCount;
    private int orderRAMCount;
    private int tradeRedisCount;
    private int entrustRedisCount;
    private int integratedEntrustRedisCount;
    private int orderRedisCount;

    public int getProductTradeMsgCount() {
        return productTradeMsgCount;
    }

    public void setProductTradeMsgCount(int productTradeMsgCount) {
        this.productTradeMsgCount = productTradeMsgCount;
    }

    public int getConsumerTradeMsgCount() {
        return consumerTradeMsgCount;
    }

    public void setConsumerTradeMsgCount(int consumerTradeMsgCount) {
        this.consumerTradeMsgCount = consumerTradeMsgCount;
    }

    public int getProductPersistenceCount() {
        return productPersistenceCount;
    }

    public void setProductPersistenceCount(int productPersistenceCount) {
        this.productPersistenceCount = productPersistenceCount;
    }

    public int getConsumerPersistenceCount() {
        return consumerPersistenceCount;
    }

    public void setConsumerPersistenceCount(int consumerPersistenceCount) {
        this.consumerPersistenceCount = consumerPersistenceCount;
    }

    public int getTradeRAMCount() {
        return tradeRAMCount;
    }

    public void setTradeRAMCount(int tradeRAMCount) {
        this.tradeRAMCount = tradeRAMCount;
    }

    public int getEntrustRAMCount() {
        return entrustRAMCount;
    }

    public void setEntrustRAMCount(int entrustRAMCount) {
        this.entrustRAMCount = entrustRAMCount;
    }

    public int getIntegratedEntrustRAMCount() {
        return integratedEntrustRAMCount;
    }

    public void setIntegratedEntrustRAMCount(int integratedEntrustRAMCount) {
        this.integratedEntrustRAMCount = integratedEntrustRAMCount;
    }

    public int getTradeRedisCount() {
        return tradeRedisCount;
    }

    public void setTradeRedisCount(int tradeRedisCount) {
        this.tradeRedisCount = tradeRedisCount;
    }

    public int getEntrustRedisCount() {
        return entrustRedisCount;
    }

    public void setEntrustRedisCount(int entrustRedisCount) {
        this.entrustRedisCount = entrustRedisCount;
    }

    public int getIntegratedEntrustRedisCount() {
        return integratedEntrustRedisCount;
    }

    public void setIntegratedEntrustRedisCount(int integratedEntrustRedisCount) {
        this.integratedEntrustRedisCount = integratedEntrustRedisCount;
    }

    public int getOrderRAMCount() {
        return orderRAMCount;
    }

    public void setOrderRAMCount(int orderRAMCount) {
        this.orderRAMCount = orderRAMCount;
    }

    public int getOrderRedisCount() {
        return orderRedisCount;
    }

    public void setOrderRedisCount(int orderRedisCount) {
        this.orderRedisCount = orderRedisCount;
    }

    public int getExceptionTradeMsgCount() {
        return exceptionTradeMsgCount;
    }

    public void setExceptionTradeMsgCount(int exceptionTradeMsgCount) {
        this.exceptionTradeMsgCount = exceptionTradeMsgCount;
    }

    public int getExceptionPersistenceCount() {
        return exceptionPersistenceCount;
    }

    public void setExceptionPersistenceCount(int exceptionPersistenceCount) {
        this.exceptionPersistenceCount = exceptionPersistenceCount;
    }
}
