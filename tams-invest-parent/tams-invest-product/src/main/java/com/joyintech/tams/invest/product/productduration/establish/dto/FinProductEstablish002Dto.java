/*
 * 文件名：FinProductEstablish002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品认申购新建管理
 * 
 * @author ejupiter
 * @version 1.0 2019年12月25日
 * @see FinProductEstablish002Dto
 * @since 1.0
 */
@PageId("FinProductEstablish002")
public class FinProductEstablish002Dto extends TrdFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5562418617863995085L;
    
    /**
     * 是否续投
     */
    private String continueFlag;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 处理模式
     */
    private String dealMode;
    
    /**
     * 撤销原因
     */
    private String revokeReason;

    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 业务编号
     */
    private String bussId;

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 组合代码
     */
    private String ptlId;
    
    /**
     * 有效状态
     */
    private String effectStatus;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 产品名称
     */
    private String finprodName;
    
    /**
     * 产品要素起息日
     */
    private Date vdate2;
    
    /**
     * 是否保本
     */
    private String isGuarantee;
    
    /**
     * 运作模式
     */
    private String operationType;
    
    /**
     * 计息基础
     */
    private String Basis;   
    
    /**
     * 是否分层
     */
    private String isLay;
    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;
    
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 基础信息
     */
//    private transient FinProductBuild002Dto finProductInfoDto;
    
    /**
     * 费用信息
     */
    private transient List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList;

    /**
     * 产品募集金额信息
     */
//    @Valid
//    private transient FinProductEstablishDealTotal finProductEstablishDealTotal;

    /**
     * 分层金额明细
     */
    @Valid
    private transient List<FinProductEstablishLayerDto> finProductEstablishLayerDtoList;

    /**
     * 分行募集信息
     */
    @Valid
    private transient List<FinProductEstablishBranchDto> finProductEstablishBranchDtoList;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 获取 finProductInfoDto
     * 
     * @return finProductInfoDto.
     */
//    public FinProductBuild002Dto getFinProductInfoDto() {
//        return finProductInfoDto;
//    }

    /**
     * 设置 finProductInfoDto
     * 
     * @param finProductInfoDto finProductInfoDto
     */
//    public void setFinProductInfoDto(FinProductBuild002Dto finProductInfoDto) {
//        this.finProductInfoDto = finProductInfoDto;
//    }

    /**
     * 获取 finProductEstablishDealTotal
     * 
     * @return finProductEstablishDealTotal.
     */
//    public FinProductEstablishDealTotal getFinProductEstablishDealTotal() {
//        return finProductEstablishDealTotal;
//    }

    /**
     * 设置 finProductEstablishDealTotal
     * 
     * @param finProductEstablishDealTotal finProductEstablishDealTotal
     */
//    public void setFinProductEstablishDealTotal(FinProductEstablishDealTotal finProductEstablishDealTotal) {
//        this.finProductEstablishDealTotal = finProductEstablishDealTotal;
//    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 finProductEstablishLayerDtoList
     * 
     * @return finProductEstablishLayerDtoList.
     */
    public List<FinProductEstablishLayerDto> getFinProductEstablishLayerDtoList() {
        return finProductEstablishLayerDtoList;
    }

    /**
     * 设置 finProductEstablishLayerDtoList
     * 
     * @param finProductEstablishLayerDtoList finProductEstablishLayerDtoList
     */
    public void setFinProductEstablishLayerDtoList(List<FinProductEstablishLayerDto> finProductEstablishLayerDtoList) {
        this.finProductEstablishLayerDtoList = finProductEstablishLayerDtoList;
    }

    /**
     * 获取 finProductEstablishBranchDtoList
     * 
     * @return finProductEstablishBranchDtoList.
     */
    public List<FinProductEstablishBranchDto> getFinProductEstablishBranchDtoList() {
        return finProductEstablishBranchDtoList;
    }

    /**
     * 设置 finProductEstablishBranchDtoList
     * 
     * @param finProductEstablishBranchDtoList finProductEstablishBranchDtoList
     */
    public void setFinProductEstablishBranchDtoList(List<FinProductEstablishBranchDto> finProductEstablishBranchDtoList) {
        this.finProductEstablishBranchDtoList = finProductEstablishBranchDtoList;
    }

    /**
     * 获取 bussTrdTransferOrder
     * 
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * 
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 isGuarantee
     * 
     * @return isGuarantee.
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置 isGuarantee
     * 
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 tradeId
     * 
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     * 
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }
    /**
     * 获取交易类型
     * 
     * @return 交易类型
     */
    @PrimaryKey(Field.PK_2)
    public String getDealType() {
        return DictCodeList.共通业务类型_产品申购.getValue();
    }

