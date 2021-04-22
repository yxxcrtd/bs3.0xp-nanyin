package com.joyintech.tams.exchange.controller;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.ExchangeCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/joyin")
public class ExchangeCounterController {

    @Autowired
    private ExchangeCounterService exchangeCounterService;

    /*** 
     * @Description: 查询交易对手信息
     * @Param: [counterId]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/10/27 
     */
    @RequestMapping(value = "/counter/counterPartyInfo", method = RequestMethod.GET)
    @CrossOrigin
    public ResultBean counterPartyInfo(@RequestParam(value = "counter_id", required = false) String counterId) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(counterId)) {
            pageParam.put("counterId", counterId);
        }
        return exchangeCounterService.queryCounterPartyInfo(pageParam);
    }

    /*** 
     * @Description: 查询交易对手席位信息
     * @Param: [counterId, counterSeatId] 
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30 
     */
    @RequestMapping(value = "/counter/counterSeatInfo", method = RequestMethod.GET)
    @CrossOrigin
    public ResultBean queryCounterSeatInfo(@RequestParam(value = "counter_id", required = false) String counterId,
                                           @RequestParam(value = "counter_seat_id", required = false) String counterSeatId) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(counterId)) {
            pageParam.put("counterId", counterId);
        }
        if (!Strings.isNullOrEmpty(counterSeatId)) {
            pageParam.put("counterSeatId", counterSeatId);
        }
        return exchangeCounterService.queryCounterSeatInfo(pageParam);
    }

    /*** 
     * @Description: 查询交易对手交易员信息
     * @Param: [counterId, counterTraderId] 
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30 
     */
    @RequestMapping(value = "/counter/counterTraderInfo", method = RequestMethod.GET)
    @CrossOrigin
    public ResultBean queryCounterTraderInfo(@RequestParam(value = "counter_id", required = false) String counterId,
                                             @RequestParam(value = "counter_trader_id", required = false) String counterTraderId) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(counterId)) {
            pageParam.put("counterId", counterId);
        }
        if (!Strings.isNullOrEmpty(counterTraderId)) {
            pageParam.put("counterTraderId", counterTraderId);
        }
        return exchangeCounterService.queryCounterTraderInfo(pageParam);
    }

    /**
     * 本方交易员查询接口
     *
     * @param counterTraderId 本方交易员编号
     * @return ResultBean
     */
    @RequestMapping(value = "/counter/our/trader", method = RequestMethod.GET)
    @CrossOrigin
    public ResultBean queryOurTraderInfo(@RequestParam(value = "our_trader_id", required = false) String counterTraderId) {
        // 该接口和查询对手方交易员为同一个， 此处字段为固定入参
        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("counterId", "homeTrader");
        if (!Strings.isNullOrEmpty(counterTraderId)) {
            pageParam.put("counterTraderId", counterTraderId);
        }
        return exchangeCounterService.queryCounterTraderInfo(pageParam);
    }

    /*** 
     * @Description: 新增交易对手席位
     * @Param: [counterId, counterSeatId] 
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30 
     */
    @RequestMapping(value = "/counter/addCounterSeat", method = RequestMethod.POST)
    @CrossOrigin
    public ResultBean addCounterSeat(@RequestParam(value = "counter_id") String counterId,
                                     @RequestParam(value = "counter_seat_id") String counterSeatId) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        if (Strings.isNullOrEmpty(counterId)) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "交易对手代码不可以为空！");
        }
        if (Strings.isNullOrEmpty(counterSeatId)) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "交易对手席位代码不可以为空！");
        }
        pageParam.put("counterId", counterId);
        pageParam.put("counterSeatId", counterSeatId);
        return exchangeCounterService.addCounterSeat(pageParam);
    }

    /*** 
     * @Description: 新增交易对手交易员
     * @Param: [counterId, counterTraderId] 
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/9/30 
     */
    @RequestMapping(value = "/counter/addCounterTrader", method = RequestMethod.POST)
    @CrossOrigin
    public ResultBean addCounterTrader(@RequestParam(value = "counter_id") String counterId,
                                       @RequestParam(value = "counter_trader_id") String counterTraderId) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        if (Strings.isNullOrEmpty(counterId)) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "交易对手代码不可以为空！");
        }
        if (Strings.isNullOrEmpty(counterTraderId)) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "交易对手交易员代码不可以为空！");
        }
        pageParam.put("counterId", counterId);
        pageParam.put("counterTraderId", counterTraderId);
        return exchangeCounterService.addCounterTrader(pageParam);
    }

    /**
     * 新增本方交易员信息
     *
     * @param counterTraderId 本方交易员编号
     * @return ResultBean
     */
    @RequestMapping(value = "/counter/add/our/trader", method = RequestMethod.POST)
    @CrossOrigin
    public ResultBean addOurTrader(@RequestParam(value = "our_trader_id") String counterTraderId) {
        Map<String, Object> pageParam = new HashMap<>();
        if (Strings.isNullOrEmpty(counterTraderId)) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, "交易对手交易员代码不可以为空！");
        }
        // 该处接口调用和新增对手交易员一致， 但此处字段为固定入参，标识为新增本方交易员
        pageParam.put("counterId", "homeTrader");
        pageParam.put("counterTraderId", counterTraderId);
        return exchangeCounterService.addCounterTrader(pageParam);
    }
}