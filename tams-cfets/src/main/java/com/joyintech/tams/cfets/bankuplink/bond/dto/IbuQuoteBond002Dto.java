package com.joyintech.tams.cfets.bankuplink.bond.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="债券对话报价请求参数",description="债券对话报价请求参数")
public class IbuQuoteBond002Dto {
	
	/**
     * 交易方向
     */
	@ApiModelProperty(name="ps",value="交易方向",example="", required = true)
    private String ps;
    
    /**
     * 资产简称
     */
	@ApiModelProperty(name="finprodAbbr",value="资产简称",example="债券002", required = true)
    private String finprodAbbr;
    
    /**
     * 资产代码
     */
	@ApiModelProperty(name="finprodId",value="资产代码",example="F01_xq002.IB", required = true)
    private String finprodId;
    
    /**
     * 本方
     */
	@ApiModelProperty(name="mySide",value="本方",example="test", required = true)
    private String mySide;
    
    /**
     * 本方交易员
     */
	@ApiModelProperty(name="mySideTrader",value="本方交易员",example="测试", required = true)
    private String mySideTrader;
    
    /**
     * 对手方
     */
	@ApiModelProperty(name="counterId",value="对手方",example="test", required = true)
    private String counterId;
    
    /**
     * 对手方交易员
     */
	@ApiModelProperty(name="counterTrader",value="对手方交易员",example="测试", required = true)
    private String counterTrader;
    
    /**
     * 币种
     */
	@ApiModelProperty(name="ccy",value="币种",example="", required = true)
    private String ccy;
    
    /**
     * 清算速度
     */
	@ApiModelProperty(name="settleSpreed",value="清算速度",example="", required = true)
    private String settleSpreed;
    
    /**
     * 结算日期
     */
	@ApiModelProperty(name="settleDate",value="结算日期",example="", required = true)
    private Date settleDate;
    
    /**
     * 净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @ApiModelProperty(name="price",value="净价",example="", required = true)
    private BigDecimal price;
    
    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(name="matureYield",value="到期收益率",example="", required = true)
    private BigDecimal matureYield;
    
    /**
     * 行权收益率
     */
    @ApiModelProperty(name="exerYield",value="行权收益率",example="", required = true)
    private BigDecimal exerYield;
    
    /**
     * 券面总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    @ApiModelProperty(name="avaNum",value="券面总额",example="", required = true)
    private BigDecimal avaNum;
    
    /**
     * 本方券券面总额
     */
    /**
     * 融入券券面总额
     */
    /**
     * 卖空购回券面总额
     */

    
    /**
     * 结算方式
     */
    @ApiModelProperty(name="settleMethod",value="结算方式",example="", required = true)
    private String settleMethod;
    
    /**
     * 清算类型
     */
    @ApiModelProperty(name="settleType",value="清算类型",example="", required = true)
    private String settleType;
    
    /**
     * 交易手续费
     */
    
    /**
     * 结算服务费
     */
    
    /**
     * 备注
     */
    @ApiModelProperty(name="remark",value="备注",example="", required = true)
    private String remark;
    
    /**
     * 组合分配dto
     */
    @ApiModelProperty(name="combDistribtDtoList",value="组合分配dto",example="", required = true)
    private List<CombDistribtDto> combDistribtDtoList;

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getMySide() {
		return mySide;
	}

	public void setMySide(String mySide) {
		this.mySide = mySide;
	}

	public String getMySideTrader() {
		return mySideTrader;
	}

	public void setMySideTrader(String mySideTrader) {
		this.mySideTrader = mySideTrader;
	}

	public String getCounterId() {
		return counterId;
	}

	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	public String getCounterTrader() {
		return counterTrader;
	}

	public void setCounterTrader(String counterTrader) {
		this.counterTrader = counterTrader;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getSettleSpreed() {
		return settleSpreed;
	}

	public void setSettleSpreed(String settleSpreed) {
		this.settleSpreed = settleSpreed;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMatureYield() {
		return matureYield;
	}

	public void setMatureYield(BigDecimal matureYield) {
		this.matureYield = matureYield;
	}

	public BigDecimal getExerYield() {
		return exerYield;
	}

	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}

	public BigDecimal getAvaNum() {
		return avaNum;
	}

	public void setAvaNum(BigDecimal avaNum) {
		this.avaNum = avaNum;
	}

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CombDistribtDto> getCombDistribtDtoList() {
		return combDistribtDtoList;
	}

	public void setCombDistribtDtoList(List<CombDistribtDto> combDistribtDtoList) {
		this.combDistribtDtoList = combDistribtDtoList;
	}
    
}
