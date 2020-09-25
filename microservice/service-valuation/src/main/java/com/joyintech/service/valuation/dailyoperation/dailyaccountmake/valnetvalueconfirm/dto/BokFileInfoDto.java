/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 产品资产查询Dto
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see BokFileInfoDto
 * @since 1.0
 */
@PageId("ValNetValueConfirm003")
public class BokFileInfoDto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 产品代码
     */
    private String fileId;
    
    /**
     * 产品代码
     */
    private String fileType;
    
    /**
     * 附件名称
     */
    private String fileName;
    
    /**
     * 附件路径
     */
    private String filePath;
    
    
    public String getFileName() {
        return fileName;
    }

    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    public String getFilePath() {
        return filePath;
    }

    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}     				
      			
      