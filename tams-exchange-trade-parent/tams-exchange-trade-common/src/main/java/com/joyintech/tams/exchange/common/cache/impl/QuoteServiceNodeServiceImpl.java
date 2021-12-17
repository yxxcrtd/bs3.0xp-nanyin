package com.joyintech.tams.exchange.common.cache.impl;

import com.joyintech.tams.exchange.common.bean.cache.QuoteServiceNode;
import com.joyintech.tams.exchange.common.cache.QuoteServiceNodeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 14:09 2021/8/16
 */
@Service
public class QuoteServiceNodeServiceImpl extends RedisCacheServiceImpl<String, QuoteServiceNode> implements QuoteServiceNodeService {


    @Override
    public void saveOrUpdateQuoteServiceNode(QuoteServiceNode serviceNode) {
        updateCache(serviceNode);
    }

    @Override
    public QuoteServiceNode queryQuoteSerNodeFromRedisByName(String quoteServiceNodeName) {
        return memory_cache.get(quoteServiceNodeName);
    }

    @Override
    public List<QuoteServiceNode> findAllFromRedis() {
        return getAllFromRedis();
    }

    @Override
    public void coverCache(boolean fromRedis) {
        className = QuoteServiceNode.class.getName();
    }

    @Override
    public void clearCache() {

    }

    @Override
    protected void update(QuoteServiceNode serviceNode) {

    }

    @Override
    protected void save(QuoteServiceNode serviceNode) {

    }

    @Override
    protected void delete(String PK) {

    }
}
