package com.joyintech.tams.disclosure.information.config.profailureinfo.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdNocreate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.profailureinfo.validator.ProFailureInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

@PageId("ProFailureInfo002")
@ValidLogic(targetClass = ProFailureInfoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ProFailureInfo001Dto extends DisProdNocreate implements ITableRow, ProcessEntity {
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
    private transient List<ProFailureInfo001Dto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    /**
     * 产品代码(名称)
     */
    private  String prodId;


    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ProFailureInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProFailureInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getProdId() {
        return prodId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getDataStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,super.getDataStatus());
    }
    public String getIsSalesAgencyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否,super.getIsSalesAgency());
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "ProElementAdj001.prodRegistNo", groups = {ValidateGroupEdit.class})
    public String getProdRegistNo() {
        return super.getProdRegistNo();
    }

    @Override
    @NotEmpty(fieldName = "不成立原因", groups = {ValidateGroupEdit.class})
    public String getNocreateReason() {
        return super.getNocreateReason();
    }


    @Override
    @NotEmpty(fieldName = "报表发布日期", groups = {ValidateGroupEdit.class})
    public Date getReportIssueDate() {
        return super.getReportIssueDate();
    }

    private String reportIssueDateStr;
    public String getReportIssueDateStr(){
        return DateUtil.format(super.getReportIssueDate());
    }
}
