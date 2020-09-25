/*
 * 文件名：RepFtpPathDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.home.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.RepFtpPath;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月15日
 * @see RepFtpPathDto
 * @since 1.0
 */
public class RepFtpPathDto extends RepFtpPath {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3161353231706835339L;

    /**
     * 第一列
     */
    private String firstCol;

    /**
     * 报表数量
     */
    private BigDecimal reportCount;

    /**
     * 获取 firstCol
     * 
     * @return firstCol.
     */
    public String getFirstCol() {
        return firstCol;
    }

    /**
     * 设置 firstCol
     * 
     * @param firstCol firstCol
     */
    public void setFirstCol(String firstCol) {
        this.firstCol = firstCol;
    }

    /**
     * 获取 reportCount
     * 
     * @return reportCount.
     */
    public BigDecimal getReportCount() {
        return reportCount;
    }

    /**
     * 设置 reportCount
     * 
     * @param reportCount reportCount
     */
    public void setReportCount(BigDecimal reportCount) {
        this.reportCount = reportCount;
    }

}
