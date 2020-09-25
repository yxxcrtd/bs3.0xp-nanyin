/*
 * 文件名：StlOrderManagermentInterfController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.interf.bankname.order.dto.StlOrderManagement001Dto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 指令接口管理重新发送
 * @author wangqidong
 * @version 1.0 2019年7月11日
 * @see StlOrderManagermentInterfController
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/StlOrderManagermentInterf")
public class StlOrderManagermentInterfController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @PostMapping("resendOrderInfo")
    public Map<String, String> resendOrderInfo(@RequestBody List<String>  orderIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for(String orderId : orderIds){
            logger.debug("发送指令编号：" + orderId); 
            //TODO
            msg  += "发送指令编号: "  + orderId + "成功！<br />";
        }
        res.put("msg", msg);
        return  res;
    }
    
    
    @PostMapping("refreshInfo")
    public Map<String, String> refreshInfo() {
        Map<String, String> res = new HashMap<>();
        String msg = "刷新成功";
        //TODO
        res.put("msg", msg);
        return  res;
    }
    
    @PostMapping("invalidOrderInfo")
    public Map<String, String> invalidOrderInfo(@RequestBody List<String>  orderIds) {
        Map<String, String> res = new HashMap<>();
        String msg = "";
        for(String orderId : orderIds){
            logger.debug("作废指令编号：" + orderId); 
            //TODO
            msg  += "指令编号: "  + orderId + "作废成功！<br />";
        }
        res.put("msg", msg);
        return  res;
    }
}
