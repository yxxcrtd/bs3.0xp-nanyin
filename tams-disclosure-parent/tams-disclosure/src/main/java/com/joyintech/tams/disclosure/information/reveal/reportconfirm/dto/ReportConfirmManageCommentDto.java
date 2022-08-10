package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto;

import com.joyintech.framework.base.BaseEntity;

public class ReportConfirmManageCommentDto extends BaseEntity {
    private String seqno;

    private String infoSeqno;

    //修订意见
    private String commentRemark;

    public ReportConfirmManageCommentDto(){

    }

    public ReportConfirmManageCommentDto(String seqno,String infoSeqno,String commentRemark){
        this.seqno = seqno;
        this.infoSeqno = infoSeqno;
        this.commentRemark = commentRemark;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getInfoSeqno() {
        return infoSeqno;
    }

    public void setInfoSeqno(String infoSeqno) {
        this.infoSeqno = infoSeqno;
    }

    public String getCommentRemark() {
        return commentRemark;
    }

    public void setCommentRemark(String commentRemark) {
        this.commentRemark = commentRemark;
    }
}
