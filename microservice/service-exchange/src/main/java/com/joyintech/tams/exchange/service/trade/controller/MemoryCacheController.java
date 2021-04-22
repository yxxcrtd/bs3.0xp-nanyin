package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.trade.management.service.MemoryCacheManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/joyin")
public class MemoryCacheController {
    @Autowired
    private MemoryCacheManagement memoryCacheManagement;

    //    @Permission(value = {UserType.SUPER_ADMINISTRATOR})
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
}
