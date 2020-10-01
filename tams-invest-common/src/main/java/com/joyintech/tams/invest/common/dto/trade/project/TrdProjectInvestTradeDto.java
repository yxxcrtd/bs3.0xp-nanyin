package com.joyintech.tams.invest.common.dto.trade.project;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;

/**
 * @program: tams-invest-parent
 * @description: 项目类资产投资交易
 * @author: 强成西
 * @create: 2018-12-04 11:52
 **/
public class TrdProjectInvestTradeDto extends BaseEntity {

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 交易数据
     */
    private BaseTradeInfoDto baseTradeInfoDto;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 业务编号
     */
    private String bizCode;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 清算速度
     */
    private String settleSpeed;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 资产类型
     */
    private String projectType;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 交割日期
     */
    private String settleDate;

    /**
     * 本方交易员
     */
    private String trader;

    /**
     * 对手方
     */
    private String counterId;

    /**
     * 对手方交易员
     */
    private String counterTrader;

    /**
     * 对手方交易员联系方式
     */
    private String counterContact;

    /**
     * 是否外部交易
     */
    private String isOutTrade;

    /**
     * 本金金额
     */
    private BigDecimal prinAmt;

    /**
     * 单位净价
     */
    private BigDecimal unitCprice;

    /**
     * 单位应计利息
     */
    private BigDecimal unitIntAmt;

    /**
     * 净价总额
     */
    private BigDecimal cpriceAmt;

    /**
     * 应计利息总额
     */
    private BigDecimal intAmt;

    /**
     * 实付金额
     */
    private BigDecimal tradeAmt;

    /**
     * 手续费率
     */
    private BigDecimal commissionRate;

    /**
     * 单位全价
     */
    private BigDecimal unitFprice;

    /**
     * 全价总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 净投资收益率
     */
    private BigDecimal investRate;

    /**
     * 手续费
     */
    private BigDecimal commissionAmt;

    /**
     * 组合T日可用现金
     */
    private BigDecimal accAvailableCashT;

    /**
     * 组合T+1日可用现金
     */
    private BigDecimal accAvailableCashT1;

    /**
     * 托管户剩余现金
     */
    private BigDecimal accAvailableCash;

    /**
     * 资产可用持仓
     */
    private BigDecimal bottomAssetAmount;

    /**
     * 所选资产的计息基础
     */
    private String basis;
    /**
     * 币种
     */
    private String ccy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 通道费
     */
    private BigDecimal channelFee;
    /**
     * 支付未支付利息
     */
    private BigDecimal payUnpaidintAmt;

    /**
     * 是否转让还本未支付利息
     */
    private String ispayUnpaidint;

    /**
     * 获取 statusValid
     *
     * @return statusValid
     */
    public String getStatusValid() {
        return statusValid;
    }

    /**
     * 设置 statusValid
     *
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 trader
     *
     * @return trader
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 trader
     *
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 counterId
     *
     * @return counterId
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     *
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 counterTrader
     *
     * @return counterTrader
     */
    public String getCounterTrader() {
        return counterTrader;
    }

    /**
     * 设置 counterTrader
     *
     * @param counterTrader counterTrader
     */
    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    /**
     * 获取 counterContact
     *
     * @return counterContact
     */
    public String getCounterContact() {
        return counterContact;
    }

    /**
     * 设置 counterContact
     *
     * @param counterContact counterContact
     */
    public void setCounterContact(String counterContact) {
        this.counterContact = counterContact;
    }

    /**
     * 获取 bizCode
     *
     * @return bizCode
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置 bizCode
     *
     * @param bizCode bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 获取 entrustId
     *
     * @return entrustId
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
     * @return portfolioId
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
     * @return portfolioName
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
     * 获取 projectType
     *
     * @return projectType
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * 设置 projectType
     *
     * @param projectType projectType
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 tradeDate
     *
     * @return tradeDate
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     *
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 unitCprice
     *
     * @return unitCprice
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 unitCprice
     *
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 unitFprice
     *
     * @return unitFprice
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    /**
     * 设置 unitFprice
     *
     * @param unitFprice unitFprice
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
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
     * 获取 baseTradeInfoDto
     *
     * @return baseTradeInfoDto
     */
    public BaseTradeInfoDto getBaseTradeInfoDto() {
        return baseTradeInfoDto;
    }

    /**
     * 设置 baseTradeInfoDto
     *
     * @param baseTradeInfoDto baseTradeInfoDto
     */
    public void setBaseTradeInfoDto(BaseTradeInfoDto baseTradeInfoDto) {
        this.baseTradeInfoDto = baseTradeInfoDto;
    }

