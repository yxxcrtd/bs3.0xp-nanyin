/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * ValVoucherQuery001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author weipeng
 * @version 1.0 2018年5月8日
 * @see ValVoucherQuery003SearchDto
 * @since 1.0
 */
public class ValVoucherQuery003SearchDto {

    /**
     * 主表凭证编号
     */
    @NotEmpty(fieldName = "ValVoucherQuery001.vouchNum")
    private String vouchNum;

    /**
     * 摘要
     */
    @NotEmpty(fieldName = "ValVoucherQuery001.vouchRemark")
    private String vouchRemark;

    /**
     * 获取 主表凭证编号
     * 
     * @return 主表凭证编号
     */
    public String getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 主表凭证编号
     * 
     * @param vouchNum 主表凭证编号
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum;
    }

    /**
     * 获取 摘要
     * 
     * @return 摘要
     */
    public String getVouchRemark() {
        return vouchRemark;
    }

    /**
     * 设置 摘要
     * 
     * @param vouchRemark 摘要
     */
    public void setVouchRemark(String vouchRemark) {
        this.vouchRemark = vouchRemark;
    }

}
