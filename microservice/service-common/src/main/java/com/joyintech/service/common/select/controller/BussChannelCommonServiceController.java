/*
 * 文件名：TrdAvailableCashQueryController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年10月11日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.controller;

import java.util.Map;

import com.joyintech.service.common.select.service.BussChannelCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

@RestController
@RequestMapping("/common/BussChannelCommonService")
public class BussChannelCommonServiceController {

    @Autowired
    private BussChannelCommonService bussChannelCommonService;

    /**
     * 联想查询通道
     *
     * @param params 画面参数
     * @return 通道代码：下拉框
     */
    @PostMapping("getChannelId/{page}/{length}")
    public Page<SelectOptionDto> getChannelId(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length) {
        return bussChannelCommonService.getChannelId(page, length, params);
    }

    /**
     * 联想查询通道(划款方式为一次性划款)
     *
     * @param params 画面参数
     * @return 通道代码：下拉框
     */
    @PostMapping("getYcxhkChannelId/{page}/{length}")
    public Page<SelectOptionDto> getYcxhkChannelId(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length) {
        return bussChannelCommonService.getYcxhkChannelId(page, length, params);
    }
    
    /**
     * 联想查询通道（通道收取方式）
     *
     * @param params 画面参数
     * @return 通道代码：下拉框
     */
    @PostMapping("getChannelIdAndCollectType/{page}/{length}")
    public Page<SelectOptionDto> getChannelIdAndCollectType(@RequestBody Map<String, Object> params, @PathVariable("page") int page, @PathVariable("length") int length) {
        return bussChannelCommonService.getChannelIdAndCollectType(page, length, params);
    }
    

}
