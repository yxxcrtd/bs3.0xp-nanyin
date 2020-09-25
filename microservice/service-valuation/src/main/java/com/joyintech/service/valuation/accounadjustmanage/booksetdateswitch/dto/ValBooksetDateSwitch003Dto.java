/*
 * 文件名：CustomerReportConfig002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.validator.ValBooksetDateSwitch003Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author zhangwei
 * @version 1.0 2019年2月18日
 * @see ValBooksetDateSwitch003Dto
 * @since 1.0
 */
@PageId("ValBooksetDateSwitch002")
@ValidLogic(targetClass = ValBooksetDateSwitch003Validator.class, groups = {
    ValidateGroupEdit.class})
public class ValBooksetDateSwitch003Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value = Field.PK_1)
    private String booksetId;

    /**
     * 账套日期
     */
    private String booksetDate;

    /**
     * 账套名
     */
    private String booksetName;

    /**
     * 批量选择用户
     */
    private transient List<ValBooksetDateSwitch003Dto> selectedItem;

    /**
     * 获取 booksetName
     * 
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 booksetName
     * 
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
    }

    public List<ValBooksetDateSwitch003Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ValBooksetDateSwitch003Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public String toString() {
        return "ValBooksetDateSwitch003Dto [booksetId=" + booksetId + ", booksetDate=" + booksetDate + ", booksetName="
               + booksetName + "]";
    }

}
