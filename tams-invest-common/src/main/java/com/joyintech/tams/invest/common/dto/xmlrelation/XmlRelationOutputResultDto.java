/*
 * 文件名：XmlRelationOutputResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.xmlrelation;

import java.io.Serializable;

import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系
 * result
 * @author etliu
 * @version 1.0 2018年12月8日
 * @see XmlRelationOutputResultDto
 * @since 1.0
 */
public class XmlRelationOutputResultDto implements Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1655088913868431850L;

    /**
     * sysCode
     */
    private String sysCode = XmlRelationUtil.SYSCODE_VAL;

    /**
     * dictCode
     */
    private String dictCode;

    /**
     * itemCode
     */
    private String itemCode;

    /**
     * 获取 sysCode
     *
     * @return sysCode.
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * 设置 sysCode
     *
     * @param sysCode sysCode
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    /**
     * 获取 dictCode
     *
     * @return dictCode.
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 dictCode
     *
     * @param dictCode dictCode
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取 itemCode
     *
     * @return itemCode.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 itemCode
     *
     * @param itemCode itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
