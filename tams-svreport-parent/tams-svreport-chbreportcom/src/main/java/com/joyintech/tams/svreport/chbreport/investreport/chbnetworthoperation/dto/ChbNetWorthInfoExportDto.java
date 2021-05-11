package com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dto;

/**
 * 文件导出Dto
 *
 * @author wangyong
 * @version 1.0 2021年5月10日
 * @see ChbNetWorthInfoExportDto
 * @since 1.0
 */
public class ChbNetWorthInfoExportDto{

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
     *
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * 设置 文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * 获取 文件路径
     *
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }


    /**
     * 设置 文件路径
     *
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
