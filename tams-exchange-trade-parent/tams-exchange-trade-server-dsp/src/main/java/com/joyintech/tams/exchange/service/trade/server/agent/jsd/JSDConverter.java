package com.joyintech.tams.exchange.service.trade.server.agent.jsd;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.AccountFinance;
import com.joyintech.tams.exchange.common.bean.HistoryQryBean;
import com.joyintech.tams.exchange.common.bean.HistoryTrade;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkCloseQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedQuote;
import com.joyintech.tams.exchange.common.bean.QuoteQryBean;
import com.joyintech.tams.exchange.common.bean.StandardBonds;
import com.joyintech.tams.exchange.common.constant.BSFlag;
import com.joyintech.tams.exchange.common.constant.BusinessFlag;
import com.joyintech.tams.exchange.common.constant.CbptransType;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustChildBusiness;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.EntrustStatus;
import com.joyintech.tams.exchange.common.constant.EntrustType;
import com.joyintech.tams.exchange.common.constant.JSDBsFlagName;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.constant.MarketPriceType;
import com.joyintech.tams.exchange.common.constant.TradeType;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.utils.CbptransTypeUtils;
import com.joyintech.tams.exchange.common.utils.EntrustUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryAfterIntegratedQuotes;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryEntrustData;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryHistoryTrade;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryIntegratedOrder;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryIntegratedQuotes;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryPositionData;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryProtocolBackTrade;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryStandardBondData;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data.RespQryTradeData;
import com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header.RespQryCustomerFinanceHeader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * create By MAXX on 2020/6/28
 */
public class JSDConverter {
    private final static Logger logger = LoggerFactory.getLogger(JSDConverter.class);

    public static Entrust getEntrust(Account account, RespQryEntrustData bean) {
        Entrust entrust = new Entrust();
        entrust.setReportNo(bean.declareNum);
        entrust.setPositionStr(bean.contractId + "");
        entrust.setAccountId(account.getId());
        entrust.setTradingDay(bean.date);
        entrust.setBrokerEntrustId(bean.contractId + "");
        entrust.setReportTime(bean.tradeTime);
        entrust.setFinanceAccount(account.getFinanceAccount());
        if ('1' == bean.market || '3' == bean.market) {
            entrust.setMarket(Market.SHSE.value);
        } else if ('2' == bean.market || '4' == bean.market) {
            entrust.setMarket(Market.SZSE.value);
        }
        entrust.setStockCode(bean.entrustCode);
        entrust.setStockName(bean.entrustName);
        entrust.setEntrustBs(bean.sellTypeFlag + "");
        //委托业务类型
        String businessType = bean.businessType;
        //委托子业务
        String entrustChildBusiness = bean.entrustChildBusiness;
        String entrustProp = getEntrustProp(businessType, entrustChildBusiness);
        if (StringUtils.isNotBlank(entrustProp)) {
            entrust.setEntrustProp(entrustProp);
        }
        entrust.setEntrustType(EntrustType.ENTRUST.value);
        entrust.setEntrustPrice(BigDecimal.valueOf(bean.entrustPrice));
        if (EntrustUtils.isSHZQ(entrust.getMarket(), null, entrust.getStockCode())) {
            entrust.setEntrustVolume(new Double(bean.entrustVolume * 10).longValue());
            entrust.setTradedVolume(new Double(bean.tradedVolume * 10).longValue());
            entrust.setCancelVolume(new Double(bean.cancelNum * 10).longValue());
        } else {
            entrust.setEntrustVolume(new Double(bean.entrustVolume).longValue());
            entrust.setCancelVolume(new Double(bean.cancelNum).longValue());
            entrust.setTradedVolume(new Double(bean.tradedVolume).longValue());
        }
        entrust.setTradedPrice(BigDecimal.valueOf(bean.tradedPrice));
        entrust.setReportTime(bean.tradeTime);
        int entrustStatus = getEntrustStatus(bean.entrustStatus);
        if (entrustStatus != -111) {
            entrust.setEntrustStatus(entrustStatus);
        }
        entrust.setErrorMsg(bean.errorMsg);
        return entrust;
    }

