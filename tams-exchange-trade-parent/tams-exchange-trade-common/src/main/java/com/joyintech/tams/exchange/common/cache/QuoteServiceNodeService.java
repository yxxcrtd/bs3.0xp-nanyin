package com.joyintech.tams.exchange.common.cache;

import com.joyintech.tams.exchange.common.bean.cache.QuoteServiceNode;

import java.util.List;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 14:07 2021/8/16
 */
public interface QuoteServiceNodeService extends CacheService<String, QuoteServiceNode> {

    void saveOrUpdateQuoteServiceNode(QuoteServiceNode serviceNode);

    QuoteServiceNode queryQuoteSerNodeFromRedisByName(String quoteServiceNodeName);

    List<QuoteServiceNode> findAllFromRedis();
}
