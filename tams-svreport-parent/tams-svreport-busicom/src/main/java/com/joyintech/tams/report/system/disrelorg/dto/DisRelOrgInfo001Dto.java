package com.joyintech.tams.report.system.disrelorg.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.disrelorg.validator.DisRelOrgValidator;
import com.joyintech.tams.report.system.prorate.validator.DisPrdFeeRateValidator;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.workflow.engine.dto.ProcessEntity;


@TableName("REP_RELORG")
@PageId("DisRelOrg002")
@ValidLogic(targetClass = DisRelOrgValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class DisRelOrgInfo001Dto extends DisAssociatedInstitutions implements ITableRow, ProcessEntity {


}
