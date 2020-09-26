package com.joyintech.tams.valuation.common.interf.base.dto;


public class TemplateSourceFlgDTO {
    
    /**
     * 调用来源
     */
    private String callSourceType;
    
    /**
     * 文件类型
     */
    private String tmpType;
    
    /**
     * 文件路径
     */
    private String filePath;

    /**
     *  取得 调用来源
     *  @return 调用来源
     */
    public String getCallSourceType() {
       return callSourceType;
    }

    /**
     *  设置 调用来源
     *  @param callSourceType 调用来源
     */
    public void setCallSourceType( String callSourceType)  {
       this.callSourceType = callSourceType;
    }

    /**
     *  取得 文件类型
     *  @return 文件类型
     */
    public String getTmpType() {
       return tmpType;
    }

    /**
     *  设置 文件类型
     *  @param fileType 文件类型
     */
    public void setTmpType( String tmpType)  {
       this.tmpType = tmpType;
    }

    /**
     *  取得 文件路径
     *  @return 文件路径
     */
    public String getFilePath() {
       return filePath;
    }

    /**
     *  设置 文件路径
     *  @param filePath 文件路径
     */
    public void setFilePath( String filePath)  {
       this.filePath = filePath;
    }
}