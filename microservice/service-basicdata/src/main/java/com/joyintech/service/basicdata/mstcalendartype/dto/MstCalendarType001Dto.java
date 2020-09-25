/*
 * 文件名：MstCalendarType001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstcalendartype.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstTradeCalendar;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 日历类型dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月15日
 * @see MstCalendarType001Dto
 * @since 1.0
 */
@PageId("MstCalendarType002")
public class MstCalendarType001Dto extends MstTradeCalendar implements ITableRow{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作类型
     */
    private String operateType;    
    /**
     * 界面类型
     */
    private String pageModel;   
    /**
     * 选中的数据列表
     */
    private transient List<MstCalendarType001Dto> selectedItem;    
    /**
     * 画面元素
     */
    private String elementId;
    
    /**
     * 日历编号
     */
    private String calendarCode;

    /**
     * 日历名称
     */
    private String calendarName;
    
    /**
     * 日历名称
     */
    private String calendarType;

    /**
     * 第三方日历编号，比如万得的交易场所
     */
    private String otherId;
    
    /**
     * 是否是交易日历
     */
    private String isTradeCalendar;
    
    /**
     * 日历类型列表
     */
    private List<String> calendarTypeList;
    
    /**
     * 备注
     */
    private String remark;

    /**
     * 日期
     */
    private Date cDate ;

    /**
     * 节假日标志
     */
    private String isHoliday ;
    /**
     * 取得 交易日历代码
     * @return 交易日历代码
     */
    public String getTradeCalendarId() {
        return super.getTradeCalendarId();
    }
    
    /**
     * 取得 日历编码
     * @return 日历编码
     */
    public String getCalendarId() {
        return super.getCalendarId();
    }
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<MstCalendarType001Dto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstCalendarType001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarCode() {
        return calendarCode;
    }

    
    /**
     * 设置 calendarCode
     * @param calendarCode 日历编码
     */
    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    
    /**
     * 获取 calendarName
     * @return calendarName.
     */
    @Length(fieldName = "MstCalendarType001.calendarName", max = 60)
    @NotEmpty(fieldName = "MstCalendarType001.calendarName",groups={ValidateGroupEdit.class})
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
     * 获取 otherId
     * @return otherId.
     */
    public String getOtherId() {
        return otherId;
    }

    
    /**
     * 设置 otherId
     * @param otherId otherId
     */
    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    
    /**
     * 获取 isTradeCalendar
     * @return isTradeCalendar.
     */
    public String getIsTradeCalendar() {
        return isTradeCalendar;
    }

    
    /**
     * 设置 isTradeCalendar
     * @param isTradeCalendar isTradeCalendar
     */
    public void setIsTradeCalendar(String isTradeCalendar) {
        this.isTradeCalendar = isTradeCalendar;
    }

    
    /**
     * 获取 calendarType
     * @return calendarType.
     */
    @NotEmpty(fieldName = "MstCalendarType002.calendarTypeInclude",groups={ValidateGroupEdit.class})
    public String getCalendarType() {
        return calendarType;
    }
   
    /**
     * 设置 calendarType
     * @param calendarType calendarType
     */
    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    
    /**
     * 获取 calendarTypeList
     * @return calendarTypeList.
     */
    public List<String> getCalendarTypeList() {
        return calendarTypeList;
    }

    
    /**
     * 设置 calendarTypeList
     * @param calendarTypeList calendarTypeList
     */
    public void setCalendarTypeList(List<String> calendarTypeList) {
        this.calendarTypeList = calendarTypeList;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */ 
    @Length(fieldName = "MstCalendarType001.remark", max = 300)
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 cDate
     *
     * @return cDate
     */
    @NotEmpty(fieldName = "MstCalendarType004.cDate",groups={ValidateGroupEdit.class})
    public Date getcDate() {
        return cDate;
    }

    /**
     * 设置 cDate
     *
     * @param cDate cDate
     */
    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    /**
     * 获取 isHoliday
     *
     * @return isHoliday
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 isHoliday
     *
     * @param isHoliday isHoliday
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }
}
