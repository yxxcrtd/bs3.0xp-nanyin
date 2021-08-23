package com.joyintech.tams.invest.bankuplink.reponew.dto;

import com.joyintech.framework.base.dto.UploadFile;

import java.util.List;

/**
 * @author chenjiaxing
 * @date 2021年08月17日
 * @see CfetsRepoDealQuotedPrFileDto
 * @since 1.0
 */
public class CfetsRepoDealQuotedPrFileDto {
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
