package com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InfoDisProdtrdEntity extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 确认日
     */
    private Date confirmDate;

    /**
     * 申购份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal subAmt;

    /**
     * 赎回份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redAmt;

    /**
     * 红利再投结转份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusAmt;

    /**
     * 产品份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID
     */
    private String adapterId;

    /**
     * 父产品代码
     */
    private String fprodId;
    
    /**
     * 申购金额
     */
    private BigDecimal subTradePrince;
    
    /**
     * 赎回金额
     */
    private BigDecimal redTradePrince;
    
    /**
     * 赎回利息
     */
    private BigDecimal redIntAmt;
    
    
    
    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 确认日
     * @return 确认日
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置 确认日
     * @param confirmDate 确认日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 取得 申购份额
     * @return 申购份额
     */
    public BigDecimal getSubAmt() {
        return subAmt;
    }

    /**
     * 设置 申购份额
     * @param subAmt 申购份额
     */
    public void setSubAmt(BigDecimal subAmt) {
        this.subAmt = subAmt;
    }

    /**
     * 取得 赎回份额
     * @return 赎回份额
     */
    public BigDecimal getRedAmt() {
        return redAmt;
    }

    /**
     * 设置 赎回份额
     * @param redAmt 赎回份额
     */
    public void setRedAmt(BigDecimal redAmt) {
        this.redAmt = redAmt;
    }

    /**
     * 取得 红利再投结转份额
     * @return 红利再投结转份额
     */
    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    /**
     * 设置 红利再投结转份额
     * @param bonusAmt 红利再投结转份额
     */
    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    /**
     * 取得 产品份额
     * @return 产品份额
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 产品份额
     * @param capital 产品份额
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID
     * @return 适配器ID
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID
     * @param adapterId 适配器ID
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
	public String getFprodId() {
		return fprodId;
	}

	/**
	 * 设置 父产品代码
	 * @param fprodId 父产品代码
	 */
	public void setFprodId(String fprodId) {
		this.fprodId = fprodId;
	}

	/**取得 申购金额
	 * @return 申购金额
	 */
	public BigDecimal getSubTradePrince() {
		return subTradePrince;
	}

	/**设置 申购金额
	 * @param subTradePrince 申购金额
	 */
	public void setSubTradePrince(BigDecimal subTradePrince) {
		this.subTradePrince = subTradePrince;
	}

	/**取得 赎回金额
	 * @return 赎回金额
	 */
	public BigDecimal getRedTradePrince() {
		return redTradePrince;
	}

	/**设置 赎回金额
	 * @param redTradePrince 赎回金额
	 */
	public void setRedTradePrince(BigDecimal redTradePrince) {
		this.redTradePrince = redTradePrince;
	}

	/**取得 赎回利息
	 * @return 赎回利息
	 */
	public BigDecimal getRedIntAmt() {
		return redIntAmt;
	}

	/**设置 赎回利息
	 * @param redIntAmt 赎回利息
	 */
	public void setRedIntAmt(BigDecimal redIntAmt) {
		this.redIntAmt = redIntAmt;
	}
    
    
}