//    @PrimaryKey(Field.PK_3)
    public String getVdateStr() {
        return DateUtil.format(this.getVdate());
    }
    
    public String getMateStr() {
        return DateUtil.format(this.getMdate());
    }

    /**
     * 获取 bussFinProductFeeInfoDtoList
     * @return bussFinProductFeeInfoDtoList.
     */
    public List<BussFinProductFeeInfoDto> getBussFinProductFeeInfoDtoList() {
        return bussFinProductFeeInfoDtoList;
    }

    
    /**
     * 设置 bussFinProductFeeInfoDtoList
     * @param bussFinProductFeeInfoDtoList bussFinProductFeeInfoDtoList
     */
    public void setBussFinProductFeeInfoDtoList(List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList) {
        this.bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDtoList;
    }

    
    /**
     * 获取 revokeReason
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }
    
    /**
     * 设置 revokeReason
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

	public StlPayOrders getStlPayOrders() {
		return stlPayOrders;
	}

	public void setStlPayOrders(StlPayOrders stlPayOrders) {
		this.stlPayOrders = stlPayOrders;
	}
	
//	@NotEmpty(fieldName = "FinProductBuild002.basis")
	public String getBasis() {
		return Basis;
	}

	public void setBasis(String basis) {
		Basis = basis;
	}

	public String getIsLay() {
		return isLay;
	}

	public void setIsLay(String isLay) {
		this.isLay = isLay;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public String getPtlId() {
		return ptlId;
	}

	public void setPtlId(String ptlId) {
		this.ptlId = ptlId;
	}

	@NotEmpty(fieldName = "FinProductEstablish002.finprodMarketId",groups={ValidateGroupEdit.class})
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	@NotEmpty(fieldName = "FinProductEstablish002.finprodAbbr",groups={ValidateGroupEdit.class})
	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	@Override
	@NotEmpty(fieldName = "FinProductEstablish002.vdate",groups={ValidateGroupEdit.class})
	public Date getVdate() {
		return super.getVdate();
	}

	@Override
	@NotEmpty(fieldName = "FinProductEstablish002.mdate",groups={ValidateGroupEdit.class})
	public Date getMdate() {
		return super.getMdate();
	}

	@Override
	@NotEmpty(fieldName = "FinProductEstablish002.ccy",groups={ValidateGroupEdit.class})
	public String getCcy() {
		return super.getCcy();
	}

	@Override
//	@NotEmpty(fieldName = "FinProductEstablish002.lastAmt",groups={ValidateGroupEdit.class})
	public BigDecimal getLastAmt() {
		return super.getLastAmt();
	}

	@Override
//	@NotEmpty(fieldName = "FinProductEstablish002.currentSub",groups={ValidateGroupEdit.class})
	public BigDecimal getCurrentSub() {
		return super.getCurrentSub();
	}

	@Override
//	@NotEmpty(fieldName = "FinProductEstablish002.lastAmtSub",groups={ValidateGroupEdit.class})
	public BigDecimal getLastAmtSub() {
		return super.getLastAmtSub();
	}

	@Override
//	@NotEmpty(fieldName = "FinProductEstablish002.lastRed",groups={ValidateGroupEdit.class})
	public BigDecimal getLastRed() {
		return super.getLastRed();
	}

	@Override
//	@NotEmpty(fieldName = "FinProductEstablish002.accountingAmt",groups={ValidateGroupEdit.class})
	public BigDecimal getAccountingAmt() {
		return super.getAccountingAmt();
	}

	public Date getVdate2() {
		return vdate2;
	}
	
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setVdate2(Date vdate2) {
		this.vdate2 = vdate2;
	}

    @PrimaryKey(Field.PK_3)
	public String getBussId() {
		return bussId;
	}

	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	public String getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}
	
	public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态,  effectStatus);
    }

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getContinueFlag() {
		return continueFlag;
	}

	public void setContinueFlag(String continueFlag) {
		this.continueFlag = continueFlag;
	}
//add by yejiejie 2019-2-20 start
	//期初客户数
	private BigDecimal customersBeginNum;

	public BigDecimal getCustomersBeginNum() {
		return customersBeginNum;
	}

	public void setCustomersBeginNum(BigDecimal customersBeginNum) {
		this.customersBeginNum = customersBeginNum;
	}

	//增加客户数
	private BigDecimal customersAddNum;

	public BigDecimal getCustomersAddNum() {
		return customersAddNum;
	}

	public void setCustomersAddNum(BigDecimal customersAddNum) {
		this.customersAddNum = customersAddNum;
	}

	//add by yejiejie 2019-2-20 end
	//add by yejiejie 2019-3-10 start
	//交易方向
	private String ps;

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}
	//add by yejiejie 2019-3-10 end
	/**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return StringUtil.isNotEmpty(profitType)?this.profitType:DictCodeList.收益类型_收益型.getValue();
    }

    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
}
