package com.joyintech.tams.exchange.quoteterminal.sse.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 14:27 2021/8/9
 */
@Component
@ConfigurationProperties(prefix = "sse.file")
public class SSEFileConfig {
    /**
     * 行情落地文件目录
     */
    private String quoteDir;
    /**
     * 产品信息版本
     */
    private String cpxx;
    /**
     * 股票行情文件路径
     */
    private String stockName;
    /**
     * 是否需要股票行情
     */
    private boolean stockEnable;
    /**
     * 债券行情文件路径
     */
    private String bondName;
    /**
     * 是否启用债券行情
     */
    private boolean bondEnable;
    /**
     * 期权行情文件路径
     */
    private String optionName;
    /**
     * 是否启用期权行情
     */
    private boolean optionEnable;

    public String getQuoteDir() {
        return quoteDir;
    }

    public void setQuoteDir(String quoteDir) {
        this.quoteDir = quoteDir;
    }

    public String getCpxx() {
        return cpxx;
    }

    public void setCpxx(String cpxx) {
        this.cpxx = cpxx;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public boolean isStockEnable() {
        return stockEnable;
    }

    public void setStockEnable(boolean stockEnable) {
        this.stockEnable = stockEnable;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public boolean isBondEnable() {
        return bondEnable;
    }

    public void setBondEnable(boolean bondEnable) {
        this.bondEnable = bondEnable;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public boolean isOptionEnable() {
        return optionEnable;
    }

    public void setOptionEnable(boolean optionEnable) {
        this.optionEnable = optionEnable;
    }

    public boolean isStockQuote(String filename) {
        return isStockEnable() && this.stockName.equals(filename);
    }

    public boolean isBondQuote(String filename) {
        return isBondEnable() && this.bondName.equals(filename);
    }

    public boolean isOptionQuote(String filename) {
        return isOptionEnable() && this.optionName.equals(filename);
    }

    public String getStockFilePath() {
        return this.quoteDir + "\\" + this.stockName;
    }

    public String getBondFilePath() {
        return this.quoteDir + "\\" + this.bondName;
    }

    public String getOptionFilePath() {
        return this.quoteDir + "\\" + this.optionName;
    }
}
