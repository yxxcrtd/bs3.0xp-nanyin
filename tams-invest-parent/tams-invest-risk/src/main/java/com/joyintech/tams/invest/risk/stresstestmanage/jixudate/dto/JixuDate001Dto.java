/*
 * 文件名：JixuDate001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.jixudate.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Dto
 * @since 1.0
 */
public class JixuDate001Dto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1647598149572099808L;
    
    private Date jixuDate;
    
    //jixu日期
    private Date newDate;
    
    
    /**
     * 得到jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Date getJixuDate() {
        return jixuDate;
    }

    /**
     * 设置jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @param jixuDate
     * @see
     * @since 1.0
     */
    public void setJixuDate(Date jixuDate) {
        this.jixuDate = jixuDate;
    }

    /**
     * 得到jixu新日期
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Date getNewDate() {
        return newDate;
    }

    /**
     * 设置jixu新日期
     * <一句话功能简述>
     * <功能详细描述>
     * @param newDate
     * @see
     * @since 1.0
     */
    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }
    
    
    
    
    
}
