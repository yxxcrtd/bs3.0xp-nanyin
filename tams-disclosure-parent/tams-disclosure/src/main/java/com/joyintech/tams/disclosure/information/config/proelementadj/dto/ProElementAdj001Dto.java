package com.joyintech.tams.disclosure.information.config.proelementadj.dto;

import com.joyintech.framework.annotation.PageId;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdAdjust;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.proelementadj.validator.ProElementAdjValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("ProElementAdj002")
@ValidLogic(targetClass = ProElementAdjValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ProElementAdj001Dto extends DisProdAdjust implements ITableRow, ProcessEntity {
    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735261L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<ProElementAdj001Dto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ProElementAdj001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProElementAdj001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getIsSalesAgencyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否,super.getIsSalesAgency());
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdAdjustId() {
        return super.getProdAdjustId();
    }

    @Override
    @NotEmpty(fieldName = "ProElementAdj001.prodRegistNo", groups = {ValidateGroupEdit.class})
    public String getProdRegistNo() {
        return super.getProdRegistNo();
    }

    @Override
    @NotEmpty(fieldName = "ProElementAdj001.prodId", groups = {ValidateGroupEdit.class})
    public String getProdId() {
        return super.getProdId();
    }


    @Override
    @NotEmpty(fieldName = "ProElementAdj001.adjField", groups = {ValidateGroupEdit.class})
    public String getAdjField() {
        return super.getAdjField();
    }

    @Override
    @NotEmpty(fieldName = "ProElementAdj001.preAdjust", groups = {ValidateGroupEdit.class})
    public String getPreAdjust() {
        return super.getPreAdjust();
    }

    @Override
    @NotEmpty(fieldName = "ProElementAdj001.afterAdjust", groups = {ValidateGroupEdit.class})
    public String getAfterAdjust() {
        return super.getAfterAdjust();
    }

    @Override
    @NotEmpty(fieldName = "ProElementAdj002.effDate", groups = {ValidateGroupEdit.class})
    public Date getEffDate() {
        return super.getEffDate();
    }


    @Override
    @NotEmpty(fieldName = "ProElementAdj001.rptIssueDate", groups = {ValidateGroupEdit.class})
    public Date getRptIssueDate() {
        return super.getRptIssueDate();
    }


    public String getDataStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,super.getDataStatus());
    }


}
