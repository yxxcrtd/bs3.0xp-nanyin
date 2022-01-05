package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author swwan
 * @date 2021/12/22 - 10:55
 */
@ApiModel(value = "查询历史成交信息请求对象", description = "查询历史成交信息请求对象")
public class HistoryTradeInfoRequest extends BaseRequest {

    @ApiModelProperty(required = false, name = "postStr", value = "定位串", example = "1001")
    private String postStr;

    @ApiModelProperty(required = true, name = "accountId", value = "账户编号", example = "A001")
    private String accountId;

    @ApiModelProperty(required = true, name = "startDay", value = "起始日期", example = "2021-01-01")
    private String startDay;

    @ApiModelProperty(required = true, name = "endDay", value = "截止日志", example = "2021-01-31")
    private String endDay;

    @ApiModelProperty(required = true, name = "pageSize", value = "每页数量", example = "20")
    private Integer pageSize;

    @ApiModelProperty(required = true, name = "pageMode", value = "分页请求信息")
    @NotNull(message = "分页请求信息不可为空")
    @Valid
    private PageMode pageMode;

    public HistoryTradeInfoRequest() {
    }

    public HistoryTradeInfoRequest(String postStr, String accountId, String startDay, String endDay, Integer pageSize) {
        this.postStr = postStr;
        this.accountId = accountId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.pageSize = pageSize;
    }

    public String getPostStr() {
        return postStr;
    }

    public void setPostStr(String postStr) {
        this.postStr = postStr;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    @Override
    public String toString() {
        return "HistoryTradeInfoRequest{" +
                "postStr='" + postStr + '\'' +
                ", accountId='" + accountId + '\'' +
                ", startDay='" + startDay + '\'' +
                ", endDay='" + endDay + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
