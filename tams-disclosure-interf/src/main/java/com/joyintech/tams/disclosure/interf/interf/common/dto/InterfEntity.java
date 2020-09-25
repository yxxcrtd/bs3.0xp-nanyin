/*
 * 文件名：InterfEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;


/**
 * 接口基础Entity
 * 
 * @author lihuadong
 * @version 1.0 2017年2月3日
 * @see InterfEntity
 * @since 1.0
 */
public class InterfEntity extends BaseEntity {

    /**
     * 资讯对应的表名
     */
    private String tableName;

    /**
     * 系统日期 yyyymmdd
     */
    private String sysDate;

    /**
     * 更新时间
     */
    private Date lasttime;

    /**
     * XFUNDS流水号(外汇)
     */
    private String xfundsId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1483251481810299704L;

    /**
     * 获取 xfundsId
     * 
     * @return xfundsId.
     */
    public String getXfundsId() {
        return xfundsId;
    }

    /**
     * 设置 xfundsId
     * 
     * @param xfundsId xfundsId
     */
    public void setXfundsId(String xfundsId) {
        this.xfundsId = xfundsId;
    }

    /**
     * 对应[X03]分隔符文件的字段
     * 
     * @return String[] 字段列表
     * @see
     * @since 1.0
     */
    public String[] getArrayFortxt() {
        return new String[1];
    }

    /**
     * 对应标签文件的字段
     * 
     * @return String[] 字段列表
     * @see
     * @since 1.0
     */
    public String[] getArrayForXml() {
        return new String[1];
    }

    /**
     * 对应db插入语句
     * 
     * @return String[] 字段列表
     * @see
     * @since 1.0
     */
    public String[] getArrayForDB() {
        return new String[1];
    }

    /**
     * 对应db字段列表
     * 
     * @return String[] 字段列表
     * @see
     * @since 1.0
     */
    public String[] getCloumns() {
        return new String[1];
    }

    /**
     * 获取 tableName
     * 
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 tableName
     * 
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取 sysDate
     * 
     * @return sysDate.
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * 设置 sysDate
     * 
     * @param sysDate sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}
