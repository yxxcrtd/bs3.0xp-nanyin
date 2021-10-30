package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.validator.ExchangeTransMatchValidator;

import java.math.BigDecimal;
import java.util.List;

@ValidLogic(targetClass = ExchangeTransMatchValidator.class)
public class ExchangeTransMatch002Dto extends SettleTransDataTotal implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1231102556224322743L;

    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 匹配进度
     */
    private BigDecimal matchProgress;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 资产市场代码
     */
    private String finprodMarketId;

    /**
     * 剩余成交金额
     */
    private BigDecimal remainAmount;

    /**
     * 剩余成交数量
     */
    private BigDecimal remainNum;

    /**
     * 资金账号名称
     */
    private String acctName;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 委托交易list
     */
    private List<ExchangeTransMatch003Dto> entrustList;


    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.下行交易匹配状态, this.getMatchStatus());
    }

    public BigDecimal getMatchProgress() {
        return matchProgress;
    }

    public void setMatchProgress(BigDecimal matchProgress) {
        this.matchProgress = matchProgress;
    }

    public String getMatchProgressStr() {
        if(StringUtil.isNotEmpty(this.getMatchProgress())) {
            return ArithmeticUtil.multiply(this.getMatchProgress(),new BigDecimal(100))+"%";
        }
        else {
            return "0";
        }
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(BigDecimal remainNum) {
        this.remainNum = remainNum;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public List<ExchangeTransMatch003Dto> getEntrustList() {
        return entrustList;
    }

    public void setEntrustList(List<ExchangeTransMatch003Dto> entrustList) {
        this.entrustList = entrustList;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getTradeMarketStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradeMarket());
    }

    public String getFinprodType2Str() {
        return  SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, this.getFinprodType2());
    }

    public String getTradePlatStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.交易平台, this.getTradePlat());
    }

    public String getPsStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getPs());
    }
}

