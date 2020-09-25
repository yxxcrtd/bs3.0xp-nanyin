/*
 * 文件名：MstCaptialAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.validator.MstPerformanceBenchMark001Validator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 复合基准管理的业绩基准实体类
 * 
 * @author zhangjun
 * @version 1.0 2018年8月23日
 * @see MstPerformanceBenchMark002
 * @since 1.0
 * 
 */
@PageId("MstPerformanceBenchMark002")
@ValidLogic(targetClass = MstPerformanceBenchMark001Validator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class MstPerformanceBenchMarkDto extends MstIndexInfo implements ITableRow, ProcessEntity{
    
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
    private transient List<MstPerformanceBenchMarkDto> selectedItem;
    
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
     * 基准成分
     */
    private List<ComponentInfoDto> componentInfo;
  
    /**
     * 基准编号
     */
    @Override
    public String getIndexId() {
        return super.getIndexId();
    }
    /**
     * 基准名称
     */
    @Override
    @NotEmpty(fieldName = "MstPerformanceBenchMark001.indexName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstPerformanceBenchMark001.indexName", max = 15, groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
    public String getIndexName() {
        return super.getIndexName();
    }
    /**
     * 基准类型
     */
    @Override
    @NotEmpty(fieldName = "MstPerformanceBenchMark001.indexType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstPerformanceBenchMark001.indexType", max = 200, groups = {ValidateGroupEdit.class})
    public String getIndexType() {
        return super.getIndexType();
    }
    /**
     * 基准类型Text值
     * @return String
     */
    public String getIndexTypeName(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.基准类型, super.getIndexType());
    }
    /**
     * 获取 基准成分
     * @return componentInfo.
     */
    public List<ComponentInfoDto> getComponentInfo() {
        return componentInfo;
    }

    /**
     * 设置 基准成分
     * @param componentInfo componentInfo
     */
    public void setComponentInfo(List<ComponentInfoDto> componentInfo) {
        this.componentInfo = componentInfo;
    }
    
    /**
     * 获取 操作类型
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 操作类型
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 <意义，目的和功能，以及可能被用到的地方>
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 <意义，目的和功能，以及可能被用到的地方>
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 选中的数据
     * @return selectedItem.
     */
    public List<MstPerformanceBenchMarkDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 选中的数据
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstPerformanceBenchMarkDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取 文件名
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * 设置 文件名
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 获取 文件编号
     * @return fileNo.
     */
    public String getFileNo() {
        return fileNo;
    }
    
    /**
     * 设置 文件编号
     * @param fileNo fileNo
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }
    
    /**
     * 获取 文件服务器路径
     * @return path.
     */
    public String getPath() {
        return path;
    }
    
    /**
     * 设置文件服务器路径
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
