/*
 * 文件名：TrdPlanCurrDuration001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人："??????é1?"
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdplancurrduration.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;
import com.joyintech.tams.invest.period.trdplancurrduration.validator.TrdPlanCurrDuration001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 计划类货币型存续期
 * 〈功能详细描述〉
 * @author "丁志鹏"
 * @version 1.0 2019年1月29日
 * @see TrdPlanCurrDuration001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdPlanCurrDuration001Validator.class, groups = {
	    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdPlanCurrDuration001Dto extends TrdFundDurationCommonDto implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3974078160802737259L;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
    /**
     * 发行人
     */
    private String issuer;
    /**
     * 业务类型00 新建模式 01 货币再投，02 非货除权 03 非货分红 04 非货再投（用于画面显示及业务操作类型甄别）
     */
    private String type;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 控件Id
     */
    private String elementId;
    /**
     * 模式（辅助标记功能）
     */
    private String mode;
    /**
     * 有效状态
     */
    private String statusValid;
    /**
     * 资产代码
     */
    @NotEmpty(fieldName = "TrdFundDuration001.finprodMarketId",groups= {ValidateGroupEdit.class})
    private String finprodMarketId;
    /**
     * 资产名称
     */
    @NotEmpty(fieldName = "TrdFundDuration001.finprodMarketName",groups= {ValidateGroupEdit.class})
    private String finprodMarketName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 分红类型
     */
    @NotEmpty(fieldName = "TrdFundDuration001.bonusMode",groups= {ValidateGroupEdit.class})
    private String bonusMode;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权益登记日
     */
    private Date registDate;
    /**
     * 除权日
     */
    private Date exRightsDate;
    /**
     * 分红到账日
     */
    private Date arriveDate;
    /**
     * 红利再投日
     */
    private Date reInvesDate;
    /**
     * 权益登记日份额
     */
    private BigDecimal registShare;
    /**
     * 单位分红净值
     */
    @Amount(maxDecBit=4, fieldName = "TrdFundDuration001.unitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal unitCprice;
    /**
     * 分红金额
     */
    private BigDecimal tradeAmt;

    /**
     * 分红份额
     */
    private BigDecimal qty;
    public String getUpdateFlag(){
        if(!StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())||StringUtil.isNotEmpty(getHasFlow())){
            return "02";
        }
        else if(StringUtil.equals(type,"06")||StringUtil.equals(type,"01")){
            return "02";
        }
        else{
            return "01";
        }
    }
