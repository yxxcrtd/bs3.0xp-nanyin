package com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TrdSecOverdueDurationDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class TrdSecOverdueDurationDto extends TrdFinProductDealTotal {

    private String cashType;//类型
    private String recType;//应收款类型
    
    /**
     * 未支付本息总额(理论本息总额
     */
    private BigDecimal theoryAmt;//理论本息总额

    /**
     * 未支付本金(理论本金
     */
    private BigDecimal theoryPrin;//未支付本金
    
    /**
     * 未支付利息(理论利息
     */
    private BigDecimal theoryInt;//未支付利息
    
    /**
     * 理论罚息总额
     */
    private BigDecimal theoryPenaltyInt;//理论罚息总额
    
//    private BigDecimal fpriceAmt;//实收本息总额
//    
//    private BigDecimal intAmt;//实收利息
    
    /**
     * 实收罚息总额
     */
    private BigDecimal penaltyIntamt;//实收罚息总额

    private String isAccrPenaltyInt;//是否计提罚息

    private String penaltyBaseType;//罚息计提基数

    private String fxYield;//罚息利率

    private String isRushBack;//截止后是否冲回

    private String portfolioName;//组合名称

    private String finprodName;//资产名称

    private String secManageAcctName;//账户名称

    private Date fxvdate;
    private Date fxmdate;//罚息截止日

    private String fxmdateStr;
    private String fxvdateStr;
    private String finprodMarketId;

    //剩余应收金额
    private BigDecimal residueAmount;
    private Date endPayDate;//最终支付日
    private Long bjBranch;//本金分支序号
    private Long liBranch;//利息分支序号

    private String withoutBranch;//还本不带息分支序号
    private String branchType;//分支类型

    private String vdateStr;
    private String mdateStr;

    private String branchAll;//分支序号汇总

    private String cashIds;//现金流代码汇总
    private String finprodTypeName;//资产类型名称
    
    /**
     * 交易编号
     */
    private String busiCode;
    
    /**
     * 通道收取方式
     */
    private String collectType;
    
    /**
     * 通道费
     */
    private BigDecimal channelFee;

    private String basis;


    private String validatorType;

    // 多级法人改造 add by caiqun 20200615
    /**
     * 机构名称
     */
    private String institutionName;
    // 多级法人改造 add by caiqun 20200615

    public String getValidatorType() {
        return validatorType;
    }

    public void setValidatorType(String validatorType) {
        this.validatorType = validatorType;
    }

    /**
     * 利息信息明细
     * 
     */
    private List<FinAssetIntInfo> finAssetIntInfoList;
    

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 是否是通道
     */
    private boolean channel;
    
    private String secAcctId;

    private String chIldName;

    private String tradeStausType;

    private String isRevoke;


    private String childPortfolioIds;

    private String finprodTypeGz;
    private String finprodTypeTg;


    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    
    /**
     * Amount 数量（还本不带息应收款数量）
     * @return
     */
    private BigDecimal Amount;
    

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 撤销理由
     */
    private String revokeReason;
    
    /**
     * 子组合利息明细
     */
    private List<FinAssetIntInfo> childPortfolioInfo;

    public String getWithoutBranch() {
        return withoutBranch;
    }

    public void setWithoutBranch(String withoutBranch) {
        this.withoutBranch = withoutBranch;
    }

    public String getFinprodTypeGz() {
        return finprodTypeGz;
    }

    public void setFinprodTypeGz(String finprodTypeGz) {
        this.finprodTypeGz = finprodTypeGz;
    }

    public String getFinprodTypeTg() {
        return finprodTypeTg;
    }

    public void setFinprodTypeTg(String finprodTypeTg) {
        this.finprodTypeTg = finprodTypeTg;
    }

    public String getChildPortfolioIds() {
        return childPortfolioIds;
    }

    public void setChildPortfolioIds(String childPortfolioIds) {
        this.childPortfolioIds = childPortfolioIds;
    }

    public String getIsRevoke() {
        return isRevoke;
    }

    public void setIsRevoke(String isRevoke) {
        this.isRevoke = isRevoke;
    }

    public String getTradeStausType() {
        return tradeStausType;
    }

    public void setTradeStausType(String tradeStausType) {
        this.tradeStausType = tradeStausType;
    }

    public String getChIldName() {
        return chIldName;
    }

    public void setChIldName(String chIldName) {
        this.chIldName = chIldName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getFinprodTypeName() {
        return finprodTypeName;
    }

    public void setFinprodTypeName(String finprodTypeName) {
        this.finprodTypeName = finprodTypeName;
    }

    public String getCashIds() {
        return cashIds;
    }

    public void setCashIds(String cashIds) {
        this.cashIds = cashIds;
    }

    public String getBranchAll() {
        return branchAll;
    }

    public void setBranchAll(String branchAll) {
        this.branchAll = branchAll;
    }

    public String getVdateStr() {
        return vdateStr;
    }

    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }

    public String getMdateStr() {
        return mdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public Long getBjBranch() {
        return bjBranch;
    }

    public void setBjBranch(Long bjBranch) {
        this.bjBranch = bjBranch;
    }

    public Long getLiBranch() {
        return liBranch;
    }

    public void setLiBranch(Long liBranch) {
        this.liBranch = liBranch;
    }

    public Date getEndPayDate() {
        return endPayDate;
    }

    public void setEndPayDate(Date endPayDate) {
        this.endPayDate = endPayDate;
    }

    public BigDecimal getResidueAmount() {
        return residueAmount;
    }

    public void setResidueAmount(BigDecimal residueAmount) {
        this.residueAmount = residueAmount;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public Date getFxmdate() {
        return fxmdate;
    }

    public void setFxmdate(Date fxmdate) {
        this.fxmdate = fxmdate;
    }

    public String getFxmdateStr() {
        return fxmdateStr;
    }

    public void setFxmdateStr(String fxmdateStr) {
        this.fxmdateStr = fxmdateStr;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getSecManageAcctName() {
        return secManageAcctName;
    }

    public void setSecManageAcctName(String secManageAcctName) {
        this.secManageAcctName = secManageAcctName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    

    public String getIsAccrPenaltyInt() {
        return isAccrPenaltyInt;
    }

    public void setIsAccrPenaltyInt(String isAccrPenaltyInt) {
        this.isAccrPenaltyInt = isAccrPenaltyInt;
    }

    public String getPenaltyBaseType() {
        return penaltyBaseType;
    }

    public void setPenaltyBaseType(String penaltyBaseType) {
        this.penaltyBaseType = penaltyBaseType;
    }

    public String getFxYield() {
        return fxYield;
    }

    public void setFxYield(String fxYield) {
        this.fxYield = fxYield;
    }

    public String getIsRushBack() {
        return isRushBack;
    }

    public void setIsRushBack(String isRushBack) {
        this.isRushBack = isRushBack;
    }

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public BigDecimal getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	

	public List<FinAssetIntInfo> getFinAssetIntInfoList() {
		return finAssetIntInfoList;
	}

	public void setFinAssetIntInfoList(List<FinAssetIntInfo> finAssetIntInfoList) {
		this.finAssetIntInfoList = finAssetIntInfoList;
	}

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	/**
	 * @return the channel
	 */
	public boolean isChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(boolean channel) {
		this.channel = channel;
	}

    public Date getFxvdate() {
        return fxvdate;
    }

    public void setFxvdate(Date fxvdate) {
        this.fxvdate = fxvdate;
    }

    public String getFxvdateStr() {
        return fxvdateStr;
    }

    public void setFxvdateStr(String fxvdateStr) {
        this.fxvdateStr = fxvdateStr;
    }

	public BigDecimal getTheoryAmt() {
		return theoryAmt;
	}

	public void setTheoryAmt(BigDecimal theoryAmt) {
		this.theoryAmt = theoryAmt;
	}

	public BigDecimal getTheoryPrin() {
		return theoryPrin;
	}

	public void setTheoryPrin(BigDecimal theoryPrin) {
		this.theoryPrin = theoryPrin;
	}

	public BigDecimal getTheoryInt() {
		return theoryInt;
	}

	public void setTheoryInt(BigDecimal theoryInt) {
		this.theoryInt = theoryInt;
	}

	public BigDecimal getTheoryPenaltyInt() {
		return theoryPenaltyInt;
	}

	public void setTheoryPenaltyInt(BigDecimal theoryPenaltyInt) {
		this.theoryPenaltyInt = theoryPenaltyInt;
	}

	public BigDecimal getPenaltyIntamt() {
		return penaltyIntamt;
	}

	public void setPenaltyIntamt(BigDecimal penaltyIntamt) {
		this.penaltyIntamt = penaltyIntamt;
	}

	/**
	 * @return 数量（还本不带息应收款数量）
	 */
	public BigDecimal getAmount() {
		return Amount;
	}

	/**
	 * @param 数量（还本不带息应收款数量）
	 */
	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
		return baseTotalTradeInfoDto;
	}

	public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
		this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public List<FinAssetIntInfo> getChildPortfolioInfo() {
		return childPortfolioInfo;
	}

	public void setChildPortfolioInfo(List<FinAssetIntInfo> childPortfolioInfo) {
		this.childPortfolioInfo = childPortfolioInfo;
	}
    // 多级法人改造 add by caiqun 20200615
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    // 多级法人改造 add by caiqun 20200615
}
