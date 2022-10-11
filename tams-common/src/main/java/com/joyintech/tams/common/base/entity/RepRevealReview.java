package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepRevealReview extends BaseEntity {
    /**
     * UUID
     */
    private String uuid;

    /**
     * REP_REVEAL的ID
     */
    private String revealId;

    /**
     * 复核状态 01待复核 02已推送披露 03已推送托管 04被托管退回 05被披露退回 06退回确认
     */
    private String reviewStatus;

    /**
     * 托管复核状态 01待复核 02已推送披露 03被披露退回
     */
    private String reviewTgStatus;

    /**
     * 复核人
     */
    private String reviewUser;

    /**
     * 复核时间
     */
    private Date reviewTime;

    /**
     * 托管复核人
     */
    private String reviewTgUser;

    /**
     * 托管复核时间
     */
    private Date reviewTgTime;

    /**
     * 取得 UUID
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 UUID
     * @param uuid UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 REP_REVEAL的ID
     * @return REP_REVEAL的ID
     */
    public String getRevealId() {
        return revealId;
    }

    /**
     * 设置 REP_REVEAL的ID
     * @param revealId REP_REVEAL的ID
     */
    public void setRevealId(String revealId) {
        this.revealId = revealId == null ? null : revealId.trim();
    }

    /**
     * 取得 复核状态 01待复核 02已推送披露 03已推送托管 04被托管退回 05被披露退回 06退回确认
     * @return 复核状态 01待复核 02已推送披露 03已推送托管 04被托管退回 05被披露退回 06退回确认
     */
    public String getReviewStatus() {
        return reviewStatus;
    }

    /**
     * 设置 复核状态 01待复核 02已推送披露 03已推送托管 04被托管退回 05被披露退回 06退回确认
     * @param reviewStatus 复核状态 01待复核 02已推送披露 03已推送托管 04被托管退回 05被披露退回 06退回确认
     */
    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus == null ? null : reviewStatus.trim();
    }

    /**
     * 取得 托管复核状态 01待复核 02已推送披露 03被披露退回
     * @return 托管复核状态 01待复核 02已推送披露 03被披露退回
     */
    public String getReviewTgStatus() {
        return reviewTgStatus;
    }

    /**
     * 设置 托管复核状态 01待复核 02已推送披露 03被披露退回
     * @param reviewTgStatus 托管复核状态 01待复核 02已推送披露 03被披露退回
     */
    public void setReviewTgStatus(String reviewTgStatus) {
        this.reviewTgStatus = reviewTgStatus == null ? null : reviewTgStatus.trim();
    }

    /**
     * 取得 复核人
     * @return 复核人
     */
    public String getReviewUser() {
        return reviewUser;
    }

    /**
     * 设置 复核人
     * @param reviewUser 复核人
     */
    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser == null ? null : reviewUser.trim();
    }

    /**
     * 取得 复核时间
     * @return 复核时间
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     * 设置 复核时间
     * @param reviewTime 复核时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * 取得 托管复核人
     * @return 托管复核人
     */
    public String getReviewTgUser() {
        return reviewTgUser;
    }

    /**
     * 设置 托管复核人
     * @param reviewTgUser 托管复核人
     */
    public void setReviewTgUser(String reviewTgUser) {
        this.reviewTgUser = reviewTgUser == null ? null : reviewTgUser.trim();
    }

    /**
     * 取得 托管复核时间
     * @return 托管复核时间
     */
    public Date getReviewTgTime() {
        return reviewTgTime;
    }

    /**
     * 设置 托管复核时间
     * @param reviewTgTime 托管复核时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReviewTgTime(Date reviewTgTime) {
        this.reviewTgTime = reviewTgTime;
    }
}