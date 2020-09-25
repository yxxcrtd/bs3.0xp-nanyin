package com.joyintech.tams.common.base.entity;

public class InterfPayhsrcsecrating extends InterfPayhsrcsecratingKey {
    /**
     * 评级结果
     */
    private String rating;

    /**
     * 是否推荐
     */
    private String isRecommand;

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getRating() {
        return rating;
    }

    /**
     * 设置 评级结果
     * @param rating 评级结果
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    /**
     * 取得 是否推荐
     * @return 是否推荐
     */
    public String getIsRecommand() {
        return isRecommand;
    }

    /**
     * 设置 是否推荐
     * @param isRecommand 是否推荐
     */
    public void setIsRecommand(String isRecommand) {
        this.isRecommand = isRecommand == null ? null : isRecommand.trim();
    }
}