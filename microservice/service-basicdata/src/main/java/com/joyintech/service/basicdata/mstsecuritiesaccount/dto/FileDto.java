/*
 * 文件名：FileDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstsecuritiesaccount.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 上传附件Dto 〈功能详细描述〉
 * 
 * @author zhangjun
 * @version 1.0 2018年9月15日
 * @see FileDto
 * @since 1.0
 */
public class FileDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String srcName;

    /**
     * 文档代码
     */
    private String businessId;

    /**
     * 文件路径
     */
    private String targetFile;

    /**
     * 流水号
     */
    private String fileNo;

    
    /**
     * 获取 srcName
     * @return srcName.
     */
    public String getSrcName() {
        return srcName;
    }

    
    /**
     * 设置 srcName
     * @param srcName srcName
     */
    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    
    /**
     * 获取 businessId
     * @return businessId.
     */
    public String getBusinessId() {
        return businessId;
    }

    
    /**
     * 设置 businessId
     * @param businessId businessId
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    
    /**
     * 获取 targetFile
     * @return targetFile.
     */
    public String getTargetFile() {
        return targetFile;
    }

    
    /**
     * 设置 targetFile
     * @param targetFile targetFile
     */
    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    
    /**
     * 获取 fileNo
     * @return fileNo.
     */
    public String getFileNo() {
        return fileNo;
    }

    
    /**
     * 设置 fileNo
     * @param fileNo fileNo
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }
    
}
