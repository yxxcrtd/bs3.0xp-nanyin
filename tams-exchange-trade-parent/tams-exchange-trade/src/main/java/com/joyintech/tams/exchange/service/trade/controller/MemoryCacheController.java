package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.service.trade.management.service.MemoryCacheManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade/joyin")
//@Api(tags = "场内交易-内存数据监控接口")
public class MemoryCacheController {
    @Autowired
    private MemoryCacheManagement memoryCacheManagement;

    @RequestMapping(value = "/cache", method = RequestMethod.POST)
    public ResultBean queryCache(@RequestParam(value = "user_id") String userId,
                                 @RequestParam(value = "service_name") String serviceName,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "count", defaultValue = "1000") int perPage) {
        return memoryCacheManagement.getData(serviceName, page, perPage);
    }

    @RequestMapping(value = "/cache/recover", method = RequestMethod.POST)
    public ResultBean recoverCache(@RequestParam(value = "user_id") String userId,
                                   @RequestParam(value = "service_name") String serviceName,
                                   @RequestParam(value = "id", required = false) String id,
                                   @RequestParam(value = "type", required = false, defaultValue = "0") int coverType) {
        return memoryCacheManagement.recoverData(serviceName, id, coverType);
    }

    @RequestMapping(value = "/cache/quote", method = RequestMethod.POST)
    public ResultBean recoverCacheQuoteFromRedis(@RequestParam(value = "user_id") String userId) {
        return memoryCacheManagement.recoverQuoteData();
    }

    @RequestMapping(value = "/cache/clear", method = RequestMethod.POST)
    public ResultBean clearCache(@RequestParam(value = "user_id") String userId,
                                 @RequestParam(value = "service_name") String serviceName) {
        return memoryCacheManagement.clearData(serviceName);
    }

    @RequestMapping(value = "/cache/add", method = RequestMethod.POST)
    public ResultBean addData(@RequestParam(value = "user_id") String userId,
                              @RequestParam(value = "service_name") String serviceName,
                              @RequestBody String beanInfoList) {
        return memoryCacheManagement.addData(serviceName, beanInfoList);
    }

    @RequestMapping(value = "/cache/delete", method = RequestMethod.POST)
    public ResultBean deleteData(@RequestParam(value = "user_id") String userId,
                                 @RequestParam(value = "service_name") String serviceName,
                                 @RequestBody List<String> id) {
        return memoryCacheManagement.deleteData(serviceName, id);
    }

//    @ApiOperation(value = "查询内存数据", notes = "查询内存数据")
//    @RequestMapping(value = "/cache", method = RequestMethod.POST)
//    public Message<Object> queryCache(@RequestParam(value = "user_id") String userId,
//                                      @RequestParam(value = "service_name") String serviceName,
//                                      @RequestParam(value = "page", defaultValue = "1") int page,
//                                      @RequestParam(value = "count", defaultValue = "1000") int perPage) {
//        return new Message<Object>(memoryCacheManagement.getData(serviceName, page, perPage));
//    }
//
//    @ApiOperation(value = "内存数据覆盖", notes = "内存数据覆盖")
//    @RequestMapping(value = "/cache/recover", method = RequestMethod.POST)
//    public Message<Object> recoverCache(@RequestParam(value = "user_id") String userId,
//                                        @RequestParam(value = "service_name") String serviceName,
//                                        @RequestParam(value = "id", required = false) String id,
//                                        @RequestParam(value = "type", required = false, defaultValue = "0") int coverType) {
//        return new Message<Object>(memoryCacheManagement.recoverData(serviceName, id, coverType));
//    }
//
////    @ApiOperation(value = "重新写入行情", notes = "重新写入行情")
////    @RequestMapping(value = "/cache/quote", method = RequestMethod.POST)
////    public ResultBean recoverCacheQuoteFromRedis(@RequestParam(value = "user_id") String userId) {
////        return memoryCacheManagement.recoverQuoteData();
////    }
//
//    @ApiOperation(value = "内存数据清空", notes = "内存数据清空")
//    @RequestMapping(value = "/cache/clear", method = RequestMethod.POST)
//    public Message<Object> clearCache(@RequestParam(value = "user_id") String userId,
//                                      @RequestParam(value = "service_name") String serviceName) {
//        return new Message<Object>(memoryCacheManagement.clearData(serviceName));
//    }
//
//    @ApiOperation(value = "保存监控数据", notes = "保存监控数据")
//    @RequestMapping(value = "/cache/add", method = RequestMethod.POST)
//    public Message<Object> addData(@RequestParam(value = "user_id") String userId,
//                                   @RequestParam(value = "service_name") String serviceName,
//                                   @RequestBody String beanInfoList) {
//        return new Message<Object>(memoryCacheManagement.addData(serviceName, beanInfoList));
//    }
//
//    @ApiOperation(value = "删除监控数据", notes = "删除监控数据")
//    @RequestMapping(value = "/cache/delete", method = RequestMethod.POST)
//    public Message<Object> deleteData(@RequestParam(value = "user_id") String userId,
//                                      @RequestParam(value = "service_name") String serviceName,
//                                      @RequestBody List<String> id) {
//        return new Message<Object>(memoryCacheManagement.deleteData(serviceName, id));
//    }
}