    public static Trade getTrade(Account account, RespQryTradeData bean) {
        Trade trade = new Trade();
        trade.setReportNo(bean.reportId);
        trade.setTradingDay(bean.date);
        trade.setAccountId(account.getId());
        if ('1' == bean.market || '3' == bean.market) {
            trade.setMarket(Market.SHSE.value);
        } else if ('2' == bean.market || '4' == bean.market) {
            trade.setMarket(Market.SZSE.value);
        }
        trade.setFinanceAccount(account.getFinanceAccount());
        trade.setPositionStr(bean.positionStr);
        trade.setStockCode(bean.entrustCode);
        trade.setStockName(bean.entrustName);
        trade.setEntrustBs(bean.direction + "");
        trade.setTradedPrice(BigDecimal.valueOf(bean.tradedPrice));
        boolean isSHZQ = false;
        if (EntrustUtils.isSHZQ(trade.getMarket(), null, trade.getStockCode())) {
            isSHZQ = true;
        }
        if (bean.tradedVolume < 0) {
            if (isSHZQ) {
                trade.setTradedVolume(new Double(-bean.tradedVolume * 10).longValue());
            } else {
                trade.setTradedVolume(new Double(-bean.tradedVolume).longValue());
            }
        } else {
            if (isSHZQ) {
                trade.setTradedVolume(new Double(bean.tradedVolume * 10).longValue());
            } else {
                trade.setTradedVolume(new Double(bean.tradedVolume).longValue());
            }
        }
        trade.setTradeTime(bean.tradeTime);
        if ('0' == bean.tradeKind) {
            trade.setTradeType(TradeType.NORMAL.value);
        } else if ('2' == bean.tradeKind) {
            trade.setTradeType(TradeType.CANCEL.value);
        }
        trade.setBrokerEntrustId(bean.entrustId + "");
        trade.setTradedBalance(BigDecimal.valueOf(bean.tradedAmount));
        if (bean.tradeKind == '2' && bean.tradeType == '0' && "-1".equals(bean.tradedId)) {
            trade.setBrokerTradeId(bean.entrustId + "");
        } else {
            trade.setBrokerTradeId(bean.tradedId);
        }
        trade.setBusinessFlag(BusinessFlag.NORMAL.value);
        return trade;
    }

    public static void getEntrustList(Account account, List<RespQryEntrustData> list, List<Entrust> entrustList) {
        if (Objects.nonNull(list)) {
            list.forEach(bean -> {
                entrustList.add(getEntrust(account, bean));
            });
        }
    }

    public static void getTradeList(Account account, List<RespQryTradeData> list, List<Trade> tradeList) {
        if (Objects.nonNull(list)) {
            list.forEach(bean -> {
                tradeList.add(getTrade(account, bean));
            });
        }
    }

    public static int getEntrustStatus(char entrustStatus) {
        int entrust_status = -111;
        switch (entrustStatus) {
            case ' ':
            case '\0':
                entrust_status = EntrustStatus.TOBE_REPORTED.value;
                return entrust_status;
            case '6':
            case 'A':
                entrust_status = EntrustStatus.CANCELED.value;
                return entrust_status;
            case '7':
                entrust_status = EntrustStatus.PART_SUCCESS.value;
                return entrust_status;
            case '5':
                entrust_status = EntrustStatus.PART_TRADED_CANCELED.value;
                return entrust_status;
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
                return entrust_status;
            default:
                entrust_status = (int) entrustStatus - (int) '0';
                return entrust_status;
        }
    }

    public static String getEntrustProp(String businessType, String entrustChildBusiness) {
        String entrust_prop = "";
        if (EntrustChildBusiness.NORMAL.value.equals(entrustChildBusiness) &&
                ("01".equals(businessType) || "02".equals(businessType))) {
            entrust_prop = EntrustProp.NORMAL.value;
            return entrust_prop;
        }
        switch (entrustChildBusiness) {
            case "002":
                entrust_prop = EntrustProp.COUNTERPARTY.value;
                return entrust_prop;
            case "003":
                entrust_prop = EntrustProp.OWN_FIRST.value;
                return entrust_prop;
            case "004":
                entrust_prop = EntrustProp.IMMEDIATE_TO_CANCEL.value;
                return entrust_prop;
            case "005":
                entrust_prop = EntrustProp.BEST5_TO_CANCEL.value;
                return entrust_prop;
            case "006":
                entrust_prop = EntrustProp.FILL.value;
                return entrust_prop;
            case "007":
                entrust_prop = EntrustProp.BEST5_TO_LIMIT.value;
                return entrust_prop;
            case "z10":
                entrust_prop = EntrustProp.SET_PRICE.value;
                return entrust_prop;
            case "z20":
                entrust_prop = EntrustProp.INTENTION.value;
                return entrust_prop;
            case "z30":
                entrust_prop = EntrustProp.MUTUAL_TRADE.value;
                return entrust_prop;
            case "z40":
                entrust_prop = EntrustProp.CONFIRM.value;
                return entrust_prop;
            case "z70":
                entrust_prop = EntrustProp.LARGE_AFTER_CLOSING_PRICE.value;
                return entrust_prop;
            case "z80":
                entrust_prop = EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value;
                return entrust_prop;
            case "X31":
                entrust_prop = EntrustProp.SH_REPURCHASE_TRADE.value;
                return entrust_prop;
            case "X32":
                entrust_prop = EntrustProp.SH_REPURCHASE_RENEWAL.value;
                return entrust_prop;
            case "X33":
                entrust_prop = EntrustProp.SH_REPURCHASE_TERMINATE.value;
                return entrust_prop;
            case "X40":
                entrust_prop = EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value;
                return entrust_prop;
            case "X41":
                entrust_prop = EntrustProp.SH_REPURCHASE_RENEWAL_CONFIRM.value;
                return entrust_prop;
            case "X42":
                entrust_prop = EntrustProp.SH_REPURCHASE_TERMINATE_CONFIRM.value;
                return entrust_prop;
            case "g10":
                entrust_prop = EntrustProp.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value;
                return entrust_prop;
        }
        if ("".equals(entrust_prop)) {
            switch (businessType) {
                case "04":
                    entrust_prop = EntrustProp.ALLOTMENT.value;
                    return entrust_prop;
                case "0k":
                case "0l":
                    entrust_prop = EntrustProp.ZYCRK.value;
                    return entrust_prop;
                case "05":
                    entrust_prop = EntrustProp.CHANGE_TO_STOCK.value;
                    return entrust_prop;
                case "06":
                    entrust_prop = EntrustProp.HS.value;
                    return entrust_prop;
                case "0M":
                case "0N":
                    entrust_prop = EntrustProp.ETF_APPLY.value;
                    return entrust_prop;
                case "0P":
                case "0Q":
                    entrust_prop = EntrustProp.JJSHSH.value;
                    return entrust_prop;
                case "0D":
                    entrust_prop = EntrustProp.APPLY.value;
                    return entrust_prop;
            }
        }
        return entrust_prop;
    }

