package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokProfitRepConfig extends BaseEntity {
    /**
     * 项目编码
     */
    private String projCode;

    /**
     * 项目名
     */
    private String projName;

    /**
     * 项目级别，0-明细项，1-汇总项
     */
    private Long projLevel;

    /**
     * 计算公式
     */
    private String formula;

    /**
     * 是否是报表展示项
     */
    private String showFlg;

    /**
     * 报表展示序号
     */
    private Long showOrder;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 期待方向，C-贷方，D-借方
     */
    private String crdrFlg;

    /**
     * 取得 项目编码
     * @return 项目编码
     */
    public String getProjCode() {
        return projCode;
    }

    /**
     * 设置 项目编码
     * @param projCode 项目编码
     */
    public void setProjCode(String projCode) {
        this.projCode = projCode == null ? null : projCode.trim();
    }

    /**
     * 取得 项目名
     * @return 项目名
     */
    public String getProjName() {
        return projName;
    }

    /**
     * 设置 项目名
     * @param projName 项目名
     */
    public void setProjName(String projName) {
        this.projName = projName == null ? null : projName.trim();
    }

    /**
     * 取得 项目级别，0-明细项，1-汇总项
     * @return 项目级别，0-明细项，1-汇总项
     */
    public Long getProjLevel() {
        return projLevel;
    }

    /**
     * 设置 项目级别，0-明细项，1-汇总项
     * @param projLevel 项目级别，0-明细项，1-汇总项
     */
    public void setProjLevel(Long projLevel) {
        this.projLevel = projLevel;
    }

    /**
     * 取得 计算公式
     * @return 计算公式
     */
    public String getFormula() {
        return formula;
    }

    /**
     * 设置 计算公式
     * @param formula 计算公式
     */
    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    /**
     * 取得 是否是报表展示项
     * @return 是否是报表展示项
     */
    public String getShowFlg() {
        return showFlg;
    }

    /**
     * 设置 是否是报表展示项
     * @param showFlg 是否是报表展示项
     */
    public void setShowFlg(String showFlg) {
        this.showFlg = showFlg == null ? null : showFlg.trim();
    }

    /**
     * 取得 报表展示序号
     * @return 报表展示序号
     */
    public Long getShowOrder() {
        return showOrder;
    }

    /**
     * 设置 报表展示序号
     * @param showOrder 报表展示序号
     */
    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 期待方向，C-贷方，D-借方
     * @return 期待方向，C-贷方，D-借方
     */
    public String getCrdrFlg() {
        return crdrFlg;
    }

    /**
     * 设置 期待方向，C-贷方，D-借方
     * @param crdrFlg 期待方向，C-贷方，D-借方
     */
    public void setCrdrFlg(String crdrFlg) {
        this.crdrFlg = crdrFlg == null ? null : crdrFlg.trim();
    }
}