/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.validator.ValAssetCategories001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 资产大类评级配置查询dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValAssetCategories001Dto
 * @since 2.0
 */
@PageId("AssetCategories001")
@ValidLogic(targetClass = ValAssetCategories001Validator.class)
public class ValAssetCategories001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = -8867951752448157337L;

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
     * 评级机构类型
     */
    private String gradeOrgType;

    /**
     * 评级机构（数据字典 评级机构）
     */
    private String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
     */
    private String oType;

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
