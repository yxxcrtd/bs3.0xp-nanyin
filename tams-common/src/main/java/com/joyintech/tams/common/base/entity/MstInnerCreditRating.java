package com.joyintech.tams.common.base.entity;

public class MstInnerCreditRating extends MstInnerCreditRatingKey {
    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * 评级展望
     */
    private String gradeExpectation;

    /**
     * 变动方向
     */
    private String directionChange;

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级结果
     * @param gradeResult 评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 评级展望
     * @return 评级展望
     */
    public String getGradeExpectation() {
        return gradeExpectation;
    }

    /**
     * 设置 评级展望
     * @param gradeExpectation 评级展望
     */
    public void setGradeExpectation(String gradeExpectation) {
        this.gradeExpectation = gradeExpectation == null ? null : gradeExpectation.trim();
    }

    /**
     * 取得 变动方向
     * @return 变动方向
     */
    public String getDirectionChange() {
        return directionChange;
    }

    /**
     * 设置 变动方向
     * @param directionChange 变动方向
     */
    public void setDirectionChange(String directionChange) {
        this.directionChange = directionChange == null ? null : directionChange.trim();
    }
}