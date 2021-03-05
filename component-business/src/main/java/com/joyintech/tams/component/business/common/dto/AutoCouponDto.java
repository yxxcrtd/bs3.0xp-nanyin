/*
 * 文件名：AutoCouponDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年4月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.common.dto;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 自动加券
 * 自动加券交易已经加载成功后，返回产品信息
 * @author yangjm
 * @version 1.0 2019年4月1日
 * @see AutoCouponDto
 * @since 1.0
 */
public class AutoCouponDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2983816893042724104L;
    /**
     * 一级类型
     */
    private String type4;
    /**
     * 计息基础
     */
    private String basis;

    /**
     * 获取 type4
     * @return type4.
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 type4
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }

    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }


    private String type9;
    private String type10;
    private String type11;
    private String type12;

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getType10() {
        return type10;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    public String getType11() {
        return type11;
    }

    public void setType11(String type11) {
        this.type11 = type11;
    }

    public String getType12() {
        return type12;
    }

    public void setType12(String type12) {
        this.type12 = type12;
    }
}
