/*
 * 文件名：WflValidInputParameter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;


/**
 * 附件信息共通函数输入参数
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月26日
 * @see MstFileInputParameter
 * @since 1.0
 */
public class MstFileInputParameter {

    /**
     * 业务编号
     */
    private String bussinessId;
    
    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 文件List(全路径)
     */
    private List<SysUploadFileInfo> fileList;

    
    
    /**
     * 获取 fileList
     * @return fileList.
     */
    public List<SysUploadFileInfo> getFileList() {
        return fileList;
    }


    
    /**
     * 设置 fileList
     * @param fileList fileList
     */
    public void setFileList(List<SysUploadFileInfo> fileList) {
        this.fileList = fileList;
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
}
