package com.joyintech.tams.disclosure.information.config.repreportcheck.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepReportCheck;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repreportcheck.validator.ReportCheckValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("ReportCheck001")
@ValidLogic(targetClass = ReportCheckValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ReportCheckDto extends RepReportCheck implements ITableRow, ProcessEntity {

    private String reportTypeName ;

    private  String prodName;

    private List<ReportCheckDto> selectedItem;

    public String getReportTypeName() {
      //  return reportTypeName;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披报表类别,getReportType());

    }


    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    public String getCheckDateStr(){
        return DateUtil.format(this.getCheckDate());
    }

    public List<ReportCheckDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ReportCheckDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}