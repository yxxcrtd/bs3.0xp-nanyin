package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

public class InsideOutsideGradeDetail001Dto{
    /**
     * 外部评级
     */
    private String gradeResult;
    /**
     * 内部评级
     */
    private String gradeStandardResult;

    @NotEmpty(fieldName = "外部评级", groups = {ValidateGroupEdit.class})
    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    @NotEmpty(fieldName = "内部评级", groups = {ValidateGroupEdit.class})
    public String getGradeStandardResult() {
        return gradeStandardResult;
    }

    public void setGradeStandardResult(String gradeStandardResult) {
        this.gradeStandardResult = gradeStandardResult;
    }

}
