package com.joyintech.tams.exchange.terminal.trade.ctp.test;

import com.joyintech.tams.exchange.terminal.trade.ctp.CTPTraderApiAdapter;
import net.common.util.BufferUtil;
import net.jctp.*;
import net.jtrader.JTraderException;

import static net.jctp.JctpConstants.THOST_FTDC_BZTP_Future;
import static net.jctp.JctpConstants.THOST_TERT_RESTART;


public class CtpTest {

    public static void main(String[] args) throws JTraderException {
        TraderApi ctpApi = new TraderApi("");
        ctpApi.setListener(new CTPTraderApiAdapter(null, null));

        CThostFtdcUserSystemInfoField cThostFtdcUserSystemInfoField = new CThostFtdcUserSystemInfoField();
        cThostFtdcUserSystemInfoField.BrokerID = "9999";
        cThostFtdcUserSystemInfoField.ClientIPPort = 10101;
        cThostFtdcUserSystemInfoField.ClientPublicIP = "180.168.146.187";
        cThostFtdcUserSystemInfoField.UserID = "159887";
        ctpApi.RegisterUserSystemInfo(cThostFtdcUserSystemInfoField);
        ctpApi.SubscribePrivateTopic(THOST_TERT_RESTART);
        //ctpApi.Connect("");

        String traderFrontUrl = "tcp://180.168.146.187:10101";
        String brokerId = "9999";
        String userId = "159887";
        String password = "891026Ljp";
        String authCode = "";
        String appId = "";
        System.out.println("Connecting " + traderFrontUrl + " ... ");


        TraderApi traderApi = new TraderApi("tmp_traderapi/");
        System.out.println("JCTP TRADERAPI VERSION: " + traderApi.GetApiVersion());
        traderApi.setListener(new CTPTraderApiAdapter(null, null));
        traderApi.setFlowControl(true);
        traderApi.SubscribePrivateTopic(JctpConstants.THOST_TERT_QUICK);
        traderApi.SubscribePublicTopic(JctpConstants.THOST_TERT_QUICK);
        System.out.println("连接 TraderApi");
        traderApi.SyncConnect(traderFrontUrl);
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        if (authCode != null && authCode.length() > 0) {
            System.out.println("TraderApi 连接成功发送认证码");
            CThostFtdcReqAuthenticateField f = new CThostFtdcReqAuthenticateField();
            f.AuthCode = authCode;
            f.AppID = appId;
            f.BrokerID = brokerId;
            f.UserID = userId;
            CThostFtdcRspAuthenticateField r = traderApi.SyncReqAuthenticate(f);
            System.out.println("认证码结果：" + r);
        }
        {
            System.out.println("TraderApi 连接成功, 登录");
            CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
            f.BrokerID = brokerId;
            f.UserID = userId;
            f.Password = password;
            System.out.println("登录系统: " + f);
            CThostFtdcRspUserLoginField r = traderApi.SyncReqUserLogin(f);
            System.out.println("Front ID: " + r.FrontID + " , Session ID: " + r.SessionID + " , MaxOrderRef : " + r.MaxOrderRef);
        }
        {
            System.out.println("登录成功, 取结算单确认信息");
            CThostFtdcQrySettlementInfoConfirmField qrySettlementInfoConfirmField = new CThostFtdcQrySettlementInfoConfirmField(brokerId, userId, userId, null);
            CThostFtdcSettlementInfoConfirmField settlementInfoConfirmField =
                    traderApi.SyncReqQrySettlementInfoConfirm(qrySettlementInfoConfirmField);
            if (settlementInfoConfirmField == null || !traderApi.GetTradingDay().equals(settlementInfoConfirmField.ConfirmDate)) {
                System.out.println("查询上日结算单");
                CThostFtdcQrySettlementInfoField qrySettlementInfoField = new CThostFtdcQrySettlementInfoField();
                qrySettlementInfoField.BrokerID = brokerId;
                qrySettlementInfoField.AccountID = userId;
                CThostFtdcSettlementInfoField[] settlementInfoFields
                        = traderApi.SyncAllReqQrySettlementInfo(qrySettlementInfoField);
                if (settlementInfoFields == null || settlementInfoFields.length == 0) {
                    System.out.println("上一交易日无结算");
                } else {
                    CThostFtdcSettlementInfoField f1 = settlementInfoFields[0];
                    byte[][] rawByteArrays = new byte[settlementInfoFields.length][];
                    for (int i = 0; i < settlementInfoFields.length; i++)
                        rawByteArrays[i] = settlementInfoFields[i]._rawBytes;

                    System.out.println("交易日 " + f1.TradingDay
                            + " 投资者 " + f1.InvestorID
                            + " 结算号 " + f1.SettlementID
                            + " 流水号 " + f1.SequenceNo);
                    System.out.println(BufferUtil.getStringFromByteArrays(rawByteArrays, JctpConstants.Offset_CThostFtdcSettlementInfoField_Content, JctpConstants.SizeOf_TThostFtdcContentType - 1));
                }
                System.out.println("确认结算单");
                settlementInfoConfirmField = new CThostFtdcSettlementInfoConfirmField(brokerId, userId, traderApi.GetTradingDay(), "", 0, null, null);
                settlementInfoConfirmField = traderApi.SyncReqSettlementInfoConfirm(settlementInfoConfirmField);
            }
        }
        {
            System.out.println("查交易所保证金率...");
            CThostFtdcQryExchangeMarginRateField f = new CThostFtdcQryExchangeMarginRateField();
            f.BrokerID = brokerId;
            CThostFtdcExchangeMarginRateField rr[] = traderApi.SyncAllReqQryExchangeMarginRate(f);
            for (int i = 0; i < rr.length; i++) {
                CThostFtdcExchangeMarginRateField r = rr[i];
                System.out.println(r.InstrumentID + " 多仓保证金率(金): " + r.LongMarginRatioByMoney + " 多仓保证金率(量): " + r.LongMarginRatioByVolume
                        + " 空仓保证金率(金): " + r.ShortMarginRatioByMoney + " 空仓保证金率(量): " + r.ShortMarginRatioByVolume);
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
        {
            System.out.println("查交易所手续费率...");
            CThostFtdcQryInstrumentCommissionRateField f = new CThostFtdcQryInstrumentCommissionRateField(brokerId, userId, null, null, null);
            CThostFtdcInstrumentCommissionRateField[] rr = traderApi.SyncAllReqQryInstrumentCommissionRate(f);
            for (int i = 0; i < rr.length; i++) {
                CThostFtdcInstrumentCommissionRateField r = rr[i];
                System.out.println(r.InstrumentID + " 开仓费率(金)： " + r.OpenRatioByMoney + " 开仓费率(量)： " + r.OpenRatioByVolume
                        + " 平仓费率(金)： " + r.CloseRatioByMoney + " 平仓费率(量)： " + r.CloseRatioByVolume
                        + " 今平费率(金)： " + r.CloseTodayRatioByMoney + " 今平费率(量)： " + r.CloseTodayRatioByVolume);
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
        {
            System.out.println("查保证金...");
            CThostFtdcQryTradingAccountField q = new CThostFtdcQryTradingAccountField(brokerId, userId, null, THOST_FTDC_BZTP_Future, null);
            CThostFtdcTradingAccountField f = traderApi.SyncReqQryTradingAccount(q);
            System.out.println("总额: " + f.Balance + " 可用: " + f.Available + " 保证金: " + f.CurrMargin);
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
        {
            System.out.println("查持仓明细...");
            CThostFtdcQryInvestorPositionDetailField f = new CThostFtdcQryInvestorPositionDetailField();
            f.BrokerID = brokerId;
            f.InvestorID = userId;
            CThostFtdcInvestorPositionDetailField[] r = traderApi.SyncAllReqQryInvestorPositionDetail(f);
            for (int i = 0; i < r.length; i++) {
                CThostFtdcInvestorPositionDetailField d = r[i];
                System.out.println(d.TradeID + " " + d.ExchangeID + " " + (d.Direction == JctpConstants.THOST_FTDC_D_Buy ? "多" : "空")
                        + " 日 " + d.OpenDate + " 价 " + d.OpenPrice + " 量 " + d.Volume + " 盈 " + d.CloseProfitByDate + " 保 " + d.Margin
                );
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
        {
            System.out.println("查持仓信息...");
            CThostFtdcQryInvestorPositionField f = new CThostFtdcQryInvestorPositionField(brokerId, userId, null, null, null);
            CThostFtdcInvestorPositionField[] r = traderApi.SyncAllReqQryInvestorPosition(f);
            if (r != null) {
                for (int i = 0; i < r.length; i++) {
                    System.out.println("InstrumentID: " + r[i].InstrumentID + ", Amount: " + r[i].OpenAmount + ", Volume: " + r[i].OpenVolume + ", Cost " + r[i].OpenCost);
                }
            }
            try {
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
        {
            System.out.println("查下单信息...");
            CThostFtdcQryOrderField f = new CThostFtdcQryOrderField();
            f.BrokerID = brokerId;
            f.InvestorID = userId;
            CThostFtdcOrderField[] r = traderApi.SyncAllReqQryOrder(f);
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        return;
    }
}


