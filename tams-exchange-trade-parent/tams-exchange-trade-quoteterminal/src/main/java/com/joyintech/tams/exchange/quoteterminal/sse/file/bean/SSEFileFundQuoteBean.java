package com.joyintech.tams.exchange.quoteterminal.sse.file.bean;

import java.time.LocalTime;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 7:42 2021/8/14
 */
public class SSEFileFundQuoteBean extends SSEFileQuote {

    private Double preCloseIOPV;            //基金 T-1 日收盘时刻 IOPV
    private Double IOPV;                    //基金 IOPV
    private String tradingPhaseCode;        //产品实时阶段及标志
    private LocalTime timestamp;            //时间戳，格式为 HH:MM:SS.sss

    public Double getPreCloseIOPV() {
        return preCloseIOPV;
    }

    public void setPreCloseIOPV(Double preCloseIOPV) {
        this.preCloseIOPV = preCloseIOPV;
    }

    public Double getIOPV() {
        return IOPV;
    }

    public void setIOPV(Double IOPV) {
        this.IOPV = IOPV;
    }

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
