/*
 * 文件名：OrderController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.interf.core.controller.BaseController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i3005.service.Interf3005Service;
import com.joyintech.service.interf.icusdep1020.service.InterfCusdep1020service;
import com.joyintech.service.interf.icusdep1207.service.InterfCUSDEP1207Service;
import com.joyintech.service.interf.order.dto.CusdepOrderDto;

/**
 * 对接托管网银系统接口入口
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月12日
 * @see OrderController
 * @since 1.0
 */
@RestController
public class OrderController extends BaseController<CusdepOrderDto> {

    /**
     * 接口CUSDEP1020服务
     */
    @Autowired
    private InterfCusdep1020service cusdep1020Service;

    /**
     * 接口CUSDEP1207服务
     */
    @Autowired
    private InterfCUSDEP1207Service cusdep1207Service;

    /**
     * 接口3005服务
     */
    @Autowired
    private Interf3005Service i3005Service;

    /**
     * 处理类型发送指令
     */
    private static final String DO_TYPE_ORDER = "1";

    /**
     * 处理类型查询状态
     */
    private static final String DO_TYPE_STATUS = "2";

    @RequestMapping("/tzConsumer/doCusdep")
    public CusdepOrderDto doCusdep(@RequestBody CusdepOrderDto param) {
        excuteService(param);
        return param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(CusdepOrderDto dto, SendEsbService<?, ?> service) {
        dto.setResult(service.isHandleOK());
        dto.setErrMsg(service.getErrMsg());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(CusdepOrderDto dto) {
        if (DO_TYPE_ORDER.equals(dto.getInterfType())) {
            return cusdep1020Service;
        }
        else if (DO_TYPE_STATUS.equals(dto.getInterfType())) {
            return i3005Service;
        }
        return cusdep1207Service;
    }
}
