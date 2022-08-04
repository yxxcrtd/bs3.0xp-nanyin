package com.joyintech.tams.disclosure.information.config.repreportcheck.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.common.base.entity.RepReportCheck;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.repreportcheck.validator.ReportCheckValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("ReportCheck001")
@ValidLogic(targetClass = ReportCheckValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ReportCheckDto extends RepReportCheck implements ITableRow, ProcessEntity {

    private String reportTypeName ;

    public String getReportTypeName() {
      //  return reportTypeName;
        return SysDataUtil.getNameFromDataDict(DictCodeExt1.信披报表类别,getReportType());

    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }
}