    /***
     * 获取买卖类型
     */
    public static String getDirection(String entrustProp, String entrustBs, String market) {
        String direction = "";
        if (EntrustProp.HS.value.equals(entrustProp)) {
            direction = "6";
            return direction;
        }
        if (EntrustProp.CHANGE_TO_STOCK.value.equals(entrustProp)) {
            direction = "5";
            return direction;
        }
        if (EntrustProp.ALLOTMENT.value.equals(entrustProp)) {
            if (Market.SZSE.value.equals(market)) {
                direction = "1";
                return direction;
            }
            if (Market.SHSE.value.equals(market)) {
                direction = "2";
                return direction;
            }
        }
        if (EntrustProp.COUNTERPARTY.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "12";
            return direction;
        }
        if (EntrustProp.COUNTERPARTY.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "22";
            return direction;
        }
        if (EntrustProp.BEST5_TO_LIMIT.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "17";
            return direction;
        }
        if (EntrustProp.BEST5_TO_LIMIT.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "27";
            return direction;
        }
        if (EntrustProp.OWN_FIRST.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "13";
            return direction;
        }
        if (EntrustProp.OWN_FIRST.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "23";
            return direction;
        }
        if (EntrustProp.IMMEDIATE_TO_CANCEL.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "14";
            return direction;
        }
        if (EntrustProp.IMMEDIATE_TO_CANCEL.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "24";
            return direction;
        }
        if (EntrustProp.BEST5_TO_CANCEL.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "15";
            return direction;
        }
        if (EntrustProp.BEST5_TO_CANCEL.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "25";
            return direction;
        }
        if (EntrustProp.FILL.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "16";
            return direction;
        }
        if (EntrustProp.FILL.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "26";
            return direction;
        }
        if (EntrustProp.ZYCRK.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "C";
            return direction;
        }
        if (EntrustProp.ZYCRK.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "R";
            return direction;
        }
        if (EntrustProp.JJSHSH.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "0";
            return direction;
        }
        if (EntrustProp.JJSHSH.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "9";
            return direction;
        }
        if (EntrustBs.BUY.value.equals(entrustBs)) {
            direction = "1";
            if (EntrustProp.NORMAL.value.equals(entrustProp)) {
                direction += MarketPriceType.LIMITED_PRICE.value;
            } else if (EntrustProp.COUNTERPARTY.value.equals(entrustProp)) {
                direction += MarketPriceType.SDSFZYJGWT.value;
            } else if (EntrustProp.OWN_FIRST.value.equals(entrustProp)) {
                direction += MarketPriceType.SBFZYJGWT.value;
            } else if (EntrustProp.IMMEDIATE_TO_CANCEL.value.equals(entrustProp)) {
                direction += MarketPriceType.SJSCJSYCXWT.value;
            } else if (EntrustProp.BEST5_TO_CANCEL.value.equals(entrustProp)) {
                direction += MarketPriceType.HSZYWDBCXWT.value;
            } else if (EntrustProp.FILL.value.equals(entrustProp)) {
                direction += MarketPriceType.SQECJHCXWT.value;
            }
            return direction;
        }
        if (EntrustBs.SELL.value.equals(entrustBs)) {
            direction = "2";
            if (EntrustProp.NORMAL.value.equals(entrustProp)) {
                direction += MarketPriceType.LIMITED_PRICE.value;
            } else if (EntrustProp.COUNTERPARTY.value.equals(entrustProp)) {
                direction += MarketPriceType.SDSFZYJGWT.value;
            } else if (EntrustProp.OWN_FIRST.value.equals(entrustProp)) {
                direction += MarketPriceType.SBFZYJGWT.value;
            } else if (EntrustProp.IMMEDIATE_TO_CANCEL.value.equals(entrustProp)) {
                direction += MarketPriceType.SJSCJSYCXWT.value;
            } else if (EntrustProp.BEST5_TO_CANCEL.value.equals(entrustProp)) {
                direction += MarketPriceType.HSZYWDBCXWT.value;
            } else if (EntrustProp.FILL.value.equals(entrustProp)) {
                direction += MarketPriceType.SQECJHCXWT.value;
            }
            return direction;
        }
        return direction;
    }

