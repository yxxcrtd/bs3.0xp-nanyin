
package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 资产大类配置Dto
 * 
 * @author 刘华明
 * @version 1.0 2021年6月29日
 * @see AssetCategories002Dto
 * @since 1.0
 */
@PageId("AssetCategories002")
public class AssetCategories002Dto extends BaseEntity implements ProcessEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 资产细分类
     */
    private String assetDetailType;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级类型
     */
    private String oType;

    /**
     * 评级机构类型
     */
    private String gradeOrgType;

    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * elementId
     */
    private String elementId;


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

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    @PrimaryKey(Field.PK_1)
    @NotEmpty(fieldName = "资产类别", groups = {ValidateGroupEdit.class})
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @PrimaryKey(Field.PK_2)
    public String getAssetDetailType() {
        return assetDetailType;
    }

    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType;
    }

   /* @NotEmpty(fieldName = "评级机构", groups = {ValidateGroupEdit.class})*/
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    @NotEmpty(fieldName = "评级类型", groups = {ValidateGroupEdit.class})
    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    /**
     * 是新建还是修改操作 Y：修改 N：新建
     */
    private String isEdit;

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    @NotEmpty(fieldName = "评级机构类型", groups = {ValidateGroupEdit.class})
    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }

    /**
     * 选中的数据
     */
    private transient List<AssetCategories002Dto> selectedItem;

    public List<AssetCategories002Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<AssetCategories002Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

}
