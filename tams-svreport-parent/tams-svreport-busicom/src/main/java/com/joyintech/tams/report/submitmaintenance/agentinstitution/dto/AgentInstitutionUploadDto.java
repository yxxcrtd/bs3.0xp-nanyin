package com.joyintech.tams.report.submitmaintenance.agentinstitution.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class AgentInstitutionUploadDto extends BaseEntity implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 附件上传
     */
    private List<AgentUploadFileInfo> fileArray;

    /**
     * 代销机构信息dto
     */
    private List<AgentInstitutionDto> uploadArrStr;

    public List<AgentUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<AgentUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    public List<AgentInstitutionDto> getUploadArrStr() {
        return uploadArrStr;
    }

    public void setUploadArrStr(List<AgentInstitutionDto> uploadArrStr) {
        this.uploadArrStr = uploadArrStr;
    }
}