//  画面数据状态
  public String getStatusValidStr(){
      return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, this.statusValid);
  }
  //   交易方向
  public String getPsStr(){
      return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPs());
  }
  //   分红类型
  public String getBonusModeStr(){
      return SysDataUtil.getNameFromDataDict(DictCode.分红方式, this.bonusMode);
  }
    /**
     * PK1
     * @return
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId_PK() {
        return StringUtil.isNotEmpty(super.getTotleTradeId())? super.getTotleTradeId(): IDGenerator.generate();
    }
    /**
     * 获取 PK2
     * @return elementId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusinessCode() {
        return DictCodeList.共通业务类型_计划类资产_货币型_存续期.getValue();
    }
    /**
     * 获取 PK3
     * @return getbTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getbTradeId() {
        return super.getbTradeId();
    }
    /**
     * 获取 PK4
     * @return getpTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getpTradeId() {
        return super.getpTradeId();
    }
	/**
	 * 获取 bussTrdTransferOrder
	 * @return bussTrdTransferOrder.
	 */
	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}
	/**
	 * 设置 bussTrdTransferOrder
	 * @param bussTrdTransferOrder bussTrdTransferOrder
	 */
	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}
	/**
	 * 获取 repIssuePartyId
	 * @return repIssuePartyId.
	 */
	public String getRepIssuePartyId() {
		return repIssuePartyId;
	}
	/**
	 * 设置 repIssuePartyId
	 * @param repIssuePartyId repIssuePartyId
	 */
	public void setRepIssuePartyId(String repIssuePartyId) {
		this.repIssuePartyId = repIssuePartyId;
	}
	/**
	 * 获取 issuer
	 * @return issuer.
	 */
	public String getIssuer() {
		return issuer;
	}
	/**
	 * 设置 issuer
	 * @param issuer issuer
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * 获取 type
	 * @return type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置 type
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}
	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	/**
	 * 获取 elementId
	 * @return elementId.
	 */
	public String getElementId() {
		return elementId;
	}
	/**
	 * 设置 elementId
	 * @param elementId elementId
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	/**
	 * 获取 mode
	 * @return mode.
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * 设置 mode
	 * @param mode mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * 获取 statusValid
	 * @return statusValid.
	 */
	public String getStatusValid() {
		return statusValid;
	}
	/**
	 * 设置 statusValid
	 * @param statusValid statusValid
	 */
	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}
	/**
	 * 获取 finprodMarketId
	 * @return finprodMarketId.
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	/**
	 * 设置 finprodMarketId
	 * @param finprodMarketId finprodMarketId
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	/**
	 * 获取 finprodMarketName
	 * @return finprodMarketName.
	 */
	public String getFinprodMarketName() {
		return finprodMarketName;
	}
	/**
	 * 设置 finprodMarketName
	 * @param finprodMarketName finprodMarketName
	 */
	public void setFinprodMarketName(String finprodMarketName) {
		this.finprodMarketName = finprodMarketName;
	}
	/**
	 * 获取 portfolioId
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}
	/**
	 * 设置 portfolioId
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	/**
	 * 获取 bonusMode
	 * @return bonusMode.
	 */
	public String getBonusMode() {
		return bonusMode;
	}
	/**
	 * 设置 bonusMode
	 * @param bonusMode bonusMode
	 */
	public void setBonusMode(String bonusMode) {
		this.bonusMode = bonusMode;
	}
	/**
	 * 获取 portfolioName
	 * @return portfolioName.
	 */
	public String getPortfolioName() {
		return portfolioName;
	}
	/**
	 * 设置 portfolioName
	 * @param portfolioName portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	/**
	 * 获取 remark
	 * @return remark.
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置 remark
	 * @param remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取 registDate
	 * @return registDate.
	 */
	public Date getRegistDate() {
		return registDate;
	}
	/**
	 * 设置 registDate
	 * @param registDate registDate
	 */
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	/**
	 * 获取 exRightsDate
	 * @return exRightsDate.
	 */
	public Date getExRightsDate() {
		return exRightsDate;
	}
	/**
	 * 设置 exRightsDate
	 * @param exRightsDate exRightsDate
	 */
	public void setExRightsDate(Date exRightsDate) {
		this.exRightsDate = exRightsDate;
	}
	/**
	 * 获取 arriveDate
	 * @return arriveDate.
	 */
	public Date getArriveDate() {
		return arriveDate;
	}
	/**
	 * 设置 arriveDate
	 * @param arriveDate arriveDate
	 */
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	/**
	 * 获取 reInvesDate
	 * @return reInvesDate.
	 */
	public Date getReInvesDate() {
		return reInvesDate;
	}
	/**
	 * 设置 reInvesDate
	 * @param reInvesDate reInvesDate
	 */
	public void setReInvesDate(Date reInvesDate) {
		this.reInvesDate = reInvesDate;
	}
	/**
	 * 获取 registShare
	 * @return registShare.
	 */
	public BigDecimal getRegistShare() {
		return registShare;
	}
	/**
	 * 设置 registShare
	 * @param registShare registShare
	 */
	public void setRegistShare(BigDecimal registShare) {
		this.registShare = registShare;
	}
	/**
	 * 获取 unitCprice
	 * @return unitCprice.
	 */
	public BigDecimal getUnitCprice() {
		return unitCprice;
	}
	/**
	 * 设置 unitCprice
	 * @param unitCprice unitCprice
	 */
	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}
	/**
	 * 获取 tradeAmt
	 * @return tradeAmt.
	 */
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	/**
	 * 设置 tradeAmt
	 * @param tradeAmt tradeAmt
	 */
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	/**
	 * 获取 qty
	 * @return qty.
	 */
	public BigDecimal getQty() {
		return qty;
	}
	/**
	 * 设置 qty
	 * @param qty qty
	 */
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
    
}
