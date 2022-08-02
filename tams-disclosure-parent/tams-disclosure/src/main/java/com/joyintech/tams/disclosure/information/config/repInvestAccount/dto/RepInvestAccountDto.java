package com.joyintech.tams.disclosure.information.config.repInvestAccount.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepInvestAccount;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.validator.RepInvestAccountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;
@PageId("RepInvestAccount002")
@ValidLogic(targetClass = RepInvestAccountValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepInvestAccountDto extends RepInvestAccount implements ITableRow, ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<RepInvestAccountDto> selectedItem;



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

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RepInvestAccountDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepInvestAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdId() {
        return super.getProdId();
    }


    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }
    public String getAccountTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.托管账户, this.getAccountType());
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyyMMddHHmmss");
    }
}
