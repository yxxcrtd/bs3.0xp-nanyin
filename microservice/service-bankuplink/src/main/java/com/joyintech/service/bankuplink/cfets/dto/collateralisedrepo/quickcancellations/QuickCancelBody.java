package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.quickcancellations;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


/**
 * 质押式回购</br>
 * 交易接口可通过 QuickCancel（U20）报文快速撤销指定交易员发送的报价。</br>
 * 若快速撤单成功，则逐条反馈QuoteStatusReport（AI）报价撤销通知消息；</br>
 * 若快速撤单失败，则反馈BusinessMessageReject（j）失败反馈消息。</br>
 * 见《第3卷-回购市场V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-18
 */
// @ApiModel(value="QuickCancel报文",description="快速撤单")
public class QuickCancelBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识",example="9")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="tradeMethod",value="10317域-交易方式(1-协商)",example="1")
	private Integer tradeMethod;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="2")
	private String side;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getMarketIndicator() {
		return marketIndicator;
	}

	public void setMarketIndicator(String marketIndicator) {
		this.marketIndicator = marketIndicator;
	}

	public Integer getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(Integer tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public Integer getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
