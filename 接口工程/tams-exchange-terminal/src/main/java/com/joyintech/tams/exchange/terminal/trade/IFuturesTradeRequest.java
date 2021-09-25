package com.joyintech.tams.exchange.terminal.trade;

import com.joyintech.tams.exchange.common.bean.*;

public interface IFuturesTradeRequest extends ITradeRequest {
    /***
     * @Description: 查询期货合约
     * @Param: [futuresInstrumentQryBean, requestId]
     * @return: void
     * @Author:liujianping
     * @Date: 2020/11/2
     */
    void queryFuturesInstrument(FuturesInstrumentQryBean futuresInstrumentQryBean, Long requestId);

    /**
     * @Description: 查询期货持仓
     * @Author: liujianping
     * @Param: [futuresPositionQryBean, requestId]
     * @return: void
     * @Date: 2020/11/3
     **/
    void queryFuturesPosition(FuturesPositionQryBean futuresPositionQryBean, Long requestId);

    /**
     * @Description: 查询期货持仓明细
     * @Author: liujianping
     * @Param: [futuresPositionDetailQryBean, requestId]
     * @return: void
     * @Date: 2020/11/3
     **/
    void queryFuturesPositionDetail(FuturesPositionDetailQryBean futuresPositionDetailQryBean, Long requestId);

    /**
     * @Description: 查询期货保证金率
     * @Author: liujianping
     * @Param: [futuresInstrumentMarginRateQryBean, requestId]
     * @return: void
     * @Date: 2020/11/3
     **/
    void queryFuturesInstrumentMarginRate(FuturesInstrumentMarginRateQryBean futuresInstrumentMarginRateQryBean, Long requestId);

    /**
     * @Description: 查询期货手续费
     * @Author: liujianping
     * @Param: [FuturesInstrumentCommissionRateQryBean, requestId]
     * @return: void
     * @Date: 2020/11/3
     **/
    void queryFuturesInstrumentCommissionRate(FuturesInstrumentCommissionRateQryBean futuresInstrumentCommissionRateQryBean, Long requestId);
}
