package com.joyintech.tams.disclosure.infoCompliance.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class InfoComplianceDto extends BaseEntity implements ProcessEntity {

    /**
     *  序号
     * */
    private String indexNo;

    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String seqno;

    /**
     *  模板类型
     * */
    private String infoType;

    private String infoTypeText;

    /**
     *  报表/模板名称
     * */
    private String infoName;

    /**
     *  当前状态,审批状态
     * */
    private String infoStatus;

    private String infoStatusText;

    /**
     *  开发状态
     * */
    private String infoDevStatus;

    private String infoDevStatusText;

    /**
     *  备注
     * */
    private String remark;

    /**
     * 当前审核人
     * */
    private String approveUser;

    /**
     *报表/模板附件
     */
    private List<UploadFileDto> uploadFileList;

    private List<UploadFileDto> uploadFileList2;

    private String updateTimeText;


    /**
     * 画面模式
     */
    private String pageModel;

    private String operationType;

    private String elementId;

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoDevStatus() {
        return infoDevStatus;
    }

    public void setInfoDevStatus(String infoDevStatus) {
        this.infoDevStatus = infoDevStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public List<UploadFileDto> getUploadFileList() {
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

    public String getInfoTypeText() {
        return infoTypeText;
    }

    public void setInfoTypeText(String infoTypeText) {
        this.infoTypeText = infoTypeText;
    }

    public String getInfoStatusText() {
        return infoStatusText;
    }

    public void setInfoStatusText(String infoStatusText) {
        this.infoStatusText = infoStatusText;
    }

    public String getInfoDevStatusText() {
        return infoDevStatusText;
    }

    public void setInfoDevStatusText(String infoDevStatusText) {
        this.infoDevStatusText = infoDevStatusText;
    }

    public String getUpdateTimeText() {
        return updateTimeText;
    }

    public void setUpdateTimeText(String updateTimeText) {
        this.updateTimeText = updateTimeText;
    }

    public List<UploadFileDto> getUploadFileList2() {
        return uploadFileList2;
    }

    public void setUploadFileList2(List<UploadFileDto> uploadFileList2) {
        this.uploadFileList2 = uploadFileList2;
    }
}
