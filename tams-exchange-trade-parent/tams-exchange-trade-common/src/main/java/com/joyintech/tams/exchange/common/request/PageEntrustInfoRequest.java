package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author swwan
 * @date 2021/12/22 - 10:55
 */
@ApiModel(value = "查询分页委托信息请求对象", description = "查询分页委托信息请求对象")
public class PageEntrustInfoRequest extends BaseRequest {

    @ApiModelProperty(required = true, name = "pageNum", value = "当前页数，0 表示查出全部", example = "1")
//    @NotNull(message = "当前页数不可为空")
    private Integer pageNum;

    @ApiModelProperty(required = true, name = "pageSize", value = "每页数量，0 表示查出全部", example = "20")
//    @NotNull(message = "每页数量不可为空")
    private Integer pageSize;

    @ApiModelProperty(required = true, name = "historyType", value = "查询类型，0 历史信息；1，当前信息", example = "0")
    @NotNull(message = "查询类型不可为空")
    private Integer historyType;

    @ApiModelProperty(required = false, name = "startDay", value = "起始日期", example = "2021-01-01")
    private String startDay;

    @ApiModelProperty(required = false, name = "accountId", value = "账户编号", example = "A001")
    private String accountId;

    @ApiModelProperty(required = false, name = "endDay", value = "截止日志", example = "2021-01-31")
    private String endDay;

    @ApiModelProperty(required = false, name = "market", value = "交易市场，03 上交所；04 深交所", example = "03")
    private String market;

    @ApiModelProperty(required = false, name = "stockCode", value = "证券编号", example = "600000.SH")
    private String stockCode;

    @ApiModelProperty(required = false, name = "entrustProp", value = "委托属性", example = "0")
    private String entrustProp;

    @ApiModelProperty(required = false, name = "portfolioId", value = "组合编号", example = "A001")
    private String portfolioId;

    @ApiModelProperty(required = true, name = "entrustStatus", value = "委托状态，-100 全部", example = "1")
    @NotNull(message = "委托状态不可为空")
    private Integer entrustStatus;

    @ApiModelProperty(required = true, name = "pageMode", value = "分页请求信息")
    @NotNull(message = "分页请求信息不可为空")
    @Valid
    private PageMode pageMode;

    public PageEntrustInfoRequest() {
    }

    public PageEntrustInfoRequest(Integer pageNum, Integer pageSize, Integer historyType, String startDay, String accountId, String endDay, String market, String stockCode, String entrustProp, String portfolioId, Integer entrustStatus) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.historyType = historyType;
        this.startDay = startDay;
        this.accountId = accountId;
        this.endDay = endDay;
        this.market = market;
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.portfolioId = portfolioId;
        this.entrustStatus = entrustStatus;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getHistoryType() {
        return historyType;
    }

    public void setHistoryType(Integer historyType) {
        this.historyType = historyType;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

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

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Integer getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(Integer entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    @Override
    public String toString() {
        return "PageEntrustInfoRequest{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", historyType=" + historyType +
                ", startDay='" + startDay + '\'' +
                ", accountId='" + accountId + '\'' +
                ", endDay='" + endDay + '\'' +
                ", market='" + market + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", entrustProp='" + entrustProp + '\'' +
                ", portfolioId='" + portfolioId + '\'' +
                ", entrustStatus=" + entrustStatus +
                '}';
    }
}
