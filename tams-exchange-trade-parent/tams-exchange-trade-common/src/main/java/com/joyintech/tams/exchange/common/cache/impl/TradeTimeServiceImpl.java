package com.joyintech.tams.exchange.common.cache.impl;

import com.joyintech.tams.exchange.common.bean.investment.TradeTimeBean;
import com.joyintech.tams.exchange.common.cache.TradeTimeService;
import com.joyintech.tams.exchange.common.constant.joyin.DeclarationType;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: think
 * @Description: 交易时间段服务
 * @Date: Created in 9:12 2020/11/2
 */
@Service
public class TradeTimeServiceImpl implements TradeTimeService {

    private Map<String, TradeTimeBean> tradeTimeBeanMap = new ConcurrentHashMap<>();


    @Override
    public void saveTradeTimeCache(List<TradeTimeBean> tradeTimeBeanList) {
        tradeTimeBeanMap.clear();
        if (Objects.nonNull(tradeTimeBeanList)) {
            tradeTimeBeanList.forEach(tradeTimeBean -> {
                String key = tradeTimeBean.getTradeMarket() + "|" + tradeTimeBean.getTradePlat() + "|"
                        + tradeTimeBean.getDeclarationType() + "|" + tradeTimeBean.getAssetType() + "|"
                        + tradeTimeBean.getAssetType1() + "|" + tradeTimeBean.getAssetType2();
                tradeTimeBeanMap.put(key, tradeTimeBean);
            });
        }
    }

    @Override
    public TradeTimeBean getTradeTimeByInstructionAndEntrustProp(Instruction instruction, String assetType1, String assetType2, String entrustProp) {
        String marketKey = instruction.getTradeMarket() + "|" + instruction.getTradePlat() + "|"
                + DeclarationType.convertEntrustPropToDeclarationType(entrustProp) + "|";
        String assetTypeKey = instruction.getAssetType() + "|"
                + assetType1 + "|" + assetType2;
        int type = 0;
        while (type <= 3) {
            TradeTimeBean tradeTimeBean = tradeTimeBeanMap.get(marketKey + assetTypeKey);
            if (Objects.nonNull(tradeTimeBean)) {
                return tradeTimeBean;
            } else {
                type++;
                assetTypeKey = createKey(instruction.getAssetType(), assetType1, assetType2, type);
            }
        }
        return null;
    }

    private String createKey(String assetType, String assetType1, String assetType2, int type) {
        if (type == 3) {
            assetType = null;
        }
        if (type == 2 || type == 3) {
            assetType1 = null;
        }
        if (type == 1 || type == 2 || type == 3) {
            assetType2 = null;
        }
        return assetType + "|" + assetType1 + "|" + assetType2;
    }
}
