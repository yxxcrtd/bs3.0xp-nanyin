package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskIndividConditionCustom;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.valid.RskSupervisionIndividuation007Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

@PageId("RskSupervisionIndividuation007")
@ValidLogic(targetClass = RskSupervisionIndividuation007Validator.class,groups = ValidateGroupEdit.class)
public class RskIndividConditionCustomDto extends RskIndividConditionCustom {
    private static final long serialVersionUID = 1L;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    /**
     * 取得 条件代码
     * @return 条件代码
     */
    @Override
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "RskSupervisionIndividuation001.conditionItem")
    public String getConditionItem() {
        return super.getConditionItem();
    }


    /**
     * 取得 条件名称
     * @return 条件名称
     */
    @Override
    @NotEmpty(fieldName = "RskSupervisionIndividuation001.conditionName")
    public String getConditionName() {
        return super.getConditionName();
    }


    /**
     * 取得 单位
     * @return 单位
     */
    @Override
    @NotEmpty(fieldName = "RskSupervisionIndividuation001.conditionUnit")
    public String getConditionUnit() {
        return super.getConditionUnit();
    }

    /**
     * 取得 单位
     * @return 单位
     */
    public String getConditionUnitStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.单位, this.getConditionUnit());
    }

    /**
     * 取得 类型
     * @return 类型
     */
    @Override
    @NotEmpty(fieldName = "RskSupervisionIndividuation001.conditionType")
    public String getConditionType() {
        return super.getConditionType();
    }


    /**
     * 取得 内容
     * @return 内容
     */
    @Override
    @NotEmpty(fieldName = "RskSupervisionIndividuation001.content")
    public String getContent() {
        return super.getContent();
    }
}
