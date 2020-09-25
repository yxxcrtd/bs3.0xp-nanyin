/*
 * 文件名：FlowRiskStressTestView003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;


/**
 * 〈现金流详细〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年8月30日
 * @see FlowRiskStressTestView003Dto
 * @since 1.0
 */
public class FlowRiskStressTestView003Dto extends BaseEntity {
    
    /**
     * 列值
     */
    private String testId;
    
    /**
     * 列值
     */
    private int lineNum;
    
    /**
     * 第一列值
     */
    private String colValue1;
    
    /**
     * 第二列值
     */
    private String colValue2;
    
    /**
     * 第三列值
     */
    private String colValue3;
    
    /**
     * 金额
     */
    private BigDecimal amount;
    
    /**
     * 备注
     */
    private String remarks;

    
    /**
     * 获取 testId
     * @return testId.
     */
    public String getTestId() {
        return testId;
    }

    
    /**
     * 设置 testId
     * @param testId testId
     */
    public void setTestId(String testId) {
        this.testId = testId;
    }

    
    /**
     * 获取 lineNum
     * @return lineNum.
     */
    public int getLineNum() {
        return lineNum;
    }

    
    /**
     * 设置 lineNum
     * @param lineNum lineNum
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    
    /**
     * 获取 colValue1
     * @return colValue1.
     */
    public String getColValue1() {
        return colValue1;
    }

    
    /**
     * 设置 colValue1
     * @param colValue1 colValue1
     */
    public void setColValue1(String colValue1) {
        this.colValue1 = colValue1;
    }

    
    /**
     * 获取 colValue2
     * @return colValue2.
     */
    public String getColValue2() {
        return colValue2;
    }

    
    /**
     * 设置 colValue2
     * @param colValue2 colValue2
     */
    public void setColValue2(String colValue2) {
        this.colValue2 = colValue2;
    }

    
    /**
     * 获取 colValue3
     * @return colValue3.
     */
    public String getColValue3() {
        return colValue3;
    }

    
    /**
     * 设置 colValue3
     * @param colValue3 colValue3
     */
    public void setColValue3(String colValue3) {
        this.colValue3 = colValue3;
    }

    
    /**
     * 获取 amount
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    
    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    /**
     * 获取 remarks
     * @return remarks.
     */
    public String getRemarks() {
        return remarks;
    }

    
    /**
     * 设置 remarks
     * @param remarks remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    
}
