/*
 * 文件名：DocumentManagementDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.document.documentmanagement.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月16日
 * @see DocumentManagementDto
 * @since 1.0
 */
public class DocumentManagementDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 业务编号
     */
    private String businessId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 所属部门编号/机构名称
     */
    private String deptInstitutionCode;
    /**
     * 所属部门名称/机构名称
     */
    private String deptInstitutionName;
    /**
     * 上传文件名
     */
    private String attachmentFileName;
    /**
     * 上传文件流水号
     */
    private String seqno;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 用户名：真实姓名
     */
    private String name;
    /**
     * 附件
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<DocumentManagementDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    
    public String getPortfolioId() {
        return portfolioId;
    }

    
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    
    public String getPortfolioName() {
        return portfolioName;
    }

    
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    
    public String getBusinessType() {
        return businessType;
    }

    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    
    public String getBusinessId() {
        return businessId;
    }

    
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getRealName() {
        return realName;
    }

    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    
    public String getDeptInstitutionCode() {
        return deptInstitutionCode;
    }

    
    public void setDeptInstitutionCode(String deptInstitutionCode) {
        this.deptInstitutionCode = deptInstitutionCode;
    }

    
    public String getDeptInstitutionName() {
        return deptInstitutionName;
    }

    
    public void setDeptInstitutionName(String deptInstitutionName) {
        this.deptInstitutionName = deptInstitutionName;
    }

    
    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    
    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    
    public String getSeqno() {
        return seqno;
    }

    
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    
    public String getOperateType() {
        return operateType;
    }

    
    public String getPath() {
        return path;
    }


    
    public void setPath(String path) {
        this.path = path;
    }

    
    
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }


    
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }


    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public List<DocumentManagementDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<DocumentManagementDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    
    public String getName() {
        return name;
    }


    
    public void setName(String name) {
        this.name = name;
    }

}
