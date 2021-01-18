package com.joyintech.tams.svreport.chb.assettypesetting.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbAssetTypeMappingEx;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assettypesetting.validator.AssetTypeSettingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.List;
@PageId("AssetTypeSetting002")
@ValidLogic(targetClass= AssetTypeSettingValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class AssetTypeSetting001Dto extends BaseEntity {

    /**
     * 主键，自动生成
     */
    private String typeuuid;

    /**
     * 资产一级类型
     */
    private String assetType1;

    /**
     * 资产二级类型
     */
    private String assetType2;

    /**
     * 资产三级类型
     */
    private String assetType3;

    /**
     * 业务分类
     */
    private String assetBusiType;

    /**
     * 部门代码
     */
    private String deptCode;

    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getTypeuuid() {
        return typeuuid;
    }

    public void setTypeuuid(String typeuuid) {
        this.typeuuid = typeuuid;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getAssetType3() {
        return assetType3;
    }

    public void setAssetType3(String assetType3) {
        this.assetType3 = assetType3;
    }

    public String getAssetBusiType() {
        return assetBusiType;
    }

    public void setAssetBusiType(String assetBusiType) {
        this.assetBusiType = assetBusiType;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 资产负债类别
     */
    private String chbAssetType;

    /**
     * 资产负债二级类别
     */
    private String chbAssetType2;

    /**
     * 资产负债类别映射表uuid
     */
    private String mappingUuid;

    public String getChbAssetType() {
        return chbAssetType;
    }

    public void setChbAssetType(String chbAssetType) {
        this.chbAssetType = chbAssetType;
    }

    public String getChbAssetType2() {
        return chbAssetType2;
    }

    public void setChbAssetType2(String chbAssetType2) {
        this.chbAssetType2 = chbAssetType2;
    }

    public String getMappingUuid() {
        return mappingUuid;
    }

    public void setMappingUuid(String mappingUuid) {
        this.mappingUuid = mappingUuid;
    }

    /**
     * selectedItem
     */
    public List<AssetTypeSetting001Dto> selectedItem;

    public List<AssetTypeSetting001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<AssetTypeSetting001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * selectedItem1
     */
    public List<ChbAssetTypeMappingEx> selectedItem1;

    public List<ChbAssetTypeMappingEx> getSelectedItem1() {
        return selectedItem1;
    }

    public void setSelectedItem1(List<ChbAssetTypeMappingEx> selectedItem1) {
        this.selectedItem1 = selectedItem1;
    }

    /**
     * 资产一级分类字符串
     * @return
     */
    public String getAssetType1Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.宁波资产一级分类, this.getAssetType1());
    }

    /**
     * 资产二级分类字符串
     * @return
     */
    public String getAssetType2Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.宁波资产二级分类, this.getAssetType2());
    }

    /**
     * 资产三级分类字符串
     * @return
     */
    public String getAssetType3Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.宁波资产三级分类, this.getAssetType3());
    }

    /**
     * 业务分类字符串
     * @return
     */
    public String getAssetBusiTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.宁波业务类型, this.getAssetBusiType());
    }

    /**
     * 资产负债类别字符串
     * @return
     */
    public String getChbAssetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资产_负债类别, this.getChbAssetType());
    }

    /**
     * 资产负债二级类别字符串
     * @return
     */
    public String getChbAssetType2Str(){
        return SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, this.getChbAssetType2());
    }

    /**
     * 操作类型
     */
    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     *
     */
    private String pageModel;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}
