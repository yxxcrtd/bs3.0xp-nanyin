package com.joyintech.tams.exchange.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@ApiModel(value = "组合计算", description = "组合计算")
public class PortfolioCalculateResponse {
    @JsonProperty("portfolioInfo")
    private PortfolioInfoResponse portfolioInfo;

    @JsonProperty("portfolioName")
    private String portfolioName;

    @JsonProperty("portfolioId")
    private String portfolioId;

    @JsonProperty("finprodName")
    private String finprodName;

    @JsonProperty("instructionId")
    private String instructionId;

    @JsonProperty("finprodMarketId")
    private String finprodMarketId;

    @JsonProperty("entrustAmt")
    private BigDecimal entrustAmt;

    @JsonProperty("entrustPrice")
    private BigDecimal entrustPrice;

    @JsonProperty("entrustProp")
    private String entrustProp;

    @JsonProperty("instructionTotalAmt")
    private BigDecimal instructionTotalAmt;

    @JsonProperty("instructionTotalNum")
    private Long instructionTotalNum;

    @JsonProperty("instructionLeftAmt")
    private BigDecimal instructionLeftAmt;

    @JsonProperty("disRate")
    private BigDecimal disRate;

    @JsonProperty("entrustNum")
    private Long entrustNum;

    @JsonProperty("instructionLeftNum")
    private Long instructionLeftNum;

    public PortfolioCalculateResponse portfolioInfo(PortfolioInfoResponse portfolioInfo) {
        this.portfolioInfo = portfolioInfo;
        return this;
    }

    /**
     * Get portfolioInfo
     *
     * @return portfolioInfo
     */
    @ApiModelProperty(required = true, value = "组合信息")
    public PortfolioInfoResponse getPortfolioInfo() {
        return portfolioInfo;
    }

    public void setPortfolioInfo(PortfolioInfoResponse portfolioInfo) {
        this.portfolioInfo = portfolioInfo;
    }

