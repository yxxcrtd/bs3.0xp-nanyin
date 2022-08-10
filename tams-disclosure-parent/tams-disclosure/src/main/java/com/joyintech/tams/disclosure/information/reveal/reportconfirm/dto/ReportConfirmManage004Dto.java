package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;

import java.util.Date;
import java.util.List;

public class ReportConfirmManage004Dto extends RepReveal implements ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<ReportConfirmManage004Dto> selectedItem;

    private String operationType;

    /**
     *  当前状态,审批状态
     * */
    private String infoStatus;

    private List<UploadFileDto> uploadFileList2;

    private String comment;

    //修订意见
    private String commentRemark;

    private String checkUser;

    private Date checkTime;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<ReportConfirmManage004Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ReportConfirmManage004Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public List<UploadFileDto> getUploadFileList2() {
        return uploadFileList2;
    }

    public void setUploadFileList2(List<UploadFileDto> uploadFileList2) {
        this.uploadFileList2 = uploadFileList2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getId() {
        return super.getId();
    }

    public String getStartDateStr() {
        return this.getStartDate()==null?null: DateUtil.format(this.getStartDate(), "yyyy-MM-dd");
    }
    public String getEndDateStr() {
        return this.getEndDate()==null?null: DateUtil.format(this.getEndDate(), "yyyy-MM-dd");
    }

    public String getCommentRemark() {
        return commentRemark;
    }

    public void setCommentRemark(String commentRemark) {
        this.commentRemark = commentRemark;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
