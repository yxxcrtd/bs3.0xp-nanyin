package com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PrdFileTemplat;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.validator.FinDisclosureModuleValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 
 * @author wangzehui
 *
 */
@PageId("FinDisclosureModule002")
@ValidLogic(targetClass = FinDisclosureModuleValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinDisclosureModuleDto extends PrdFileTemplat implements ProcessEntity {
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 文档代码
     */
    private String fileId;
//    
//    /**
//     * 文件路径
//     */
//    private String path;
//    /**
//     * 文件路径
//     */
//    private String pathName;
//    
//    /**
//     * 流水号
//     */
//    private String seqNo;
//    /**
//     * 流水号
//     * @return 流水号
//     */
//    public String getSeqNo() {
//        return seqNo;
//    }
//
//
//    /**
//     * 流水号
//     * @param seqNo 流水号
//     */
//    public void setSeqNo(String seqNo) {
//        this.seqNo = seqNo;
//    }
//
//
//    public String getPath() {
//        return path;
//    }
//
//    
//    public void setPath(String path) {
//        this.path = path;
//    }
//    public String getPathName() {
//        return pathName;
//    }
//
//
//    
//    public void setPathName(String pathName) {
//        this.pathName = pathName;
//    }

    /**
     * 要素id
     */
    private String elementId;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    
    public String getFileId() {
        return fileId;
    }
    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    /**
     * opType
     */
    private String opType;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<FinDisclosureModuleDto> selectedItem;
    /**
     * 校验披露文档代码
     */
    @Override
    @NotEmpty(fieldName = "FinDisclosureModule001.revealFileId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinDisclosureModule001.revealFileId", max = 32, groups = {
        ValidateGroupEdit.class})
    public String getRevealFileId() {
        return super.getRevealFileId();
    }
    /**
     * 校验文件模板类型
     */
    @Override
    @NotEmpty(fieldName = "FinDisclosureModule001.fileType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinDisclosureModule001.fileType", max = 32, groups = {
        ValidateGroupEdit.class})
    public String getFileType() {
        return super.getFileType();
    }
    public String getFileTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.文件类型, super.getFileType());
    }
    /**
     * 校验文件名
     * @return String String
     */
    @Length(fieldName = "FinDisclosureModule001.fileName", max = 32, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_1)
    public String getFileName() {
        return fileName;
    }
    /**
     * 校验备注
     */
    @Override
    @Length(fieldName = "FinDisclosureModule001.remark", max = 300, groups = {
        ValidateGroupEdit.class})
    public String getRemark() {
        return super.getRemark();
    }
    
    public String getOpType() {
        return opType;
    }

    
    public void setOpType(String opType) {
        this.opType = opType;
    }

    /**
     * 操作类型
     * @return 操作类型
     */
    public String getOperateType() {
        return operateType;
    }


    /**
     * 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    /**
     * 选择数据
     * @return list
     */
    public List<FinDisclosureModuleDto> getSelectedItem() {
        return selectedItem;
    }


    /**
     * 选择数据
     * @param selectedItem list
     */
    public void setSelectedItem(List<FinDisclosureModuleDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 要素id
     * @return elementId 要素id
     */
    public String getElementId() {
        return elementId;
    }


    /**
     * 要素id
     * @param elementId 要素id
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * pageModel
     * @return String
     * @see
     * @since 1.0
     */
    public String getPageModel() {
        return pageModel;
    }


    /**
     * pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 文件名
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
