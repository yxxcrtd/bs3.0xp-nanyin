package com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.trade.cstpTrdMatchInfo.CstpTrdMatchInfo;
import com.joyintech.tams.invest.trade.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CstpTrdMatchInfoDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
public class CstpTrdMatchInfoDto extends CstpTrdMatchInfo {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7438510730454849159L;

    private List<TrdMatchInfoDto> trdMatchInfos;
    
    private List<CstpSelectDto> cstpSelectDtoList;//cstp列表
    
    private List<TrdCommandReceive001Dto> trdCommandReceive001DtoList;//委托列表

    private String finprodMarketId;

    public String getMatchTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.下行交易匹配类型, super.getMatchType());
    }

    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getSettleSpreedStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, super.getSettleSpreed());
    }
    public String getFinprodTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,super.getFinprodType());
    }
    public String getInvAimStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
    }

	public List<TrdMatchInfoDto> getTrdMatchInfos() {
		return trdMatchInfos;
	}

	public void setTrdMatchInfos(List<TrdMatchInfoDto> trdMatchInfos) {
		this.trdMatchInfos = trdMatchInfos;
	}

	public List<CstpSelectDto> getCstpSelectDtoList() {
		return cstpSelectDtoList;
	}

	public void setCstpSelectDtoList(List<CstpSelectDto> cstpSelectDtoList) {
		this.cstpSelectDtoList = cstpSelectDtoList;
	}

	public List<TrdCommandReceive001Dto> getTrdCommandReceive001DtoList() {
		return trdCommandReceive001DtoList;
	}

	public void setTrdCommandReceive001DtoList(List<TrdCommandReceive001Dto> trdCommandReceive001DtoList) {
		this.trdCommandReceive001DtoList = trdCommandReceive001DtoList;
	}

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
