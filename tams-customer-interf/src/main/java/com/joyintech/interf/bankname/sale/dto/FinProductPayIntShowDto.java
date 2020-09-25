/*
 * 文件名：FinProductPayIntShowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.sale.dto;

import java.util.Date;

/**
 * 收益型产品中间付息管理双击点开界面显示dto
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年3月1日
 * @see FinProductPayIntShowDto
 * @since 1.0
 */
public class FinProductPayIntShowDto extends FinProductPayIntProdBaseInfoDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 产品起息日
     */
    private Date vdate;

    /**
     * 产品到期日
     */
    private Date mdate;

    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
    
}
