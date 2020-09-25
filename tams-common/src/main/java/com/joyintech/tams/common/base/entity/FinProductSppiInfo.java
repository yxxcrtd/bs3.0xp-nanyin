package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class FinProductSppiInfo extends FinProductSppiInfoKey {
    /**
     * 测试日期
     */
    private Date testDate;

    /**
     * 测试结果
     */
    private String testResult;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 测试日期
     * @return 测试日期
     */
    public Date getTestDate() {
        return testDate;
    }

    /**
     * 设置 测试日期
     * @param testDate 测试日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    /**
     * 取得 测试结果
     * @return 测试结果
     */
    public String getTestResult() {
        return testResult;
    }

    /**
     * 设置 测试结果
     * @param testResult 测试结果
     */
    public void setTestResult(String testResult) {
        this.testResult = testResult == null ? null : testResult.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}