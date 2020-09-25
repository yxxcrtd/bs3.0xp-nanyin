/*
 * 文件名：TrdFundInvestTradeDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting 修改时间：2018年10月19日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.fund.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.fund.validator.TrdFundInvestTradeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 基金投资交易画面端dto 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年10月19日
 * @see TrdFundInvestTradeDto
 * @since 1.0
 */
@PageId("TrdFundInvestTrade002")
@ValidLogic(targetClass = TrdFundInvestTradeValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdFundInvestTradeDto extends TrdProductDeal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8235781005828570442L;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;

    /**
     * 组合T日可用现金
     */
    private BigDecimal proAvailableCash;

    /**
     * 组合T+1日可用现金,用于App端显示
     */
    private String proAvailableCash1;

    /**
     * 通道性质
     */
    private String payType;

    /**
     * 通道收取方式
     */
    private String collectType;

    /**
     * 通道可用现金
     */
    private BigDecimal channelAvailableCash;

    /**
     *  通道可用现金，用于APP显示
     */
    private String appChannelAvailableCash;

    /**
     * 划款金额
     */
    private BigDecimal tradeAmtAndFee;

    /**
     * 预计赎回到账日
     */
    private Date redCfmDate;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 控制操作按钮
     */
    private String flg;

    /**
     * 合同号
     */
    private String fTradeId;

    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 表格所选数据
     */
    private List<TrdFundInvestTradeDto> selectedItem;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    // add by 张年 20190917 start
    /**
     * 预计申购确认日（确认日期）
     */
    private Date purCfmDate;

    // add by 张年 20190917 end

    // 场内基金改造 add by renping 20191117 start

    /**
     * 交易所基金简称
     */
    private String trmFundAbbr;

    /**
     * 交易所基金类型
     */
    private String trmFundType;

    /**
     * 证券托管户
     */
    private String secManageAcctId;

    /**
     * 佣金
     */
    private BigDecimal commissionFee;

    /**
     * 佣金是否随交易支付
     */
    private String isPayWithTrade;

    /**
     * 费用明细List
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;

    /**
     * 通道费用明细，估值接口需要
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;

    /**
     * 转入金融产品代码
     */
    private String rFinprodId;

    /**
     * 转入资产代码
     */
    private String changeToFinprodMarketId;

    /**
     * 转入资产名称
     */
    private String changeToFinprodName;

    /**
     * 撤销理由
     */
    private String revokeReason;

    /**
     * 汇总附属表
     */
    private TrdDealTotalAdd dealTotalAdd;

    /**
     * 金融产品交易汇总
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 获取 trdTradeFeeDetail
     * 
     * @return trdTradeFeeDetail.
     */
    public TrdTradeFeeDetail getTrdTradeFeeDetail() {
        return trdTradeFeeDetail;
    }

    /**
     * 设置 trdTradeFeeDetail
     * 
     * @param trdTradeFeeDetail trdTradeFeeDetail
     */
    public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
        this.trdTradeFeeDetail = trdTradeFeeDetail;
    }

    /**
     * 获取 trdTradeFeeDetailList
     * 
     * @return trdTradeFeeDetailList.
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetailList() {
        return trdTradeFeeDetailList;
    }

    /**
     * 设置 trdTradeFeeDetailList
     * 
     * @param trdTradeFeeDetailList trdTradeFeeDetailList
     */
    public void setTrdTradeFeeDetailList(List<TrdTradeFeeDetail> trdTradeFeeDetailList) {
        this.trdTradeFeeDetailList = trdTradeFeeDetailList;
    }

    /**
     * 获取 trmFundAbbr
     * 
     * @return trmFundAbbr.
     */
    public String getTrmFundAbbr() {
        return trmFundAbbr;
    }

    /**
     * 设置 trmFundAbbr
     * 
     * @param trmFundAbbr trmFundAbbr
     */
    public void setTrmFundAbbr(String trmFundAbbr) {
        this.trmFundAbbr = trmFundAbbr;
    }

    /**
     * 获取 trmFundType
     * 
     * @return trmFundType.
     */
    public String getTrmFundType() {
        return trmFundType;
    }

    /**
     * 设置 trmFundType
     * 
     * @param trmFundType trmFundType
     */
    public void setTrmFundType(String trmFundType) {
        this.trmFundType = trmFundType;
    }

    /**
     * 获取 secManageAcctId
     * 
     * @return secManageAcctId.
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 secManageAcctId
     * 
     * @param secManageAcctId secManageAcctId
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 获取 commissionFee
     * 
     * @return commissionFee.
     */
    public BigDecimal getCommissionFee() {
        return commissionFee;
    }

    /**
     * 设置 commissionFee
     * 
     * @param commissionFee commissionFee
     */
    public void setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
    }

    /**
     * 获取 isPayWithTrade
     * 
     * @return isPayWithTrade.
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置 isPayWithTrade
     * 
     * @param isPayWithTrade isPayWithTrade
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }
    // 场内基金改造 add by renping 20191117 end

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
     * 取得 交易编号
     *
     * @return 交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return StringUtil.isNotEmpty(super.getTradeId()) ? super.getTradeId() : IDGenerator.generate();
    }

    /**
     * 业务类型
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return super.getBusiType();
    }

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 清算速度
     */
    private String settleSpreed;

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 实体组合
     */
    private String actPortfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 基金类型
     */
    private String type6;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 金融产品交易扩展
     */
    private List<PaTrdProductDeal> paTrdProductDeals;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

    /**
     * 金融产品交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAdds;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 二级类型
     */
    private String secondType;

    /** 新增资产二、三、四级分类 王贵奇 2019/7/30 开始 */

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
    private String counterType;

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
    
    private RiskCreditListCommonDto riskCreditListCommonDto;

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType9Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, getType9());
    }

    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType10Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, getType10());
    }

    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

    public String getType11Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, getType11());
    }

    /** 新增资产二、三、四级分类 王贵奇 2019/7/30 结束 */

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getSecondTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.基金类资产二级类型, secondType);
    }

    /**
     * 获取 flg
     * 
     * @return flg.
     */
    public String getFlg() {
        return flg;
    }

    /**
     * 设置 flg
     * 
     * @param flg flg
     */
    public void setFlg(String flg) {
        this.flg = flg;
    }

    /**
     * 获取 issuePartyId
     * 
     * @return issuePartyId.
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 issuePartyId
     * 
     * @param issuePartyId issuePartyId
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    /**
     * 获取 assetAvailablePosition
     * 
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }

    /**
     * 设置 assetAvailablePosition
     * 
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }

    /**
     * 获取 proAvailableCash
     * 
     * @return proAvailableCash.
     */
    public BigDecimal getProAvailableCash() {
        return proAvailableCash;
    }

    /**
     * 设置 proAvailableCash
     * 
     * @param proAvailableCash proAvailableCash
     */
    public void setProAvailableCash(BigDecimal proAvailableCash) {
        this.proAvailableCash = proAvailableCash;
    }

    /**
     * 获取 redCfmDate
     * 
     * @return redCfmDate.
     */
    public Date getRedCfmDate() {
        return redCfmDate;
    }

    /**
     * 获取 TradeDateString
     * 
     * @return TradeDateString.
     */
    public String getTradeDateString() {
        if (StringUtil.isNotEmpty(getTradeDate())) {
            return DateUtil.format(getTradeDate());
        }
        return "";
    }

    /**
     * 设置 redCfmDate
     * 
     * @param redCfmDate redCfmDate
     */
    public void setRedCfmDate(Date redCfmDate) {
        this.redCfmDate = redCfmDate;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
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

    /**
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
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
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<TrdFundInvestTradeDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdFundInvestTradeDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 settleSpreed
     * 
     * @return settleSpreed.
     */
    public String getSettleSpreed() {
        return settleSpreed;
    }

    /**
     * 设置 settleSpreed
     * 
     * @param settleSpreed settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    /**
     * 获取 trdProductDealAdd
     * 
     * @return trdProductDealAdd.
     */
    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    /**
     * 设置 trdProductDealAdd
     * 
     * @param trdProductDealAdds trdProductDealAdds
     */
    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 tradeMarket
     * 
     * @return tradeMarket.
     */
    @NotEmpty(fieldName = "TrdFundInvestTrade002.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 type6
     * 
     * @return type6.
     */

    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * 
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 validStatus
     * 
     * @return validStatus.
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 设置 validStatus
     * 
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
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
    @NotEmpty(fieldName = "TrdFundInvestTrade002.portfolioId", groups = {ValidateGroupEdit.class})
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

    // 资产推荐方 新增 zhuyongkang 20191023 start
    private String assetRecommandOrg;

    public String getAssetRecommandOrg() {
        return assetRecommandOrg;
    }

    public void setAssetRecommandOrg(String assetRecommandOrg) {
        this.assetRecommandOrg = assetRecommandOrg;
    }

    // 资产推荐方 新增 zhuyongkang 20191023 start
    /**
     * 获取 finprodName
     * 
     * @return finprodName.
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
     * 获取 paTrdProductDeal
     * 
     * @return paTrdProductDeal.
     */
    public List<PaTrdProductDeal> getPaTrdProductDeals() {
        return paTrdProductDeals;
    }

    /**
     * 设置 paTrdProductDeal
     * 
     * @param paTrdProductDeals paTrdProductDeals
     */
    public void setPaTrdProductDeals(List<PaTrdProductDeal> paTrdProductDeals) {
        this.paTrdProductDeals = paTrdProductDeals;
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
     * 获取 trdInvestPersonInfo
     * 
     * @return trdInvestPersonInfo.
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    /**
     * 设置 trdInvestPersonInfo
     * 
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

    public String getValidStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
    }

    public String getType6Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, type6);
    }

    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getTradeMarketStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, tradeMarket);
    }

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    @Override
    @NotEmpty(fieldName = "TrdFundInvestTrade002.ps", groups = {ValidateGroupEdit.class})
    public String getPs() {
        return super.getPs();
    }

    @Override
    @NotEmpty(fieldName = "TrdFundInvestTrade002.finprodId", groups = {ValidateGroupEdit.class})
    public String getFinprodId() {
        return super.getFinprodId();
    }

    @Override
    @Amount(fieldName = "TrdFundInvestTrade002.tradeAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }

    @Override
    @Amount(fieldName = "申购费/赎回费", maxIntBit = 10, groups = {ValidateGroupEdit.class})
    public BigDecimal getFeeAmtPay() {
        return super.getFeeAmtPay();
    }

    @Override
    @Amount(fieldName = "TrdFundInvestTrade002.tradeSha", groups = {ValidateGroupEdit.class})
    public BigDecimal getShareAmt() {
        return super.getShareAmt();
    }

    /**
     * 通道
     */
    // private String channelId;
    //

    /**
     * 通道费
     */
    private BigDecimal channelAmt;

    /**
     * 交易金额
     */
    private BigDecimal dealAmt;

    // public String getChannelId() {
    // return channelId;
    // }

    // public void setChannelId(String channelId) {
    // this.channelId = channelId;
    // }

    public BigDecimal getChannelAmt() {
        return channelAmt;
    }

    public void setChannelAmt(BigDecimal channelAmt) {
        this.channelAmt = channelAmt;
    }

    public BigDecimal getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(BigDecimal dealAmt) {
        this.dealAmt = dealAmt;
    }

    /**
     * 获取 channelAvailableCash
     *
     * @return channelAvailableCash
     */
    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    /**
     * 设置 channelAvailableCash
     *
     * @param channelAvailableCash channelAvailableCash
     */
    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
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

    /**
     * 获取 payType
     *
     * @return payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 payType
     *
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
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

    @NotEmpty(fieldName = "TrdFundInvestTrade002.purCfmDate", groups = {ValidateGroupEdit.class})
    public Date getPurCfmDate() {
        return purCfmDate;
    }

    public void setPurCfmDate(Date purCfmDate) {
        this.purCfmDate = purCfmDate;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    @Override
    public String getfTradeId() {
        return fTradeId;
    }

    @Override
    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    /**
     * 获取 rFinprodId
     *
     * @return rFinprodId
     */
    public String getrFinprodId() {
        return rFinprodId;
    }

    /**
     * 设置 rFinprodId
     *
     * @param rFinprodId rFinprodId
     */
    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId;
    }

    /**
     * 获取 changeToFinprodMarketId
     *
     * @return changeToFinprodMarketId
     */
    public String getChangeToFinprodMarketId() {
        return changeToFinprodMarketId;
    }

    /**
     * 设置 changeToFinprodMarketId
     *
     * @param changeToFinprodMarketId changeToFinprodMarketId
     */
    public void setChangeToFinprodMarketId(String changeToFinprodMarketId) {
        this.changeToFinprodMarketId = changeToFinprodMarketId;
    }

    /**
     * 获取 changeToFinprodName
     *
     * @return changeToFinprodName
     */
    public String getChangeToFinprodName() {
        return changeToFinprodName;
    }

    /**
     * 设置 changeToFinprodName
     *
     * @param changeToFinprodName changeToFinprodName
     */
    public void setChangeToFinprodName(String changeToFinprodName) {
        this.changeToFinprodName = changeToFinprodName;
    }

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 dealTotalAdd
     *
     * @return dealTotalAdd
     */
    public TrdDealTotalAdd getDealTotalAdd() {
        return dealTotalAdd;
    }

    /**
     * 设置 dealTotalAdd
     *
     * @param dealTotalAdd dealTotalAdd
     */
    public void setDealTotalAdd(TrdDealTotalAdd dealTotalAdd) {
        this.dealTotalAdd = dealTotalAdd;
    }

    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }

    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }

    public String getAppChannelAvailableCash() {
        return appChannelAvailableCash;
    }

    public void setAppChannelAvailableCash(String appChannelAvailableCash) {
        this.appChannelAvailableCash = appChannelAvailableCash;
    }

    public String getProAvailableCash1() {
        return proAvailableCash1;
    }

    public void setProAvailableCash1(String proAvailableCash1) {
        this.proAvailableCash1 = proAvailableCash1;
    }
}