    public PortfolioCalculateResponse portfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
        return this;
    }

    /**
     * 组合名称
     *
     * @return portfolioName
     */
    @ApiModelProperty(required = true, value = "组合名称")
    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public PortfolioCalculateResponse portfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
        return this;
    }

    /**
     * 组合代码
     *
     * @return portfolioId
     */
    @ApiModelProperty(required = true, value = "组合代码")
    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public PortfolioCalculateResponse finprodName(String finprodName) {
        this.finprodName = finprodName;
        return this;
    }

    /**
     * 资产名称
     *
     * @return finprodName
     */
    @ApiModelProperty(required = true, value = "资产名称")
    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public PortfolioCalculateResponse instructionId(String instructionId) {
        this.instructionId = instructionId;
        return this;
    }

    /**
     * 指令代码
     *
     * @return instructionId
     */
    @ApiModelProperty(required = true, value = "指令代码")
    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public PortfolioCalculateResponse finprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
        return this;
    }

    /**
     * 资产代码
     *
     * @return finprodMarketId
     */
    @ApiModelProperty(required = true, value = "资产代码")
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public PortfolioCalculateResponse entrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
        return this;
    }

    /**
     * 委托金额
     *
     * @return entrustAmt
     */
    @ApiModelProperty(required = true, value = "委托金额")
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public PortfolioCalculateResponse entrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
        return this;
    }

    /**
     * 委托价格
     *
     * @return entrustPrice
     */
    @ApiModelProperty(required = true, value = "委托价格")
    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public PortfolioCalculateResponse entrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
        return this;
    }

    /**
     * 委托模式
     *
     * @return entrustProp
     */
    @ApiModelProperty(required = true, value = "委托模式")
    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public PortfolioCalculateResponse instructionTotalAmt(BigDecimal instructionTotalAmt) {
        this.instructionTotalAmt = instructionTotalAmt;
        return this;
    }

    /**
     * 指令总金额
     *
     * @return instructionTotalAmt
     */
    @ApiModelProperty(required = true, value = "指令总金额")
    public BigDecimal getInstructionTotalAmt() {
        return instructionTotalAmt;
    }

    public void setInstructionTotalAmt(BigDecimal instructionTotalAmt) {
        this.instructionTotalAmt = instructionTotalAmt;
    }

    public PortfolioCalculateResponse instructionTotalNum(Long instructionTotalNum) {
        this.instructionTotalNum = instructionTotalNum;
        return this;
    }

    /**
     * 指令总数量
     *
     * @return instructionTotalNum
     */
    @ApiModelProperty(required = true, value = "指令总数量")
    public Long getInstructionTotalNum() {
        return instructionTotalNum;
    }

    public void setInstructionTotalNum(Long instructionTotalNum) {
        this.instructionTotalNum = instructionTotalNum;
    }

    public PortfolioCalculateResponse instructionLeftAmt(BigDecimal instructionLeftAmt) {
        this.instructionLeftAmt = instructionLeftAmt;
        return this;
    }

    /**
     * 指令剩余金额
     *
     * @return instructionLeftAmt
     */
    @ApiModelProperty(required = true, value = "指令剩余金额")
    public BigDecimal getInstructionLeftAmt() {
        return instructionLeftAmt;
    }

    public void setInstructionLeftAmt(BigDecimal instructionLeftAmt) {
        this.instructionLeftAmt = instructionLeftAmt;
    }

    public PortfolioCalculateResponse disRate(BigDecimal disRate) {
        this.disRate = disRate;
        return this;
    }

    /**
     * 分配比例
     *
     * @return disRate
     */
    @ApiModelProperty(required = true, value = "分配比例")
    public BigDecimal getDisRate() {
        return disRate;
    }

    public void setDisRate(BigDecimal disRate) {
        this.disRate = disRate;
    }

    public PortfolioCalculateResponse entrustNum(Long entrustNum) {
        this.entrustNum = entrustNum;
        return this;
    }

    /**
     * 委托数量
     *
     * @return entrustNum
     */
    @ApiModelProperty(required = true, value = "委托数量")
    public Long getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(Long entrustNum) {
        this.entrustNum = entrustNum;
    }

    public PortfolioCalculateResponse instructionLeftNum(Long instructionLeftNum) {
        this.instructionLeftNum = instructionLeftNum;
        return this;
    }

    /**
     * 指令剩余数量
     *
     * @return instructionLeftNum
     */
    @ApiModelProperty(required = true, value = "指令剩余数量")
    @NotNull


    public Long getInstructionLeftNum() {
        return instructionLeftNum;
    }

    public void setInstructionLeftNum(Long instructionLeftNum) {
        this.instructionLeftNum = instructionLeftNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PortfolioCalculateResponse portfolioCalculateResponse = (PortfolioCalculateResponse) o;
        return Objects.equals(this.portfolioInfo, portfolioCalculateResponse.portfolioInfo) &&
                Objects.equals(this.portfolioName, portfolioCalculateResponse.portfolioName) &&
                Objects.equals(this.portfolioId, portfolioCalculateResponse.portfolioId) &&
                Objects.equals(this.finprodName, portfolioCalculateResponse.finprodName) &&
                Objects.equals(this.instructionId, portfolioCalculateResponse.instructionId) &&
                Objects.equals(this.finprodMarketId, portfolioCalculateResponse.finprodMarketId) &&
                Objects.equals(this.entrustAmt, portfolioCalculateResponse.entrustAmt) &&
                Objects.equals(this.entrustPrice, portfolioCalculateResponse.entrustPrice) &&
                Objects.equals(this.entrustProp, portfolioCalculateResponse.entrustProp) &&
                Objects.equals(this.instructionTotalAmt, portfolioCalculateResponse.instructionTotalAmt) &&
                Objects.equals(this.instructionTotalNum, portfolioCalculateResponse.instructionTotalNum) &&
                Objects.equals(this.instructionLeftAmt, portfolioCalculateResponse.instructionLeftAmt) &&
                Objects.equals(this.disRate, portfolioCalculateResponse.disRate) &&
                Objects.equals(this.entrustNum, portfolioCalculateResponse.entrustNum) &&
                Objects.equals(this.instructionLeftNum, portfolioCalculateResponse.instructionLeftNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portfolioInfo, portfolioName, portfolioId, finprodName, instructionId, finprodMarketId, entrustAmt, entrustPrice, entrustProp, instructionTotalAmt, instructionTotalNum, instructionLeftAmt, disRate, entrustNum, instructionLeftNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PortfolioCalculateResponse {\n");

        sb.append("    portfolioInfo: ").append(toIndentedString(portfolioInfo)).append("\n");
        sb.append("    portfolioName: ").append(toIndentedString(portfolioName)).append("\n");
        sb.append("    portfolioId: ").append(toIndentedString(portfolioId)).append("\n");
        sb.append("    finprodName: ").append(toIndentedString(finprodName)).append("\n");
        sb.append("    instructionId: ").append(toIndentedString(instructionId)).append("\n");
        sb.append("    finprodMarketId: ").append(toIndentedString(finprodMarketId)).append("\n");
        sb.append("    entrustAmt: ").append(toIndentedString(entrustAmt)).append("\n");
        sb.append("    entrustPrice: ").append(toIndentedString(entrustPrice)).append("\n");
        sb.append("    entrustProp: ").append(toIndentedString(entrustProp)).append("\n");
        sb.append("    instructionTotalAmt: ").append(toIndentedString(instructionTotalAmt)).append("\n");
        sb.append("    instructionTotalNum: ").append(toIndentedString(instructionTotalNum)).append("\n");
        sb.append("    instructionLeftAmt: ").append(toIndentedString(instructionLeftAmt)).append("\n");
        sb.append("    disRate: ").append(toIndentedString(disRate)).append("\n");
        sb.append("    entrustNum: ").append(toIndentedString(entrustNum)).append("\n");
        sb.append("    instructionLeftNum: ").append(toIndentedString(instructionLeftNum)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}



