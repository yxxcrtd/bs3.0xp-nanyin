/*
 * 文件名：BenchmarkDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业绩基准实体类
 * 
 * @author zhangJin
 * @version 1.0 2019年10月10日
 * @see BenchmarkDto
 * @since 1.0
 */
public class BenchmarkDto {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private String branchId;

    /**
     * 评价日
     */
    private Date effDate;

    /**
     * 最低业绩基准
     */
    private BigDecimal lowestBen;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 branchId
     * 
     * @return branchId.
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置 branchId
     * 
     * @param branchId branchId
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 lowestBen
     * 
     * @return lowestBen.
     */
    public BigDecimal getLowestBen() {
        return lowestBen;
    }

    /**
     * 设置 lowestBen
     * 
     * @param lowestBen lowestBen
     */
    public void setLowestBen(BigDecimal lowestBen) {
        this.lowestBen = lowestBen;
    }

}
