/*
 * 文件名：MarketCalendarInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.framework.util.DateUtil;

/**
 * 节假日表dto
 * 
 * @author xiexinting
 * @version 1.0 2018年8月3日
 * @see ComMarketCalendarInfo001Dto
 * @since 1.0
 */
public class ComMarketCalendarInfo001Dto extends MstHoliday{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5811084101048852454L;

    /**
     * 日历名
     */
    private String calendarName;
    /**
     * 获取是否
     */
    private long yorn;

    
    /**
     * 获取 calendarName
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    
    /**
     * 设置 calendarName
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    /**
     * 获取 yorn
     * 
     * @return yorn.
     */
    public long getYorn() {
        return yorn;
    }

    /**
     * 设置 yorn
     * 
     * @param yorn yorn
     */
    public void setYorn(long yorn) {
        this.yorn = yorn;
    }

    /**
     * 获取 节假日标志
     * 
     * @return 节假日标志
     */
    public String getIsHolidayStr() {
        return super.getIsHoliday() != 0 ? "是":"否";
    }

    /**
     * 取得 日历编号
     * 
     * @return 日历编号
     */
    @Override
    public String getCalendarId() {
        return super.getCalendarId();
    }

    /**
     * 取得 日期
     * 
     * @return 日期
     */
    public String getCdateStr() {
        return DateUtil.format(super.getCdate());
    }

    /**
     * 选中的数据
     */
    private transient List<ComMarketCalendarInfo001Dto> selectedItem;

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<ComMarketCalendarInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ComMarketCalendarInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

}
