/*
 * 文件名：FinSubmittedModule002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.validator.FinSubmittedModule002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 申报附件方案dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月22日
 * @see FinSubmittedModule002Dto
 * @since 1.0
 */
@PageId("FinSubmittedModule003")
@ValidLogic(targetClass = FinSubmittedModule002Validator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinSubmittedModule002Dto extends PrdDeclareSchemeDoc implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据列表
     */
    private transient List<FinSubmittedModule002Dto> selectedItem;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文档代码
     */
    private String businessId;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 取得 附件类型
     * 
     * @return 附件类型
     */
    @NotEmpty(fieldName = "FinSubmittedModule003.docType", groups = {ValidateGroupEdit.class})
    public String getDocType() {
        return super.getDocType();
    }

    /**
     * 取得 附件类型
     * 
     * @return 附件类型
     */
    @NotEmpty(fieldName = "FinSubmittedModule003.tplType", groups = {ValidateGroupEdit.class})
    public String getTplType() {
        return super.getTplType();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    @Length(fieldName = "FinSubmittedModule003.remark", max = 300)
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<FinSubmittedModule002Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinSubmittedModule002Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得 流水号
     * 
     * @return 流水号
     */
    @PrimaryKey(Field.PK_1)
    public String getSeqno() {
        return super.getSeqno();
    }

    /**
     * 将附件类型对应的数据字典代码转换为数据字典值
     * 
     * @return 数据字典值
     */
    public String getDocTypeAbbr() {
        return SysDataUtil.getNameFromDataDict(DictCode.附件类型, super.getDocType());
    }

    /**
     * 将模板类型对应的数据字典代码转换为数据字典值
     * 
     * @return 数据字典值
     */
    public String getTplTypeAbbr() {
        return SysDataUtil.getNameFromDataDict(DictCode.模板类型, super.getTplType());
    }

    /**
     * 获取 fileName
     * 
     * @return fileName.
     */
    @NotEmpty(fieldName = "FinSubmittedModule002.appendix", groups = {ValidateGroupEdit.class})
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 fileName
     * 
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 businessId
     * 
     * @return businessId.
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * 设置 businessId
     * 
     * @param businessId businessId
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取 path
     * 
     * @return path.
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 path
     * 
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取 seqNo
     * 
     * @return seqNo.
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 seqNo
     * 
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    
    public String getFileId() {
        return fileId;
    }

    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
}
