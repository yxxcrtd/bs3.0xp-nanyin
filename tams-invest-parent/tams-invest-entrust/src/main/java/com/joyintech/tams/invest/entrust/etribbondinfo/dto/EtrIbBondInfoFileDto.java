/*
 * 文件名：EtrIbBondInfoFileDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月27日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.entrust.etribbondinfo.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

import java.util.List;

/**
 * @author 王波
 * @date 2021年01月27日
 * @see EtrIbBondInfoFileDto
 * @since 1.0
 */
public class EtrIbBondInfoFileDto {
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
