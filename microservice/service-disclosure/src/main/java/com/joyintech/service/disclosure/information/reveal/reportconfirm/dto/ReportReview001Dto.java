package com.joyintech.service.disclosure.information.reveal.reportconfirm.dto;

import com.joyintech.framework.annotation.PageId;

import java.util.Date;

@PageId("ReportReview001")
public class ReportReview001Dto extends ReportConfirmManage001Dto {

    private String reviewStatus;

    private Date reviewTime;

    private String confirmFileName;

    private String confirmFilePath;

    private String reviewTgGroup;

    private String reviewTgUser;

    private Date reviewTgTime;
    private String reviewTgStatus;
    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getConfirmFileName() {
        return confirmFileName;
    }

    public void setConfirmFileName(String confirmFileName) {
        this.confirmFileName = confirmFileName;
    }

    public String getConfirmFilePath() {
        return confirmFilePath;
    }

    public void setConfirmFilePath(String confirmFilePath) {
        this.confirmFilePath = confirmFilePath;
    }

    public String getReviewTgGroup() {
        return reviewTgGroup;
    }

    public void setReviewTgGroup(String reviewTgGroup) {
        this.reviewTgGroup = reviewTgGroup;
    }

    public String getReviewTgUser() {
        return reviewTgUser;
    }

    public void setReviewTgUser(String reviewTgUser) {
        this.reviewTgUser = reviewTgUser;
    }

    public Date getReviewTgTime() {
        return reviewTgTime;
    }

    public void setReviewTgTime(Date reviewTgTime) {
        this.reviewTgTime = reviewTgTime;
    }

    public String getReviewTgStatus() {
        return reviewTgStatus;
    }

    public void setReviewTgStatus(String reviewTgStatus) {
        this.reviewTgStatus = reviewTgStatus;
    }
}
