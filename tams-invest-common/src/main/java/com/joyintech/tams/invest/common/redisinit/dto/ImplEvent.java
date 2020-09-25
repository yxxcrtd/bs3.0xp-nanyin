/*
 * 文件名：ImplEvent.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEvent;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEventItem;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEventParaRel;

/**
 * 风险事件实现类
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see ImplEvent
 * @since 1.0
 */
public class ImplEvent extends RskEvent implements IEvent {

    private List<IEventItem> iEventItemList = new ArrayList<>();

    private List<IEventParaRel> iEventParaRelList = new ArrayList<>();

    @Override
    public List<IEventItem> getEventItem() {
        return iEventItemList;
    }

    @Override
    public List<IEventParaRel> getEventParaRel() {
        return iEventParaRelList;
    }

    public void setImplEventItemList(List<ImplEventItem> implEventItemList) {
        implEventItemList.forEach(item -> {
            this.iEventItemList.add(item);
        });
    }

    public void setiEventParaRelList(List<ImplEventParaRel> iEventParaRelList) {
        iEventParaRelList.forEach(item -> {
            item.setParaCode(item.getParaCode()+ GlobalConstant.COLON+item.getParaGranularity());
            this.iEventParaRelList.add(item);
        });
    }
}
