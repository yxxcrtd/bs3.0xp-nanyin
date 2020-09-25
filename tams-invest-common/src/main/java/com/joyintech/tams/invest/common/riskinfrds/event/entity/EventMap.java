/*
 * 文件名：EventMap.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 风险事件列表存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月27日
 * @see EventMap
 * @since 1.0
 */
public class EventMap implements IBusiness {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4279851064736490452L;

    /**
     * 风险事件列表
     */
    private Map<String, IEvent> eveMap = new HashMap<>();

    /**
     * 存储Field
     */
    public static final String ALL = "ALL";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        return RedisKeyEnum.风险事件配置.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getField() {
        return ALL;
    }

    /**
     * 获取 风险事件列表
     * 
     * @return 风险事件列表.
     */
    public Map<String, IEvent> getEventMap() {
        return eveMap;
    }

    /**
     * 设置 风险事件列表
     * 
     * @param eveMap 风险事件列表
     */
    public void setEventMap(Map<String, IEvent> eveMap) {
        this.eveMap = eveMap;
    }

    public Collection<IEvent> values() {
        return this.eveMap.values();
    }

    public void add(IEvent event) {
        String code = event.getEventCode();
        this.eveMap.put(code, event);
    }

    public IEvent get(String code) {
        return this.eveMap.get(code);
    }
}
