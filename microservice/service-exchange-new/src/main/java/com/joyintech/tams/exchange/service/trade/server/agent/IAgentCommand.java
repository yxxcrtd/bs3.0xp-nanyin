package com.joyintech.tams.exchange.service.trade.server.agent;

import com.joyintech.tams.exchange.common.bean.AccountFinance;
import com.joyintech.tams.exchange.common.bean.AccountLoginReqBean;
import com.joyintech.tams.exchange.common.bean.EnableQryBean;
import com.joyintech.tams.exchange.common.bean.FuturesInstrumentCommissionRateQryBean;
import com.joyintech.tams.exchange.common.bean.FuturesInstrumentMarginRateQryBean;
import com.joyintech.tams.exchange.common.bean.FuturesInstrumentQryBean;
import com.joyintech.tams.exchange.common.bean.FuturesPositionBean;
import com.joyintech.tams.exchange.common.bean.FuturesPositionDetailBean;
import com.joyintech.tams.exchange.common.bean.FuturesPositionDetailQryBean;
import com.joyintech.tams.exchange.common.bean.FuturesPositionQryBean;
import com.joyintech.tams.exchange.common.bean.HistoryQryBean;
import com.joyintech.tams.exchange.common.bean.HistoryTrade;
import com.joyintech.tams.exchange.common.bean.InstrumentQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkCloseQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkCloseQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedFixedIncomeQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedFixedIncomeQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedQuote;
import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.PledgeDiscount;
import com.joyintech.tams.exchange.common.bean.PositionQueryBean;
import com.joyintech.tams.exchange.common.bean.QuoteQryBean;
import com.joyintech.tams.exchange.common.bean.ReissueEntrustQryBean;
import com.joyintech.tams.exchange.common.bean.ReissueTradeQryBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.StandardBonds;
import com.joyintech.tams.exchange.common.bean.bond.StandardBondQryBean;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.db.domain.Trade;

import java.util.List;

/**
 * 交易接口
 *
 * @author lifangpeng
 * @date 2021/10/9
 */
public interface IAgentCommand {

    /**
     * 查询委托
     *
     * @param accountId 账号
     * @return 委托
     * @throws Exception 异常
     */
    List<Entrust> reqEntrusts(String accountId) throws Exception;

    /**
     * 查询综合业务委托
     *
     * @param accountId 账号
     * @return 综合业务委托
     * @throws Exception 异常
     */
    List<IntegratedEntrust> reqIntegratedEntrusts(String accountId) throws Exception;

    /**
     * 查询成交委托
     *
     * @param accountId 账号
     * @return 成交委托
     * @throws Exception 异常
     */
    List<Trade> reqTrades(String accountId) throws Exception;

    /**
     * 委托下单
     *
     * @param entrust 委托
     */
    void entrust(Entrust entrust);

    /**
     * 综合业务下单
     *
     * @param integratedEntrust 综合业务委托
     */
    void integratedEntrust(IntegratedEntrust integratedEntrust);

    /**
     * 委托撤单
     *
     * @param entrust       委托
     * @param cancelEntrust 撤销委托
     */
    void cancelEntrust(Entrust entrust, EntrustInterface cancelEntrust);

    /**
     * 综合业务撤单
     *
     * @param integratedEntrust 综合业务委托
     * @param cancelEntrust     撤销委托
     */
    void cancelIntegratedEntrust(IntegratedEntrust integratedEntrust, IntegratedEntrust cancelEntrust);

    /**
     * 账号登录
     *
     * @param accountLoginReqBean 登录信息
     * @return 登录响应
     */
    ResultBean reqLoginAccount(AccountLoginReqBean accountLoginReqBean);

    /**
     * 账号登出
     *
     * @param account 账号信息
     */
    void reqLogoutAccount(Account account);

    /**
     * 查询大宗交易行情
     *
     * @param quoteQryBean 查询条件
     * @return 大宗交易行情
     * @throws Exception 异常
     */
    List<IntegratedQuote> reqIntegratedQuotes(QuoteQryBean quoteQryBean) throws Exception;

    /**
     * 查询最大可交易数量
     *
     * @param qryBean 查询条件
     * @return 最大可交易数量
     * @throws Exception 异常
     */
    int reqEnable(EnableQryBean qryBean) throws Exception;

    /**
     * 历史成交明细查询
     *
     * @param historyQryBean 查询条件
     * @return 历史成交明细
     * @throws Exception 异常
     */
    List<HistoryTrade> reqHistoryTrade(HistoryQryBean historyQryBean) throws Exception;

