package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "合约数据刷新请求报文", description = "合约数据刷新请求报文")
public class InstrumentFlushRequest implements Serializable {
    private static final long serialVersionUID = -6056736634274395913L;

    @ApiModelProperty(required = false, name = "market", value = "市场")
    private String market;
    @ApiModelProperty(required = false, name = "stockCode", value = "证券代码")
    private String stockCode;
    @ApiModelProperty(required = false, name = "pageNo", value = "页面")
    private String pageNo;
    @ApiModelProperty(required = false, name = "count", value = "记录数")
    private String count;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
