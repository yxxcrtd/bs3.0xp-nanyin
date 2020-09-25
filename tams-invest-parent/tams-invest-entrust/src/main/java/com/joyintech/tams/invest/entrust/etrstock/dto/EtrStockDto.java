package com.joyintech.tams.invest.entrust.etrstock.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrstock.validator.EtrStockValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@PageId("EtrStock002")
@ValidLogic(targetClass = EtrStockValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrStockDto extends EtrEntrust implements ProcessEntity {


    private static final long serialVersionUID = 9222570679406156110L;

    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<EtrStockDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产一级分类
     */
    private String assetType1;
    
    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 证券托管户
     */
    private String secAcctId;
    /**
     * 获取证券托管户
     * @return
     */
    @NotEmpty(fieldName = "EtrStock002.secAcctId", groups = {ValidateGroupEdit.class})
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置证券托管户
     * @param secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;
    
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;
     /**
      * 序号
      */
    private String indexId;
    /**
     * 交易所资金账户可用现金
     */
    private BigDecimal tradeMarketAccAvailableCash;
    /**
     * 交易对手名称
     */
    private String counterIdName;
    /**
     * 证券账户名称
     */
    private String secAcctIdName;
    /**
     *获取secAcctIdName
     */
    public String getSecAcctIdName() {
		return secAcctIdName;
	}
    /**
     *设置secAcctIdName
     */
	public void setSecAcctIdName(String secAcctIdName) {
		this.secAcctIdName = secAcctIdName;
	}

	/**
     * 获取交易对手名称
     * @return
     */
    public String getCounterIdName() {
		return counterIdName;
	}
    /**
     * 设置交易对手名称
     * @param counterIdName
     */
	public void setCounterIdName(String counterIdName) {
		this.counterIdName = counterIdName;
	}

	/**
     * 获取交易所资金账户可用现金
     * @return
     */
    public BigDecimal getTradeMarketAccAvailableCash() {
		return tradeMarketAccAvailableCash;
	}
    /**
     * 设置交易所资金账户可用现金
     * @param tradeMarketAccAvailableCash
     */
	public void setTradeMarketAccAvailableCash(BigDecimal tradeMarketAccAvailableCash) {
		this.tradeMarketAccAvailableCash = tradeMarketAccAvailableCash;
	}

	/**
     * 获取序号
     * @return
     */
	public String getIndexId() {
		return indexId;
	}
    /**
     * 设置序号
     * @param indexId
     */
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	/**
     * 获取 closePrice
     * @return closePrice.
     */
    public BigDecimal getClosePrice() {
		return closePrice;
	}
    
    /**
     * 设置 closePrice
     * @param closePrice closePrice
     */
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
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
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }
    
    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
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
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrStockDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrStockDto> selectedItem) {
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
     * 获取 closeWay
     * @return closeWay.
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
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }
    
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
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
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }
    
    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }
    
    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }
    
    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
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
    
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrStock002.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    @Override
    @NotEmpty(fieldName = "EtrStock002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }

    @Override
    @NotEmpty(fieldName = "EtrStock002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrStock002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrStock002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    @Override
    @NotEmpty(fieldName = "EtrStock002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    @Override
    @NotEmpty(fieldName = "EtrStock002.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket(){
        return super.getTradeMarket();
    }

    @Override
   /* @NotEmpty(fieldName = "EtrStock002.price", groups = {ValidateGroupEdit.class})*/
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    @NotEmpty(fieldName = "EtrStock002.priceModel", groups = {ValidateGroupEdit.class})
     public String getPriceModel() {
         return super.getPriceModel();
     }
    
    @Override
    /*@NotEmpty(fieldName = "EtrStock002.entrustNum", groups = {ValidateGroupEdit.class})*/
    public BigDecimal getEntrustNum(){
        return super.getEntrustNum();
    }

    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getBusBoard(){
        return SysDataUtil.getNameFromDataDict(DictCode.上市板, super.getBoard());
    }

    public String getBusTradeMarket(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    public String getBusDealMode(){ return SysDataUtil.getNameFromDataDict(DictCode.外汇交易模式, super.getDealMode()); }
    
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }

    public String getPriceModelStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }
    
    /*public BigDecimal getBusEntrustNum(){
        return this.getEntrustNum()==null?super.getEntrustNum():getEntrustNum();
    }

    public BigDecimal getBusEntrustAmt(){
        return this.getEntrustAmt()==null?super.getEntrustNum():getEntrustAmt();
    }*/
}