    /**
     * 查询账户资金信息
     *
     * @param accountId 账号
     * @return 账户资金信息
     * @throws Exception 异常
     */
    List<AccountFinance> reqCustomerFinance(String accountId) throws Exception;


    /**
     * 查询账户信息
     *
     * @param positionQueryBean 查询条件
     * @return 账户信息
     * @throws Exception 异常
     */
    List<PositionBean> reqAccountPosition(PositionQueryBean positionQueryBean) throws Exception;

    /**
     * 查询合约信息
     *
     * @param instrumentQryBean 查询条件
     * @return 合约信息
     * @throws Exception 异常
     */
    List<Instrument> reqInstrumentInfo(InstrumentQryBean instrumentQryBean) throws Exception;

    /**
     * 查询综合业务委托
     *
     * @param accountId 账号
     * @param market    市场
     * @param stockCode 债券代码
     * @return 综合业务委托
     * @throws Exception 异常
     */
    List<PledgeDiscount> reqPledgeDiscount(String accountId, String market, String stockCode) throws Exception;

    /**
     * 查询可用标准券
     *
     * @param standardBondQryBean 查询条件
     * @return 可用标准券
     * @throws Exception 异常
     */
    List<StandardBonds> reqStandardBonds(StandardBondQryBean standardBondQryBean) throws Exception;

    /**
     * 查询大宗行情
     *
     * @param integratedBulkQuoteQryBean 查询条件
     * @return 大宗行情
     * @throws Exception 异常
     */
    List<IntegratedBulkQuoteBean> reqIntegratedBulkQuote(IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) throws Exception;

    /**
     * 查询盘后大宗行情
     *
     * @param integratedBulkCloseQuoteQryBean 查询条件
     * @return 盘后大宗行情
     * @throws Exception 异常
     */
    List<IntegratedBulkCloseQuoteBean> reqIntegratedBulkCloseQuote(IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) throws Exception;

    /**
     * @param integratedRepurchaseQuoteQryBean
     * @return
     * @description: 回购行情
     * @date: 2020/9/22 9:31
     */
    List<IntegratedRepurchaseQuoteBean> reqIntegratedRepurchaseQuote(IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) throws Exception;

    /**
     * @param integratedFixedIncomeQuoteQryBean
     * @return
     * @description: 固收行情
     * @date: 2020/9/22 9:31
     */
    List<IntegratedFixedIncomeQuoteBean> reqIntegratedFixedIncomeQuote(IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) throws Exception;

    /**
     * @param reissueEntrustQryBean
     * @return
     * @description: 委托重传
     * @date: 2020/9/22 9:31
     */
    void reqReissueEntrust(ReissueEntrustQryBean reissueEntrustQryBean) throws Exception;

    /**
     * @param reissueTradeQryBean
     * @return
     * @description: 成交重传
     * @date: 2020/9/22 9:31
     */
    void reqReissueTrade(ReissueTradeQryBean reissueTradeQryBean) throws Exception;

    /**
     * @param futuresInstrumentQryBean
     * @return
     * @description: 查询期货合约
     * @date: 2020/10/12 9:31
     */
    List<FuturesInstrument> reqFuturesInstrument(FuturesInstrumentQryBean futuresInstrumentQryBean) throws Exception;

    /**
     * @Description: 查询期货持仓
     * @Author: liujianping
     * @Param: futuresPositionQryBean
     * @return: List<FuturesPositionBean>
     * @Date: 2020/11/3
     **/
    List<FuturesPositionBean> reqAccountFuturesPosition(FuturesPositionQryBean futuresPositionQryBean) throws Exception;

    /**
     * @Description: 查询期货持仓明细
     * @Author: liujianping
     * @Param: futuresPositionDetailQryBean
     * @return: List<FuturesPositionDetailBean>
     * @Date: 2020/11/3
     **/
    List<FuturesPositionDetailBean> reqAccountFuturesPositionDetail(FuturesPositionDetailQryBean futuresPositionDetailQryBean) throws Exception;

    /**
     * @Description: 查询期货保证金
     * @Author: liujianping
     * @Param: futuresInstrumentMarginRateQryBean 查询条件
     * @return: void
     * @Date: 2020/11/23
     **/
    void reqAccountFuturesMarginRate(FuturesInstrumentMarginRateQryBean futuresInstrumentMarginRateQryBean) throws Exception;

    /**
     * @Description: 查询期货手续费
     * @Author: liujianping
     * @Param: futuresInstrumentCommissionRateQryBean
     * @return: void
     * @Date: 2020/11/23
     **/
    void reqAccountFuturesCommissionRate(FuturesInstrumentCommissionRateQryBean futuresInstrumentCommissionRateQryBean) throws Exception;

}
