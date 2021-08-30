package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ModGradePdConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 年份
     */
    private Long vouchYear;

    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型）
     */
    private String oType;

    /**
     * 评级结果（数据字典 评级结果）
     */
    private String gradeResult;

    /**
     * 前瞻前年度边际
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal forwardYearPd;

    /**
     * 评级pd类型 （01债券、其他债权、02回购、03存款）
     */
    private String pdConfigType;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 年份
     * @return 年份
     */
    public Long getVouchYear() {
        return vouchYear;
    }

    /**
     * 设置 年份
     * @param vouchYear 年份
     */
    public void setVouchYear(Long vouchYear) {
        this.vouchYear = vouchYear;
    }

    /**
     * 取得 评级机构（数据字典 评级机构）
     * @return 评级机构（数据字典 评级机构）
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 设置 评级机构（数据字典 评级机构）
     * @param gradeOrgId 评级机构（数据字典 评级机构）
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId == null ? null : gradeOrgId.trim();
    }

    /**
     * 取得 评级类型（数据字典 评级类型）
     * @return 评级类型（数据字典 评级类型）
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级类型（数据字典 评级类型）
     * @param oType 评级类型（数据字典 评级类型）
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 评级结果（数据字典 评级结果）
     * @return 评级结果（数据字典 评级结果）
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级结果（数据字典 评级结果）
     * @param gradeResult 评级结果（数据字典 评级结果）
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 前瞻前年度边际
     * @return 前瞻前年度边际
     */
    public BigDecimal getForwardYearPd() {
        return forwardYearPd;
    }

    /**
     * 设置 前瞻前年度边际
     * @param forwardYearPd 前瞻前年度边际
     */
    public void setForwardYearPd(BigDecimal forwardYearPd) {
        this.forwardYearPd = forwardYearPd;
    }

    /**
     * 取得 评级pd类型 （01债券、其他债权、02回购、03存款）
     * @return 评级pd类型 （01债券、其他债权、02回购、03存款）
     */
    public String getPdConfigType() {
        return pdConfigType;
    }

    /**
     * 设置 评级pd类型 （01债券、其他债权、02回购、03存款）
     * @param pdConfigType 评级pd类型 （01债券、其他债权、02回购、03存款）
     */
    public void setPdConfigType(String pdConfigType) {
        this.pdConfigType = pdConfigType == null ? null : pdConfigType.trim();
    }
}