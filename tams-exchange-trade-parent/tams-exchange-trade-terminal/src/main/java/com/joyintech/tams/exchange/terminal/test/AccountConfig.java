package com.joyintech.tams.exchange.terminal.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "terminalTest")
@PropertySource(value = {"classpath:accountConf.properties"})
public class AccountConfig {
    @Value("${terminalTest.broker.serverInfo}")
    private String serverInfo;
    @Value("${terminalTest.account}")
    private String accounts;
    @Value("${terminalTest.interface.name}")
    private String testInterfaceName;
    @Value("${terminalTest.order.fixedRate}")
    private int delay;
    @Value("${terminalTest.instrument.stockCodes}")
    private String stockCodes;
    @Value("${terminalTest.instrument.entrustPrice}")
    private String entrustPrice;
    @Value("${terminalTest.instrument.entrustVolume}")
    private String entrustVolume;
    @Value("${terminalTest.instrument.stockCode_Stocks}")
    private String stockCode_Stocks;
    @Value("${terminalTest.instrument.stockCode_Bonds}")
    private String stockCode_Bonds;
    @Value("${terminalTest.instrument.stockCode_Funds}")
    private String stockCode_Funds;
    @Value("${terminalTest.instrument.stockCode_ChangeBonds}")
    private String stockCode_ChangeBonds;
    @Value("${terminalTest.instrument.entrustPrice_ChangeBonds}")
    private String entrustPrice_ChangeBonds;
    @Value("${terminalTest.testType}")
    private String testType;
    @Value("${terminalTest.propSeatNo}")
    private String propSeatNo;
    @Value("${terminalTest.entrustBs}")
    private String entrustBs;
    @Value("${terminalTest.compactTerm}")
    private int compactTerm;
    @Value("${terminalTest.firstSettleDate}")
    private String firstSettleDate;
    @Value("${terminalTest.trader}")
    private String trader;
    @Value("${terminalTest.stockCode_Funds_FixedIncome}")
    private String stockCode_Funds_FixedIncome;
    @Value("${terminalTest.entrustPrice_FixedIncome}")
    private String entrustPrice_FixedIncome;
    @Value("${terminalTest.stockCode_Funds_ProtocolBack_SH}")
    private String stockCode_Funds_ProtocolBack_SH;
    @Value("${terminalTest.entrustPrice_ProtocolBack_SH}")
    private String entrustPrice_ProtocolBack_SH;
    @Value("${terminalTest.stockCode_Funds_ProtocolBack_SZ}")
    private String stockCode_Funds_ProtocolBack_SZ;
    @Value("${terminalTest.entrustPrice_ProtocolBack_SZ}")
    private String entrustPrice_ProtocolBack_SZ;


    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getTestInterfaceName() {
        return testInterfaceName;
    }

    public void setTestInterfaceName(String testInterfaceName) {
        this.testInterfaceName = testInterfaceName;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(String stockCodes) {
        this.stockCodes = stockCodes;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(String entrustVolume) {
        this.entrustVolume = entrustVolume;
    }

    public String getStockCode_Stocks() {
        return stockCode_Stocks;
    }

    public void setStockCode_Stocks(String stockCode_Stocks) {
        this.stockCode_Stocks = stockCode_Stocks;
    }

    public String getStockCode_Bonds() {
        return stockCode_Bonds;
    }

    public void setStockCode_Bonds(String stockCode_Bonds) {
        this.stockCode_Bonds = stockCode_Bonds;
    }

    public String getStockCode_Funds() {
        return stockCode_Funds;
    }

    public void setStockCode_Funds(String stockCode_Funds) {
        this.stockCode_Funds = stockCode_Funds;
    }

    public String getStockCode_ChangeBonds() {
        return stockCode_ChangeBonds;
    }

    public void setStockCode_ChangeBonds(String stockCode_ChangeBonds) {
        this.stockCode_ChangeBonds = stockCode_ChangeBonds;
    }

    public String getEntrustPrice_ChangeBonds() {
        return entrustPrice_ChangeBonds;
    }

    public void setEntrustPrice_ChangeBonds(String entrustPrice_ChangeBonds) {
        this.entrustPrice_ChangeBonds = entrustPrice_ChangeBonds;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getPropSeatNo() {
        return propSeatNo;
    }

    public void setPropSeatNo(String propSeatNo) {
        this.propSeatNo = propSeatNo;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public int getCompactTerm() {
        return compactTerm;
    }

    public void setCompactTerm(int compactTerm) {
        this.compactTerm = compactTerm;
    }

    public String getFirstSettleDate() {
        return firstSettleDate;
    }

    public void setFirstSettleDate(String firstSettleDate) {
        this.firstSettleDate = firstSettleDate;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getStockCode_Funds_FixedIncome() {
        return stockCode_Funds_FixedIncome;
    }

    public void setStockCode_Funds_FixedIncome(String stockCode_Funds_FixedIncome) {
        this.stockCode_Funds_FixedIncome = stockCode_Funds_FixedIncome;
    }

    public String getEntrustPrice_FixedIncome() {
        return entrustPrice_FixedIncome;
    }

    public void setEntrustPrice_FixedIncome(String entrustPrice_FixedIncome) {
        this.entrustPrice_FixedIncome = entrustPrice_FixedIncome;
    }

    public String getStockCode_Funds_ProtocolBack_SH() {
        return stockCode_Funds_ProtocolBack_SH;
    }

    public void setStockCode_Funds_ProtocolBack_SH(String stockCode_Funds_ProtocolBack_SH) {
        this.stockCode_Funds_ProtocolBack_SH = stockCode_Funds_ProtocolBack_SH;
    }

    public String getEntrustPrice_ProtocolBack_SH() {
        return entrustPrice_ProtocolBack_SH;
    }

    public void setEntrustPrice_ProtocolBack_SH(String entrustPrice_ProtocolBack_SH) {
        this.entrustPrice_ProtocolBack_SH = entrustPrice_ProtocolBack_SH;
    }

    public String getStockCode_Funds_ProtocolBack_SZ() {
        return stockCode_Funds_ProtocolBack_SZ;
    }

    public void setStockCode_Funds_ProtocolBack_SZ(String stockCode_Funds_ProtocolBack_SZ) {
        this.stockCode_Funds_ProtocolBack_SZ = stockCode_Funds_ProtocolBack_SZ;
    }

    public String getEntrustPrice_ProtocolBack_SZ() {
        return entrustPrice_ProtocolBack_SZ;
    }

    public void setEntrustPrice_ProtocolBack_SZ(String entrustPrice_ProtocolBack_SZ) {
        this.entrustPrice_ProtocolBack_SZ = entrustPrice_ProtocolBack_SZ;
    }
}
