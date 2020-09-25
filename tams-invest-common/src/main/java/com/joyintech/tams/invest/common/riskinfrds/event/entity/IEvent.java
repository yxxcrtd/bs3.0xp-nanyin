/*
 * 文件名：IEvent.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;

/**
 * 风险事件存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月14日
 * @see IEvent
 * @since 1.0
 */
public interface IEvent extends Serializable {

    /**
     * 委托/交易数据是否能匹配该风险事件
     * 
     * @param mapData 委托/交易数据
     * @param calendar 日历信息
     * @return true:能匹配该风险事件，false:不能
     * @see
     * @since 1.0
     */
    default boolean match(Map<String, Object> mapData, ICalendar calendar) {
        List<IEventItem> items = getEventItem();
        if (StringUtil.isEmptyList(items) || mapData == null) {
            return false;
        }
        Map<String, Boolean> groupMap = new HashMap<>();
        items.forEach(item -> groupMap.put(item.getGroupId(), true));
        // 当所有风险事件的特征值都被满足时，则返回匹配成功
        for (IEventItem item : items) {
            // 如果有匹配失败的特征值，则返回false，表示该事件不匹配
            if (!item.match(mapData, calendar)) {
                groupMap.put(item.getGroupId(), false);
            }
        }
        // 如果有任一分组匹配成功则表示该事件被触发返回true
        for (Boolean val : groupMap.values()) {
            if (val) {
                return true;
            }
        }
        // 当所有分组匹配特征值失败时，返回false，表示该事件未被触发
        return false;
    }

    default void setRelValue(Map<String, Object> mapData) {
        List<IEventParaRel> eprList = getEventParaRel();
        if (StringUtil.isEmptyList(eprList)) {
            return;
        }
        eprList.forEach(epr ->{
            //进行自定义列判断
            if(StringUtil.equals(BussinessConstantRds.CUSTOM,epr.getRelItem())){
                epr.setRelValue(StringUtil.isNotEmpty(epr.getCustomVal())?epr.getCustomVal():BigDecimal.ZERO);
            }else {
                epr.setRelValue(new BigDecimal(mapData.getOrDefault(epr.getRelItem(), BigDecimal.ZERO).toString()));
            }
        });
    }

    /**
     * 取得 风险事件代码
     * 
     * @return 风险事件代码
     * @see
     * @since 1.0
     */
    String getEventCode();

    /**
     * 取得 风险事件名称
     * 
     * @return 风险事件名称
     * @see
     * @since 1.0
     */
    String getEventName();

    /**
     * 取得 风险事件特征值列表
     * 
     * @return 风险事件特征值列表
     * @see
     * @since 1.0
     */
    List<IEventItem> getEventItem();

    /**
     * 取得 风险事件与参数之间的关系列表
     * 
     * @return 风险事件与参数之间的关系列表
     * @see
     * @since 1.0
     */
    List<IEventParaRel> getEventParaRel();
    
    /**
     * 是否计算指标
     * 
     * @return Y:是，N:否
     */
    String getIsCalcIndicator();
}
