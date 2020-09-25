/*
 * 文件名：PtlLatestStrategyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合最新策略
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see PtlLatestStrategyDto
 * @since 1.0
 */
public class PtlLatestStrategyDto {

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 策略明细代码
     */
    private String strategyDetId;

    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 资产二级分类
     */
    private String type2;

    /**
     * 资产三级分类
     */
    private String type3;

    /**
     * 资产四级分类
     */
    private String type4;

    /**
     * 资产五级分类
     */
    private String type5;

    /**
     * 比例
     */
    private BigDecimal ratio;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 获取 batchId
     * 
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 batchId
     * 
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取 strategyDetId
     * 
     * @return strategyDetId.
     */
    public String getStrategyDetId() {
        return strategyDetId;
    }

    /**
     * 设置 strategyDetId
     * 
     * @param strategyDetId strategyDetId
     */
    public void setStrategyDetId(String strategyDetId) {
        this.strategyDetId = strategyDetId;
    }

    /**
     * 获取 strategyId
     * 
     * @return strategyId.
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * 设置 strategyId
     * 
     * @param strategyId strategyId
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    /**
     * 获取 type2
     * 
     * @return type2.
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 type2
     * 
     * @param type2 type2
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    /**
     * 获取 type3
     * 
     * @return type3.
     */
    public String getType3() {
        return type3;
    }

    /**
     * 设置 type3
     * 
     * @param type3 type3
     */
    public void setType3(String type3) {
        this.type3 = type3;
    }

    /**
     * 获取 type4
     * 
     * @return type4.
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 type4
     * 
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }

    /**
     * 获取 type5
     * 
     * @return type5.
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 type5
     * 
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
    }

    /**
     * 获取 ratio
     * 
     * @return ratio.
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 ratio
     * 
     * @param ratio ratio
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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

}
