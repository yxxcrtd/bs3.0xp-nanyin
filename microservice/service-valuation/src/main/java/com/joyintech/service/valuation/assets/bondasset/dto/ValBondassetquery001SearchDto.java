/*
 * 文件名：ValBondassetquery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * ValBondassetquery001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery001SearchDto
 * @since 1.0
 */
@SuppressWarnings("unused")
public class ValBondassetquery001SearchDto {
    
    /**
     * 债券代码
     */
    private String bond;
    /**
     * 息票品种
     */
    private String couponType;
    /**
     * 息票品种
     */
    private String couponTypeName;
    /**
     * 债券分类
     */
    private String bondtype;
    /**
     * 起息日
     */
    private String vdate;
    /**
     * 起息日开始时间
     */
    private String vDateBegin;
    /**
     * 起息日结束时间
     */
    private String vDateEnd;
    /**
     * 到期日
     */
    private String mdate;
    /**
     * 起息日开始时间
     */
    private String mDateBegin;

    /**
     * 起息日结束时间
     */
    private String mDateEnd;
    
    /**
     * 获取 bond
     * @return bond.
     */
    public String getBond() {
        return bond;
    }
    
    /**
     * 设置 bond
     * @param bond bond
     */
    public void setBond(String bond) {
        this.bond = bond;
    }
    
    /**
     * 获取 couponType
     * @return couponType.
     */
    public String getCouponType() {
        return couponType;
    }
    
    /**
     * 设置 couponType
     * @param couponType couponType
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
    
    /**
     * 获取 bondtype
     * @return bondtype.
     */
    public String getBondtype() {
        return bondtype;
    }
    
    /**
     * 设置 bondtype
     * @param bondtype bondtype
     */
    public void setBondtype(String bondtype) {
        this.bondtype = bondtype;
    }

    
    
    /**
     * 获取 couponTypeName
     * @return couponTypeName.
     */
    public String getCouponTypeName() {
        return this.couponTypeName;
    }

    
    /**
     * 设置 couponTypeName
     * @param couponTypeName couponTypeName
     */
    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName;
    }

    /**
     * 获取 vdate
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public String getMdate() {
        return mdate;
    }

    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    
    /**
     * 获取 vDateBegin
     * @return vDateBegin.
     */
    public String getvDateBegin() {
        return vDateBegin;
    }

    
    /**
     * 设置 vDateBegin
     * @param vDateBegin vDateBegin
     */
    public void setvDateBegin(String vDateBegin) {
        this.vDateBegin = vDateBegin;
    }

    
    /**
     * 获取 vDateEnd
     * @return vDateEnd.
     */
    public String getvDateEnd() {
        return vDateEnd;
    }

    
    /**
     * 设置 vDateEnd
     * @param vDateEnd vDateEnd
     */
    public void setvDateEnd(String vDateEnd) {
        this.vDateEnd = vDateEnd;
    }

    
    /**
     * 获取 mDateBegin
     * @return mDateBegin.
     */
    public String getmDateBegin() {
        return mDateBegin;
    }

    
    /**
     * 设置 mDateBegin
     * @param mDateBegin mDateBegin
     */
    public void setmDateBegin(String mDateBegin) {
        this.mDateBegin = mDateBegin;
    }

    
    /**
     * 获取 mDateEnd
     * @return mDateEnd.
     */
    public String getmDateEnd() {
        return mDateEnd;
    }

    
    /**
     * 设置 mDateEnd
     * @param mDateEnd mDateEnd
     */
    public void setmDateEnd(String mDateEnd) {
        this.mDateEnd = mDateEnd;
    }
    
 
}
