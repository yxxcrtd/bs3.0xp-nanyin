package com.joyintech.service.entrust.etrmoneycalculation.dto;


public class RatingDto {

    //资产代码
    private  String finprodId;
    //评选日期
    private String gradeDate ;
    //评级类型
    private  String gradeType;
    // 评级结果
    private  String gradeResult;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(String gradeDate) {
        this.gradeDate = gradeDate;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }
}
