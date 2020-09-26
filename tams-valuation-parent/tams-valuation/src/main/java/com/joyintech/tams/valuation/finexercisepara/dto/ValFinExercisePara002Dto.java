/*
 * 文件名：ValFinExercisePara002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.finexercisepara.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.finexercisepara.validator.ValFinExercisePara002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 含权债摊销参数设置
 * 
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara002Dto
 * @since 1.0
 */
@PageId("ValFinExercisePara002")
@ValidLogic(targetClass = ValFinExercisePara002DtoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ValFinExercisePara002Dto extends BaseEntity implements ProcessEntity {

    /**
     * 账套代码
     */
    @PrimaryKey(value = Field.PK_1)
    @NotBlank(message = "账套代码不能为空！", groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 金融产品代码
     */
    @PrimaryKey(value = Field.PK_2)
    @NotBlank(message = "资产代码不能为空！", groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 含权债摊销日
     */
    @NotBlank(message = "含权债摊销日设置不能为空！", groups = {ValidateGroupEdit.class})
    private String exeDateType;

    /**
     * 处理操作
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getExeDateType() {
        return exeDateType;
    }

    public void setExeDateType(String exeDateType) {
        this.exeDateType = exeDateType;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
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

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

}
