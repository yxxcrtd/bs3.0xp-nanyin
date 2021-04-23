package com.joyintech.tams.exchange.controller;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.ExchangeCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/joyin")
public class ExchangeCommonController {
    @Autowired
    private ExchangeCommonService commonService;

    /**
     * 公平交易参数查询
     *
     * @param userId 用户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/common/fairtrade", method = RequestMethod.GET)
    public ResultBean queryFairTradeParam(@RequestParam(value = "user_id") String userId) {
        String param = "?user_id=" + userId;
        return commonService.queryFairTradeParam(param);
    }

    /**
     * 市场合约代码查询
     *
     * @param userId 用户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/common/instrument", method = RequestMethod.POST)
    public ResultBean queryInstrument(@RequestParam(value = "user_id") String userId,
                                      @RequestParam(value = "market", required = false) String market,
                                      @RequestBody(required = false) List<String> stockCode) {
        String param = "?user_id=" + userId;
        if (Objects.nonNull(market)) param += "&market=" + market;
        HttpEntity<List<String>> request = new HttpEntity<>(stockCode);
        return commonService.queryInstrument(param, request);
    }

    /**
     * 期货市场合约代码查询
     *
     * @param stockCode 用户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/common/futuresInstrument", method = RequestMethod.POST)
    public ResultBean queryInstrument(@RequestParam(value = "stock_code") String stockCode) {
        String param = "?stock_code=" + stockCode;
        List<String> list = new ArrayList<>();
        list.add("");
        HttpEntity<List<String>> request = new HttpEntity<>(list);
        return commonService.queryFuturesInstrument(param, request);
    }

    /**
     * 当前的master节点查询
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/common/master", method = RequestMethod.GET)
    public ResultBean queryMasterAddress() {
        return commonService.queryMaster();
    }

    /**
     * 修改redis方案参数
     * true 正常情况;异步DB，同步redis；
     * false 该配置提供同步DB，同时跳过redis
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/common/redis/use", method = RequestMethod.POST)
    public ResultBean changeDbType(@RequestParam(value = "user_id") String userId,
                                   @RequestParam(value = "use_redis", required = false) boolean useRedis,
                                   @RequestParam(value = "async_db", required = false) boolean asyncDb) {
        String param = "?user_id=" + userId;
        param += "&use_redis=" + useRedis;
        param += "&async_db=" + asyncDb;
        return commonService.setDataServiceConfig(param);
    }

    /**
     * 获取系统平台日
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/common/sysdate", method = RequestMethod.GET)
    public ResultBean queryInvestSystemDate(@RequestParam(value = "user_id") String userId) {
        String param = "?user_id=" + userId;
        return new ResultBean(ErrorCode.SUCCESS, DateUtil.platDateFromCache());
    }

    @RequestMapping(value = "/common/trade/date", method = RequestMethod.POST)
    public ResultBean changeDbType(@RequestParam(value = "calender_type") String tradeCalendarType,
                                   @RequestParam(value = "current_date") String CDATE,
                                   @RequestParam(value = "days") int NDay) {
        return commonService.getTradeDate(tradeCalendarType, CDATE, NDay);
    }
}
