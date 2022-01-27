package com.joyintech.tams.exchange.common.bean.request;

import com.joyintech.tams.exchange.common.request.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 场外指令分发
 * 指令明细请求参数DTO
 *
 * @author sjj
 * @version 1.0 2021年10月29日
 * @see EtrDispatcherInfoRequest
 * @since 1.0
 */
@ApiModel(value = "指令明细请求参数", description = "指令明细请求参数")
public class EtrDispatcherInfoRequest {

    @ApiModelProperty(name = "entrustId", value = "指令编号", example = "WTREPO202107081860", required = true)
    @NotBlank(message = "指令编号不可为空")
    private String entrustId;

    @ApiModelProperty(name = "busiTypePosition", value = "业务类型", example = "EX001", required = true)
    private String busiTypePosition;

    @ApiModelProperty(name = "ps", value = "交易方向", example = "T01", required = true)
    private String ps;

    @ApiModelProperty(name = "portfolioId", value = "组合代码", example = "CHUMIJZ001", required = true)
    private String portfolioId;

    @ApiModelProperty(name = "entrustStatus", value = "指令状态", example = "03", required = true)
    private String entrustStatus;

    @ApiModelProperty(name = "finprodType2", value = "资产类型", example = "F01", required = true)
    private String finprodType2;

//    @ApiModelProperty(name = "pageMode", value = "分页对象", example = "{}")
//    private PageMode pageMode;

//    @ApiModelProperty(name="tradeMarket",value="交易场所",example="字符串1")
//    private String tradeMarket;
//
//        public String getTradeMarket() {
//        return tradeMarket;
//    }
//
//    public void setTradeMarket(String tradeMarket) {
//        this.tradeMarket = tradeMarket;
//    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

//    public PageMode getPageMode() {
//        return pageMode;
//    }
//
//    public void setPageMode(PageMode pageMode) {
//        this.pageMode = pageMode;
//    }

    public String getBusiTypePosition() {
        return busiTypePosition;
    }

    public void setBusiTypePosition(String busiTypePosition) {
        this.busiTypePosition = busiTypePosition;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }
}

