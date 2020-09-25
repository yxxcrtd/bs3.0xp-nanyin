/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstcaptialaccount.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see MstCaptialAccountDto
 * @since 1.0
 */
@PageId("MstCaptialAccount002")
//@ValidLogic(targetClass = MstCaptialAccountValidator.class, groups = {ValidateGroupEdit.class,
//    ValidateGroupDelete.class})
public class MstCaptialAccountDto extends StlCapacctInfo implements ITableRow{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

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
    private transient List<MstCaptialAccountDto> selectedItem;
    
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
    private List<SysUploadFileInfo> fileArray;
    
    
    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }



    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
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


    public String getFileName() {
        return fileName;
    }

    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getBusiAcctNo() {
        return super.getBusiAcctNo();
    }
    
    @Override
    public String getAcctName() {
        return super.getAcctName();
    }
    
    @Override
    public String getCcy() {
        return super.getCcy();
    }
    
    @Override
    public String getBankDep() {
        return super.getBankDep();
    }
    
    @Override
    public String getLargePayno() {
        return super.getLargePayno();
    }
    
    @Override
    public String getSwiftCode() {
        return super.getSwiftCode();
    }
    
    public List<MstCaptialAccountDto> getSelectedItem() {
        return selectedItem;
    }



    
    public void setSelectedItem(List<MstCaptialAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }



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
    
    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

}
