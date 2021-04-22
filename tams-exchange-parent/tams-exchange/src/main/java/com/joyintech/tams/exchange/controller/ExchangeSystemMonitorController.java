package com.joyintech.tams.exchange.controller;

import cn.hutool.core.util.ObjectUtil;
import com.joyintech.tams.exchange.common.bean.DynamicMonitorDTO;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.service.ExchangeSystemMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joyin")
public class ExchangeSystemMonitorController {

    @Autowired
    private ExchangeSystemMonitorService exchangeSystemMonitorService;

    /**
     * 查询监控数据
     *
     * @param userId 用户Id
     * @return ResultBean
     */
    @Deprecated
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public ResultBean queryExchangeMonitor(@RequestParam(value = "user_id") String userId) {
        String param = "?user_id=" + userId;
        return exchangeSystemMonitorService.queryExchangeMonitor(param);
    }

    /**
     * 查询内存数据
     *
     * @param userId      用户Id
     * @param serviceName 内存数据名称  Account BatchInfo Broker Instrument Order Trade Entrust IntegratedEntrust Instruction InstructionPledge
     * @param page        起始页数
     * @param perPage     查询页数
     * @return ResultBean
     */
    @RequestMapping(value = "/monitor/cache", method = RequestMethod.GET)
    public ResultBean queryExchangeCacheMonitor(@RequestParam(value = "user_id") String userId,
                                                @RequestParam(value = "service_name") String serviceName,
                                                @RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "count", defaultValue = "1000") int perPage) {
        String param = "?user_id=" + userId + "&service_name=" + serviceName;
        if (ObjectUtil.isNotEmpty(page)) {
            param += "&page=" + page;
        }
        if (ObjectUtil.isNotEmpty(perPage)) {
            param += "&count=" + perPage;
        }
        return exchangeSystemMonitorService.queryCacheExchangeMonitor(param);
    }

    /**
     * 保存监控数据
     */
    @RequestMapping(value = "/monitor/cache", method = RequestMethod.PUT)
    public ResultBean addExchangeCacheMonitor(@RequestParam(value = "user_id") String userId,
                                              @RequestParam(value = "service_name") String serviceName,
                                              @RequestBody String beanInfoList) {
        String param = "?user_id=" + userId + "&service_name=" + serviceName;
        return exchangeSystemMonitorService.addCacheExchangeMonitor(param, beanInfoList);
    }

    /**
     * 数据覆盖
     */
    @RequestMapping(value = "/monitor/cache/recover", method = RequestMethod.POST)
    public ResultBean recoverExchangeCacheMonitor(@RequestParam(value = "user_id") String userId,
                                                  @RequestParam(value = "service_name") String serviceName,
                                                  @RequestParam(value = "id", required = false) String id,
                                                  @RequestParam(value = "type", required = false, defaultValue = "0") int coverType) {
        String param = "?user_id=" + userId + "&service_name=" + serviceName;
        if (ObjectUtil.isNotEmpty(id)) {
            param += "&id=" + id;
        }
        if (ObjectUtil.isNotEmpty(coverType)) {
            param += "&type=" + coverType;
        }
        return exchangeSystemMonitorService.recoverCacheExchangeMonitor(param);
    }

    /**
     * 数据清空
     */
    @RequestMapping(value = "/monitor/cache", method = RequestMethod.DELETE)
    public ResultBean clearExchangeCacheMonitor(@RequestParam(value = "user_id") String userId,
                                                @RequestParam(value = "service_name") String serviceName) {
        String param = "?user_id=" + userId + "&service_name=" + serviceName;
        return exchangeSystemMonitorService.clearCacheExchangeMonitor(param);
    }

    /**
     * 查询动态监控数据
     *
     * @param userId 用户Id
     * @return ResultBean
     */
    @GetMapping(TradeServiceURL.querySystemMonitorDynamic)
    public ResultBean queryExchangeMonitorDynamic(@RequestParam(value = "user_id") String userId,
                                                  @RequestParam(value = "category_name", required = false) String categoryName,
                                                  @RequestParam(value = "snapshot_time", required = false) Long snapshotTime) {
        DynamicMonitorDTO dto = new DynamicMonitorDTO();
        if (ObjectUtil.isNotEmpty(categoryName)) {
            dto.setCategoryName(categoryName);
        }
        if (ObjectUtil.isNotEmpty(snapshotTime)) {
            dto.setSnapshotTime(snapshotTime);
        }
        return exchangeSystemMonitorService.queryExchangeMonitorDynamic(dto);
    }

    /*** 
     * @Description: 删除数据
     * @Param: [userId, serviceName, id] 
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/11/26 
     */
    @RequestMapping(value = "/monitor/cache/delete", method = RequestMethod.DELETE)
    public ResultBean deleteExchangeCacheMonitor(@RequestParam(value = "user_id") String userId,
                                                 @RequestParam(value = "service_name") String serviceName,
                                                 @RequestBody List<String> id) {
        if (id == null || id.size() == 0) {
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        }
        String param = "?user_id=" + userId
                + "&service_name=" + serviceName;
        return exchangeSystemMonitorService.deleteExchangeCacheMonitor(param, id);
    }

    /***
     * @Description: 内存数据
     * @Param: []
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/11/26
     */
    @RequestMapping(value = "/cache/monitor", method = RequestMethod.GET)
    public ResultBean queryExchangeCacheMonitor() {
        return exchangeSystemMonitorService.queryExchangeCacheMonitor();
    }
}
