package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("AssetCategories001")
public class AssetCategories001Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4970789074805169078L;

    /**
     * 流水号
     */
    private String seqNo;
    
    
    /**
     * 资产类型（证券品种_模型）
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String assetType;

    /**
     * 资产细分类（数据字典证券细分类_模型）
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    private String assetDetailType;

    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
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

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getAssetType() {return assetType;}

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetDetailType() {
        return assetDetailType;
    }

    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getoType() {
        return oType;
    }

    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }

    public void setoType(String oType) {
        this.oType = oType;
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

    public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.证券品种, this.getAssetType());
    }

    public String getGradeOrgTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构类型, this.getGradeOrgType());
    }

    public String getAssetDetailTypeStr() {
        String strName="";
        if(StringUtil.isNotEmpty(this.getAssetType())){
            if(DictCodeListExt1.证券品种_债券.getValue().equals(this.getAssetType())){
                strName=SysDataUtil.getNameFromDataDict(DictCode.债券类型, this.getAssetDetailType());
            }else if(DictCodeListExt1.证券品种_存款.getValue().equals(this.getAssetType())){
                strName=SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型_GZ, this.getAssetDetailType());
            }else if(DictCodeListExt1.证券品种_回购.getValue().equals(this.getAssetType())){
                strName=SysDataUtil.getNameFromDataDict(DictCode.回购标的类型, this.getAssetDetailType());
            }else if(DictCodeListExt1.证券品种_其他债权类.getValue().equals(this.getAssetType())){
                strName=SysDataUtil.getNameFromDataDict(DictCode.计划类资产类型, this.getAssetDetailType());
            }
        }
        return strName;
    }

    public String getGradeOrgIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构, this.getGradeOrgId());
    }

    public String getoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级类型_减值, this.getoType());
    }
}
