/*
 * 文件名：TaxScheme004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxscheme.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.validator.TaxScheme006DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 参数设置
 * @author zhangwei
 * @version 1.0 2018年10月17日
 * @see TaxScheme006Dto
 * @since 1.0
 */
@PageId("TaxScheme006")
@ValidLogic(targetClass = TaxScheme006DtoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class TaxScheme006Dto extends BaseEntity implements ProcessEntity {

    /**
     * 流水
     */
    @PrimaryKey(value=Field.PK_1)
    private String seqId;
    
    /**
     * 方案代码
     */
    @PrimaryKey(value=Field.PK_2)
    private String schemeId;

    /**
     * 配置项代码
     */
    @PrimaryKey(value=Field.PK_3)
    @NotBlank(message="项目不能为空!", groups= {ValidateGroupEdit.class})
    private String configId;
    
    /**
     * 配置项名称 - 项目
     */
    private String configName;
    
    /**
     * 配置项值  - 值
     */
    @NotBlank(message="值不能为空!", groups= {ValidateGroupEdit.class})
    @Length(max=50, message="值不能大于50个字符", groups= {ValidateGroupEdit.class})
    private String configValue;
    
    /**
     * 生效日
     */
    @PrimaryKey(value=Field.PK_4)
    @NotBlank(message="生效日不能为空!", groups= {ValidateGroupEdit.class})
    private String effDate;

    /**
     * 说明
     */
    private String remark;
    
    /**
     * 取值数据字典
     */
    private String dictCode;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 处理操作
     */
    private String operateType;
    
    
    public String getSeqId() {
        return seqId;
    }

    
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    
    public String getConfigName() {
        return configName;
    }

    
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    
    public String getConfigValue() {
        return configValue;
    }

    
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    
    public String getEffDate() {
        return effDate;
    }

    
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }


    
    public String getSchemeId() {
        return schemeId;
    }


    
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }


    
    public String getConfigId() {
        return configId;
    }


    
    public void setConfigId(String configId) {
        this.configId = configId;
    }


    
    public String getOperateType() {
        return operateType;
    }


    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    public String getRemark() {
        return remark;
    }


    
    public void setRemark(String remark) {
        this.remark = remark;
    }


    
    public String getDictCode() {
        return dictCode;
    }


    
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }


    
    public String getPageModel() {
        return pageModel;
    }


    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    
    
}
