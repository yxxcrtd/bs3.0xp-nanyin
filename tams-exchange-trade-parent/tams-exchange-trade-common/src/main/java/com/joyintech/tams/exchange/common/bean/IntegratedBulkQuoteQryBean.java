package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "大宗行情请求报文", description = "大宗行情请求报文")
public class IntegratedBulkQuoteQryBean {
    @ApiModelProperty(required = true, name = "instructionId", value = "指令编号", example = "FUND000121")
    @NotBlank(message = "指令编号不能为空")
    private String instructionId;

    @ApiModelProperty(required = true, name = "stockCode", value = "证券代码", example = "000001.SZ")
//    @NotBlank(message = "证券代码不能为空")
    private String stockCode;

    @ApiModelProperty(name = "entrustProp", value = "委托属性", example = "a", hidden = true)
    private String entrustProp;

    @ApiModelProperty(name = "entrustBs", value = "委托方向", example = "1", hidden = true)
    private String entrustBs;

    @ApiModelProperty(required = true, name = "accountId", value = "网点账号", example = "A0001:12345")
//    @NotBlank(message = "网点账号不能为空")
    private String accountId;

    @ApiModelProperty(name = "pageSize", value = "记录数", example = "100")
    private int pageSize;

    public IntegratedBulkQuoteQryBean(String stockCode, String entrustProp, String entrustBs, String accountId, int pageSize) {
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustBs = entrustBs;
        this.accountId = accountId;
        this.pageSize = pageSize;
    }

    public IntegratedBulkQuoteQryBean() {
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
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

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
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
