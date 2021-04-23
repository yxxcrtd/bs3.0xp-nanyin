package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.db.domain.PositionBeanExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.PositionBeanMapper;
import com.joyintech.tams.exchange.datacommon.db.service.PositionBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: think
 * @Description: 持仓管理实现
 * @Date: Created in 18:05 2020/11/9
 */
@Service
public class PositionBeanServiceImpl extends AsyncServiceImpl<String, PositionBean> implements PositionBeanService {
    @Autowired
    private PositionBeanMapper positionBeanMapper;

    Map<String, Map<String, PositionBean>> accountPositionMap = new ConcurrentHashMap<>();

    public void clearPosition() {
        logger.info("清空账户持仓信息... {}", accountPositionMap.size());
        accountPositionMap.clear();
    }

    public PositionBean getPositionBean(String accountId, String stockCode) {
        Map<String, PositionBean> positionBeanMap = accountPositionMap.get(accountId);
        if (Objects.nonNull(positionBeanMap)) {
            return positionBeanMap.get(stockCode);
        }
        return null;
    }

    @Override
    public IMybatisMapper<PositionBean, String, PositionBeanExample> getMapper() {
        return positionBeanMapper;
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        accountPositionMap.clear();
    }

    @Override
    protected void update(PositionBean positionBean) {
        Map<String, PositionBean> positionBeanMap = accountPositionMap.get(positionBean.getAccountId());
        if (Objects.isNull(positionBeanMap)) {
            positionBeanMap = new ConcurrentHashMap<>();
            accountPositionMap.put(positionBean.getAccountId(), positionBeanMap);
        }
        positionBeanMap.put(positionBean.getStockCode(), positionBean);
    }

    @Override
    protected void save(PositionBean positionBean) {
        update(positionBean);
    }

    @Override
    protected void delete(String PK) {
        PositionBean positionBean = memory_cache.get(PK);
        if (Objects.nonNull(positionBean)) {
            Map<String, PositionBean> positionBeanMap = accountPositionMap.get(positionBean.getAccountId());
            if (Objects.nonNull(positionBeanMap)) {
                positionBeanMap.remove(positionBean.getStockCode());
            }
        }
    }
}
