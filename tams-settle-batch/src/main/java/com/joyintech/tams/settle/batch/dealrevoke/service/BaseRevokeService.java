package com.joyintech.tams.settle.batch.dealrevoke.service;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/8/4
 * @see BaseRevokeService
 * @since 1.0
 */
public interface BaseRevokeService {

    String REVOKE_BEAN_NAME = "__revokeService";

    /**
     * 交易撤销
     * @param tradeIdList 交易编号
     * @param totalTradeIdList 汇总交易编号
     */
    void doRevoke(List<String> tradeIdList, List<String> totalTradeIdList);

    /**
     * 获取业务类型
     * @return 业务类型
     */
    String getBusiType();
}
