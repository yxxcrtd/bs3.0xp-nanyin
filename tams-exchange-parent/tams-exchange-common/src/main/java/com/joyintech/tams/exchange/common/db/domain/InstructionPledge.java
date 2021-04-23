package com.joyintech.tams.exchange.common.db.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author swwan
 * @create 2020-03-20 13:13
 */
public class InstructionPledge implements Serializable, KeyMethodInterface<String> {
    @NotNull
    @Size(max = 64)
    @Id
    private String id;

    @NotNull
    @Size(max = 32)
    private String entrustId = "";// 委托代码

    @Size(max = 50)
    private String finprodId = "";// 金融产品代码

    @Size(max = 200)
    private String finprodAbbr = "";//金融产品简称

    @Size(max = 50)
    private String tradeMarket = "";//交易场所

    @Size(max = 20)
    private String finprodMarketId = "";//市场代码

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue = new BigDecimal(0);//质押面值

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pledgeNumber = new BigDecimal(0);//质押数量

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal usedPledgeNumber = new BigDecimal(0);//已使用质押数量

    @Digits(integer = 30, fraction = 14)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pledgeRatio = new BigDecimal(0);//质押率

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pledgeAmt = new BigDecimal(0);//质押金额

    @Size(max = 50)
    private String oldFinprodId;

    @Size(max = 200)
    private String oldFinprodAbbr;

    @Size(max = 50)
    private String oldTradeMarket;

    @Size(max = 20)
    private String oldFinprodMarketId;

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oldPledgeFaceValue;

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oldPledgeNumber;

    @Digits(integer = 30, fraction = 14)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oldPledgeRatio;

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oldPledgeAmt;

    private int count;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public BigDecimal getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }

    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    public BigDecimal getUsedPledgeNumber() {
        return usedPledgeNumber;
    }

    public void setUsedPledgeNumber(BigDecimal usedPledgeNumber) {
        this.usedPledgeNumber = usedPledgeNumber;
    }

    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOldFinprodId() {
        return oldFinprodId;
    }

    public void setOldFinprodId(String oldFinprodId) {
        this.oldFinprodId = oldFinprodId;
    }

    public String getOldFinprodAbbr() {
        return oldFinprodAbbr;
    }

    public void setOldFinprodAbbr(String oldFinprodAbbr) {
        this.oldFinprodAbbr = oldFinprodAbbr;
    }

    public String getOldTradeMarket() {
        return oldTradeMarket;
    }

    public void setOldTradeMarket(String oldTradeMarket) {
        this.oldTradeMarket = oldTradeMarket;
    }

    public String getOldFinprodMarketId() {
        return oldFinprodMarketId;
    }

    public void setOldFinprodMarketId(String oldFinprodMarketId) {
        this.oldFinprodMarketId = oldFinprodMarketId;
    }

    public BigDecimal getOldPledgeFaceValue() {
        return oldPledgeFaceValue;
    }

    public void setOldPledgeFaceValue(BigDecimal oldPledgeFaceValue) {
        this.oldPledgeFaceValue = oldPledgeFaceValue;
    }

    public BigDecimal getOldPledgeNumber() {
        return oldPledgeNumber;
    }

    public void setOldPledgeNumber(BigDecimal oldPledgeNumber) {
        this.oldPledgeNumber = oldPledgeNumber;
    }

    public BigDecimal getOldPledgeRatio() {
        return oldPledgeRatio;
    }

    public void setOldPledgeRatio(BigDecimal oldPledgeRatio) {
        this.oldPledgeRatio = oldPledgeRatio;
    }

    public BigDecimal getOldPledgeAmt() {
        return oldPledgeAmt;
    }

    public void setOldPledgeAmt(BigDecimal oldPledgeAmt) {
        this.oldPledgeAmt = oldPledgeAmt;
    }
}
