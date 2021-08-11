package com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto;

public class InsideOutsideGradeDetail001Dto {
    /**
     * 外部评级
     */
    private String gradeResult;
    /**
     * 内部评级
     */
    private String gradeStandardResult;

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public String getGradeStandardResult() {
        return gradeStandardResult;
    }

    public void setGradeStandardResult(String gradeStandardResult) {
        this.gradeStandardResult = gradeStandardResult;
    }

}
