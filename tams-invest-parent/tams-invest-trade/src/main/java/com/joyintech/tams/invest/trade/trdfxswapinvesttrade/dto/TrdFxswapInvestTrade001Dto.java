/*
 * 文件名：TrdFxswapInvestTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdfxswapinvesttrade.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.validate.TrdFxswapInvestTrade001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 外汇掉期投资交易实体类
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Dto
 * @since 1.0
 */
@PageId("TrdFxswapInvestTrade002")
@ValidLogic(targetClass = TrdFxswapInvestTrade001Validator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdFxswapInvestTrade001Dto extends WflTrdProductDeal implements ProcessEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1214752543324317680L;

    /**
     * 交易日
     */
    private Date tradeDateBegin;

    /**
     * 交易日
     */
    private Date tradeDateEnd;

    /**
     * 近端交割日
     */
    private Date vDateBegin;

    /**
     * 近端交割日
     */
    private Date vDateEnd;

    /**
     * 远端交割日
     */
    private Date mDateBegin;

    /**
     * 远端交割日
     */
    private Date mDateEnd;

    /**
     * 委托代码
     */
    @Length(fieldName = "TrdFxswapInvestTrade001.entrustId", max = 50, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
    private String entrustId;

    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.portfolioId", groups = {
        ValidateGroupEdit.class})
    @Length(fieldName = "TrdFxswapInvestTrade001.portfolioId", max = 32, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 交易类型
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.excDealType", groups = {
        ValidateGroupEdit.class})
    private String excDealType;

    /**
     * 货币对
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.curPair", groups = {ValidateGroupEdit.class})
    private String curPair;

    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.calendarId", groups = {ValidateGroupEdit.class})
    private String calendarId;

    /**
     * 交易币种
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.dealCur", groups = {ValidateGroupEdit.class})
    private String dealCur;

    /**
     * 对应币种
     */
    private String corCcy;

    /**
     * 即期汇率
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.spotRate", groups = {ValidateGroupEdit.class})
    private BigDecimal spotRate;

    /**
     * 期限类型
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.excFxsTermType", groups = {
        ValidateGroupEdit.class})
    private String excFxsTermType;

    /**
     * 我方角色
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.ourRole", groups = {ValidateGroupEdit.class})
    private String ourRole;

    /**
     * 交易模式
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.dealMode", groups = {ValidateGroupEdit.class})
    private String dealMode;

    /**
     * 清算模式
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.settMode", groups = {ValidateGroupEdit.class})
    private String settMode;

    /**
     * 近端期限
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.nTermTypeExc", groups = {
        ValidateGroupEdit.class})
    private String nTermTypeExc;

    /**
     * 近端掉期点
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.nPoint", groups = {ValidateGroupEdit.class})
    private BigDecimal nPoint;

    /**
     * 近端掉期全价
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.nFprice", groups = {ValidateGroupEdit.class})
    private BigDecimal nFprice;

    /**
     * 交易货币金额
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.nDealCurAmt", groups = {
        ValidateGroupEdit.class})
    private BigDecimal nDealCurAmt;

    /**
     * 交易货币可用持仓
     */
    private BigDecimal dealCurPos;

    /**
     * 对应货币金额
     */
    private BigDecimal nCorCurAmt;

    /**
     * 对应货币可用持仓
     */
    private BigDecimal corCurPos;

    /**
     * 折美元金额 近端
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.nUsdAmt", groups = {ValidateGroupEdit.class})
    private BigDecimal nUsdAmt;

    /**
     * 交易费用
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.feeAmt", groups = {ValidateGroupEdit.class})
    private BigDecimal feeAmt;

    /**
     * 远端期限
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.fTermTypeExc", groups = {
        ValidateGroupEdit.class})
    private String fTermTypeExc;

    /**
     * 远端掉期点
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.fPoint", groups = {ValidateGroupEdit.class})
    private BigDecimal fPoint;

    /**
     * 远端掉期全价
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.fFprice", groups = {ValidateGroupEdit.class})
    private BigDecimal fFprice;

    /**
     * 交易货币金额
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.fDealCurAmt", groups = {
        ValidateGroupEdit.class})
    private BigDecimal fDealCurAmt;

    /**
     * 对应货币金额
     */
    private BigDecimal fCorCurAmt;

    /**
     * 折美元金额
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.fUsdAmt", groups = {ValidateGroupEdit.class})
    private BigDecimal fUsdAmt;

    /**
     * 资产提供行
     */
    private String assetProvider;

    /**
     * 操作类型
     */
    private String operateType;

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
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;
    
    /**
     * 金融产品交易附加
     */
    private TrdProductDealAdd trdProductDealAdd;

    /**
     * 交易类金融产品
     */
    private FinTradeProduct finTradeProduct;
    
    /**
     * 组合表
     */
    private PtlTrade ptlTrade;

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
     * 外汇掉期实体类
     */
    private List<TrdFxswapInvestTrade001Dto> selectedItem;

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
     * 子组合代码
     */
    private String actPortfolioId;

    /**
     * 风险试算实体类集合
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 到期交易交易编号
     */
    private String tradeId1;
    
    /**
     * 持有目的 HOLD_PURPOSE 中债新增 20200409
     */
    private String holdPurpose;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;

    /**
     * 获取 tradeId1
     * 
     * @return tradeId1.
     */
    public String getTradeId1() {
        return tradeId1;
    }

    /**
     * 设置 tradeId1
     * 
     * @param tradeId1 tradeId1
     */
    public void setTradeId1(String tradeId1) {
        this.tradeId1 = tradeId1;
    }

    /**
     * 获取 riskTrialRetDto
     * 
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    /**
     * 设置 riskTrialRetDto
     * 
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
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
     * 备注 {@inheritDoc}
     */
    @Override
    @Length(fieldName = "TrdFxswapInvestTrade002.remark", max = 1000, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
    public String getRemark() {
        return super.getRemark();
    }

    @Override
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.tradeDate", groups = {ValidateGroupEdit.class})
    public Date getTradeDate() {
        return super.getTradeDate();
    }

    /**
     * 获取 cancelRemark
     * 
     * @return cancelRemark.
     */
    public String getCancelRemark() {
        return cancelRemark;
    }

    /**
     * 设置 cancelRemark
     * 
     * @param cancelRemark cancelRemark
     */
    @Override
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
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
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<TrdFxswapInvestTrade001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 获取 interfSendOpicsList
     * 
     * @return interfSendOpicsList.
     */
    public List<InterfSendOpics> getInterfSendOpicsList() {
        return interfSendOpicsList;
    }

    /**
     * 设置 interfSendOpicsList
     * 
     * @param interfSendOpicsList interfSendOpicsList
     */
    public void setInterfSendOpicsList(List<InterfSendOpics> interfSendOpicsList) {
        this.interfSendOpicsList = interfSendOpicsList;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdFxswapInvestTrade001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 设置主键 {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getTradeId() {
        return super.getTradeId();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     *
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodType2() {
        return super.getFinprodType2();
    }

    /**
     * 业务类型 {@inheritDoc}
     */
    @Override
    public String getBusiType() {
        return super.getBusiType();
    }

    /**
     * 获取 trdInvestPersonInfo002Dto
     * 
     * @return trdInvestPersonInfo002Dto.
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo002Dto() {
        return trdInvestPersonInfo002Dto;
    }

    /**
     * 设置 trdInvestPersonInfo002Dto
     * 
     * @param trdInvestPersonInfo002Dto trdInvestPersonInfo002Dto
     */
    public void setTrdInvestPersonInfo002Dto(TrdInvestPersonInfo002Dto trdInvestPersonInfo002Dto) {
        this.trdInvestPersonInfo002Dto = trdInvestPersonInfo002Dto;
    }

    /**
     * 获取 finProductTradeDto
     * 
     * @return finProductTradeDto.
     */
    public FinProductTradeDto getFinProductTradeDto() {
        return finProductTradeDto;
    }

    /**
     * 设置 finProductTradeDto
     * 
     * @param finProductTradeDto finProductTradeDto
     */
    public void setFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        this.finProductTradeDto = finProductTradeDto;
    }

    /**
     * 获取 stlPayOrdersList
     * 
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     * 
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public PtlTrade getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(PtlTrade ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 paTrdProductDeal
     * 
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     * 
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
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
     * 获取 operatType
     * 
     * @return operatType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operatType
     * 
     * @param operateType operatType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 assetProvider
     * 
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }

    /**
     * 设置 assetProvider
     * 
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }

    /**
     * 交易状态字符串
     * 
     * @return 交易状态
     * @see
     * @since 1.0
     */
    public String getTradeStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, super.getTradeStatus());
    }

    /**
     * 币种字符串
     * 
     * @return 币种
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    /**
     * 交易方向 {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade001.ps", groups = {ValidateGroupEdit.class})
    @Override
    public String getPs() {
        return super.getPs();
    }

    /**
     * 近端交割日 {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.vdate", groups = {ValidateGroupEdit.class})
    @Override
    public Date getVdate() {
        return super.getVdate();
    }

    /**
     * 远端交割日 {@inheritDoc}
     */
    @NotEmpty(fieldName = "TrdFxswapInvestTrade002.mdate", groups = {ValidateGroupEdit.class})
    @Override
    public Date getMdate() {
        return super.getMdate();
    }

    /**
     * 交易方向字符串
     * 
     * @return 交易方向
     * @see
     * @since 1.0
     */
    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    /**
     * 获取 entrustId
     * 
     * @return entrustId.
     */
    // huwenbin 指令接收交易录单验证数据已存在流程中
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * 
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 excDealType
     * 
     * @return excDealType.
     */
    public String getExcDealType() {
        return excDealType;
    }

    /**
     * 设置 excDealType
     * 
     * @param excDealType excDealType
     */
    public void setExcDealType(String excDealType) {
        this.excDealType = excDealType;
    }

    /**
     * 获取 curPair
     * 
     * @return curPair.
     */
    public String getCurPair() {
        return curPair;
    }

    /**
     * 设置 curPair
     * 
     * @param curPair curPair
     */
    public void setCurPair(String curPair) {
        this.curPair = curPair;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 dealCur
     * 
     * @return dealCur.
     */
    public String getDealCur() {
        return dealCur;
    }

    /**
     * 设置 dealCur
     * 
     * @param dealCur dealCur
     */
    public void setDealCur(String dealCur) {
        this.dealCur = dealCur;
    }

    /**
     * 获取 corCcy
     * 
     * @return corCcy.
     */
    public String getCorCcy() {
        return corCcy;
    }

    /**
     * 设置 corCcy
     * 
     * @param corCcy corCcy
     */
    public void setCorCcy(String corCcy) {
        this.corCcy = corCcy;
    }

    /**
     * 获取 spotRate
     * 
     * @return spotRate.
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * 设置 spotRate
     * 
     * @param spotRate spotRate
     */
    public void setSpotRate(BigDecimal spotRate) {
        this.spotRate = spotRate;
    }

    /**
     * 获取 excFxsTermType
     * 
     * @return excFxsTermType.
     */
    public String getExcFxsTermType() {
        return excFxsTermType;
    }

    /**
     * 设置 excFxsTermType
     * 
     * @param excFxsTermType excFxsTermType
     */
    public void setExcFxsTermType(String excFxsTermType) {
        this.excFxsTermType = excFxsTermType;
    }

    /**
     * 获取 ourRole
     * 
     * @return ourRole.
     */
    public String getOurRole() {
        return ourRole;
    }

    /**
     * 设置 ourRole
     * 
     * @param ourRole ourRole
     */
    public void setOurRole(String ourRole) {
        this.ourRole = ourRole;
    }

    /**
     * 获取 dealMode
     * 
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 dealMode
     * 
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    /**
     * 获取 settMode
     * 
     * @return settMode.
     */
    public String getSettMode() {
        return settMode;
    }

    /**
     * 设置 settMode
     * 
     * @param settMode settMode
     */
    public void setSettMode(String settMode) {
        this.settMode = settMode;
    }

    /**
     * 获取 nTermTypeExc
     * 
     * @return nTermTypeExc.
     */
    public String getnTermTypeExc() {
        return nTermTypeExc;
    }

    /**
     * 设置 nTermTypeExc
     * 
     * @param nTermTypeExc nTermTypeExc
     */
    public void setnTermTypeExc(String nTermTypeExc) {
        this.nTermTypeExc = nTermTypeExc;
    }

    /**
     * 获取 nPoint
     * 
     * @return nPoint.
     */
    public BigDecimal getnPoint() {
        return nPoint;
    }

    /**
     * 设置 nPoint
     * 
     * @param nPoint nPoint
     */
    public void setnPoint(BigDecimal nPoint) {
        this.nPoint = nPoint;
    }

    /**
     * 获取 nFprice
     * 
     * @return nFprice.
     */
    public BigDecimal getnFprice() {
        return nFprice;
    }

    /**
     * 设置 nFprice
     * 
     * @param nFprice nFprice
     */
    public void setnFprice(BigDecimal nFprice) {
        this.nFprice = nFprice;
    }

    /**
     * 获取 nDealCurAmt
     * 
     * @return nDealCurAmt.
     */
    public BigDecimal getnDealCurAmt() {
        return nDealCurAmt;
    }

    /**
     * 设置 nDealCurAmt
     * 
     * @param nDealCurAmt nDealCurAmt
     */
    public void setnDealCurAmt(BigDecimal nDealCurAmt) {
        this.nDealCurAmt = nDealCurAmt;
    }

    /**
     * 获取 dealCurPos
     * 
     * @return dealCurPos.
     */
    public BigDecimal getDealCurPos() {
        return dealCurPos;
    }

    /**
     * 设置 dealCurPos
     * 
     * @param dealCurPos dealCurPos
     */
    public void setDealCurPos(BigDecimal dealCurPos) {
        this.dealCurPos = dealCurPos;
    }

    /**
     * 获取 nCorCurAmt
     * 
     * @return nCorCurAmt.
     */
    public BigDecimal getnCorCurAmt() {
        return nCorCurAmt;
    }

    /**
     * 设置 nCorCurAmt
     * 
     * @param nCorCurAmt nCorCurAmt
     */
    public void setnCorCurAmt(BigDecimal nCorCurAmt) {
        this.nCorCurAmt = nCorCurAmt;
    }

    /**
     * 获取 corCurPos
     * 
     * @return corCurPos.
     */
    public BigDecimal getCorCurPos() {
        return corCurPos;
    }

    /**
     * 设置 corCurPos
     * 
     * @param corCurPos corCurPos
     */
    public void setCorCurPos(BigDecimal corCurPos) {
        this.corCurPos = corCurPos;
    }

    /**
     * 获取 nUsdAmt
     * 
     * @return nUsdAmt.
     */
    public BigDecimal getnUsdAmt() {
        return nUsdAmt;
    }

    /**
     * 设置 nUsdAmt
     * 
     * @param nUsdAmt nUsdAmt
     */
    public void setnUsdAmt(BigDecimal nUsdAmt) {
        this.nUsdAmt = nUsdAmt;
    }

    /**
     * 获取 feeAmt
     * 
     * @return feeAmt.
     */
    @Override
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 feeAmt
     * 
     * @param feeAmt feeAmt
     */
    @Override
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 获取 fTermTypeExc
     * 
     * @return fTermTypeExc.
     */
    public String getfTermTypeExc() {
        return fTermTypeExc;
    }

    /**
     * 设置 fTermTypeExc
     * 
     * @param fTermTypeExc fTermTypeExc
     */
    public void setfTermTypeExc(String fTermTypeExc) {
        this.fTermTypeExc = fTermTypeExc;
    }

    /**
     * 获取 fPoint
     * 
     * @return fPoint.
     */
    public BigDecimal getfPoint() {
        return fPoint;
    }

    /**
     * 设置 fPoint
     * 
     * @param fPoint fPoint
     */
    public void setfPoint(BigDecimal fPoint) {
        this.fPoint = fPoint;
    }

    /**
     * 获取 fFprice
     * 
     * @return fFprice.
     */
    public BigDecimal getfFprice() {
        return fFprice;
    }

    /**
     * 设置 fFprice
     * 
     * @param fFprice fFprice
     */
    public void setfFprice(BigDecimal fFprice) {
        this.fFprice = fFprice;
    }

    /**
     * 获取 fDealCurAmt
     * 
     * @return fDealCurAmt.
     */
    public BigDecimal getfDealCurAmt() {
        return fDealCurAmt;
    }

    /**
     * 设置 fDealCurAmt
     * 
     * @param fDealCurAmt fDealCurAmt
     */
    public void setfDealCurAmt(BigDecimal fDealCurAmt) {
        this.fDealCurAmt = fDealCurAmt;
    }

    /**
     * 获取 fCorCurAmt
     * 
     * @return fCorCurAmt.
     */
    public BigDecimal getfCorCurAmt() {
        return fCorCurAmt;
    }

    /**
     * 设置 fCorCurAmt
     * 
     * @param fCorCurAmt fCorCurAmt
     */
    public void setfCorCurAmt(BigDecimal fCorCurAmt) {
        this.fCorCurAmt = fCorCurAmt;
    }

    /**
     * 获取 fUsdAmt
     * 
     * @return fUsdAmt.
     */
    public BigDecimal getfUsdAmt() {
        return fUsdAmt;
    }

    /**
     * 设置 fUsdAmt
     * 
     * @param fUsdAmt fUsdAmt
     */
    public void setfUsdAmt(BigDecimal fUsdAmt) {
        this.fUsdAmt = fUsdAmt;
    }

    /**
     * 获取 excDealTypeStr
     * 
     * @return excDealTypeStr.
     */
    public String getExcDealTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.外汇交易类型, this.getExcDealType());
    }

    /**
     * 获取 curPairStr
     * 
     * @return curPairStr.
     */
    public String getCurPairStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.货币对, this.getCurPair());
    }

    /**
     * 获取 dealCurStr
     * 
     * @return dealCurStr.
     */
    public String getDealCurStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getDealCur());
    }

    /**
     * 获取 corCcyStr
     * 
     * @return corCcyStr.
     */
    public String getCorCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCorCcy());
    }

    /**
     * 获取 excFxsTermTypeStr
     * 
     * @return excFxsTermTypeStr.
     */
    public String getExcFxsTermTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.外汇掉期期限类型, this.getExcFxsTermType());
    }

    /**
     * 获取 nTermTypeExcStr
     * 
     * @return nTermTypeExcStr.
     */
    public String getnTermTypeExcStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.外汇期限, this.getnTermTypeExc());
    }

    /**
     * 获取 fTermTypeExcStr
     * 
     * @return fTermTypeExcStr.
     */
    public String getfTermTypeExcStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.外汇期限, this.getfTermTypeExc());
    }

    /**
     * 获取 tradeDateBegin
     * 
     * @return tradeDateBegin.
     */
    public Date getTradeDateBegin() {
        return tradeDateBegin;
    }

    /**
     * 设置 tradeDateBegin
     * 
     * @param tradeDateBegin tradeDateBegin
     */
    public void setTradeDateBegin(Date tradeDateBegin) {
        this.tradeDateBegin = tradeDateBegin;
    }

    /**
     * 获取 tradeDateEnd
     * 
     * @return tradeDateEnd.
     */
    public Date getTradeDateEnd() {
        return tradeDateEnd;
    }

    /**
     * 设置 tradeDateEnd
     * 
     * @param tradeDateEnd tradeDateEnd
     */
    public void setTradeDateEnd(Date tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
    }

    /**
     * 获取 vDateBegin
     * 
     * @return vDateBegin.
     */
    public Date getvDateBegin() {
        return vDateBegin;
    }

    /**
     * 设置 vDateBegin
     * 
     * @param vDateBegin vDateBegin
     */
    public void setvDateBegin(Date vDateBegin) {
        this.vDateBegin = vDateBegin;
    }

    /**
     * 获取 vDateEnd
     * 
     * @return vDateEnd.
     */
    public Date getvDateEnd() {
        return vDateEnd;
    }

    /**
     * 设置 vDateEnd
     * 
     * @param vDateEnd vDateEnd
     */
    public void setvDateEnd(Date vDateEnd) {
        this.vDateEnd = vDateEnd;
    }

    /**
     * 获取 mDateBegin
     * 
     * @return mDateBegin.
     */
    public Date getmDateBegin() {
        return mDateBegin;
    }

    /**
     * 设置 mDateBegin
     * 
     * @param mDateBegin mDateBegin
     */
    public void setmDateBegin(Date mDateBegin) {
        this.mDateBegin = mDateBegin;
    }

    /**
     * 获取 mDateEnd
     * 
     * @return mDateEnd.
     */
    public Date getmDateEnd() {
        return mDateEnd;
    }

    /**
     * 设置 mDateEnd
     * 
     * @param mDateEnd mDateEnd
     */
    public void setmDateEnd(Date mDateEnd) {
        this.mDateEnd = mDateEnd;
    }

    /**
     * 获取 vdateStr
     * 
     * @return vdateStr.
     */
    public String getVdateStr() {
        return DateUtil.format(super.getVdate());
    }

    /**
     * 获取 mdateStr
     * 
     * @return mdateStr.
     */
    public String getMdateStr() {
        return DateUtil.format(super.getMdate());
    }
    /**
     * 金融产品分类
     */
    private FinProductType finProductType;

    /**
     * 获取 finProductType
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }
    
    /**
     * 获取 actPortfolioId
     * @return actPortfolioId.
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }
    
    /**
     * 设置 actPortfolioId
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 trdProductDeal
     * 
     * @return trdProductDeal.
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     * 
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 trdProductDealAdd
     * 
     * @return trdProductDealAdd.
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     * 
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 finTradeProduct
     * 
     * @return finTradeProduct.
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     * 
     * @param finTradeProduct finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

	/**
	 * @return the holdPurpose
	 */
	public String getHoldPurpose() {
		return holdPurpose;
	}

	/**
	 * @param holdPurpose the holdPurpose to set
	 */
	public void setHoldPurpose(String holdPurpose) {
		this.holdPurpose = holdPurpose;
	}

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }
}
