package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

/**
 * @Author: think
 * @Description: 持仓处理service
 * @Date: Created in 18:04 2020/11/9
 */
public interface PositionBeanService extends AsyncService<String, PositionBean> {

    PositionBean getPositionBean(String accountId, String stockCode);

}
