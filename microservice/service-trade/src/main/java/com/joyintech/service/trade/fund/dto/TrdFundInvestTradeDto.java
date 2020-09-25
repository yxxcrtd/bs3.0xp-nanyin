/*
 * 文件名：TrdFundInvestTradeDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting 修改时间：2018年10月19日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.fund.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.service.trade.bond.dto.TrdInvestPersonInfoDto;
import com.joyintech.tams.common.base.entity.*;

/**
 * 基金投资交易微服务端dto 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年10月19日
 * @see TrdFundInvestTradeDto
 * @since 1.0
 */
public class TrdFundInvestTradeDto extends TrdProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6281515607874581450L;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 组合T日可用现金
     */
    private BigDecimal proAvailableCash;

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
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;

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

    private String fTradeId;

    /**
     * 金融产品交易扩展
     */
    private List<PaTrdProductDeal> paTrdProductDeals;

    /**
     * 划款指令
     */
    private List<StlPayOrders> bussTrdTransferOrder;

    /**
     * 人员信息
     */
    private TrdInvestPersonInfoDto trdInvestPersonInfo;

    /**
     * 金融产品交易附属
     */
    private List<TrdProductDealAdd> trdProductDealAdds;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 通道费用明细，估值接口需要
     */
    private TrdTradeFeeDetail trdTradeFeeDetail;

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
     * 资产四级类型
     */
    private String type11;

    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;

    /**
     * 委托数量
     */
    private BigDecimal entrustNum;

    /**
     * 完成委托额度
     */
    private BigDecimal finishAmt;

    /**
     * 委托金额
     */
    private BigDecimal prinAmt;

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
     * 通道收取方式
     */
    private String collectType;

    /**
     * 转入基金收益类型
     */
    private String inFinProfitType;

    /**
     * 转出基金收益类型
     */
    private String outFinProfitType;

    /**
     * 转入基金申购确认交易数据
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 转入基金申购费
     */
    private BigDecimal feeAmtPayPurchase;

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
     * 金融产品交易汇总
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 汇总附属表
     */
    private TrdDealTotalAdd dealTotalAdd;

    /**
     * 转换申请交易份额
     */
    private BigDecimal shareAmtHid;

    /**
     * 转换申购确认交易份额
     */
    private BigDecimal changeInShareAmt;

    /**
     * 转入基金单位净值
     */
    private BigDecimal unitCpriceIn;

    /**
     * 费用明细List
     */
    private List<TrdTradeFeeDetail> trdTradeFeeDetailList;

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
     * 获取 collectType
     * 
     * @return collectType.
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置 collectType
     * 
     * @param collectType collectType
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
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
    @Override
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 secManageAcctId
     * 
     * @param secManageAcctId secManageAcctId
     */
    @Override
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
    // 资产推荐方 新增 zhuyongkang 20191023 start
    private String assetRecommandOrg;

    public String getAssetRecommandOrg() {
        return assetRecommandOrg;
    }

    public void setAssetRecommandOrg(String assetRecommandOrg) {
        this.assetRecommandOrg = assetRecommandOrg;
    }

    // 资产推荐方 新增 zhuyongkang 20191023 start
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    @Override
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    @Override
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

    /** 新增资产二、三、四级分类 王贵奇 2019/7/30 结束 */

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
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
    @Override
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    @Override
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
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
     * 获取 bussTrdTransferOrder
     * 
     * @return bussTrdTransferOrder.
     */
    public List<StlPayOrders> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * 
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<StlPayOrders> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 trdInvestPersonInfo
     * 
     * @return trdInvestPersonInfo.
     */
    public TrdInvestPersonInfoDto getTrdInvestPersonInfo() {
        return trdInvestPersonInfo;
    }

    /**
     * 设置 trdInvestPersonInfo
     * 
     * @param trdInvestPersonInfo trdInvestPersonInfo
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfoDto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

    // /**
    // * 通道
    // */
    // private String channelId;

    /**
     * 通道费
     */
    private BigDecimal channelAmt;

    /**
     * 交易金额
     */
    private BigDecimal dealAmt;

    // @Override
    // public String getChannelId() {
    // return channelId;
    // }
    //
    // @Override
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
     * 获取 trdTradeFeeDetail
     *
     * @return trdTradeFeeDetail
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

    public Date getPurCfmDate() {
        return purCfmDate;
    }

    public void setPurCfmDate(Date purCfmDate) {
        this.purCfmDate = purCfmDate;
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

    /**
     * 获取 inFinProfitType
     *
     * @return inFinProfitType
     */
    public String getInFinProfitType() {
        return inFinProfitType;
    }

    /**
     * 设置 inFinProfitType
     *
     * @param inFinProfitType inFinProfitType
     */
    public void setInFinProfitType(String inFinProfitType) {
        this.inFinProfitType = inFinProfitType;
    }

    /**
     * 获取 outFinProfitType
     *
     * @return outFinProfitType
     */
    public String getOutFinProfitType() {
        return outFinProfitType;
    }

    /**
     * 设置 outFinProfitType
     *
     * @param outFinProfitType outFinProfitType
     */
    public void setOutFinProfitType(String outFinProfitType) {
        this.outFinProfitType = outFinProfitType;
    }

    /**
     * 获取 trdProductDeal
     *
     * @return trdProductDeal
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
     * 获取 feeAmtPayPurchase
     *
     * @return feeAmtPayPurchase
     */
    public BigDecimal getFeeAmtPayPurchase() {
        return feeAmtPayPurchase;
    }

    /**
     * 设置 feeAmtPayPurchase
     *
     * @param feeAmtPayPurchase feeAmtPayPurchase
     */
    public void setFeeAmtPayPurchase(BigDecimal feeAmtPayPurchase) {
        this.feeAmtPayPurchase = feeAmtPayPurchase;
    }

    /**
     * 获取 shareAmtHid
     *
     * @return shareAmtHid
     */
    public BigDecimal getShareAmtHid() {
        return shareAmtHid;
    }

    /**
     * 设置 shareAmtHid
     *
     * @param shareAmtHid shareAmtHid
     */
    public void setShareAmtHid(BigDecimal shareAmtHid) {
        this.shareAmtHid = shareAmtHid;
    }

    /**
     * 获取 unitCpriceIn
     *
     * @return unitCpriceIn
     */
    public BigDecimal getUnitCpriceIn() {
        return unitCpriceIn;
    }

    /**
     * 设置 unitCpriceIn
     *
     * @param unitCpriceIn unitCpriceIn
     */
    public void setUnitCpriceIn(BigDecimal unitCpriceIn) {
        this.unitCpriceIn = unitCpriceIn;
    }

    /**
     * 获取 paTrdProductDeals
     *
     * @return paTrdProductDeals
     */
    public List<PaTrdProductDeal> getPaTrdProductDeals() {
        return paTrdProductDeals;
    }

    /**
     * 设置 paTrdProductDeals
     *
     * @param paTrdProductDeals paTrdProductDeals
     */
    public void setPaTrdProductDeals(List<PaTrdProductDeal> paTrdProductDeals) {
        this.paTrdProductDeals = paTrdProductDeals;
    }

    /**
     * 获取 trdProductDealAdds
     *
     * @return trdProductDealAdds
     */
    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    /**
     * 设置 trdProductDealAdds
     *
     * @param trdProductDealAdds trdProductDealAdds
     */
    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

    /**
     * 获取 changeInShareAmt
     *
     * @return changeInShareAmt
     */
    public BigDecimal getChangeInShareAmt() {
        return changeInShareAmt;
    }

    /**
     * 设置 changeInShareAmt
     *
     * @param changeInShareAmt changeInShareAmt
     */
    public void setChangeInShareAmt(BigDecimal changeInShareAmt) {
        this.changeInShareAmt = changeInShareAmt;
    }
}
