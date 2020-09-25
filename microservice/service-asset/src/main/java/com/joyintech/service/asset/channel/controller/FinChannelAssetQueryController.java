/*
 * 文件名：ChannelAssetQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.channel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.asset.channel.dto.ChannelAssetDto;
import com.joyintech.service.asset.channel.service.ChannelAssetQueryService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月25日
 * @see FinChannelAssetQueryController
 * @since 1.0
 */
@RestController
@RequestMapping("channelmanageinfo")
public class FinChannelAssetQueryController {
    /**
     * 通道资产查询
     */
    @Autowired
    private ChannelAssetQueryService service;
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChannelAssetDto> selectAll(@RequestBody Map<String, Object> param, 
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return service.selectForPage(page,length,param);
    }
}
