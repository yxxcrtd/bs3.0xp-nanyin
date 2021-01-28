/*
 * 文件名：TrdDepositInvestTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trddepositinvesttrade.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.validate.TrdDepositInvestTrade001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;

/**
 * 存款类投资交易实体类
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositInvestTrade001Dto
 * @since 1.0
 */
@PageId("TrdDepositInvestTrade002")
@ValidLogic(targetClass = TrdDepositInvestTrade001Validator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class TrdDepositInvestTrade001Dto extends WflTrdProductDeal implements ProcessEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4814792523354012630L;
    /**
     * 委托代码
     */
    @Length(fieldName="TrdDepositInvestTrade001.entrustId",max=50,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String entrustId;
    /**
     * 组合代码
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade001.portfolioId",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdDepositInvestTrade001.portfolioId",max=32,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String portfolioId;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 金融产品类型
     */
    private String type1;
    /**
     * 金融产品名称
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade001.finprodName",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdDepositInvestTrade001.finprodName",max=200,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String finprodName;
    /**
     * 期限
     */
    @Length(fieldName="TrdDepositInvestTrade002.termDays",max=10,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
//    @NotEmpty(fieldName="TrdDepositInvestTrade002.termDays",groups = {ValidateGroupEdit.class})
    private String termDays;
    /**
     * 计息基础
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade001.basis",groups = {ValidateGroupEdit.class})
    private String basis;
    /**
     * 资产提供行
     */
    private String assetProvider;
    /**
     * 借款利率%
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade002.loadRate",groups = {ValidateGroupEdit.class} )
    @Length(fieldName="TrdDepositInvestTrade002.termDays",max=16 ,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String loadRate;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 市场机构代码
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade001.finprodId",groups = {ValidateGroupEdit.class} )
    private String finprodMarketId;
    /**
     * elementId
     */
    private String elementId;

    /**
     * 合作资产
     */
    private String chlFinprodId;

    public String getChlFinprodId() {
        return chlFinprodId;
    }

    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId;
    }

    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 组合表
     */
    private List<PtlTrade> ptlTrade;
    /**
     * 金融产品交易扩展
     */
    private PaTrdProductDeal paTrdProductDeal;
    /**
     * 金融产品交易实体类
     */
    private FinProductTradeDto finProductTradeDto;
    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto;
    /**
     * 存款实体类
     */
    private List<TrdDepositInvestTrade001Dto> selectedItem;
    /**
     * opics
     */
    private List<InterfSendOpics> interfSendOpicsList;
    
    /**
     * 附件上传
     */
    private List<UploadFile> fileArray;
    /**
     * 撤销原因
     */
    private String cancelRemark;
    /**
     * pageModel
     */
    private String pageModel;
    /**
     * 组合可用持仓
     */
    private BigDecimal bottomAssetAmount;
    /**
     * 组合可用现金
     */
    private BigDecimal accAvailableCashT;

    /**
     * 组合T+1日可用现金
     */
    private BigDecimal accAvailableCashT1;


    /**
     * 组合可用持仓,用于APP显示
     */
    private String appBottomAssetAmount;

    /**
     * 组合可用现金,用于APP显示
     */
    private String appAccAvailableCashT;
    /**
     * 组合T+1日可用现金，用于APP显示
     */
    private String appAccAvailableCashT1;


    /**
     * 托管户剩余现金
     */
    private BigDecimal accAvailableCash;
    /**
     * 风险试算实体类集合
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    // <!-- 通道交易 新增 强成西 2019/03/26 start -->
    /**
     * 通道费
     */
    private BigDecimal channelFee;
    
    /**
     * 通道费
     */
    private String collectType;

    /**
     * 带通道费的交易金额
     */
    private BigDecimal tradeAmtAndFee;
    
/**新增资产二、三、四级分类	王贵奇	2019/7/30 开始*/   
    
    /**
     * 资产二级类型
     */
    private String type9;    
    
    /**
     * 资产三级类型
     */
    private String type10;
    /**
     * 交易对手类型
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade002.cno",groups = {ValidateGroupEdit.class} )
    private String counterType;

    /**
     * 存款机构
     */
    private String issuer;
    
    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 授信来源
     */
    private String creditSource;

    private RiskCreditListCommonDto riskCreditListCommonDto;

    /**
     * 获取 counterType
     *
     * @return counterType
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 counterType
     *
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    /**
     * 资产四级类型
     */
    private String type11;
    
    public String getType9() {
		return type9;
	}
    
    public String getType9Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, getType9());
	}

	public void setType9(String type9) {
		this.type9 = type9;
	}

	public String getType10() {
		return type10;
	}
    
    public String getType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, getType10());
	}

	public void setType10(String type10) {
		this.type10 = type10;
	}

	public String getType11() {
		return type11;
	}
    
    public String getType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, getType11());
	}

	public void setType11(String type11) {
		this.type11 = type11;
	}

	/**新增资产二、三、四级分类	王贵奇	2019/7/30 结束*/

    /**
     * 获取 channelFee
     *
     * @return channelFee
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 设置 channelFee
     *
     * @param channelFee channelFee
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 获取 tradeAmtAndFee
     *
     * @return tradeAmtAndFee
     */
    public BigDecimal getTradeAmtAndFee() {
        return tradeAmtAndFee;
    }

    /**
     * 设置 tradeAmtAndFee
     *
     * @param tradeAmtAndFee tradeAmtAndFee
     */
    public void setTradeAmtAndFee(BigDecimal tradeAmtAndFee) {
        this.tradeAmtAndFee = tradeAmtAndFee;
    }

    // <!-- 通道交易 新增 强成西 2019/03/26 end -->
    
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
     * 获取 accAvailableCash
     * @return accAvailableCash.
     */
    public BigDecimal getAccAvailableCash() {
        return accAvailableCash;
    }

    /**
     * 设置 accAvailableCash
     * @param accAvailableCash accAvailableCash
     */
    public void setAccAvailableCash(BigDecimal accAvailableCash) {
        this.accAvailableCash = accAvailableCash;
    }

    /**
     * 获取 bottomAssetAmount
     * @return bottomAssetAmount.
     */
    public BigDecimal getBottomAssetAmount() {
        return bottomAssetAmount;
    }
    
    /**
     * 设置 bottomAssetAmount
     * @param bottomAssetAmount bottomAssetAmount
     */
    public void setBottomAssetAmount(BigDecimal bottomAssetAmount) {
        this.bottomAssetAmount = bottomAssetAmount;
    }
    
    /**
     * 获取 accAvailableCashT
     * @return accAvailableCashT.
     */
    public BigDecimal getAccAvailableCashT() {
        return accAvailableCashT;
    }
    
    /**
     * 设置 accAvailableCashT
     * @param accAvailableCashT accAvailableCashT
     */
    public void setAccAvailableCashT(BigDecimal accAvailableCashT) {
        this.accAvailableCashT = accAvailableCashT;
    }
    
    /**
     * 获取 accAvailableCashT1
     * @return accAvailableCashT1.
     */
    public BigDecimal getAccAvailableCashT1() {
        return accAvailableCashT1;
    }
    
    /**
     * 设置 accAvailableCashT1
     * @param accAvailableCashT1 accAvailableCashT1
     */
    public void setAccAvailableCashT1(BigDecimal accAvailableCashT1) {
        this.accAvailableCashT1 = accAvailableCashT1;
    }

    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
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
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    /**
     * 金融产品类型
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade001.finprodType",groups = {ValidateGroupEdit.class})
    public String getFinprodType(){
        return super.getFinprodType();
    }
    /**
     * 备注
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName="TrdDepositInvestTrade002.remark",max=1000,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    public String getRemark(){
        return super.getRemark();
    }
    /**
     * 利息总额
     * {@inheritDoc}
     */
    @Override
    //@NotEmpty(fieldName="TrdDepositInvestTrade002.intAmt",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdDepositInvestTrade002.intAmt",max=16 ,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    public BigDecimal getIntAmt(){
        return super.getIntAmt();
    }
    /**
     * 本金金额
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade002.prinAmt",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdDepositInvestTrade002.prinAmt",max=30  ,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    @Amount(fieldName = "TrdDepositInvestTrade002.prinAmt")
    public BigDecimal getPrinAmt(){
        return super.getPrinAmt();
    }
    
//    /**
//     * 资产代码
//     * {@inheritDoc}
//     */
//    @Override
//    @NotEmpty(fieldName="TrdDepositInvestTrade001.finprodId",groups = {ValidateGroupEdit.class})
//    public String getFinprodId(){
//        return super.getFinprodId();
//    }
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade002.vDate",groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade002.mDate",groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade002.tradeDate",groups = {ValidateGroupEdit.class})
    public Date getTradeDate(){
        return super.getTradeDate();
    }
    
    
    /**
     * 获取 cancelRemark
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }
    
    /**
     * 设置 cancelRemark
     * @param cancelRemark cancelRemark
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
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
     * 获取 fileArray
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }
    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<TrdDepositInvestTrade001Dto> getSelectedItem() {
        return selectedItem;
    }
    
    
    /**
     * 获取 interfSendOpicsList
     * @return interfSendOpicsList.
     */
    public List<InterfSendOpics> getInterfSendOpicsList() {
        return interfSendOpicsList;
    }

    
    /**
     * 设置 interfSendOpicsList
     * @param interfSendOpicsList interfSendOpicsList
     */
    public void setInterfSendOpicsList(List<InterfSendOpics> interfSendOpicsList) {
        this.interfSendOpicsList = interfSendOpicsList;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdDepositInvestTrade001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    /**
     * 设置主键
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getTradeId(){
        return StringUtil.isNotEmpty(super.getTradeId())?super.getTradeId():IDGenerator.generate();
    }
    /**
     * 业务类型
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_2)
    public String getBusiType(){
        return super.getBusiType();
    }
    /**
     * 获取 trdInvestPersonInfo002Dto
     * @return trdInvestPersonInfo002Dto.
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo002Dto() {
        return trdInvestPersonInfo002Dto;
    }
    
    /**
     * 设置 trdInvestPersonInfo002Dto
     * @param trdInvestPersonInfo002Dto trdInvestPersonInfo002Dto
     */
    public void setTrdInvestPersonInfo002Dto(TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto) {
        this.trdInvestPersonInfo002Dto = trdInvestPersonInfo002Dto;
    }

    /**
     * 获取 finProductTradeDto
     * @return finProductTradeDto.
     */
    public FinProductTradeDto getFinProductTradeDto() {
        return finProductTradeDto;
    }
    
    /**
     * 设置 finProductTradeDto
     * @param finProductTradeDto finProductTradeDto
     */
    public void setFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        this.finProductTradeDto = finProductTradeDto;
    }

    /**
     * 获取 stlPayOrdersList
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }
    
    /**
     * 设置 stlPayOrdersList
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }
    
    /**
     * 获取 ptlTrade
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }
    
    /**
     * 设置 ptlTrade
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }
    
    /**
     * 获取 paTrdProductDeal
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }
    
    /**
     * 设置 paTrdProductDeal
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
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
     * 获取 operatType
     * @return operatType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operatType
     * @param operateType operatType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 termDays
     * @return termDays.
     */
    public String getTermDays() {
        return termDays;
    }
    
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(String termDays) {
        this.termDays = termDays;
    }
    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }
    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }
    
    /**
     * 获取 assetProvider
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }
    
    /**
     * 设置 assetProvider
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }
    
    /**
     * 获取 loadRate
     * @return loadRate.
     */
    public String getLoadRate() {
        return loadRate;
    }
    
    /**
     * 设置 loadRate
     * @param loadRate loadRate
     */
    public void setLoadRate(String loadRate) {
        this.loadRate = loadRate;
    }
    /**
     * 交易状态字符串
     * @return 交易状态
     * @see
     * @since 1.0
     */
    public String getTradeStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, super.getTradeStatus());
    }
    /**
     * 币种字符串
     * @return 币种
     * @see
     * @since 1.0
     */
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     * 币种
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="TrdDepositInvestTrade001.ccy",groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    /**
     * 交易方向
     * {@inheritDoc}
     */
    @NotEmpty(fieldName="TrdDepositInvestTrade001.ps",groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    /**
     * 交易方向字符串
     * @return 交易方向
     * @see
     * @since 1.0
     */
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    /**
     * 获取 entrustId
     * @return entrustId.
     */
    //huwenbin 指令接收交易录单验证数据已存在流程中
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEntrustId() {
        return entrustId;
    }
    
    /**
     * 设置 entrustId
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
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
     * 获取 type1
     * @return type1.
     */
    public String getType1() {
        return type1;
    }
    
    /**
     * 设置 type1
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
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
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
    /**
     * 组合分配
     */
    private List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto;
    

	public List<BussTrdTransfenpeiDto> getBussTrdTransfenpeiDto() {
		return bussTrdTransfenpeiDto;
	}

	public void setBussTrdTransfenpeiDto(List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto) {
		this.bussTrdTransfenpeiDto = bussTrdTransfenpeiDto;
	}

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }



    public String getAppAccAvailableCashT() {
        return appAccAvailableCashT;
    }

    public void setAppAccAvailableCashT(String appAccAvailableCashT) {
        this.appAccAvailableCashT = appAccAvailableCashT;
    }

    public String getAppAccAvailableCashT1() {
        return appAccAvailableCashT1;
    }

    public void setAppAccAvailableCashT1(String appAccAvailableCashT1) {
        this.appAccAvailableCashT1 = appAccAvailableCashT1;
    }

    public String getAppBottomAssetAmount() {
        return appBottomAssetAmount;
    }

    public void setAppBottomAssetAmount(String appBottomAssetAmount) {
        this.appBottomAssetAmount = appBottomAssetAmount;
    }

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
    }
}
