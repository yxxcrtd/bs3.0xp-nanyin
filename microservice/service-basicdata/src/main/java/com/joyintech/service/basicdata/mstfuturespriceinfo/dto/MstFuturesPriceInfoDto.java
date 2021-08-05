package com.joyintech.service.basicdata.mstfuturespriceinfo.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname MstFuturesPriceInfoDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/26 20:25 
 * @version 1.0 
 */
public class MstFuturesPriceInfoDto extends BaseEntity implements ITableRow {


    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面类型
     */
    private String pageModel;


    /**
     * 合约代码
     */
    private String futuresId;

    /**
     * wind代码
     */
    private String windCode;

    /**
     * 行情日期
     */
    private Date tradeDate;

    /**
     * 前结算价
     */
    private BigDecimal preSettlePrice;

    /**
     * 开盘价
     */
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 最高价
     */
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

    /**
     * 结算价
     */
    private BigDecimal settlePrice;


    public String getWindCode() {
        return windCode;
    }

    public void setWindCode(String windCode) {
        this.windCode = windCode;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getFuturesId() {
        return futuresId;
    }

    public void setFuturesId(String futuresId) {
        this.futuresId = futuresId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getPreSettlePrice() {
        return preSettlePrice;
    }

    public void setPreSettlePrice(BigDecimal preSettlePrice) {
        this.preSettlePrice = preSettlePrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
