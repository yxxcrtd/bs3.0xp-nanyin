package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "盘后大宗行情请求报文", description = "盘后大宗行情请求报文")
public class IntegratedBulkCloseQuoteQryBean {

    @ApiModelProperty(required = true, name = "stockCode", value = "证券代码",example = "000001.SZ")
    @NotBlank(message = "证券代码不能为空")
    private String stockCode;

    @ApiModelProperty(name = "exchangeType", value = "交易类别（1-上海，2-深圳）",example = "2")
    @NotBlank(message = "交易类别不能为空")
    private String exchangeType;

    @ApiModelProperty(required = true, name = "accountId", value = "网点账号",example = "A0001:12345")
    @NotBlank(message = "网点账号不能为空")
    private String accountId;

    @ApiModelProperty(name = "pageSize", value = "记录数",example = "100")
    private int pageSize;

    public IntegratedBulkCloseQuoteQryBean(String stockCode, String exchangeType, String accountId, int pageSize) {
        this.stockCode = stockCode;
        this.exchangeType = exchangeType;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public IntegratedBulkCloseQuoteQryBean() {
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
