package com.joyintech.tams.exchange.terminal.trade.ufx.builder;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.terminal.trade.ufx.UfxTradeApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UfxDataSetBuilderFactory {
    private final static Logger logger = LoggerFactory.getLogger(UfxTradeApi.class);

    public static BaseUfxDataSetBuilder genDataSetBuild(String brokeType) {
        if (Strings.isNullOrEmpty(brokeType) || brokeType == BrokeType.GUANGFA.value) {
            return new GuangFaUfxDataSetBuilder();
        } else if (brokeType == BrokeType.OFFICIAL.value) {
            return new OfficialUfxDataSetBuilder();
        } else if (brokeType == BrokeType.GUOJIN.value) {
            return new GuoJinUfxDataSetBuilder();
        } else {
            logger.warn("unknown ufx broker type: {}", brokeType);
            return new GuangFaUfxDataSetBuilder();
        }
    }

}
