package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;

public interface IntegratedEntrustService extends AsyncService<String, IntegratedEntrust> {

    List<IntegratedEntrust> queryByUserId(String userId);

    List<IntegratedEntrust> queryIntegratedEntrustByOrderId(String orderId);

    List<IntegratedEntrust> queryIntegratedEntrustByInstructionId(String instructionId);

    List<IntegratedEntrust> queryIntegratedEntrustByAccountId(String accountId);

    IntegratedEntrust queryIntegratedEntrustByAccountIdAndBrokerEntrustId(String accountId, String brokerEntrustId);

    IntegratedEntrust queryIntegratedEntrustByLocalEntrustIdAndAccount(String localEntrustId, String accountId);

    PageInfo<IntegratedEntrust> queryAllForPage(int pageNum, int pageSize, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList);

    /**
     * 查询 普通委托 和 综合业务委托 总和
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param accountId
     * @param portfolioId
     * @param market
     * @param stockCode
     * @param entrustProp
     * @param entrustStatus
     * @return
     */
    PageInfo<IntegratedEntrust> queryAllEntrustForPage(int pageNum, int pageSize, String userId, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList);

    String genDbId();

    void clearCacheBySettle(String date);

    void fillPlatDate(String date);

    /*** 
     * @Description: 根据原委托获取撤单委托
     * @Param: [origEntrustId, accountId] 
     * @return: java.util.List<com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust>
     * @Author: MAXX
     * @Date: 2020/9/1 
     */
    List<IntegratedEntrust> queryCancelIntegratedEntrustByOrigEntrustIdAndAccount(String origEntrustId, String accountId);

    /***
     * @Description: 根据委托状态获取委托
     * @Param: [userId, entrustStatusList]
     * @return: java.util.List<com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust>
     * @Author: swwan
     * @Date: 2020/10/13
     */
    List<IntegratedEntrust> queryIntegratedEntrustByStatus(String userId, String entrustStatusList, List<String> ptlAuthList);
}
