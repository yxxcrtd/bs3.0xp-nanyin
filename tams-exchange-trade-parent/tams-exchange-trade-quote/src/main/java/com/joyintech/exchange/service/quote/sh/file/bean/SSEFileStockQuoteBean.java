package com.joyintech.exchange.service.quote.sh.file.bean;

import java.time.LocalTime;

/**
 * @Author: think
 * @Description: 该类相关字段需要按照交易所文档字段顺序排放，修改钱请注意
 * @Date: Created in 13:59 2021/8/10
 */
public class SSEFileStockQuoteBean extends SSEFileQuote {

    private String tradingPhaseCode;        //产品实时阶段及标志
    private LocalTime timestamp;            //时间戳，格式为 HH:MM:SS.sss

    public String getTradingPhaseCode() {
        return tradingPhaseCode;
    }

    public void setTradingPhaseCode(String tradingPhaseCode) {
        this.tradingPhaseCode = tradingPhaseCode;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }
}