    /**
     * 获取 settleSpeed
     *
     * @return settleSpeed
     */
    public String getSettleSpeed() {
        return settleSpeed;
    }

    /**
     * 设置 settleSpeed
     *
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 unitIntAmt
     *
     * @return unitIntAmt
     */
    public BigDecimal getUnitIntAmt() {
        return unitIntAmt;
    }

    /**
     * 设置 unitIntAmt
     *
     * @param unitIntAmt unitIntAmt
     */
    public void setUnitIntAmt(BigDecimal unitIntAmt) {
        this.unitIntAmt = unitIntAmt;
    }

    /**
     * 获取 cpriceAmt
     *
     * @return cpriceAmt
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 cpriceAmt
     *
     * @param cpriceAmt cpriceAmt
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 获取 intAmt
     *
     * @return intAmt
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     *
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 commissionRate
     *
     * @return commissionRate
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置 commissionRate
     *
     * @param commissionRate commissionRate
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 investRate
     *
     * @return investRate
     */
    public BigDecimal getInvestRate() {
        return investRate;
    }

    /**
     * 设置 investRate
     *
     * @param investRate investRate
     */
    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }

    /**
     * 获取 commissionAmt
     *
     * @return commissionAmt
     */
    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }

    /**
     * 设置 commissionAmt
     *
     * @param commissionAmt commissionAmt
     */
    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }

    /**
     * 获取 accAvailableCashT
     *
     * @return accAvailableCashT
     */
    public BigDecimal getAccAvailableCashT() {
        return accAvailableCashT;
    }

    /**
     * 设置 accAvailableCashT
     *
     * @param accAvailableCashT accAvailableCashT
     */
    public void setAccAvailableCashT(BigDecimal accAvailableCashT) {
        this.accAvailableCashT = accAvailableCashT;
    }

    /**
     * 获取 accAvailableCashT1
     *
     * @return accAvailableCashT1
     */
    public BigDecimal getAccAvailableCashT1() {
        return accAvailableCashT1;
    }

    /**
     * 设置 accAvailableCashT1
     *
     * @param accAvailableCashT1 accAvailableCashT1
     */
    public void setAccAvailableCashT1(BigDecimal accAvailableCashT1) {
        this.accAvailableCashT1 = accAvailableCashT1;
    }

    /**
     * 获取 accAvailableCash
     *
     * @return accAvailableCash
     */
    public BigDecimal getAccAvailableCash() {
        return accAvailableCash;
    }

    /**
     * 设置 accAvailableCash
     *
     * @param accAvailableCash accAvailableCash
     */
    public void setAccAvailableCash(BigDecimal accAvailableCash) {
        this.accAvailableCash = accAvailableCash;
    }

    /**
     * 获取 bottomAssetAmount
     *
     * @return bottomAssetAmount
     */
    public BigDecimal getBottomAssetAmount() {
        return bottomAssetAmount;
    }

    /**
     * 设置 bottomAssetAmount
     *
     * @param bottomAssetAmount bottomAssetAmount
     */
    public void setBottomAssetAmount(BigDecimal bottomAssetAmount) {
        this.bottomAssetAmount = bottomAssetAmount;
    }

    /**
     * 获取 fpriceAmt
     *
     * @return fpriceAmt
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     *
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 basis
     *
     * @return basis
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 isOutTrade
     *
     * @return isOutTrade
     */
    public String getIsOutTrade() {
        return isOutTrade;
    }

    /**
     * 设置 isOutTrade
     *
     * @param isOutTrade isOutTrade
     */
    public void setIsOutTrade(String isOutTrade) {
        this.isOutTrade = isOutTrade;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
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
    // 项目类资产五级分类改造 add by renping 2019-08-01 start
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
     * 获取 type9
     *
     * @return type9.
     */
    public String getType9() {
        return type9;
    }

    /**
     * 设置 type9
     *
     * @param type9
     *            type9
     */
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

    /**
     * 设置 type10
     *
     * @param type10
     *            type10
     */
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

    /**
     * 设置 type11
     *
     * @param type11
     *            type11
     */
    public void setType11(String type11) {
        this.type11 = type11;
    }

    /**
     * 获取 type9Name
     *
     * @return type9Name.
     */
    public String getType9Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getType9());
    }

    /**
     * 获取 type10Name
     *
     * @return type10Name.
     */
    public String getType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getType10());
    }

    /**
     * 获取 type11Name
     *
     * @return type11Name.
     */
    public String getType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getType11());
    }
    // 项目类资产五级分类改造 add by renping 2019-08-01 end

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
}
