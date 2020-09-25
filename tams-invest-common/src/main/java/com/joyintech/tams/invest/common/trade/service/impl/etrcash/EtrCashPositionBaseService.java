package com.joyintech.tams.invest.common.trade.service.impl.etrcash;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-10-12 15:34
 **/
public class EtrCashPositionBaseService {
    /**
     * 共通Service
     */
    static EtrCommonCashPositionDealService etrCommonCashPositionDealService;

    /**
     * 获取 etrCommonCashPositionDealService
     *
     * @return etrCommonCashPositionDealService
     */
    @Autowired
    public void setEtrCommonCashPositionDealService(EtrCommonCashPositionDealService etrCommonCashPositionDealService) {
        EtrCashPositionBaseService.etrCommonCashPositionDealService = etrCommonCashPositionDealService;
    }
}
