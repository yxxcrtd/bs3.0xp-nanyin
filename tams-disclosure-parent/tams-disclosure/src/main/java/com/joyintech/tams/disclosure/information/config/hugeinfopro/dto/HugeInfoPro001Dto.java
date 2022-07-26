package com.joyintech.tams.disclosure.information.config.hugeinfopro.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisLargeRedemption;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.proelementadj.validator.ProElementAdjValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("HugeInfoPro002")
public class HugeInfoPro001Dto extends DisLargeRedemption implements ITableRow, ProcessEntity {

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
    private transient List<HugeInfoPro001Dto> selectedItem;
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

    public List<HugeInfoPro001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<HugeInfoPro001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    @Override
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getRedemptionId() {
        return super.getRedemptionId();
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
    @NotEmpty(fieldName = "巨额赎回日期", groups = {ValidateGroupEdit.class})
    public Date getDealDate() {
        return super.getDealDate();
    }


    @Override
    @NotEmpty(fieldName = "ProElementAdj001.rptIssueDate", groups = {ValidateGroupEdit.class})
    public Date getRptIssueDate() {
        return super.getRptIssueDate();
    }

    @Override
    @NotEmpty(fieldName = "HugeInfoPro001.triggerRemark", groups = {ValidateGroupEdit.class})
    public String getTriggerRemark() {
        return super.getTriggerRemark();
    }

    @Override
    @NotEmpty(fieldName = "HugeInfoPro001.rejectRemark", groups = {ValidateGroupEdit.class})
    public String getRejectRemark() {
        return super.getRejectRemark();
    }


    public String getDataStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态,super.getDataStatus());
    }
    public String getIsSalesAgencyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否,super.getIsSalesAgency());
    }
}
