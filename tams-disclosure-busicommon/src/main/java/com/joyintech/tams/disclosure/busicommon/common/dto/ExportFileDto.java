/*
 * 文件名：ExportFileDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.common.dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年8月30日
 * @see ExportFileDto
 * @since 1.0
 */
public class ExportFileDto {
    
    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 文件路径
     */
    private String filePath;

    
    /**
     * 获取 文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    
    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * 获取 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    
    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    

}
