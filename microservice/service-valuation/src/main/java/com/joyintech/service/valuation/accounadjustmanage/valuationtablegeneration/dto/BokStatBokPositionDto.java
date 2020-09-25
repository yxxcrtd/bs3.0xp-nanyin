/*
 * 文件名：BokStatBokPositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年10月9日
 * @see BokStatBokPositionDto
 * @since 1.0
 */
public class BokStatBokPositionDto extends BokStatBokPosition {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5829872500133866994L;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 估值小数位位数
     */
    private String valDecDigits;

    /**
     * 估值小数位处理方式
     */
    private String valDecType;

    /**
     * 万份收益小数位位数
     */
    private String pyeildDecDigits;

    /**
     * 取得 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 取得 金融产品名称
     * 
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * 
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 取得 估值小数位位数
     * 
     * @return 估值小数位位数
     */
    public String getValDecDigits() {
        return valDecDigits;
    }

    /**
     * 设置 估值小数位位数
     * 
     * @param valDecDigits 估值小数位位数
     */
    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits;
    }

    /**
     * 取得 估值小数位处理方式
     * 
     * @return 估值小数位处理方式
     */
    public String getValDecType() {
        return valDecType;
    }

    /**
     * 设置 估值小数位处理方式
     * 
     * @param valDecType 估值小数位处理方式
     */
    public void setValDecType(String valDecType) {
        this.valDecType = valDecType;
    }

    /**
     * 取得 万份收益小数位位数
     * 
     * @return 万份收益小数位位数
     */
    public String getPyeildDecDigits() {
        return pyeildDecDigits;
    }

    /**
     * 设置 万份收益小数位位数
     * 
     * @param pyeildDecDigits 万份收益小数位位数
     */
    public void setPyeildDecDigits(String pyeildDecDigits) {
        this.pyeildDecDigits = pyeildDecDigits;
    }

}
