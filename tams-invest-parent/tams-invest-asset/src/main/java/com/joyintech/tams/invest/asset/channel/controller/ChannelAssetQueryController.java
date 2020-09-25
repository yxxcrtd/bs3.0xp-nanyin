/*
 * 文件名：ChannelAssetQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.asset.channel.service.ChannelAssetQueryService;
import com.joyintech.tams.invest.asset.channel.service.ChannelAssetService;

/**
 * 通道资产查询
 * @author yangjm
 * @version 1.0 2019年3月25日
 * @see ChannelAssetQueryController
 * @since 1.0
 */
@Controller
@PageId("ChannelAssetQuery")
@RequestMapping("/invest/asset/channel/ChannelAssetQuery")
public class ChannelAssetQueryController extends BaseController{

    /**
     * 通道资产查询-处理详细
     */
    @Autowired
    private ChannelAssetQueryService service ;
    
    /**
     * 界面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
        
    }

    /**
     * 分页查询
     * @return 通道资产分页查询
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChannelAssetDto> findPage() throws Exception{
        return service.selectForPage(RequestUtil.getPageParam());
        
    }
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

}
