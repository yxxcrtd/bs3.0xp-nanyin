package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

/**
 * 文件信息
 * 
 * @author yangjian
 * @version 1.0 2018年11月15日
 * @see FileInfoDto
 * @since 1.0
 */
public class FileInfoDto {

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 估值日期
     */
    private String valDateString;

    /**
     * 估值表余额表标志
     */
    private String valBalFlag;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 valDateString
     * 
     * @return valDateString.
     */
    public String getValDateString() {
        return valDateString;
    }

    /**
     * 设置 valDateString
     * 
     * @param valDateString valDateString
     */
    public void setValDateString(String valDateString) {
        this.valDateString = valDateString;
    }

    /**
     * 获取 valBalFlag
     * 
     * @return valBalFlag.
     */
    public String getValBalFlag() {
        return valBalFlag;
    }

    /**
     * 设置 valBalFlag
     * 
     * @param valBalFlag valBalFlag
     */
    public void setValBalFlag(String valBalFlag) {
        this.valBalFlag = valBalFlag;
    }

    /**
     * 获取 fileId
     * 
     * @return fileId.
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 fileId
     * 
     * @param fileId fileId
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取 fileType
     * 
     * @return fileType.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 fileType
     * 
     * @param fileType fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取 fileName
     * 
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 fileName
     * 
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 filePath
     * 
     * @return filePath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 filePath
     * 
     * @param filePath filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
