package com.joyintech.service.product.productduration.netproductbonusscheme.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.PrdBonusPlanN;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author liuwei
 * @version 1.0 2018年10月17日
 * @see FinNetProductBonusSchemeDto
 * @since 1.0
 */
public class FinNetProductBonusSchemeDto extends PrdBonusPlanN {
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
     * 金融产品全称
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
     * 单位净值小数位
     */
    private Long unitvalueDec;
    /**
     * 申购确认速度，如果资产只有一个确认速度，则存该字段
     */
    private Long purSpeed;
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
     * 币种
     */
    private String ccy;
    
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
     * 已分红金额 --包括现金分红和红利再投
     */
    private BigDecimal totalBonusCashAmt;
    
    /**
     * 分红金额确认的交易编号 ----现金分红和分红金额确认的前置交易编号为分红金额确认的交易编号
     */
    private String bTradeId;
    
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
     * 产品款型
     */
    private String prodStyle;
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
     * 期次子产品列表
     */
    private List<FinNetProductBonusSchemeDto> listCycleProductInfo;
    
	/*期次子产品信息 end */

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
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

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getActBonusDate() {
		return actBonusDate;
	}

	public void setActBonusDate(Date actBonusDate) {
		this.actBonusDate = actBonusDate;
	}

	public String getBonusDealRemark() {
		return bonusDealRemark;
	}

	public void setBonusDealRemark(String bonusDealRemark) {
		this.bonusDealRemark = bonusDealRemark;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getProdStyle() {
		return prodStyle;
	}

	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
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

}
