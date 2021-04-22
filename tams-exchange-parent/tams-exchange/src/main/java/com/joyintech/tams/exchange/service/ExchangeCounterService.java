package com.joyintech.tams.exchange.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.CounterSeatInfo;
import com.joyintech.tams.exchange.common.bean.CounterTraderInfo;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.ResultBt;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ExchangeCounterService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(ExchangeCounterService.class);

    /***
     * @Description: 交易对手席位信息查询
     * @Param: [params]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30
     */
    public ResultBean queryCounterSeatInfo(Map<String, Object> params) {
        logger.info("queryCounterSeatInfo params:{}", params);
        try {
            List<CounterSeatInfo> list = restTemplate.postForList(ServiceApplication.BASIC_DATA_SERVICE + TradeServiceURL.queryCounterSeatInfo, params, CounterSeatInfo.class);
            return new ResultBean(ErrorCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("queryCounterSeatInfo:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR.value, e.toString());
        }
    }

    /***
     * @Description: 查询交易对手信息
     * @Param: [params]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/10/27
     */
    public ResultBean queryCounterPartyInfo(Map<String, Object> params) {
        logger.info("queryCounterPartyInfo params:{}", params);
        try {
            List<CounterSeatInfo> list = restTemplate.postForList(ServiceApplication.BASIC_DATA_SERVICE + TradeServiceURL.selectMstCounterParty, params, CounterSeatInfo.class);
            return new ResultBean(ErrorCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("queryCounterPartyInfo:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR.value, e.toString());
        }
    }

    /***
     * @Description: 查询交易对手交易员信息
     * @Param: [params]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30
     */
    public ResultBean queryCounterTraderInfo(Map<String, Object> params) {
        logger.info("queryCounterTraderInfo params:{}", params);
        try {
            List<CounterTraderInfo> list = restTemplate.postForList(ServiceApplication.BASIC_DATA_SERVICE + TradeServiceURL.queryCounterTraderInfo, params, CounterTraderInfo.class);
            if (Objects.nonNull(list)) {
                if ("homeTrader".equals(params.get("counterId"))) {
                    list.forEach(counterTraderInfo -> {
                        counterTraderInfo.setOurTraderId(counterTraderInfo.getCounterTraderId());
                        counterTraderInfo.setCounterTraderId(null);
                    });
                }
            }
            return new ResultBean(ErrorCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("queryCounterTraderInfo:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR.value, e.toString());
        }
    }

    /***
     * @Description: 新增交易对手席位
     * @Param: [params]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30
     */
    public ResultBean addCounterSeat(Map<String, Object> params) {
        logger.info("addCounterSeat params:{}", params);
        try {
            ResultBt resultBt = restTemplate.postForObject(ServiceApplication.BASIC_DATA_SERVICE + TradeServiceURL.addCounterSeat, params, ResultBt.class);
            if (resultBt.getErrorNo().equals(ErrorCode.SUCCESS.value)) {
                return new ResultBean(ErrorCode.SUCCESS);
            }
            return new ResultBean(Integer.parseInt(resultBt.getErrorNo()), resultBt.getErrorInfo());
        } catch (Exception e) {
            logger.error("addCounterSeat:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR.value, e.toString());
        }
    }

    /***
     * @Description: 新增交易对手交易员
     * @Param: [params]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30
     */
    public ResultBean addCounterTrader(Map<String, Object> params) {
        logger.info("addCounterTrader params:{}", params);
        try {
            ResultBt resultBt = restTemplate.postForObject(ServiceApplication.BASIC_DATA_SERVICE + TradeServiceURL.addCounterTrader, params, ResultBt.class);
            if (resultBt.getErrorNo().equals(ErrorCode.SUCCESS.value)) {
                return new ResultBean(ErrorCode.SUCCESS);
            }
            return new ResultBean(Integer.parseInt(resultBt.getErrorNo()), resultBt.getErrorInfo());
        } catch (Exception e) {
            logger.error("addCounterTrader:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR.value, e.toString());
        }
    }
}
