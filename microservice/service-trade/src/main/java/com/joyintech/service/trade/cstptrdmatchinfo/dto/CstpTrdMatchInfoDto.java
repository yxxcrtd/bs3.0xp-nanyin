package com.joyintech.service.trade.cstptrdmatchinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.invest.common.dto.trade.cstpTrdMatchInfo.CstpTrdMatchInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName trdMatchInfoDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
public class CstpTrdMatchInfoDto extends CstpTrdMatchInfo {

    private String finprodMarketId;

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
    //add by chenjiaxing 费用计算划分改造 20210804 start
    /**
     * 计费类型
     */
    private String chargeType;

    /**
     * 计费基数
     */
    private String chargeBasis;
    /**
     * 生效日
     */
    private Date effDate;
    /**
     * 是否多券种
     */
    private String isMlti;
    /**
     * 费用上限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal feeMaxi;

    /**
     * 费用下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMini;

    /**
     * 计费值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeValue;
    /**
     * 计费比率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRate;

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeBasis() {
        return chargeBasis;
    }

    public void setChargeBasis(String chargeBasis) {
        this.chargeBasis = chargeBasis;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getIsMlti() {
        return isMlti;
    }

    public void setIsMlti(String isMlti) {
        this.isMlti = isMlti;
    }

    public BigDecimal getFeeMaxi() {
        return feeMaxi;
    }

    public void setFeeMaxi(BigDecimal feeMaxi) {
        this.feeMaxi = feeMaxi;
    }

    public BigDecimal getFeeMini() {
        return feeMini;
    }

    public void setFeeMini(BigDecimal feeMini) {
        this.feeMini = feeMini;
    }

    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }

    public BigDecimal getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }
    //add by chenjiaxing 费用计算划分改造 20210804 end
}
