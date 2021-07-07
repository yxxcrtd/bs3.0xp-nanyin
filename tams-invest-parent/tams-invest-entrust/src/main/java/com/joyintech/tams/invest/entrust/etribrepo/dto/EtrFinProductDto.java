package com.joyintech.tams.invest.entrust.etribrepo.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrFinProductDto
 * @since 1.0
 */
public class EtrFinProductDto {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 证券管理账户/通道代码
     */
    private String secAcctId;

    /**
     * 组合T日可用现金
     */
    private BigDecimal zhkyxj_t;

    /**
     * 组合T+1日可用现金
     */
    private BigDecimal zhkyxj_t1;

    /**
     * 托管户T日可用现金
     */
    private BigDecimal tghkyxj_t;

    /**
     * 托管户T+1日可用现金
     */
    private BigDecimal tghkyxj_t1;

    /**
     * 通道T日可用现金
     */
    private BigDecimal tdkyxj_t;

    /**
     * 通道T+1日可用现金
     */
    private BigDecimal tdkyxj_t1;

    /**
     * 资产持仓券面总额
     */
    private BigDecimal zccc_qmze;

    /**
     * 资产可用券面总额
     */
    private BigDecimal zcky_qmze;

    /**
     * 行情类别
     */
    private String hqType;

    /**
     * 交易品种
     */
    private String tradeType;

    /**
     * 最新利率
     */
    private BigDecimal newRate;

    /**
     * 开盘利率
     */
    private BigDecimal kpRate;

    /**
     * 最高利率
     */
    private BigDecimal zgRate;

    /**
     * 最低利率
     */
    private BigDecimal zdRate;

    /**
     * 加权平均利率
     */
    private BigDecimal pjRate;

    /**
     * 平均回购期限
     */
    private BigDecimal pjTerm;

    /**
     * 加权平均利率(利率债)
     */
    private BigDecimal pjlvRate;
    
    

    public String getHqType() {
		return hqType;
	}

	public void setHqType(String hqType) {
		this.hqType = hqType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getKpRate() {
		return kpRate;
	}

	public void setKpRate(BigDecimal kpRate) {
		this.kpRate = kpRate;
	}

	public BigDecimal getZgRate() {
		return zgRate;
	}

	public void setZgRate(BigDecimal zgRate) {
		this.zgRate = zgRate;
	}

	public BigDecimal getZdRate() {
		return zdRate;
	}

	public void setZdRate(BigDecimal zdRate) {
		this.zdRate = zdRate;
	}

	public BigDecimal getPjRate() {
		return pjRate;
	}

	public void setPjRate(BigDecimal pjRate) {
		this.pjRate = pjRate;
	}

	public BigDecimal getPjTerm() {
		return pjTerm;
	}

	public void setPjTerm(BigDecimal pjTerm) {
		this.pjTerm = pjTerm;
	}

	public BigDecimal getPjlvRate() {
		return pjlvRate;
	}

	public void setPjlvRate(BigDecimal pjlvRate) {
		this.pjlvRate = pjlvRate;
	}

	public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public BigDecimal getZhkyxj_t() {
        return zhkyxj_t;
    }

    public void setZhkyxj_t(BigDecimal zhkyxj_t) {
        this.zhkyxj_t = zhkyxj_t;
    }

    public BigDecimal getZhkyxj_t1() {
        return zhkyxj_t1;
    }

    public void setZhkyxj_t1(BigDecimal zhkyxj_t1) {
        this.zhkyxj_t1 = zhkyxj_t1;
    }

    public BigDecimal getTghkyxj_t() {
        return tghkyxj_t;
    }

    public void setTghkyxj_t(BigDecimal tghkyxj_t) {
        this.tghkyxj_t = tghkyxj_t;
    }

    public BigDecimal getTghkyxj_t1() {
        return tghkyxj_t1;
    }

    public void setTghkyxj_t1(BigDecimal tghkyxj_t1) {
        this.tghkyxj_t1 = tghkyxj_t1;
    }

    public BigDecimal getTdkyxj_t() {
        return tdkyxj_t;
    }

    public void setTdkyxj_t(BigDecimal tdkyxj_t) {
        this.tdkyxj_t = tdkyxj_t;
    }

    public BigDecimal getTdkyxj_t1() {
        return tdkyxj_t1;
    }

    public void setTdkyxj_t1(BigDecimal tdkyxj_t1) {
        this.tdkyxj_t1 = tdkyxj_t1;
    }

    public BigDecimal getZccc_qmze() {
        return zccc_qmze;
    }

    public void setZccc_qmze(BigDecimal zccc_qmze) {
        this.zccc_qmze = zccc_qmze;
    }

    public BigDecimal getZcky_qmze() {
        return zcky_qmze;
    }

    public void setZcky_qmze(BigDecimal zcky_qmze) {
        this.zcky_qmze = zcky_qmze;
    }

    public BigDecimal getNewRate() {
        return newRate;
    }

    public void setNewRate(BigDecimal newRate) {
        this.newRate = newRate;
    }
    
}