    /*** 
     * 获取委托子业务
     */
    public static String getEntrustSubBusiness(String entrustProp) {
        String entrustSubBusiness = "";
        if (EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SET_PRICE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.INTENTION.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.INTENTION.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.MUTUAL_TRADE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.MUTUAL_TRADE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.CONFIRM.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.CONFIRM.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.LARGE_AFTER_CLOSING_PRICE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.LARGE_AFTER_CLOSING_PRICE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_TRADE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_TRADE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_RENEWAL.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_RENEWAL.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_TERMINATE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_TERMINATE.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_TRADE_CONFIRM.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_RENEWAL_CONFIRM.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_RENEWAL_CONFIRM.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_REPURCHASE_TERMINATE_CONFIRM.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_REPURCHASE_TERMINATE_CONFIRM.value;
            return entrustSubBusiness;
        }
        if (EntrustProp.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value.equals(entrustProp)) {
            entrustSubBusiness = EntrustChildBusiness.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value;
            return entrustSubBusiness;
        }
        return entrustSubBusiness;
    }

    /*** 
     * 获取大宗行情返回列表
     */
    public static IntegratedQuote getIntegratedQuote(QuoteQryBean quoteQryBean, RespQryIntegratedQuotes integratedQuote) {
        IntegratedQuote integratedQuoteBt = new IntegratedQuote();
        integratedQuoteBt.setContractId(integratedQuote.contractNo);
        String instruction = integratedQuote.instruction;
        if ('1' == integratedQuote.market || '3' == integratedQuote.market) {
            integratedQuoteBt.setMarket(Market.SHSE.value);
        } else if ('2' == integratedQuote.market || '4' == integratedQuote.market) {
            integratedQuoteBt.setMarket(Market.SZSE.value);
        }
        if (!Strings.isNullOrEmpty(instruction)) {
            if (CbptransType.YXMR.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.YXMR.value);
            } else if (CbptransType.YXMC.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.YXMC.value);
            } else if (CbptransType.DJMR.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.DJMR.value);
            } else if (CbptransType.DJMC.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.DJMC.value);
            }
            integratedQuoteBt.setEntrustProp(EntrustUtils.getEntrustProp(integratedQuoteBt.getBsFlag(), integratedQuoteBt.getMarket()));
            integratedQuoteBt.setEntrustBs(EntrustUtils.getEntrustBs(integratedQuoteBt.getBsFlag()));
        }
        integratedQuoteBt.setStockCode(integratedQuote.stockCode);
        integratedQuoteBt.setCompactTerm(integratedQuote.backTerm);
        integratedQuoteBt.setLiaisonTel(integratedQuote.relationTel);
        integratedQuoteBt.setReportTimeX(integratedQuote.declarationTime);
        integratedQuoteBt.setReportTime(integratedQuote.declarationTime);
        integratedQuoteBt.setRemark(integratedQuote.remark);
        integratedQuoteBt.setAccountId(quoteQryBean.getAccountId());
        integratedQuoteBt.setOrderNo(integratedQuote.recordNum + "");
        integratedQuoteBt.setEntrustPrice(integratedQuote.declarationPrice);
        if (EntrustUtils.isSHZQ(integratedQuoteBt.getMarket(), null, integratedQuoteBt.getStockCode())) {
            integratedQuoteBt.setEntrustVolume(new Double(integratedQuote.declarationQuantity * 10).intValue());
        } else {
            integratedQuoteBt.setEntrustVolume(new Double(integratedQuote.declarationQuantity).intValue());
        }
        integratedQuoteBt.setLiaisonName(integratedQuote.relationName);

        return integratedQuoteBt;
    }

    /*** 
     * 获取盘后大宗行情返回列表
     */
    public static IntegratedQuote getAfterIntegratedQuote(QuoteQryBean quoteQryBean, RespQryAfterIntegratedQuotes integratedQuote) {
        IntegratedQuote integratedQuoteBt = new IntegratedQuote();
        String instruction = integratedQuote.instruction.trim();
        if ('1' == integratedQuote.market || '3' == integratedQuote.market) {
            integratedQuoteBt.setMarket(Market.SHSE.value);
        } else if ('2' == integratedQuote.market || '4' == integratedQuote.market) {
            integratedQuoteBt.setMarket(Market.SZSE.value);
        }
        if (!Strings.isNullOrEmpty(instruction)) {
            if (CbptransType.YXMR.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.YXMR.value);
            } else if (CbptransType.YXMC.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.YXMC.value);
            } else if (CbptransType.DJMR.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.DJMR.value);
            } else if (CbptransType.DJMC.value.equals(instruction)) {
                integratedQuoteBt.setBsFlag(BSFlag.DJMC.value);
            }
            integratedQuoteBt.setEntrustProp(EntrustUtils.getEntrustProp(integratedQuoteBt.getBsFlag(), integratedQuoteBt.getMarket()));
            integratedQuoteBt.setEntrustBs(EntrustUtils.getEntrustBs(integratedQuoteBt.getBsFlag()));
        }
        integratedQuoteBt.setStockCode(integratedQuote.stockCode);
        integratedQuoteBt.setStockName(integratedQuote.stockName);
        integratedQuoteBt.setRemark(integratedQuote.remark);
        integratedQuoteBt.setAccountId(quoteQryBean.getAccountId());
        integratedQuoteBt.setOrderNo(integratedQuote.recordNum + "");
        integratedQuoteBt.setEntrustPrice(integratedQuote.price1);
        if (EntrustUtils.isSHZQ(integratedQuoteBt.getMarket(), null, integratedQuoteBt.getStockCode())) {
            integratedQuoteBt.setEntrustVolume(new Double(integratedQuote.dealVolume1 * 10).intValue());
            integratedQuoteBt.setBuyRemainQuantity1(integratedQuote.buyRemainQuantity1 * 10);
            integratedQuoteBt.setSellRemainQuantity1(integratedQuote.sellRemainQuantity1 * 10);
            integratedQuoteBt.setPledgeStampVolume(new Double(integratedQuote.dealVolume2 * 10).intValue());
            integratedQuoteBt.setBuyRemainQuantity2(integratedQuote.buyRemainQuantity2 * 10);
            integratedQuoteBt.setSellRemainQuantity2(integratedQuote.sellRemainQuantity2 * 10);
        } else {
            integratedQuoteBt.setEntrustVolume(new Double(integratedQuote.dealVolume1).intValue());
            integratedQuoteBt.setBuyRemainQuantity1(integratedQuote.buyRemainQuantity1);
            integratedQuoteBt.setSellRemainQuantity1(integratedQuote.sellRemainQuantity1);
            integratedQuoteBt.setPledgeStampVolume(new Double(integratedQuote.dealVolume2).intValue());
            integratedQuoteBt.setBuyRemainQuantity2(integratedQuote.buyRemainQuantity2);
            integratedQuoteBt.setSellRemainQuantity2(integratedQuote.sellRemainQuantity2);
        }
        integratedQuoteBt.setTradeBalance(integratedQuote.dealMoney1);
        integratedQuoteBt.setDealCount1(integratedQuote.dealCount1);
        integratedQuoteBt.setFullPrice(integratedQuote.price2);
        integratedQuoteBt.setOrigTradeBalance(integratedQuote.dealMoney2);
        integratedQuoteBt.setDealCount2(integratedQuote.dealCount2);
        integratedQuoteBt.setReportTime(integratedQuote.updateTime);
        return integratedQuoteBt;
    }

    /*** 
     * 成交历史查询
     */
    public static HistoryTrade getHistoryTrade(Account account, HistoryQryBean historyQryBean, RespQryHistoryTrade historyTradeBt) {
        HistoryTrade historyTrade = new HistoryTrade();
        historyTrade.setTradingDay(historyTradeBt.date);
        if ('1' == historyTradeBt.market || '3' == historyTradeBt.market) {
            historyTrade.setMarket(Market.SHSE.value);
        } else if ('2' == historyTradeBt.market || '4' == historyTradeBt.market) {
            historyTrade.setMarket(Market.SZSE.value);
        }
        historyTrade.setStockHolderId(historyTradeBt.gddm);
        historyTrade.setStockCode(historyTradeBt.stockCode);
        historyTrade.setStockName(historyTradeBt.stockName);
        if ('1' == historyTradeBt.entrustBs) {
            historyTrade.setBsFlag(BSFlag.ZQMR.value);
        } else if ('2' == historyTradeBt.entrustBs) {
            historyTrade.setBsFlag(BSFlag.ZQMC.value);
        }
        historyTrade.setTradePrice(historyTradeBt.entrustPrice);
        historyTrade.setTradeTime(historyTradeBt.tradeTime);
        historyTrade.setOrderId(historyTradeBt.contractId);
        historyTrade.setTradedBalance(historyTradeBt.tradeBalance);
        historyTrade.setFinanceAmount(historyTradeBt.changeAmount);
        historyTrade.setProcedureCost(historyTradeBt.commission);
        historyTrade.setPrintingCost(historyTradeBt.printingCost);
        historyTrade.setTransferCost(historyTradeBt.transferCost);
        historyTrade.setPostStr(historyTradeBt.serialNum);
        historyTrade.setBrokerTradeId(historyTradeBt.newSerialNo);
        historyTrade.setOtherCost(historyTradeBt.otherExpenses);
        historyTrade.setTradingDay(historyTradeBt.date);
        historyTrade.setAccountId(historyQryBean.getAccountId());
        historyTrade.setFinanceAccount(account.getFinanceAccount());
        if (EntrustUtils.isSHZQ(historyTrade.getMarket(), null, historyTrade.getStockCode())) {
            historyTrade.setTradeVolume(new Double(historyTradeBt.entrustVolume * 10).longValue());
        } else {
            historyTrade.setTradeVolume(new Double(historyTradeBt.entrustVolume).longValue());
        }

        return historyTrade;
    }

    /***
     * 获取客户资金查询
     */
    public static AccountFinance getCustomerFinance(String accountId, RespQryCustomerFinanceHeader respQryCustomerFinance) {
        AccountFinance accountFinance = new AccountFinance();
        accountFinance.setAccountId(accountId);
        if ('1' == respQryCustomerFinance.getCurrencyCode()) {
            accountFinance.setMoneyType("0");
        } else if ('2' == respQryCustomerFinance.getCurrencyCode()) {
            accountFinance.setMoneyType("1");
        } else if ('3' == respQryCustomerFinance.getCurrencyCode()) {
            accountFinance.setMoneyType("2");
        }
        accountFinance.setFinanceBalance(respQryCustomerFinance.getFinanceBalance());
        accountFinance.setEnableBalance(respQryCustomerFinance.getExpendableFund());
        accountFinance.setTotalFrozenFinance(respQryCustomerFinance.getUnTradeFrozenAmount() + respQryCustomerFinance.getAbnormalFrozenFunds());
        return accountFinance;
    }

    public static IntegratedEntrust getIntegratedEntrust(Account account, RespQryIntegratedOrder respQryIntegratedOrder) {
        IntegratedEntrust entrust = new IntegratedEntrust();
        entrust.setReportNo(respQryIntegratedOrder.offerContractNo);
        entrust.setAccountId(account.getId());
        entrust.setPositionStr(respQryIntegratedOrder.contractId + "");
        entrust.setTradingDay(respQryIntegratedOrder.date);
        entrust.setBrokerEntrustId(respQryIntegratedOrder.contractId + "");
        entrust.setReportTime(respQryIntegratedOrder.entrustTime);
        entrust.setFinanceAccount(account.getFinanceAccount());
        if ('1' == respQryIntegratedOrder.market || '3' == respQryIntegratedOrder.market) {
            entrust.setMarket(Market.SHSE.value);
        } else if ('2' == respQryIntegratedOrder.market || '4' == respQryIntegratedOrder.market) {
            entrust.setMarket(Market.SZSE.value);
        }
        entrust.setStockCode(respQryIntegratedOrder.stockCode);
        entrust.setStockName(respQryIntegratedOrder.stockName);
        entrust.setEntrustBs(respQryIntegratedOrder.sellTypeFlag + "");
        entrust.setEntrustType(EntrustType.LARGE.value);
        //委托子业务
        String entrustChildBusiness = respQryIntegratedOrder.entrustChildBusiness;
        String entrust_prop = getEntrustProp("", entrustChildBusiness);
        if (!"".equals(entrust_prop)) {
            entrust.setEntrustProp(entrust_prop);
        }
        entrust.setEntrustPrice(BigDecimal.valueOf(respQryIntegratedOrder.entrustPrice));
        if (EntrustUtils.isSHZQ(entrust.getMarket(), null, entrust.getStockCode())) {
            entrust.setEntrustVolume(new Double(respQryIntegratedOrder.entrustVolume * 10).longValue());
            entrust.setTradedVolume(new Double(respQryIntegratedOrder.tradeVolume * 10).longValue());
            entrust.setCancelVolume(new Double(respQryIntegratedOrder.cancelNum * 10).longValue());
        } else {
            entrust.setEntrustVolume(new Double(respQryIntegratedOrder.entrustVolume).longValue());
            entrust.setTradedVolume(new Double(respQryIntegratedOrder.tradeVolume).longValue());
            entrust.setCancelVolume(new Double(respQryIntegratedOrder.cancelNum).longValue());
        }
        entrust.setTradedPrice(BigDecimal.valueOf(respQryIntegratedOrder.dealPrice));
        char entrustStatus = respQryIntegratedOrder.entrustStatus;
        int entrust_status = getEntrustStatus(entrustStatus);
        if (entrust_status != -111) {
            entrust.setEntrustStatus(entrust_status);
        }
        entrust.setErrorMsg(respQryIntegratedOrder.errorMsg);
        return entrust;
    }

    public static Trade getProtocolBackTrade(Account account, RespQryProtocolBackTrade protocolBackTrade) {
        Trade trade = new Trade();
        trade.setAccountId(account.getId());
        trade.setPositionStr(protocolBackTrade.dealNo);
        trade.setTradingDay(protocolBackTrade.date);
        trade.setBrokerEntrustId(protocolBackTrade.contractId);
        trade.setFinanceAccount(account.getFinanceAccount());
        if ('1' == protocolBackTrade.market || '3' == protocolBackTrade.market) {
            trade.setMarket(Market.SHSE.value);
        } else if ('2' == protocolBackTrade.market || '4' == protocolBackTrade.market) {
            trade.setMarket(Market.SZSE.value);
        }
        trade.setStockCode(protocolBackTrade.stockCode);
        trade.setStockName(protocolBackTrade.stockName);
        if ("买".equals(protocolBackTrade.bsFlagName)) {
            trade.setEntrustBs(EntrustBs.BUY.value);
        } else if ("卖".equals(protocolBackTrade.bsFlagName)) {
            trade.setEntrustBs(EntrustBs.SELL.value);
        }
        if (EntrustUtils.isSHZQ(trade.getMarket(), null, trade.getStockCode())) {
            trade.setTradedVolume(protocolBackTrade.tradedVolume * 10);
        } else {
            trade.setTradedVolume(protocolBackTrade.tradedVolume);
        }
        trade.setTradedPrice(BigDecimal.valueOf(protocolBackTrade.tradedPrice));
        trade.setTradedBalance(BigDecimal.valueOf(protocolBackTrade.tradedAmount));
        trade.setBrokerTradeId(protocolBackTrade.dealNo);
        trade.setTradeTime(protocolBackTrade.tradeTime);
        trade.setBusinessFlag(BusinessFlag.BULK.value);
        logger.info("protocolBackTrade.bsFlagName:" + protocolBackTrade.bsFlagName);
        if (JSDBsFlagName.CANCEL.value.equals(protocolBackTrade.bsFlagName)) {
            trade.setTradeType(TradeType.CANCEL.value);
        } else {
            trade.setTradeType(TradeType.NORMAL.value);
        }
        return trade;
    }

    /**
     * 大宗交易行情查询返回数据解析
     */
    public static IntegratedBulkQuoteBean getIntegratedBulkQuote(RespQryIntegratedQuotes integratedQuote) {
        IntegratedBulkQuoteBean integratedBulkQuoteBean = new IntegratedBulkQuoteBean();
        integratedBulkQuoteBean.setOrderNo(integratedQuote.tradeId);
        String cbptransType = "";
        switch (integratedQuote.instruction) {
            case "N1":
                cbptransType = CbptransType.YXMR.value;
                break;
            case "N2":
                cbptransType = CbptransType.YXMC.value;
                break;
            default:
                cbptransType = integratedQuote.instruction;
        }
        integratedBulkQuoteBean.setEntrustProp(CbptransTypeUtils.getEntrustPropByCbptransType(cbptransType));
        integratedBulkQuoteBean.setEntrustBs(CbptransTypeUtils.getEntrustBsByCbptransType(cbptransType));
        String stockCode = integratedQuote.stockCode;
        integratedBulkQuoteBean.setEntrustPrice(integratedQuote.declarationPrice);
        if ('1' == integratedQuote.market || '3' == integratedQuote.market) {
            stockCode = stockCode + "." + Market.SHSE.value;
        } else if ('2' == integratedQuote.market || '4' == integratedQuote.market) {
            stockCode = stockCode + "." + Market.SZSE.value;
        }
        integratedBulkQuoteBean.setStockCode(stockCode);
        if (EntrustUtils.isSHZQ(StockCodeUtils.getMarket(integratedBulkQuoteBean.getStockCode()), null, integratedBulkQuoteBean.getStockCode())) {
            integratedBulkQuoteBean.setEntrustAmount(new Double(integratedQuote.declarationQuantity * 10).intValue());
        } else {
            integratedBulkQuoteBean.setEntrustAmount(new Double(integratedQuote.declarationQuantity).intValue());
        }
        integratedBulkQuoteBean.setCompactTerm(integratedQuote.backTerm);
        integratedBulkQuoteBean.setRelationName(integratedQuote.relationName);
        integratedBulkQuoteBean.setRelationTel(integratedQuote.relationTel);
        integratedBulkQuoteBean.setCbpconferId(integratedQuote.contractNo);
        integratedBulkQuoteBean.setReportTime(integratedQuote.declarationTime);
        integratedBulkQuoteBean.setPositionStr(integratedQuote.tradeId);
        integratedBulkQuoteBean.setRemark(integratedQuote.remark);

        return integratedBulkQuoteBean;
    }

    /***
     * 盘后大宗交易行情查询
     */
    public static IntegratedBulkCloseQuoteBean getIntegratedBulkCloseQuote(RespQryAfterIntegratedQuotes integratedQuote) {
        IntegratedBulkCloseQuoteBean integratedBulkCloseQuoteBean = new IntegratedBulkCloseQuoteBean();
        String stockCode = integratedQuote.stockCode;
        if ('1' == integratedQuote.market || '3' == integratedQuote.market) {
            stockCode = stockCode + "." + Market.SHSE.value;
        } else if ('2' == integratedQuote.market || '4' == integratedQuote.market) {
            stockCode = stockCode + "." + Market.SZSE.value;
        }
        integratedBulkCloseQuoteBean.setStockCode(stockCode);
        integratedBulkCloseQuoteBean.setStockName(integratedQuote.stockName);
        integratedBulkCloseQuoteBean.setBusinessBalance(integratedQuote.dealMoney1);
        integratedBulkCloseQuoteBean.setClosingPrice(integratedQuote.price1);
        integratedBulkCloseQuoteBean.setWeightavgPrice(integratedQuote.price2);
        if (EntrustUtils.isSHZQ(StockCodeUtils.getMarket(integratedBulkCloseQuoteBean.getStockCode()), null, integratedBulkCloseQuoteBean.getStockCode())) {
            integratedBulkCloseQuoteBean.setBusinessAmount(new Double(integratedQuote.dealVolume1 * 10).intValue());
            integratedBulkCloseQuoteBean.setBuyAmount1(integratedQuote.buyRemainQuantity1 * 10);
            integratedBulkCloseQuoteBean.setSaleAmount1(integratedQuote.sellRemainQuantity1 * 10);
            integratedBulkCloseQuoteBean.setBuyAmount2(integratedQuote.buyRemainQuantity2 * 10);
            integratedBulkCloseQuoteBean.setSaleAmount2(integratedQuote.sellRemainQuantity2 * 10);
        } else {
            integratedBulkCloseQuoteBean.setBusinessAmount(new Double(integratedQuote.dealVolume1).intValue());
            integratedBulkCloseQuoteBean.setBuyAmount1(integratedQuote.buyRemainQuantity1);
            integratedBulkCloseQuoteBean.setSaleAmount1(integratedQuote.sellRemainQuantity1);
            integratedBulkCloseQuoteBean.setBuyAmount2(integratedQuote.buyRemainQuantity2);
            integratedBulkCloseQuoteBean.setSaleAmount2(integratedQuote.sellRemainQuantity2);
        }

        return integratedBulkCloseQuoteBean;
    }

    /***
     * 持仓查询
     */
    public static PositionBean getAccountPosition(Account account, RespQryPositionData positionData) {
        PositionBean positionBean = new PositionBean();
        positionBean.setAccountId(account.getId());
        positionBean.setFinanceAccount(account.getFinanceAccount());
        String stockCode = positionData.stockCode;
        if ('1' == positionData.market) {
            positionBean.setMarket(Market.SHSE.value);
            stockCode = stockCode + "." + Market.SHSE.value;
        } else if ('2' == positionData.market) {
            positionBean.setMarket(Market.SZSE.value);
            stockCode = stockCode + "." + Market.SZSE.value;
        }
        positionBean.setStockCode(stockCode);
        positionBean.setStockName(positionData.stockName);
        positionBean.setCostPrice(positionData.costPrice);
        positionBean.setMarketValue(positionData.marketValue);
        positionBean.setHoldVolume(new Double(positionData.balance).longValue());
        positionBean.setEnableVolume(new Double(positionData.availableBalance).longValue());
        return positionBean;
    }

    public static StandardBonds getStandardBond(String accountId, RespQryStandardBondData standardBondData) {
        StandardBonds standardBonds = new StandardBonds();
        if ('1' == standardBondData.market || '3' == standardBondData.market) {
            standardBonds.setMarket(Market.SHSE.value);
        } else if ('2' == standardBondData.market || '4' == standardBondData.market) {
            standardBonds.setMarket(Market.SZSE.value);
        }
        standardBonds.setBondEnable(standardBondData.standardBondVolume);
        standardBonds.setDiscount(standardBondData.useRatio);
        standardBonds.setAccountId(accountId);
        return standardBonds;
    }
}
