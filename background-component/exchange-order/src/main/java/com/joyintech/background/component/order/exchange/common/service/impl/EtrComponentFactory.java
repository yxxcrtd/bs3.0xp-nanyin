/*
 * 文件名：EtrComponentFactory.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.joyintech.background.component.order.exchange.common.dto.EtrBaseDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.exception.EtrComponentException;
import com.joyintech.background.component.order.exchange.common.service.EtrComponentCommService;

/**
 * 接口工厂
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月13日
 * @see EtrComponentFactory
 * @since 1.0
 */
public final class EtrComponentFactory {

    private EtrComponentFactory() {
    }

    private static final Map<Class<? extends EtrBaseDto>, EtrComponentCommService<? extends EtrBaseDto>> commonMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static final <V extends EtrBaseDto> EtrComponentCommService<V> getComponentService(Class<V> clz) {
        return (EtrComponentCommService<V>)commonMap.get(clz);
    }

    /**
     * 取得组件Service<br>
     * 调用示例：<br>
     * <B>EtrComponentCommService<V> service =
     * EtrComponentFactory.getComponentService(V.class,</B><br>
     * &nbsp;&nbsp;&nbsp;&nbsp;<B>settings -> {</B><br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<B>settings.setMidDiffPrice(minDiffPrice);</B><br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;......<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;<B>});</B><br>
     * 
     * @param clz 组件Dto类型
     * @param userConsumer 设置配置信息 <必须> 若设置NULL会抛出 {@link EtrComponentException}异常
     * @return 组件Service
     */
    public static final <V extends EtrBaseDto> EtrComponentCommService<V> getComponentService(Class<V> clz,
                                                                                              Consumer<EtrParameterDto> userConsumer) {
        EtrComponentCommService<V> service = getComponentService(clz);
        EtrParameterDto dto = new EtrParameterDto();
        if (userConsumer == null) {
            throw new EtrComponentException("必须设置用户信息");
        }
        userConsumer.accept(dto);
        return service;
    }

    static final <V extends EtrBaseDto> EtrComponentCommService<V> put(Class<V> clz,
                                                                       EtrComponentCommService<V> componentService) {
        commonMap.put(clz, componentService);
        return componentService;
    }
}
