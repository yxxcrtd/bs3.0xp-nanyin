package com.joyintech.tams.invest.entrust.etrbondtostock.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrbondtostock.validator.EtrBondToStockValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 可转债转股委托画面dto
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockDto
 * @since 1.0
 */
@PageId("EtrBondToStock002")
@ValidLogic(targetClass = EtrBondToStockValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrBondToStockDto extends EtrEntrust implements ProcessEntity{

	
    /**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -3914776038704153318L;

	/**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    
    /**
     * 投资人员信息DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    
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
     * 按钮id
     */
    private String elementId;
    
    /**
     * 转债名称
     */
    private String finprodName;
    
    /**
     * 正股代码对应金融产品代码
     */
    private String shareFinprodId;
    
    /**
     * 正股代码
     */
    private String shareId;
    
    /**
     * 正股名称
     */
    private String shareName;
    
    /**
     * 转股代码
     */
    private String conShareId;
    
    /**
     * 交易所资金账户可用现金
     */
    private BigDecimal tradeMarketCash;
    
    /**
     * 通道
     */
    private String chlId;
    
    /**
     * 证券托管户
     */
    private String secAcctId;
    
    /**
     * 通道可用现金
     */
    private BigDecimal channelAvailableCash;
    
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
     * 转股价格
     */
    private BigDecimal bondPrice;
    
    /**
     * 转股价格
     */
    private BigDecimal pbondvalue;
    
    /**
     * 预计转换股数
     */
    private String expConNum;
    
    /**
     * 正股价
     */
    private BigDecimal sharePrice;
    
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
	
    /**
     * 获取 shareId
     * @return shareId.
     */
	public String getShareId() {
		return shareId;
	}
	
    /**
     * 设置 shareId
     * @param shareId shareId
     */
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	
    /**
     * 获取 shareName
     * @return shareName.
     */
	public String getShareName() {
		return shareName;
	}
	
    /**
     * 设置 shareName
     * @param shareName shareName
     */
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	
	@NotEmpty(fieldName = "EtrBondToStock002.stocketrPriceTotal", groups = {ValidateGroupEdit.class})
	public BigDecimal getStocketrPriceTotal() {
		return stocketrPriceTotal;
	}
	
	//@NotEmpty(fieldName = "EtrBondToStock001.sharePrice", groups = {ValidateGroupEdit.class})
	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	//	@NotEmpty(fieldName = "EtrBondToStock001.preb", groups = {ValidateGroupEdit.class})
	public BigDecimal getPreb() {
		return preb;
	}
	public void setPreb(BigDecimal preb) {
		this.preb = preb;
	}
	
	//@NotEmpty(fieldName = "EtrBondToStock001.prec", groups = {ValidateGroupEdit.class})
	public BigDecimal getPrec() {
		return prec;
	}
    @Override
    public String getChlId() {
        return chlId;
    }

    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
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
     * 获取 shareFinprodId
     * @return shareFinprodId.
     */
    public String getShareFinprodId() {
        return shareFinprodId;
    }
    
    /**
     * 设置 shareFinprodId
     * @param shareFinprodId shareFinprodId
     */
    public void setShareFinprodId(String shareFinprodId) {
        this.shareFinprodId = shareFinprodId;
    }
    
    /**
     * 获取 conShareId
     * @return conShareId.
     */
    public String getConShareId() {
        return conShareId;
    }
    
    /**
     * 设置 conShareId
     * @param conShareId conShareId
     */
    public void setConShareId(String conShareId) {
        this.conShareId = conShareId;
    }
    
    /**
     * 获取 tradeMarketCash
     * @return tradeMarketCash.
     */
    public BigDecimal getTradeMarketCash() {
        return tradeMarketCash;
    }
    
    /**
     * 设置 tradeMarketCash
     * @param tradeMarketCash tradeMarketCash
     */
    public void setTradeMarketCash(BigDecimal tradeMarketCash) {
        this.tradeMarketCash = tradeMarketCash;
    }
    
    /**
     * 获取 secAcctId
     * @return secAcctId.
     */
    public String getSecAcctId() {
        return secAcctId;
    }
    
    /**
     * 设置 secAcctId
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }
    
    /**
     * 获取 channelAvailableCash
     * @return channelAvailableCash.
     */
    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }
    
    /**
     * 设置 channelAvailableCash
     * @param channelAvailableCash channelAvailableCash
     */
    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }
    
    /**
     * 获取 holdetrPriceTotal
     * @return holdetrPriceTotal.
     */
    public BigDecimal getHoldetrPriceTotal() {
        return holdetrPriceTotal;
    }
    
    /**
     * 设置 holdetrPriceTotal
     * @param holdetrPriceTotal holdetrPriceTotal
     */
    public void setHoldetrPriceTotal(BigDecimal holdetrPriceTotal) {
        this.holdetrPriceTotal = holdetrPriceTotal;
    }
    
    /**
     * 获取 conetrPriceTotal
     * @return conetrPriceTotal.
     */
    public BigDecimal getConetrPriceTotal() {
        return conetrPriceTotal;
    }
    
    /**
     * 设置 conetrPriceTotal
     * @param conetrPriceTotal conetrPriceTotal
     */
    public void setConetrPriceTotal(BigDecimal conetrPriceTotal) {
        this.conetrPriceTotal = conetrPriceTotal;
    }
    
    /**
     * 获取 conPrice
     * @return conPrice.
     */
    public BigDecimal getConPrice() {
        return conPrice;
    }
    
    /**
     * 设置 conPrice
     * @param conPrice conPrice
     */
    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }
    
    /**
     * 获取 bondPrice
     * @return bondPrice.
     */
    public BigDecimal getBondPrice() {
        return bondPrice;
    }
    
    /**
     * 设置 bondPrice
     * @param bondPrice bondPrice
     */
    public void setBondPrice(BigDecimal bondPrice) {
        this.bondPrice = bondPrice;
    }
    
    /**
     * 获取 pbondvalue
     * @return pbondvalue.
     */
    public BigDecimal getPbondvalue() {
        return pbondvalue;
    }
    
    /**
     * 设置 pbondvalue
     * @param pbondvalue pbondvalue
     */
    public void setPbondvalue(BigDecimal pbondvalue) {
        this.pbondvalue = pbondvalue;
    }
    
    /**
     * 获取 expConNum
     * @return expConNum.
     */
    public String getExpConNum() {
        return expConNum;
    }
    
    /**
     * 设置 expConNum
     * @param expConNum expConNum
     */
    public void setExpConNum(String expConNum) {
        this.expConNum = expConNum;
    }
    
    /**
     * 获取 resalePrice
     * @return resalePrice.
     */
    public BigDecimal getResalePrice() {
        return resalePrice;
    }
    
    /**
     * 设置 resalePrice
     * @param resalePrice resalePrice
     */
    public void setResalePrice(BigDecimal resalePrice) {
        this.resalePrice = resalePrice;
    }
    
    /**
     * 获取 redeemPrice
     * @return redeemPrice.
     */
    public BigDecimal getRedeemPrice() {
        return redeemPrice;
    }
    
    /**
     * 设置 redeemPrice
     * @param redeemPrice redeemPrice
     */
    public void setRedeemPrice(BigDecimal redeemPrice) {
        this.redeemPrice = redeemPrice;
    }
    
    /**
     * 获取 conProportion
     * @return conProportion.
     */
    public BigDecimal getConProportion() {
        return conProportion;
    }
    
    /**
     * 设置 conProportion
     * @param conProportion conProportion
     */
    public void setConProportion(BigDecimal conProportion) {
        this.conProportion = conProportion;
    }
    
    /**
     * 获取 expConValue
     * @return expConValue.
     */
    public BigDecimal getExpConValue() {
        return expConValue;
    }
    
    /**
     * 设置 expConValue
     * @param expConValue expConValue
     */
    public void setExpConValue(BigDecimal expConValue) {
        this.expConValue = expConValue;
    }
    
    /**
     * 获取 conValue
     * @return conValue.
     */
    public BigDecimal getConValue() {
        return conValue;
    }
    
    /**
     * 设置 conValue
     * @param conValue conValue
     */
    public void setConValue(BigDecimal conValue) {
        this.conValue = conValue;
    }
    
    /**
     * 获取 conVdate
     * @return conVdate.
     */
    public Date getConVdate() {
        return conVdate;
    }
    
    /**
     * 设置 conVdate
     * @param conVdate conVdate
     */
    public void setConVdate(Date conVdate) {
        this.conVdate = conVdate;
    }
    
    /**
     * 设置 stocketrPriceTotal
     * @param stocketrPriceTotal stocketrPriceTotal
     */
    public void setStocketrPriceTotal(BigDecimal stocketrPriceTotal) {
        this.stocketrPriceTotal = stocketrPriceTotal;
    }
    
    /**
     * 设置 sharePrice
     * @param sharePrice sharePrice
     */
    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }
    
    /**
     * 设置 prec
     * @param prec prec
     */
    public void setPrec(BigDecimal prec) {
        this.prec = prec;
    }
    
    /**
     * 获取 投资目的名称
     *
     * @return invAimName 投资目的名称
     */
    public String getInvAimName() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, getInvAim());
    }
}
