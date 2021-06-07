package com.joyintech.tams.svreport.chb.perprodeclarereg.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class PerProdDeclareregDto extends BaseEntity implements ProcessEntity{

	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 附件上传
     */
    private List<UploadFileInfo> fileArray;
    
    /**
     * 产品申报dto
     */
    private List<PerProDeclareRegDto> uploadArrStr;

	public List<UploadFileInfo> getFileArray() {
		return fileArray;
	}

	public void setFileArray(List<UploadFileInfo> fileArray) {
		this.fileArray = fileArray;
	}

	public List<PerProDeclareRegDto> getUploadArrStr() {
		return uploadArrStr;
	}

	public void setUploadArrStr(List<PerProDeclareRegDto> uploadArrStr) {
		this.uploadArrStr = uploadArrStr;
	}  
}
