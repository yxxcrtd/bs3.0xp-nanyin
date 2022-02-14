package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * @author swwan
 * @create 2020-03-20 13:13
 */
public class InstructionDetail implements Serializable, KeyMethodInterface<String> {
    private static final long serialVersionUID = -7130177040330108562L;
    @NotNull
    @Size(max = 64)
    @Id
    private String id;

    @NotNull
    @Size(max = 32)
    private String entrustId = "";// 委托代码

    @Size(max = 32)
    private String portfolioId = "";// 组合代码

    @Size(max = 200)
    private String portfolioName = "";//组合名称

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private String disRate = "";//分配比例

    @Digits(integer = 30, fraction = 2)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private String entrustAmt = "";//委托金额
    
    @Digits(integer = 30, fraction = 4)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private String entrustNum = "";//委托数量
    
    @Size(max = 50)
    private String targetType = "";//目标类型


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

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getDisRate() {
		return disRate;
	}

	public void setDisRate(String disRate) {
		this.disRate = disRate;
	}

	public String getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(String entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	public String getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

    
}
