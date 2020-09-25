package com.joyintech.tams.common.base.entity;

public class PaRskCustomerRating extends PaRskCustomerRatingKey {
    /**
     * 评级认定结果
     */
    private String rating;

    /**
     * 评级报告年度
     */
    private Long ratingYear;

    /**
     * 使用模型类别
     */
    private String ratingMode;

    /**
     * 取得 评级认定结果
     * @return 评级认定结果
     */
    public String getRating() {
        return rating;
    }

    /**
     * 设置 评级认定结果
     * @param rating 评级认定结果
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    /**
     * 取得 评级报告年度
     * @return 评级报告年度
     */
    public Long getRatingYear() {
        return ratingYear;
    }

    /**
     * 设置 评级报告年度
     * @param ratingYear 评级报告年度
     */
    public void setRatingYear(Long ratingYear) {
        this.ratingYear = ratingYear;
    }

    /**
     * 取得 使用模型类别
     * @return 使用模型类别
     */
    public String getRatingMode() {
        return ratingMode;
    }

    /**
     * 设置 使用模型类别
     * @param ratingMode 使用模型类别
     */
    public void setRatingMode(String ratingMode) {
        this.ratingMode = ratingMode == null ? null : ratingMode.trim();
    }
}