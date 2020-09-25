/*
 * 文件名：EtrInvestPersonInfo002Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年9月19日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.BussChannelCommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通道相关共通
 * 
 * @author Johnnie
 * @version 1.0 2019年3月26日
 * @see BussChannelCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussChannelCommon")
public class BussChannelCommonController extends BaseController {

    @Autowired
    private BussChannelCommonService bussChannelCommonService;
    /**
     * 模糊查询通道代码
     *
     * @return 通道代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannelId")
    @ResponseBody
    public Page<SelectOptionDto> getChannelId() {
        return bussChannelCommonService.getChannelId(RequestUtil.getPageParam());
    }

    /**
     * 模糊查询通道代码与通道收取方式
     *
     * @return 通道代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannelIdAndCollectType")
    @ResponseBody
    public Page<SelectOptionDto> getChannelIdAndCollectType() {
        return bussChannelCommonService.getChannelIdAndCollectType(RequestUtil.getPageParam());
    }
    
    /**
     * 模糊查询通道代码
     *
     * @return 通道代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannelIdForAsset")
    @ResponseBody
    public List<SelectOptionDto> getChannelIdForAsset() {
        return bussChannelCommonService.getChannelIdForAsset(RequestUtil.getPageParam());
    }

    /**
     * 模糊查询通道代码
     *
     * @return 通道代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getChannelIdAndCollectTypeForAsset")
    @ResponseBody
    public List<SelectOptionDto> getChannelIdAndCollectTypeForAsset() {
        return bussChannelCommonService.getChannelIdAndCollectTypeForAsset(RequestUtil.getPageParam());
    }

    /**
     * 模糊查询通道代码(划款方式为一次性划款)
     *
     * @return 通道代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getYcxhkChannelId")
    @ResponseBody
    public Page<SelectOptionDto> getYcxhkChannelId() {
        return bussChannelCommonService.getYcxhkChannelId(RequestUtil.getPageParam());
    }

    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public Page<SelectOptionDto> getSecManageAcctId() {
        return bussChannelCommonService.getSecManageAcctId(RequestUtil.getPageParam());
    }
}
