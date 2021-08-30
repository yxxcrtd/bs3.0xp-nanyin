package com.joyintech.tams.model.impairment.batch.common.searchinfo.dto;

import com.joyintech.framework.base.BaseEntity;

public class ModStandardGradeMapping extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 评级机构
     */
    private String gradeInst;

    /**
     * 评级类型
     */
    private String oType;

    /**
     * 外部评级结果
     */
    private String gradeResult;

    /**
     * 标准评级结果
     */
    private String gradeStandardResult;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 评级机构
     * @return 评级机构
     */
    public String getGradeInst() {
        return gradeInst;
    }

    /**
     * 设置 评级机构
     * @param gradeInst 评级机构
     */
    public void setGradeInst(String gradeInst) {
        this.gradeInst = gradeInst == null ? null : gradeInst.trim();
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级类型
     * @param oType 评级类型
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 外部评级结果
     * @return 外部评级结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 外部评级结果
     * @param gradeResult 外部评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 标准评级结果
     * @return 标准评级结果
     */
    public String getGradeStandardResult() {
        return gradeStandardResult;
    }

    /**
     * 设置 标准评级结果
     * @param gradeStandardResult 标准评级结果
     */
    public void setGradeStandardResult(String gradeStandardResult) {
        this.gradeStandardResult = gradeStandardResult == null ? null : gradeStandardResult.trim();
    }
}