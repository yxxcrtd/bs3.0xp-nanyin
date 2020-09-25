/*
 * 文件名：MarketCalendarInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstmarketcalendarinfo.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 节假日表dto 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月3日
 * @see MstMarketCalendarInfo001Dto
 * @since 1.0
 */
@PageId("MstMarketCalendarInfo001")
public class MstMarketCalendarInfo001Dto extends MstHoliday implements ProcessEntity {

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
    private String yorn;

    /**
     * 获取 yorn
     * 
     * @return yorn.
     */
    public String getYorn() {
        return yorn;
    }

    /**
     * 获取 calendarName
     * @return calendarName.
     */
    public String getCalendarNameStr() {
        return super.getCalendarId()+" "+calendarName;
    }
    
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
     * 设置 yorn
     * 
     * @param yorn yorn
     */
    public void setYorn(String yorn) {
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
    @PrimaryKey(Field.PK_1)
    public String getCalendarId() {
        return super.getCalendarId();
    }

    /**
     * 取得 日期
     * 
     * @return 日期
     */
    @PrimaryKey(Field.PK_2)
    public String getCdateStr() {
        return DateUtil.format(super.getCdate());
    }

    /**
     * 选中的数据
     */
    private transient List<MstMarketCalendarInfo001Dto> selectedItem;

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<MstMarketCalendarInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstMarketCalendarInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

}
