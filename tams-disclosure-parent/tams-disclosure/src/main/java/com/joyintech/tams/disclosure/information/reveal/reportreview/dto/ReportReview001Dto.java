package com.joyintech.tams.disclosure.information.reveal.reportreview.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;

import java.util.Date;


@PageId("ReportReview001")
public class ReportReview001Dto extends ReportConfirmManage001Dto {

    private String reviewStatus;

    private Date reviewTime;

    private String confirmFileName;

    private String confirmFilePath;
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


    public String getReviewStatusStr(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCodeExt1.复核状态, this.getReviewStatus()))?
                SysDataUtil.getNameFromDataDict(DictCodeExt1.复核状态, this.getReviewStatus()):this.getReviewStatus();
    }

    public String getReviewTimeStr(){
        return DateUtil.format(this.getReviewTime(),"yyyy-MM-dd HH:mm");
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
}
