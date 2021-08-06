package com.joyintech.tams.model.impairment.batch.impairmentdataload.dto;

import java.util.Date;

/**
 * 减值模型评级数据参数对象
 * @author zhangfei
 * @date 2021年7月14日14:48:25
 */
public class ModImpairRateParamsDto {

    /**
     * 评级主体
     */
    private String ocode;
    /**
     * 评级类型 （非标外部评级查询-减值模型查询时可为空）
     */
    private String otype;
    /**
     * 评级机构
     */
    private String gradeorgId;
    /**
     * 查询日期
     */
    private Date searchDate;

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getGradeorgId() {
        return gradeorgId;
    }

    public void setGradeorgId(String gradeorgId) {
        this.gradeorgId = gradeorgId;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
}
