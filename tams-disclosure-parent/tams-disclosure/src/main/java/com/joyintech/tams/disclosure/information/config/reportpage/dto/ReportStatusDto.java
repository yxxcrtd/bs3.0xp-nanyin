package com.joyintech.tams.disclosure.information.config.reportpage.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportStatusDto extends BaseEntity implements ProcessEntity {

    /**
     *  主键
     * */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String seqno;

    /**
     *  报表ID
     * */
    private String repId;

    /**
     *  启用禁用标识
     * */
    private String startOff;

    /**
     *  生效日
     * */
    private Date effDate;

    /**
     *  生效日
     * */
    private String effDateStr;

    /**
     *  报表审核状态:01流程中;02审核通过
     * */
    private String repStatus;

    /**
     *  备注
     * */
    private String remark;

    /**
     *  流程id
     * */
    private String workflowId;

    /**
     *  流程类型:01启用流程;02禁用流程
     * */
    private String workflowType;

    /**
     * 模板相关附件
     */
    private List<UploadFileDto> uploadFileList;

    /**
     * 画面模式
     */
    private String pageModel;

    private String operationType;

    private String elementId;

    private String comment;

    private String startRepId;

    private String offRepId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     *  是否已同步:记录REP_INTO_STATUS记录的状态是否已同步到REP_INFO表
     * */
    private String isSynchro;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getStartOff() {
        return startOff;
    }

    public void setStartOff(String startOff) {
        this.startOff = startOff;
    }

    public String getStartRepId() {
        return startRepId;
    }

    public void setStartRepId(String startRepId) {
        this.startRepId = startRepId;
    }

    public String getOffRepId() {
        return offRepId;
    }

    public void setOffRepId(String offRepId) {
        this.offRepId = offRepId;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(String repStatus) {
        this.repStatus = repStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public List<UploadFileDto> getUploadFileList() {
        if(uploadFileList == null){
            this.uploadFileList = new ArrayList<UploadFileDto>();
        }
        return uploadFileList;
    }

    public void setUploadFileList(List<UploadFileDto> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public String getEffDateStr() {
        return effDateStr;
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    public String getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(String isSynchro) {
        this.isSynchro = isSynchro;
    }
}
