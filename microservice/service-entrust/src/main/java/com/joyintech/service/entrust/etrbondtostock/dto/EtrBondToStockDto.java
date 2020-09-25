package com.joyintech.service.entrust.etrbondtostock.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

/**
 * 可转债转股委托画面dto
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockDto
 * @since 1.0
 */
public class EtrBondToStockDto extends EtrEntrust {

	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
	private static final long serialVersionUID = 7098383250450566733L;
	
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    
    /**
     * 接收状态
     */
    private String recStatus;
    
    /**
     * 指定交易员
     */
    private String trader;
    
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    
    /**
     * 选中的数据
     */
    private transient List<EtrBondToStockDto> selectedItem;
    
    /**
     * 纯债价值
     */
    private BigDecimal pbondvalue;
    
    /**
     * 投资人员信息DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    
    /**
     * 转债价格
     */
    private BigDecimal bondPrice;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 转债代码
     */
    private String finprodId;
    
    /**
     * 转债代码对应产品代码
     */
    private String finprodMarketId;
    
    /**
     * 正股代码对应金融产品代码
     */
    private String shareFinprodId;
    
    /**
     * 转债名称
     */
    private String finprodName;
    
    /**
     * 正股代码
     */
    private String shareId;
    
    /**
     * 正股名称
     */
    private String shareName;
    
    /**
     * 证券账户
     */
    private String secAcctId;
    
    /**
     * 通道
     */
    private String chlId;
    
    /**
     * 转股代码
     */
    private String conShareId;
    
    /**
     * 持有券面总额
     */
    private BigDecimal holdetrPriceTotal;
    
    /**
     * 可转换券面总额
     */
    private BigDecimal conetrPriceTotal;
    
    /**
     * 转股券面总额
     */
    private BigDecimal stocketrPriceTotal;
    
    /**
     * 转股价格
     */
    private BigDecimal conPrice;
    
    /**
     * 预计转换股数
     */
    private BigDecimal expConNum;
    
    /**
     * 正股价
     */
    private BigDecimal sharePrice;
    
    
    /**
     * 预计转股价值
     */
    private BigDecimal expConValue;
    
    /**
     * 转股价值
     */
    private BigDecimal conValue;
    
    /**
     * 转股溢价率
     */
    private BigDecimal preb;
    
    /**
     * 纯债溢价率
     */
    private BigDecimal prec;
    
    /**
     * 转股起始日
     */
    private Date conVdate;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 起息日
     */
    private Date finvdate;
    
    /**
     * 到期日
     */
    private Date finmdate;
    
    /**
     * 回售触发价
     */
    private BigDecimal resalePrice;
    
    /**
     * 赎回触发价
     */
    private BigDecimal redeemPrice;
    
