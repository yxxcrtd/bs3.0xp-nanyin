
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.validator.ValAssetCategories002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产大类评级to
 * 
 * @author 刘华明
 * @version 1.0 2021年6月29日
 * @see ValAssetCategories002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValAssetCategories002Validator.class)
public class ValAssetCategories002Dto extends BaseEntity implements ProcessEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 资产类型（证券品种_模型）
     */
    @PrimaryKey(value=Field.PK_1)
    private String assetType;

    /**
     * 资产细分类（数据字典证券细分类_模型）
     */
    @PrimaryKey(value=Field.PK_2)
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

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getAssetType() {
        return assetType;
    }

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

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getGradeOrgType() {
        return gradeOrgType;
    }

    public void setGradeOrgType(String gradeOrgType) {
        this.gradeOrgType = gradeOrgType;
    }
}
