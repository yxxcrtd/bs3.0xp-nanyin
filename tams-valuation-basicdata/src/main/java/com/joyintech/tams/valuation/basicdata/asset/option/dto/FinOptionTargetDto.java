package com.joyintech.tams.valuation.basicdata.asset.option.dto; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：
 * 修改时间：
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.asset.option.valid.FinOptionTargetValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("FinOptionTarget002")
@ValidLogic(targetClass = FinOptionTargetValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinOptionTargetDto extends BaseEntity implements ProcessEntity {
    private static final long serialVersionUID = -5790927186395487117L;
    /**
     * 标的代码
     */
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    private String targetId;

    /**
     * 标的名称
     */
     private String targetName;

     /**
      * 文件上传dto
      */
     private List<SysUploadFileInfo> fileArray;

    /**
     * 标的类型
     */
    private String targetType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 已选择的数据
     */
    public List<FinOptionTargetDto> SelectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 数据来源
     */
    private String dataSource;
    private String dataSourceValue;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceValue(){
        return SysDataUtil.getNameFromDataDict(DictCode.估值数据来源,this.getDataSource());
    }

    public String getUpdateTimeStr(){
        String updateTimeStr = DateUtil.format(this.getUpdateTime(), "yyyy/MM/dd");
        return updateTimeStr;
    }
    /**
     * 获取 fileArray
     *
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     *
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
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

    public List<FinOptionTargetDto> getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(List<FinOptionTargetDto> selectedItem) {
        SelectedItem = selectedItem;
    }
    public String getTargetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.期权资产类型, this.getTargetType());
    }
}