    /**
     * 转股比例
     */
    private BigDecimal conProportion;
    
    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }
    /**
     * 
     * 取得组合代码
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return super.getPortfolioId();
    }
    
    /**
     * 取得 委托开始日
     * @return 委托开始日
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate() {
        return super.getVdate();
    }
    
    /**
     * 取得 委托结束日
     * @return 委托结束日
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate() {
        return super.getMdate();
    }
    
    /**
     * 取得 币种
     * @return 币种
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }
    
    /**
     * 取得 投资目的
     * @return 投资目的
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.invAim", groups = {ValidateGroupEdit.class})
    public String getInvAim() {
        return super.getInvAim();
    }
    /**
     * 取得 交易场所
     * @return 交易场所
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestAllInfo001.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket() {
        return super.getTradeMarket();
    }
   
    /**
     * 
     * 设置委托状态数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.委托状态, super.getEntrustStatus());
    }
    /**
     * 
     * 设置币种数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.币种, super.getCcy());

    }
    /**
     * 
     * 设置交易场所数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusTradeMarket(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易场所, super.getTradeMarket());
    }
	
    /**
     * 获取 fileArray
     * @return fileArray.
     */
	public List<SysUploadFileInfo> getFileArray() {
		return fileArray;
	}
	
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
	public void setFileArray(List<SysUploadFileInfo> fileArray) {
		this.fileArray = fileArray;
	}
	
    /**
     * 获取 getTrader
     * @return getTrader.
     */
	public String getTrader() {
		return trader;
	}
	
    /**
     * 设置 trader
     * @param trader trader
     */
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
    /**
     * 获取 CloseWay
     * @return CloseWay.
     */
	public String getCloseWay() {
		return CloseWay;
	}
	
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
	public void setCloseWay(String closeWay) {
		CloseWay = closeWay;
	}
	
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
	public List<EtrBondToStockDto> getSelectedItem() {
		return selectedItem;
	}
	
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
	public void setSelectedItem(List<EtrBondToStockDto> selectedItem) {
		this.selectedItem = selectedItem;
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
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return riskTrialRetDto;
	}
	
    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}
	
    /**
     * 获取 finvdate
     * @return finvdate.
     */
	public Date getFinvdate() {
		return finvdate;
	}
	
    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
	public void setFinvdate(Date finvdate) {
		this.finvdate = finvdate;
	}
	
    /**
     * 获取 finmdate
     * @return finmdate.
     */
	public Date getFinmdate() {
		return finmdate;
	}
	
    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
	public void setFinmdate(Date finmdate) {
		this.finmdate = finmdate;
	}
	
    /**
     * 获取 finprodId
     * @return finprodId.
     */
	public String getFinprodId() {
		return finprodId;
	}
	
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	
    /**
     * 获取 finprodName
     * @return finprodName.
     */
	public String getFinprodName() {
		return finprodName;
	}
	
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
	
	
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	
    /**
     * 取得 转股代码
     * @return 转股代码
     */
	public String getConShareId() {
		return conShareId;
	}
	
    /**
     * 设置 转股代码
     * @param conShareId 转股代码
     */
	public void setConShareId(String conShareId) {
		this.conShareId = conShareId;
	}
	
	public BigDecimal getConetrPriceTotal() {
		return conetrPriceTotal;
	}
	public void setConetrPriceTotal(BigDecimal conetrPriceTotal) {
		this.conetrPriceTotal = conetrPriceTotal;
	}
	public BigDecimal getStocketrPriceTotal() {
		return stocketrPriceTotal;
	}
	public void setStocketrPriceTotal(BigDecimal stocketrPriceTotal) {
		this.stocketrPriceTotal = stocketrPriceTotal;
	}
	
    /**
     * 取得 转股价
     * @return 转股价
     */
	public BigDecimal getConPrice() {
		return conPrice;
	}
	
    /**
     * 设置 转股价
     * @param conPrice 转股价
     */
	public void setConprice(BigDecimal conPrice) {
		this.conPrice = conPrice;
	}
	public BigDecimal getExpConNum() {
		return expConNum;
	}
	public void setExpConNum(BigDecimal expConNum) {
		this.expConNum = expConNum;
	}
    /**
     * 取得 正股价
     * @return 正股价
     */
    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    /**
     * 设置 正股价
     * @param shapePrice 正股价
     */
    public void setShapePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }
    
    /**
     * 取得 预计转股价值
     * @return 预计转股价值
     */
    public BigDecimal getExpConValue() {
        return expConValue;
    }

    /**
     * 设置 预计转股价值
     * @param expConValue 预计转股价值
     */
    public void setExpConValue(BigDecimal expConValue) {
        this.expConValue = expConValue;
    }
    
    /**
     * 取得 转股溢价率
     * @return 转股溢价率
     */
    public BigDecimal getPreb() {
        return preb;
    }

    /**
     * 设置 转股溢价率
     * @param preb 转股溢价率
     */
    public void setPreb(BigDecimal preb) {
        this.preb = preb;
    }
    
    /**
     * 取得 纯债溢价率
     * @return 纯债溢价率
     */
    public BigDecimal getPrec() {
        return prec;
    }

    /**
     * 设置 纯债溢价率
     * @param preb 纯债溢价率
     */
    public void setPrec(BigDecimal prec) {
        this.prec = prec;
    }
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public Date getConVdate() {
		return conVdate;
	}
	public void setConVdate(Date conVdate) {
		this.conVdate = conVdate;
	}
	public BigDecimal getResalePrice() {
		return resalePrice;
	}
	public void setResalePrice(BigDecimal resalePrice) {
		this.resalePrice = resalePrice;
	}
	public BigDecimal getRedeemPrice() {
		return redeemPrice;
	}
	public void setRedeemPrice(BigDecimal redeemPrice) {
		this.redeemPrice = redeemPrice;
	}
	public BigDecimal getConProportion() {
		return conProportion;
	}
	public void setConProportion(BigDecimal conProportion) {
		this.conProportion = conProportion;
	}
	public BigDecimal getHoldetrPriceTotal() {
		return holdetrPriceTotal;
	}
	public void setHoldetrPriceTotal(BigDecimal holdetrPriceTotal) {
		this.holdetrPriceTotal = holdetrPriceTotal;
	}
	public BigDecimal getPbondvalue() {
		return pbondvalue;
	}
	public void setPbondvalue(BigDecimal pbondvalue) {
		this.pbondvalue = pbondvalue;
	}
	public BigDecimal getBondPrice() {
		return bondPrice;
	}
	public void setBondPrice(BigDecimal bondPrice) {
		this.bondPrice = bondPrice;
	}
	public BigDecimal getConValue() {
		return conValue;
	}
	public void setConValue(BigDecimal conValue) {
		this.conValue = conValue;
	}
	public String getShareFinprodId() {
		return shareFinprodId;
	}
	public void setShareFinprodId(String shareFinprodId) {
		this.shareFinprodId = shareFinprodId;
	}
	public void setConPrice(BigDecimal conPrice) {
		this.conPrice = conPrice;
	}
	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}
	
    /**
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }
    
    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	public String getSecAcctId() {
		return secAcctId;
	}
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}
    @Override
    public String getChlId() {
        return chlId;
    }

    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }
}
