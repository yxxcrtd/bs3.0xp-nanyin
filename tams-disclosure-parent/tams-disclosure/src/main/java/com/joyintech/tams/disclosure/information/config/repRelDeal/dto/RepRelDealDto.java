package com.joyintech.tams.disclosure.information.config.repRelDeal.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepRelDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repRelDeal.validator.RepRelDealValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("RepRelDeal002")
@ValidLogic(targetClass = RepRelDealValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepRelDealDto extends RepRelDeal implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
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
    private transient List<RepRelDealDto> selectedItem;

    /**
     * 产品名称
     */
    private String prodName;

    private String effdateStr;

    private String effdelidateStr;

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getUuid() {
        return super.getUuid();
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

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RepRelDealDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepRelDealDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getPubDateStr() {
        return this.getPubDate()==null?null: DateUtil.format(this.getPubDate(), "yyyy-MM-dd");
    }

    public String getEffdateStr() {
        if (StringUtil.isNotEmpty(this.effdateStr)) {
            return this.effdateStr;
        } else {
            return this.getEffdate() == null ? null : DateUtil.format(this.getEffdate(), "yyyy-MM-dd");
        }
    }

    public String getEffdelidateStr() {
        if (StringUtil.isNotEmpty(this.effdelidateStr)) {
            return this.effdelidateStr;
        } else {
            return this.getEffdelidate() == null ? null : DateUtil.format(this.getEffdelidate(), "yyyy-MM-dd");
        }
    }

    public void setEffdateStr(String effdateStr) {
        this.effdateStr = effdateStr;
    }

    public void setEffdelidateStr(String effdelidateStr) {
        this.effdelidateStr = effdelidateStr;
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
}
