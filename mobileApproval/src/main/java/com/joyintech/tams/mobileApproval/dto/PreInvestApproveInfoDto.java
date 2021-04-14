/*
 * 文件名：PreInvestApproveInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.mobileApproval.dto;


import java.util.List;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产预审批dto
 * 〈功能详细描述〉
 * @author hzt
 * @version 1.0 2019年8月8日
 * @see PreInvestApproveInfoDto
 * @since 1.0
 */
@PageId("PreInvestApproveInfo002")
public class PreInvestApproveInfoDto extends PreInvestApprove implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8503717126985211652L;
	/**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    
    /**
     * 选中的数据
     */
    private transient List<PreInvestApproveInfoDto> selectedItem;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 文件编号
     */
    private String fileNo;
    
    /**
     * 文件服务器路径
     */
    private String path;
    
    /**
     * 
     */
    private List<UploadFile> fileArray;

	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public List<UploadFile> getFileArray() {
		return fileArray;
	}
	public void setFileArray(List<UploadFile> fileArray) {
		this.fileArray = fileArray;
	}
	public List<PreInvestApproveInfoDto> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(List<PreInvestApproveInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getPageModel() {
		return pageModel;
	}
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
