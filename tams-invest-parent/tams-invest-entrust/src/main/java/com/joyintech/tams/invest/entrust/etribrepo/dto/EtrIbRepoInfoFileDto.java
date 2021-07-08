package com.joyintech.tams.invest.entrust.etribrepo.dto;

import com.joyintech.framework.base.dto.UploadFile;
import java.util.List;

/**
 * @author 王波
 * @date 2021年01月27日
 * @see EtrIbRepoInfoFileDto
 * @since 1.0
 */
public class EtrIbRepoInfoFileDto {
    /**
     * 文件上传dto
     */
    private List<UploadFile> fileArray;

    /**
     * 文件业务ID
     */
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }
}
