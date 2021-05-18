/*
 * 文件名：TrdPlanRateInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trdplanrateinvesttrade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.dto.BussTrdAvailableCashQuery;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.validate.TrdPlanRateInvestTradeDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 计划类（利率型）投资交易
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTradeDto
 * @since 1.0
 */
@PageId("TrdPlanRateInvestTrade002")
@ValidLogic(targetClass = TrdPlanRateInvestTradeDtoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class TrdPlanRateInvestTradeDto extends WflTrdProductDeal implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5309538894816479429L;
    
    /**
     * 组合分配
     */
    private List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto;
    
    /**
     * 带通道费的交易金额
     */
    private BigDecimal tradeAmtAndFee;
	
    /**
     * 委托代码
     */
    @Length(fieldName="TrdPlanRateInvestTrade001.entrustId",max=50,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String entrustId;
    /**
     * 组合代码
     */
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.portfolioId",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdPlanRateInvestTrade001.portfolioId",max=32,groups = {ValidateGroupEdit.class,
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
     * 金融产品类型
     */
    private String type1;
    /*原因、zhuyongkang 2019/7/30  start */
    /**
     * 资产二级分类
     */
    private String type9;
    /**
     * 资产三级分类
     */
    private String type10;
    /**
     * 资产四级分类
     */
    private String type11;
    /**
     * 通道费
     */
    private BigDecimal channelFee;
    
    /**
     * 通道收取方式
     */
    private String collectType;
    /**
     * 获取 Type9
     *
     * @return type9.
     */
    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }
    /**
     * 获取 type10
     *
     * @return type10.
     */
    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }
    /**
     * 获取 type11
     *
     * @return type11.
     */
    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }
    /**
     * 资产类型
     * @return 资产二级类型
     * @see
     * @since 1.0
     */
    public String getType9Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getType9());
    }
    public String getType10Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getType10());
    }
    public String getType11Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getType11());
    }
    /* zhuyongkang 2019/7/30  end*/
    /**
     * 金融产品名称
     */
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.portfolioName",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdPlanRateInvestTrade001.portfolioName",max=200,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    private String finprodName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 市场机构代码
     */
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.finprodId",groups = {ValidateGroupEdit.class} )
    private String finprodMarketId;
    /**
     * elementId
     */
    private String elementId;
    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;
    /**
     * 划款指令
     */
    @NotEmpty(fieldName="TrdPlanRateInvestTrade002.stlPayInfo",groups = {ValidateGroupEdit.class})
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
     * 金融产品交易实体类-供应收款类型交易
     */
    private FinProductTradeDto finRecdProductTradeDto;
    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto;
    /**
     * 存款实体类
     */
    private List<TrdPlanRateInvestTradeDto> selectedItem;
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
    private BigDecimal assetPosition;
    /**
     * 净投资收益率
     */
    private BigDecimal investRate;
    /**
     * 组合可用现金
     */
    private BigDecimal avalibleCash;
    /**
     * 组合T+1日可用现金
     */
    private BigDecimal accAvailableCashT1;
    /**
     * 托管户剩余现金
     */
    private BigDecimal accAvailableCash;
    /**
     * 清算速度
     */
    private String mySettleSpeed;

    /**
     * 交易对手类型
     */
    private String counterType;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;


    /**
     * 组合T+1日可用现金,用于APP显示
     */
    private String appAccAvailableCashT1Str;

    /**
     * 组合T日可用现金，用于APP显示
     */
    private String appAccAvailableCashStr;

    /**
     * 资产可用持仓，用于APP显示
     *
     */
    private String appAssetPositionStr;



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
     * 风险试算实体类集合
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    /**
     * 可用资产实体类
     */
    private BussTrdAvailableCashQuery bussTrdAvailableCashQuery;
    /**
     * 还本不带息交易类
     */
    private  List<TrdProductDeal> trdProductDealList;

    /**
     * 支付未支付利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal payUnpaidintAmt;

    /**
     * 是否转让还本未支付利息
     */
    private String ispayUnpaidint;
    /**
     * 汇总扩展表
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    @NotEmpty(fieldName="TrdPlanRateInvestTrade002.settleSpeed",groups = {ValidateGroupEdit.class})
    @Override
    public Long getSettleSpeed(){
        return super.getSettleSpeed();
    }
    /**
     * 获取 bussTrdAvailableCashQuery
     * @return bussTrdAvailableCashQuery.
     */
    public BussTrdAvailableCashQuery getBussTrdAvailableCashQuery() {
        return bussTrdAvailableCashQuery;
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
     * 设置 bussTrdAvailableCashQuery
     * @param bussTrdAvailableCashQuery bussTrdAvailableCashQuery
     */
    public void setBussTrdAvailableCashQuery(BussTrdAvailableCashQuery bussTrdAvailableCashQuery) {
        this.bussTrdAvailableCashQuery = bussTrdAvailableCashQuery;
    }

    /**
     * 资产类型
     * @return 资产类型
     * @see
     * @since 1.0
     */
    public String getFinprodTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, this.getType1());
    }
    
    /**
     * 获取 mySettleSpeed
     * @return mySettleSpeed.
     */
    public String getMySettleSpeed() {
        return mySettleSpeed;
    }
    
    /**
     * 设置 mySettleSpeed
     * @param mySettleSpeed mySettleSpeed
     */
    public void setMySettleSpeed(String mySettleSpeed) {
        this.mySettleSpeed = mySettleSpeed;
    }

    /**
     * 获取 investRate
     * @return investRate.
     */
    public BigDecimal getInvestRate() {
        return investRate;
    }
    
    /**
     * 设置 investRate
     * @param investRate investRate
     */
    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
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
     * 获取 accAvailableCashT1
     * @return accAvailableCashT1.
     */
    public BigDecimal getAccAvailableCashT1() {
        return accAvailableCashT1;
    }
    
    
    /**
     * 获取 assetPosition
     * @return assetPosition.
     */
    public BigDecimal getAssetPosition() {
        return assetPosition;
    }

    
    /**
     * 设置 assetPosition
     * @param assetPosition assetPosition
     */
    public void setAssetPosition(BigDecimal assetPosition) {
        this.assetPosition = assetPosition;
    }

    
    /**
     * 获取 avalibleCash
     * @return avalibleCash.
     */
    public BigDecimal getAvalibleCash() {
        return avalibleCash;
    }

    
    /**
     * 设置 avalibleCash
     * @param avalibleCash avalibleCash
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
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
   /* @NotEmpty(fieldName="TrdPlanRateInvestTrade001.finprodType",groups = {ValidateGroupEdit.class})*/
    public String getFinprodType(){
        return super.getFinprodType();
    }
    /**
     * 备注
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName="TrdPlanRateInvestTrade002.remark",max=1000,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    public String getRemark(){
        return super.getRemark();
    }
    /**
     * 利息总额
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="TrdPlanRateInvestTrade002.intAmt",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdPlanRateInvestTrade002.intAmt",max=16 ,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    public BigDecimal getIntAmt(){
        return super.getIntAmt();
    }
    /**
     * 本金金额
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="TrdPlanRateInvestTrade002.prinAmt",groups = {ValidateGroupEdit.class})
    @Length(fieldName="TrdPlanRateInvestTrade002.prinAmt",max=30  ,groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
    public BigDecimal getPrinAmt(){
        return super.getPrinAmt();
    }
    
    @Override
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.tradeDate",groups = {ValidateGroupEdit.class})
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
    public List<TrdPlanRateInvestTradeDto> getSelectedItem() {
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
    public void setSelectedItem(List<TrdPlanRateInvestTradeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    private String totleTradeId;

    /**
     * 设置主键
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getTotleTradeId(){
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
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
     * @param  operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.ccy",groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    /**
     * 交易方向
     * {@inheritDoc}
     */
    @NotEmpty(fieldName="TrdPlanRateInvestTrade001.ps",groups = {ValidateGroupEdit.class})
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

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

    /**
     * 获取 trdProductDealList
     *
     * @return trdProductDealList
     */
    public List<TrdProductDeal> getTrdProductDealList() {
        return trdProductDealList;
    }

    /**
     * 设置 trdProductDealList
     *
     * @param trdProductDealList trdProductDealList
     */
    public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
        this.trdProductDealList = trdProductDealList;
    }

    /**
     * 获取 payUnpaidintAmt
     *
     * @return payUnpaidintAmt
     */
    public BigDecimal getPayUnpaidintAmt() {
        return payUnpaidintAmt;
    }

    /**
     * 设置 payUnpaidintAmt
     *
     * @param payUnpaidintAmt payUnpaidintAmt
     */
    public void setPayUnpaidintAmt(BigDecimal payUnpaidintAmt) {
        this.payUnpaidintAmt = payUnpaidintAmt;
    }

    /**
     * 获取 ispayUnpaidint
     *
     * @return ispayUnpaidint
     */
    public String getIspayUnpaidint() {
        return ispayUnpaidint;
    }

    /**
     * 设置 ispayUnpaidint
     *
     * @param ispayUnpaidint ispayUnpaidint
     */
    public void setIspayUnpaidint(String ispayUnpaidint) {
        this.ispayUnpaidint = ispayUnpaidint;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     *
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 finRecdProductTradeDto
     *
     * @return finRecdProductTradeDto
     */
    public FinProductTradeDto getFinRecdProductTradeDto() {
        return finRecdProductTradeDto;
    }

    /**
     * 设置 finRecdProductTradeDto
     *
     * @param finRecdProductTradeDto finRecdProductTradeDto
     */
    public void setFinRecdProductTradeDto(FinProductTradeDto finRecdProductTradeDto) {
        this.finRecdProductTradeDto = finRecdProductTradeDto;
    }

	public List<BussTrdTransfenpeiDto> getBussTrdTransfenpeiDto() {
		return bussTrdTransfenpeiDto;
	}

	public void setBussTrdTransfenpeiDto(List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto) {
		this.bussTrdTransfenpeiDto = bussTrdTransfenpeiDto;
	}

	public BigDecimal getTradeAmtAndFee() {
		return tradeAmtAndFee;
	}

	public void setTradeAmtAndFee(BigDecimal tradeAmtAndFee) {
		this.tradeAmtAndFee = tradeAmtAndFee;
	}

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }



    public String getAppAccAvailableCashT1Str() {
        return appAccAvailableCashT1Str;
    }

    public void setAppAccAvailableCashT1Str(String appAccAvailableCashT1Str) {
        this.appAccAvailableCashT1Str = appAccAvailableCashT1Str;
    }

    public String getAppAccAvailableCashStr() {
        return appAccAvailableCashStr;
    }

    public void setAppAccAvailableCashStr(String appAccAvailableCashStr) {
        this.appAccAvailableCashStr = appAccAvailableCashStr;
    }

    public String getAppAssetPositionStr() {
        return appAssetPositionStr;
    }

    public void setAppAssetPositionStr(String appAssetPositionStr) {
        this.appAssetPositionStr = appAssetPositionStr;
    }



}
