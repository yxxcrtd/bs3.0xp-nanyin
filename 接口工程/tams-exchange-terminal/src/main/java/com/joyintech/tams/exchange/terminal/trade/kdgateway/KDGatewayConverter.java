package com.joyintech.tams.exchange.terminal.trade.kdgateway;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.CancelEntrustBean;
import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.utils.EntrustUtils;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.terminal.bean.EntrustBean;
import com.joyintech.tams.exchange.terminal.bean.IntegratedEntrustBean;
import com.joyintech.tams.exchange.terminal.trade.EntrustIdManager;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class KDGatewayConverter {

    public static String getKDMarket(String market) {
        return market.equals(Market.SHSE.value) ? "10" : "00";
    }

    public static String getMarketFromKD(String kdMarket) {
        return "10".equals(kdMarket) ? Market.SHSE.value : Market.SZSE.value;
    }

    public static List<Trade> convertTradeList(List<Map> respBody, String accountId) {
        List<Trade> tradeList = new ArrayList<>();
        respBody.forEach(map -> {
            Trade trade = new Trade();
            trade.setReportNo((String) map.get("ORDER_ID"));
            trade.setAccountId(accountId);
            trade.setTradingDay((String) map.get("TRD_DATE"));
            trade.setTradeTime((String) map.get("MATCHED_TIME"));
            trade.setFinanceAccount((String) map.get("ACCOUNT"));
            trade.setBsFlag((String) map.get("TRD_ID"));
            trade.setMarket(getMarketFromKD((String) map.get("MARKET")));
            trade.setStockCode((String) map.get("SECU_CODE"));
            trade.setStockName((String) map.get("SECU_NAME"));
            trade.setTradedBalance(BigDecimal.valueOf(Double.parseDouble((String) map.get("MATCHED_AMT"))));
            trade.setTradedPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("MATCHED_PRICE"))));
            long tradeVolume = Long.parseLong((String) map.get("MATCHED_QTY"));
            if (tradeVolume > 0) {
                trade.setTradeType(TradeType.NORMAL.value);
                trade.setTradedVolume(tradeVolume);
            } else {
                trade.setTradeType(TradeType.CANCEL.value);
                trade.setTradedVolume(-tradeVolume);
            }
            trade.setBrokerTradeId((String) map.get("MATCHED_SN"));
            trade.setBrokerEntrustId((String) map.get("ORDER_ID"));
            trade.setEntrustBs(EntrustUtils.getEntrustBs(trade.getBsFlag()));
            trade.setEntrustProp(EntrustUtils.getEntrustProp(trade.getBsFlag(), trade.getMarket()));
            if (trade.getTradeType() == TradeType.CANCEL.value
                    || Strings.isNullOrEmpty(trade.getBrokerTradeId())) {
                trade.setBrokerTradeId("cancel_" + trade.getBrokerEntrustId());
            }
            tradeList.add(trade);
        });
        return tradeList;
    }

    public static List<EntrustBean> convertEntrustList(List<Map> respBody, String accountId, EntrustIdManager kdEntrustIdMap) {
        List<EntrustBean> entrustList = new ArrayList<>();
        respBody.forEach(map -> {
            EntrustBean entrust = new EntrustBean();
            entrust.setReportNo((String) map.get("ORDER_ID"));
            entrust.setAccountId(accountId);
            entrust.setFinanceAccount((String) map.get("ACCOUNT"));
            entrust.setBsFlag((String) map.get("TRD_ID"));
            entrust.setStockName((String) map.get("SECU_NAME"));
            entrust.setBrokerEntrustId((String) map.get("ORDER_ID"));
            entrust.setStockName((String) map.get("SECU_NAME"));
            entrust.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("PRICE"))));
            entrust.setEntrustVolume(Long.parseLong((String) map.get("QTY")));
            entrust.setLocalEntrustId((String) map.get("EXT_REC_NUM"));
            // 柜台返回委托相关信息（包含错误信息）
            entrust.setErrorMsg((String) map.get("OP_REMARK"));
            if (!Strings.isNullOrEmpty(entrust.getLocalEntrustId())) {
                entrust.setId(kdEntrustIdMap.getIdByLocalEntrustId(accountId, entrust.getLocalEntrustId()));
            }
            entrust.setTradedVolume(Long.parseLong(String.valueOf(map.get("MATCHED_QTY"))));
            entrust.setTradedPrice(BigDecimal.valueOf(Double.parseDouble(String.valueOf(map.get("MATCHED_PRICE")))));
            entrust.setTradedBalance(BigDecimal.valueOf(Double.parseDouble(String.valueOf(map.get("MATCHED_AMT")))));
            entrust.setCancelVolume(Long.parseLong(String.valueOf(map.get("WITHDRAWN_QTY"))));
            entrust.setEntrustStatus(getEntrustStatus(Long.parseLong(String.valueOf(map.get("MATCHED_QTY"))), Long.parseLong(String.valueOf(map.get("QTY"))), String.valueOf(map.get("VALID_FLAG")), String.valueOf(map.get("IS_WITHDRAW")), String.valueOf(map.get("DCL_FLAG"))));
            entrustList.add(entrust);
        });
        return entrustList;
    }

    public static EntrustBean convertEntrust(List<Map> respBody, EntrustBean entrust) {
        respBody.forEach(map -> {
            entrust.setBrokerEntrustId((String) map.get("ORDER_ID"));
            entrust.setReportNo((String) map.get("ORDER_ID"));
        });
        return entrust;
    }

    public static CancelEntrustBean convertCancelEntrust(List<Map> respBody, CancelEntrustBean cancelEntrustBean) {
        respBody.forEach(map -> cancelEntrustBean.setBrokerEntrustId((String) map.get("ORDER_ID")));
        cancelEntrustBean.setCancelBrokerEntrustId(cancelEntrustBean.getCancelId());
        return cancelEntrustBean;
    }

    public static IntegratedEntrustBean convertIntegratedEntrust(List<Map> respBody, IntegratedEntrustBean integratedEntrust) {
        respBody.forEach(map -> {
            integratedEntrust.setBrokerEntrustId((String) map.get("ORDER_ID"));
            integratedEntrust.setReportNo((String) map.get("ORDER_ID"));
        });
        return integratedEntrust;
    }

    public static List<HistoryTrade> convertHistoryTrade(List<Map> respBody, String accountId) {
        List<HistoryTrade> trades = new ArrayList<>();
        respBody.forEach(map -> {
            HistoryTrade trade = new HistoryTrade();
            trade.setAccountId(accountId);
            trade.setClearDay((String) map.get("SETT_DATE"));
            trade.setFinanceAmount(Double.parseDouble(String.valueOf(map.get("CPTL_AMT"))));
            trade.setTradingDay((String) map.get("TRD_DATE"));
            trade.setTradeTime((String) map.get("MATCHED_TIME"));
            trade.setFinanceAccount((String) map.get("ACCOUNT"));
            trade.setStockHolderId((String) map.get("SECU_ACC"));
            trade.setBsFlag((String) map.get("TRD_ID"));
            trade.setMarket((String) map.get("MARKET"));
            trade.setSerialNo((String) map.get("SERIAL_NO"));
            trade.setOrderId((String) map.get("ORDER_ID"));
            trade.setStockCode((String) map.get("SECU_CODE"));
            trade.setStockName((String) map.get("SECU_NAME"));
            trade.setStockType((String) map.get("SECU_CLS"));
            trade.setEntrustPrice(Double.parseDouble(String.valueOf(map.get("PRICE"))));
            trade.setEntrustVolume(Long.parseLong(String.valueOf(map.get("QTY"))));
            trade.setTradePrice(Double.parseDouble(String.valueOf(map.get("MATCHED_PRICE"))));
            trade.setTradeVolume(Long.parseLong(String.valueOf(map.get("MATCHED_QTY"))));
            trade.setTradedBalance(Double.parseDouble(String.valueOf(map.get("MATCHED_AMT"))));
            trade.setBrokerTradeId((String) map.get("MATCHED_SN"));
            trade.setProcedureCost(Double.parseDouble(String.valueOf(map.get("COMMISSION"))));
            trade.setPrintingCost(Double.parseDouble(String.valueOf(map.get("STAMP_DUTY"))));
            trade.setSecCost(Double.parseDouble(String.valueOf(map.get("ADMIN_FEE"))));
            trade.setHandleCost(Double.parseDouble(String.valueOf(map.get("HANDLE_FEE"))));
            trade.setTransferCost(Double.parseDouble(String.valueOf(map.get("TRANS_FEE"))));
            trade.setClearCost(Double.parseDouble(String.valueOf(map.get("CLEAR_FEE"))));
            trade.setOtherCost(Double.parseDouble(String.valueOf(map.get("OTHER_FEE"))));
            trade.setTradeRuleCost(Double.parseDouble(String.valueOf(map.get("TRADE_FEE"))));
            trade.setPostStr((String) map.get("RECORD_NO"));
            trades.add(trade);
        });
        return trades;
    }

    public static int convertEnable(List<Map> respBody) {
        return Integer.parseInt(String.valueOf(respBody.get(0).get("SHARE_QTY")));
    }

    public static List<IntegratedQuote> convertIntegratedQuote(List<Map> respBody, String accountId, String funId) {
        List<IntegratedQuote> integratedQuotes = new ArrayList<>();
        respBody.forEach(map -> {
            IntegratedQuote integratedQuote = new IntegratedQuote();
            integratedQuote.setAccountId(accountId);
            if ("513".equals(funId)) {
                integratedQuote.setStockCode((String) map.get("YXZQDM"));
                integratedQuote.setEntrustVolume(Integer.parseInt((String) map.get("YXSBSL")));
                integratedQuote.setEntrustPrice(Double.parseDouble((String) map.get("YXSBJG")));
                integratedQuote.setBsFlag((String) map.get("YXMMLB"));
                integratedQuote.setContractId((String) map.get("YXZDYDH"));//自动约定号
                integratedQuote.setLiaisonTel((String) map.get("YXLXFS"));// 联系方式
                integratedQuote.setReportTime((String) map.get("YXSBSJ"));//申报时间
                integratedQuote.setPropSeatNo((String) map.get("YXXWDM"));//意向交易单元代码
                integratedQuote.setBrokerEntrustId((String) map.get("YXSBXH"));//意向申报序号
                integratedQuote.setOrigSerialNo((String) map.get("YXLSH"));//意向流水号
                integratedQuote.setAgencyNo((String) map.get("YXYHDM"));//用户代码
                integratedQuote.setLiaisonName((String) map.get("YXYHMC"));//用户名称
            } else if ("718".equals(funId)) {
                integratedQuote.setTradeDay((String) map.get("TRD_DATE"));
                integratedQuote.setTradeTime((String) map.get("MATCH_TIME"));
                integratedQuote.setQueryReqId((String) map.get("APPL_REQ_ID"));
                integratedQuote.setPosstr((String) map.get("REC_SN"));
                integratedQuote.setEndQuoteNo((String) map.get("END_SEQ_NO"));
                integratedQuote.setBrokerEntrustId((String) map.get("QUOTE_REF_ID"));
                integratedQuote.setReportType((String) map.get("ORD_TYPE"));
                integratedQuote.setEntrustPrice(Double.parseDouble((String) map.get("PRICE")));
                integratedQuote.setCompactTerm(Integer.parseInt((String) map.get("REPURCHASE_TERM")));
                integratedQuote.setRealOccupyDays(Integer.parseInt((String) map.get("ACCRUAL_DAYS")));
                integratedQuote.setFirstSettleDate((String) map.get("SETTL_DATE"));
                integratedQuote.setBackDate((String) map.get("MATURITY_DATE"));
                integratedQuote.setExpireSettleDate((String) map.get("SETTL_DATE2"));
                integratedQuote.setEntrustBs((String) map.get("SIDE"));
                integratedQuote.setPledgeStampCode((String) map.get("SECU_CODE"));
                integratedQuote.setOrigPledgeStampCode((String) map.get("OLD_SECU_CODE"));
                integratedQuote.setPledgeStampVolume(Integer.parseInt((String) map.get("ORDER_QTY")));
                integratedQuote.setDiscount(Double.parseDouble((String) map.get("RATE")));
                integratedQuote.setTradeBalance(Double.parseDouble((String) map.get("MATCH_AMT")));
                integratedQuote.setOrigTradeBalance(Double.parseDouble((String) map.get("UNDERLYING_QTY")));
                integratedQuote.setBackInterest(Double.parseDouble((String) map.get("INTEREST_AMT")));
                integratedQuote.setExpireSettleBalance(Double.parseDouble((String) map.get("SETTL_CURR_AMT")));
                integratedQuote.setPledgeStampBalance(Double.parseDouble((String) map.get("LAST_QTY")));
                integratedQuote.setReductionType((String) map.get("ORDER_REST"));
                integratedQuote.setOrigTradeDate((String) map.get("ORIG_TRADE_DATE"));
                integratedQuote.setOrigSerialNo((String) map.get("EXEC_REF_ID"));
                integratedQuote.setPropSeatNo((String) map.get("COMPANY_ID_IN"));
                integratedQuote.setPropSeatName((String) map.get("COMPANY_NAME_OUT"));
                integratedQuote.setLiaisonTel((String) map.get("TRADER_OUT"));
                integratedQuote.setRemark((String) map.get("REMARK"));
                integratedQuote.setEntrustProp(convertReportType(integratedQuote.getReportType()));
            }
            integratedQuotes.add(integratedQuote);
        });
        return integratedQuotes;
    }
    public static List<IntegratedBulkQuoteBean> convertIntegratedBulkQuote(List<Map> respBody, String accountId, String funId) {
        List<IntegratedBulkQuoteBean> integratedBulkQuoteBeans = new ArrayList<>();
        respBody.forEach(map -> {
            IntegratedBulkQuoteBean integratedBulkQuoteBean = new IntegratedBulkQuoteBean();
            //integratedBulkQuoteBean.setAccountId(accountId);
            if ("513".equals(funId)) {
                integratedBulkQuoteBean.setOrderNo((String) map.get("YXSBXH"));//意向申报序号
                integratedBulkQuoteBean.setSeatNo((String) map.get("YXXWDM"));//意向交易单元代码
                integratedBulkQuoteBean.setStockCode((String) map.get("YXZQDM"));
                integratedBulkQuoteBean.setEntrustPrice(Double.parseDouble((String) map.get("YXSBJG")));
                integratedBulkQuoteBean.setEntrustAmount(Integer.parseInt((String) map.get("YXSBSL")));
                integratedBulkQuoteBean.setRelationName((String) map.get("YXYHMC"));//用户名称
                integratedBulkQuoteBean.setRelationTel((String) map.get("YXLXFS"));// 联系方式
                integratedBulkQuoteBean.setCbpconferId((String) map.get("YXZDYDH"));//自动约定号
                integratedBulkQuoteBean.setReportTime(((String) map.get("YXSBSJ")));//申报时间

                //integratedBulkQuoteBean.set.setOrigSerialNo((String) map.get("YXLSH"));//意向流水号
                //integratedBulkQuoteBean.set.setAgencyNo((String) map.get("YXYHDM"));//用户代码

            }

            integratedBulkQuoteBeans.add(integratedBulkQuoteBean);
        });
        return integratedBulkQuoteBeans;
    }

    public static List<IntegratedBulkCloseQuoteBean> convertIntegratedBulkCloseQuote(List<Map> respBody, String accountId, String funId) {
        List<IntegratedBulkCloseQuoteBean> integratedBulkCloseQuoteBeans = new ArrayList<>();
        respBody.forEach(map -> {
            IntegratedBulkCloseQuoteBean integratedBulkCloseQuoteBean = new IntegratedBulkCloseQuoteBean();
            //integratedBulkQuoteBean.setAccountId(accountId);
            if ("880".equals(funId)) {
                String market = (String) map.get("MARKET");
                if (Strings.isNullOrEmpty(market)) {
                    integratedBulkCloseQuoteBean.setStockCode(StockCodeUtils.formatStockCode((String) map.get("SECU_CODE"), market));
                } else {
                    integratedBulkCloseQuoteBean.setStockCode((String) map.get("SECU_CODE"));
                }

                integratedBulkCloseQuoteBean.setBusinessBalance(Double.parseDouble((String) map.get("MATCHED_AMT")));
                integratedBulkCloseQuoteBean.setBusinessAmount(Integer.parseInt((String) map.get("MATCHED_QTY")));

                integratedBulkCloseQuoteBean.setClosingPrice(Double.parseDouble((String) map.get("CLOSE_PRICE")));
                integratedBulkCloseQuoteBean.setBuyAmount1(Integer.parseInt((String) map.get("ODD_BUY_QTY")));
                integratedBulkCloseQuoteBean.setSaleAmount1(Integer.parseInt((String) map.get("ODD_SELL_QTY")));
            }

            integratedBulkCloseQuoteBeans.add(integratedBulkCloseQuoteBean);
        });
        return integratedBulkCloseQuoteBeans;
    }
    public static List<IntegratedRepurchaseQuoteBean> convertIntegratedRepurchaseQuote(List<Map> respBody, String accountId, String funId) {
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeans = new ArrayList<>();
        respBody.forEach(map -> {
            IntegratedRepurchaseQuoteBean integratedRepurchaseQuoteBean = new IntegratedRepurchaseQuoteBean();

            if ("718".equals(funId)) {
                integratedRepurchaseQuoteBean.setEntrustBs((String)map.get("SIDE"));//无字典对应，暂不转换
                integratedRepurchaseQuoteBean.setStockCode(StockCodeUtils.formatStockCode((String) map.get("SECU_CODE"),"SH"));//目前仅支持上海协议回购
                integratedRepurchaseQuoteBean.setRepoTerm(Integer.parseInt((String) map.get("REPURCHASE_TERM")));
                integratedRepurchaseQuoteBean.setFundUsedDays(Integer.parseInt((String) map.get("ACCRUAL_DAYS")));
                integratedRepurchaseQuoteBean.setFirstExchdate(Integer.parseInt((String) map.get("SETTL_DATE")));
                integratedRepurchaseQuoteBean.setRepurchaseDate(Integer.parseInt((String) map.get("MATURITY_DATE")));
                integratedRepurchaseQuoteBean.setSettleEndDate(Integer.parseInt((String) map.get("SETTL_DATE2")));
                integratedRepurchaseQuoteBean.setOldImpawnCode((String) map.get("OLD_SECU_CODE"));
                integratedRepurchaseQuoteBean.setImpawnAmount(Integer.parseInt((String) map.get("ORDER_QTY")));
                integratedRepurchaseQuoteBean.setFunderRatio(Double.parseDouble((String) map.get("RATE")));
                integratedRepurchaseQuoteBean.setImpawnBalance(Double.parseDouble((String) map.get("MATCH_AMT")));
                integratedRepurchaseQuoteBean.setPrevImpawnBalance(Double.parseDouble((String) map.get("UNDERLYING_QTY")));
                integratedRepurchaseQuoteBean.setProfit(Double.parseDouble((String) map.get("INTEREST_AMT")));
                integratedRepurchaseQuoteBean.setSettleBalance(Double.parseDouble((String) map.get("SETTL_CURR_AMT")));
                integratedRepurchaseQuoteBean.setAgencyNo((String) map.get("COMPANY_ID_IN"));
                integratedRepurchaseQuoteBean.setOppoTraderId((String) map.get("TRADER_OUT"));
                integratedRepurchaseQuoteBean.setOppoAgencyName((String) map.get("COMPANY_NAME_OUT"));
                integratedRepurchaseQuoteBean.setRemark((String) map.get("REMARK"));
                integratedRepurchaseQuoteBean.setExpireYearRate(Double.parseDouble((String) map.get("PRICE")));
            }
            integratedRepurchaseQuoteBeans.add(integratedRepurchaseQuoteBean);
        });
        return integratedRepurchaseQuoteBeans;
    }

    public static List<AccountFinance> convertCustomerFinance(List<Map> respBody, String accountId) {
        List<AccountFinance> accountFinances = new ArrayList<>();
        respBody.forEach(map -> {
            AccountFinance accountFinance = new AccountFinance();
            accountFinance.setAccountId(accountId);
            accountFinance.setCustomerId((String) map.get("USER_CODE"));
            accountFinance.setFinanceAccount((String) map.get("ACCOUNT"));
            accountFinance.setOrgId((String) map.get("BRANCH"));
            accountFinance.setFinanceBalance(Double.parseDouble((String) map.get("BALANCE")));
            accountFinance.setMoneyType((String) map.get("CURRENCY"));
            accountFinance.setEnableBalance(Double.parseDouble((String) map.get("AVAILABLE")));
            accountFinance.setStkValue(Double.parseDouble((String) map.get("MKT_VAL")));
            accountFinances.add(accountFinance);
        });
        return accountFinances;
    }

    public static List<PositionBean> convertAccountPosition(List<Map> respBody, PositionQueryBean positionQueryBean) {
        List<PositionBean> positionList = new ArrayList<>();
        respBody.forEach(map -> {
            PositionBean positionBean = new PositionBean();
            positionBean.setAccountId(positionQueryBean.getAccountId());
            positionBean.setFinanceAccount((String) map.get("ACCOUNT"));
            positionBean.setMarket(getMarketFromKD((String) map.get("MARKET")));
            positionBean.setStockCode((String) map.get("SECU_CODE"));
            positionBean.setStockName((String) map.get("SECU_NAME"));
            positionBean.setMarketValue(Double.parseDouble((String) map.get("MKT_VAL")));
            positionBean.setHoldVolume(Long.parseLong((String) map.get("SHARE_BLN")));
            positionBean.setEnableVolume(Long.parseLong((String) map.get("SHARE_AVL")));
            positionBean.setCostPrice(Double.parseDouble((String) map.get("COST_PRICE")));
            positionList.add(positionBean);
        });
        return positionList;
    }

    public static String getKdBsFlag(String bsFlag) {
        Map<String, BSFlag> bsFlagMap = Arrays.stream(BSFlag.values()).collect(Collectors.toMap(BSFlag::getValue, bsFlag1 -> bsFlag1));
        BSFlag kdBsFlag = bsFlagMap.get(bsFlag);
        if (Objects.nonNull(kdBsFlag)) {
            return kdBsFlag.getKdValue();
        } else
            return null;
    }

    /***
     * @Description: 债券质押率返回数据解析
     * @Param: [respBody, accountId]
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.PledgeDiscount>
     * @Author: MAXX
     * @Date: 2020/5/14
     */
    public static List<PledgeDiscount> convertPledgeDiscount(List<Map> respBody, String accountId) {
        List<PledgeDiscount> pledgeDiscountList = Lists.newArrayListWithExpectedSize(respBody.size());
        respBody.forEach(map -> {
            PledgeDiscount pledgeDiscount = new PledgeDiscount();
            pledgeDiscount.setAccountId(accountId);
            pledgeDiscount.setFinanceAccount((String) map.get("ACCOUNT"));
            pledgeDiscount.setMarket(getMarketFromKD((String) map.get("MARKET")));
            pledgeDiscount.setStockCode((String) map.get("SECU_CODE"));
            pledgeDiscount.setStockName((String) map.get("SECU_NAME"));
            pledgeDiscount.setStockType((String) map.get("SECU_CLS"));
            pledgeDiscount.setDiscount(Double.parseDouble(String.valueOf(map.get("CONVERT_RATE"))));
            pledgeDiscount.setBondEnable(Integer.parseInt(String.valueOf(map.get("STD_BOND"))));
            pledgeDiscount.setFinprodMarketId(pledgeDiscount.getStockCode() + "." + pledgeDiscount.getMarket());
            pledgeDiscountList.add(pledgeDiscount);
        });
        return pledgeDiscountList;
    }

    /*** 
     * @Description: 标准券查询
     * @Param: [respBody, accountId] 
     * @return: java.util.List<com.joyintech.tams.exchange.common.bean.StandardBonds> 
     * @Author: MAXX
     * @Date: 2020/5/14 
     */
    public static List<StandardBonds> convertStandardBonds(List<Map> respBody, String accountId) {
        List<StandardBonds> standardBondsList = Lists.newArrayListWithExpectedSize(respBody.size());
        respBody.forEach(map -> {
            StandardBonds standardBonds = new StandardBonds();
            standardBonds.setAccountId(accountId);
            standardBonds.setMarket(getMarketFromKD((String) map.get("MARKET")));
            standardBonds.setStockCode((String) map.get("SECU_CODE"));
            standardBonds.setStockName((String) map.get("SECU_NAME"));
            standardBonds.setStockType((String) map.get("SECU_CLS"));
            standardBonds.setBondFrozen(Integer.parseInt(String.valueOf(map.get("SHARE_TRD_FRZ"))));
            standardBonds.setDiscount(Double.parseDouble(String.valueOf(map.get("CONVERT_RATE"))));
            standardBonds.setBondEnable(Integer.parseInt(String.valueOf(map.get("STD_BOND"))));
            standardBondsList.add(standardBonds);
        });
        return standardBondsList;
    }

    /**
     * KDGateway 固收平台不支持业务
     *
     * @param entrustProp
     * @return
     */
    public static boolean unSupportFunction(String entrustProp) {
        return (EntrustProp.SH_FIXEDINCOME_BAOJIA_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_FIXEDINCOME_TRADE_CLICK.value.equals(entrustProp)
                || EntrustProp.SH_FIXEDINCOME_KZHCJSB.value.equals(entrustProp)
                || EntrustProp.SH_FIXEDINCOME_ZYJCJSB.value.equals(entrustProp));
    }

    public static int getEntrustStatus(long tradedVolume, long entrustVolume, String validFlag, String isWithdraw, String dclFlag) {
        if ("0".equals(validFlag))
            return EntrustStatus.INVALID.value;//合法标志
        if ("0".equals(dclFlag)) {//0:未发送
            return EntrustStatus.UNREPORTED.value;
        } else {//已发送
            if ("0".equals(isWithdraw)) {//0:正常委托
                if (tradedVolume > 0 && tradedVolume < entrustVolume)
                    return EntrustStatus.PART_SUCCESS.value;
                else if (tradedVolume == entrustVolume)
                    return EntrustStatus.SUCCEEDED.value;
                else
                    return EntrustStatus.REPORTED.value;
            } else {//撤单委托
                if (tradedVolume > 0 && tradedVolume < entrustVolume)
                    return EntrustStatus.PART_TRADED_CANCELED.value;
                else if (tradedVolume == entrustVolume)
                    return EntrustStatus.CANCELED.value;
                else
                    return EntrustStatus.REPORTED_TOBE_CANCEL.value;
            }
        }
    }

    public static String convertReportType(String reportType) {
        if (Strings.isNullOrEmpty(reportType))
            return EntrustProp.SH_REPURCHASE_TRADE.value;
        switch (reportType) {
            case "F":
                return EntrustProp.SH_REPURCHASE_TRADE.value;
            case "X":
                return EntrustProp.SH_REPURCHASE_RENEWAL.value;
            case "C":
                return EntrustProp.SH_REPURCHASE_RELEASE.value;
            case "H":
                return EntrustProp.SH_REPURCHASE_EXCHANGE.value;
            case "Z":
                return EntrustProp.SH_REPURCHASE_TERMINATE.value;
        }
        return EntrustProp.SH_REPURCHASE_EXPIRE.value;
    }
    //获取报价申报类型
    public static String getOrdtype(String entrustProp) {
        if (EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_TRADE_REFUSE.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_TRADE.value.equals(entrustProp)) {
            return "F";//成交申报
        } else if (EntrustProp.SH_REPURCHASE_RENEWAL_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_RENEWAL_REFUSE.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_RENEWAL.value.equals(entrustProp)) {
            return "X";//到期续做
        } else if (EntrustProp.SH_REPURCHASE_RELEASE_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_RELEASE_REFUSE.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_RELEASE.value.equals(entrustProp)) {
            return "C";//解除质押
        } else if (EntrustProp.SH_REPURCHASE_EXCHANGE_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_EXCHANGE_REFUSE.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_EXCHANGE.value.equals(entrustProp)) {
            return "H";//换券申报
        } else if (EntrustProp.SH_REPURCHASE_TERMINATE_CONFIRM.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_TERMINATE_REFUSE.value.equals(entrustProp)
                || EntrustProp.SH_REPURCHASE_TERMINATE.value.equals(entrustProp)) {
            return "Z";//提前终止
        }
        return "";
    }
}
