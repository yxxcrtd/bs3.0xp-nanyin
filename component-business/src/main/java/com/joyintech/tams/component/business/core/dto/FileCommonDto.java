/*
 * 文件名：FilecCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.core.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 附件信息
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see FilecCommonDto
 * @since 1.0
 */
public class FileCommonDto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 业务编号
     */
    private String bussinessId;
    
    /**
     * 文件路径
     */
    private String path;
    
    /**
     * 流水号
     */
    private String fileNo;
    
    private MstFileInputParameter mstFileInputParameter;

    
    
    
    /**
     * 获取 mstFileInputParameter
     * @return mstFileInputParameter.
     */
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }


    
    /**
     * 设置 mstFileInputParameter
     * @param mstFileInputParameter mstFileInputParameter
     */
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }


    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    
    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * 获取 bussinessId
     * @return bussinessId.
     */
    public String getBussinessId() {
        return bussinessId;
    }

    
    /**
     * 设置 bussinessId
     * @param bussinessId bussinessId
     */
    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    
    /**
     * 获取 path
     * @return path.
     */
    public String getPath() {
        return path;
    }

    
    /**
     * 设置 path
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
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
