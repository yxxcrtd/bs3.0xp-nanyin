/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstcaptialaccount.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.validator.MstCaptialAccountValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;
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
@ValidLogic(targetClass = MstCaptialAccountValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class MstCaptialAccountDto extends StlCapacctInfo implements ITableRow, ProcessEntity, GridRowDto{
    
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
    private List<UploadFile> fileArray;
    
    
    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }

    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
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
    @Length(fieldName = "MstCaptialAccount002.accountId", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstCaptialAccount002.accountId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
    public String getBusiAcctNo() {
        return super.getBusiAcctNo();
    }
    
    @Override
    @NotEmpty(fieldName = "MstCaptialAccount001.accountName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstCaptialAccount001.accountName", max = 200, groups = {
        ValidateGroupEdit.class})
    public String getAcctName() {
        return super.getAcctName();
    }
    
    @Override
    @Length(fieldName = "MstCaptialAccount001.ccy", max = 50, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstCaptialAccount001.ccy", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_3)
    public String getCcy() {
        return super.getCcy();
    }
    
    @Override
    @NotEmpty(fieldName = "MstCaptialAccount001.bankDep", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstCaptialAccount001.bankDep", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getBankDep() {
        return super.getBankDep();
    }
    
    @Override
//    @NotEmpty(fieldName = "MstCaptialAccount002.accountOutlet", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstCaptialAccount002.accountOutlet", max = 20, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_2)
    public String getLargePayno() {
        return super.getLargePayno();
    }
    
    @Override
    /*@NotEmpty(fieldName = "MstCaptialAccount002.swiftCode", groups = {ValidateGroupEdit.class})*/
    @Length(fieldName = "MstCaptialAccount002.swiftCode", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getSwiftCode() {
        return super.getSwiftCode();
    }
    
    @Length(fieldName = "MstCaptialAccount002.remark", max = 1000, groups = {
        ValidateGroupEdit.class})
    public String getRemark(){
        return super.getRemark();
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

    
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public String getRowId() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setId(String arg0) {
        
    }

    @Override
    public void setLeaf(boolean arg0) {
        
    }

    @Override
    public void setName(String arg0) {
        
    }

    @Override
    public void setOrder(int arg0) {
        
    }

    @Override
    public void setParentId(String arg0) {
        
    }

    @Override
    public void setRowId(String arg0) {
        
    }

}
