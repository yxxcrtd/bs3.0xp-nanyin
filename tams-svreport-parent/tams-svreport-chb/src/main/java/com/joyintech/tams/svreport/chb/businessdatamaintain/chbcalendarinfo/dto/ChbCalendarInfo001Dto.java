/*
 * 文件名：MarketCalendarInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 节假日表dto 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月3日
 * @see ChbCalendarInfo001Dto
 * @since 1.0
 */
@PageId("ChbCalendarInfo001")
public class ChbCalendarInfo001Dto extends MstHoliday implements ProcessEntity {

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
    private Integer yorn;
    /**
     * 数据来源
     */
    private String dataSource;

    private String isHolidayStr;

    private String updateTimeStr;

    /**
     * 错误信息
     */
    private String  errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 获取 yorn
     * 
     * @return yorn.
     */
    public Integer getYorn() {
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
    public void setYorn(Integer yorn) {
        this.yorn = yorn;
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
    private transient List<ChbCalendarInfo001Dto> selectedItem;

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<ChbCalendarInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ChbCalendarInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getIsHolidayStr() {
        return isHolidayStr;
    }

    public void setIsHolidayStr(String isHolidayStr) {
        this.isHolidayStr = isHolidayStr;
    }
}
