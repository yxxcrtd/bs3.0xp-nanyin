package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameterExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.AccountRiskParameterMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.service.AccountRiskParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountRiskParameterServiceImpl extends AsyncServiceImpl<String, AccountRiskParameter> implements AccountRiskParameterService {
    private static final Logger logger = LoggerFactory.getLogger(AccountRiskParameterServiceImpl.class);

    @Autowired
    private AccountRiskParameterMapper accountRiskParameterMapper;

    private final String defaultAccountRiskId = "0";

    private Map<String, AccountRiskParameter> accountRiskParameterMap = Maps.newConcurrentMap();

    @Override
    public void coverCache(boolean fromRedis) {
        // 设置一个默认的风控参数accountRiskParameterMap
        clearCache();
        List<AccountRiskParameter> accountRiskParameterList = fromRedis ? getAllFromRedis() : accountRiskParameterMapper.selectByExample(null);
        if (Objects.nonNull(accountRiskParameterList)) {
            Map<String, AccountRiskParameter> riskParameterMap = accountRiskParameterList.stream().collect(Collectors.toMap(AccountRiskParameter::getAccountId, accountRiskParameter -> accountRiskParameter));
            AccountRiskParameter accountRiskParameter = riskParameterMap.get(defaultAccountRiskId);
            if (Objects.isNull(accountRiskParameter)) {
                accountRiskParameter = new AccountRiskParameter(defaultAccountRiskId, 2000, 20000, 0.5, true);
                accountRiskParameterList.add(accountRiskParameter);
            }
            logger.info("更新账户风控参数：{}", accountRiskParameterList.size());
            updateCache(accountRiskParameterList);
        }
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        accountRiskParameterMap.clear();
    }

    @Override
    protected void update(AccountRiskParameter accountRiskParameter) {
        accountRiskParameterMap.put(accountRiskParameter.getAccountId(), accountRiskParameter);
    }

    @Override
    protected void save(AccountRiskParameter accountRiskParameter) {
        update(accountRiskParameter);
    }

    @Override
    protected void delete(String PK) {
        if (!defaultAccountRiskId.equals(PK))
            accountRiskParameterMap.remove(PK);
    }

    protected String genDbId() {
        return null;
    }

    @Override
    public AccountRiskParameter queryAccountRiskParameterByAccountId(String accountId) {
        AccountRiskParameter accountRiskParameter = accountRiskParameterMap.get(accountId);
        if (Objects.isNull(accountRiskParameter))
            accountRiskParameter = accountRiskParameterMap.get(defaultAccountRiskId);// 默认参数
        return accountRiskParameter;
    }

    @Override
    public IMybatisMapper<AccountRiskParameter, String, AccountRiskParameterExample> getMapper() {
        return accountRiskParameterMapper;
    }
}
