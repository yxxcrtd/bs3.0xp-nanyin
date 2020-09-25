/*
 * 文件名：InterfTcms002InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 理财产品信息查询TCMS002输入entity
 * 
 * @author changwenbin
 * @version 1.0 2018年11月12日
 * @see InterfTcms002InEntity
 * @since 1.0
 */
public class InterfTcms002InEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 9137006478870817889L;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 查询模式
     */
    private String chkmode;

    /**
     * 数据字典.金融产品类型.理财产品code
     */
    private String lcprod;

    /**
     * 数据字典.现金流二级类型.利息code
     */
    private String xjint;

    /**
     * 数据字典.是否.是code
     */
    private String yes;

    /**
     * 数据字典.发行方式.单一code
     */
    private String danyi;

    /**
     * 获取 产品代码
     * 
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * 
     * @param prodid 产品代码
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    /**
     * 获取 查询模式
     * 
     * @return 查询模式
     */
    public String getChkmode() {
        return chkmode;
    }

    /**
     * 设置 查询模式
     * 
     * @param chkmode 查询模式
     */
    @EsbName(value = "QUERY_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setChkmode(String chkmode) {
        this.chkmode = chkmode;
    }

    /**
     * 获取 数据字典.金融产品类型.理财产品code
     * 
     * @return 数据字典.金融产品类型.理财产品code
     */
    public String getLcprod() {
        return lcprod;
    }

    /**
     * 设置 数据字典.金融产品类型.理财产品code
     * 
     * @param lcprod 数据字典.金融产品类型.理财产品code
     */
    public void setLcprod(String lcprod) {
        this.lcprod = lcprod;
    }

    /**
     * 获取 数据字典.现金流二级类型.利息code
     * 
     * @return 数据字典.现金流二级类型.利息code
     */
    public String getXjint() {
        return xjint;
    }

    /**
     * 设置 数据字典.现金流二级类型.利息code
     * 
     * @param xjint 数据字典.现金流二级类型.利息code
     */
    public void setXjint(String xjint) {
        this.xjint = xjint;
    }

    /**
     * 获取 数据字典.是否.是code
     * 
     * @return 数据字典.是否.是code
     */
    public String getYes() {
        return yes;
    }

    /**
     * 设置 数据字典.是否.是code
     * 
     * @param yes 数据字典.是否.是code
     */
    public void setYes(String yes) {
        this.yes = yes;
    }

    /**
     * 获取 数据字典.发行方式.单一code
     * 
     * @return 数据字典.发行方式.单一code
     */
    public String getDanyi() {
        return danyi;
    }

    /**
     * 设置 数据字典.发行方式.单一code
     * 
     * @param danyi 数据字典.发行方式.单一code
     */
    public void setDanyi(String danyi) {
        this.danyi = danyi;
    }

}
