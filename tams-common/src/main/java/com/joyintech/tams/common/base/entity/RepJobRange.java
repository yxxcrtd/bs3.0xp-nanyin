package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepJobRange extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 规则类型
     */
    private String flag;

    /**
     * 规则1(枚举)
     */
    private String ruleType1;

    /**
     * 规则2(符号)
     */
    private String ruleType2;

    /**
     * 规则3(值类型)
     */
    private String ruleType3;

    /**
     * 规则4(值)
     */
    private String ruleType4;

    /**
     * 取得 ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 作业编号
     * @return 作业编号
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置 作业编号
     * @param jobId 作业编号
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * 取得 规则类型
     * @return 规则类型
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 规则类型
     * @param flag 规则类型
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * 取得 规则1(枚举)
     * @return 规则1(枚举)
     */
    public String getRuleType1() {
        return ruleType1;
    }

    /**
     * 设置 规则1(枚举)
     * @param ruleType1 规则1(枚举)
     */
    public void setRuleType1(String ruleType1) {
        this.ruleType1 = ruleType1 == null ? null : ruleType1.trim();
    }

    /**
     * 取得 规则2(符号)
     * @return 规则2(符号)
     */
    public String getRuleType2() {
        return ruleType2;
    }

    /**
     * 设置 规则2(符号)
     * @param ruleType2 规则2(符号)
     */
    public void setRuleType2(String ruleType2) {
        this.ruleType2 = ruleType2 == null ? null : ruleType2.trim();
    }

    /**
     * 取得 规则3(值类型)
     * @return 规则3(值类型)
     */
    public String getRuleType3() {
        return ruleType3;
    }

    /**
     * 设置 规则3(值类型)
     * @param ruleType3 规则3(值类型)
     */
    public void setRuleType3(String ruleType3) {
        this.ruleType3 = ruleType3 == null ? null : ruleType3.trim();
    }

    /**
     * 取得 规则4(值)
     * @return 规则4(值)
     */
    public String getRuleType4() {
        return ruleType4;
    }

    /**
     * 设置 规则4(值)
     * @param ruleType4 规则4(值)
     */
    public void setRuleType4(String ruleType4) {
        this.ruleType4 = ruleType4 == null ? null : ruleType4.trim();
    }
}