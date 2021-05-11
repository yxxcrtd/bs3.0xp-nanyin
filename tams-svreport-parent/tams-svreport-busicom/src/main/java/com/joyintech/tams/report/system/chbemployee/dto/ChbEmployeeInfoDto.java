package com.joyintech.tams.report.system.chbemployee.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.chbemployee.validator.ChbEmployeeInfoValidator;
import com.joyintech.tams.report.system.mstbond.validator.MstBondCreditRatingValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@TableName("CHB_EMPLOYEE_INFO")
@PageId("ChbEmployeeInfo002")
@ValidLogic(targetClass = ChbEmployeeInfoValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class ChbEmployeeInfoDto extends ChbEmployeeInfo implements ITableRow, ProcessEntity {

}
