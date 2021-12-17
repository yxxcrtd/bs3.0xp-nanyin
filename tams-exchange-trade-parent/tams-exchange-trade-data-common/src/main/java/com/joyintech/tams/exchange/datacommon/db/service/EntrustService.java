package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.Collection;
import java.util.List;

public interface EntrustService extends AsyncService<String, Entrust> {
    List<Entrust> queryByUserId(String userId);

    List<Entrust> queryEntrustByOrderId(String orderId);

    List<Entrust> queryEntrustByInstructionId(String instructionId);

    Entrust queryEntrustByBrokerEntrustIdAndAccount(String brokerEntrustId, String accountId);

    Entrust queryEntrustByLocalEntrustIdAndAccount(String localEntrustId, String accountId);

    List<Entrust> queryEntrustByAccountId(String accountId);

    String genDbId();

    String genLocalId(Entrust entrust);

    PageInfo<Entrust> queryAllForPage(int pageNum, int pageSize, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus);

    double getAccountCancelRatio(String accountId);

    int getAccountTotalCount(String accountId);

    Collection<Entrust> getAccountSameStockCodeEntrusts(String accountId, String stockCode);

    void clearCacheBySettle(String date);

    void fillPlatDate(String date);

    /*** 
     * @Description: 根据原委托获取撤单委托
     * @Param: [origEntrustId, accountId] 
     * @return: java.util.List<com.joyintech.tams.exchange.common.db.domain.Entrust>
     * @Author: MAXX
     * @Date: 2020/9/1 
     */
    List<Entrust> queryCancelEntrustByOrigEntrustIdAndAccount(String origEntrustId, String accountId);

    /***
     * @Description: 根据委托状态获取委托
     * @Param: [userId, entrustStatusList]
     * @return: java.util.List<com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust>
     * @Author: swwan
     * @Date: 2020/10/13
     */
    List<Entrust> queryEntrustByStatus(String userId, String entrustStatus, List<String> ptlAuthList);
}
