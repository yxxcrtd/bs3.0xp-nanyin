package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.PrdBonusPlanN;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.validator.FinNetProductBonusSchemeValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author liuwei
 * @version 1.0 2018年10月17日
 * @see FinNetProductBonusSchemeDto
 * @since 1.0
 */
@PageId("FinNetProductBonusScheme002")
@ValidLogic(targetClass = FinNetProductBonusSchemeValidator.class, groups = {ValidateGroupEdit.class,
ValidateGroupDelete.class})
public class FinNetProductBonusSchemeDto extends PrdBonusPlanN implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    /**
     * 金融产品名称
     */
    private String finprodName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 撤销说明
     */
    private String revokeReason;
    /**
     * 单位净值小数位
     */
    private Long unitvalueDec;
    /**
     * 申购确认速度，如果资产只有一个确认速度，则存该字段
     */
    private Long purSpeed;
    /**
     * 是否被选中
     */
    private String isChecked;
    /**
     * 当日未分配利润，对于预期收益产品存应计客户收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidIntincexp;
    /**
     * 当日资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetAmt;
    /**
     * 日期
     */
    private Date cdate;
    /**
     * 当前份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyShareAmt;

    /**
     * 当日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnitValue;
    /**
     * 当日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netvalue;
    /**
     * 选择数据
     */
    private List<FinNetProductBonusSchemeDto> selectedItem;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 币种中文名称
     */
    private String ccyName;
    
    /**
     * 实际分红总金额
     */
    private BigDecimal actTotalAmt;
    /**
     * 实际单位分红金额
     */
    private BigDecimal actUnitBonusVlu;
    /**
     * 权益登记日份额
     */
    private BigDecimal regDateNum;
    /**
     * 现金红利金额
     */
    private BigDecimal bonusCashAmt;
    /**
     * 红利再投金额
     */
    private BigDecimal bonusShareAmt;
    /**
     * 红利再投份额
     */
    private BigDecimal bonusShareNum;
    
    /**
     * 交易类型：方案登记、方案确认、现金分红、红利再投
     */
    private String dealType;
    
    /**
     * 交易id
     */
    private String tradeId;
    
    /**
     * 实际红利下发日
     */
    private Date actBonusDate;
    /**
     * 交易备注字段
     */
    private String bonusDealRemark;
    
    /**
     * 红利再投确认日
     */
    private Date confirmDate;
    
    /**
     * 已分红金额 --包括现金分红和红利再投
     */
    private BigDecimal totalBonusCashAmt;
    
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    
    /**
     * 前置交易编号 ---现金分红和分红金额确认的前置交易编号为分红金额确认的交易编号
     */
    private String bTradeId;
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 产品处理模式
     */
    private String dealMode;
    /**
     * 红利再投初始化净值
     */
    private BigDecimal shareTdyUnitValue;
    
    /**
     * 是否周期型产品 Y/N
     */
    private String isCycle;
    
    // 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;
    
    /**
     * 是否轧差清算
     */
    private String liquidationYesno;
    
    /**
     * 交易方向(风险接口需要用到)
     */
    private String ps;
    
    /*期次子产品信息 start */
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 期次代码
     */
    private String periodId;
    
    /**
     * 期次开始日
     */
    private Date subCycleStartDate;
    
    /**
     * 期次结束日
     */
    private Date subCycleEndDate;
    
    /**
     * 收益类型
     */
    private String profitType;    
    /**
     * 期次子产品列表
     */
    private List<FinNetProductBonusSchemeDto> listCycleProductInfo;
    
	/*期次子产品信息 end */
    /**
     * 取得 分红方案，按单位分红、按总额分红、净值归一
     * @return 分红方案，按单位分红、按总额分红、净值归一
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.bonuScalcFlag",groups={ValidateGroupEdit.class})
    public String getBonuScalcFlag() {
        return super.getBonuScalcFlag();
    }
    

    
    public List<FinNetProductBonusSchemeDto> getSelectedItem() {
        return selectedItem;
    }


    
    public void setSelectedItem(List<FinNetProductBonusSchemeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 取得 收益分配基准日
     * @return 收益分配基准日
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.baseDate",groups={ValidateGroupEdit.class})
    public Date getBaseDate() {
        return super.getBaseDate();
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.regDate",groups={ValidateGroupEdit.class})
    public Date getRegDate() {
        return super.getRegDate();
    }

    /**
     * 取得 除权日
     * @return 除权日
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.delDate",groups={ValidateGroupEdit.class})
    public Date getDelDate() {
        return super.getDelDate();
    }

    /**
     * 取得 分红日
     * @return 分红日
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.bonusDate",groups={ValidateGroupEdit.class})
    public Date getBonusDate() {
        return super.getBonusDate();
    }

    /**
     * 取得 净值日
     * @return 净值日
     */
    //@NotEmpty(fieldName = "FinNetProductBonusScheme002.vluRefDate",groups={ValidateGroupEdit.class})
    public Date getVluRefDate() {
        return super.getVluRefDate();
    }

    /**
     * 取得 分红方案编号
     * @return 分红方案编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBonusPlanId() {
        return super.getBonusPlanId();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    @NotEmpty(fieldName = "FinNetProductBonusScheme002.finprodMarketId",groups={ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getPortfolioId() {
		return portfolioId;
	}



	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}



	/**
     * 获取 finprodAbbr
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
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
     * 获取 pageModel
     *
     * @return pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     *
     * @return elementId
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
    public String getRevokeReason() {
		return revokeReason;
	}



	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}



	/**
     * 将分红方案对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getBonuScalcFlagAbbr(){
        return SysDataUtil.getNameFromDataDict(DictCode.分红方案, super.getBonuScalcFlag());
    }

    /**
     * 获取 unitvalueDec
     *
     * @return unitvalueDec
     */
    public Long getUnitvalueDec() {
        return unitvalueDec;
    }

    /**
     * 设置 unitvalueDec
     *
     * @param unitvalueDec unitvalueDec
     */
    public void setUnitvalueDec(Long unitvalueDec) {
        this.unitvalueDec = unitvalueDec;
    }

    /**
     * 获取 purSpeed
     *
     * @return purSpeed
     */
    public Long getPurSpeed() {
        return purSpeed;
    }

    /**
     * 设置 purSpeed
     *
     * @param purSpeed purSpeed
     */
    public void setPurSpeed(Long purSpeed) {
        this.purSpeed = purSpeed;
    }

    /**
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 isChecked
     *
     * @return isChecked
     */
    public String getIsChecked() {
        return isChecked;
    }

    /**
     * 设置 isChecked
     *
     * @param isChecked isChecked
     */
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * 获取 tdyUnpaidIntincexp
     *
     * @return tdyUnpaidIntincexp
     */
    public BigDecimal getTdyUnpaidIntincexp() {
        return tdyUnpaidIntincexp;
    }

    /**
     * 设置 tdyUnpaidIntincexp
     *
     * @param tdyUnpaidIntincexp tdyUnpaidIntincexp
     */
    public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
        this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
    }

    /**
     * 获取 tdyAssetAmt
     *
     * @return tdyAssetAmt
     */
    public BigDecimal getTdyAssetAmt() {
        return tdyAssetAmt;
    }

    /**
     * 设置 tdyAssetAmt
     *
     * @param tdyAssetAmt tdyAssetAmt
     */
    public void setTdyAssetAmt(BigDecimal tdyAssetAmt) {
        this.tdyAssetAmt = tdyAssetAmt;
    }

    /**
     * 获取 cdate
     *
     * @return cdate
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     *
     * @param cdate cdate
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 tdyShareAmt
     *
     * @return tdyShareAmt
     */
    public BigDecimal getTdyShareAmt() {
        return tdyShareAmt;
    }

    /**
     * 设置 tdyShareAmt
     *
     * @param tdyShareAmt tdyShareAmt
     */
    public void setTdyShareAmt(BigDecimal tdyShareAmt) {
        this.tdyShareAmt = tdyShareAmt;
    }

    /**
     * 获取 tdyUnitValue
     *
     * @return tdyUnitValue
     */
    public BigDecimal getTdyUnitValue() {
        return tdyUnitValue;
    }

    /**
     * 设置 tdyUnitValue
     *
     * @param tdyUnitValue tdyUnitValue
     */
    public void setTdyUnitValue(BigDecimal tdyUnitValue) {
        this.tdyUnitValue = tdyUnitValue;
    }

    /**
     * 获取 netvalue
     *
     * @return netvalue
     */
    public BigDecimal getNetvalue() {
        return netvalue;
    }

    /**
     * 设置 netvalue
     *
     * @param netvalue netvalue
     */
    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }
    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }
    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    /**
     * 获取 ccyName
     * @return ccyName.
     */
    public String getCcyName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
    }
    
    /**
     * 设置 ccyName
     * @param ccyName ccyName
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }



    /**
     * 获取 actTotalAmt
     * @return actTotalAmt.
     */
    public BigDecimal getActTotalAmt() {
        return actTotalAmt;
    }



    
    /**
     * 设置 actTotalAmt
     * @param actTotalAmt actTotalAmt
     */
    public void setActTotalAmt(BigDecimal actTotalAmt) {
        this.actTotalAmt = actTotalAmt;
    }



    
    /**
     * 获取 actUnitBonusVlu
     * @return actUnitBonusVlu.
     */
    public BigDecimal getActUnitBonusVlu() {
        return actUnitBonusVlu;
    }



    
    /**
     * 设置 actUnitBonusVlu
     * @param actUnitBonusVlu actUnitBonusVlu
     */
    public void setActUnitBonusVlu(BigDecimal actUnitBonusVlu) {
        this.actUnitBonusVlu = actUnitBonusVlu;
    }



    
    /**
     * 获取 regDateNum
     * @return regDateNum.
     */
    public BigDecimal getRegDateNum() {
        return regDateNum;
    }



    
    /**
     * 设置 regDateNum
     * @param regDateNum regDateNum
     */
    public void setRegDateNum(BigDecimal regDateNum) {
        this.regDateNum = regDateNum;
    }



    
    /**
     * 获取 bonusCashAmt
     * @return bonusCashAmt.
     */
    public BigDecimal getBonusCashAmt() {
        return bonusCashAmt;
    }



    
    /**
     * 设置 bonusCashAmt
     * @param bonusCashAmt bonusCashAmt
     */
    public void setBonusCashAmt(BigDecimal bonusCashAmt) {
        this.bonusCashAmt = bonusCashAmt;
    }



    
    /**
     * 获取 bonusShareAmt
     * @return bonusShareAmt.
     */
    public BigDecimal getBonusShareAmt() {
        return bonusShareAmt;
    }



    
    /**
     * 设置 bonusShareAmt
     * @param bonusShareAmt bonusShareAmt
     */
    public void setBonusShareAmt(BigDecimal bonusShareAmt) {
        this.bonusShareAmt = bonusShareAmt;
    }



    
    /**
     * 获取 bonusShareNum
     * @return bonusShareNum.
     */
    public BigDecimal getBonusShareNum() {
        return bonusShareNum;
    }



    
    /**
     * 设置 bonusShareNum
     * @param bonusShareNum bonusShareNum
     */
    public void setBonusShareNum(BigDecimal bonusShareNum) {
        this.bonusShareNum = bonusShareNum;
    }



    
    /**
     * 获取 dealType
     * @return dealType.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getDealType() {
        return dealType;
    }



    
    /**
     * 设置 dealType
     * @param dealType dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }



    
    /**
     * 获取 tradeId
     * @return tradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getTradeId() {
        return tradeId;
    }



    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }



    
    /**
     * 获取 actBonusDate
     * @return actBonusDate.
     */
    public Date getActBonusDate() {
        return actBonusDate;
    }



    
    /**
     * 设置 actBonusDate
     * @param actBonusDate actBonusDate
     */
    public void setActBonusDate(Date actBonusDate) {
        this.actBonusDate = actBonusDate;
    }



    
    /**
     * 获取 bonusDealRemark
     * @return bonusDealRemark.
     */
    public String getBonusDealRemark() {
        return bonusDealRemark;
    }



    
    /**
     * 设置 bonusDealRemark
     * @param bonusDealRemark bonusDealRemark
     */
    public void setBonusDealRemark(String bonusDealRemark) {
        this.bonusDealRemark = bonusDealRemark;
    }



    
    /**
     * 获取 confirmDate
     * @return confirmDate.
     */
    public Date getConfirmDate() {
        return confirmDate;
    }



    
    /**
     * 设置 confirmDate
     * @param confirmDate confirmDate
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }



    
    /**
     * 获取 totalBonusCashAmt
     * @return totalBonusCashAmt.
     */
    public BigDecimal getTotalBonusCashAmt() {
        return totalBonusCashAmt;
    }



    
    /**
     * 设置 totalBonusCashAmt
     * @param totalBonusCashAmt totalBonusCashAmt
     */
    public void setTotalBonusCashAmt(BigDecimal totalBonusCashAmt) {
        this.totalBonusCashAmt = totalBonusCashAmt;
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
     * 获取 bTradeId
     * @return bTradeId.
     */
    public String getbTradeId() {
        return bTradeId;
    }



    
    /**
     * 设置 bTradeId
     * @param bTradeId bTradeId
     */
    public void setbTradeId(String bTradeId) {
        this.bTradeId = bTradeId;
    }



	public String getProdStyle() {
		return prodStyle;
	}



	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}



	public String getDealMode() {
		return dealMode;
	}



	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public BigDecimal getShareTdyUnitValue() {
		return shareTdyUnitValue;
	}



	public void setShareTdyUnitValue(BigDecimal shareTdyUnitValue) {
		this.shareTdyUnitValue = shareTdyUnitValue;
	}
    
    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

	public String getSendStatusStr() {
		return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
	}






	public Date getSubCycleStartDate() {
		return subCycleStartDate;
	}



	public void setSubCycleStartDate(Date subCycleStartDate) {
		this.subCycleStartDate = subCycleStartDate;
	}



	public Date getSubCycleEndDate() {
		return subCycleEndDate;
	}



	public void setSubCycleEndDate(Date subCycleEndDate) {
		this.subCycleEndDate = subCycleEndDate;
	}

	
    private List<FinNetProductBonusSchemeDto> subCycleList;
    public List<FinNetProductBonusSchemeDto> getSubCycleList() {
		return subCycleList;
	}



	public void setSubCycleList(List<FinNetProductBonusSchemeDto> subCycleList) {
		this.subCycleList = subCycleList;
	}



	public Long getBranch() {
		return branch;
	}



	public void setBranch(Long branch) {
		this.branch = branch;
	}



	public String getIsCycle() {
		return isCycle;
	}



	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}



	public String getPeriodId() {
		return periodId;
	}



	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}



	public List<FinNetProductBonusSchemeDto> getListCycleProductInfo() {
		return listCycleProductInfo;
	}



	public void setListCycleProductInfo(List<FinNetProductBonusSchemeDto> listCycleProductInfo) {
		this.listCycleProductInfo = listCycleProductInfo;
	}

	/**
     * 取得 是否轧差清算
     * @return 是否轧差清算
     */
    public String getLiquidationYesno() {
        return liquidationYesno;
    }

    /**
     * 设置 是否轧差清算
     * @param liquidationYesno 是否轧差清算
     */
    public void setLiquidationYesno(String liquidationYesno) {
        this.liquidationYesno = liquidationYesno == null ? null : liquidationYesno.trim();
    }
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return StringUtil.isNotEmpty(profitType)?this.profitType:DictCodeList.收益类型_净值型.getValue();
    }



    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }


}
